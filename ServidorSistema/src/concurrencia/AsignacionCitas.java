package concurrencia;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import entidadesTransversales.Candidato;
import entidadesTransversales.Oferta;
import estadoSistema.SingletonCandidatos;
import estadoSistema.SingletonCitas;
import estadoSistema.SingletonOfertas;
import negocio.facadeEvaluarCandidato;

public class AsignacionCitas {
	private static final ExecutorService pool;
	static {
		pool = Executors.newFixedThreadPool(25);
	}

	public static void serviceAsignacion() {
		try {
			HandlerCitas thread = new HandlerCitas();
			pool.execute(thread);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class HandlerCitas implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			Date myTS = new Date();

			HashMap<String, Entry<Candidato, Oferta>> mCitas = new HashMap<>();
			HashMap<Integer, Entry<Oferta, ArrayList<Candidato>>> mCitasOferta = new HashMap<>();

			if (myTS.compareTo(SingletonCandidatos.getInstance().getLastWriteTS()) < 0
					|| myTS.compareTo(SingletonOfertas.getInstance().getLastWriteTS()) < 0
					|| myTS.compareTo(SingletonCitas.getInstance().getLastWriteTS()) < 0) {
				continue;
				// reject read request and abort corresponding transaction
			} else {
				final ArrayList<Candidato> mListCand = (ArrayList<Candidato>) SingletonCandidatos.getInstance().getListaCandidatos().clone();
				final ArrayList<Oferta> mListOfer = (ArrayList<Oferta>) SingletonOfertas.getInstance().getOfertas().clone();

				mCitas = SingletonCitas.getInstance().getListaCitas();
				mCitasOferta = SingletonCitas.getInstance().getListaCitasOfertas();

				Date maxi = SingletonCandidatos.getInstance().getLastReadTS().compareTo(myTS) > 0
						? SingletonCandidatos.getInstance().getLastReadTS()
						: myTS;
				SingletonCandidatos.getInstance().setLastReadTS(maxi);

				Date maxi2 = SingletonOfertas.getInstance().getLastReadTS().compareTo(myTS) > 0
						? SingletonOfertas.getInstance().getLastReadTS()
						: myTS;

				SingletonOfertas.getInstance().setLastReadTS(maxi2);

				Date maxi3 = SingletonCitas.getInstance().getLastReadTS().compareTo(myTS) > 0
						? SingletonCitas.getInstance().getLastReadTS()
						: myTS;

				SingletonCitas.getInstance().setLastReadTS(maxi3);

				for (Oferta ofer : mListOfer) {
					Map.Entry<Oferta, ArrayList<Candidato>> actuales = mCitasOferta.get(ofer.getId());
					Candidato mejores[];
					int puntajes[];
					if (actuales == null) {
						mejores = new Candidato[3];
						puntajes = new int[3];
					} else {
						ArrayList<Candidato> candidatosEnOferta = actuales.getValue();
						if (candidatosEnOferta == null) {
							mejores = new Candidato[3];
							puntajes = new int[3];
						} else {
							int cantCand = candidatosEnOferta.size();
							if (cantCand < 3) {
								mejores = new Candidato[3 - cantCand];
								puntajes = new int[3 - cantCand];
							} else {
								continue;
							}
						}
					}
					for (int j = 0; j < puntajes.length; ++j) {
						puntajes[j] = 0;
					}

					for (Candidato candi : mListCand) {
						int mPuntaje = facadeEvaluarCandidato.evaluarCandidato(candi, ofer);

						int minPuntaje = 200;
						int indiceMinPuntaje = -1;
						for (int i = 0; i < puntajes.length; ++i) {
							if (puntajes[i] < minPuntaje) {
								indiceMinPuntaje = i;
								minPuntaje = puntajes[i];
							}
						}
						// el puntaje es mayor a 70, es un mejor puntaje que los que ya existen
						// y además no tiene una cita asignada
						if (mPuntaje >= 70 && mPuntaje > minPuntaje && mCitas.get(candi.getDocumento()) == null) {
							mejores[indiceMinPuntaje] = candi;
							puntajes[indiceMinPuntaje] = mPuntaje;
						}

					}

					/*
					 * Ya tengo los mejores C; debería escribir
					 */

					boolean writed = false;
					while (writed == false) {
						Date myTSWrite = new Date();
						if (myTSWrite.compareTo(SingletonCitas.getInstance().getLastReadTS()) < 0
								|| myTSWrite.compareTo(SingletonCitas.getInstance().getLastWriteTS()) < 0) {
							continue;
							// reject write request
						} else {

							// mList.add(this.cand);

							// escribir en mCitas y mCitasOferta
							// ArrayList<>

							for (int escogido = 0; escogido < mejores.length; escogido++) {
								if(mejores[escogido]==null) continue;
								
								mCitas.put(mejores[escogido].getDocumento(),
										new AbstractMap.SimpleEntry<Candidato, Oferta>(mejores[escogido], ofer));
							}
							Entry<Oferta, ArrayList<Candidato>> mapaOferta;
							mapaOferta = mCitasOferta.get(ofer.getId());
							if (mapaOferta == null) {
								ArrayList<Candidato> temp_list = new ArrayList<>();
								for (int escogido = 0; escogido < mejores.length; escogido++) {
									if(mejores[escogido]==null) continue;
									temp_list.add(mejores[escogido]);
								}

								mCitasOferta.put(ofer.getId(),
										new AbstractMap.SimpleEntry<Oferta, ArrayList<Candidato>>(ofer, temp_list));
							} else {
								for (int escogido = 0; escogido < mejores.length; escogido++) {
									if(mejores[escogido]==null) continue;
									mapaOferta.getValue().add(mejores[escogido]);
								}
							}

							SingletonCitas.getInstance().setLastWriteTS(myTS);
							writed = true;
						}
					}

				}

				// TODO FIX WRITE ON HASHMAP

				/*
				 * if(myTS.compareTo(SingletonCandidatos.getInstance().getLastReadTS()) < 0 ||
				 * myTS.compareTo(SingletonCandidatos.getInstance().getLastWriteTS()) < 0) {
				 * //reject write request } else { mList.add(this.cand);
				 * SingletonCandidatos.getInstance().setLastWriteTS(myTS); waiting = false; }
				 */
			}

			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidadesTransversales.Candidato;
import entidadesTransversales.NotiCandidato;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class FrameCandidato extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel bienvenido;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public FrameCandidato(Candidato candidato) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bienvenido = new JLabel("Bienvenido: "+candidato.getNombre());
		bienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		bienvenido.setBounds(6, 6, 438, 16);
		contentPane.add(bienvenido);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 34, 438, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id oferta", "Empresa", "Cargo", "Salario"
				}
			));
		scrollPane.setViewportView(table);
	}

	public void actualizarTabla(NotiCandidato noticia) {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.addRow(new Object[] {noticia.getId(), noticia.getEmpresaName(), noticia.getCargo(), noticia.getSalario()});
	}
	
}

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidadesTransversales.Candidato;
import utils.Utils;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class FrameCandidato extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel bienvenido;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<Candidato> candidatos = Utils.cargarCandidatos("./candidatos.json");
					for(Candidato c: candidatos) {
						FrameCandidato frame = new FrameCandidato(c);
						frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
					"Empresa", "Cargo", "Salario"
				}
			));
		scrollPane.setViewportView(table);
	}
}

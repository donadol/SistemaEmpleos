package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidadesTransversales.Candidato;
import entidadesTransversales.Empresa;
import utils.Utils;

public class FrameEmpresa extends JFrame {

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
					List<Empresa> empresas = Utils.cargarEmpresas("./empresas.json");
					for(Empresa e: empresas) {
						FrameEmpresa frame = new FrameEmpresa(e);
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
	public FrameEmpresa(Empresa empresa) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bienvenido = new JLabel("Bienvenido: "+empresa.getNombre());
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
					"Nombre", "Documento", "Aspiración salarial"
				}
			));
		scrollPane.setViewportView(table);
	}

}

package GUI;

import java.util.ArrayList;
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
import entidadesTransversales.Oferta;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class FrameEmpresa extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static List<JTable> tables;

	/**
	 * Create the frame.
	 */
	public FrameEmpresa(Empresa empresa) {
		tables = new ArrayList<JTable>();
		setTitle(empresa.getNombre());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblBienvenido = new JLabel("Bienvenido: "+empresa.getNombre());
		GridBagConstraints gbc_lblBienvenido = new GridBagConstraints();
		gbc_lblBienvenido.gridwidth = 2;
		gbc_lblBienvenido.insets = new Insets(0, 0, 5, 0);
		gbc_lblBienvenido.anchor = GridBagConstraints.NORTH;
		gbc_lblBienvenido.gridx = 0;
		gbc_lblBienvenido.gridy = 0;
		contentPane.add(lblBienvenido, gbc_lblBienvenido);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 2;
		gbc_tabbedPane.insets = new Insets(0, 0, 0, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		for(Oferta o: empresa.getOfertas()) {
			tabbedPane.addTab("Oferta "+o.getId(), null, createPanel(o), "Oferta: "+o.getId());
		}
		contentPane.add(tabbedPane, gbc_tabbedPane);
		
	}
	public JPanel createPanel(Oferta oferta) {
		JPanel panel = new JPanel();
		JLabel lblOferta = new JLabel("Oferta: "+oferta.id);
		JScrollPane scrollPane = new JScrollPane();
		JTable table = new JTable();
		GridBagLayout gbl_panel = new GridBagLayout();
		GridBagConstraints gbc_labelOferta = new GridBagConstraints();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		
		gbl_panel.columnWidths = new int[]{145, 61, 61, 0};
		gbl_panel.rowHeights = new int[]{16, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		gbc_labelOferta.gridwidth = 3;
		gbc_labelOferta.insets = new Insets(0, 0, 5, 0);
		gbc_labelOferta.gridx = 0;
		gbc_labelOferta.gridy = 0;
		
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		
		lblOferta.setHorizontalAlignment(SwingConstants.CENTER);
		lblOferta.setBounds(6, 23, 438, 16);
		
		scrollPane.setBounds(6, 46, 438, 226);
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Nombre", "Documento", "Aspiración salarial"
				}
			));
		scrollPane.setViewportView(table);

		panel.add(scrollPane, gbc_scrollPane);
		panel.add(lblOferta, gbc_labelOferta);
		
		tables.add(table);
		
		return panel;
	}
	public void actualizarTable(Candidato candidato, int tabla) {
		DefaultTableModel tableModel = (DefaultTableModel) tables.get(tabla).getModel();
		tableModel.addRow(new Object[] {candidato.getNombre(), candidato.getDocumento(), candidato.getAspiracionSalarial()});
	}
}

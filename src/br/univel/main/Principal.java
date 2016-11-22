package br.univel.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.univel.controller.BuscaArquivo;
import br.univel.controller.GoogleSearchApi;
import br.univel.dao.PessoaController;
import br.univel.model.BD;
import br.univel.model.Pessoa;
import br.univel.model.TabelaMysqlModel;
import br.univel.model.TabelaPostgresModel;
import java.awt.Color;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtBusca;
	private JTabbedPane tabbedPane;
	private JPanel pnPostgres;
	private JPanel pnMysql;
	private JPanel painel;
	private JPanel pnGoogle;
	private JTable tblPostgres;
	private JLabel lblDir;
	private JTextField txtDir;

	private TabelaMysqlModel mysqlModel;
	private TabelaPostgresModel postgresModel;
	private JScrollPane scrollPane_1;
	private JTextArea txtPesquisaGoogle;
	private JScrollPane scrollPane_2;
	private JTextArea txtArquivos;
	private JScrollPane scrollPane_3;
	private JTable tblMySql;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal principal = new Principal();
					principal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Principal() {
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 583, 496);
		setLocationRelativeTo(null);
		setTitle("Buscador de arquivos");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Panel panel = new Panel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
				pnGoogle = new JPanel();
				tabbedPane.addTab("Google", null, pnGoogle, null);
				GridBagLayout gbl_pnGoogle = new GridBagLayout();
				gbl_pnGoogle.columnWidths = new int[] { 0, 0 };
				gbl_pnGoogle.rowHeights = new int[] { 0, 0, 0 };
				gbl_pnGoogle.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
				gbl_pnGoogle.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
				pnGoogle.setLayout(gbl_pnGoogle);
				
						scrollPane_1 = new JScrollPane();
						GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
						gbc_scrollPane_1.gridheight = 2;
						gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
						gbc_scrollPane_1.gridx = 0;
						gbc_scrollPane_1.gridy = 0;
						pnGoogle.add(scrollPane_1, gbc_scrollPane_1);
						
								txtPesquisaGoogle = new JTextArea();
								scrollPane_1.setViewportView(txtPesquisaGoogle);
		
				painel = new JPanel();
				tabbedPane.addTab("Arquivos", null, painel, null);
				GridBagLayout gbl_painel = new GridBagLayout();
				gbl_painel.columnWidths = new int[] { 36, 419, 0, 0 };
				gbl_painel.rowHeights = new int[] { 30, 369, 0 };
				gbl_painel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
				gbl_painel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
				painel.setLayout(gbl_painel);
				
						lblDir = new JLabel("Dir:");
						GridBagConstraints gbc_lblDir = new GridBagConstraints();
						gbc_lblDir.anchor = GridBagConstraints.EAST;
						gbc_lblDir.insets = new Insets(0, 0, 5, 5);
						gbc_lblDir.gridx = 0;
						gbc_lblDir.gridy = 0;
						painel.add(lblDir, gbc_lblDir);
						
								txtDir = new JTextField();
								txtDir.setText("C:\\");
								GridBagConstraints gbc_txtDir = new GridBagConstraints();
								gbc_txtDir.gridwidth = 2;
								gbc_txtDir.fill = GridBagConstraints.HORIZONTAL;
								gbc_txtDir.insets = new Insets(0, 0, 5, 0);
								gbc_txtDir.gridx = 1;
								gbc_txtDir.gridy = 0;
								painel.add(txtDir, gbc_txtDir);
								txtDir.setColumns(10);
								
										scrollPane_2 = new JScrollPane();
										GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
										gbc_scrollPane_2.gridwidth = 3;
										gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
										gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
										gbc_scrollPane_2.gridx = 0;
										gbc_scrollPane_2.gridy = 1;
										painel.add(scrollPane_2, gbc_scrollPane_2);
										
												txtArquivos = new JTextArea();
												scrollPane_2.setViewportView(txtArquivos);

		pnPostgres = new JPanel();
		tabbedPane.addTab("Postgres", null, pnPostgres, null);
		GridBagLayout gbl_pnPostgres = new GridBagLayout();
		gbl_pnPostgres.columnWidths = new int[] { 0, 0 };
		gbl_pnPostgres.rowHeights = new int[] { 0, 0, 0 };
		gbl_pnPostgres.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnPostgres.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		pnPostgres.setLayout(gbl_pnPostgres);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnPostgres.add(scrollPane, gbc_scrollPane);

		tblPostgres = new JTable();
		scrollPane.setViewportView(tblPostgres);

		pnMysql = new JPanel();
		tabbedPane.addTab("MySql", null, pnMysql, null);
		GridBagLayout gbl_pnMysql = new GridBagLayout();
		gbl_pnMysql.columnWidths = new int[] { 0, 0 };
		gbl_pnMysql.rowHeights = new int[] { 0, 0 };
		gbl_pnMysql.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnMysql.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		pnMysql.setLayout(gbl_pnMysql);

		scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 0;
		pnMysql.add(scrollPane_3, gbc_scrollPane_3);

		tblMySql = new JTable();
		scrollPane_3.setViewportView(tblMySql);

		txtBusca = new JTextField();
		panel.add(txtBusca, BorderLayout.NORTH);
		txtBusca.setToolTipText("Busca..");
		txtBusca.setText("Busca..");
		txtBusca.setColumns(10);
		txtBusca.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					try {
						String busca = txtBusca.getText().trim();

						buscarDadosPostgres(busca);
						// buscarDadosMySql(busca);
						// buscarDadosArq(busca);
						buscarDadosGoogle(busca);
						txtBusca.requestFocus();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

			}

		});

	}

	protected void buscarDadosGoogle(String criterio) {

		final ExecutorService executor = Executors.newFixedThreadPool(1);

		final Future<List<String>> future = executor.submit(new GoogleSearchApi(criterio));

		try {
			final List<String> webSites = future.get();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < webSites.size(); i++) {

				sb.append((webSites).get(i));
				sb.append("\n");
			}
			txtPesquisaGoogle.setText(sb.toString());
			txtPesquisaGoogle.setEditable(false);

			executor.shutdown();

		} catch (Exception e) {
			executor.shutdown();
			e.printStackTrace();
		}

	}

	protected void buscarDadosArq(String criterio) throws Exception {

		String path = txtDir.getText();
		File file = new File(path);
		File qtdFile[] = file.listFiles();

		ExecutorService executor = Executors.newFixedThreadPool(qtdFile.length);

		final Future<List<String>> future = executor
				.submit((Callable<List<String>>) new BuscaArquivo(criterio, path));

		try {
			List<String> arquivos = future.get();

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < arquivos.size(); i++) {
				sb.append(arquivos.get(i));
				sb.append("\n");
			}

			txtArquivos.setText(sb.toString());

			executor.shutdown();

		} catch (Exception e) {
			executor.shutdown();
			e.printStackTrace();
		}

	}

	protected void buscarDadosMySql(String criterio) {

		final ExecutorService executor = Executors.newFixedThreadPool(1);
		final Future<List<Pessoa>> future = executor.submit(new PessoaController(criterio, BD.MYSQL));

		try {
			List<Pessoa> bdMysql = future.get();
			mysqlModel= new TabelaMysqlModel(bdMysql);
			if (bdMysql.size() == 0) {
				JOptionPane.showMessageDialog(Principal.this, "Busca n�o encontrada na utiliza��o do banco MYSQL", "ATENCAO!",
						JOptionPane.ERROR_MESSAGE);
				tblMySql.setModel(new DefaultTableModel());
			} else {
				tblMySql.setModel(mysqlModel);
			}
			executor.shutdown();
		} catch (Exception e) {
			executor.shutdown();
			e.printStackTrace();
		}
	}

	public void buscarDadosPostgres(final String busca) {

		final ExecutorService executor = Executors.newFixedThreadPool(1);
		final Future<List<Pessoa>> future = executor.submit(new PessoaController(busca, BD.POSTGRES));

		try {
			List<Pessoa> bdPostgres = future.get();
			postgresModel = new TabelaPostgresModel(bdPostgres);
			if (bdPostgres.size() == 0) {
				JOptionPane.showMessageDialog(Principal.this, "Busca n�o encontrada na utiliza��o do banco POSTGRES!",
						"ATENCAO", JOptionPane.ERROR_MESSAGE);

				tblPostgres.setModel(new DefaultTableModel());

			} else {
				tblPostgres.setModel(postgresModel);

			}

			executor.shutdown();

		} catch (Exception e) {
			executor.shutdown();
			e.printStackTrace();
		}

	}
}

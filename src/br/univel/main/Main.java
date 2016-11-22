package br.univel.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.ImageIcon;
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
import br.univel.model.MysqlTableModel;
import br.univel.model.PostgresTableModel;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Window.Type;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtBusca;
	private JTabbedPane tabbedPane;
	private JPanel painelPostg;
	private JPanel pnMysql;
	private JPanel painel;
	private JPanel painelGoogle;
	private JTable tblPostgres;
	private JLabel lblDir;
	private JTextField txtDir;

	private MysqlTableModel mysqlModel;
	private PostgresTableModel postgresModel;
	private JScrollPane scrollPane_1;
	private JTextArea txtPesquisaGoogle;
	private JScrollPane scrollPane_2;
	private JTextArea txtArquivos;
	private JScrollPane scrollPane_3;
	private JTable tblMysql;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main principal = new Main();
					principal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icone/busc2.png"));
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 412, 400);
		setLocationRelativeTo(null);
		setTitle("Buscador");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		Panel panel = new Panel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.activeCaption);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		painelGoogle = new JPanel();
		tabbedPane.addTab("Google", null, painelGoogle, null);
		GridBagLayout gbl_pnGoogle = new GridBagLayout();
		gbl_pnGoogle.columnWidths = new int[] { 0, 0 };
		gbl_pnGoogle.rowHeights = new int[] { 0, 0, 0 };
		gbl_pnGoogle.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnGoogle.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		painelGoogle.setLayout(gbl_pnGoogle);
				
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		painelGoogle.add(scrollPane_1, gbc_scrollPane_1);
						
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

		painelPostg = new JPanel();
		tabbedPane.addTab("postgres", null, painelPostg, null);
		GridBagLayout gbl_pnPostgres = new GridBagLayout();
		gbl_pnPostgres.columnWidths = new int[] { 0, 0 };
		gbl_pnPostgres.rowHeights = new int[] { 0, 0, 0 };
		gbl_pnPostgres.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pnPostgres.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		painelPostg.setLayout(gbl_pnPostgres);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		painelPostg.add(scrollPane, gbc_scrollPane);

		tblPostgres = new JTable();
		scrollPane.setViewportView(tblPostgres);

		pnMysql = new JPanel();
		tabbedPane.addTab("mySql", null, pnMysql, null);
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
		
		tblMysql = new JTable();
		scrollPane_3.setViewportView(tblMysql);
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
						buscaGoogle(busca);
						//buscaArquivo(busca);
						//buscaPostgres(busca);
						//buscaMysql(busca);
						txtBusca.requestFocus();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
	}
	protected void buscaGoogle(String busca) {

		final ExecutorService es = Executors.newFixedThreadPool(1);
		final Future<List<String>> listPessoa = es.submit(new GoogleSearchApi(busca));

		try {
			final List<String> pesqGoogle = listPessoa.get();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < pesqGoogle.size(); i++) {

				sb.append((pesqGoogle).get(i));
				sb.append("\n\n");
			}
			txtPesquisaGoogle.setText(sb.toString());
			txtPesquisaGoogle.setEditable(false);
			es.shutdown();
		} catch (Exception e) {
			es.shutdown();
			e.printStackTrace();
		}

	}

	protected void buscaArquivo(String busca) throws Exception {

		String dir = txtDir.getText();
		File file = new File(dir);
		File files[] = file.listFiles();
		//execução das threads
		ExecutorService es = Executors.newFixedThreadPool(files.length);

		final Future<List<String>> listPessoa = es.submit((Callable<List<String>>) new BuscaArquivo(busca, dir));
		try {
			List<String> arquivos = listPessoa.get();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < arquivos.size(); i++) {
				sb.append(arquivos.get(i));
				sb.append("\n");
			}
			txtArquivos.setText(sb.toString());
			es.shutdown();
		} catch (Exception e) {
			es.shutdown();
			e.printStackTrace();
		}
	}

	public void buscaPostgres(final String busca) {

		final ExecutorService es = Executors.newFixedThreadPool(1);
		final Future<List<Pessoa>> listPessoa = es.submit(new PessoaController(busca, BD.POSTGRES));
		try {
			List<Pessoa> bdPostgres = listPessoa.get();
			postgresModel = new PostgresTableModel(bdPostgres);
			if (bdPostgres.size() == 0) {
				JOptionPane.showMessageDialog(Main.this, "Busca não encontrada na utilização do banco POSTGRES!",
						"ATENCAO", JOptionPane.ERROR_MESSAGE);

				tblPostgres.setModel(new DefaultTableModel());
			} else {
				tblPostgres.setModel(postgresModel);
			}
			es.shutdown();
		} catch (Exception e) {
			es.shutdown();
			e.printStackTrace();
		}

	}
	
	protected void buscaMysql(String busca) {

		final ExecutorService es = Executors.newFixedThreadPool(1);
		final Future<List<Pessoa>> listPessoa = es.submit(new PessoaController(busca, BD.MYSQL));

		try {
			List<Pessoa> bdMysql = listPessoa.get();
			mysqlModel= new MysqlTableModel(bdMysql);
			if (bdMysql.size() == 0) {
				JOptionPane.showMessageDialog(Main.this, "Busca não encontrada na utilização do banco MYSQL", "ATENCAO!",
						JOptionPane.WARNING_MESSAGE);
				tblMysql.setModel(new DefaultTableModel());
			} else {
				tblMysql.setModel(mysqlModel);
			}
			es.shutdown();
		} catch (Exception e) {
			es.shutdown();
			e.printStackTrace();
		}
	}
}

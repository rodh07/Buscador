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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.univel.controller.ArquivoController;
import br.univel.controller.GoogleSearchApi;
import br.univel.dao.PessoaController;
import br.univel.enuns.TipoBanco;
import br.univel.model.Pessoa;
import br.univel.model.TabelaModel;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField txtCriterio;
	private JTabbedPane tabbedPane;
	private JPanel pnPostgres;
	private JPanel pnMysql;
	private JPanel pnArq;
	private JPanel pnGoogle;
	private JTable tblPostgres;
	private JLabel lblPath;
	private JTextField txtPath;

	private TabelaModel model;
	private JScrollPane scrollPane_1;
	private JTextArea txtGoogle;
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

	/**
	 * Create the frame.
	 */
	public Principal() {
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

		pnArq = new JPanel();
		tabbedPane.addTab("Arquivos", null, pnArq, null);
		GridBagLayout gbl_pnArq = new GridBagLayout();
		gbl_pnArq.columnWidths = new int[] { 36, 419, 0, 0 };
		gbl_pnArq.rowHeights = new int[] { 30, 369, 0 };
		gbl_pnArq.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pnArq.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		pnArq.setLayout(gbl_pnArq);

		lblPath = new JLabel("Path:");
		GridBagConstraints gbc_lblPath = new GridBagConstraints();
		gbc_lblPath.anchor = GridBagConstraints.EAST;
		gbc_lblPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblPath.gridx = 0;
		gbc_lblPath.gridy = 0;
		pnArq.add(lblPath, gbc_lblPath);

		txtPath = new JTextField();
		txtPath.setText("E:\\");
		GridBagConstraints gbc_txtPath = new GridBagConstraints();
		gbc_txtPath.gridwidth = 2;
		gbc_txtPath.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPath.insets = new Insets(0, 0, 5, 0);
		gbc_txtPath.gridx = 1;
		gbc_txtPath.gridy = 0;
		pnArq.add(txtPath, gbc_txtPath);
		txtPath.setColumns(10);

		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.gridwidth = 3;
		gbc_scrollPane_2.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 1;
		pnArq.add(scrollPane_2, gbc_scrollPane_2);

		txtArquivos = new JTextArea();
		scrollPane_2.setViewportView(txtArquivos);

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

		txtGoogle = new JTextArea();
		scrollPane_1.setViewportView(txtGoogle);

		txtCriterio = new JTextField();
		panel.add(txtCriterio, BorderLayout.NORTH);
		txtCriterio.setToolTipText("Informe o crit�rio de busca...");
		txtCriterio.setText("Informe o crit�rio de busca...");
		txtCriterio.setColumns(10);
		txtCriterio.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					try {

						String criterio = txtCriterio.getText().trim();

						buscarDadosPostgres(criterio);
						// buscarDadosMySql(criterio);
						// buscarDadosArq(criterio);
						buscarDadosGoogle(criterio);
						txtCriterio.requestFocus();
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

			txtGoogle.setText(sb.toString());
			txtGoogle.setEditable(false);

			executor.shutdown();

		} catch (Exception e) {
			executor.shutdown();
			e.printStackTrace();
		}

	}

	protected void buscarDadosArq(String criterio) throws Exception {

		String path = txtPath.getText();
		File file = new File(path);
		File qtdFile[] = file.listFiles();

		ExecutorService executor = Executors.newFixedThreadPool(qtdFile.length);

		final Future<List<String>> future = executor
				.submit((Callable<List<String>>) new ArquivoController(criterio, path));

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

		final Future<List<Pessoa>> future = executor.submit(new PessoaController(criterio, TipoBanco.MYSQL));

		try {
			List<Pessoa> dadosPostgres = future.get();

			model = new TabelaModel(dadosPostgres);

			if (dadosPostgres.size() == 0) {
				JOptionPane.showMessageDialog(Principal.this, "Nenhuma informa��o encontrada no banco MySql", "Aten��o",
						JOptionPane.WARNING_MESSAGE);
				
				tblMySql.setModel(new DefaultTableModel());
			} else {
				tblMySql.setModel(model);

			}

			executor.shutdown();

		} catch (Exception e) {
			executor.shutdown();
			e.printStackTrace();
		}

	}

	public void buscarDadosPostgres(final String criterio) {

		final ExecutorService executor = Executors.newFixedThreadPool(1);

		final Future<List<Pessoa>> future = executor.submit(new PessoaController(criterio, TipoBanco.POSTGRES));

		try {
			List<Pessoa> dadosPostgres = future.get();

			model = new TabelaModel(dadosPostgres);

			if (dadosPostgres.size() == 0) {
				JOptionPane.showMessageDialog(Principal.this, "Nenhuma informa��o encontrada no banco Postgres",
						"Aten��o", JOptionPane.WARNING_MESSAGE);

				tblPostgres.setModel(new DefaultTableModel());

			} else {
				tblPostgres.setModel(model);

			}

			executor.shutdown();

		} catch (Exception e) {
			executor.shutdown();
			e.printStackTrace();
		}

	}
}

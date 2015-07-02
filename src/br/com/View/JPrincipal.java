package br.com.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import br.com.Bin.Banca;
import br.com.Bin.Instituicao;
import br.com.Bin.Materia;
import br.com.Bin.Palavra;
import br.com.Bin.Prova;
import br.com.Persistencia.Banco;

import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class JPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnSalvar;
	private JButton btnCancelar;

	private Banco banco = new Banco();
	private JTextArea textArea;
	private JComboBox box;
	private JComboBox boxBanca;
	private JComboBox boxAno;
	private JComboBox boxNivel;
	private String materia;
	private JComboBox boxInstituicao;
	private JMenuItem mntmExcluirOcorrencia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JPrincipal() {
		setTitle("Ordena\u00E7\u00E3o de Provas e Ocorrencia de Palavras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 456);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		JMenuItem mntmSalvarArquivo = new JMenuItem("Salvar Arquivo");
		mnArquivo.add(mntmSalvarArquivo);

		JMenuItem mntmCriarClasse = new JMenuItem("Criar Classe");
		mnArquivo.add(mntmCriarClasse);

		JMenuItem mntmCriaBanca = new JMenuItem("Cria Banca");
		mnArquivo.add(mntmCriaBanca);

		JMenuItem mntmCriaInstituio = new JMenuItem(
				"Cria Institui\u00E7\u00E3o");
		mnArquivo.add(mntmCriaInstituio);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);

		JMenu mnRelatorio = new JMenu("Relatorio");
		menuBar.add(mnRelatorio);

		JMenuItem mntmRelatorioProvas = new JMenuItem("Relatorio Provas");
		mnRelatorio.add(mntmRelatorioProvas);

		JMenuItem mntmRelatorioPalavras = new JMenuItem("Relatorio Palavras");
		mnRelatorio.add(mntmRelatorioPalavras);
		
		JMenu mnExtra = new JMenu("Extra");
		menuBar.add(mnExtra);
		
		mntmExcluirOcorrencia = new JMenuItem("Excluir 1 Ocorrencia");
		mnExtra.add(mntmExcluirOcorrencia);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 686, 262);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(607, 88, 89, 25);
		contentPane.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(508, 88, 89, 25);
		contentPane.add(btnCancelar);

		JLabel label = new JLabel("Materia");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(10, 15, 80, 25);
		contentPane.add(label);

		box = new JComboBox(listaMaterias());
		box.setBounds(95, 15, 245, 25);
		contentPane.add(box);

		JLabel lblBanca = new JLabel("Banca");
		lblBanca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBanca.setBounds(10, 50, 80, 25);
		contentPane.add(lblBanca);

		boxBanca = new JComboBox(listaBancas());
		boxBanca.setBounds(95, 50, 89, 25);
		contentPane.add(boxBanca);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAno.setBounds(10, 85, 80, 25);
		contentPane.add(lblAno);

		boxAno = new JComboBox(new Object[] {});
		boxAno.setModel(new DefaultComboBoxModel(new String[] { "2015", "2014",
				"2013" }));
		boxAno.setBounds(95, 85, 89, 25);
		contentPane.add(boxAno);

		JLabel lblNivel = new JLabel("Nivel");
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNivel.setBounds(380, 15, 66, 25);
		contentPane.add(lblNivel);

		String[] nivel = { "Medio", "Superior", "Fundamental" };

		boxNivel = new JComboBox(nivel);
		boxNivel.setBounds(452, 15, 245, 25);
		contentPane.add(boxNivel);

		JLabel lblInstituio = new JLabel("Institui\u00E7\u00E3o");
		lblInstituio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInstituio.setBounds(194, 51, 80, 25);
		contentPane.add(lblInstituio);

		boxInstituicao = new JComboBox(listaInstituicao());
		boxInstituicao.setBounds(284, 50, 412, 25);
		contentPane.add(boxInstituicao);

		btnCancelar.addActionListener(this);
		btnSalvar.addActionListener(this);

		mntmCriarClasse.addActionListener(this);
		mntmRelatorioPalavras.addActionListener(this);
		mntmRelatorioProvas.addActionListener(this);
		mntmSair.addActionListener(this);
		mntmSalvarArquivo.addActionListener(this);
		mntmCriaBanca.addActionListener(this);
		mntmCriaInstituio.addActionListener(this);
		mntmExcluirOcorrencia.addActionListener(this);
	}

	private String[] listaInstituicao() {
		@SuppressWarnings("unchecked")
		List<Object> listaObj = (List<Object>) banco.listarObjetos(
				Instituicao.class, "nome");
		String[] lista = new String[listaObj.size()];
		for (int i = 0; i < listaObj.size(); i++) {
			Instituicao inst = (Instituicao) listaObj.get(i);
			lista[i] = inst.getNome();

		}
		return lista;
	}

	private String[] listaMaterias() {

		@SuppressWarnings("unchecked")
		List<Object> listaObj = (List<Object>) banco.listarObjetos(
				Materia.class, "nome");
		String[] lista = new String[listaObj.size()];
		for (int i = 0; i < listaObj.size(); i++) {
			Materia materi = (Materia) listaObj.get(i);
			lista[i] = materi.getNome();

		}
		return lista;
	}

	private String[] listaBancas() {

		@SuppressWarnings("unchecked")
		List<Object> listaObj = (List<Object>) banco.listarObjetos(Banca.class,
				"nome");
		String[] lista = new String[listaObj.size()];
		for (int i = 0; i < listaObj.size(); i++) {
			Banca materi = (Banca) listaObj.get(i);
			lista[i] = materi.getNome();

		}
		return lista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acao = e.getActionCommand();

		// atualizaBox();

		// System.out.println(acao);

		switch (acao) {
		case "Salvar Arquivo":
			JOptionPane.showMessageDialog(null, "Não implementado ainda");
			break;
		case "Criar Classe":
			criaClasse();
			break;
		case "Sair":
			dispose();
			break;
		case "Relatorio Provas":
			JOptionPane.showMessageDialog(null, "Não implementado ainda");
			break;
		case "Relatorio Palavras":
			JOptionPane.showMessageDialog(null, "Não implementado ainda");
			break;
		case "Cancelar":
			textArea.setText("");
			break;
		case "Salvar":
			salvar();
			break;

		case "Cria Banca":
			criaBanca();
			break;
		case "Cria Institui\u00E7\u00E3o":
			criaInstituicao();
			break;
			
		case "Excluir 1 Ocorrencia":
			excluirOcorrencia();
			break;
			

		default:
			break;
		}

	}

	private void excluirOcorrencia() {

		@SuppressWarnings("unchecked")
		ArrayList<Object> listaBanco = (ArrayList<Object>) banco.listarObjetos(
				Palavra.class, "nome");

		for (int i = 0; i < listaBanco.size(); i++) {
			Palavra palavraBanco = (Palavra) listaBanco.get(i);
			palavraBanco.setNome(palavraBanco.getNome().replace(";", "").replace(".", "")
					.replace(",", "").replace("_", "").replace("(", "").replace(")", "").replace(" ", ""));
			palavraBanco.setMateria("DIREITO PREVIDENCIARIO");
			banco.salvarOuAtualizarObjeto(palavraBanco);
			if (palavraBanco.getOcorrencia()<=1 || palavraBanco.getNome().equals("")) {
				
				//JOptionPane.showMessageDialog(null, "palavra igual: "+palavra.getNome());

				banco.deletarObjeto(palavraBanco);
			}
		}
		
	}

	private void criaInstituicao() {
		String nomeInst = JOptionPane.showInputDialog("Nome da Instituição : ");

		Instituicao inst = new Instituicao();

		inst.setNome(nomeInst);

		banco.salvarObjeto(inst);

		atualizaBox();

	}

	private void criaBanca() {
		String nomebanca = JOptionPane.showInputDialog("Nome da Banca: ");

		Banca banca = new Banca();

		banca.setNome(nomebanca);

		banco.salvarObjeto(banca);

		atualizaBox();

	}

	private void atualizaBox() {

		contentPane.remove(box);
		contentPane.remove(boxBanca);
		contentPane.remove(boxInstituicao);

		boxInstituicao = new JComboBox(listaInstituicao());
		boxInstituicao.setBounds(284, 50, 412, 25);
		contentPane.add(boxInstituicao);

		boxBanca = new JComboBox(listaBancas());
		boxBanca.setBounds(95, 50, 89, 25);
		contentPane.add(boxBanca);

		box = new JComboBox(listaMaterias());
		box.setBounds(95, 15, 245, 25);
		contentPane.add(box);
		invalidate();
		validate();
		repaint();

	}

	private void salvar() {

		String descricao = boxBanca.getSelectedItem() + ", Ano - "
				+ boxAno.getSelectedItem() + ", nivel -"
				+ boxNivel.getSelectedItem() + " Materia - "
				+ box.getSelectedItem() + "Descrição - "
				+ boxInstituicao.getSelectedItem();

		String texto = textArea.getText();

		int a = 0;
		// define um ponto posterior
		int b = texto.indexOf(" ", a);

		String txtIndicador = texto.substring(a, b + 100);

		materia = (String) box.getSelectedItem();

		salvaProva(descricao, txtIndicador, materia, texto);

		listarTudo(listaNomes(texto));

		// TODO restante da implementação

		atualizaBox();

		// String txtIndicador =

	}

	public static ArrayList<Palavra> listaNomes(String texto) {

		// texto.replaceAll(".","").replaceAll(",","").replaceAll(";","");
		// texto.replaceAll("\\(","").replaceAll("\\)","").replace("-","");

		// cria uma lista para poder armazenar as palavras
		ArrayList<Palavra> lista = new ArrayList<Palavra>();

		// define um ponto incial
		int a = 0;
		// define um ponto posterior
		int b = texto.indexOf(" ", a);

		// aloca a palavra
		String palavra = texto.substring(a, b).replace(";", "");

		// modifica o proximo ponto de partida para a proxima palavra
		a = b + 1;
		// aloca o ponto apos ao proximo para coletar a proxima palavra
		b = texto.indexOf(" ", a);

		// instancia a palavra
		Palavra pal = new Palavra();
		// seta valores para as variais
		pal.setId(0);
		pal.setNome(palavra);
		pal.setOcorrencia(1);

		// adiciona a primeira palavra na lista
		lista.add(pal);

		//

		// System.out.println(b);

		while (b != -1) {

			// coleta a proxima palavra
			palavra = texto.substring(a, b).replace(";", "").replace(".", "")
					.replace(",", "").replace("_", "").replace("(", "").replace(")", "").replace(":", "");
			// modifica o ponto de partida
			a = b + 1;
			// modifica o ponto apos o da partida para poder pegar a proxima
			// palavra
			b = texto.indexOf(" ", a);

			for (int i = 0; i < lista.size(); i++) {

				// lista.get(0).getNome());

				if (palavra.equalsIgnoreCase(lista.get(i).getNome())) {
					pal = lista.get(i);
					pal.setOcorrencia(lista.get(i).getOcorrencia() + 1);
					lista.set(i, pal);

					palavra = null;
					i = lista.size() + 100;
					break;
				}
			}
			if (palavra != null) {
				pal = new Palavra();
				pal.setId(0);
				palavra.replace(";", "");
				pal.setNome(palavra);
				pal.setOcorrencia(1);
				lista.add(pal);
			}

		}
		return lista;
	}

	public void listarTudo(ArrayList<Palavra> lista) {
		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getNome().length() >= 4) {
				Palavra obj = new Palavra();
				obj.setNome(lista.get(i).getNome());
				obj.setOcorrencia(Integer.valueOf(lista.get(i).getOcorrencia()));
				obj.setMateria(materia);

				salvaPalavra(obj);

			}

		}

	}

	public void salvaPalavra(Palavra palavra) {

		@SuppressWarnings("unchecked")
		ArrayList<Object> listaBanco = (ArrayList<Object>) banco.listarObjetos(
				Palavra.class, "nome");

		for (int i = 0; i < listaBanco.size(); i++) {
			Palavra palavraBanco = (Palavra) listaBanco.get(i);
			if (palavraBanco.getNome().equalsIgnoreCase(palavra.getNome())
					&& palavraBanco.getMateria().equalsIgnoreCase(palavra.getMateria())) {
				
				//JOptionPane.showMessageDialog(null, "palavra igual: "+palavra.getNome());

				palavraBanco.setOcorrencia(palavraBanco.getOcorrencia()
						+ palavra.getOcorrencia());
				palavraBanco.setQuantProvas(palavraBanco.getQuantProvas() + 1);
				banco.salvarOuAtualizarObjeto(palavraBanco);
				palavra = null;
				System.out.println("Encontrou palavra igual");
				break;
			}
		}
		if (palavra != null) {
			palavra.setQuantProvas(1);
			banco.salvarObjeto(palavra);
		}

	}

	private void salvaProva(String descricao, String txtIndicador,
			String materia, String texto) {
		Prova prov = new Prova();
		@SuppressWarnings("unchecked")
		List<Object> listaObj = (List<Object>) banco.listarObjetos(Prova.class,
				"id");
		for (int i = 0; i < listaObj.size(); i++) {
			Prova prova = (Prova) listaObj.get(i);

			// TODO essa merda não presta da forma que deveria. por que nunca
			// entra nessa condição
			// acho que vou verificar com alguem que já trabalhou com isso antes
			if (texto.contains(".*" + prova.getTextoVerificacao() + ".*")) {
				prov = null;
			} else {
				prov.setDescricao(descricao);
				prov.setMateria(materia);
				prov.setTextoVerificacao(txtIndicador);

			}

		}
		if (prov != null) {
			banco.salvarObjeto(prov);
		}

		atualizaBox();
		textArea.setText("");

	}

	private void criaClasse() {
		String nomeClasse = JOptionPane.showInputDialog("Nome da Classe: ");

		Materia materia = new Materia();

		materia.setNome(nomeClasse);

		banco.salvarObjeto(materia);

		atualizaBox();

	}
}

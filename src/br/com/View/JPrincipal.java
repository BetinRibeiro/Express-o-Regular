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
	private JComboBox boxMateria;
	private JComboBox boxBanca;
	private JComboBox boxAno;
	private JComboBox boxNivel;
	private String materia;
	private JMenuItem mntmPalavra;
	private JTextField txtDescricao;

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
		setBounds(0, 0, 722, 700);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Cadastro");
		menuBar.add(mnArquivo);

		JMenuItem mntmMateria = new JMenuItem("Materia");
		mnArquivo.add(mntmMateria);

		JMenuItem mntmBanca = new JMenuItem("Banca");
		mnArquivo.add(mntmBanca);

		JMenuItem mntmAno = new JMenuItem("Ano");
		mnArquivo.add(mntmAno);

		JMenuItem mntmNivel = new JMenuItem("Nivel");
		mnArquivo.add(mntmNivel);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);

		JMenu mnRelatorio = new JMenu("Adicionar");
		menuBar.add(mnRelatorio);

		JMenuItem mntmLegislacao = new JMenuItem("Legisla\u00E7\u00E3o");
		mnRelatorio.add(mntmLegislacao);

		JMenuItem mntmRestrincao = new JMenuItem("Restrin\u00E7\u00F5es");
		mnRelatorio.add(mntmRestrincao);

		JMenu mnExtra = new JMenu("Relatorio");
		menuBar.add(mnExtra);

		mntmPalavra = new JMenuItem("Palavras");
		mnExtra.add(mntmPalavra);

		JMenuItem mntmRelatorioLegislacao = new JMenuItem(
				"Relatorio Legisla\u00E7\u00E3o");
		mnExtra.add(mntmRelatorioLegislacao);

		JMenuItem mntmOutro = new JMenuItem("Outro");
		mnExtra.add(mntmOutro);

		JMenu mnExcluir = new JMenu("Excluir");
		menuBar.add(mnExcluir);

		JMenuItem mntmOcorrencia = new JMenuItem("Palavras Pouco Ocorridas");
		mnExcluir.add(mntmOcorrencia);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 686, 493);
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

		boxMateria = new JComboBox(listaMaterias());
		boxMateria.setBounds(95, 15, 245, 25);
		contentPane.add(boxMateria);

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

		JLabel lblInstituio = new JLabel("Descri\u00E7\u00E3o");
		lblInstituio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInstituio.setBounds(194, 51, 80, 25);
		contentPane.add(lblInstituio);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(284, 51, 412, 25);
		contentPane.add(txtDescricao);
		txtDescricao.setColumns(10);

		btnCancelar.addActionListener(this);
		btnSalvar.addActionListener(this);

		mntmBanca.addActionListener(this);
		mntmRestrincao.addActionListener(this);
		mntmLegislacao.addActionListener(this);
		mntmSair.addActionListener(this);
		mntmMateria.addActionListener(this);
		mntmAno.addActionListener(this);
		mntmNivel.addActionListener(this);
		mntmPalavra.addActionListener(this);
		mntmRelatorioLegislacao.addActionListener(this);
		mntmOcorrencia.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acao = e.getActionCommand();
		
		System.out.println(acao);

		switch (acao) {
		case "Cancelar":
			textArea.setText("");
			break;
		case "Salvar":
			salvar();
			break;
		case "Materia":
			criaMateria();
			break;
		case "Banca":
			criaBanca();
			break;
			
		case "Ano":
			criaAno();
			break;
		case "Nivel":
			criaNivel();
			break;
		case "Legisla\u00E7\u00E3o":
			JAdicionaLei lei = new JAdicionaLei();
			lei.setVisible(true);
			break;
		case "Sair":
			dispose();
			break;
		case "Palavras":
			JListaPalavra frame = new JListaPalavra();
			frame.setVisible(true);
			break;
		case "Palavras Pouco Ocorridas":
			excluirOcorrencia();
			break;
		case "Relatorio Legisla\u00E7\u00E3o":
			JListaLegislacao n = new JListaLegislacao();
			n.setVisible(true);
			break;
		case "Restrinções":
			JRestricoes fram = new JRestricoes();
			fram.setVisible(true);
			break;
			
		default:
			break;
		}
	}
	
	
	private void criaNivel() {
		// TODO Auto-generated method stub
		
	}

	private void criaAno() {
		// TODO Auto-generated method stub
		
	}

	private String[] listaInstituicao() {
		@SuppressWarnings("unchecked")
		List<Object> listaObj = (List<Object>) banco.listarObjetosAsc(
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
		List<Object> listaObj = (List<Object>) banco.listarObjetosAsc(
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
		List<Object> listaObj = (List<Object>) banco.listarObjetosAsc(
				Banca.class, "nome");
		String[] lista = new String[listaObj.size()];
		for (int i = 0; i < listaObj.size(); i++) {
			Banca materi = (Banca) listaObj.get(i);
			lista[i] = materi.getNome();

		}
		return lista;
	}

	private void excluirOcorrencia() {

		@SuppressWarnings("unchecked")
		ArrayList<Object> listaBanco = (ArrayList<Object>) banco
				.listarObjetosAsc(Palavra.class, "nome");

		for (int i = 0; i < listaBanco.size(); i++) {
			Palavra palavraBanco = (Palavra) listaBanco.get(i);
			palavraBanco.setNome(palavraBanco.getNome().replace(";", "")
					.replace(".", "").replace(",", "").replace("_", "")
					.replace("(", "").replace(")", "").replace(" ", ""));
			palavraBanco.setMateria("DIREITO PREVIDENCIARIO");
			banco.salvarOuAtualizarObjeto(palavraBanco);
			if (palavraBanco.getQuantProvas() < 0
					|| palavraBanco.getOcorrencia()<2) {
System.out.println(palavraBanco.getNome());
				// JOptionPane.showMessageDialog(null,
				// "palavra igual: "+palavra.getNome());

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

		contentPane.remove(boxMateria);
		contentPane.remove(boxBanca);

		txtDescricao.setText("");

		boxBanca = new JComboBox(listaBancas());
		boxBanca.setBounds(95, 50, 89, 25);
		contentPane.add(boxBanca);

		boxMateria = new JComboBox(listaMaterias());
		boxMateria.setBounds(95, 15, 245, 25);
		contentPane.add(boxMateria);
		invalidate();
		validate();
		repaint();

	}

	private void salvar() {

		String descricao = txtDescricao.getText();

		String texto = textArea.getText();

		int a = 0;
		// define um ponto posterior
		int b = texto.indexOf(" ", a);

		String txtIndicador = texto.substring(a, b + 100);

		materia = (String) boxMateria.getSelectedItem();

		salvaProva(descricao, txtIndicador, materia, texto);

		listarTudo(listaNomes(texto));


		atualizaBox();


	}

	public static ArrayList<Palavra> listaNomes(String texto) {


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
					.replace(",", "").replace("_", "").replace("(", "")
					.replace(")", "").replace(":", "");
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
		ArrayList<Object> listaBanco = (ArrayList<Object>) banco
				.listarObjetosAsc(Palavra.class, "nome");

		for (int i = 0; i < listaBanco.size(); i++) {
			Palavra palavraBanco = (Palavra) listaBanco.get(i);
			if (palavraBanco.getNome().equalsIgnoreCase(palavra.getNome())
					&& palavraBanco.getMateria().equalsIgnoreCase(
							palavra.getMateria())) {

				// JOptionPane.showMessageDialog(null,
				// "palavra igual: "+palavra.getNome());

				palavraBanco.setOcorrencia(palavraBanco.getOcorrencia()
						+ palavra.getOcorrencia());
				palavraBanco.setQuantProvas(palavraBanco.getQuantProvas() + 1);
				banco.salvarOuAtualizarObjeto(palavraBanco);
				palavra = null;
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
		List<Object> listaObj = (List<Object>) banco.listarObjetosAsc(
				Prova.class, "id");
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

	private void criaMateria() {
		String nomeClasse = JOptionPane.showInputDialog("Nome da Materia: ");

		Materia materia = new Materia();

		materia.setNome(nomeClasse);

		banco.salvarObjeto(materia);

		atualizaBox();

	}
}

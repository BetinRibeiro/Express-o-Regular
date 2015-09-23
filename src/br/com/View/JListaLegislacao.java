package br.com.View;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import br.com.Bin.ArtigoLei;
import br.com.Bin.Palavra;
import br.com.Persistencia.Banco;
import br.com.TableModel.ModelLegislacao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class JListaLegislacao extends JDialog {

	private JPanel contentPane;
	private Banco banco = new Banco();
	private JTable table;
	private ModelLegislacao model = new ModelLegislacao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JListaLegislacao frame = new JListaLegislacao();
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
	public JListaLegislacao() {
		setBounds(10, 50, 632, 486);
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnZerar = new JMenu("Zerar");
		menuBar.add(mnZerar);

		JMenuItem mntmPrioridades = new JMenuItem("Zerar Prioridades");
		mntmPrioridades.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				zararOcorrencia();

			}

			private void zararOcorrencia() {
				zerar();

			}

		});
		mnZerar.add(mntmPrioridades);

		JMenu mnProcessar = new JMenu("Processar");
		menuBar.add(mnProcessar);

		JMenuItem mntmProcessarPrioridade = new JMenuItem("Processar Prioridade");
		mntmProcessarPrioridade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processar();
				atualizarTabela();

			}
		});
		mnProcessar.add(mntmProcessarPrioridade);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 596, 365);
		contentPane.add(scrollPane);

		table = new JTable(model);
		scrollPane.setViewportView(table);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(10, 397, 89, 23);
		contentPane.add(btnSair);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela();
			}
		});
		btnAtualizar.setBounds(109, 397, 89, 23);
		contentPane.add(btnAtualizar);

		JButton btnUnir = new JButton("Unir");
		btnUnir.setVisible(false);
		btnUnir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);

					ArtigoLei art = (ArtigoLei) banco.buscarPorId(ArtigoLei.class, id);

					ArtigoLei artAnterior = (ArtigoLei) banco.buscarPorId(ArtigoLei.class, id - 1);

					System.out.println("artigo anterior " + artAnterior.getId());
					artAnterior.setConteudo(artAnterior.getConteudo() + " " + art.getConteudo());

					banco.salvarOuAtualizarObjeto(artAnterior);
					banco.deletarObjeto(art);
					atualizarTabela();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}

		});
		btnUnir.setBounds(208, 397, 89, 23);
		contentPane.add(btnUnir);

		JButton btnPrioridade = new JButton("Prioridade");
		btnPrioridade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.removeTudo();

				List<?> lista = banco.listarObjetosDesc(ArtigoLei.class, "prioridade");
				System.out.println(lista.size());

				for (int i = 0; i < lista.size(); i++) {
					ArtigoLei art = (ArtigoLei) lista.get(i);
					model.addRow(art);

				}
			}
		});
		btnPrioridade.setBounds(517, 397, 89, 23);
		contentPane.add(btnPrioridade);
		
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArtigoLei art = ((ArtigoLei) banco.buscarPorId(
						ArtigoLei.class,
						(Integer) table.getValueAt(
								table.getSelectedRow(), 0)));
				JVerArt j = new JVerArt(art);
				j.setVisible(true);
			}
		});
		btnVer.setBounds(418, 397, 89, 23);
		contentPane.add(btnVer);

	
		atualizarTabela();
	}

	// private void deletar() {
	// try {
	//
	// Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
	//
	// ArtigoLei art = (ArtigoLei) banco.buscarPorId(ArtigoLei.class, id);
	// banco.deletarObjeto(art);
	//
	// atualizarTabela();
	// } catch (Exception e) {
	// JOptionPane.showMessageDialog(null, e);
	// }
	//
	// }

	private void processar() {
		zerar();

		ArrayList<?> listaPalavras = (ArrayList<?>) banco.listarObjetosDesc(Palavra.class, "ocorrencia");

		ArrayList<?> listaArtigos = (ArrayList<?>) banco.listarObjetosDesc(ArtigoLei.class, "id");

		for (int i = 0; i < listaPalavras.size(); i++) {

			for (int j = 0; j < listaArtigos.size(); j++) {

				ArtigoLei artigo = (ArtigoLei) listaArtigos.get(j);

				ArrayList<Palavra> listaPalavraArtigo = listaOcorrencia(artigo.getConteudo());

				for (int k = 0; k < listaPalavraArtigo.size(); k++) {
					Palavra palavraComparada = (Palavra) listaPalavras.get(i);

					Palavra palavraArtigo = listaPalavraArtigo.get(k);

					if (palavraComparada.getNome().equals(palavraArtigo.getNome())) {
						float prioridade = palavraComparada.getOcorrencia() / palavraComparada.getQuantProvas();
						artigo.setPrioridade(prioridade + artigo.getPrioridade());
						banco.salvarOuAtualizarObjeto(artigo);
						System.out.println("numero do laço " + i);
						System.out.println("Tamanho total " + listaPalavras.size());

					}

				}

			}

		}
	}

	private ArrayList<Palavra> listaOcorrencia(String texto) {
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
			palavra = texto.substring(a, b).replace(";", "").replace(".", "").replace(",", "").replace("_", "")
					.replace("(", "").replace(")", "").replace(":", "");
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

	private void atualizarTabela() {
		model.removeTudo();

		List<?> lista = banco.listarObjetosAsc(ArtigoLei.class, "id");
		System.out.println(lista.size());

		for (int i = 0; i < lista.size(); i++) {
			ArtigoLei art = (ArtigoLei) lista.get(i);
			model.addRow(art);

		}
	}

	private void zerar() {
		ArrayList<?> lista = (ArrayList<?>) banco.listarObjetosAsc(ArtigoLei.class, "id");

		for (int i = 0; i < lista.size(); i++) {
			System.out.println("Entrou");
			ArtigoLei artigo = (ArtigoLei) lista.get(i);
			artigo.setPrioridade(0);
			banco.salvarOuAtualizarObjeto(artigo);
		}
		atualizarTabela();

	}
}

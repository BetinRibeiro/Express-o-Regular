package br.com.View;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.com.TableModel.*;
import br.com.Bin.*;
import br.com.Persistencia.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class JListaPalavra extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private ModelPalavras model = new ModelPalavras();
	private Banco banco = new Banco();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JListaPalavra frame = new JListaPalavra();
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
	public JListaPalavra() {
		setTitle("Lista de Ocorrencia das Palavras");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(730, 350, 632, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 596, 222);
		contentPane.add(scrollPane);

		table = new JTable(model);

		// tabela com colunas fixasv
		table.getTableHeader().setReorderingAllowed(false);
		// tamanho especifico da coluna
		table.getColumn("Nome").setPreferredWidth(250);

		// seleciona apenas uma linha
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane.setViewportView(table);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela();
			}
		});
		btnAtualizar.setBounds(10, 278, 89, 23);
		contentPane.add(btnAtualizar);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Integer id = (Integer) table.getValueAt(
							table.getSelectedRow(), 0);

					Palavra pala = (Palavra) banco.buscarPorId(Palavra.class, id);
					banco.deletarObjeto(pala);

					
					atualizarTabela();
				} catch (Exception ea) {
					JOptionPane.showMessageDialog(null, "ERRO - " + ea
							+ ".(Selecione uma palavra para deletar!!) ");
				}
				
			}
		});
		btnDeletar.setBounds(109, 278, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSair.setBounds(208, 278, 89, 23);
		contentPane.add(btnSair);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 620, 21);
		contentPane.add(menuBar);
		
		JMenu mnExcluir = new JMenu("Excluir");
		menuBar.add(mnExcluir);
		
		JMenuItem mntmRestrines = new JMenuItem("Restrin\u00E7\u00F5es");
		mntmRestrines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				retirarRestrincoes();
				
			}

			private void retirarRestrincoes() {
				ArrayList<?> listaRestrincao = (ArrayList<?>) banco.listarObjetosDesc(Restricao.class, "id");
				ArrayList<?> listaPalavras = (ArrayList<?>) banco.listarObjetosDesc(Palavra.class, "ocorrencia");

				for (int i = 0; i < listaRestrincao.size(); i++) {
					for (int j = 0; j < listaPalavras.size(); j++) {
						Restricao restrincao = (Restricao) listaRestrincao.get(i);
						Palavra palavra = (Palavra) listaPalavras.get(j);
						if (restrincao.getNome().equals(palavra.getNome())) {
							System.out.println(palavra.getNome());
							banco.deletarObjeto(palavra);
							break;
						}
					}
					
				}
				
			}
		});
		mnExcluir.add(mntmRestrines);
		
		atualizarTabela();
	}
	private void processar() {
		
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
						float prioridade = palavraComparada.getOcorrencia()/palavraComparada.getQuantProvas();
						artigo.setPrioridade(prioridade+artigo.getPrioridade());
						banco.salvarOuAtualizarObjeto(artigo);
						
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

	private void atualizarTabela() {
		model.removeTudo();

		List<?> lista = banco.listarObjetosDesc(Palavra.class, "ocorrencia");
		System.out.println(lista.size());
		
		for (int i = 0; i < lista.size(); i++) {
			Palavra palavra = (Palavra) lista.get(i);
			model.addRow(palavra);

		}
	}
}

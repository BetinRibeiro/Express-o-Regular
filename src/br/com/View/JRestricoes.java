package br.com.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.transaction.Transactional.TxType;

import org.hibernate.property.Getter;

import br.com.Bin.Restricao;
import br.com.Persistencia.Banco;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Dialog.ModalExclusionType;

public class JRestricoes extends JFrame {

	private JPanel contentPane;
	private JTextField txtClasse;
	private JTextArea textAreaRestricaos;
	private Banco banco = new Banco();
	String classificacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JRestricoes frame = new JRestricoes();
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
	public JRestricoes() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 722, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 686, 555);
		contentPane.add(scrollPane);

		textAreaRestricaos = new JTextArea();
		scrollPane.setViewportView(textAreaRestricaos);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String texto = textAreaRestricaos.getText().replace(";", "")
						.replace(".", "").replace(",", "").replace("_", "")
						.replace("(", "").replace(")", "").replace(":", "")
						.replace("–", "").replace("-", "").replace("\"", " ");
				;

				classificacao = txtClasse.getText();
				listarTudo(listaNomes(texto));

				txtClasse.setText("");
				textAreaRestricaos.setText("");
			}
		});
		btnAdicionar.setBounds(10, 630, 89, 25);
		contentPane.add(btnAdicionar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaRestricaos.setText("");
				txtClasse.setText("");
			}
		});
		btnCancelar.setBounds(109, 630, 89, 25);
		contentPane.add(btnCancelar);

		JLabel lblClasseDeRestricaos = new JLabel("Classe de Restricaos");
		lblClasseDeRestricaos.setBounds(10, 30, 104, 25);
		contentPane.add(lblClasseDeRestricaos);

		txtClasse = new JTextField();
		txtClasse.setBounds(124, 30, 239, 25);
		contentPane.add(txtClasse);
		txtClasse.setColumns(10);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(209, 631, 89, 25);
		contentPane.add(btnSair);
	}

	public ArrayList<Restricao> listaNomes(String texto) {

		texto = texto.replace(";", "").replace(".", "").replace(",", "")
				.replace("_", "").replace("(", "").replace(")", "")
				.replace(":", "").replace("\n$", "");// .replace("-", "")

		// cria uma lista para poder armazenar as palavras
		ArrayList<Restricao> lista = new ArrayList<Restricao>();

		// define um ponto incial
		int a = 0;
		// define um ponto posterior
		int b = texto.indexOf(" ", a);

		// aloca a palavra
		String palavra = texto.substring(a, b);

		// modifica o proximo ponto de partida para a proxima palavra
		a = b + 1;
		// aloca o ponto apos ao proximo para coletar a proxima palavra
		b = texto.indexOf(" ", a);

		// instancia a palavra
		Restricao rest = new Restricao();
		// seta valores para as variais
		rest.setId(0);
		rest.setNome(palavra);
		String classe = txtClasse.getText();
		rest.setClassePalavra(classe);

		// adiciona a primeira palavra na lista
		lista.add(rest);

		//

		// System.out.println(b);

		while (b != -1) {
			// coleta a proxima palavra
			palavra = texto.substring(a, b).replace(";", "").replace(".", "")
					.replace(",", "").replace("_", "").replace("(", "")
					.replace(")", "").replace(":", "").replace("-", "");

			// modifica o ponto de partida
			a = b + 1;
			// modifica o ponto apos o da partida para poder pegar a proxima
			// palavra
			b = texto.indexOf(" ", a);

			for (int i = 0; i < lista.size(); i++) {

				// lista.get(0).getNome());

				if (!palavra.equalsIgnoreCase(lista.get(i).getNome())) {
					rest = new Restricao();
					rest.setId(0);
					palavra.replace(";", "").replace(".", "").replace(",", "")
							.replace("_", "").replace("(", "").replace(")", "");// .replace(":",
																				// "").replace("-",
																				// "");
					rest.setNome(palavra);
					rest.setClassePalavra(classe);
					lista.add(rest);
					break;
				}
			}

		}
		return lista;
	}

	public void listarTudo(ArrayList<Restricao> lista) {
		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getNome().length() > 1) {
				Restricao obj = new Restricao();
				obj.setNome(lista.get(i).getNome());
				obj.setClassePalavra(lista.get(i).getClassePalavra());
				;
				salvaRestricao(obj);

			}

		}

	}

	public void salvaRestricao(Restricao restr) {

		@SuppressWarnings("unchecked")
		ArrayList<Object> listaBanco = (ArrayList<Object>) banco
				.listarObjetosAsc(Restricao.class, "nome");

		for (int i = 0; i < listaBanco.size(); i++) {
			Restricao palavraBanco = (Restricao) listaBanco.get(i);
			if (palavraBanco.getNome().equalsIgnoreCase(restr.getNome())) {
				System.out.println("palavra igual no banco " + restr.getNome());
				restr = null;
				break;
			}
		}
		if (restr != null) {
			System.out.println("salvou " + restr.getNome() + " no banco");
			restr.setClassePalavra(classificacao);
			System.out.println(classificacao);
			banco.salvarObjeto(restr);

		}

	}

}

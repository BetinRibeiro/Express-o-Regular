package br.com.View;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import br.com.Bin.Palavra;
import br.com.Persistencia.Banco;

import javax.swing.JTextArea;
import java.awt.Color;

@SuppressWarnings("serial")
public class JAdicionaPalavras extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JButton btnSalvar;
	private JButton btnCancelar;

	private Banco banco = new Banco();
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAdicionaPalavras frame = new JAdicionaPalavras();
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
	public JAdicionaPalavras() {
		setTitle("Ordena\u00E7\u00E3o de Provas e Ocorrencia de Palavras");
//		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		setBounds(10, 50, 722, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 686, 462);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(607, 551, 89, 25);
		contentPane.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(508, 551, 89, 25);
		contentPane.add(btnCancelar);

		JTextArea lblAdicioneUmTexto = new JTextArea(
				"Adicione um texto para que o sistema fa\u00E7a uma contagem das palavras mais ocorridas, pode ser uma prova inteira ou simplesmente um conjunte de quest\u00F5es, importante que seja da mesma materia");
		lblAdicioneUmTexto.setDisabledTextColor(Color.BLACK);
		lblAdicioneUmTexto.setCaretColor(Color.BLACK);
		lblAdicioneUmTexto.setBackground(new Color(245, 245, 245));
		lblAdicioneUmTexto.setForeground(Color.BLACK);
		lblAdicioneUmTexto.setBorder(null);
		lblAdicioneUmTexto.setEnabled(false);
		lblAdicioneUmTexto.setEditable(false);
		lblAdicioneUmTexto.setBounds(10, 11, 686, 56);
		lblAdicioneUmTexto.setWrapStyleWord(true);
		lblAdicioneUmTexto.setLineWrap(true);
		contentPane.add(lblAdicioneUmTexto);

		btnCancelar.addActionListener(this);
		btnSalvar.addActionListener(this);
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

		default:
			break;
		}
	}

	private void salvar() {

		String texto = textArea.getText();

		int a = 0;
		// define um ponto posterior
		int b = texto.indexOf(" ", a);

		listarTudo(listaNomes(texto));

		textArea.setText("");
		JOptionPane.showMessageDialog(contentPane, "As palavras já foram contadas!");
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

	public void listarTudo(ArrayList<Palavra> lista) {
		for (int i = 0; i < lista.size(); i++) {

			if (lista.get(i).getNome().length() >= 4) {
				Palavra obj = new Palavra();
				obj.setNome(lista.get(i).getNome());
				obj.setQuantProvas(1);
				obj.setOcorrencia(Integer.valueOf(lista.get(i).getOcorrencia()));

				salvaPalavra(obj);

			}

		}

	}

	public void salvaPalavra(Palavra palavra) {
		try {
			System.out.println(palavra.getNome());
			// List<?> listaBanco = banco.listarObjetosAsc(Palavra.class,
			// "nome");
			Palavra pal = (Palavra) banco.BuscaNome(Palavra.class, palavra.getNome(), "nome");
			System.out.println("esta palavra "+pal);
			System.out.println(pal==null);
			
			if (pal==null) {
				System.out.println("Salva palavra");
				banco.salvarObjeto(palavra);
			} if (pal != null)  {
				System.out.println("Altera palavra");
				pal.setOcorrencia(pal.getOcorrencia() + palavra.getOcorrencia());
				pal.setQuantProvas(pal.getQuantProvas() + palavra.getQuantProvas());
				banco.salvarOuAtualizarObjeto(pal);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}

package br.com.View;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import br.com.Bin.ArtigoLei;
import br.com.Persistencia.Banco;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class JAdicionaLei extends JDialog {

	private JPanel contentPane;
	private JTextField txtNomeLei;
	private JTextArea txtConteudo;
	
	private Banco banco = new Banco();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAdicionaLei frame = new JAdicionaLei();
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
	public JAdicionaLei() {
		setTitle("Ordenando uma Lei ");
//		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(10, 50, 632, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 596, 533);
		contentPane.add(scrollPane);

		txtConteudo = new JTextArea();
		scrollPane.setViewportView(txtConteudo);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String texto = txtConteudo.getText();

				ArrayList<ArtigoLei> lista = ordenarSalvando(texto);
				
				confereSalvaBanco(lista);

				txtConteudo.setText("");
				txtNomeLei.setText("");
			}

			

		});
		btnSalvar.setBounds(10, 625, 90, 25);
		contentPane.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtConteudo.setText("");
				txtNomeLei.setText("");

			}
		});
		btnCancelar.setBounds(110, 625, 90, 25);
		contentPane.add(btnCancelar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnSair.setBounds(210, 625, 90, 25);
		contentPane.add(btnSair);

		txtNomeLei = new JTextField();
		txtNomeLei.setBounds(120, 40, 319, 25);
		contentPane.add(txtNomeLei);
		txtNomeLei.setColumns(10);

		JLabel lblLei = new JLabel("Nome da Lei");
		lblLei.setBounds(10, 40, 89, 25);
		contentPane.add(lblLei);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 706, 21);
		contentPane.add(menuBar);
	}
	
	private void confereSalvaBanco(ArrayList<ArtigoLei> lista) {
		String erro = "";
		
		for (int i = 0; i < lista.size(); i++) {
				ArtigoLei art = new ArtigoLei();
				
				art.setConteudo(lista.get(i).getConteudo());
				art.setLei(lista.get(i).getLei());
				art.setNome(lista.get(i).getNome());
				art.setPrioridade(0);
				try {
					banco.salvarObjeto(art);
				} catch (Exception e) {
					
					erro=erro+" -  - "+e;
					System.out.println("Erro - " +erro);
				}
				
		}
		System.out.println(erro);
	}

	private ArrayList<ArtigoLei> ordenarSalvando(String texto) {

		ArrayList<ArtigoLei> lista = new ArrayList<ArtigoLei>();

		int a = texto.indexOf("Art.", 0);
		
		int b = texto.indexOf("Art.", a+1);
		
		String conteudo = texto.substring(a - 1, b);
		String nome = conteudo.substring(1, 10);
		a++;

		ArtigoLei art = new ArtigoLei();

		art.setNome(nome);
		art.setConteudo(conteudo);
		art.setLei(txtNomeLei.getText());
		art.setPrioridade(0);

		lista.add(art);

		while (b != -1 && b!=texto.length()) {
			
			
			 a = texto.indexOf("Art.", a);
			
			 b = texto.indexOf("Art.", a+1);
			 if (b==-1) {
					b=texto.length();
				}

			 conteudo = texto.substring(a - 1, b);
			 nome = conteudo.substring(1, 10);
			a++;

			 art = new ArtigoLei();


			
				conteudo = texto.substring(a - 1, b);
				
			
			
			art = new ArtigoLei();

			art.setNome(nome);
			art.setConteudo(conteudo);
			art.setLei(txtNomeLei.getText());
			art.setPrioridade(0);

			lista.add(art);
			

		}
		
		return lista;

	}
}

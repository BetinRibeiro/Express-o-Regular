package br.com.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.transaction.Transactional.TxType;

import br.com.Bin.ArtigoLei;
import br.com.Bin.Palavra;
import br.com.Persistencia.Banco;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class JAdicionaLei extends JFrame {

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(730, 0, 632, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 686, 533);
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
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmOrganizar = new JMenuItem("Organizar");
		mntmOrganizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				organizaArtigosLei();
			}

			
		});
		mnArquivo.add(mntmOrganizar);
	}
	
	private void confereSalvaBanco(ArrayList<ArtigoLei> lista) {
		String erro = "";
		
		for (int i = 0; i < lista.size(); i++) {
				ArtigoLei art = new ArtigoLei();
				
				art.setConteudo(lista.get(i).getConteudo());
				//System.out.println("tamanho "+art.getConteudo().length());
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
		a++;
		int b = texto.indexOf("Art.", a);

		int c = texto.indexOf(".", a);

		c++;
		c = texto.indexOf(".", c);

		String nome = texto.substring(a - 1, c);

		String conteudo = texto.substring(a - 1, b);

		ArtigoLei art = new ArtigoLei();

		art.setNome(nome);
		art.setConteudo(conteudo);
		art.setLei(txtNomeLei.getText());
		art.setPrioridade(0);

		System.out.println("Art: " + art.getNome() + " fim");
		System.out.println(art.getConteudo());
		lista.add(art);
		System.out.println("_______________________________________________________________________________");

		while (b != -1) {
			a = b + 1;

			b = texto.indexOf("Art.", a);

			c = texto.indexOf(".", a);

			c++;
			c = texto.indexOf(".", c);

			System.out.println(a);
			System.out.println(b);
			nome = texto.substring(a - 1, c);

			if (b!=-1) {
				conteudo = texto.substring(a - 1, b);
				
			}else {
				conteudo = texto.substring(a - 1, texto.length());
			}
			

			a = b + 1;

			b = texto.indexOf("Art.", a);

			art = new ArtigoLei();

			art.setNome(nome);
			art.setConteudo(conteudo);
			art.setLei(txtNomeLei.getText());
			art.setPrioridade(0);

			System.out.println("Art: " + art.getNome() + " fim");
			System.out.println(art.getConteudo());
			lista.add(art);
			
			System.out.println("_______________________________________________________________________________");

		}
		return lista;

	}
	private void organizaArtigosLei() {
		ArrayList<?> lista =  (ArrayList<?>) banco.listarObjetosAsc(ArtigoLei.class, "id");
		
		System.out.println(lista.size());
		for (int i = 0; i < lista.size(); i++) {
			ArtigoLei ar = (ArtigoLei) lista.get(i);
//			System.out.println(ar.getId() +" - "+ar.getConteudo().length());
			int comp = ar.getNome().length(); 
			if (comp>8) {
				int n = i-1;
				System.out.println("um antes .."+n);
				ArtigoLei art = (ArtigoLei) lista.get(n);
				System.out.println(art.getNome());
				art.setConteudo(art.getConteudo()+ar.getConteudo());
				
				banco.salvarOuAtualizarObjeto(art);
				banco.deletarObjeto(ar);
				System.out.println("deletou obj com id "+ar.getId());
				
			}
			
		}

	}
}

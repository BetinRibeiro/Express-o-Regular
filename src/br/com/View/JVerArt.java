package br.com.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.Bin.ArtigoLei;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JVerArt extends JDialog {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JVerArt frame = new JVerArt(null);
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
	public JVerArt(ArtigoLei art) {
		setTitle("Artigo");
		setBounds(100, 100, 750, 453);
		this.setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel(art.getNome()+" - "+art.getLei());
		lblTitulo.setBounds(10, 10, 459, 14);
		contentPane.add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 714, 374);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea(art.getConteudo());
		scrollPane.setViewportView(textArea);
	}
}

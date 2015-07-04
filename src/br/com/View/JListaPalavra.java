package br.com.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

public class JListaPalavra extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private ModelPalavras model = new ModelPalavras();
	private Banco banco = new Banco();

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JListaPalavra frame = new JListaPalavra();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public JListaPalavra() {
		setTitle("Lista de Ocorrencia das Palavras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(730, 0, 632, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 596, 606);
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
		btnAtualizar.setBounds(10, 628, 89, 23);
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
		btnDeletar.setBounds(109, 628, 89, 23);
		contentPane.add(btnDeletar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSair.setBounds(208, 628, 89, 23);
		contentPane.add(btnSair);
		
		atualizarTabela();
	}
	private void atualizarTabela() {
		model.removeTudo();

		List<?> lista = banco.listarObjetos(Palavra.class, "ocorrencia");
		System.out.println(lista.size());
		
		for (int i = 0; i < lista.size(); i++) {
			Palavra palavra = (Palavra) lista.get(i);
			model.addRow(palavra);

		}
	}
}

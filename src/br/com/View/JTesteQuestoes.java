package br.com.View;

import java.awt.EventQueue;
import java.awt.Frame;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import br.com.Persistencia.Banco;
import br.com.Bin.Desempenho;
import br.com.Bin.Opcao;
import br.com.Bin.Questao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class JTesteQuestoes extends JFrame {

	private JPanel contentPane;
	private JButton btnFinalizar;
	private JRadioButton ltE;
	private JButton btnResponder;
	private JTextArea txtrE;
	private JRadioButton ltC;
	private JRadioButton ltD;
	private JTextArea txtrD;
	private JTextArea txtrC;
	private JTextArea txtrB;
	private JRadioButton ltB;
	private JTextArea txtrA;
	private JRadioButton ltA;
	private JTextArea txtEnunciado;

	private Banco banco = new Banco();

	private ButtonGroup grupoBotoes;

	private ArrayList<Integer> listaQuestaoResposta = new ArrayList<Integer>();
	private ArrayList<Integer> listaOpcoesApresentadas = new ArrayList<Integer>();
	private ArrayList<ArrayList<Integer>> listaHistorico = new ArrayList<ArrayList<Integer>>();
	private JLabel lblAcert0;
	private JLabel lblErr0;
	private JLabel lblTotal0;
	private JLabel lblQuest;

	private float acertos = 0;
	private float erros = 0;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnLiberar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTesteQuestoes frame = new JTesteQuestoes();
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
	public JTesteQuestoes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1635, 760);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(200, 10, 950, 700);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel painelEnunciado = new JPanel();
		painelEnunciado.setBorder(new LineBorder(new Color(0, 0, 0)));
		painelEnunciado.setBounds(10, 11, 930, 207);
		panel.add(painelEnunciado);
		painelEnunciado.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 910, 185);
		painelEnunciado.add(scrollPane);

		txtEnunciado = new JTextArea();
		txtEnunciado.setEditable(false);
		scrollPane.setViewportView(txtEnunciado);

		JPanel painelOpcoes = new JPanel();
		painelOpcoes.setBorder(new LineBorder(new Color(0, 0, 0)));
		painelOpcoes.setBounds(10, 229, 930, 428);
		panel.add(painelOpcoes);
		painelOpcoes.setLayout(null);

		ltA = new JRadioButton("A");
		ltA.setBounds(22, 20, 33, 23);
		painelOpcoes.add(ltA);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(60, 20, 400, 120);
		painelOpcoes.add(scrollPane_1);

		txtrA = new JTextArea();
		txtrA.setEditable(false);
		txtrA.setText("A");
		scrollPane_1.setViewportView(txtrA);

		ltB = new JRadioButton("B");
		ltB.setBounds(470, 20, 33, 23);
		painelOpcoes.add(ltB);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(510, 20, 400, 120);
		painelOpcoes.add(scrollPane_2);

		txtrB = new JTextArea();
		txtrB.setEditable(false);
		txtrB.setText("B");
		scrollPane_2.setViewportView(txtrB);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(60, 160, 400, 120);
		painelOpcoes.add(scrollPane_3);

		txtrC = new JTextArea();
		txtrC.setEditable(false);
		txtrC.setText("C");
		scrollPane_3.setViewportView(txtrC);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(510, 160, 400, 120);
		painelOpcoes.add(scrollPane_4);

		txtrD = new JTextArea();
		txtrD.setEditable(false);
		txtrD.setText("D");
		scrollPane_4.setViewportView(txtrD);

		ltD = new JRadioButton("D");
		ltD.setBounds(470, 160, 33, 23);
		painelOpcoes.add(ltD);

		ltC = new JRadioButton("C");
		ltC.setBounds(22, 160, 33, 23);
		painelOpcoes.add(ltC);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(60, 300, 400, 120);
		painelOpcoes.add(scrollPane_5);

		txtrE = new JTextArea();
		txtrE.setEditable(false);
		txtrE.setText("E");
		scrollPane_5.setViewportView(txtrE);

		ltE = new JRadioButton("E");
		ltE.setBounds(22, 300, 33, 23);
		painelOpcoes.add(ltE);

		txtEnunciado.setLineWrap(true);
		txtrA.setLineWrap(true);
		txtrB.setLineWrap(true);
		txtrC.setLineWrap(true);
		txtrD.setLineWrap(true);
		txtrE.setLineWrap(true);

		btnResponder = new JButton("Responder");
		btnResponder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				responder();
			}

		});
		btnResponder.setBounds(730, 664, 100, 25);
		panel.add(btnResponder);

		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(840, 664, 100, 25);
		panel.add(btnFinalizar);
		// System.out.println(getExtendedState());

		grupoBotoes = new ButtonGroup();

		grupoBotoes.add(ltA);
		grupoBotoes.add(ltB);
		grupoBotoes.add(ltC);
		grupoBotoes.add(ltD);
		grupoBotoes.add(ltE);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnResponder.setEnabled(true);
				btnSalvar.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnLiberar.setEnabled(true);

				Opcao op = (Opcao) banco.buscarPorId(Opcao.class,
						listaOpcoesApresentadas.get(0));
				op.setDescricao(txtrA.getText());
				System.out.println(op.getDescricao());

				Opcao op1 = (Opcao) banco.buscarPorId(Opcao.class,
						listaOpcoesApresentadas.get(1));
				op1.setDescricao(txtrB.getText());
				System.out.println(op1.getDescricao());

				Opcao op2 = (Opcao) banco.buscarPorId(Opcao.class,
						listaOpcoesApresentadas.get(2));
				op2.setDescricao(txtrC.getText());
				System.out.println(op2.getDescricao());

				Opcao op3 = (Opcao) banco.buscarPorId(Opcao.class,
						listaOpcoesApresentadas.get(3));
				op3.setDescricao(txtrD.getText());
				System.out.println(op3.getDescricao());

				Opcao op4 = (Opcao) banco.buscarPorId(Opcao.class,
						listaOpcoesApresentadas.get(4));
				op4.setDescricao(txtrE.getText());
				System.out.println(txtrE.getText());
				System.out.println(op4.getDescricao());

				Questao q = (Questao) banco.buscarPorId(Questao.class,
						op4.getIdQuestao());
				System.out.println(q.getEnunciado());

				q.setEnunciado(txtEnunciado.getText());
				banco.salvarOuAtualizarObjeto(op);
				banco.salvarOuAtualizarObjeto(op1);
				banco.salvarOuAtualizarObjeto(op2);
				banco.salvarOuAtualizarObjeto(op3);
				banco.salvarOuAtualizarObjeto(op4);
				banco.salvarOuAtualizarObjeto(q);

				listaOpcoesApresentadas.clear();
				insereQuestao();

			}
		});
		btnSalvar.setBounds(208, 664, 100, 25);
		panel.add(btnSalvar);

		btnLiberar = new JButton("Liberar");
		btnLiberar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnResponder.setEnabled(false);

				btnSalvar.setEnabled(true);
				btnCancelar.setEnabled(true);
				btnLiberar.setEnabled(false);

				txtEnunciado.setEditable(true);
				txtrA.setEditable(true);
				txtrB.setEditable(true);
				txtrC.setEditable(true);
				txtrD.setEditable(true);
				txtrE.setEditable(true);
			}
		});
		btnLiberar.setBounds(10, 665, 89, 23);
		panel.add(btnLiberar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnResponder.setEnabled(true);
				btnSalvar.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnLiberar.setEnabled(true);
				insereQuestao();
			}
		});
		btnCancelar.setBounds(109, 665, 89, 23);
		panel.add(btnCancelar);

		JLabel lblAcertos = new JLabel("Acertos");
		lblAcertos.setBounds(10, 35, 70, 14);
		contentPane.add(lblAcertos);

		lblAcert0 = new JLabel("0");
		lblAcert0.setBounds(97, 35, 46, 14);
		contentPane.add(lblAcert0);

		JLabel lblErros = new JLabel("Erros");
		lblErros.setBounds(10, 60, 70, 14);
		contentPane.add(lblErros);

		lblErr0 = new JLabel("0");
		lblErr0.setBounds(97, 60, 46, 14);
		contentPane.add(lblErr0);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 85, 70, 14);
		contentPane.add(lblTotal);

		lblTotal0 = new JLabel("0");
		lblTotal0.setBounds(97, 85, 46, 14);
		contentPane.add(lblTotal0);

		JLabel label = new JLabel("Questao");
		label.setBounds(1160, 11, 70, 14);
		contentPane.add(label);

		lblQuest = new JLabel("Quest");
		lblQuest.setBounds(1247, 11, 70, 14);
		contentPane.add(lblQuest);

//		List<?> li = banco.listarObjetosAsc(Questao.class, "numeroOcorrencia");

		// for (int i = 0; i < li.size(); i++) {
		// Questao q = (Questao) li.get(i);
		//
		// if (q.getTitulo().equalsIgnoreCase(
		// "")) {
		// q.setTitulo("DIREITO PREVIDENCIARIO");
		// System.out
		// .println("__________________________________________________________________________");
		// System.out.println(q.getEnunciado());
		// System.out
		// .println("__________________________________________________________________________");
		//
		// // banco.salvarOuAtualizarObjeto(q);
		// }
		// }

		// System.out.println(lblAcertos.getText());

		insereQuestao();
	}

	@SuppressWarnings("unchecked")
	private void insereQuestao() {
		txtEnunciado.setEditable(false);
		txtrA.setEditable(false);
		txtrB.setEditable(false);
		txtrC.setEditable(false);
		txtrD.setEditable(false);
		txtrE.setEditable(false);

		List<?> li = banco.listarObjetosAsc(Questao.class, "numeroOcorrencia");

		Questao quest = (Questao) li.get(0);

		txtEnunciado.setText(quest.getEnunciado());

		lblQuest.setText(String.valueOf(quest.getId()));

		listaQuestaoResposta.add(quest.getId());

		ArrayList<Opcao> listOpcoes = (ArrayList<Opcao>) banco
				.listarObjetosAsc(Opcao.class, "id");

		for (int i = 0; i < listOpcoes.size(); i++) {
			Opcao op = listOpcoes.get(i);
			if (op.getIdQuestao().equals(quest.getId())) {
				listaOpcoesApresentadas.add(op.getId());
				if (op.getVerdadeira().equals(true)) {
					listaQuestaoResposta.add(op.getId());
				}
			}
		}

		Collections.shuffle(listaOpcoesApresentadas);
		Opcao op = ((Opcao) banco.buscarPorId(Opcao.class,
				listaOpcoesApresentadas.get(0)));

		txtrA.setText(op.getDescricao());
		op = ((Opcao) banco.buscarPorId(Opcao.class,
				listaOpcoesApresentadas.get(1)));

		txtrB.setText(op.getDescricao());
		op = ((Opcao) banco.buscarPorId(Opcao.class,
				listaOpcoesApresentadas.get(2)));

		txtrC.setText(op.getDescricao());
		op = ((Opcao) banco.buscarPorId(Opcao.class,
				listaOpcoesApresentadas.get(3)));

		txtrD.setText(op.getDescricao());

		op = ((Opcao) banco.buscarPorId(Opcao.class,
				listaOpcoesApresentadas.get(4)));

		txtrE.setText(op.getDescricao());

		System.out.println("questão - " + listaQuestaoResposta.get(0)
				+ ", resposta certa - " + listaQuestaoResposta.get(1));
		System.out.println("lista de questões -"
				+ listaOpcoesApresentadas.get(0) + " "
				+ listaOpcoesApresentadas.get(1) + " "
				+ listaOpcoesApresentadas.get(2) + " "
				+ listaOpcoesApresentadas.get(3) + " "
				+ listaOpcoesApresentadas.get(4) + " ");

		// System.out.println("lista na posição 2 " +
		// listaQuestaoResposta.get(2));
	}

	private void responder() {
		if (ltA.isSelected()) {
			if (listaOpcoesApresentadas.get(0).equals(
					listaQuestaoResposta.get(1))) {
				listaQuestaoResposta.add(1);
				Questao q = (Questao) banco.buscarPorId(Questao.class,
						listaQuestaoResposta.get(0));
				q.setDificuldade((q.getDificuldade() * (float) 0.75));
				q.setAcertos(q.getAcertos() + 1);
				banco.salvarOuAtualizarObjeto(q);
				acertos = acertos + 1;
				lblAcert0.setText(String.valueOf(acertos));
				JOptionPane.showMessageDialog(null, "Resposta Correta!!");
			}
		}
		if (ltB.isSelected()) {
			if (listaOpcoesApresentadas.get(1).equals(
					listaQuestaoResposta.get(1))) {
				listaQuestaoResposta.add(1);
				Questao q = (Questao) banco.buscarPorId(Questao.class,
						listaQuestaoResposta.get(0));
				q.setDificuldade((q.getDificuldade() * (float) 0.75));
				q.setAcertos(q.getAcertos() + 1);
				banco.salvarOuAtualizarObjeto(q);
				acertos = acertos + 1;
				lblAcert0.setText(String.valueOf(acertos));
				JOptionPane.showMessageDialog(null, "Resposta Correta!!");
			}
		}
		if (ltC.isSelected()) {
			if (listaOpcoesApresentadas.get(2).equals(
					listaQuestaoResposta.get(1))) {
				listaQuestaoResposta.add(1);
				Questao q = (Questao) banco.buscarPorId(Questao.class,
						listaQuestaoResposta.get(0));
				q.setDificuldade((q.getDificuldade() * (float) 0.75));
				q.setAcertos(q.getAcertos() + 1);
				banco.salvarOuAtualizarObjeto(q);
				acertos = acertos + 1;
				lblAcert0.setText(String.valueOf(acertos));
				JOptionPane.showMessageDialog(null, "Resposta Correta!!");
			}
		}
		if (ltD.isSelected()) {
			if (listaOpcoesApresentadas.get(3).equals(
					listaQuestaoResposta.get(1))) {
				listaQuestaoResposta.add(1);
				Questao q = (Questao) banco.buscarPorId(Questao.class,
						listaQuestaoResposta.get(0));
				q.setDificuldade((q.getDificuldade() * (float) 0.75));
				q.setAcertos(q.getAcertos() + 1);
				banco.salvarOuAtualizarObjeto(q);
				acertos = acertos + 1;
				lblAcert0.setText(String.valueOf(acertos));
				JOptionPane.showMessageDialog(null, "Resposta Correta!!");
			}
		}
		if (ltE.isSelected()) {
			if (listaOpcoesApresentadas.get(4).equals(
					listaQuestaoResposta.get(1))) {
				listaQuestaoResposta.add(1);
				Questao q = (Questao) banco.buscarPorId(Questao.class,
						listaQuestaoResposta.get(0));
				q.setDificuldade((q.getDificuldade() * (float) 0.75));
				q.setAcertos(q.getAcertos() + 1);
				banco.salvarOuAtualizarObjeto(q);
				acertos = acertos + 1;
				lblAcert0.setText(String.valueOf(acertos));

				JOptionPane.showMessageDialog(null, "Resposta Correta!!");
			}
		}

		System.out.println(listaQuestaoResposta.size());
		if (listaQuestaoResposta.size() <= 2) {
			listaQuestaoResposta.add(0);
			JOptionPane.showMessageDialog(null, "Sua Resposta esta errada! ");
			erros = erros + 1;
			lblErr0.setText(String.valueOf(erros));
			Questao q = (Questao) banco.buscarPorId(Questao.class,
					listaQuestaoResposta.get(0));
			q.setDificuldade((q.getDificuldade() * 2));
			q.setAcertos(q.getAcertos() - 1);
			banco.salvarOuAtualizarObjeto(q);
			// Opcao p = (Opcao) banco.buscarPorId(Opcao.class,
			// listaQuestaoResposta.get(1));
			// JMapeamento mp = new JMapeamento(txtEnunciado.getText() + "\n\n"
			// + txtrA.getText() + "\n\n" + txtrB.getText() + "\n\n"
			// + txtrC.getText() + "\n\n" + txtrD.getText() + "\n\n"
			// + txtrE.getText()+ "\n\n A resposta correta é : " +
			// p.getDescricao(), listaQuestaoResposta.get(0));
			// mp.setVisible(true);
		}

		Questao questao = (Questao) banco.buscarPorId(Questao.class,
				listaQuestaoResposta.get(0));

		questao.setNumeroOcorrencia(questao.getNumeroOcorrencia() + 1);

		listaHistorico.add(listaQuestaoResposta);

		listaQuestaoResposta.clear();
		listaOpcoesApresentadas.clear();

		banco.salvarOuAtualizarObjeto(questao);

		lblTotal0.setText(String.valueOf(acertos + erros));
		insereQuestao();
		if ((acertos + erros) >= 20) {
			salvar();
			dispose();
		}

	}

	private void salvar() {

		Desempenho des = new Desempenho();

		System.out.println(acertos + erros);
		des.setNiveis(acertos / (acertos + erros));
		des.setDate(new java.sql.Date(new java.util.Date().getTime()));

		JOptionPane
				.showMessageDialog(
						null,
						"Seu desemprenho doi de "
								+ des.getNiveis()
								+ " \n veja todos os resultados para verificar o grafico de desempenho");
		banco.salvarObjeto(des);
	}
}
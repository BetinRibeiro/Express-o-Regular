package br.com.View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jboss.jandex.Main;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Principal extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel foto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
					Principal frame = new Principal();
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
	public Principal() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		foto = new JLabel();
		foto.setBounds(0, 0, screenSize.width, screenSize.height);
		ImageIcon imagem = new ImageIcon(Main.class.getResource("/Img/01.jpg"));
		Image img = imagem.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT);
		foto.setIcon(new ImageIcon(img));

		setTitle("Organizador de Estudo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setBounds(0, 0, screenSize.width, screenSize.height);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane.add(foto);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		JMenuItem mntmResponderQuestes = new JMenuItem("Responder Quest\u00F5es");
		mnArquivo.add(mntmResponderQuestes);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);

		JMenu mnAdiconando = new JMenu("Adicionar");
		menuBar.add(mnAdiconando);

		JMenuItem mntmProvaContas = new JMenuItem("Prova - Contas Palavras");
		mnAdiconando.add(mntmProvaContas);

		JMenuItem mntmLegislaoSeparar = new JMenuItem("Legisla\u00E7\u00E3o - Separar Artigos");
		mnAdiconando.add(mntmLegislaoSeparar);

		JMenuItem mntmQuestes = new JMenuItem("Quest\u00F5es ");
		mnAdiconando.add(mntmQuestes);

		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);

		JMenuItem mntmOcorrenciaPalavras = new JMenuItem("Ocorrencia Palavras");
		mnConsulta.add(mntmOcorrenciaPalavras);

		JMenuItem mntmPrioridadeDeArtigos = new JMenuItem("Prioridade de Artigos");
		mnConsulta.add(mntmPrioridadeDeArtigos);

		JMenuItem mntmQuestes_1 = new JMenuItem("Consultar Quest\u00F5es");
		mnConsulta.add(mntmQuestes_1);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		mntmLegislaoSeparar.addActionListener(this);
		mntmOcorrenciaPalavras.addActionListener(this);
		mntmPrioridadeDeArtigos.addActionListener(this);
		mntmProvaContas.addActionListener(this);
		mntmQuestes.addActionListener(this);
		mntmQuestes_1.addActionListener(this);
		mntmResponderQuestes.addActionListener(this);
		mntmSair.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String acao = e.getActionCommand();
		System.out.println(acao);

		switch (acao) {
		case "Responder Quest\u00F5es":
			JTesteQuestoes testeQuestoes = new JTesteQuestoes();
			testeQuestoes.setVisible(true);

			break;
		case "Sair":

			break;
		case "Prova - Contas Palavras":
			JAdicionaPalavras janelaAddPalavras = new JAdicionaPalavras();
			janelaAddPalavras.setVisible(true);

			break;
		case "Legisla\u00E7\u00E3o - Separar Artigos":
			JAdicionaLei addlei = new JAdicionaLei();
			addlei.setVisible(true);
			break;
		case "Quest\u00F5es ":
			jCadastroQuestao cadastroQuestao = new jCadastroQuestao();
			cadastroQuestao.setVisible(true);

			break;
		case "Consulta":

			break;
		case "Ocorrencia Palavras":
			JListaPalavra listaPalavras = new JListaPalavra();
			listaPalavras.setVisible(true);
			break;
		case "Prioridade de Artigos":
			JListaLegislacao listaLei = new JListaLegislacao();
			listaLei.setVisible(true);

			break;
		case "Consultar Quest\u00F5es":

			break;

		default:
			break;
		}

	}
}

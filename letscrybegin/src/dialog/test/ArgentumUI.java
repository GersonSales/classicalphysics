package dialog.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArgentumUI {
	private static final int HEIGTH = 540;
	private static final int WIDTH = 540;

	public static void main(String[] args) {
		new ArgentumUI().montaTela();
	}

	private JFrame janela;
	private JPanel painelPrincipal;

	private void montaTela() {
		preparaJanela();
		preparaPainelPrincipal();
		preparaBotaoCarregar();
		preparaBotaoSair();
		mostraJanela();
	}

	private void preparaJanela() {
		janela = new JFrame("ArgentumUI");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
	}

	private void preparaBotaoCarregar() {
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.addActionListener((event) -> new EscolhedorDeXML().escolhe());
		painelPrincipal.add(botaoCarregar);

	}

	private void preparaBotaoSair() {
		JButton botaoSair = new JButton("Sair");

		botaoSair.addActionListener((event) -> System.exit(0));

		painelPrincipal.add(botaoSair);

	}

	private void mostraJanela() {
		janela.pack();
		janela.setSize(WIDTH, HEIGTH);
		janela.setVisible(true);
	}
}

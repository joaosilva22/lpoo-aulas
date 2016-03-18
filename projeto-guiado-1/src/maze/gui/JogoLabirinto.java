package maze.gui;

import maze.logic.Jogo;
import maze.logic.JogoDragaoAdormece;
import maze.logic.JogoMovimentoAleatorio;
import maze.logic.MazeBuilder;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JSlider;

public class JogoLabirinto {

	private MazeBuilder mb = new MazeBuilder(1);
	private Jogo jogo = new Jogo(mb.buildMaze(5));
	boolean playerHasMoved = false;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JogoLabirinto window = new JogoLabirinto();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JogoLabirinto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 684, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Dimensão do Labirinto");
		lblNewLabel.setBounds(33, 37, 160, 15);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNmeroDeDrages = new JLabel("Tipo de Dragões");
		lblNmeroDeDrages.setBounds(33, 91, 141, 15);
		frame.getContentPane().add(lblNmeroDeDrages);

		JLabel label = new JLabel("Número de Dragões");
		label.setBounds(33, 64, 141, 15);
		frame.getContentPane().add(label);

		final JComboBox fldDragonType = new JComboBox();
		fldDragonType.setModel(new DefaultComboBoxModel(new String[] {"Estáticos", "Dinâmicos", "Adormecidos"}));
		fldDragonType.setBounds(213, 86, 160, 24);
		frame.getContentPane().add(fldDragonType);

		final JTextArea fldDisplay = new JTextArea();
		fldDisplay.setEnabled(false);
		fldDisplay.setEditable(false);
		fldDisplay.setBounds(33, 133, 261, 284);
		fldDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
		fldDisplay.setDisabledTextColor(Color.BLACK);
		frame.getContentPane().add(fldDisplay);

		final JLabel lblNewGame = new JLabel("Pode gerar um novo labirinto!");
		lblNewGame.setBounds(33, 429, 220, 15);
		frame.getContentPane().add(lblNewGame);

		final JButton btnUp = new JButton("Cima");
		final JButton btnLeft = new JButton("Esquerda");
		final JButton btnRight = new JButton("Direita");
		final JButton btnDown = new JButton("Baixo");
		
		final JComboBox fldMazeDim = new JComboBox();
		fldMazeDim.setModel(new DefaultComboBoxModel(new String[] {"5", "7", "9", "11", "13", "15", "17"}));
		fldMazeDim.setBounds(213, 32, 64, 24);
		frame.getContentPane().add(fldMazeDim);
		
		final JComboBox fldDragonNum = new JComboBox();
		fldDragonNum.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		fldDragonNum.setBounds(213, 59, 64, 24);
		frame.getContentPane().add(fldDragonNum);

		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playerHasMoved = jogo.moveHeroUp();
				if (playerHasMoved)
					jogo.moveDragon();
				fldDisplay.setText(jogo.toString());

				if (!jogo.getHero().isAlive()) {
					fldDisplay.setText(jogo.toString() + "\nYour hero died. You lost!");
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnUp.setEnabled(false);
					lblNewGame.setVisible(true);
				} 
				else if (jogo.isDone()) {
					fldDisplay.setText(jogo.toString() + "\nYour hero escaped. You won!");
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnUp.setEnabled(false);
					lblNewGame.setVisible(true);
				}
			}
		});
		btnUp.setEnabled(false);
		btnUp.setBounds(444, 223, 117, 25);
		frame.getContentPane().add(btnUp);

		btnLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playerHasMoved = jogo.moveHeroLeft();
				if (playerHasMoved)
					jogo.moveDragon();
				fldDisplay.setText(jogo.toString());

				if (!jogo.getHero().isAlive()) {
					fldDisplay.setText(jogo.toString() + "\nYour hero died. You lost!");
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnUp.setEnabled(false);
					lblNewGame.setVisible(true);
				} 
				else if (jogo.isDone()) {
					fldDisplay.setText(jogo.toString() + "\nYour hero escaped. You won!");
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnUp.setEnabled(false);
					lblNewGame.setVisible(true);
				}
			}
		});
		btnLeft.setEnabled(false);
		btnLeft.setBounds(382, 252, 117, 25);
		frame.getContentPane().add(btnLeft);

		btnRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playerHasMoved = jogo.moveHeroRight();
				if (playerHasMoved)
					jogo.moveDragon();
				fldDisplay.setText(jogo.toString());

				if (!jogo.getHero().isAlive()) {
					fldDisplay.setText(jogo.toString() + "\nYour hero died. You lost!");
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnUp.setEnabled(false);
					lblNewGame.setVisible(true);
				} 
				else if (jogo.isDone()) {
					fldDisplay.setText(jogo.toString() + "\nYour hero escaped. You won!");
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnUp.setEnabled(false);
					lblNewGame.setVisible(true);
				}
			}
		});
		btnRight.setEnabled(false);
		btnRight.setBounds(503, 252, 117, 25);
		frame.getContentPane().add(btnRight);

		btnDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playerHasMoved = jogo.moveHeroDown();
				if (playerHasMoved)
					jogo.moveDragon();
				fldDisplay.setText(jogo.toString());

				if (!jogo.getHero().isAlive()) {
					fldDisplay.setText(jogo.toString() + "\nYour hero died. You lost!");
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnUp.setEnabled(false);
					lblNewGame.setVisible(true);
				} 
				else if (jogo.isDone()) {
					fldDisplay.setText(jogo.toString() + "\nYour hero escaped. You won!");
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
					btnUp.setEnabled(false);
					lblNewGame.setVisible(true);
				}
			}
		});
		btnDown.setEnabled(false);
		btnDown.setBounds(444, 280, 117, 25);
		frame.getContentPane().add(btnDown);

		JButton btnNewMaze = new JButton("Gerar novo labirinto");
		btnNewMaze.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				mb = new MazeBuilder(fldDragonNum.getSelectedIndex()+1);
				int mazeDim[] = {5,7,9,11,13,15,17};
				
				if (fldDragonType.getSelectedIndex() == 0) {
					jogo = new Jogo(mb.buildMaze(mazeDim[fldMazeDim.getSelectedIndex()]));
				} else if (fldDragonType.getSelectedIndex() == 1) {
					jogo = new JogoMovimentoAleatorio(mb.buildMaze(mazeDim[fldMazeDim.getSelectedIndex()]));
				} else if (fldDragonType.getSelectedIndex() == 2) {
					jogo = new JogoDragaoAdormece(mb.buildMaze(mazeDim[fldMazeDim.getSelectedIndex()]));
				}

				fldDisplay.setText(jogo.toString());

				lblNewGame.setVisible(false);
				btnUp.setEnabled(true);
				btnRight.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
			}

		});
		btnNewMaze.setBounds(434, 50, 203, 25);
		frame.getContentPane().add(btnNewMaze);

		JButton btnCloseGame = new JButton("Terminar programa");
		btnCloseGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnCloseGame.setBounds(434, 86, 203, 25);
		frame.getContentPane().add(btnCloseGame);
	}
}
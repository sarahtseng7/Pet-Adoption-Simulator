import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.SwingConstants;

/**
 * Displays the result of a pet-adopter match. Shows match outcome, score
 * impact, coins earned, and provides options to continue, play again, or save
 * the session.
 *
 *
 */
public class MatchEnd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea matchResult;
	private JLabel scoreLabel;
	private JLabel coinLabel;
	private JLabel resultLabel;
	private JButton continueButton;
	private JButton saveButton;
	private JButton playAgainButton;

	/**
	 * Launch the application.
	 *
	 * @param args command-line arguments (unused)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatchEnd frame = new MatchEnd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructs the MatchEnd frame and initializes all UI components. Displays the
	 * last match result from {@code MatchResult.getLastResult()}, including outcome
	 * text, score, and coins earned. Calls {@code setTitleImage()} to determine
	 * which result image and buttons to display based on game state.
	 *
	 * Precondition: {@code MatchResult.getLastResult()} is not null;
	 * {@code GameState.getInstance()} has been initialized. Postcondition: The
	 * MatchEnd window is fully constructed and ready to display. Score and coin
	 * labels reflect the most recent match's impact.
	 *
	 *
	 */
	public MatchEnd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		matchResult = new JTextArea("Match Result");
		matchResult.setEditable(false);
		matchResult.setOpaque(false);
		matchResult.setForeground(new Color(139, 69, 19));
		matchResult.setFont(new Font("Kohinoor Gujarati", Font.PLAIN, 18));
		matchResult.setBounds(113, 70, 345, 180);
		contentPane.add(matchResult);

		scoreLabel = new JLabel("Score: ");
		scoreLabel.setForeground(new Color(139, 69, 19));
		scoreLabel.setFont(new Font("Kohinoor Bangla", Font.PLAIN, 18));
		scoreLabel.setBounds(236, 241, 97, 54);
		contentPane.add(scoreLabel);

		coinLabel = new JLabel("Coins Earned: ");
		coinLabel.setForeground(new Color(139, 69, 19));
		coinLabel.setFont(new Font("Kohinoor Bangla", Font.PLAIN, 18));
		coinLabel.setBounds(199, 286, 224, 16);
		contentPane.add(coinLabel);

		continueButton = new JButton("");
		continueButton.setBounds(210, 339, 150, 54);
		continueButton.setVisible(true);
		setTransparent(continueButton);
		continueButton.setIcon(ImageLoader.loadImage("continue.gif"));
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(continueButton);

		saveButton = new JButton("");
		saveButton.setBounds(455, 362, 89, 54);
		saveButton.setVisible(true);
		setTransparent(saveButton);
		saveButton.setIcon(ImageLoader.loadImage("save.gif"));
		saveButton.addActionListener(new ActionListener() {
			/**
			 * Saves the current game state and last match result to history when the save
			 * button is clicked, then closes the window.
			 *
			 * Precondition: {@code MatchResult.getLastResult()} is not null;
			 * {@code GameState.getInstance()} has valid state. Postcondition: Game state is
			 * written to file via {@code Storage.saveGameState()}; match is appended to
			 * history via {@code Storage.saveMatchToHistory()}; the MatchEnd window is
			 * disposed.
			 *
			 * @param e the action event triggered by clicking the save button
			 */
			public void actionPerformed(ActionEvent e) {
				dispose();
				GameState gs = GameState.getInstance();
				Storage.saveGameState(gs.getCoins(), gs.getHearts(), gs.getEnergy(), gs.getLastSaveTime());
				Storage.saveMatchToHistory(MatchResult.getLastResult().getPet().getName(),
						MatchResult.getLastResult().getAdopter().getName(), MatchResult.getLastResult().getOutcome(),
						MatchResult.getLastResult().getSuccessScore());
			}
		});
		contentPane.add(saveButton);

		resultLabel = new JLabel("");
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setBounds(69, 18, 406, 62);
		contentPane.add(resultLabel);

		int score;
		int coin;
		if (MatchResult.getLastResult() != null) {
			matchResult.setText(MatchResult.getLastResult().toString());
			score = MatchResult.getLastResult().getScoreImpact();
			coin = score * 10;
			scoreLabel.setText("Score: " + score);
			coinLabel.setText("Coins Earned: " + coin);
		}

		playAgainButton = new JButton("Play Again");
		playAgainButton.setBounds(162, 312, 229, 104);
		setTransparent(playAgainButton);
		playAgainButton.setIcon(ImageLoader.loadImage("playAgainButton.gif"));
		playAgainButton.setVisible(false);
		playAgainButton.addActionListener(new ActionListener() {
			/**
			 * Disposes the MatchEnd window and launches a new PetHome session when the play
			 * again button is clicked. If the player has no hearts remaining, a warning
			 * dialog is shown.
			 *
			 * Precondition: {@code GameState.getInstance()} has been initialized.
			 * Postcondition: MatchEnd is disposed; a new {@code PetHome} window is created
			 * and made visible.
			 *
			 * @param e the action event triggered by clicking the play again button
			 */
			public void actionPerformed(ActionEvent e) {
				int numHearts = GameState.getInstance().getHearts();
				if (numHearts == 0) {
					JOptionPane.showMessageDialog(new JFrame(),
							"You are out of Hearts! Buy more or wait until tomorrow!");
				}
				dispose();
				PetHome frame2;
				try {
					frame2 = new PetHome();
					frame2.frame.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(playAgainButton);

		setTitleImage();
	}

	/**
	 * Makes a JButton fully transparent by removing its background, border, content
	 * area fill, and focus indicator.
	 *
	 * Precondition: JButton is not null Postcondition: The button background is
	 * transparent and only icon is shown
	 *
	 * @param o the button to make transparent
	 *
	 */
	public void setTransparent(JButton o) {
		o.setBackground(new Color(255, 255, 255, 0));
		o.setContentAreaFilled(false);
		o.setBorderPainted(false);
		o.setOpaque(false);
		o.setFocusPainted(false);
	}

	/**
	 * Sets the title image and configures button visibility based on the current
	 * match outcome and game over state.
	 *
	 * Precondition: All variables used are initialized (not null) Postcondition:
	 * displays the appropriate outcome image and buttons are shown or hidden
	 * according to game state.
	 *
	 *
	 */
	public void setTitleImage() {
		String outcome = "";
		if (MatchResult.getLastResult() != null) {
			outcome = MatchResult.getLastResult().getOutcome();
		}
		String img = "";
		if (outcome.equals("Successful Match")) {
			img = "success.png";
		} else if (outcome.equals("Return Risk")) {
			img = "risk.png";
		} else if (outcome.equals("Neutral")) {
			img = "neutral.png";
		} else if (GameState.getInstance().getHearts() == 0) {
			img = "lose.png";
		}

		if (GameState.getInstance().isGameOver()) {
			img = "lose.png";
			continueButton.setVisible(false);
			matchResult.setText(GameState.getInstance().getGameOverReason());
			playAgainButton.setVisible(true);
			saveButton.setVisible(false);
			BackButton back = new BackButton(this, contentPane);
			back.setBounds(-50, 330, 200, 130);
		} else {
			playAgainButton.setVisible(false);
		}

		resultLabel.setIcon(ImageLoader.loadImage(img));
	}
}
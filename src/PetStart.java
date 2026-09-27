import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * Creates Start Screen to start game
 * 
 *
 */
public class PetStart extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetStart frame = new PetStart();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
     *
	 */
	public PetStart() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleLabel = new JLabel("");

		titleLabel.setIcon(ImageLoader.loadImage("title4.png"));
		titleLabel.setBounds(197, 51, 484, 118);
		contentPane.add(titleLabel);

		JButton playButton = new JButton("");
		playButton.setBorderPainted(false);
		playButton.setFocusPainted(false);
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setIcon(ImageLoader.loadImage("play.gif"));
		playButton.setFont(new Font("Noteworthy", Font.BOLD, 22));
		playButton.setBounds(334, 391, 246, 92);
		contentPane.add(playButton);
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FileUpload window;
				try {
					window = new FileUpload();
					window.getFrame().setVisible(true);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JLabel titleLabel2 = new JLabel("");
		titleLabel2.setIcon(ImageLoader.loadImage("titleHome.gif"));
		titleLabel2.setBounds(44, 120, 853, 272);
		contentPane.add(titleLabel2);
		titleLabel2.setVisible(true);

	}

}

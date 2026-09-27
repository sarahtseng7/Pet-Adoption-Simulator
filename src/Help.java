import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.SwingConstants;

/** 
 * Displays Help Screen with instructions
 *
 */
public class Help extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Image img;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Help frame = new Help();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Displays Help class screen
     *
	 */
	public Help() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel instructionTitle = new JLabel("");
		instructionTitle.setHorizontalAlignment(SwingConstants.CENTER);
		instructionTitle.setBounds(61, 6, 470, 72);
		instructionTitle.setIcon(ImageLoader.loadImage("instructions.png"));
		contentPane.add(instructionTitle);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setBounds(71, 76, 444, 245);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane text = new JTextPane();
		text.setBackground(new Color(255, 248, 220));
		text.setForeground(new Color(139, 69, 19));
		text.setEditable(false);
		text.setText("Click the Play Button to start your Game! \nSelect a pet and an adopter from each list, read their stats, and click the match button!\nA Successful match earns 10 points, a neutral match earns 5 points, and a risky match earns 2.\n\nClick the Icons on the bottom right to view your Leaderboard, Saved History, and End-of-Day Report.\n\nHearts and Energy are used to play the games. Click the heart or energy icon to learn more!\n\nClick the coin icon or the plus buttons next to the hearts and energy to access our Shop and buy more hearts/energy.");
		text.setBounds(30, 33, 383, 183);
		panel.add(text);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 248, 220));
		panel_1.setBounds(19, 15, 407, 219);
		panel.add(panel_1);
		
		BackButton back = new BackButton(this, contentPane);
		back.setLocation(7, 322);

	}
}

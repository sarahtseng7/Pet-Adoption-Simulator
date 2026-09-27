import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.SwingConstants;

/** 
 * Shows HeartEnergyInfo, displaying information about how to buy hearts and energy
 *
 */
public class HeartEnergyInfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeartEnergyInfo frame = new HeartEnergyInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the Heart Energy screen
     *
	 */
	public HeartEnergyInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel heartImg2 = new JLabel("");
		heartImg2.setBounds(241, 0, 72, 72);
		heartImg2.setIcon(ImageLoader.loadImage("heart2.png"));
		contentPane.add(heartImg2);
		
		JLabel energyImg2 = new JLabel("");
		energyImg2.setIcon(ImageLoader.loadImage("lightning2.png"));
		energyImg2.setBounds(528, 10, 72, 68);
		contentPane.add(energyImg2);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 192, 203));
		panel.setBounds(38, 33, 237, 280);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel heartLabel = new JLabel("");
		heartLabel.setHorizontalAlignment(SwingConstants.CENTER);
		heartLabel.setIcon(ImageLoader.loadImage("heartTitle.png"));
		heartLabel.setBounds(16, 11, 213, 47);
		panel.add(heartLabel);
		
		JTextPane heartText = new JTextPane();
		heartText.setEditable(false);
		heartText.setText("You get 5 hearts a day! Everytime you fail an adoption, you lose a heart. When you reach zero, you will have to wait until the next day to continue playing!");
		heartText.setBounds(17, 63, 192, 171);
		panel.add(heartText);
		
		JPanel energyPanel = new JPanel();
		energyPanel.setBackground(new Color(176, 196, 222));
		energyPanel.setBounds(324, 33, 237, 280);
		contentPane.add(energyPanel);
		energyPanel.setLayout(null);
		
		JLabel energyLabel = new JLabel("");
		energyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		energyLabel.setIcon(ImageLoader.loadImage("energyTitle.png"));
		energyLabel.setBounds(19, 12, 198, 46);
		energyPanel.add(energyLabel);
		
		JTextPane energyText = new JTextPane();
		energyText.setEditable(false);
		energyText.setText("You get 500 energy at the start of each day! Every successful match is -50 energy. Reaching zero means the workers at the shelter are too tired to continue adoptions! I guess you'll have to wait until tomorrow...");
		energyText.setBounds(27, 62, 186, 174);
		energyPanel.add(energyText);
		
		JButton buyHeartButton = new JButton("Buy more?");
		buyHeartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            dispose();
	            Shop frame2 = new Shop();
	            frame2.setVisible(true);
			}
		});
		buyHeartButton.setBounds(129, 240, 90, 29);
		panel.add(buyHeartButton);
		
		JButton buyEnergyButton = new JButton("Buy more?");
		buyEnergyButton.setBounds(130, 238, 90, 29);
		energyPanel.add(buyEnergyButton);
		buyEnergyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            dispose();
	            Shop frame2 = new Shop();
	            frame2.setVisible(true);
			}
		});
		
		BackButton back = new BackButton(this, contentPane);
		back.setLocation(7, 322);
		
		JButton buyButton = new JButton("Buy more?");
		buyButton.setBounds(347, 213, 97, 29);
		contentPane.add(buyButton);
		
		

	}
}

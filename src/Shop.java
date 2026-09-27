import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * Shop screen where the player can purchase hearts and energy using coins.
 * Displays current resource balances and updates GameState on purchase.
 *
 *
 */
public class Shop extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField energyCount;
	private JTextField heartCount;
	private JTextField textField_1;
	private JTextField coinEnergyCount;
	private JTextField coinHeartCount;
	private JLabel heartImg;
	private JLabel energyImg;
	private JLabel coinImg1;
	private JLabel coinImg2;
	private JLabel heartTotalImg;
	private JLabel energyTotalImg;
	private JLabel coinTotalImg;
	private JButton heartsButton;
	private JButton coinButton;
	private JButton energyButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop frame = new Shop();
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
	public Shop() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 420);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel shopLabel = new JLabel("Shop");
		shopLabel.setHorizontalAlignment(SwingConstants.LEFT);
		shopLabel.setIcon(ImageLoader.loadImage("shop.png"));
		shopLabel.setBounds(134, 63, 239, 58);
		contentPane.add(shopLabel);
		
		//heart
		heartImg = new JLabel("");
		heartImg.setIcon(ImageLoader.loadImage("heart.png"));
		heartImg.setBounds(69, 215, 48, 48);
		contentPane.add(heartImg);
		
		coinImg1 = new JLabel("");
		coinImg1.setIcon(ImageLoader.loadImage("coin.png"));
		coinImg1.setBounds(69, 266, 48, 48);
		contentPane.add(coinImg1);
		
		JSlider heartSlider = new JSlider(0,10,0);
		heartSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				heartCount.setText(String.valueOf(heartSlider.getValue()));
				coinHeartCount.setText(String.valueOf(heartSlider.getValue() * 50));
			}
		});
		heartSlider.setBounds(38, 189, 190, 29);
		heartSlider.setMajorTickSpacing(1);
		heartSlider.setSnapToTicks(true);
		contentPane.add(heartSlider);
		
		
		JTextPane heartShopText = new JTextPane();
		heartShopText.setForeground(new Color(139, 69, 19));
		heartShopText.setBackground(new Color(240, 248, 255));
		heartShopText.setEditable(false);
		heartShopText.setText("How many hearts would you like to purchase?");
		heartShopText.setBounds(55, 146, 158, 43);
		contentPane.add(heartShopText);
		
		JButton confirmHeartButton = new JButton("Confirm");
		confirmHeartButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int amount = Integer.parseInt(heartCount.getText());
		        int cost = amount * 50;
		        GameState gs = GameState.getInstance();
		        
		        if (gs.getCoins() >= cost) {
		            gs.addHearts(amount);
		            gs.addCoins(-cost);
		            heartsButton.setText(String.valueOf(gs.getHearts()));
		            coinButton.setText(String.valueOf(gs.getCoins()));
		        } else {
		            JOptionPane.showMessageDialog(null, "Not enough coins!");
		        }
		    }
		});
		confirmHeartButton.setBackground(new Color(218, 165, 32));
		confirmHeartButton.setForeground(new Color(139, 69, 19));
		confirmHeartButton.setBounds(115, 324, 101, 29);
		contentPane.add(confirmHeartButton);
		
		heartCount = new JTextField();
		heartCount.setEditable(false);
		heartCount.setBounds(133, 225, 73, 31);
		contentPane.add(heartCount);
		heartCount.setColumns(10);
		
		coinHeartCount = new JTextField();
		coinHeartCount.setEditable(false);
		coinHeartCount.setColumns(10);
		coinHeartCount.setBounds(134, 269, 74, 34);
		contentPane.add(coinHeartCount);
		
		//energy
		energyImg = new JLabel("");
		energyImg.setIcon(ImageLoader.loadImage("lightning.png"));
		energyImg.setBounds(352, 218, 48, 48);
		contentPane.add(energyImg);
		
		coinImg2 = new JLabel("");
		coinImg2.setIcon(ImageLoader.loadImage("coin.png"));
		coinImg2.setBounds(350, 266, 48, 48);
		contentPane.add(coinImg2);
		
		JSlider energySlider = new JSlider(0,500,0);
		energySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				energyCount.setText(String.valueOf(energySlider.getValue()));
				coinEnergyCount.setText(String.valueOf(energySlider.getValue() * 2));
			}
		});
		energySlider.setBounds(319, 189, 190, 29);
		energySlider.setMajorTickSpacing(50);
		energySlider.setSnapToTicks(true);
		contentPane.add(energySlider);
		
		
		JTextPane energyShopText = new JTextPane();
		energyShopText.setForeground(new Color(139, 69, 19));
		energyShopText.setBackground(new Color(240, 248, 255));
		energyShopText.setEditable(false);
		energyShopText.setText("How many energies would you like to purchase?");
		energyShopText.setBounds(336, 141, 158, 48);
		contentPane.add(energyShopText);
		
		JButton confirmEnergyButton = new JButton("Confirm");
		confirmEnergyButton.setForeground(new Color(139, 69, 19));
		confirmEnergyButton.setBounds(394, 324, 101, 29);
		contentPane.add(confirmEnergyButton);
		confirmEnergyButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int energyAmount = Integer.parseInt(energyCount.getText()); // already * 50, e.g. 50, 100...
		        int cost = Integer.parseInt(coinEnergyCount.getText());     // already correct cost
		        GameState gs = GameState.getInstance();

		        if (gs.getCoins() >= cost) {
		            gs.addEnergy(energyAmount);   // add the already-correct energy amount
		            gs.addCoins(-cost);
		            energyButton.setText(String.valueOf(gs.getEnergy()));
		            coinButton.setText(String.valueOf(gs.getCoins()));
		        } else {
		            JOptionPane.showMessageDialog(null, "Not enough coins!");
		        }
		    }
		});
		
		energyCount = new JTextField();
		energyCount.setEditable(false);
		energyCount.setBounds(412, 230, 73, 26);
		contentPane.add(energyCount);
		energyCount.setColumns(10);
		
		coinEnergyCount = new JTextField();
		coinEnergyCount.setEditable(false);
		coinEnergyCount.setColumns(10);
		coinEnergyCount.setBounds(413, 277, 74, 26);
		contentPane.add(coinEnergyCount);
		
		//top right corner with heart energy count
		energyTotalImg = new JLabel("");
		energyTotalImg.setIcon(ImageLoader.loadImage("lightning.png"));
		energyTotalImg.setBounds(461, 7, 48, 47);
		contentPane.add(energyTotalImg);
		
		heartTotalImg = new JLabel("");
		heartTotalImg.setIcon(ImageLoader.loadImage("heart.png"));
		heartTotalImg.setBounds(377, 1, 48, 48);
		contentPane.add(heartTotalImg);
		
		coinTotalImg = new JLabel("");
		coinTotalImg.setIcon(ImageLoader.loadImage("Coin.png"));
		coinTotalImg.setBounds(274, 1, 48, 48);
		contentPane.add(coinTotalImg);
		
		heartsButton = new JButton(String.valueOf(GameState.getInstance().getHearts()));
		heartsButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		heartsButton.setBounds(405, 15, 61, 27);
		contentPane.add(heartsButton);
		
		coinButton = new JButton(String.valueOf(GameState.getInstance().getCoins()));
		coinButton.setHorizontalAlignment(SwingConstants.RIGHT);
		coinButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		coinButton.setBounds(305, 14, 63, 27);
		contentPane.add(coinButton);
		
		energyButton = new JButton(String.valueOf(GameState.getInstance().getEnergy()));
		energyButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		energyButton.setBounds(476, 14, 68, 29);
		contentPane.add(energyButton);
		
		BackButton back = new BackButton(this, contentPane);
		back.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Refresh PetHome buttons with updated values
		        if (PetHome.getInstance() != null) {
		            PetHome.getInstance().refreshResourceButtons();
		        }
		        dispose(); // just close the Shop window
		    }
		});
		back.setLocation(6, 343);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		        GameState gs = GameState.getInstance();
		        Storage.saveGameState(
		            gs.getCoins(), gs.getHearts(),
		            gs.getEnergy(), gs.getLastSaveTime()
		        );
		    }
		});

	}
}

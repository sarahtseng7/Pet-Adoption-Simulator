import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;

/** displays file Input window for user to input file names
 * checks if file inputted is valid
 * 
 *
 */
public class FileUpload extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField petInput;
	private JButton continueButton;
	private JTextField adopterInput;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileUpload frame = new FileUpload();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the frame and displays the File upload screen
     * @author - Sarah Tseng
	 */
	public FileUpload() throws IOException {
		
		JFrame frame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		adopterInput = new JTextField();
		adopterInput.setBounds(370, 195, 130, 26);
		contentPane.add(adopterInput);
		adopterInput.setColumns(20);
		adopterInput.setBackground(Color.WHITE);
		adopterInput.setOpaque(true);
		
		petInput = new JTextField();
		petInput.setBackground(new Color(255, 255, 255));
		petInput.setBounds(78, 195, 130, 26);
		petInput.setOpaque(true);
		contentPane.add(petInput);
		petInput.setColumns(20);
		
		JLabel fileTitle = new JLabel("");
		fileTitle.setHorizontalAlignment(SwingConstants.CENTER);
		fileTitle.setBounds(61, 6, 470, 72);
		fileTitle.setIcon(ImageLoader.loadImage("fileTitle.png"));
		contentPane.add(fileTitle);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setBounds(35, 85, 226, 189);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane petText = new JTextPane();
		petText.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		petText.setEditable(false);
		petText.setBackground(new Color(255, 248, 220));
		petText.setForeground(new Color(139, 69, 19));
		petText.setText("Please type the name of your pet .csv file below");
		petText.setBounds(24, 20, 180, 146);
		panel.add(petText);

		continueButton = new JButton("");
		continueButton.setBounds(208, 269, 178, 95);
		continueButton.setVisible(true);
		setTransparent(continueButton);
		continueButton.setIcon(ImageLoader.loadImage("continue.gif"));
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkPetFile()) {
					JOptionPane.showMessageDialog(frame, "Please give valid pet file");
				} else if (!checkAdopterFile()) {
					JOptionPane.showMessageDialog(frame, "Please give valid adopter file");
				} else {
					dispose();
					PetHome frame2;
					try {
						frame2 = new PetHome();
					} catch (IOException e1) {
						throw new RuntimeException(e1);
					}
					frame2.frame.setVisible(true);
				}
			}
		});
		
		
		contentPane.add(continueButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(245, 222, 179));
		panel_1.setBounds(324, 85, 226, 189);
		contentPane.add(panel_1);
		
		JTextPane adopterText = new JTextPane();
		adopterText.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		adopterText.setText("Please type the name of your adopter .csv file below");
		adopterText.setForeground(new Color(139, 69, 19));
		adopterText.setEditable(false);
		adopterText.setBackground(new Color(255, 248, 220));
		adopterText.setBounds(24, 20, 180, 147);
		panel_1.add(adopterText);
	}
	
	/**
	 * Makes a JButton fully transparent (no background, border, or fill).
	 *
	 * @param button the button to make transparent
	 * 
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
	 * returns this class
	 * @return FileUpload class
	 *
	 */
	public FileUpload getFrame() {
		return this;
	}
	
	/**
	 * checks if pet file is valid and returns true, else false
	 * Pre-condition: pet input is not null
	 * Post-condition: pet file is set to the user input if pet file is valid. true is returned
	 * @return true if pet file is valid
	 *
	 */
	public boolean checkPetFile() {
		ArrayInitializer a = new ArrayInitializer();
		if (!a.isValidPetFile(petInput.getText()))
			return false;
		ArrayInitializer.setPetFile(petInput.getText());
		return true;
	}
	
	/**
	 * checks if adopter file is valid and returns true, else false
	 * Pre-condition: adopter input is not null
	 * Post-condition: adopter file is set to the user input if adopter file is valid. true is returned
	 * @return true if adopter file is valid
	 *
	 */
	public boolean checkAdopterFile() {
		ArrayInitializer a = new ArrayInitializer();
		if (!a.isValidAdopterFile(adopterInput.getText()))
			return false;
		ArrayInitializer.setAdopterFile(adopterInput.getText());
		return true;
	}
 
}

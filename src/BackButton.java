import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Creates back button that can be called in other classes. 
* Makes class it's called in close when called
*
*/
public class BackButton extends JButton {
	
    /**
    * sets button background transparent so image can be seen clearly
    * Precondition: none
    * Postcondition: background of BackButton is transparent
    *
    */
	public void setTransparent() {
		setBackground(new Color(255, 255, 255, 0));
		setContentAreaFilled(false);
		setBorderPainted(false);
		setOpaque(false);
		setFocusPainted(false);
	}
	
    /**
    * creates back button, with the image back.gif
    * closes frame when BackButton is clicked
    * @param JFrama frame - class the back button is to be shown
    * @param JPanel pane - screen the backbutton is displayed on
    * Precondition: frame and pane are not null
    * Postcondition: back button is displayed
    *
    */
	public BackButton(JFrame frame, JPanel pane) {
		super("");
		setVisible(true);
		setTransparent();
		setIcon(ImageLoader.loadImage("back.gif"));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		setBounds(6, 223, 84, 43);
		pane.add(this);
	}
}

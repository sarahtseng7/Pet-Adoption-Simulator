import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

/**
 * Displays End of Day report with matches played, scores, and coins earned
 * 
 *
 */
public class DayReport extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DayReport frame = new DayReport();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
    * Displays End of Day report with matches played, scores, and coins earned
    * 
    *
    */
    public DayReport() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("End-Of-Day Report");
        lblNewLabel.setIcon(ImageLoader.loadImage("report.png"));
        lblNewLabel.setBounds(6, 6, 421, 41);
        contentPane.add(lblNewLabel);

        BackButton back = new BackButton(this, contentPane);

        // Load today's session stats: {matchesPlayed, coinsEarned, highScore}
        int[] stats = Storage.loadDayReport();

        JLabel matchCountLabel = new JLabel("Matches Played: " + stats[0]);
        matchCountLabel.setForeground(new Color(139, 69, 19));
        matchCountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
        matchCountLabel.setBounds(132, 83, 300, 23);
        contentPane.add(matchCountLabel);

        JLabel coinsLabel = new JLabel("Coins Earned: " + stats[1]);
        coinsLabel.setForeground(new Color(139, 69, 19));
        coinsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
        coinsLabel.setBounds(132, 129, 300, 23);
        contentPane.add(coinsLabel);

        JLabel highScoreLabel = new JLabel("High Score: " + stats[2]);
        highScoreLabel.setForeground(new Color(139, 69, 19));
        highScoreLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
        highScoreLabel.setBounds(131, 174, 300, 23);
        contentPane.add(highScoreLabel);
    }
}
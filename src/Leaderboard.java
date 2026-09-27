import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
/** Displays Leaderboard with top three scores.
 * Shows Score, Time, and Date
 *
 */
public class Leaderboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Leaderboard frame = new Leaderboard();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Leaderboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel leaderBoardLabel = new JLabel("Leaderboard");
        leaderBoardLabel.setIcon(ImageLoader.loadImage("leaderboard.png"));
        leaderBoardLabel.setBounds(49, 16, 389, 49);
        contentPane.add(leaderBoardLabel);

        BackButton back = new BackButton(this, contentPane);

        // Column headers
        JLabel header = new JLabel("Rank    Score    Time       Date");
        header.setForeground(new Color(139, 69, 19));
        header.setFont(new Font("Monospaced", Font.BOLD, 16));
        header.setBounds(55, 77, 357, 20);
        contentPane.add(header);

        // Load top 3 entries: each is String[]{score, timeSeconds, date}
        ArrayList<String[]> entries = Storage.loadLeaderboard();
        String[] medals = {"🥇", "🥈", "🥉"};
        int[] yPositions = {105, 150, 195};

        for (int i = 0; i < entries.size() && i < 3; i++) {
            String[] entry = entries.get(i);
            int score       = Integer.parseInt(entry[0]);
            int timeSecs    = Integer.parseInt(entry[1]);
            String date     = entry[2];

            // Format time as mm:ss
            String time = String.format("%02d:%02d", timeSecs / 60, timeSecs % 60);

            String text = String.format("%-10s  %-7d %-8s %s", medals[i], score, time, date);
            JLabel entryLabel = new JLabel(text);
            entryLabel.setForeground(new Color(139, 69, 19));
            entryLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
            entryLabel.setBounds(69, yPositions[i], 380, 25);
            contentPane.add(entryLabel);
        }

        if (entries.isEmpty()) {
            JLabel emptyLabel = new JLabel("No scores yet — play a round!");
            emptyLabel.setForeground(new Color(139, 69, 19));
            emptyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
            emptyLabel.setBounds(100, 130, 280, 25);
            contentPane.add(emptyLabel);
        }
    }
}
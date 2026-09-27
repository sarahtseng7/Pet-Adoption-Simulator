import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;

/** Displays History window with all saved matches by date added
 * shows pet name, adopter name, score, and compatibility, and total matches played
 *
 */
public class History extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    History frame = new History();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** Displays History window with all saved matches by date added
    * shows pet name, adopter name, score, and compatibility, and total matches played
    *
    */
    public History() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Saved History");
        titleLabel.setIcon(ImageLoader.loadImage("history.png"));
        titleLabel.setBounds(25, 18, 390, 52);
        contentPane.add(titleLabel);

        BackButton back = new BackButton(this, contentPane);

        // Load all-time stats: {totalMatches, totalCoins, highScore, highestStreak}
        int[] stats = Storage.loadAllTimeStats();
        

        JLabel matchCountLabel = new JLabel("Total Matches Played: " + stats[0]);
        matchCountLabel.setForeground(new Color(139, 69, 19));
        matchCountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        matchCountLabel.setBounds(232, 231, 250, 23);
        contentPane.add(matchCountLabel);

        // Load and display saved match sessions in sorted order
        ArrayList<String[]> history = Storage.loadHistory();
        history = CompatibilityEngine.historySort(history);

        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setOpaque(true);
        historyArea.setBackground(new Color(245, 222, 179));
        historyArea.setForeground(new Color(139, 69, 19));
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        if (history.isEmpty()) {
            historyArea.setText("No saved sessions yet.\nFinish a game and press Save Session to record it.");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-10s %-10s %-18s %-7s%n",
                    "Pet", "Adopter", "Outcome", "Compatility"));
            sb.append("-".repeat(62) + "\n");
            for (String[] entry : history) {
                    sb.append(String.format("%-10s %-10s %-18s %-7s%n",
                            entry[0], entry[1], entry[2], entry[3]));
            }
            historyArea.setText(sb.toString());
        }

        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBounds(25, 73, 390, 150);
        scrollPane.getViewport().setBackground(new Color(245, 222, 179));
        contentPane.add(scrollPane);
    }
    
    
}
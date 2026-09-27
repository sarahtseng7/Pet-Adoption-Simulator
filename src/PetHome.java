import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Main screen for the Pet Adoption Matchmaker application.
 * Displays a list of pets and adopters, allows the user to browse,
 * search, sort, and match a pet with an adopter.
 *
 *
 */
public class PetHome {

	JFrame frame;

	// Scroll panes
	private JScrollPane petScroll;
	private JScrollPane adopterScroll;

	// Lists
	private JList<String> petList;
	private JList<String> adopterList;

	// Labels
	private JLabel titleLabel;
	private JLabel petImg;
	private JLabel adopterImg;
	private JLabel scoreLabel;
	private JLabel heartImg;
	private JLabel coinImg;
	private JLabel energyImg;
	private JLabel adopterBorder;
	private JLabel petLabel;
	private JLabel adopterLabel;
	private JLabel timeLabel;
	private JLabel adopterSearchLabel;

	// Buttons
	private JButton addHeartButton;
	private JButton playButton;
	private JButton petSortButton;
	private JButton heartsButton;
	private JButton coinButton;
	private JButton energyButton;
	private static PetHome instance;

	// Panels
	private JPanel petPanel;
	private JPanel adopterPanel;

	// Text components
	private JTextPane adopterDetails;
	private JTextPane petDetails;
	private JTextField petSearchField;
	private JTextField adopterSearchField;

	// Data
	private ArrayList<Pet> petArray;
	private ArrayList<Adopter> adopterArray;
	private DefaultListModel<String> petModel;
	private DefaultListModel<String> adopterModel;
	private int petIdx;
	private int adopterIdx;

	// Timer
	private Timer gameTimer;
	private int elapsedSeconds = 0;

	// -------------------------------------------------------------------------

	/**
	 * Launch the application.
	 *
	 * @param args command-line arguments (unused)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetHome window = new PetHome();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * static method that keeps PetHome info saved
	 */
	public static PetHome getInstance() {
	    return instance;
	}


	/**
	 * Constructs the PetHome window.
	 * Loads pet and adopter data from CSV files and initializes the UI.
	 * @throws IOException 
	 */
	public PetHome() throws IOException {
		instance = this;
		Storage.loadGameState();
	    GameState.getInstance().checkDailyRefresh();
	    petArray    = new ArrayInitializer().petArrayInitialize();
	    adopterArray = new ArrayInitializer().adopterArrayInitialize();
	    initialize();
	}

	// -------------------------------------------------------------------------

	/**
	 * Makes a JButton fully transparent (no background, border, or fill).
	 *
	 * @param button the button to make transparent
	 */
	public void setTransparent(JButton button) {
		button.setBackground(new Color(255, 255, 255, 0));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setFocusPainted(false);
	}

	

	/**
	 * Starts the game timer. Updates {@code timeLabel} every second.
	 */
	private void startTimer() {
		elapsedSeconds = 0;
		gameTimer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elapsedSeconds++;
				timeLabel.setText("Time: " + elapsedSeconds + "s");
			}
		});
		gameTimer.start();
	}
	
	/**
	 * Stops the game timer.
	 *
	 *
	 */
	public void stopTimer() {
	    if (gameTimer != null) {
	        gameTimer.stop();
	    }
	}

	/**
	 * Returns elapsed seconds for leaderboard saving.
	 *
	 *
	 */
	public int getElapsedSeconds() {
	    return elapsedSeconds;
	}

	// -------------------------------------------------------------------------

	/**
	 * Initializes and lays out all UI components in the frame.
	 *
	 *
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		// --- Model arrayList setup
		adopterModel = new DefaultListModel<>();
		for (Adopter a : adopterArray) {
			adopterModel.addElement(a.getName());
		}
		petModel = new DefaultListModel<>();
		for (Pet p : petArray) {
			petModel.addElement(p.getName());
		}
		
		
		// --- Frame setup ---
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 248, 255));
		frame.setBounds(100, 100, 880, 650);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		LineBorder roundedBorder = new LineBorder(new Color(145, 55, 14), 3, true);

		// =============================================================
		// TITLE
		// =============================================================
		titleLabel = new JLabel("Pet Adoption Matchmaker");
		titleLabel.setIcon(ImageLoader.loadImage("title6.png"));
		titleLabel.setFont(new Font("Yuanti SC", Font.BOLD, 30));
		titleLabel.setBounds(-18, 8, 542, 48);
		frame.getContentPane().add(titleLabel);

		// =============================================================
		// TOP BAR: Coins, Hearts, Energy
		// =============================================================
		
		// Add heart button — opens Shop
				addHeartButton = new JButton("");
				addHeartButton.setIcon(ImageLoader.loadImage("add.png"));
				setTransparent(addHeartButton);
				addHeartButton.setBounds(730, 21, 30, 32);
				addHeartButton.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        int result = JOptionPane.showConfirmDialog(frame, "Go to Shop?",
				                "Confirm", JOptionPane.OK_CANCEL_OPTION);
				        if (result == JOptionPane.OK_OPTION) {
				            Shop shop = new Shop();
				            shop.setVisible(true);
				        }
				        // Remove frame.dispose() — keep PetHome alive
				    }
				});
				frame.getContentPane().add(addHeartButton);

				// Add energy button — opens Shop
				JButton addEnergyButton = new JButton("");
				addEnergyButton.setIcon(ImageLoader.loadImage("add.png"));
				setTransparent(addEnergyButton);
				addEnergyButton.setBounds(827, 23, 30, 32);
				addEnergyButton.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        int result = JOptionPane.showConfirmDialog(frame, "Go to Shop?",
				                "Confirm", JOptionPane.OK_CANCEL_OPTION);
				        if (result == JOptionPane.OK_OPTION) {
				            Shop shop = new Shop();
				            shop.setVisible(true);
				        }
				        // Remove frame.dispose() — keep PetHome alive
				    }
				});
				frame.getContentPane().add(addEnergyButton);
		
		coinImg = new JLabel("");
		coinImg.setIcon(ImageLoader.loadImage("Coin.png"));
		coinImg.setBounds(546, 12, 48, 48);
		frame.getContentPane().add(coinImg);

		heartImg = new JLabel("");
		heartImg.setIcon(ImageLoader.loadImage("heart.png"));
		heartImg.setBounds(661, 10, 48, 48);
		frame.getContentPane().add(heartImg);

		energyImg = new JLabel("");
		energyImg.setIcon(ImageLoader.loadImage("lightning.png"));
		energyImg.setBounds(761, 16, 48, 47);
		frame.getContentPane().add(energyImg);

		// Coin button — shows balance, opens Shop on click
		coinButton = new JButton(String.valueOf(GameState.getInstance().getCoins()));
		coinButton.setHorizontalAlignment(SwingConstants.RIGHT);
		coinButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		coinButton.setBounds(578, 24, 63, 27);
		coinButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int result = JOptionPane.showConfirmDialog(frame, "Go to Shop?",
		                "Confirm", JOptionPane.OK_CANCEL_OPTION);
		        if (result == JOptionPane.OK_OPTION) {
		            Shop shop = new Shop();
		            shop.setVisible(true);
		        }
		        // Remove frame.dispose() — keep PetHome alive
		    }
		});
		frame.getContentPane().add(coinButton);

		// Hearts button — shows balance, opens HeartEnergyInfo on click
		heartsButton = new JButton(String.valueOf(GameState.getInstance().getHearts()));
		heartsButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		heartsButton.setBounds(686, 24, 61, 27);
		heartsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame, "View your Hearts?",
						"Confirm", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					new HeartEnergyInfo().setVisible(true);
				}
			}
		});
		frame.getContentPane().add(heartsButton);

		// Energy button — shows balance, opens HeartEnergyInfo on click
		energyButton = new JButton(String.valueOf(GameState.getInstance().getEnergy()));
		energyButton.setBounds(774, 25, 80, 29);
		energyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame, "View your Energy?",
						"Confirm", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					new HeartEnergyInfo().setVisible(true);
				}
			}
		});
		frame.getContentPane().add(energyButton);

		

		// =============================================================
		// SCORE & TIME
		// =============================================================
		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setForeground(new Color(139, 69, 19));
		scoreLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		scoreLabel.setBounds(376, 340, 101, 24);
		scoreLabel.setVisible(false);
		frame.getContentPane().add(scoreLabel);
		
		timeLabel = new JLabel("Time: 0s");
		timeLabel.setForeground(new Color(139, 69, 19));
		timeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		timeLabel.setBounds(373, 374, 110, 24);
		timeLabel.setVisible(false);
		frame.getContentPane().add(timeLabel);

		// =============================================================
		// PLAY & MATCH BUTTONS
		// =============================================================
		JButton matchButton = new JButton("");
		matchButton.setVisible(false);
		matchButton.setFont(new Font("Galvji", Font.BOLD, 21));
		matchButton.setBounds(338, 219, 231, 109);
		setTransparent(matchButton);
		matchButton.setIcon(ImageLoader.loadImage("match.gif"));
		matchButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int petIndex     = petList.getSelectedIndex();
		        int adopterIndex = adopterList.getSelectedIndex();

		        if (petIndex == -1 || adopterIndex == -1) {
		            JOptionPane.showMessageDialog(frame, "Please select a pet AND an adopter first!");
		            return;
		        }

		        // Run the match
		        MatchResult result = MatchResult.createMatch(
		            petArray.get(petIndex), adopterArray.get(adopterIndex));

		        GameState gs = GameState.getInstance();

		        // Track return risks
		        if (result.getOutcome().equals("Return Risk")) {
		            gs.incrementReturnRiskCount();
		        }

		        // Apply score and coins
		        gs.addScore(result.getScoreImpact());
		        int coinsEarned = result.getScoreImpact() * 10;
		        gs.addCoins(coinsEarned);

		        // Update streak
		        if (result.getOutcome().equals("Successful Match")) {
		            gs.setStreak(gs.getStreak() + 1);
		            if (gs.getStreak() > gs.getHighestStreak()) {
		                gs.setHighestStreak(gs.getStreak());
		            }
		        } else {
		            gs.setStreak(0);
		        }

		        // Update all-time and today stats
		        gs.setTotalMatches(gs.getTotalMatches() + 1);
		        gs.setTotalCoins(gs.getTotalCoins() + coinsEarned);
		        gs.setMatchesToday(gs.getMatchesToday() + 1);
		        gs.setCoinsToday(gs.getCoinsToday() + coinsEarned);
		        if (gs.getScore() > gs.getHighScoreToday()) {
		            gs.setHighScoreToday(gs.getScore());
		        }

		        Storage.saveAllTimeStats(
		            gs.getTotalMatches(),
		            gs.getTotalCoins(),
		            Math.max(Storage.loadAllTimeStats()[2], gs.getScore()),
		            gs.getHighestStreak()
		        );
		        Storage.saveDayReport(
		            gs.getMatchesToday(),
		            gs.getCoinsToday(),
		            gs.getHighScoreToday()
		        );

		        // Deduct 50 energy
		        gs.addEnergy(-50);

		        // Update display
		        scoreLabel.setText("Score: " + gs.getScore());
		        heartsButton.setText(String.valueOf(gs.getHearts()));
		        energyButton.setText(String.valueOf(gs.getEnergy()));
		        coinButton.setText(String.valueOf(gs.getCoins()));

		        // Remove matched pet and adopter from lists
		        petModel.remove(petIndex);
		        adopterModel.remove(adopterIndex);
		        petArray.remove(petIndex);
		        adopterArray.remove(adopterIndex);

		        Storage.saveGameState(
		            gs.getCoins(), gs.getHearts(),
		            gs.getEnergy(), gs.getLastSaveTime()
		        );

		        // Check loss conditions: 1 return risks, no hearts, or no energy
		        boolean lostByRisk   = gs.getReturnRiskCount() >= 1;
		        boolean lostByEnergy = gs.getEnergy() <= 0;

		        if (lostByRisk || lostByEnergy) {
		            stopTimer();
		            gs.setGameOver(true);

		            if (lostByRisk) {
		                gs.setGameOverReason("You got a Return Risk!\nthe shelter is overwhelmed!");
		            } else {
		                gs.setGameOverReason("You ran out of Energy!");
		            }
		        }

		        new MatchEnd().setVisible(true);
		    }
		});
		frame.getContentPane().add(matchButton);
		
		JLabel playLabel = new JLabel("Please press the Play Button to start!");
		playLabel.setForeground(new Color(139, 69, 19));
		playLabel.setFont(new Font("Yuanti SC", Font.BOLD, 30));
		playLabel.setBounds(200, 325, 542, 48);
		frame.getContentPane().add(playLabel);
		frame.getContentPane().setComponentZOrder(playLabel, 0);
		
		playButton = new JButton("");
		playButton.setBounds(353, 225, 178, 95);
		setTransparent(playButton);
		playButton.setIcon(ImageLoader.loadImage("play.gif"));
		playButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		        // Reset game-over state for new session
		        GameState.getInstance().setGameOver(false);
		        GameState.getInstance().setGameOverReason("");
		        GameState.getInstance().resetReturnRiskCount();

		        if (!GameState.getInstance().addHearts(-1)) {
                    JOptionPane.showMessageDialog(frame, "You're out of Hearts! Please wait until Tomorrow or buy more Hearts from the Shop");
		        } else {
		        	startTimer();
		        	playButton.setVisible(false);
			        matchButton.setVisible(true);
			        matchButton.setEnabled(true);
			        playLabel.setVisible(false);
			        scoreLabel.setVisible(true);
			        timeLabel.setVisible(true);
		        }
                heartsButton.setText(String.valueOf(GameState.getInstance().getHearts()));
		    }
		});
		frame.getContentPane().add(playButton);

		// =============================================================
		// BOTTOM PANEL: Leaderboard, History, Day Report
		// =============================================================
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(653, 546, 221, 70);
		frame.getContentPane().add(panel);

		JButton leaderboardButton = new JButton("");
		leaderboardButton.setIcon(ImageLoader.loadImage("leaderboard1.png"));
		leaderboardButton.setBackground(new Color(245, 222, 179));
		leaderboardButton.setForeground(new Color(139, 69, 19));
		leaderboardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Leaderboard().setVisible(true);
			}
		});
		panel.add(leaderboardButton);

		JButton historyButton = new JButton("");
		historyButton.setIcon(ImageLoader.loadImage("history1.png"));
		historyButton.setBackground(new Color(245, 222, 179));
		historyButton.setForeground(new Color(139, 69, 19));
		historyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new History().setVisible(true);
			}
		});
		panel.add(historyButton);

		JButton dayReportButton = new JButton("");
		dayReportButton.setIcon(ImageLoader.loadImage("report1.png"));
		dayReportButton.setBackground(new Color(245, 222, 79));
		dayReportButton.setForeground(new Color(139, 69, 19));
		dayReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DayReport().setVisible(true);
			}
		});
		panel.add(dayReportButton);

		// =============================================================
		// PET PANEL
		// =============================================================
		petPanel = new JPanel();
		petPanel.setBackground(new Color(255, 248, 220));
		petPanel.setBounds(26, 81, 321, 435);
		petPanel.setLayout(null);
		frame.getContentPane().add(petPanel);

		petLabel = new JLabel("Pet List");
		petLabel.setIcon(ImageLoader.loadImage("petAdopterTitle2.png"));
		petLabel.setForeground(new Color(139, 69, 19));
		petLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		petLabel.setBounds(71, 6, 139, 45);
		petPanel.add(petLabel);
		

		// Pet image display
		
		JLabel petBorder = new JLabel("");
		petBorder.setBounds(147, 60, 150, 150);
		petBorder.setForeground(new Color(245, 222, 179));
		petBorder.setBackground(new Color(245, 222, 179));
		petBorder.setOpaque(true);
		petBorder.setBorder(roundedBorder);
		petPanel.add(petBorder);

		petImg = new JLabel();
		petImg.setBounds(158, 69, 130, 125);
		petPanel.add(petImg);
		petPanel.setComponentZOrder(petImg, 1);
		
		// Pet scroll list
		petScroll = new JScrollPane();
		petScroll.setBounds(27, 57, 102, 150);
		petScroll.setBackground(new Color(255, 255, 224));
		petScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		petScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		
		petList = new JList<>(petModel);
		petList.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		petList.setForeground(new Color(139, 69, 19));
		petList.setBackground(new Color(245, 222, 179));
		petList.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            petIdx = petList.getSelectedIndex();
		            if (petIdx != -1) {
		                Pet selected = petArray.get(petIdx);
		                petDetails.setText(selected.display());
						petImg.setIcon(ImageLoader.loadPet(selected));
		            }
		        }
		    }
		});

		petScroll.setViewportView(petList);
		petPanel.add(petScroll);

		// Pet details pane
		petDetails = new JTextPane();
		petDetails.setEditable(false);
		petDetails.setForeground(new Color(139, 69, 19));
		petDetails.setBackground(new Color(245, 222, 179));
		petDetails.setText("Traits:\n\nNeeds:\n\nConstraints:");
		petDetails.setBounds(26, 255, 272, 159);
		petPanel.add(petDetails);

		// Pet sort button
		petSortButton = new JButton("Sort");
		petSortButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		petSortButton.setForeground(new Color(139, 69, 19));
		petSortButton.setBounds(26, 219, 68, 29);
		
		petSortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				petArray = CompatibilityEngine.urgencySort(petArray);
				displayPetList();
			}
		});
		petPanel.add(petSortButton);

		// Pet search field
		JLabel petSearchLabel = new JLabel("Search: ");
		petSearchLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		petSearchLabel.setForeground(new Color(139, 69, 19));
		petSearchLabel.setBounds(108, 222, 61, 16);
		petPanel.add(petSearchLabel);

		petSearchField = new JTextField();
		petSearchField.setColumns(10);
		petSearchField.setBounds(168, 219, 130, 26);
		petSearchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pet p = CompatibilityEngine.findPet(petArray, 0, petSearchField.getText());
				petSearchField.setText("");
				if(p == null) {
					petDetails.setText("Pet not found. Try again!");
				} else {
					petDetails.setText(p.display());
					petList.setSelectedValue(p, true);
					int idx = petArray.indexOf(p);
			        petList.setSelectedIndex(idx);
					petImg.setIcon(ImageLoader.loadPet(p));
				}				
			}
		});
		petPanel.add(petSearchField);

		// =============================================================
		// ADOPTER PANEL
		// =============================================================
		adopterPanel = new JPanel();
		adopterPanel.setLayout(null);
		adopterPanel.setBackground(new Color(255, 248, 220));
		adopterPanel.setBounds(534, 81, 321, 435);
		frame.getContentPane().add(adopterPanel);

		adopterLabel = new JLabel("Adopter List");
		adopterLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		adopterLabel.setIcon(ImageLoader.loadImage("petAdopterTitle2.png"));
		adopterLabel.setForeground(new Color(139, 69, 19));
		adopterLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		adopterLabel.setBounds(-55, 6, 376, 48);
		adopterPanel.add(adopterLabel);

		// Adopter image display
		adopterBorder = new JLabel("");
		adopterBorder.setBounds(26, 59, 150, 150);
		adopterBorder.setOpaque(true);
		adopterBorder.setForeground(new Color(139, 69, 19));
		adopterBorder.setBackground(new Color(245, 222, 179));
		adopterBorder.setBorder(roundedBorder);
		adopterPanel.add(adopterBorder);

		adopterImg = new JLabel();
		adopterImg.setBounds(36, 71, 130, 125);
		adopterPanel.add(adopterImg);
		adopterPanel.setComponentZOrder(adopterImg, 1);

		// Adopter scroll list
		adopterScroll = new JScrollPane();
		adopterScroll.setBounds(196, 59, 102, 150);
		adopterScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		adopterScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		
		adopterList = new JList<>(adopterModel);
		adopterList.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		adopterList.setForeground(new Color(139, 69, 19));
		adopterList.setBackground(new Color(245, 222, 179));
		adopterList.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            adopterIdx = adopterList.getSelectedIndex();
		            if (adopterIdx != -1) {
		                Adopter selected = adopterArray.get(adopterIdx);
		                adopterDetails.setText(selected.toString());
						adopterImg.setIcon(ImageLoader.loadAdopter(selected));
		            }
		        }
		    }
		});
		adopterScroll.setViewportView(adopterList);
		adopterPanel.add(adopterScroll);

		// Adopter details pane
		adopterDetails = new JTextPane();
		adopterDetails.setEditable(false);
		adopterDetails.setText("Traits:\n\nNeeds:\n\nConstraints:");
		adopterDetails.setForeground(new Color(139, 69, 19));
		adopterDetails.setBackground(new Color(245, 222, 179));
		adopterDetails.setBounds(26, 259, 272, 154);
		adopterPanel.add(adopterDetails);

		// Adopter search field
		adopterSearchLabel = new JLabel("Search: ");
		adopterSearchLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		adopterSearchLabel.setForeground(new Color(139, 69, 19));
		adopterSearchLabel.setBounds(108, 224, 61, 16);
		adopterPanel.add(adopterSearchLabel);

		adopterSearchField = new JTextField();
		adopterSearchField.setColumns(10);
		adopterSearchField.setBounds(168, 221, 130, 26);
		adopterSearchField.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Adopter a = CompatibilityEngine.findAdopter(adopterArray, 0, adopterSearchField.getText());
		        adopterSearchField.setText("");
		        if (a == null) {
		        	adopterDetails.setText("Adopter not found. Try again!");
		        	return;
		        } else {
		        	adopterDetails.setText(a.toString());
			        int idx = adopterArray.indexOf(a);
			        adopterList.setSelectedIndex(idx);
			        adopterImg.setIcon(ImageLoader.loadAdopter(a));
		        }
		    }
		});
		adopterPanel.add(adopterSearchField);
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help frame2 = new Help();
				frame2.setVisible(true);
			}
		});
		helpButton.setForeground(new Color(139, 69, 19));
		helpButton.setBounds(6, 587, 63, 29);
		frame.getContentPane().add(helpButton);


		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		        GameState gs = GameState.getInstance();
		        Storage.saveGameState(
		            gs.getCoins(), gs.getHearts(),
		            gs.getEnergy(), gs.getLastSaveTime()
		        );
		        Storage.saveLeaderboardEntry(gs.getScore(), elapsedSeconds);
		    }
		});
		
	}

	// -------------------------------------------------------------------------

	/**
	 * Refreshes the pet list display after a sort or filter operation.
	 *
	 *
	 */
	public void displayPetList() {
	    petModel.clear();
	    for (Pet p : petArray) {
	        petModel.addElement(p.getName());
	    }
	}
	
	/**
	 * Refreshes resource buttons like heart, energy, coin
	 *
	 *
	 */
	public void refreshResourceButtons() {
	    heartsButton.setText(String.valueOf(GameState.getInstance().getHearts()));
	    energyButton.setText(String.valueOf(GameState.getInstance().getEnergy()));
	    coinButton.setText(String.valueOf(GameState.getInstance().getCoins()));
	}
}
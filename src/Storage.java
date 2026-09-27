import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles all file saving and loading
 * for the Pet Adoption Matchmaker game.
 *
 * This includes:
 * match history,
 * leaderboard data,
 * daily reports,
 * and game state information.
 *
 *
 */
public class Storage {

    // =========================================================
    // FILE CONSTANTS
    // =========================================================

    private static final String HISTORY_FILE = "shelter_history.csv";
    private static final String LEADERBOARD_FILE = "leaderboard.csv";
    private static final String GAMESTATE_FILE = "gamestate.csv";
    private static final String DAYREPORT_FILE = "match_log.csv";

    // =========================================================
    // MATCH HISTORY
    // =========================================================

    /**
     * Saves a completed match into the history file.
     *
     * Preconditions: none of the parameters are null
     *
     * Postconditions:A new line is added to the history CSV file
     *
     * @param petName the matched pet's name
     * @param adopterName the adopter's name
     * @param outcome the match outcome
     * @param score the compatibility score
     *
     *
     */
    public static void saveMatchToHistory(
            String petName,
            String adopterName,
            String outcome,
            double score) {

        try {

            FileWriter fileWriter =
                    new FileWriter(HISTORY_FILE, true);

            PrintWriter printWriter =
                    new PrintWriter(fileWriter);

            printWriter.println(
                    petName + ","
                    + adopterName + ","
                    + outcome + ","
                    + score
            );

            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all match history data from the history file.
     *
     * Preconditions:The history file may or may not exist
     *
     * Postconditions:Returns an ArrayList containing all match history data
     *
     * @return an ArrayList of String arrays containing history data
     *
     *
     */
    public static ArrayList<String[]> loadHistory() {

        ArrayList<String[]> history =
                new ArrayList<String[]>();

        File file = new File(HISTORY_FILE);

        if (!file.exists()) {
            return history;
        }

        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine().trim();

                if (!line.isEmpty()) {

                    String[] data = line.split(",");
                    history.add(data);
                }
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return history;
    }

    // =========================================================
    // ALL-TIME STATS
    // =========================================================

    /**
     * Saves all-time player statistics.
     *
     * Preconditions:All parameters are greater than or equal to 0
     *
     * Postconditions:Statistics file is overwritten with updated values
     *
     * @param totalMatches total matches completed
     * @param totalCoins total coins earned
     * @param highScore highest score achieved
     * @param highestStreak highest streak achieved
     *
     *
     */
    public static void saveAllTimeStats(
            int totalMatches,
            int totalCoins,
            int highScore,
            int highestStreak) {

        try {

            FileWriter fileWriter =
                    new FileWriter(HISTORY_FILE + ".stats", false);

            PrintWriter printWriter =
                    new PrintWriter(fileWriter);

            printWriter.println(
                    totalMatches + ","
                    + totalCoins + ","
                    + highScore + ","
                    + highestStreak
            );

            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all-time statistics.
     *
     * Preconditions:Statistics file may or may not exist
     *
     * Postconditions:Returns loaded statistics
     *
     * @return int array containing:
     * totalMatches,
     * totalCoins,
     * highScore,
     * highestStreak
     *
     *
     */
    public static int[] loadAllTimeStats() {

        int[] stats = {0, 0, 0, 0};

        File file = new File(HISTORY_FILE + ".stats");

        if (!file.exists()) {
            return stats;
        }

        try {

            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                String[] data = line.split(",");

                for (int i = 0; i < data.length && i < 4; i++) {

                    stats[i] =
                            Integer.parseInt(data[i].trim());
                }
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stats;
    }

    // =========================================================
    // DAY REPORT
    // =========================================================

    /**
     * Saves the current day's report data.
     *
     * Preconditions: All parameters are greater than or equal to 0
     *
     * Postconditions: Day report file is updated
     *
     * @param matchesPlayed matches played today
     * @param coinsEarned coins earned today
     * @param highScore highest score today
     *
     *
     */
    public static void saveDayReport(
            int matchesPlayed,
            int coinsEarned,
            int highScore) {

        try {

            FileWriter fileWriter =
                    new FileWriter(DAYREPORT_FILE, false);

            PrintWriter printWriter =
                    new PrintWriter(fileWriter);

            printWriter.println(
                    matchesPlayed + ","
                    + coinsEarned + ","
                    + highScore
            );

            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the saved daily report.
     *
     * Preconditions:
     * - Day report file may or may not exist
     *
     * Postconditions:
     * - Returns saved daily report statistics
     *
     * @return int array containing:
     * matchesPlayed,
     * coinsEarned,
     * highScore
     *
     *
     */
    public static int[] loadDayReport() {

        int[] stats = {0, 0, 0};

        File file = new File(DAYREPORT_FILE);

        if (!file.exists()) {
            return stats;
        }

        try {

            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                String[] data = line.split(",");

                for (int i = 0; i < data.length && i < 3; i++) {

                    stats[i] =
                            Integer.parseInt(data[i].trim());
                }
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stats;
    }

    // =========================================================
    // LEADERBOARD
    // =========================================================

    /**
     * Saves a leaderboard entry and keeps only the top 3 scores.
     *
     * Preconditions: score and timeSeconds are greater than or equal to 0
     *
     * Postconditions: Leaderboard file is updated
     * Top 3 scores are preserved
     *
     * @param score player score
     * @param timeSeconds completion time
     *
     *
     */
    public static void saveLeaderboardEntry(
            int score,
            int timeSeconds) {

        ArrayList<String[]> entries =
                loadLeaderboard();

        String date =
                LocalDate.now().toString();

        String[] newEntry = {
                String.valueOf(score),
                String.valueOf(timeSeconds),
                date
        };

        entries.add(newEntry);

        entries.sort((entry1, entry2) -> {

            int score1 =
                    Integer.parseInt(entry1[0]);

            int score2 =
                    Integer.parseInt(entry2[0]);

            return score2 - score1;
        });

        if (entries.size() > 3) {

            ArrayList<String[]> topEntries =
                    new ArrayList<String[]>();

            for (int i = 0; i < 3; i++) {
                topEntries.add(entries.get(i));
            }

            entries = topEntries;
        }

        try {

            FileWriter fileWriter =
                    new FileWriter(LEADERBOARD_FILE, false);

            PrintWriter printWriter =
                    new PrintWriter(fileWriter);

            for (String[] entry : entries) {

                printWriter.println(
                        entry[0] + ","
                        + entry[1] + ","
                        + entry[2]
                );
            }

            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads leaderboard data sorted by highest score first.
     *
     * Preconditions: Leaderboard file may or may not exist
     * Postconditions: Returns sorted leaderboard entries
     *
     * @return ArrayList of leaderboard entries
     *
     *
     */
    public static ArrayList<String[]> loadLeaderboard() {

        ArrayList<String[]> entries =
                new ArrayList<String[]>();

        File file = new File(LEADERBOARD_FILE);

        if (!file.exists()) {
            return entries;
        }

        try {

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String line =
                        scanner.nextLine().trim();

                if (!line.isEmpty()) {

                    String[] data =
                            line.split(",");

                    entries.add(data);
                }
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        entries.sort((entry1, entry2) -> {

            int score1 =
                    Integer.parseInt(entry1[0]);

            int score2 =
                    Integer.parseInt(entry2[0]);

            return score2 - score1;
        });

        return entries;
    }

    // =========================================================
    // GAME STATE
    // =========================================================

    /**
     * Saves the current game state.
     *
     * Preconditions: coins, hearts, and energy are valid integers
     *
     * Postconditions: Game state file is updated
     *
     * @param coins current coin amount
     * @param hearts current heart amount
     * @param energy current energy amount
     * @param lastSaveTime time of last save
     *
     *
     */
    public static void saveGameState(
            int coins,
            int hearts,
            int energy,
            long lastSaveTime) {

        GameState gameState =
                GameState.getInstance();

        try {

            FileWriter fileWriter =
                    new FileWriter(GAMESTATE_FILE, false);

            PrintWriter printWriter =
                    new PrintWriter(fileWriter);

            printWriter.println(
                    coins + ","
                    + hearts + ","
                    + energy + ","
                    + lastSaveTime + ","
                    + gameState.getStreak() + ","
                    + gameState.getHighestStreak() + ","
                    + gameState.getTotalMatches() + ","
                    + gameState.getTotalCoins()
            );

            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the saved game state.
     *
     * Preconditions:
     * - Game state file may or may not exist
     *
     * Postconditions:
     * - GameState singleton is updated
     *
     *
     */
    public static void loadGameState() {

        File file = new File(GAMESTATE_FILE);

        if (!file.exists()) {
            return;
        }

        try {

            Scanner scanner =
                    new Scanner(file);

            if (scanner.hasNextLine()) {

                String line =
                        scanner.nextLine();

                String[] data =
                        line.split(",");

                GameState gameState =
                        GameState.getInstance();

                gameState.setCoins(
                        Integer.parseInt(data[0].trim()));

                gameState.setHearts(
                        Integer.parseInt(data[1].trim()));

                gameState.setEnergy(
                        Integer.parseInt(data[2].trim()));

                gameState.setLastSaveTime(
                        Long.parseLong(data[3].trim()));

                if (data.length > 4) {

                    gameState.setStreak(
                            Integer.parseInt(data[4].trim()));
                }

                if (data.length > 5) {

                    gameState.setHighestStreak(
                            Integer.parseInt(data[5].trim()));
                }

                if (data.length > 6) {

                    gameState.setTotalMatches(
                            Integer.parseInt(data[6].trim()));
                }

                if (data.length > 7) {

                    gameState.setTotalCoins(
                            Integer.parseInt(data[7].trim()));
                }

                gameState.setGameOver(false);
                gameState.setGameOverReason("");
                gameState.resetReturnRiskCount();
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
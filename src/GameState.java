/**
 * Maintains the player's game state throughout the session.
 * Tracks resources (coins, hearts, energy), score, streaks, match statistics,
 * and game-over conditions. 
 *
 *
 */
public class GameState {

    private static GameState instance;

    private int coins = 1000;
    private int hearts = 5;
    private int energy = 50;
    private int score = 0;
    private long lastSaveTime = System.currentTimeMillis();

    private int streak = 0;
    private int highestStreak = 0;
    private int totalMatches = 0;
    private int totalCoins = 0;
    private int matchesToday = 0;
    private int coinsToday = 0;
    private int highScoreToday = 0;

    private int returnRiskCount = 0;

    private boolean gameOver = false;
    private String gameOverReason = "";


    /**
     * Returns the instance of GameState.
     * Creates the instance if it does not yet exist.
     *
     * Precondition:  None.
     * Postcondition: A GameState instance is returned.
     *
     * @return GameState instance
     *
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    /**
     * Returns the player's current match streak
     *
     * Precondition:  None.
     * Postcondition: The streak value
     *
     * @return the current streak count
     *
     */
    public int getStreak()         { return streak; }

    /**
     * Returns the player's highest match streak
     *
     * Precondition:  None.
     * Postcondition: The highest streak value is returned
     *
     * @return the highest streak count
     *
     */
    public int getHighestStreak()  { return highestStreak; }

    /**
     * Returns the total number of matches played across all sessions.
     *
     * Precondition:  None.
     * Postcondition: The total matches value 
     *
     * @return the total match count
     *
     */
    public int getTotalMatches()   { return totalMatches; }

    /**
     * Returns the total coins earned across all sessions.
     *
     * Precondition:  None.
     * Postcondition: The total coins value 
     *
     * @return the total coins earned
     *
     */
    public int getTotalCoins()     { return totalCoins; }

    /**
     * Returns the number of matches played in the current day's session.
     *
     * Precondition:  None.
     * Postcondition: The matches today value 
     *
     * @return the number of matches played today
     *
     */
    public int getMatchesToday()   { return matchesToday; }

    /**
     * Returns the coins earned in the current day's session.
     *
     * Precondition:  None.
     * Postcondition: The coins today value 
     *
     * @return the coins earned today
     *
     */
    public int getCoinsToday()     { return coinsToday; }

    /**
     * Returns the highest score achieved in the current day's session.
     *
     * Precondition:  None.
     * Postcondition: The high score today value 
     *
     * @return the highest score today
     *
     */
    public int getHighScoreToday() { return highScoreToday; }

    /**
     * Sets the player's current match streak.
     *
     * Precondition:  {@code s} is a non-negative integer.
     * Postcondition: The streak field is updated to {@code s}.
     *
     * @param s the new streak value
     *
     */
    public void setStreak(int s)         { this.streak = s; }

    /**
     * Sets the player's highest match streak.
     *
     * Precondition:  {@code s} is a non-negative integer.
     * Postcondition: The highestStreak field is updated to {@code s}.
     *
     * @param s the new highest streak value
     *
     */
    public void setHighestStreak(int s)  { this.highestStreak = s; }

    /**
     * Sets the total number of matches played.
     *
     * Precondition:  {@code m} is a non-negative integer.
     * Postcondition: The totalMatches field is updated to {@code m}.
     *
     * @param m the new total matches value
     *
     */
    public void setTotalMatches(int m)   { this.totalMatches = m; }

    /**
     * Sets the total coins earned across all sessions.
     *
     * Precondition:  {@code c} is a non-negative integer.
     * Postcondition: The totalCoins field is updated to {@code c}.
     *
     * @param c the new total coins value
     *
     */
    public void setTotalCoins(int c)     { this.totalCoins = c; }

    /**
     * Sets the number of matches played today.
     *
     * Precondition:  {@code m} is a non-negative integer.
     * Postcondition: The matchesToday field is updated to {@code m}.
     *
     * @param m the new matches today value
     *
     */
    public void setMatchesToday(int m)   { this.matchesToday = m; }

    /**
     * Sets the coins earned today.
     *
     * Precondition:  {@code c} is a non-negative integer.
     * Postcondition: The coinsToday field is updated to {@code c}.
     *
     * @param c the new coins today value
     *
     */
    public void setCoinsToday(int c)     { this.coinsToday = c; }

    /**
     * Sets the highest score achieved today.
     *
     * Precondition:  {@code h} is a non-negative integer.
     * Postcondition: The highScoreToday field is updated to {@code h}.
     *
     * @param h the new high score today value
     *
     */
    public void setHighScoreToday(int h) { this.highScoreToday = h; }

    /**
     * Returns whether the game is currently in a game-over state.
     *
     * Precondition:  None.
     * Postcondition: The gameOver flag 
     *
     * @return true if the game is over, false otherwise
     *
     */
    public boolean isGameOver()                  { return gameOver; }

    /**
     * Sets the game-over flag.
     *
     * Precondition:  None.
     * Postcondition: The gameOver field is updated to {@code b}.
     *
     * @param b true to mark the game as over, false to clear it
     *
     */
    public void setGameOver(boolean b)           { this.gameOver = b; }

    /**
     * Returns the reason the game ended, for display in the MatchEnd screen.
     *
     * Precondition:  None.
     * Postcondition: The gameOverReason string 
     *
     * @return the game-over reason string
     *
     */
    public String getGameOverReason()            { return gameOverReason; }

    /**
     * Sets the reason the game ended.
     *
     * Precondition:  {@code reason} is a non-null String.
     * Postcondition: The gameOverReason field is updated to {@code reason}.
     *
     * @param reason a message describing why the game ended
     *
     */
    public void setGameOverReason(String reason) { this.gameOverReason = reason; }

    /**
     * Returns the number of Return Risk outcomes in the current game session.
     *
     * Precondition:  None.
     * Postcondition: The returnRiskCount 
     *
     * @return the current return risk count
     *
     */
    public int getReturnRiskCount()          { return returnRiskCount; }

    /**
     * Sets the return risk count to a specific value.
     *
     * Precondition:  {@code r} is a non-negative integer.
     * Postcondition: The returnRiskCount field is updated to {@code r}.
     *
     * @param r the new return risk count
     *
     */
    public void setReturnRiskCount(int r)    { this.returnRiskCount = r; }

    /**
     * Increments the return risk count by one.
     *
     * Precondition:  None.
     * Postcondition: The returnRiskCount is increased by 1.
     *
     *
     */
    public void incrementReturnRiskCount()   { this.returnRiskCount++; }

    /**
     * Resets the return risk count to zero.
     * Called at the start of each new game session.
     *
     * Precondition:  None.
     * Postcondition: The returnRiskCount field is set to 0.
     *
     *
     */
    public void resetReturnRiskCount()       { this.returnRiskCount = 0; }

    /**
     * Returns the timestamp of the last time the game state was saved.
     *
     * Precondition:  None.
     * Postcondition: The lastSaveTime 
     *
     * @return the last save time in milliseconds since epoch
     *
     */
    public long getLastSaveTime() { return lastSaveTime; }

    /**
     * Sets the last save time timestamp.
     *
     * Precondition:  {@code t} is a valid timestamp in milliseconds.
     * Postcondition: The lastSaveTime field is updated to {@code t}.
     *
     * @param t the new last save time in milliseconds since epoch
     *
     */
    public void setLastSaveTime(long t) { this.lastSaveTime = t; }

    /**
     * Returns the player's current coin balance.
     *
     * Precondition:  None.
     * Postcondition: The coins value 
     *
     * @return the current coin balance
     *
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Returns the player's current heart count.
     *
     * Precondition:  None.
     * Postcondition: The hearts value 
     *
     * @return the current heart count
     *
     */
    public int getHearts() {
        return hearts;
    }

    /**
     * Returns the player's current energy level.
     *
     * Precondition:  None.
     * Postcondition: The energy value 
     *
     * @return the current energy level
     *
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Returns the player's current score.
     *
     * Precondition:  None.
     * Postcondition: The score value 
     *
     * @return the current score
     *
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the player's coin balance to the given value.
     *
     * Precondition:  coins is a non-negative integer.
     * Postcondition: The coins field is updated to {@code coins}.
     *
     * @param coins the new coin balance
     *
     */
    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * Sets the player's heart count to the given value.
     *
     * Precondition:  hearts is a non-negative integer.
     * Postcondition: The hearts field is updated to {@code hearts}.
     *
     * @param hearts the new heart count
     *
     */
    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    /**
     * Sets the player's energy level to the given value.
     *
     * Precondition:  energy is a non-negative integer.
     * Postcondition: The energy field is updated to {@code energy}.
     *
     * @param energy the new energy level
     *
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Sets the player's score to the given value.
     *
     * Precondition:  score is a non-negative integer.
     * Postcondition: The score field is updated to {@code score}.
     *
     * @param score the new score value
     *
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Adds the given amount to the player's coin balance.
     * Use a negative value to subtract coins.
     *
     * Precondition:  None.
     * Postcondition: The coins field is increased by {@code amount}.
     *
     * @param amount the number of coins to add (negative to subtract)
     *
     */
    public void addCoins(int amount) {
        this.coins += amount;
    }

    /**
     * Adds the given amount to the player's energy level.
     * Energy minimum is 0 and will not go negative.
     *
     * Precondition:  None.
     * Postcondition: Energy is increased or decreased by amount
     *                Returns false if the result would have been negative.
     *
     * @param amount the amount of energy to add (negative to subtract)
     * @return true if energy remained at or above 0, false if it was clamped
     *
     */
    public boolean addEnergy(int amount) {
        this.energy += amount;
        if (energy < 0) {
            energy = 0;
            return false;
        }
        return true;
    }

    /**
     * Adds the given amount to the player's heart count.
     * Hearts minimum is 0 and will not go negative.
     *
     * Precondition:  None.
     * Postcondition: Hearts is increased or decreased by amount
     *                Returns false if the result would have been negative.
     *
     * @param amount the number of hearts to add (negative to subtract)
     * @return true if hearts remained at or above 0, false if it was clamped
     *
     */
    public boolean addHearts(int amount) {
        this.hearts += amount;
        if (hearts < 0) {
            hearts = 0;
            return false;
        }
        return true;
    }

    /**
     * Adds the given amount to the player's score.
     *
     * Precondition:  None.
     * Postcondition: The score field is increased by score
     *
     * @param score the amount to add to the current score
     *
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Checks whether a full day has passed since the last save time.
     * If so, restores hearts to at least 5 and energy to at least 500,
     * and updates the last save time to now.
     *
     * Precondition:  lastSaveTime has been set to a valid timestamp.
     * Postcondition: If 24 hours have elapsed, hearts and energy are refreshed
     *                to their daily minimums and lastSaveTime is updated to now.
     *
     *
     */
    public void checkDailyRefresh() {
        long now = System.currentTimeMillis();
        long millisInDay = 24 * 60 * 60 * 1000L;

        if (now - lastSaveTime >= millisInDay) {
            if (hearts < 5)   hearts = 5;
            if (energy < 500) energy = 500;
            lastSaveTime = now;
        }
    }
}
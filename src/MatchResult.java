import java.io.*;
import java.util.ArrayList;

/**
 * Represents the result of matching a pet with an adopter.
 *
 * This class stores the matched pet, matched adopter, outcome, score impact,
 * success score, return risk, and stress impact. It also contains the main
 * logic for creating a match, simulating the outcome, calculating compatibility,
 * calculating return risk, calculating shelter stress, writing match logs, and
 * updating the leaderboard.
 *
 * Preconditions:
 * - Pet and Adopter objects may or may not already be selected before a match is created
 *
 * Postconditions:
 * - Creates and stores the result of a pet-adopter match
 * - Can calculate and return match statistics
 * - Can save match and leaderboard information to files
 *
 *
 */
public class MatchResult {

    // Fields

    private static MatchResult lastResult;

    /**
     * Returns the most recent MatchResult that was created.
     *
     * @return MatchResult representing the most recent match result
     */
    public static MatchResult getLastResult() { return lastResult; }

    private Pet    pet;
    private Adopter adopter;
    private String  outcome;       // "Successful Match", "Return Risk", "Neutral"
    private int     scoreImpact;   // points added or subtracted from player score
    private double  successScore;  // 0–100 compatibility score
    private double  returnRisk;    // 0–100 chance of return
    private int     stressImpact;  // how much shelter stress this match causes


    /**
     * Creates a MatchResult object using the given pet and adopter.
     *
     * Preconditions:
     * - A Pet object and Adopter object are given
     *
     * Postconditions:
     * - Initializes the pet and adopter for this match result
     *
     * @param pet the pet being matched
     * @param adopter the adopter being matched
     *
     *
     */
    public MatchResult(Pet pet, Adopter adopter) {
        this.pet     = pet;
        this.adopter = adopter;
    }


    /**
     * Creates a match between the given pet and adopter, runs the full
     * simulation, and returns the completed MatchResult.
     *
     * Preconditions:
     * - A Pet object and Adopter object are given
     *
     * Postconditions:
     * - Creates a MatchResult object
     * - Simulates the match outcome
     * - Saves the result as the most recent match
     * - Returns the completed MatchResult
     *
     * @param pet      the Pet being matched
     * @param adopter  the Adopter being matched
     * @return         a fully simulated MatchResult
     *
     *
     */
    public static MatchResult createMatch(Pet pet, Adopter adopter) {
        MatchResult result = new MatchResult(pet, adopter);
        result.simulateOutcome();
        lastResult = result;
        return result;
    }

    /**
     * Runs the full outcome simulation for this match.
     * Populates successScore, returnRisk, stressImpact, outcome,
     * and scoreImpact based on the pet's and adopter's attributes.
     *
     * Preconditions:
     * - MatchResult object has been created
     * - Pet and adopter information is available
     *
     * Postconditions:
     * - Calculates success score
     * - Calculates return risk
     * - Calculates stress impact
     * - Sets the outcome and score impact for the match
     *
     *
     */
    public void simulateOutcome() {
        successScore  = calculateSuccessScore();
        returnRisk    = calculateReturnRisk();
        stressImpact  = calculateStressImpact();

        // Decide outcome and score impact
        if (successScore >= 50 && returnRisk < 30) {
            outcome     = "Successful Match";
            scoreImpact = 10;
        } else if (returnRisk >= 50) {
            outcome     = "Return Risk";
            scoreImpact = 2;
        } else {
            outcome     = "Neutral";
            scoreImpact = 5;
        }
    }

    /**
     * Calculates a compatibility score between the pet and adopter.
     *
     * Scoring logic:
     * +30 if preferred pet type matches
     * +25 if energy levels are within 2 of each other
     * +10 if energy levels are within 4 of each other
     * +15 if adopter has an active lifestyle with a high-energy pet
     * +15 if adopter does not live in an apartment and the pet is a dog
     * +10 if adopter has no other pets and the pet has no special needs
     * -10 if adopter has children and the pet has special needs
     *
     * Preconditions:
     * - MatchResult object has been created
     * - Pet and adopter information is available
     *
     * Postconditions:
     * - Returns a compatibility score from 0 to 100
     *
     * @return compatibility score clamped to [0, 100]
     *
     *
     */
    public double calculateSuccessScore() {
        double score = 0;

        // Type preference
       // Type preference — allergy adopters should be matched with birds only
		if (adopter.isHasAllergy()) {
		    if (pet.getType().equalsIgnoreCase("Bird")) {
		        score += 30;
		    } else {
		        score -= 40;
		    }
		} else if (adopter.getPreferredType().equalsIgnoreCase(pet.getType())) {
		    score += 30;
		}

        // Energy match
        int energyDiff = Math.abs(pet.getEnergyLevel() - adopter.getEnergyLevel());
        if (energyDiff <= 2) {
            score += 25;
        } else if (energyDiff <= 4) {
            score += 10;
        }

        // Active lifestyle with high-energy pet
        if (adopter.getLifestyle().equalsIgnoreCase("Active") && pet.getEnergyLevel() >= 7) {
            score += 15;
        }

        // House (not apartment) with a dog
        if (!adopter.getHouseType().equalsIgnoreCase("Apartment") && pet.getType().equalsIgnoreCase("Dog")) {
            score += 15;
        }

        // No other pets + no special needs = smooth match
        boolean petHasSpecialNeeds = pet.getSpecialNeeds() != null && !pet.getSpecialNeeds().trim().isEmpty();
        if (!adopter.isHasOtherPets() && !petHasSpecialNeeds) {
            score += 10;
        }

        // Penalty: children + special needs pet
        if (adopter.isHasChildren() && petHasSpecialNeeds) {
            score -= 10;
        }

        // Clamp to [0, 100]
        if (score < 0)   score = 0;
        if (score > 100) score = 100;

        return score;
    }

    /**
     * Calculates the probability that the pet will be returned after adoption.
     *
     * Logic:
     * Base risk starts at 20.
     * +25 if energy mismatch is greater than 4
     * +20 if pet has special needs and adopter has children
     * +40 if adopter has allergies and pet is not a bird
     * +15 if adopter does not have allergies but preferred type does not match pet type
     * +10 if adopter lives in an apartment with a high-energy dog
     * -15 if adopter has other pets
     * -10 if adopter is active and pet energy level is at least 6
     *
     * Preconditions:
     * - MatchResult object has been created
     * - Pet and adopter information is available
     *
     * Postconditions:
     * - Returns a return risk score from 0 to 100
     *
     * @return return risk clamped to [0, 100]
     *
     *
     */
    public double calculateReturnRisk() {
        double risk = 20; // base risk

        int energyDiff = Math.abs(pet.getEnergyLevel() - adopter.getEnergyLevel());
        if (energyDiff > 4) {
            risk += 25;
        }

        boolean petHasSpecialNeeds = pet.getSpecialNeeds() != null && !pet.getSpecialNeeds().trim().isEmpty();
        if (petHasSpecialNeeds && adopter.isHasChildren()) {
            risk += 20;
        }

        if (adopter.isHasAllergy() && !pet.getType().equalsIgnoreCase("Bird")) {
        risk += 40;
        } else if (!adopter.isHasAllergy() && !adopter.getPreferredType().equalsIgnoreCase(pet.getType())) {
        risk += 15;
        }

        if (adopter.getHouseType().equalsIgnoreCase("Apartment")
                && pet.getType().equalsIgnoreCase("Dog")
                && pet.getEnergyLevel() >= 7) {
            risk += 10;
        }

        if (adopter.isHasOtherPets()) {
            risk -= 15; // experienced with pets
        }

        if (adopter.getLifestyle().equalsIgnoreCase("Active") && pet.getEnergyLevel() >= 6) {
            risk -= 10;
        }

        // Clamp to [0, 100]
        if (risk < 0)   risk = 0;
        if (risk > 100) risk = 100;

        return risk;
    }

    /**
     * Calculates how much shelter stress this match causes.
     *
     * Logic:
     * Base stress starts at 3.
     * +3 if pet has special needs
     * +2 if return risk is at least 50
     * +2 if pet has been in the shelter for more than 20 days
     * -1 if success score is at least 70
     *
     * Preconditions:
     * - MatchResult object has been created
     * - Pet information is available
     * - returnRisk and successScore have been calculated
     *
     * Postconditions:
     * - Returns a stress value from 1 to 10
     *
     * @return stress value clamped to [1, 10]
     *
     *
     */
    public int calculateStressImpact() {
        int stress = 3;

        boolean petHasSpecialNeeds = pet.getSpecialNeeds() != null && !pet.getSpecialNeeds().trim().isEmpty();
        if (petHasSpecialNeeds) {
            stress += 3;
        }

        if (returnRisk >= 50) {
            stress += 2;
        }

        if (pet.getDaysInShelter() > 20) {
            stress += 2;
        }

        if (successScore >= 70) {
            stress -= 1;
        }

        // Clamp to [1, 10]
        if (stress < 1)  stress = 1;
        if (stress > 10) stress = 10;

        return stress;
    }

  
    /**
     * Writes the match result information to a match log file.
     *
     * Preconditions:
     * - MatchResult object has been created
     * - Pet, adopter, outcome, and score impact information is available
     * - A file path is given
     *
     * Postconditions:
     * - Creates the file if it does not exist
     * - Adds a header if the file is empty
     * - Adds the current match result to the file
     *
     * @param filePath path to the match log file
     *
     *
     */
    public void writeToMatchLog(String filePath) {
        File file = new File(filePath);
        boolean needsHeader = !file.exists() || file.length() == 0;

        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
            if (needsHeader) {
                pw.println("petID,adopterID,outcome,scoreImpact");
            }
            pw.println(pet.getPetID() + "," + adopter.getAdopterID() + ","
                     + outcome + "," + scoreImpact);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates leaderboard.csv with the player's new score.
     * If the playerName already exists, their score is updated.
     * If not, a new entry is added.
     * The leaderboard is then re-saved sorted by score descending.
     *
     * Preconditions:
     * - A file path, player name, and new score are given
     *
     * Postconditions:
     * - Reads the existing leaderboard if it exists
     * - Updates the player's score or adds the player as a new entry
     * - Sorts the leaderboard from highest score to lowest score
     * - Writes the updated leaderboard back to the file
     *
     * @param filePath    path to leaderboard.csv (e.g. "Data/leaderboard.csv")
     * @param playerName  the player's display name
     * @param newScore    the player's updated total score
     *
     *
     */
    public static void updateLeaderboard(String filePath, String playerName, int newScore) {
        File file = new File(filePath);
        ArrayList<String[]> rows = new ArrayList<>();

        // Read existing rows
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String header = br.readLine(); // skip header
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        rows.add(line.split(","));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Update existing entry or add new one
        boolean found = false;
        for (String[] row : rows) {
            if (row[0].trim().equalsIgnoreCase(playerName)) {
                row[1] = String.valueOf(newScore);
                found = true;
                break;
            }
        }
        if (!found) {
            rows.add(new String[]{playerName, String.valueOf(newScore)});
        }

        // Sort by score descending
        for (int i = 1; i < rows.size(); i++) {
            String[] key = rows.get(i);
            int j = i - 1;
            while (j >= 0 && Integer.parseInt(rows.get(j)[1]) < Integer.parseInt(key[1])) {
                rows.set(j + 1, rows.get(j));
                j--;
            }
            rows.set(j + 1, key);
        }

        // Write back
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, false))) {
            pw.println("playerName,score");
            for (String[] row : rows) {
                pw.println(row[0] + "," + row[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the pet in this match.
     *
     * @return Pet object in this match
     */
    public Pet     getPet()          { return pet; }

    /**
     * Returns the adopter in this match.
     *
     * @return Adopter object in this match
     */
    public Adopter getAdopter()      { return adopter; }

    /**
     * Returns the outcome of this match.
     *
     * @return String representing the match outcome
     */
    public String  getOutcome()      { return outcome; }

    /**
     * Returns the score impact of this match.
     *
     * @return int representing the score impact
     */
    public int     getScoreImpact()  { return scoreImpact; }

    /**
     * Returns the success score of this match.
     *
     * @return double representing the success score
     */
    public double  getSuccessScore() { return successScore; }

    /**
     * Returns the return risk of this match.
     *
     * @return double representing the return risk
     */
    public double  getReturnRisk()   { return returnRisk; }

    /**
     * Returns the stress impact of this match.
     *
     * @return int representing the stress impact
     */
    public int     getStressImpact() { return stressImpact; }

    /**
     * Returns the match result information as a formatted String.
     *
     * Preconditions:
     * - MatchResult object has been created
     * - Match outcome has been simulated
     *
     * Postconditions:
     * - Returns a String containing the pet name, adopter name, outcome,
     *   success score, return risk, stress impact, and score impact
     *
     * @return String containing match result information
     *
     *
     */
    @Override
    public String toString() {
        return "Match: " + pet.getName() + " ↔ " + adopter.getName()
             + "\nOutcome:      " + outcome
             + "\nSuccess Score: " + String.format("%.1f", successScore)
             + "\nReturn Risk:   " + String.format("%.1f", returnRisk) + "%"
             + "\nStress Impact: " + stressImpact
             + "\nScore Impact:  " + (scoreImpact >= 0 ? "+" : "") + scoreImpact;
    }
}
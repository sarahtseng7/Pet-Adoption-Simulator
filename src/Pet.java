/**
 * Represents a pet that can be matched with an adopter.
 *
 * This class stores the pet's basic information, including name, age, type,
 * image path, similarity score, days in shelter, special needs, energy level,
 * pet ID, and breed. It also includes methods for getting and updating pet
 * information, calculating urgency, getting the pet's food, and displaying
 * pet information.
 *
 * Preconditions:
 * - Pet information may or may not already exist before the object is created
 *
 * Postconditions:
 * - Creates a Pet object that stores and returns pet information
 *
 *
 */
public class Pet {
    private String name;
    private int age;
    private String type;
    private String imagePath;
    private double similarityScore;
    private int daysInShelter;
    private String specialNeeds;
    private int energyLevel;
    private int petID;
    private String breed;

    /**
     * Creates a Pet object with the given pet information.
     *
     * Preconditions:
     * - Name, age, type, image path, similarity score, days in shelter,
     *   special needs, energy level, pet ID, and breed are given
     *
     * Postconditions:
     * - Initializes the pet's information
     *
     * @param name pet's name
     * @param age pet's age
     * @param type pet's type
     * @param imagePath image path for the pet
     * @param similarityScore pet's similarity score
     * @param daysInShelter number of days the pet has been in the shelter
     * @param specialNeeds pet's special needs
     * @param energyLevel pet's energy level
     * @param petID pet's ID number
     * @param breed pet's breed
     *
     *
     */
    public Pet(String name, int age, String type, String imagePath, double similarityScore, int daysInShelter, String specialNeeds, int energyLevel, int petID, String breed) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.imagePath = imagePath;
        this.similarityScore = similarityScore;
        this.daysInShelter = daysInShelter;
        this.specialNeeds = specialNeeds;
        this.energyLevel = energyLevel;
        this.petID = petID;
        this.breed = breed;
    }

    // getter and setter methods

    /**
     * Returns the pet's name.
     *
     * @return String representing the pet's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the pet's age.
     *
     * @return int representing the pet's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the pet's type.
     *
     * @return String representing the pet's type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the image path for the pet.
     *
     * @return String representing the pet's image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Returns the pet's similarity score.
     *
     * @return double representing the pet's similarity score
     */
    public double getSimilarityScore() {
        return similarityScore;
    }

    /**
     * Returns the number of days the pet has been in the shelter.
     *
     * @return int representing the days in shelter
     */
    public int getDaysInShelter() {
        return daysInShelter;
    }

    /**
     * Returns the pet's special needs.
     *
     * @return String representing the pet's special needs
     */
    public String getSpecialNeeds() {
        return specialNeeds;
    }

    /**
     * Returns the pet's energy level.
     *
     * @return int representing the pet's energy level
     */
    public int getEnergyLevel() {
        return energyLevel;
    }

    /**
     * Returns the pet's ID number.
     *
     * @return int representing the pet's ID number
     */
    public int getPetID() {
        return petID;
    }

    /** 
     * Calculates urgency by checking special needs and days in shelter.
     *
     * Preconditions:
     * - The pet is not null
     *
     * Postconditions:
     * - specialNeeds and daysInShelter values are unchanged
     * 
     *
     * @return returns the urgency for the pet to get adopted, where greater numbers mean more urgent
     */
    public int getUrgency() {
        if(!getSpecialNeeds().equals("")) { return getDaysInShelter() + 5; }
        return getDaysInShelter();
    }

    /**
     * Returns the food that a generic Pet eats.
     *
     * Preconditions:
     * - None
     *
     * Postconditions:
     * - Pet is unchanged
     *
     *
     * @return returns a String representing the food a generic pet eats
     */
    public String getFood() {
        return "Food: pet food";
    }


    /**
     * Sets the pet's name.
     *
     * @param name new pet name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the pet's age.
     *
     * @param age new pet age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the pet's type.
     *
     * @param type new pet type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the image path for the pet.
     *
     * @param imagePath new image path for the pet
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Sets the pet's similarity score.
     *
     * @param similarityScore new pet similarity score
     */
    public void setSimilarityScore(double similarityScore) {
        this.similarityScore = similarityScore;
    }

    /**
     * Sets the number of days the pet has been in the shelter.
     *
     * @param daysInShelter new number of days in shelter
     */
    public void setDaysInShelter(int daysInShelter) {
        this.daysInShelter = daysInShelter;
    }

    /**
     * Sets the pet's special needs.
     *
     * @param specialNeeds new pet special needs
     */
    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    /**
     * Sets the pet's energy level.
     *
     * @param energyLevel new pet energy level
     */
    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    /**
     * Sets the pet's ID number.
     *
     * @param petID new pet ID number
     */
    public void setPetID(int petID) {
        this.petID = petID;
    }
    
    /**
     * Returns the pet's information as a formatted String.
     *
     * Preconditions:
     * - Pet object has been created
     *
     * Postconditions:
     * - Returns a String containing the pet's information
     *
     * @return String containing pet information
     *
     *
     */
    public String display() {
    	return "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Type: " + type + "\n" +
                "Similarity Score: " + similarityScore + "\n" +
                "Days in Shelter: " + daysInShelter + "\n" +
                "Special Needs: " + specialNeeds + "\n" +
                "Energy Level: " + energyLevel + "\n" +
                "Pet ID: " + petID + "\n" + getFood();
    }
}
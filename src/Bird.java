public class Bird extends Pet {
    private String breed;
    /* Bird class constructor
     * Precondition: none
     * Postcondition: 
     *
     *
     * @param name - 
     * @param age - 
     * @param type - 
     * @param imagePath - 
     * @param similarityScore - 
     * @param daysInShelter - 
     * @param specialNeeds - 
     * @param energyLevel - 
     * @param petID - 
     * @param breed - 
    */
    public Bird(String name, int age, String type, String imagePath, double similarityScore, int daysInShelter, String specialNeeds, int energyLevel, int petID, String breed) {
        super(name, age, type, imagePath, similarityScore, daysInShelter, specialNeeds, energyLevel, petID, breed);
        this.breed = breed;
    }

    // getter and setter methods
    public String getBreed() {
        return breed;
    }

    /* returns the food that a Bird eats
     * Precondition: none
     * Postcondition: Bird is unchanged
     *
     *
     * @return returns a String representing the food a bird eats
    */
    public String getFood() {
        return "Food: seeds, nuts, fruits";
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
    @Override
    public String toString() {
        return "Bird: " + getName() + " [" + breed + "]";
    }
}
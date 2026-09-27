public class Dog extends Pet {
    private String breed;

    public Dog(String name, int age, String type, String imagePath, double similarityScore, int daysInShelter, String specialNeeds, int energyLevel, int petID, String breed) {
        super(name, age, type, imagePath, similarityScore, daysInShelter, specialNeeds, energyLevel, petID, breed);
    }

    // getter and setter methods
    public String getBreed() {
        return breed;
    }

    /* returns the food that a Dog eats
     * Precondition: none
     * Postcondition: Dog is unchanged
     *
     * @return returns a String representing the food a dog eats
    */
    public String getFood() {
        return "Food: Meat (Turkey, Chicken, etc), Plant-based foods";
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
    @Override
    public String toString() {
        return "Dog: " + getName() + " [" + breed + "]";
    }
}

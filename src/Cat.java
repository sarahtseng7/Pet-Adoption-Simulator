public class Cat extends Pet {
    private String breed;

    public Cat(String name, int age, String type, String imagePath, double similarityScore, int daysInShelter, String specialNeeds, int energyLevel, int petID, String breed) {
        super(name, age, type, imagePath, similarityScore, daysInShelter, specialNeeds, energyLevel, petID, breed);
    }

    // getter and setter methods
    public String getBreed() {
        return breed;
    }

    /* returns the food that a Cat eats
     * Precondition: none
     * Postcondition: Cat is unchanged
     *
     * 
     * @return returns a String representing the food a cat eats
    */
    public String getFood() {
        return "Food: kibble, fish";
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
    @Override
    public String toString() {
        return "Cat: " + getName() + " [" + breed + "]";
    }
}
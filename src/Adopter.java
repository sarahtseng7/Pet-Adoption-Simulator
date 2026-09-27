/**
 * Represents an adopter who is looking to adopt a pet.
 * 
 * This class stores the adopter's basic information, including their name,
 * age, house type, energy level, lifestyle, children status, other pets status,
 * preferred pet type, adopter ID, allergy status, and image path.
 *
 * Preconditions:
 * - Adopter information may or may not already exist before the object is created
 *
 * Postconditions:
 * - Creates an Adopter object that stores and returns adopter information
 *
 */
public class Adopter {
    private String name;
    private int age;
    private String houseType;
    private int energyLevel; // to match with pet's energy
    private String lifestyle; // active, lazy
    private boolean hasChildren; 
    private boolean hasOtherPets;
    private String preferredType; // dog, cat. bird etc
    private int adopterID;
    private boolean hasAllergy;
    private String imagePath;

    /**
     * Creates an Adopter object with the given adopter information.
     *
     * Preconditions:
     * - Name, age, house type, energy level, lifestyle, children status,
     *   other pets status, preferred pet type, adopter ID, and image path are given
     *
     * Postconditions:
     * - Initializes the adopter's information
     *
     * @param name adopter's name
     * @param age adopter's age
     * @param houseType adopter's house type
     * @param energyLevel adopter's energy level
     * @param lifestyle adopter's lifestyle
     * @param hasChildren whether adopter has children
     * @param hasOtherPets whether adopter has other pets
     * @param preferredType adopter's preferred pet type
     * @param adopterID adopter's ID number
     * @param image image path for the adopter
     *
     */
    public Adopter(String name, int age, String houseType, int energyLevel, String lifestyle, boolean hasChildren, boolean hasOtherPets, String preferredType, int adopterID, String image) {
        this.name = name;
        this.age = age;
        this.houseType = houseType;
        this.energyLevel = energyLevel;
        this.lifestyle = lifestyle;
        this.hasChildren = hasChildren;
        this.hasOtherPets = hasOtherPets;
        this.preferredType = preferredType;
        this.adopterID = adopterID;
        this.imagePath = image;
    }

    // getter and setter methods

    /**
     * Returns the adopter's name.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns the name stored in the adopter object
     *
     * @return String representing the adopter's name
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the adopter's age.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns the age stored in the adopter object
     *
     * @return int representing the adopter's age
     *
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns the adopter's house type.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns the house type stored in the adopter object
     *
     * @return String representing the adopter's house type
     *
     */
    public String getHouseType() {
        return houseType;
    }

    /**
     * Returns the adopter's energy level.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns the energy level stored in the adopter object
     *
     * @return int representing the adopter's energy level
     *
     */
    public int getEnergyLevel() {
        return energyLevel;
    }

    /**
     * Returns the adopter's lifestyle.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns the lifestyle stored in the adopter object
     *
     * @return String representing the adopter's lifestyle
     *
     */
    public String getLifestyle() {
        return lifestyle;
    }

    /**
     * Returns whether the adopter has children.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns the children status stored in the adopter object
     *
     * @return boolean true if adopter has children, false otherwise
     *
     */
    public boolean isHasChildren() {
        return hasChildren;
    }

    /**
     * Returns whether the adopter has other pets.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns the other pets status stored in the adopter object
     *
     * @return boolean true if adopter has other pets, false otherwise
     *
     */
    public boolean isHasOtherPets() {
        return hasOtherPets;
    }

    /**
     * Returns the adopter's preferred pet type.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns the preferred pet type stored in the adopter object
     *
     * @return String representing the adopter's preferred pet type
     *
     */
    public String getPreferredType() {
        return preferredType;
    }

    /**
     * Returns the adopter's ID number.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns the adopter ID stored in the adopter object
     *
     * @return int representing the adopter's ID number
     *
     */
    public int getAdopterID() {
        return adopterID;
    }
    
    /**
     * Returns the image path for the adopter.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns the image path stored in the adopter object
     *
     * @return String representing the adopter's image path
     *
     */
    public String getImagePath() {
    	return imagePath;
    }

    /**
     * Sets the adopter's name.
     *
     * Preconditions:
     * - Adopter object has been created
     * - New name is given
     *
     * Postconditions:
     * - Updates the adopter's name
     *
     * @param name new adopter name
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the adopter's age.
     *
     * Preconditions:
     * - Adopter object has been created
     * - New age is given
     *
     * Postconditions:
     * - Updates the adopter's age
     *
     * @param age new adopter age
     *
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets the adopter's house type.
     *
     * Preconditions:
     * - Adopter object has been created
     * - New house type is given
     *
     * Postconditions:
     * - Updates the adopter's house type
     *
     * @param houseType new adopter house type
     *
     */
    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    /**
     * Sets the adopter's energy level.
     *
     * Preconditions:
     * - Adopter object has been created
     * - New energy level is given
     *
     * Postconditions:
     * - Updates the adopter's energy level
     *
     * @param energyLevel new adopter energy level
     *
     */
    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    /**
     * Sets the adopter's lifestyle.
     *
     * Preconditions:
     * - Adopter object has been created
     * - New lifestyle is given
     *
     * Postconditions:
     * - Updates the adopter's lifestyle
     *
     * @param lifestyle new adopter lifestyle
     *
     */
    public void setLifestyle(String lifestyle) {
        this.lifestyle = lifestyle;
    }

    /**
     * Sets whether the adopter has children.
     *
     * Preconditions:
     * - Adopter object has been created
     * - Children status is given
     *
     * Postconditions:
     * - Updates whether the adopter has children
     *
     * @param hasChildren true if adopter has children, false otherwise
     *
     */
    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    /**
     * Sets whether the adopter has other pets.
     *
     * Preconditions:
     * - Adopter object has been created
     * - Other pets status is given
     *
     * Postconditions:
     * - Updates whether the adopter has other pets
     *
     * @param hasOtherPets true if adopter has other pets, false otherwise
     *
     */
    public void setHasOtherPets(boolean hasOtherPets) {
        this.hasOtherPets = hasOtherPets;
    }

    /**
     * Sets the adopter's preferred pet type.
     *
     * Preconditions:
     * - Adopter object has been created
     * - New preferred pet type is given
     *
     * Postconditions:
     * - Updates the adopter's preferred pet type
     *
     * @param preferredType new adopter preferred pet type
     *
     */
    public void setPreferredType(String preferredType) {
        this.preferredType = preferredType;
    }

    /**
     * Returns whether the adopter has allergies.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns the allergy status stored in the adopter object
     *
     * @return boolean true if adopter has allergies, false otherwise
     *
     */
    public boolean isHasAllergy() {
    return hasAllergy;
    }

    /**
     * Sets whether the adopter has allergies.
     *
     * Preconditions:
     * - Adopter object has been created
     * - Allergy status is given
     *
     * Postconditions:
     * - Updates whether the adopter has allergies
     *
     * @param hasAllergy true if adopter has allergies, false otherwise
     *
     */
    public void setHasAllergy(boolean hasAllergy) {
    this.hasAllergy = hasAllergy;
    }
    
    /**
     * Returns the adopter's information as a formatted String.
     *
     * Preconditions:
     * - Adopter object has been created
     *
     * Postconditions:
     * - Returns a String containing the adopter's information
     *
     * @return String containing adopter information
     *
     */
    public String toString() {
    	return "Name: " + name + "\n" +
            "Age: " + age + "\n" +
            "House Type: " + houseType + "\n" +
            "Energy Level: " + energyLevel + "\n" +
            "Lifestyle: " + lifestyle + "\n" +
            "Children: " + hasChildren + "\n" +
            "Owns other Pets: " + hasOtherPets + "\n" +
            "Preferred Pet Type: " + preferredType + "\n" +
            "Adopter ID: " + adopterID + "\n";
    }
}
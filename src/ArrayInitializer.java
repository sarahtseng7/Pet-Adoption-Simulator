import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * File IO class that takes pet File and adopter File and converts it into an array 
 * Checks if file name inputted is valid
 *
 *
 */
public class ArrayInitializer {
	private static String petFileName;
	private static String adopterFileName;

    /**
    * Initializes pet array based on petFileName
    * Precondition: petFileName is initialized
    * Postcondition: ArrayList<Pet> is returned and contains all petFileName info
    *     petFileName is not changed 
    * @return ArrayList of object Pet based on petFileName
    *
    */
    public ArrayList<Pet> petArrayInitialize() {

     	File file = new File(petFileName);
        ArrayList<Pet> arr = new ArrayList<>();

        // Check if file is valid first
        if (!isValidPetFile(petFileName)) {
            return null;
        }

        try {
            Scanner scan = new Scanner(file);

            // skip header
            scan.nextLine();

            while (scan.hasNextLine()) {

                String line = scan.nextLine();
                String[] data = line.split(",");

                String name = data[0];
                int age = Integer.parseInt(data[1]);
                String type = data[2];
                String imagePath = data[3];
                double similarityScore = Double.parseDouble(data[4]);
                int daysInShelter = Integer.parseInt(data[5]);
                String specialNeeds = data[6];
                int energyLevel = Integer.parseInt(data[7]);
                int petID = Integer.parseInt(data[8]);
                String breed = data[9];

                Pet pet;

                if (type.equals("Bird")) {

                    pet = new Bird(
                            name,
                            age,
                            type,
                            imagePath,
                            similarityScore,
                            daysInShelter,
                            specialNeeds,
                            energyLevel,
                            petID,
                            breed
                    );

                } else if (type.equals("Cat")) {

                    pet = new Cat(
                            name,
                            age,
                            type,
                            imagePath,
                            similarityScore,
                            daysInShelter,
                            specialNeeds,
                            energyLevel,
                            petID,
                            breed
                    );

                } else if (type.equals("Dog")) {

                    pet = new Dog(
                            name,
                            age,
                            type,
                            imagePath,
                            similarityScore,
                            daysInShelter,
                            specialNeeds,
                            energyLevel,
                            petID,
                            breed
                    );

                } else {
                    continue;
                }

                arr.add(pet);
            }

            scan.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return arr;
    }

    /**
    * Initializes adopter array based on adopterFileName
    * Precondition: adopterFileName is initialized
    * Postcondition: ArrayList<Adopter> is returned and contains all adopterFileName info
    *     adopterFileName is not changed 
    * @return ArrayList of object Adopter based on adopterFileName
    *
    */
    public ArrayList<Adopter> adopterArrayInitialize() {

        File file = new File(adopterFileName);
        ArrayList<Adopter> arr = new ArrayList<>();

        // Check if file is valid first
        if (!isValidAdopterFile(adopterFileName)) {
            return null;
        }

        try {
            Scanner scan = new Scanner(file);

            // skip header
            scan.nextLine();

            while (scan.hasNextLine()) {

                String line = scan.nextLine();
                String[] data = line.split(",");

                String name = data[0];
                int age = Integer.parseInt(data[1]);
                String houseType = data[2];
                int energyLevel = Integer.parseInt(data[3]);
                String lifestyle = data[4];
                boolean hasChildren = Boolean.parseBoolean(data[5]);
                boolean hasOtherPets = Boolean.parseBoolean(data[6]);
                String preferredType = data[7];
                int adopterID = Integer.parseInt(data[8]);
                String image = data[9];
                boolean hasAllergy =
                        data.length > 10 &&
                        Boolean.parseBoolean(data[10]);

                Adopter adopter = new Adopter(
                        name,
                        age,
                        houseType,
                        energyLevel,
                        lifestyle,
                        hasChildren,
                        hasOtherPets,
                        preferredType,
                        adopterID,
                        image
                );

                adopter.setHasAllergy(hasAllergy);

                arr.add(adopter);
            }

            scan.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return arr;
    }

    /**
    * checks if pet file is valid, returns true if so
    * Precondition: fileName is a valid String
    * Postcondition: file corresponding to fileName is not changed
    * @param String fileName - file name tested to see if it's valid
    * @return true if pet file is valid, false if not
    *
    */
    public boolean isValidPetFile(String fileName) {

        try {

            File file = new File(fileName);

            if (!file.exists()) {
                return false;
            }

            Scanner scan = new Scanner(file);

            // must have header
            if (!scan.hasNextLine()) {
                scan.close();
                return false;
            }

            scan.nextLine();

            while (scan.hasNextLine()) {

                String line = scan.nextLine();
                String[] data = line.split(",");

                // pet file should have exactly 10 columns
                if (data.length != 10) {
                    scan.close();
                    return false;
                }

                // test parsing
                Integer.parseInt(data[1]);
                Double.parseDouble(data[4]);
                Integer.parseInt(data[5]);
                Integer.parseInt(data[7]);
                Integer.parseInt(data[8]);

                String type = data[2];

                // valid pet types
                if (!(type.equals("Bird")
                        || type.equals("Cat")
                        || type.equals("Dog")
                        || type.equals("Chipmunk"))) {

                    scan.close();
                    return false;
                }
            }

            scan.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /**
    * checks if adopter file is valid, returns true if so
    * Precondition: fileName is a valid String
    * Postcondition: file corresponding to fileName is not changed
    * @param String fileName - file name tested to see if it's valid
    * @return true if adopter file is valid, false if not
    *
    */
    public boolean isValidAdopterFile(String fileName) {

        try {

            File file = new File(fileName);

            if (!file.exists()) {
                return false;
            }

            Scanner scan = new Scanner(file);

            // must have header
            if (!scan.hasNextLine()) {
                scan.close();
                return false;
            }

            scan.nextLine();

            while (scan.hasNextLine()) {

                String line = scan.nextLine();
                String[] data = line.split(",");

                // adopter file should have 10 or 11 columns
                if (data.length < 10 || data.length > 11) {
                    scan.close();
                    return false;
                }

                // test parsing
                Integer.parseInt(data[1]);
                Integer.parseInt(data[3]);
                Boolean.parseBoolean(data[5]);
                Boolean.parseBoolean(data[6]);
                Integer.parseInt(data[8]);

                if (data.length == 11) {
                    Boolean.parseBoolean(data[10]);
                }
            }

            scan.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }
    
    public static void setPetFile(String s) {
    	petFileName = s;
    }
    
    public static void setAdopterFile(String s) {
    	adopterFileName = s;
    }
}
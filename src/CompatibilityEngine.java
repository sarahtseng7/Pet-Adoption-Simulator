import java.util.ArrayList;

/** Determines the compatibility of the adopters and the pets,
 * searches for pets and adopters by both name and id, sorts pets by urgency.
 * @author - Anjali Muralikrishnan
 */
public class CompatibilityEngine {

    /* Recursively finds pet by name through linear search.
     * Precondition: pet list isn't null, 
     * Precondtion: target is a name of a pet in the list
     * Precondition: no pet is named with numbers
     * Postcondition: pet list and all pets remain unchanged
     *
     * 
     * @param pets - the ArrayList of Pet objects to search through
     * @param idx - the index to check for the target
     * @param target - the trimmed name of the pet to find
     * @return returns the pet with the targetName
    */
    private static Pet findPetByName(ArrayList<Pet> pets, int idx, String target) {
        if(idx == pets.size() - 1) {
            return null;
        }
        Pet curr = pets.get(idx);
        
        if(curr.getName().trim().equalsIgnoreCase(target.trim())) {
            return curr;
        } else {
            return findPetByName(pets, idx+1, target);
        }
    }
    
    /* Recursively finds Adopter by name
     * Precondition: target name belongs to an adopter, adopters list isn't null
     * Postcondition: adopter list and all adopters remain unchanged
     *
     * 
     * @param adopters - the ArrayList of Adopter objects to search through
     * @param idx - the index to check for the name
     * @param target - the name of the adopter to find
     * @return returns the Adopter object with the target name
    */
    private static Adopter findAdopterByName(ArrayList<Adopter> adopters, int idx, String target) {
    	if(idx == adopters.size() - 1) {
    		return null;
    	}
    	Adopter curr = adopters.get(idx);
    	
    	if(curr.getName().trim().equalsIgnoreCase(target.trim())) {
    		return curr;
    	} else {
    		return findAdopterByName(adopters, idx+1, target);
    	}
    }
    

    /* Recursively finds pet by name through linear search.
     * Precondition: pet list isn't null, petId is an id of a pet in the list
     * Postcondition: pet list and all pets remain unchanged
     *
     * 
     * @param pets - the ArrayList of Pet objects to search through
     * @param idx - the index to check for the targetID
     * @param petId-  the Id of the pet to find
     * @return returns the pet with the petId
    */
    private static Pet findPetById(ArrayList<Pet> pets, int idx, int petId) {
        if(idx == pets.size() - 1) {
            return null;
        }
        Pet curr = pets.get(idx);
        if(curr.getPetID() == petId) {
            return curr;
        } else {
            return findPetById(pets, idx+1, petId);
        }
    }
    
    /* Recursively finds the Adopter with the given Id
     * Precondition: Id belongs to some Adopter in list, adopters list isn't null
     * Postcondition: adopter list and all adopters remain unchanged
     *
     * 
     * @param adopters - the ArrayList of Adopter objects to search through
     * @param idx - the index to check for the name
     * @param target - the Id of the adopter to find
     * @return returns the Adopter object with the target ID
    */
    private static Adopter findAdopterById(ArrayList<Adopter> adopters, int idx, int adopterId) {
    	if(idx == adopters.size() - 1) {
    		return null;
    	}
    	Adopter curr = adopters.get(idx);
    	if(curr.getAdopterID() == adopterId) {
    		return curr;
    	} else {
    		return findAdopterById(adopters, idx+1, adopterId);
    	}
    }

    /* Determines whether to find pet by name or ID and calls correct function accordingly
     * Precondition: target String isn't empty, pets list isn't null
     * Postcondition: pet list and all pets remain unchanged
     *
     * 
     * @param pets - the ArrayList of Pet objects to search through
     * @param idx - the index to check for the name
     * @param target - the name OR Id of the pet to find
     * @return returns the Pet object with the target name / target ID
    */
    public static Pet findPet(ArrayList<Pet> pets, int idx, String target) {
        try {
            int targetID = Integer.parseInt(target);
            return findPetById(pets, idx, targetID);
        } catch(NumberFormatException e) {
        	return findPetByName(pets, idx, target);
        }
    }
    
    /* Determines whether to find adopter by name or ID and calls correct function accordingly
     * Precondition: target String isn't empty, adopters list isn't null
     * Postcondition: adopter list and all adopters remain unchanged
     *
     * 
     * @param adopters - the ArrayList of Adopter objects to search through
     * @param idx - the index to check for the name
     * @param target - the name OR Id of the adopter to find
     * @return returns the Adopter object with the target name / target ID
    */
    public static Adopter findAdopter(ArrayList<Adopter> adopters, int idx, String target) {
    	try {
    		int targetID = Integer.parseInt(target);
    		return findAdopterById(adopters, idx, targetID);
    	} catch(NumberFormatException e) {
    		return findAdopterByName(adopters, idx, target);
    	}
    }

    
    /* Recursively splits pet list into smaller lists to be sorted
     * Precondition: pet list isn't null
     * Postcondition: pet list remains unchanged
     *
     * 
     * @param unsorted - the ArrayList of Pet objects to sort by urgency
     * @return returns an ArrayList of Pet objects that is sorted by urgency
    */
    public static ArrayList<Pet> urgencySort(ArrayList<Pet> unsorted) {
        
        // base case
        if(unsorted.size() <= 1) { // nothing to sort 
            return unsorted;
        }

        ArrayList<Pet> left = new ArrayList<Pet>(); // handles left half
        ArrayList<Pet> right = new ArrayList<Pet>(); // handles right half

        // splits list of pets into easier to deal with sections
        int mid = unsorted.size() / 2;
        for(int i=0; i < unsorted.size(); i++) {
            if( i < mid) { // left half
                left.add(unsorted.get(i));
            } else { // right half
                right.add(unsorted.get(i));
            }
        }

        // sorts each section individually and then merges together to return
        return mergePet(urgencySort(left), urgencySort(right));
    }

    /* merges two pet lists into one list of pets sorted by urgency
     * Precondition: both left and right pet lists are not null
     * Postcondition: combined list is sorted by urgency
     *
     * 
     * @param left - ArrayList of sorted Pet objects
     * @param right - ArrayList of sorted Pet objects
     * @return returns a merged ArrayList of Pet objects that is sorted by urgency
    */
    private static ArrayList<Pet> mergePet(ArrayList<Pet> left, ArrayList<Pet> right) {
        ArrayList<Pet> merged = new ArrayList<Pet>();
        while(left.size() > 0 || right.size() > 0) { // at least one list has something left
            if(left.size() == 0) { // there is nothing remaining in the left list
                merged.add(right.remove(0));
            } else if(right.size() == 0) { // there is nothing remaining in the right list
                merged.add(left.remove(0));
            } 
            // if the left is more or equally as urgent as the right
            else if(left.get(0).getUrgency() >= right.get(0).getUrgency()) {
                merged.add(left.remove(0)); // take it out of left list and put in merged list
            }
            // if the right is less urgent than the left
            else {
                merged.add(right.remove(0));
            }
        }
        return merged;
    }
    
    
    
    /* Recursively splits history list into smaller lists to be sorted
     * Precondition: history list isn't null
     * Postcondition: history list remains unchanged
     *
     * 
     * @param unsorted - the ArrayList of String[] objects to sort by compatibility
     * @return returns an ArrayList of String[] objects that is sorted by compatibility
    */
    public static ArrayList<String[]> historySort(ArrayList<String[]> unsorted) {
        
        // base case
        if(unsorted.size() <= 1) { // nothing to sort 
            return unsorted;
        }

        ArrayList<String[]> left = new ArrayList<String[]>(); // handles left half
        ArrayList<String[]> right = new ArrayList<String[]>(); // handles right half

        // splits list of pets into easier to deal with sections
        int mid = unsorted.size() / 2;
        for(int i=0; i < unsorted.size(); i++) {
            if( i < mid) { // left half
                left.add(unsorted.get(i));
            } else { // right half
                right.add(unsorted.get(i));
            }
        }

        // sorts each section individually and then merges together to return
        return mergeHistory(historySort(left), historySort(right));
    }
    
    
    /* merges two history lists into one list of history sorted by compatibility
     * Precondition: both left and right history lists are not null
     * Postcondition: combined list is sorted by compatibility
     *
     * 
     * @param left - ArrayList of sorted String[] objects
     * @param right - ArrayList of sorted String[] objects
     * @return returns a merged ArrayList of String[] objects that is sorted by compatibility
    */
    private static ArrayList<String[]> mergeHistory(ArrayList<String[]> left, ArrayList<String[]> right) throws NumberFormatException {
        ArrayList<String[]> merged = new ArrayList<String[]>();
        while(left.size() > 0 || right.size() > 0) { // at least one list has something left
            if(left.size() == 0) { // there is nothing remaining in the left list
                merged.add(right.remove(0));
            } else if(right.size() == 0) { // there is nothing remaining in the right list
                merged.add(left.remove(0));
            } else {
            	double leftDouble = Double.parseDouble(left.get(0)[3]);
            	double rightDouble = Double.parseDouble(right.get(0)[3]);
            	if(leftDouble > rightDouble) { // if the left is more or equally as compatible
            		merged.add(left.remove(0));
            	} else {
            		merged.add(right.remove(0)); // if the right is more compatible
            	}
            }
        }
        return merged;
    }
    
}
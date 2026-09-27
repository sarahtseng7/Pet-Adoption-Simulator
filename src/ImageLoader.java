import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;

/** Loads images in all other classes, returns corresponding pet or adopter objects
 *
 */
class ImageLoader {
	
	/**
	 * loads an image file from the /img source folder
	 * Precondition: file exists in /img source folder
	 * Postcondition: image file remains unchanged
	 * @param resourceName - the string representing the name of the image file
	 * @return returns the image object referred to by the image file
	 * @throws IOException
	 *
	 */
	 static ImageIcon loadImage(String resourceName) {
		return loadResource("img", resourceName);
	}
	 
	 /**
	* loads an image file from the /petImgFile source folder
	* Precondition: file exists in /petImgFile source folder
	* Postcondition: image file remains unchanged
	* @param resourceName - the string representing the name of the image file
	* @return returns the image object referred to by the image file
	* @throws IOException
	*
	*/	 
	 static ImageIcon loadPet(Pet p) {
			return loadResource("petImgFile", p.getImagePath());
	}
	 
	 /**
	  * loads an image file from the /adopterImgFile source folder
	  * Precondition: file exists in /adopterImgFile source folder
	  * Postcondition: image file remains unchanged
	  * @param resourceName - the string representing the name of the image file
	  * @return returns the image object referred to by the image file
	  * @throws IOException
	  *
	  */
	 static ImageIcon loadAdopter(Adopter a) {
			return loadResource("adopterImgFile", a.getImagePath());
	}

	 
	 
	 /**
		 * loads an image file from any source folder
		 * Precondition: file exists in the corresponding source folder
		 * Postcondition: image file remains unchanged
		 * @param resourceName - the string representing the name of the image file
		 * @return returns the image object referred to by the image file
		 * @throws IOException
		 *
		 */
		 private static ImageIcon loadResource(String dir, String file) {
			String resource = "/" + file;
			try (InputStream is = ImageLoader.class.getResourceAsStream(resource)) {
			    if (is == null) {
			        throw new RuntimeException ("Resource not found: " + resource);
			    }
			    byte[] bytes = is.readAllBytes();
			    return new ImageIcon(bytes);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

}

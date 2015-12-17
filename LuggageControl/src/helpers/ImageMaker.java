package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Image;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import org.apache.commons.codec.binary.Base64;

/**
 * Manipulates the image
 * @author Konrad
 */
public class ImageMaker {

    private static ImageIcon imageIcon;
    
    private ImageMaker() {
    }
    
    /** 
     * Show a filechooser to select an 
     * @return path to image
     */
    public static String getImagePath() {
        String imagePath;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile);
            imagePath = selectedFile.getAbsolutePath();
        } else {
            imagePath = "no image found";
        }
        return imagePath;
    }
    
    /**
     * encodes the image into a base64 string
     *
     * @param imageByteArray
     * @return base64 string
     */
    public static String encodeBase64(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }
    
        /**
     * encodes the image into a base64 string
     *
     * @param imageByteArray
     * @return base64 string
     */
    public static String decodeBase64(byte[] imageByteArray) {
        return Arrays.toString(Base64.decodeBase64(imageByteArray));
    }
    
    /**
     * encodes image to base64
     * @param path string path to the image
     * @return base64 string of the image
     */
    public static String decodeImage(byte[] base64Image){
          return decodeBase64(base64Image);           
    }
    
    /**
     * encodes image to base64
     * @param path string path to the image
     * @return base64 string of the image
     */
    public static String encodeImage(String path){
        File file = new File(path);
        try {
            // Reading a ImageMaker file from file system
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);

            // Converting ImageMaker byte array into Base64 String
            String imageDataString = encodeBase64(imageData);
            imageInFile.close();

            System.out.println("Image Successfully Manipulated!");
            return imageDataString;
        } catch (FileNotFoundException e) {
           return ("Image not found" + e);
        } catch (IOException ioe) {
           return ("Exception while reading the Image " + ioe);
        }
            
    }
    
    /**
     * Resizes the image to make it fit the jLabel
     * @param width of the image
     * @param height ofthe image 
     * @param path to the image
     * @return the resized image(icon)
     */
    public static ImageIcon resizeImage(int width, int height, String path) {
        imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage(); // transform image 
        Image newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale the image the SMOOTH way 
        imageIcon = new ImageIcon(newImage);  // transform image back
        return imageIcon;
    }
    
}

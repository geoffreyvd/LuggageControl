/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import static screen.add.AddLuggage.encodeImage;

/**
 * Manipulates the image
 * @author Konrad
 */
public class ImageMaker {

    private static ImageIcon imageIcon;
    
    private ImageMaker() {
    }
    
    public static String getPath() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
        return selectedFile.getAbsolutePath();
        }
        return "no image found";
    }
    
    /**
     * encodes image to base64
     * @param path string path to the image
     * @return base64 string of the image
     */
    public static String base64Encode(String path){
        File file = new File(path);
            try {
                // Reading a ImageMaker file from file system
                FileInputStream imageInFile = new FileInputStream(file);
                byte imageData[] = new byte[(int) file.length()];
                imageInFile.read(imageData);

                // Converting ImageMaker byte array into Base64 String
                String imageDataString = encodeImage(imageData);
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
        Image image = imageIcon.getImage(); // transform it 
        Image newImage = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newImage);  // transform it back
        return imageIcon;
    }
    
}

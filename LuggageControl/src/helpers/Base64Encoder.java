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
import static screen.add.AddLuggage.encodeImage;

/**
 * Base64 encoder
 * @author Konrad
 */
public class Base64Encoder {

    private Base64Encoder() {
    }
    
    /**
     * encodes image to base64
     * @param path string path to the image
     * @return base64 string of the image
     */
    public static String encode(String path){
        File file = new File(path);
            try {
                // Reading a Image file from file system
                FileInputStream imageInFile = new FileInputStream(file);
                byte imageData[] = new byte[(int) file.length()];
                imageInFile.read(imageData);

                // Converting Image byte array into Base64 String
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
    
}

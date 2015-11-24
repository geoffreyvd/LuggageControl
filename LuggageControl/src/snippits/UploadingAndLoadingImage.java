/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snippits;

//these three classes are needed to make this work
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import javax.swing.JLabel;

/**
 * uploading(and selecting) an image and loading the image on the label
 * @author Admin
 */
public class UploadingAndLoadingImage extends SwitchingJPanel{
    // the label that you already have so don't worry about this
    JLabel labelName = new JLabel();  
    
    // make a JFileChooser object
    JFileChooser fileChooser = new JFileChooser();
    
    public UploadingAndLoadingImage() {
        // example of uploading a image
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            /* example of loading an image into a label, the label you can make 
             in the panel and change the labelName*/
            ImageIcon image = new ImageIcon(selectedFile.getAbsolutePath());
            labelName.setIcon(image);
        }
    }
    
    public static void main(String[] args) throws Exception {
        new UploadingAndLoadingImage();
    }
}

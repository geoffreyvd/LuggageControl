
package snippits;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

public class PDFbox {

    public static void main (String[] args) throws Exception {
        
        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page1 = new PDPage(PDPage.PAGE_SIZE_A4);
            // PDPage.PAGE_SIZE_LETTER is also possible
        PDRectangle rect = page1.getMediaBox();
            // rect can be used to get the page width and height
        document.addPage(page1);

        // Create a new font object selecting one of the PDF base fonts
        PDFont fontPlain = PDType1Font.HELVETICA;
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;
        PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
        PDFont fontMono = PDType1Font.COURIER;

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream cos = new PDPageContentStream(document, page1);

        int line = 1;

        // Define a text content stream using the selected font, move the cursor and draw some text
        cos.beginText();

        cos.setFont(fontPlain, 12);
        cos.setNonStrokingColor(Color.RED);
        // width is 100; height is 20 lines down everytime
        cos.moveTextPositionByAmount(100, rect.getHeight() - 20*(++line)); 
        cos.drawString("eeey boy");
        cos.endText();
        
        cos.beginText();
        cos.setFont(fontItalic, 12);
        cos.setNonStrokingColor(Color.GREEN);
        cos.moveTextPositionByAmount(100, rect.getHeight() - 20*(++line));
        cos.drawString("How do you do?");
        cos.endText();

        cos.beginText();
        cos.setFont(fontBold, 12);
        cos.setNonStrokingColor(Color.BLACK);
        cos.moveTextPositionByAmount(100, rect.getHeight() - 20*(++line));
        cos.drawString("Dit duurde veel langer dan nodig");
        cos.endText();

        cos.beginText();
        cos.setFont(fontMono, 12);
        cos.setNonStrokingColor(Color.BLACK);
        cos.moveTextPositionByAmount(100, rect.getHeight() - 20*(++line));
        cos.drawString("maar ik was wat dingentjes aan t uitzoeken.");
        cos.endText();
        
        
        // Add some gucci into this place
        try {
            BufferedImage awtImage = ImageIO.read(new File("Gucci.jpg"));
            PDXObjectImage ximage = new PDPixelMap(document, awtImage);
            float scale = 0.5f; // alter this value to set the image size
            cos.drawXObject(ximage, 100, 400, ximage.getWidth()*scale, ximage.getHeight()*scale);
        } catch (IIOException Fnfex) {
            System.out.println("No image for you");
        }

        // Make sure that the content stream is closed:
        cos.close();
        // New page
        PDPage page2 = new PDPage(PDPage.PAGE_SIZE_A4);
        document.addPage(page2);
        cos = new PDPageContentStream(document, page2);

        // draw a red box in the lower left hand corner
        cos.setNonStrokingColor(Color.RED);       
        cos.fillRect(10, 10, 100, 100);

        // add two lines of different widths
        cos.setLineWidth(1);
        cos.addLine(200, 250, 400, 250);
        cos.closeAndStroke();
        cos.setLineWidth(5);
        cos.addLine(200, 300, 400, 300);
        cos.closeAndStroke();

        // close the content stream for page 2
        cos.close();

        // Save the results and ensure that the document is properly closed:
        document.save("GUCCI.pdf");
        document.close();
    }
}

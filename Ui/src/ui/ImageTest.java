package ui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.*;

public class ImageTest extends SClass {

	//variabile globale
	
	static Random rand = new Random(); // pentru generarea de valori aleatorii
	static double contrast = 2.0 + (5.0-2.0)* rand.nextDouble();
	static BufferedImage imgAltered;
	static BufferedImage img = null;
	static String filepathsrc = null;
	static String filepathdst = null;

    
    
    
    // functie ce include varargs pentru etapele proiectului ce au durat mai mult de o durata prestabilita
    
    public static void showDeadTime(String... auxTimeName) {
    	if(auxTimeName[0] != "" || auxTimeName[1] != "" || auxTimeName[2] != "" || auxTimeName[3] != "") {
    		System.out.println("Etape ce au durat mai mult decat trebuia:");
    		if(auxTimeName[0] != "")
    			System.out.println(auxTimeName[0]);
    		if(auxTimeName[1] != "")
    			System.out.println(auxTimeName[1]);
    		
	    	if(auxTimeName[2] != "")
				System.out.println(auxTimeName[2]);
			
		    if(auxTimeName[3] != "") 
				System.out.println(auxTimeName[3]);
			} 
    }
    

public static BufferedImage readImageFile(File fila) {
    BufferedImage imge;
    try {
        imge = ImageIO.read(fila);
        // imge = imaginea originala
        return imge;
    } catch (IOException e) {
    	System.out.println(e);
    }
    return null;
}


        
public static BufferedImage alterImageFile(BufferedImage image) {
	
    BufferedImage imageAltered = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
    //contrast = 2.0 + (5.0 - 2.0) * rand.nextDouble(); //values from 2.0 to 5.0
    
    
    for(int i = 0; i < image.getWidth(); i++) {
        for(int j = 0; j < image.getHeight(); j++) {
            Color c = new Color(image.getRGB(i, j));
            int red = (int) contrast * c.getRed();
            int green = (int) contrast * c.getGreen();
            int blue = (int) contrast * c.getBlue();

            if(red > 255) { // the values of the color components must be between 0-255
                red = 255;
            } else if(red < 0) {
                red = 0;
            }
            if(green > 255) {
                green = 255;
            } else if(green < 0) {
                green = 0;
            }
            if(blue > 255) {
                blue = 255;
            } else if(blue < 0) {
                blue = 0;
            }
            imageAltered.setRGB(i, j, new Color(red, green, blue).getRGB());
        }
    }
    
    return imageAltered;
   
}
//scriere imagine  - etapa a 4-a - in SClass

// exemplu polimorfism
public void nrBiti() {
	 int nr = img.getHeight()*img.getWidth();
	 System.out.println("Numarul de biti al imaginii este: "+nr);
}

public void nrBiti(int height, int length) {
	int nr = height*length;
	 System.out.println("Numarul de biti este: "+nr);
}
}



package dtr.ui;

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

	
    public static void main(String[] args) throws IOException {
    	// siruri de caractere pentru numele etapelor folosite in functia showDeadTime
    	String Time1 = "";
    	String Time2 = "";
    	String Time3 = "";
    	String Time4 = "";
    	
    	//etapa 1
    	
    	ImageTest auxReadWrite = new ImageTest();
    	long startTime1 = System.currentTimeMillis();
    	filepathsrc = auxReadWrite.sRead();
    	dRead();
    	long endTime1 = System.currentTimeMillis();
    	System.out.println("\nCitirea sursei si a destinatiei a durat: " + (endTime1 - startTime1) + " millisecunde\n");
    	if((endTime1 - startTime1) > 11000)
    		Time1 = "Citirea sursei si a destinatiei";
    	
    	//etapa 2
    	
    	long startTime2 = System.currentTimeMillis();
    	readImage();
    	long endTime2 = System.currentTimeMillis();
    	System.out.println("Citirea imaginii a durat: " + (endTime2 - startTime2) + " millisecunde\n");
    	if((endTime2 - startTime2) > 450)
    		Time2 = "Citirea imaginii";
    	
    	//etapa 3
    	
    	long startTime3 = System.currentTimeMillis();
        alterImage();
        long endTime3 = System.currentTimeMillis();
    	System.out.println("Procesarea imaginii a durat: " + (endTime3 - startTime3) + " millisecunde\n");
    	if((endTime3 - startTime3) > 150)
    		Time3 = "Procesarea imaginii";
    	
    	//etapa 4
    	
    	long startTime4 = System.currentTimeMillis();
    	auxReadWrite.writeImage(imgAltered, filepathdst);
        long endTime4 = System.currentTimeMillis();
    	System.out.println("Scrierea imaginii a durat: " + (endTime4 - startTime4) + " millisecunde\n");
    	if((endTime4 - startTime4) > 100)
    		Time4 = "Scrierea imaginii";
    	showDeadTime(Time1,Time2,Time3,Time4);
    	
    }
    
    
    
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
    
 //citire informatii sursa si destinatie - etapa 1
    
//citire informatii sursa - in SClass 
    
  //citire informatii destinatie
    public static void dRead() {
    	
    	BufferedReader brdst = new BufferedReader(new InputStreamReader(System.in));
    	System.out.print("Enter destination file path: ");
    	try {
			filepathdst = brdst.readLine();
		} catch (IOException e) {
			System.out.println(e);
		}
    }
    
// citire imagine - etapa a 2-a
    
public static void readImage() {
	
	File f = null;
    try {
    	f = new File(filepathsrc);
        img = ImageIO.read(f);
        // img = imaginea originala
    } catch (IOException e) {
    	System.out.println(e);
    }
}


//modificare imagine - etapa a 3-a

public static void alterImage() {
	
	imgAltered = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
    //contrast = 2.0 + (5.0 - 2.0) * rand.nextDouble(); //values from 2.0 to 5.0
    
    
    for(int i = 0; i < img.getWidth(); i++) {
        for(int j = 0; j < img.getHeight(); j++) {
            Color c = new Color(img.getRGB(i, j));
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
            imgAltered.setRGB(i, j, new Color(red, green, blue).getRGB());
        }
    }
   
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


//    C:\eclipse\workspace\Modify Contrast of Image\resurse\imags\parrots.bmp
//    C:\eclipse\workspace\Modify Contrast of Image\resurse\imags\MyNewParrots.bmp


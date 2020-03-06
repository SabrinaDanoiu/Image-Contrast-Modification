package dtr.ui;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class SClass extends Abstracta{
	
	//scriere imagine  - etapa a 4-a
	
	public void writeImage(BufferedImage auxImgAltered, String auxFilepathdst) {
		
		File f = null;
		try {
	    	f = new File(auxFilepathdst);
	        ImageIO.write(auxImgAltered,"bmp",f);
	        // imgAltered = imaginea alterata
	    } catch (IOException e) {
	    	System.out.println(e);
	    }
	}
	
	//citire informatii sursa
	
	 public String sRead() {
	    	String auxFilepathsrc = null;
	    	BufferedReader brsrc = new BufferedReader(new InputStreamReader(System.in));
	    	System.out.print("Enter source file path: ");
	    	try {
	    		auxFilepathsrc = brsrc.readLine();
			} catch (IOException e) {
				System.out.println(e);
			}
	    	return auxFilepathsrc;
	    }
	
	 	 
}

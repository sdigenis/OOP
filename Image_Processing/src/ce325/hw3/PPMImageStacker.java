package ce325.hw3;
import java.io.*;
import java.util.*;

public class PPMImageStacker {

	private List <PPMImage> images;
	
	public PPMImageStacker(java.io.File dir) throws UnsupportedFileFormatException, IOException {
		
		if(!dir.exists()) {
			System.out.println("[ERROR] Directory "+ dir + " does not exist!");
		}
		if(!dir.isDirectory()) {
			System.out.println("[ERROR] "+ dir + " is not a directory!");
		}
		
		images = new LinkedList<PPMImage>();
		populateList(dir);
	}
	
	public final void populateList(java.io.File dir) throws UnsupportedFileFormatException, IOException {
		
		
		File[] files = new File[100];
		
		files = dir.listFiles();
		
			
		for(int i = 0; i < files.length; i++) {
			try {
				images.add(new PPMImage(files[i]) );
			}
			catch( UnsupportedFileFormatException ex ){
				throw new UnsupportedFileFormatException();
			}
			catch( FileNotFoundException ex){
				throw new FileNotFoundException();
			}
		}
	}
	
	public void stack() {
		
		
		int counter = 0;
		
		while(counter < images.size())
			counter++;
		
		int i = 0;
		RGBImage newRGB = new RGBImage(images.get(0).getImage()[0].length, images.get(0).getImage().length , 255);
		
		for(i = 0; i < images.get(0).getImage()[0].length; i++ ) {
			for(int j = 0 ; j < images.get(0).getImage().length; j++) {
				
				int sumred = 0, sumgreen = 0, sumblue = 0 ;
				for(counter = 0; counter < images.size(); counter++) {
					
					sumred += images.get(counter).getImage()[j][i].getRed();
					sumgreen += images.get(counter).getImage()[j][i].getGreen();
					sumblue += images.get(counter).getImage()[j][i].getBlue();
				}
				sumred = sumred / counter;
				sumgreen = sumgreen / counter;
				sumblue = sumblue / counter;
				RGBPixel newpixel = new RGBPixel((short) sumred, (short) sumgreen, (short) sumblue);
				newRGB.setPixel(j, i, newpixel);
			}
		}

		PPMImage newPPM = new PPMImage(newRGB);
		images.add(newPPM);
		
	}
	
	public PPMImage getStacked() {
		
		return images.get(0);
	}
	
}

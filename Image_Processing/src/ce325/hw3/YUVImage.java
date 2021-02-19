package ce325.hw3;

import java.util.Scanner;
import java.io.*;;

public class YUVImage {
	
	private YUVPixel[][] image;

	
	public YUVImage(int width , int height) {
		image = new YUVPixel[height][width];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				image[i][j] = new YUVPixel ((short)16 ,(short) 128, (short) 128);	
			}
		}
	}
	
	public YUVImage(YUVImage copyImg) {
		image = new YUVPixel[copyImg.getHeight()][copyImg.getWidth()];
		for(int i=0; i < copyImg.image.length; i++) {
			for(int j=0; j < copyImg.image[0].length; j++) {
				this.image[i][j] = new YUVPixel(copyImg.getPixel(i, j));
			}
		}
	}
	
	public YUVImage(RGBImage RGBImg) {
		image = new YUVPixel[RGBImg.getWidth()][RGBImg.getHeight()];
		for(int i = 0; i < RGBImg.getHeight(); i++) {
			for(int j = 0; j < RGBImg.getWidth(); j++) {
				image[j][i] = new YUVPixel(RGBImg.getPixel(i,j));
			}
		}
	}
	
	public YUVImage(java.io.File file) throws UnsupportedFileFormatException, IOException {
		
		
		if(!file.exists()) {
    		throw new java.io.FileNotFoundException("This file doesn't exist !");
    	}
    	if(!file.canRead()){
    		throw new java.io.IOException("This file has no read rights !");
    	}
    	
    	String name;
    	//int index;
    	name = file.getName();
    	//index = name.lastIndexOf('.');
    	String pathname;
    	
    	pathname = file.getAbsolutePath();
    	try (Scanner in = new Scanner(new FileReader(pathname) )){
			
			int i = 0;
			int width = 0 , height = 0;
			
			while( in.hasNext()) {
				if(i == 0) { 
					name = in.next();
					if(name == "YUV3") {       //για καποιο λογο οταν βαζουμε διαφορο το "YUV3" ενω ο τιτλος ειναι ο ιδιος δεν μπαινει ποτε  
			    		throw new UnsupportedFileFormatException("Not a yuv file !");
			    	}
				}
				else if(i == 1 ) {
					width = in.nextShort(); 
				}
				else if(i == 2) {
					height = in.nextShort();
				}
				else {
					image = new YUVPixel[height][width];
					
					for(int j = 0; j < height; j++) {
						if(!in.hasNext())
							break;
						for(int k = 0; k < width; k++) {
							if(!in.hasNext())
								break;
							image[j][k] = new YUVPixel(in.nextShort(), in.nextShort(), in.nextShort() ); 
						}
					}
					break;
				}
				i++;	
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public int getHeight() {
		return this.image.length;
	}
	
	public int getWidth() {
		return this.image[0].length;
	}
	
	public String toString() {
		
		String str ;
		
		str = "YUV3" + "\n" + 
			  image[0].length + " " + image.length + "\n";
			  
		for(int i = 0; i < image.length; i++) {
			for(int j = 0; j < image[0].length; j++) {
				str = str + image[i][j].getY() + " " 
						  + image[i][j].getU() + " "
						  + image[i][j].getV() + " ";
				str = str + "\n";
			}
			
		}
		
		return str;
	}
	public YUVPixel getPixel(int row, int col) {
		return this.image[row][col];
	}
	
	public YUVPixel[][] getImage(){
		return this.image;
	}
	
	public void toFile(java.io.File file) throws IOException{
		String content=this.toString();
		
		if(file.exists()) {   												//αν η εικονα υπαρχει
			if(file.length()==0) { 											//αν δεν εχει περιεχομενο προς διαγραφη
				try { 
					BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName(), true));
					writer.append(content);    
					writer.close();
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			else {															//αν εχει περιεχομενο προς διαγραφη
				try {
					PrintWriter writer = new PrintWriter(file.getName()); //https://www.baeldung.com/java-delete-file-contents
					writer.print("");
					writer.append(content);									//γραψε το νεο περιεχομενο
					writer.close();
					System.out.println("File saved !");
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		else {                                          //αν η εικονα δεν υπαρχει
			File newFile = new File(file.getName());	//φτιαξε ενα αρχειο με ιδιο ονομα
			try { 
					BufferedWriter writer = new BufferedWriter(new FileWriter(newFile.getName(), true));
					writer.append(content);    
					writer.close();
					System.out.println("File saved !");
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
		
		}
		
	}
	
	public void equalize() {
		
		Histogram hist = new Histogram(this);
		hist.equalize();
		
		for(int i = 0; i < image.length; i++) {
			for(int j = 0; j < image[0].length; j++ ) {
				image[i][j] = new YUVPixel(hist.getEqualizeLuminocity((int)image[i][j].getY()), image[i][j].getU(), image[i][j].getV());
			}
		}
	}
}

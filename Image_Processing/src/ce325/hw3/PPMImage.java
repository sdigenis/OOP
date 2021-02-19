package ce325.hw3;
import java.io.*;
import java.util.*;


public class PPMImage extends RGBImage {

	private String title;
	
	public static int fileGet(java.io.File file, int i) throws UnsupportedFileFormatException, IOException {
		
		String name;
		String filetype;
		String pathname;
    	int index;
    	if(!file.exists()) {
    		throw new java.io.FileNotFoundException("File wasn't found !");
    	}
    	if(!file.canRead()){
    		throw new java.io.IOException("File has no read rights !");
    	}
    	name = file.getName();
    	index = name.lastIndexOf('.');
    	filetype = name.substring(index + 1);
    	//System.out.println(filetype);
    	if(filetype == "ppm") {    //για καποιο λογο οταν βαζουμε διαφορο το "ppm" ενω το ονομα ειναι το ιδιο δεν μπαινει ποτε 
    		//System.out.println(name.substring(index + 1));
    		throw new UnsupportedFileFormatException("Not a ppm file !");
    	}
    	pathname = file.getAbsolutePath();
    	try (Scanner in = new Scanner(new FileReader(pathname))){
			int width = 0 , height = 0, colordepth;
			
			while( in.hasNext()) {
				
				in.next();
				width = in.nextShort();
				height = in.nextShort();
				colordepth = in.nextShort();
				if(i == 1)
					return width; 
				else if(i == 2) 
					return height;
				else if( i == 3) 
					return colordepth;
			}
    	}
    	catch (IOException ex) {
    		ex.printStackTrace();
    	}
		
		return 0;
	}
	
    public PPMImage (java.io.File file) throws UnsupportedFileFormatException, IOException {
    	
       	super(fileGet(file, 2) , fileGet(file, 1), fileGet(file, 3));
       	String pathname;
       	pathname = file.getAbsolutePath();
       	try (Scanner in = new Scanner(new FileReader(pathname) )){
    		int i = 0;
			int width = 0 , height = 0;
			
			while( in.hasNext()) {
				if(i == 0) {
					title = in.nextLine() ;	
				}
				else if(i == 1) {
					width = in.nextShort();  
					}
				else if(i == 2) {
					height = in.nextShort();
				}
				else {
					in.nextShort();
					for(int j = 0; j < height; j++) {
						if(!in.hasNext())
							break;
						for(int k = 0; k < width; k++) {
							if(!in.hasNext())
								break;	
							this.getImage()[k][j] = new RGBPixel(in.nextShort(), in.nextShort(), in.nextShort() ); 
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
    	
	public PPMImage(RGBImage img) {
		
		super(img);	
		title = "P3";
	}
	
	
	public PPMImage (YUVImage img){
		super(img);
		title = "P3";
   }
	
	public String toString() {
		
		String str ;
		
		str = title + "\n" + 
			  super.getImage()[0].length + " " +
			  super.getImage().length + "\n" +
			  super.getColordepth() + "\n";
		str = str + super.toString();
		return str;
	}
	
	public void toFile(java.io.File file) throws IOException {
		
		try (FileWriter out = new FileWriter(file, false)){
			
			out.write(this.toString());
			System.out.println("File saved !");
		} 
		catch (FileNotFoundException e) {
		    System.err.println("File doesn't exist");
		    e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return super.getWidth();
	}
	public int getHeight() {
		return super.getHeight();
	}
	public RGBImage getRGB() {
		RGBImage newimg = new RGBImage(this.getWidth(), this.getHeight(), this.getColordepth());
		for(int i = 0 ; i < this.getHeight(); i++) {
			for(int j = 0; j < this.getWidth(); j++) {
				newimg.setPixel(i, j, this.getPixel(j, i));
			}
		}
		return newimg;
	}
}

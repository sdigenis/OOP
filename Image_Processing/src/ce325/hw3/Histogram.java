package ce325.hw3;

import java.io.*;

public class Histogram {
	
	private int histogram[];
	private double sumpos[];
	private YUVImage image;
	private int maxVal;
	
	public Histogram(YUVImage image) {
		
		int maxVal = 0; 
		double pos[];
		double size = image.getHeight() * image.getWidth();
		int i, j;
		
		for(i = 0; i < image.getHeight(); i++) {
			for(j = 0; j < image.getWidth(); j++) {
				if (maxVal < image.getImage()[i][j].getY()) {
					maxVal = image.getImage()[i][j].getY();
				}
			}
		}
		//System.out.println(maxVal);
		histogram = new int[maxVal + 1];
		sumpos = new double[maxVal + 1];
		for(i = 0; i < image.getHeight(); i++) {
			for(j = 0; j < image.getWidth(); j++) {
				histogram[image.getImage()[i][j].getY()]++;
			}
		}
		
		this.image = new YUVImage(image); 
		pos = new double [maxVal + 1];
		
		for(i = 0; i < maxVal; i++) {
			pos[i] = (double)histogram[i] / size;
		}
		sumpos[0] = pos[0];
		for(i = 1; i < maxVal; i++) {
			
			sumpos[i] = pos[i];
			sumpos[i] = ( pos[i] + sumpos[i-1]);
		}
		for(i = 0; i < maxVal; i++) {
			sumpos[i] = sumpos[i] * 235;
			//System.out.printf("%f\n", sumpos[i]);
		}
		for(i = 0; i < maxVal; i++) {
			sumpos[i] = (int) sumpos[i]; 
			//System.out.println(sumpos[i]);
		}
	}

	
	public String toString() {
		String str="";
		for(int i = 0; i < histogram.length; i++) {
			str = i + ": ";
			for(int j = 0; j < histogram[i]; j++) {
				str = str + "*";
				if(histogram[i] > 80 && j > 80) 
					break;
			}
			str = str + "\n";
		}
		return str ;
	}
	
	/*
	public void toFile(java.io.File file) throws IOException {
		
		try (FileWriter out = new FileWriter(file, false)){
			
			out.write(this.toString());
			System.out.println("File saved !");
		} 
		catch (FileNotFoundException e) {
		    System.err.println("File doesn't exist");
		    e.printStackTrace();
		}
	}*/
	
	public void toFile(java.io.File file) throws IOException{
		String content = this.toString(); 
		
		if(file.exists()) {   											//αν η εικονα υπαρχει
			if(file.length() == 0) { 									//αν δεν εχει περιεχομενο προς διαγραφη
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
					PrintWriter writer = new PrintWriter(file.getName()); 	//https://www.baeldung.com/java-delete-file-contents
					writer.print("");
					writer.append(content);									//γραψε το νεο περιεχομενο
					writer.close();
				}
				catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		else {																//αν η εικονα δεν υπαρχει
			File newFile = new File(file.getName());						//φτιαξε ενα αρχειο με ιδιο ονομα
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
		
		for(int j = 0; j < image.getHeight(); j++) {
			for(int k = 0; k < image.getWidth(); k++) {
				for(int i = 0; i < maxVal ; i++) {
					if(i == image.getImage()[j][k].getY()) {
						//System.out.println("Color changed");
						//System.out.println((short)sumpos[i]);
						//System.out.println(j + " " + k + " "+ i);
						image.getImage()[j][k].setY((short)sumpos[i]);
					}
				}
			}
		}
	}
	
	public short getEqualizeLuminocity(int luminocity) {
		int i;
		for(i = 0; i < 235; i++) {
			if (luminocity == i )
				break;
		}
		return (short)sumpos[i];
	}
}

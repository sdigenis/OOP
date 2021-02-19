package ce325.hw3;

public class RGBImage implements Image {
	
	private RGBPixel [][]image;
	private int colordepth;
	
	
	public RGBImage(int width, int height, int colordepth) {
		this.image = new RGBPixel[height][width];
		this.colordepth = colordepth ;
		for(int i = 0 ; i < image.length; i++){
			for(int j = 0; j < image[0].length; j++){
		        image[i][j] = new RGBPixel((short) 0, (short) 0, (short) 0);
			}
		}
	}
	
	public RGBImage(RGBImage copyImg) {
		image = new RGBPixel[copyImg.getHeight()][copyImg.getWidth()];
		for(int i = 0; i < copyImg.getHeight(); i++) {
			for(int j = 0; j < copyImg.getWidth(); j++) {
				this.image[i][j] = new RGBPixel(copyImg.image[j][i]);
			}
		}
		this.colordepth = copyImg.colordepth;
	}
	
	public RGBImage(YUVImage YUVImg){
		image = new RGBPixel[YUVImg.getImage().length][YUVImg.getWidth()];
		for(int i = 0; i < YUVImg.getImage().length; i++) {
			for(int j = 0; j < YUVImg.getWidth(); j++) {
				image[i][j] = new RGBPixel(YUVImg.getPixel(i,j).transform(YUVImg.getPixel(i,j)));
			}
		}
		colordepth = 255;
	}

	
	public RGBPixel[][] getImage() {
		return this.image;
	}
	
	public void grayscale() {
		double red, green, blue;
		double gray;
		
		for(int i = 0; i < this.image.length; i++) {
			for(int j = 0; j < this.image[0].length; j++) {
				
				
				red = 0.3 * (int)this.image[i][j].getRed();
				green = 0.59 * (int)this.image[i][j].getGreen();
				blue = 0.11 * (int)this.image[i][j].getBlue();
						
				gray = red + green + blue;
		
				image[i][j].setRed((short)gray);
				image[i][j].setGreen((short)gray);
				image[i][j].setBlue((short)gray);
			}
		}
	}

	public void doublesize() {
		int newwidth = this.image[0].length * 2;
		int newheight = this.image.length *2;
		RGBImage newImage = new RGBImage(newwidth, newheight, 255);
		
		for(int i=0; i < newheight / 2 ; i++) {
			for(int j=0; j < newwidth / 2; j++) {
				newImage.image[2*i][2*j] = this.image[i][j];
				newImage.image[2*i+1][2*j] = this.image[i][j];
				newImage.image[2*i][2*j+1] = this.image[i][j];
				newImage.image[2*i+1][2*j+1] = this.image[i][j];
			}
		}
		this.image = newImage.image;
	}
	
	public void halfsize() {
		int newwidth = (this.image[0].length) / 2;
		int newheight = (this.image.length) / 2;
		RGBImage newImage = new RGBImage(newwidth, newheight, 255);
		RGBPixel first, second, third, fourth;    
		int half=0;
		for(int i = 0; i < newheight; i++) {
			for(int j = 0; j < newwidth ; j++) {
				half = 0 ;
				 first = this.image[2*i][2*j].getPixel();            // χρησιμοποιω τα pixel που χρειάζεται κάθε φορά 
				 second = this.image[2*i+1][2*j].getPixel();
				 third = this.image[2*i][2*j+1].getPixel();
				 fourth = this.image[2*i+1][2*j+1].getPixel();
				 half = first.addPixel(second, third, fourth);     // τα προσθετω μεταξυ τους και τα αποθηκευω στην μεταβλητη αυτη 
				 													// 
				 half = half / 4 ;  						
				 RGBPixel pixel = new RGBPixel((int)half);													// κανω την διαιρεση για να παρω τον μεσο ορο των pixel αν και ισως να χρειαζεται
				 newImage.setPixel(i, j, pixel);			// για καθε χρωμα ξεχωριστα πρεπει να δω ενα παραδειγμα για να ειμαι σιγουρος
			}														// και μετα το αναθετω στον καινουργιο πινακα		
		}
		this.image = newImage.image ;
	}
	public void rotateClockwise() {
        int newheight = this.image[0].length;  
        int newwidth = this.image.length; 
        int colordepth = this.colordepth;
        RGBImage newImage = new RGBImage(newwidth,newheight,colordepth);
        int i, j, x;

         for(i = newheight -1, x = 0; i >= 0 && x <= this.image[0].length;  x++, i--) {
            for(j = 0; j < newwidth; j++) {
                newImage.image[x][j] = this.image[j][i];
             }
         }
   
         this.image = newImage.image;    
 }        
	
	public String toString() {
		
		String str = "" ;
		for(int i = 0; i < this.getWidth(); i++) {
			for(int j = 0; j < this.getHeight(); j++) {
				str = str + this.image[i][j].getRed() + " " 
						  + this.image[i][j].getGreen() + " "
						  + this.image[i][j].getBlue() + " ";
				str = str + "\n";
			}
			
		}
		return str;
	}
	
	public int getWidth() {
		return this.image.length;
	}
	
	public int getHeight() {
		return this.image[0].length;
	}

	public int getColordepth() {
		return this.colordepth;
	}
	public RGBPixel getPixel(int row, int col) {
		return image[col][row];
	}
	public RGBPixel getPixel2(int row, int col) {
		return image[row][col];
	}
	public void setPixel(int row, int col, RGBPixel  pixel) {
		image[row][col] = pixel;
	}
	public void setColodepth(int colordepth) {
		this.colordepth = colordepth;
	}
}


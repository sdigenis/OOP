package ce325.hw3;

public class RGBPixel {

	private int  rgbpixel;
	
	public RGBPixel(short red, short green, short blue) {
		
		rgbpixel = 0;
		rgbpixel = rgbpixel << 8;
		rgbpixel = red;
		//System.out.println(Integer.toBinaryString(rgbpixel));
		rgbpixel = rgbpixel << 8;
		//System.out.println(Integer.toBinaryString(rgbpixel));
		rgbpixel = rgbpixel + green;
		//System.out.println(Integer.toBinaryString(rgbpixel));
		rgbpixel = rgbpixel << 8;
		//System.out.println(Integer.toBinaryString(rgbpixel));
		rgbpixel = rgbpixel + blue;	
		//System.out.println(Integer.toBinaryString(rgbpixel));
	}
	
	public RGBPixel(RGBPixel pixel) {
		this.rgbpixel = pixel.rgbpixel;
	}
	public RGBPixel(int pixel) {
		this.rgbpixel = pixel;
	}
	
	public RGBPixel(YUVPixel pixel) {
	        int c,d,e;
	        short red,green,blue;
	        
	        c=pixel.getY() - 16;
	        d=pixel.getU() - 128;
	        e=pixel.getV() - 128;
	       
	        red = clip(( 298 * c + 409 * e + 128) >> 8);
	        green = clip(( 298 * c - 100 * d - 208 * e + 128) >> 8);
	        blue = clip(( 298 * c + 516 * d+ 128) >> 8);

	        rgbpixel = red;
			rgbpixel = rgbpixel << 8;
			rgbpixel = rgbpixel + green;
			rgbpixel = rgbpixel << 8;
			rgbpixel = rgbpixel + blue;	  
   }
	
	public short clip(int number) {
		if(number < 0 )
			return 0;
		else if(number > 255)
			return 255;
		else 
			return (short)number;
	}

	public short getRed(){
		//System.out.println(Integer.toBinaryString(rgbpixel));

		int red = rgbpixel >> 16;
		//System.out.println(Integer.toBinaryString(red));

		return (short)red;
	}
	
	public short getGreen() {
		//System.out.println(Integer.toBinaryString(rgbpixel));
		int green = rgbpixel << 16;
		//System.out.println(Integer.toBinaryString(green));
		
		green = green >>> 24;
		
		//System.out.println(Integer.toBinaryString(green));

		return (short)green;
	}
	
	public short getBlue() {
		int blue = rgbpixel << 24;
		blue = blue >>> 24;
		return (short)blue;
	}

	public void setRed(short red) {
		int kokkino = red;
		short blue = this.getBlue();
		short green = this.getGreen(); 
		kokkino = kokkino << 16;
		int prasino = (int)green;
		prasino = prasino << 8;
		int mple = (int)blue;
		this.rgbpixel = kokkino + prasino + mple;
	}
	
	public void setGreen(short green) {
		int prasino = green;
		short blue = this.getBlue();
		short red = this.getRed(); 
		prasino = prasino << 8;
		int kokkino = red << 16;
		int mple = (int)blue;
		this.rgbpixel = kokkino + prasino + mple;
	}
	
	public void setBlue(short blue) {
		int mple = blue;
		short green = this.getGreen();
		short red = this.getRed(); 
		int prasino = green << 8;
		int kokkino = red << 16;
		this.rgbpixel = kokkino + prasino + mple;
	}
	
	public RGBPixel getPixel() {
		return this;
	}
	

	public void setPixel(int pixel) {
		
		this.rgbpixel = pixel;
	}
	
	public int addPixel(RGBPixel first, RGBPixel second, RGBPixel third) {
		
		int sum;
		sum = first.rgbpixel + second.rgbpixel + third.rgbpixel + this.rgbpixel;
		
		return sum;
	}
	
	public int getRGB() {
		return this.rgbpixel;
	}
	public void setRGB(int value) {
		this.rgbpixel = value;
	}
	public final void setRGB(short red, short green, short blue) {
		this.setRed(red);
		this.setGreen(green);
		this.setBlue(blue);
	}
	public String toString() {
		
		String str = null;
		str = "(" + this.getRed() + 
				"," + this.getGreen() +
				"," + this.getBlue() +
				")";
		return str;
	}
	
	public int transform(RGBPixel pixel) {
		
		short red, green, blue;
		int y, u, v;
		int finalpixel;
		
		red = pixel.getRed();
		green = pixel.getGreen();
		blue = pixel.getBlue();
		
		y = ( ( (red * 66) + (129 * green) + (25 * blue) + 128) >> 8) + 16;
		u = ( ( (-38 * red) + (-74 * green) + (112 * blue) + 128) >> 8) + 128;
		v = ( ( (112 * red) + (-94 * green) + (-18 * blue) + 128) >> 8) + 128;
		
		finalpixel = y;
		finalpixel = finalpixel << 8;
		finalpixel = finalpixel + u;
		finalpixel = finalpixel << 8;
		finalpixel = finalpixel + v;	
		
		return finalpixel;
	}
}

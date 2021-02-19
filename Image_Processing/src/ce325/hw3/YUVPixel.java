package ce325.hw3;

public class YUVPixel {
	
	private int yuvPixel;
	
	public YUVPixel(short y, short u, short v) {
		
		yuvPixel = 0;
		yuvPixel = yuvPixel << 8;
		yuvPixel = y;
		yuvPixel = yuvPixel << 8;
		yuvPixel = yuvPixel + u;
		yuvPixel = yuvPixel << 8;
		yuvPixel = yuvPixel + v;
	}
	
	public YUVPixel(YUVPixel pixel) {
		yuvPixel = pixel.yuvPixel;
	}
	public YUVPixel(int pixel) {
		yuvPixel = pixel;
	}
	
	public YUVPixel(RGBPixel pixel) {
		
		short red, green, blue;
		int y, u, v;
		red = pixel.getRed();
		green = pixel.getGreen();
		blue = pixel.getBlue();
		
		y = ( ( (red * 66) + (129 * green) + (25 * blue) + 128) >> 8) + 16;
		u = ( ( (-38 * red) + (-74 * green) + (112 * blue) + 128) >> 8) + 128;
		v = ( ( (112 * red) + (-94 * green) + (-18 * blue) + 128) >> 8) + 128;
		yuvPixel = y;
		yuvPixel = yuvPixel << 8;
		yuvPixel = yuvPixel + u;
		yuvPixel = yuvPixel << 8;
		yuvPixel = yuvPixel + v;	
	}
	
	public short getY() {
		
		int y = yuvPixel >> 16;
		return (short)y;
	}
	
	public short getU() {
		int u = yuvPixel << 16;
		u = u >>> 24;
		return (short)u;
	}
	
	public short getV() {
		
		int v = yuvPixel << 24 ; 
		v = v >>> 24;
		return (short)v;
	}
	
	public YUVPixel getPixel() {
		
		return this;
	}
	
	public void setY(short y) {
		int why = y;
		int u = this.getU();
		int v = this.getV();
		why = why << 16;
		u = u << 8;
		this.yuvPixel = why + u + v;
	}
	
	public void setU(short u) {
		int you = u;
		int v = this.getV();
		int y = this.getY();
		y = y << 16;
		you = you << 8;
		this.yuvPixel = y + you + v;
	}

	public void setV(short v) {
		int ve = v;
		int y = this.getY();
		int u = this.getU();
		y = y << 16;
		u = u << 8;
		this.yuvPixel = y + u + ve;
	}
	
	public void setYUV(int pixel) {
		this.yuvPixel = pixel;
	}
	
	public final void setYUV(short y, short u, short v) {
		this.setY(y);
		this.setU(u);
		this.setV(v);
	}
	public String toString() {
		
		String str = null;
		str = "(" + this.getY() + 
				"," + this.getU() +
				"," + this.getV() +
				")";
		return str;
	}
	
	public int transform(YUVPixel pixel) {
		
		 int c,d,e;   
		 short red,green,blue;
		 int finalpixel;
	        
		 c = pixel.getY() - 16;
		 d = pixel.getU() - 128;
		 e = pixel.getV() - 128;
	       
		 red = clip(( (298 * c) + (409 * e) + 128) >> 8);
		 green = clip(( (298 * c) - (100 * d) - (208 * e) + 128) >> 8);
		 blue = clip(( (298 * c) + (516 * d) + 128) >> 8);
	     finalpixel = 0;
	     finalpixel = finalpixel << 8;
		 finalpixel = red ;
		 finalpixel = finalpixel << 8;				 
		 finalpixel = finalpixel + green;
		 finalpixel = finalpixel << 8;
		 finalpixel = finalpixel + blue;	  
	        
		return finalpixel ;
	}
	
	public short clip(int number) {
		if(number < 0 )
			return 0;
		else if(number > 255)
			return 255;
		else 
			return (short)number;
	}
}

package mandlebrot_v3;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GetSetPixels{
	
  public static BufferedImage openImg(String s)
  {
	   BufferedImage img = null;
	   File f = null;

	   //read image
	   try{
	     f = new File(s);
	     img = ImageIO.read(f);
	   }catch(IOException e){
	     System.out.println("Error reading: " + e);
	   }
	    
	   return img;
  }
  
  public static void writeImg(BufferedImage img, String s)
  {
	File f = null;
	try{
	    f = new File(s);
	    ImageIO.write(img, "png", f);
	  }catch(IOException e){
	    System.out.println("Error writing: " + e);
	  }
  }
  	
	public static int getPixel(BufferedImage img, int x, int y)
	{
	    //get image width and height
    int width = img.getWidth();
    int height = img.getHeight();


    //get pixel value
    if (x >= width || y >= height)
    		return -1;
    else
    	return img.getRGB(x,y);
}  
  
	
	//read pixel values
	public static int getA(int p)
	{
		return (p>>24) & 0xff;
	}
	
	public static int getR(int p)
	{
		return (p>>16) & 0xff;
	}
	public static int getG(int p)
	{
		return (p>>8) & 0xff;
	}
	public static int getB(int p)
	{
		return p & 0xff;
	}
	
	//set pixel values
	public static int setA(int p, int a)
	{
		return p | (a<<24);
	}
	
	public static int setR(int p, int r)
	{
		return p | (r<<16);
	}
	public static int setG(int p, int g)
	{
		return p | (g<<8);
	}
	public static int setB(int p, int b)
	{
		return p | b;
	}
	
	//set actual image pixel
	public static void setARGB(BufferedImage img, int x, int y, int p)
	{
		img.setRGB(x, y, p);
	}
}


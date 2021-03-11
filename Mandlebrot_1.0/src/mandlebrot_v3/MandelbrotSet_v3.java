package mandlebrot_v3;
import java.awt.image.BufferedImage;

public class MandelbrotSet_v3 extends Thread {
	
	//LIGHTNING
//	static double focusA = -0.235125; //focus for A axis
//	static double focusB = 0.827215; //for B axis
//	static double radius = 0.00004;
	
	//BASIC
//	static double focusA = -0.5; //focus for A axis
//	static double focusB = 0; //for B axis
//	static double radius = 1;
	
	////COOL
//	static double focusA = -0.7463; //focus for A axis
//	static double focusB = 0.1102; //for B axis
//	static double radius = 0.005;
	
//	static double focusA = -0.722; //focus for A axis
//	static double focusB = 0.246; //for B axis
//	static double radius = 0.019;

	 //fA = -1.1570776551228361, Fb = 0.2 //pow = 2
//	static double focusA = -1.1533577030005; //focus for A axis
//	static double focusB = 0.307486987838885; //for B axis
//	static double radius = 0.00000000053;
	
	//fA = -0.7240082527548029, Fb = 0.5 //pow = 4
//	static double focusA = -0.7240082527548029; //focus for A axis
//	static double focusB = 0.5; //for B axis
//	static double radius = 0.0000000001;
	
	static double focusA = 0; //focus for A axis
	static double focusB = 0; //for B axis
	static double radius = 2;
	
	private int ID;
	private BufferedImage  image;
	static final int NUM_THREADS = 4;
	
	private static int pow = 2;
	
	
	public static void main(String[] args) {
        int width = 1080;
        int height = 720;
        double start = System.currentTimeMillis();
        
        BufferedImage image = new BufferedImage(width + 1, height + 1, BufferedImage.TYPE_INT_BGR);
        
        Thread[] r = new Thread[NUM_THREADS];
        
        for (int i = 0; i < NUM_THREADS; i++)
        {
        	r[i] = new MandelbrotSet_v3(image, i);
        	r[i].start();        	
        }
        
        
			try {
				for (int i = 0; i < NUM_THREADS; i++)
					r[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
        System.out.println("Time taken: " + (System.currentTimeMillis() - start));
        GetSetPixels.writeImg(image, "D:/(programmingProjects)/Java_workplace_highschool/Mandlebrot_1.0/images/MandlebrotSet_v1.3.png");
    }
    
    
    
    
    
    
    public static int Calculate(Complex z, int loopFor){
        Complex tmp = new Complex();
        Complex t;
        
        for(int i = 1; i <= loopFor; i++){
        	t = new Complex(tmp);
        	for (int j = 1; j < pow; j++)
        		t = Complex.multiply(t, tmp);
        	
            tmp = Complex.add(t, z);
            //if (tmp.dist() >= 2.0)
            if (Math.pow(tmp.getA(), 2) + Math.pow(tmp.getB(), 2) >= 4.0)
                return i;
        }
        return 0;
    }
    
    
	   public MandelbrotSet_v3(BufferedImage i, int id) {
	       // store parameter for later use
		   image = i;
		   ID = id;
	   }

	   public void run() {

		   
	    	int quality = 10000; //higher quality if number is larger        
	        int counterW = 0;
	        int counterH = ID * (image.getHeight()- 1) / (NUM_THREADS);
	        int p = 0;
	        
	        boolean lastBlack = false;
	   
	        double rate;
	        
	        if (image.getHeight() >= image.getWidth())
	        	rate = (2*radius)/(image.getHeight() - 1);
	        else
	        	rate = (2*radius)/(image.getWidth() - 1);
	        
		    double offset = rate*(image.getHeight()-1)/NUM_THREADS;

	        for (double h = focusB + rate*((image.getHeight()-1)/2.0) - ID * offset; counterH < (ID + 1.0) * (image.getHeight()- 1.0) / (NUM_THREADS); h -= rate) {
	            for (double w = focusA - rate*((image.getWidth()-1)/2.0); counterW < image.getWidth(); w += rate) {
	            	p = 0;
	                int k = MandelbrotSet_v3.Calculate(new Complex(w, h), (quality));
	                p = GetSetPixels.setA(p, 0);

	                if (k == 0)
	                {
	                    p = 0;    
	                    
	                    GetSetPixels.setARGB(image, counterW, counterH, p);	 
	                    
	                    if (lastBlack && counterW - 2 >= 0)
	                    {
	                    	GetSetPixels.setARGB(image, counterW - 1, counterH, p);
	                    }
	                    
	                    lastBlack = true;
	                    
	                    if (counterW + 2 < image.getWidth())
	                    {
		                    counterW += 2;
		                    w += rate; //decrease w to account for jump over counterW + 1;	                    	
	                    }
	                    else
	                    {
	                    	counterW++;
	                    	lastBlack = false;
	                    }

	                }
	                else
	                {
	                	p = getColor(k);
	                	GetSetPixels.setARGB(image, counterW, counterH, p);
	                	
	                	if (lastBlack && counterW - 1 >= 0)
	                	{
	                		lastBlack = false;
	                		
	                		k = MandelbrotSet_v3.Calculate(new Complex(w - rate, h), (quality));
	                		p = getColor(k);
	                		
		                	GetSetPixels.setARGB(image, counterW - 1, counterH, p);
	                	}

	                	counterW++;
	                }
	            }
	            counterH++;
	            counterW = 0;
	            if (counterH %100 == 0)
	            		System.out.println("Thread " + ID + ": " + counterH + "/" + (ID + 1) * (image.getHeight()- 1) / (NUM_THREADS));
	        }
	   }
	   
	   public static int harrisonColor(int k, int quality) {
		   int color = 0;
		   color = GetSetPixels.setR(color, 0);
		   color = GetSetPixels.setG(color, (int)(235 * (1 - (double)(Math.pow(k, 2) / Math.pow(quality - 1, 2)))));
		   color = GetSetPixels.setB(color, (int)(255 * (1 - (double)(Math.pow(k, 2) / Math.pow(quality - 1, 2)))));
		   return color;
	   }
	   
	   public static int getColor(int k) {
		   int p = 0;
		   
       	//cool 1
//        p = GetSetPixels.setR(p, (k) % 255);
//        p = GetSetPixels.setB(p, (k - 100) % 255);
//        p = GetSetPixels.setG(p, (k - 200) % 255);         
    	
    	//cool2
//        p = GetSetPixels.setB(p, (k) % 255);
//        p = GetSetPixels.setR(p, (k - 127) % 255);
//        p = GetSetPixels.setG(p, (k - 255) % 255);  
        
    	///SUPER COOL GREEN?BLUE??
//        p = GetSetPixels.setG(p, (k) % 150);
//        p = GetSetPixels.setB(p, k % 250);//(k + 50) % 255);
//        p = GetSetPixels.setR(p, k % 5);//(k - 205) % 255); 
        
    	//blue
//        p = GetSetPixels.setG(p, (k) % 255);
//        p = GetSetPixels.setB(p, 255);
//        p = GetSetPixels.setR(p, (k - 255) % 255); 
        
        //KEVINNNNNNN
        p = GetSetPixels.setR(p, (k%255%50*30) + 000);	                		
        p = GetSetPixels.setG(p, (k%255%50*40) + 100);
        p = GetSetPixels.setB(p, (k%255%50*40) + 100);

        //HARRISON
//		p = harrisonColor((quality - k), quality);
		   
		   return p;
	   }

}
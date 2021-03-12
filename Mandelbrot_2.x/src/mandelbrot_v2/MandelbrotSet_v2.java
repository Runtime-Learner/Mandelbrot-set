package mandelbrot_v2;
import java.awt.image.BufferedImage;

public class MandelbrotSet_v2 extends Thread {
	
//	LIGHTNING
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

//	 fA = -1.1570776551228361, Fb = 0.2 //pow = 2
//	static double focusA = -1.1533577030005; //focus for A axis
//	static double focusB = 0.307486987838885; //for B axis
//	static double radius = 0.00000000053;
	
//	//fA = -0.7240082527548029, Fb = 0.5, radius = 0.0000000001//pow = 4
//	static double focusA = -0.7240082527548029; //focus for A axis
//	static double focusB = 0.5; //for B axis
//	static double radius = 0.0000000001;
	
	
//  //fA = -0.6490515932103498, Fb = 0.5	//pow 3
//	static double focusA = -0.6490515932103498; //focus for A axis
//	static double focusB = 0.5; //for B axis
//	static double radius = 0.000001;
	
	static double focusA = 0; //focus for A axis
	static double focusB = 0; //for B axis
	static double radius = 2;
	
	private int ID;
	private BufferedImage  image;
	
	
	static final int NUM_THREADS = 4; //MUST be an even number
	private static int pow = 2;
	
	
	public static BufferedImage edge(BufferedImage i){
		BufferedImage temp = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_INT_BGR);
		
		
		
		return temp;
	}
	
	
	public static void main(String[] args) 
	{
        int width = 1920*1;
        int height = 1080*1;
        double start = System.currentTimeMillis();
        
        BufferedImage image = new BufferedImage(width + 1, height + 1, BufferedImage.TYPE_INT_BGR);
        
        int p;
        for(int i = 0; i < height; i++)
        {
        	if (image.getRGB(0, i) != -16777216)
        	{
        		System.out.println("Error in code! at row: " + i);
        		return;	        		
        	}
        }
        
        Thread[] r = new Thread[NUM_THREADS];
        
        for (int i = 0; i < NUM_THREADS; i++)
        {
        	r[i] = new MandelbrotSet_v2(image, i);
        	r[i].start();        	
        }
        
        
		try {
			for (int i = 0; i < NUM_THREADS; i++)
				r[i].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
        System.out.println("Time taken: " + (System.currentTimeMillis() - start));
        GetSetPixels.writeImg(image, "D:/(programmingProjects)/Java_workplace_highschool/Mandelbrot_2.x/images/mandelbrotset_v2.2.png");
    }
    
    
    public static int Calculate(Complex z, int loopFor){
        Complex tmp = new Complex();
        Complex t = new Complex();
        double x1 = tmp.getA(), x2 = tmp.getA(), y1 = tmp.getB(), y2 = tmp.getB();	//bounding box to check for convergence
        double tmpA, tmpB;
        int noChange = 0;
        
        for(int i = 1; i <= loopFor; i++){ //or we could just have initialized as 1 in the first place, but shut up
        	t.setTo(tmp);
        	for (int j = 1; j < pow; j++)
        		t.multiply(tmp);
        	
            tmp.setToAdd(t, z);
            tmpA = tmp.getA();
            tmpB = tmp.getB();
            
            if (x1 > tmpA) {
            	x1 = tmpA;
            	noChange = 0;
            } else if (x2 < tmpA) {
            	x2 = tmpA;
            	noChange = 0;
            }
            if (y1 > tmpB) {
             y1 = tmpB;
             noChange = 0;
            } else if (y2 < tmpB) {
            	y2 = tmpB;
            	noChange = 0;
            }
            noChange++;
            
            if (noChange > 10000) {
            	return 0;
            }
            
            if (tmpA * tmpA + tmpB * tmpB >= 4.0)
                return i;
        }
        return 0;
    }
    
	   public MandelbrotSet_v2(BufferedImage i, int id) {
	       // store parameter for later user
		   image = i;
		   ID = id;
	   }

	   @SuppressWarnings("unused")
	public void run() {
		   int quality = 50000; //higher quality if number is larger    
		   
		   if (NUM_THREADS % 2 != 0)
			   oddThread(quality);
		   else if (ID % 2 == 0)
			   oddThread(quality);
		   else if (ID % 2 != 0)
			   evenThread(quality);
		   
		   return;
	   }
	   
	   public void evenThread(int quality)
	   {
		   //System.out.println("EVEN!");
		   Complex calcTemp = new Complex();
		   
	        int counterW = 0;
	        int counterH = (ID + 1) * (image.getHeight()- 1) / (NUM_THREADS);
	        int k, p = 0;
	        
	        boolean lastBlack = false;
	        
	        
	        double rate;
	        
	        if (image.getHeight() > image.getWidth())
	        	rate = (2*radius)/(image.getHeight() - 1);
	        else
	        	rate = (2*radius)/(image.getWidth() - 1);
	        
		    double offset = rate*(image.getHeight()-1)/NUM_THREADS;
	        		
	        for (double h = focusB + rate*((image.getHeight()-1)/2.0) - (ID + 1) * offset; counterH > 0; h += rate) {
	        	if (image.getRGB(0, counterH) != -16777216)
	        	{
	        		System.out.println("Even Thread " + ID + " is done at row " + counterH);
	        		return;	        		
	        	}

	        	
	            for (double w = focusA - rate*((image.getWidth()-1)/2.0); counterW < image.getWidth(); w += rate) {
	            	p = 0;
	            	
	            	calcTemp.setTo(w, h);
	                k = MandelbrotSet_v2.Calculate(calcTemp, (quality));
	                p = GetSetPixels.setA(p, 0);

	                if (k == 0)
	                {
	                    p = GetSetPixels.setR(p, 1);
	                    p = GetSetPixels.setB(p, 0);
	                    p = GetSetPixels.setG(p, 0);     
	                    
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
	                		calcTemp.setTo(w - rate, h);
	                		k = MandelbrotSet_v2.Calculate(calcTemp, (quality));
	                		
	                		p = getColor(k);
			                
		                	GetSetPixels.setARGB(image, counterW - 1, counterH, p);
	                	}
	                	counterW++;
	                }
	            }
	            counterH--;
	            counterW = 0;
	            if (counterH %100 == 0)
	            		System.out.println("Even Thread " + ID + ": " + counterH + "/" + (ID + 1) * (image.getHeight()- 1) / (NUM_THREADS));
	        }
	   }
	   
	   public void oddThread(int quality)
	   {
		   //System.out.println("ODD!");
		   Complex calcTemp = new Complex();
		         
	        int counterW = 0;
	        int counterH = ID * (image.getHeight()- 1) / (NUM_THREADS);
	        int k, p = 0;
	        
	        boolean lastBlack = false;
	        
	        
	        double rate;
	        
	        if (image.getHeight() > image.getWidth())
	        	rate = (2*radius)/(image.getHeight() - 1);
	        else
	        	rate = (2*radius)/(image.getWidth() - 1);
	        
		    double offset = rate*(image.getHeight()-1)/NUM_THREADS;

		    int height = image.getHeight();
	        
	        for (double h = focusB + rate*((image.getHeight()-1)/2.0) - ID * offset; counterH < height; h -= rate) {
	        	if (image.getRGB(0, counterH) != -16777216)
	        	{
	        		if (ID <= 1)
	        		{
	        			while(counterH < height && image.getRGB(0, counterH) != -16777216){
	        				counterH++;	        				
	        				h -= rate;
	        			}
	        			h+= rate;
		        		continue; //check all rows uderneath to see if none have been missed 		        			
	        		}
	        		else
	        		{
		        		System.out.println("Odd Thread " + ID + " is done at row " + counterH);
		        		return;	      
	        		}
	        	}
	        	
	            for (double w = focusA - rate*((image.getWidth()-1)/2.0); counterW < image.getWidth(); w += rate) {
	            	p = 0;
	            	calcTemp.setTo(w, h);
	                k = MandelbrotSet_v2.Calculate(calcTemp, (quality));
	                p = GetSetPixels.setA(p, 0);

	                if (k == 0)
	                {
	                    p = GetSetPixels.setR(p, 1);
	                    p = GetSetPixels.setB(p, 0);
	                    p = GetSetPixels.setG(p, 0);     
	                    
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
	                		calcTemp.setTo(w - rate, h);
	                		k = MandelbrotSet_v2.Calculate(calcTemp, (quality));
	                		
	                		p = getColor(k);
			                
		                	GetSetPixels.setARGB(image, counterW - 1, counterH, p);
	                	}
	                	counterW++;
	                }
	            }
	            counterH++;
	            counterW = 0;
	            if (counterH %100 == 0)
	            		System.out.println("Odd Thread " + ID + ": " + counterH + "/" + (ID + 1) * (image.getHeight()- 1) / (NUM_THREADS));
	        }
	   }
	   
		public static int getColor(int k) {
			int p = 0;

        	//cool 1
//            p = GetSetPixels.setR(p, (k) % 255);
//            p = GetSetPixels.setB(p, (k - 100) % 255);
//            p = GetSetPixels.setG(p, (k - 200) % 255);         
        	
        	//cool2
//            p = GetSetPixels.setB(p, (k) % 255);
//            p = GetSetPixels.setR(p, (k - 127) % 255);
//            p = GetSetPixels.setG(p, (k - 255) % 255);  
            
        	///SUPER COOL GREEN?BLUE??
//            p = GetSetPixels.setG(p, (k) % 150);
//            p = GetSetPixels.setB(p, k % 250);//(k + 50) % 255);
//            p = GetSetPixels.setR(p, k % 5);//(k - 205) % 255); 
            
        	//blue
//            p = GetSetPixels.setG(p, (k) % 255);
//            p = GetSetPixels.setB(p, 255);
//            p = GetSetPixels.setR(p, (k - 255) % 255); 
            
            //KEVINNNNNNN
            p = GetSetPixels.setG(p, (k%255%50*50) + 100);
            p = GetSetPixels.setB(p, (k%255%50*50) + 150);
            p = GetSetPixels.setR(p, (k%255%50*50) + 000);

			return p;
		}
}
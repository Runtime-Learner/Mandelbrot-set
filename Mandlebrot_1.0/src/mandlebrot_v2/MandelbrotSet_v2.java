package mandlebrot_v2;
import java.awt.image.BufferedImage;

public class MandelbrotSet_v2 {
	
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
	
	
	static double focusA = 0; //focus for A axis
	static double focusB = 0; //for B axis
	static double radius = 2;
	
    public static void main(String[] args) {
    	int quality = 10; //higher quality if number is larger
        int width = 720;
        int height = width;
        int counterW = 0;
        int counterH = 0;
        int p = 0;
        
        BufferedImage image = new BufferedImage(width + 1, height + 1, BufferedImage.TYPE_INT_BGR);
        
        for (double h = focusB + radius; h >= focusB - radius; h -= (2*radius)/height) {
            for (double w = focusA - radius; w <= focusA + radius; w += (2*radius)/width) {
            	p = 0;
                int k = MandelbrotSet_v2.Calculate(new Complex(w, h), (255 * quality));
                p = GetSetPixels.setA(p, 0);

                if (k == 0)
                {
                    p = GetSetPixels.setR(p, 0);
                    p = GetSetPixels.setB(p, 0);
                    p = GetSetPixels.setG(p, 0);                	
                }
                else
                {
                	//cool 1
//	                p = GetSetPixels.setR(p, (k) % 255);
//	                p = GetSetPixels.setB(p, (k - 100) % 255);
//	                p = GetSetPixels.setG(p, (k - 200) % 255);         
                	
                	//cool2
//	                p = GetSetPixels.setB(p, (k) % 255);
//	                p = GetSetPixels.setR(p, (k - 127) % 255);
//	                p = GetSetPixels.setG(p, (k - 255) % 255);  
	                
	                //KEVINNNNNNN
	                p = GetSetPixels.setG(p, (k%255%50*50) + 100);
	                p = GetSetPixels.setB(p, (k%255%50*50) + 150);
	                p = GetSetPixels.setR(p, (k%255%50*50) + 000);
	                
                	//blue
//	                p = GetSetPixels.setG(p, (k) % 255);
//	                p = GetSetPixels.setB(p, 255);
//	                p = GetSetPixels.setR(p, (k - 255) % 255); 
                }
                GetSetPixels.setARGB(image, counterW, counterH, p);
                counterW++;
            }
            counterH++;
            counterW = 0;
            if (counterH %10 == 0)
            		System.out.println(counterH + "/" + height);
        }
        GetSetPixels.writeImg(image, "D:/(programmingProjects)/Java_workplace_highschool/Mandlebrot_1.0/images/mandlebrotset_v1.2.png");
    }
    
    public static int Calculate(Complex z, int loopFor){
        Complex tmp = new Complex();
        for(int i = 1; i <= loopFor; i++){
            tmp = Complex.add(Complex.multiply(tmp,tmp), z);
            if (tmp.dist() >= 2.0)
                return i;
        }
        return 0;
    }
    
}
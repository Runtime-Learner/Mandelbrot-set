package mandlebrot_v1;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MandelbrotSet_v1 {
    
        static double centerA = 0; //focus for A axis
        static double centerB = 0; //      for B axis
        static double radius  = 2;
        
    public static void main(String[] args)throws IOException{
        int width =  1500;
        int height = width;
        int countWidth =  0;
        int countHeight = 0;
        
        int a = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        
        int p = 0;
        
        BufferedImage coutImg = new BufferedImage((int)width+1, (int)height+1, BufferedImage.TYPE_INT_BGR);
        File f = null;

        for (double h = centerB + radius; h >= centerB - radius; h -= (2*radius)/height) {
            for (double w = centerA - radius; w <= centerA + radius; w += (2*radius)/width) {
                int k = MandelbrotSet_v1.Calculate(new Complex(w, h), (255*3));
                if (k == 0) {
                    a = 255;
                    r = 0;
                    g = 0;
                    b = 0;
                }
                else {
                    a = 255;
                    r = (k+200)%255;
                    g = (k+100)%255;
                    b = k%255;
                }

                p = (a<<24) | (r<<16) | (g<<8) | b;
                coutImg.setRGB(countWidth, countHeight, p);
                countWidth++;
            }
            countWidth = 0;
            countHeight++;
        }
        try{
            f = new File("D:/(programmingProjects)/Java_workplace_highschool/Mandlebrot_1.0/images/mandlebrotset_v1.1.png");
            ImageIO.write(coutImg, "png", f);
        }catch (IOException e){
            System.out.println("Error: " + e);
        }
        System.out.println("Done");
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
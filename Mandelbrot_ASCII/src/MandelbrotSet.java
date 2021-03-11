public class MandelbrotSet {
	
	static double focusA = 0; //focus for A axis
	static double focusB = 0; //for B axis
	static double radius = 2;
	
    public static void main(String[] args) {
    	char[] shades = {' ', '@', 'B', '%', '8', '&', 'W', 'M', '#', '*', 'o', 'a', 'h', 'k', 'b', 'd', 'p', 'q', 'w', 'm', 'Z', 'O', '0', 'Q', 'L', 'C', 'J', 'U', 'Y', 'X', 'z', 'c', 'v', 'u', 'n', 'x', 'r', 'j', 'f', 't', '/', '\\', 
    			'|', '(', ')', '1', '{', '}', '[', ']', '?', '-', '+', '~', '<', '>', 'i', '!', 'l', 'I', ';', ':', ',', '"', '^', '`', '\'', '.' };
        double width = 100;
        double height = 60;
        
        for (double h = focusB + radius; h >= focusB - radius; h -= (2*radius)/height) {
            for (double w = focusA - radius; w <= focusA + radius; w += (2*radius)/width) {
                int k = MandelbrotSet.Calculate(new Complex(w, h), shades.length-1);
                System.out.print(shades[k]);
            }
            System.out.println("");
        }
    }
    
    public static int Calculate(Complex z, int loopFor){
        Complex tmp = new Complex();
        for(int i = 1; i <= loopFor; i++){ //or we could just have initialized as 1 in the first place, but shut up
            tmp = Complex.add(Complex.multiply(tmp,tmp), z);
            if (tmp.dist() >= 2.0)
                return i;
        }
        return 0;
    }
}

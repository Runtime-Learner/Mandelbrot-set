package mandelbrot_v2;
public class Complex {
    private double a;
    private double b;
    
    public Complex(){
        a = 0;
        b = 0;
    }

    public Complex(double a, double b){
        this.a = a;
        this.b = b;
    }
    
    public Complex(Complex z) {
		this.a = z.a;
		this.b = z.b;
	}

	public static Complex multiply(Complex num1, Complex num2){
        return new Complex(num1.a * num2.a - num1.b * num2.b, num1.a*num2.b + num1.b*num2.a);
    }
    
    public static Complex add(Complex num1, Complex num2){
        return new Complex(num1.a + num2.a, num1.b+num2.b);
    }
    
    public Complex subtract(Complex num1, Complex num2){
        return new Complex(num1.a - num2.a, num1.b-num2.b);
    }
    
    public double getA()
    {
    	return a;
    }
    
    public double getB()
    {
    	return b;
    }
    
    public double dist(){
        return Math.sqrt(a*a+b*b);
    }
    
    public String toString(){
        return a + " + " + b+"i";
    }
}
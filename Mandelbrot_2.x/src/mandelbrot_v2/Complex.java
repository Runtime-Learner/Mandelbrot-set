package mandelbrot_v2;

public class Complex {
    private double a;
    private double b;
    
	public static void main(String[] args) 
	{
		Complex a = new Complex(2, 1);
		Complex b = new Complex(1, 2);
		Complex c = new Complex(4, 4);
		
		a.multiply(a);
		//a = Complex.multiply(a, a);
		System.out.println(a);
	}
    public Complex(){
        a = 0;
        b = 0;
    }

    public Complex(double a, double b){
        this.a = a;
        this.b = b;
    }
    
    public Complex(Complex tmp) {
    	a = tmp.a;
    	b = tmp.b;
	}

    public void setTo(Complex tmp){
    	a = tmp.a;
    	b = tmp.b;
    }
	
    public void setTo(double a, double b){
        this.a = a;
        this.b = b;
    }
	public void multiply(Complex num){
		double tmpA = this.a;
        this.a = this.a * num.a - this.b * num.b;
        this.b = tmpA * num.b + this.b * num.a;
    } 
    
	public static Complex multiply(Complex num1, Complex num2){
        return new Complex(num1.a * num2.a - num1.b * num2.b, num1.a*num2.b + num1.b*num2.a);
    }
	
    public void add(Complex num){
        a += num.a;
        b += num.b;
    }
    
    public void setToAdd(Complex num1, Complex num2){
    	a = num1.a + num2.a;
    	b = num1.b + num2.b;
    }
    
    public static Complex add(Complex num1, Complex num2){
        return new Complex(num1.a + num2.a, num1.b + num2.b);
    }
    
    public void subtract(Complex num){
        a -= num.a;
        b -= num.b;
    }
    
    public double dist(){
        return Math.sqrt(a*a+b*b);
    }
    
    public String toString(){
        return a + " + " + b+"i";
    }

	public double getA() {
		return a;
	}
	
	public double getB() {
		return b;
	}
}
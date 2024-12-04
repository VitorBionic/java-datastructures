package recursion.examples.power;

public class Program {
	public static void main(String[] args) {
		System.out.println(pow(2, 9));
	}
	
	public static double pow(double n, int p) {
		if (p == 0)
			return 1;
		if (p % 2 != 0) {
			double x = pow(n, (p - 1) / 2);
			return n * x * x;
		}
		else {
			double x = pow(n, p / 2);
			return x * x;
		}
	}
}

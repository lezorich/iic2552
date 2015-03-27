package problem2;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			double nCows = s.nextDouble();
			double nCars = s.nextDouble();
			double nShow = s.nextDouble();
			problem2(nCows, nCars, nShow);
		}
		s.close();
	}
	
	static void problem2(double nCows, double nCars, double nShow) {
		double total = nCows + nCars;
		double p = (nCows/total) * (nCars/(total - nShow - 1))
				+ (nCars/total) * ((nCars - 1)/(total - nShow - 1));
		DecimalFormat df = new DecimalFormat("0.00000");
		System.out.println(df.format(p));
	}
}

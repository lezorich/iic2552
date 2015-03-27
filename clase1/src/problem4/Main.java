package problem4;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			int k = s.nextInt();
			problem4(k);
		}
		s.close();
	}
	
	static void problem4(int k) {
		for (int d = 1; d < 12; d ++) {
			for (int n = 0; n <= d; n ++) {
				if (gcd(n, d) == 1) {
					System.out.print("" + n + "/" + d + ", ");
				}
			}
		}
		System.out.println("list");
	}

	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

}

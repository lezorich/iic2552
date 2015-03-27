package problem1;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = problem1(4);
		System.out.println(a);
	}
	
	public static int problem1(int n) {
		if (n == 1) {
			return 1;
		}
		
		if (n == 2) {
			return 2;
		}
		
		
		return problem1(n - 1)*2 + 1;
	}

}

import java.io.File;
import java.util.Scanner;
import java.util.Stack;


public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			int N = s.nextInt();
			int x = s.nextInt();
			String f = s.nextLine().trim();
			problem3(N, x, f);
		}
		/*int x = 1;
		int N = 10;
		int a = rpn("x N %", N, x);
		System.out.println("rpn: " + a + " - " + x % N);*/
	}
	
	static void problem5(int N) {
		int x = N;
		int L = 0;
		while (x > 0) {
			int y = (int) Math.ceil(x / 2.0);
			x = x - y;
			L ++;
		}
		System.out.println(L);
	}
	
	static void problem3(int N, int x, String f) {
		int k = 10;
		for (int i = 0; i < k; i++) {
			System.out.println(rpn(f, N, x)+ ", ");
		}
	}
	
	static int rpn(String s, int N, int x) {
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int i = 0; i < s.length(); i ++) {
			if (s.charAt(i) == ' ') {
				continue;
			}
			
			if (s.charAt(i) > '0' && s.charAt(i) < '9') {
				stack.push(s.charAt(i) - '0');
			} else if(s.charAt(i) == 'x') {
				stack.push(x);
			} else if (s.charAt(i) == 'N') {
				stack.push(N);
			} else {
				int op2 = stack.pop();
				int op1 = stack.pop();
				
				if (s.charAt(i) == '*') {
					stack.push(op1 * op2);
				} else if (s.charAt(i) == '+') {
					stack.push(op1 + op2);
				} else {
					stack.push(op1 % op2);
				}
			}
		}
		
		return stack.pop();
	}
}

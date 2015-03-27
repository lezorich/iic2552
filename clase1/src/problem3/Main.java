package problem3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			int N = s.nextInt();
			int x = s.nextInt();
			String f = s.nextLine().trim();
			String[] fArr = f.split(" ");
			
			if (N == 0) break;
			
			problem3_2(N, x, fArr);
		}
		s.close();
	}
	
	static void problem3(long N, long x, String[] f) {
		Map<Long, Long> cache = new HashMap<Long, Long>();
		Map<Long, Long> stepsMap = new HashMap<Long, Long>();
		
		long steps = 0;
		stepsMap.put(x, steps++);
		long fn = rpn(f, N, x, cache);
		stepsMap.put(fn, steps++);
		while(!cache.containsKey(fn)) {
			stepsMap.put(fn, steps++);
			fn = rpn(f, N, fn, cache);
		}
		System.out.println(steps - stepsMap.get(fn));
	}
	
	static void problem3_2(long N, long x, String[] f) {
		Map<Long, Long> cache = new HashMap<Long, Long>();
		
		long tortoise = rpn(f, N, x, cache);
		long hare = rpn(f, N, rpn(f, N, x, cache), cache);
		while (tortoise != hare) {
			tortoise = rpn(f, N, tortoise, cache);
			hare = rpn(f, N, rpn(f, N, hare, cache), cache);
		}
		long mu = 0;
		tortoise = x;
		while (tortoise != hare) {
			tortoise = rpn(f, N, tortoise, cache);
			hare = rpn(f, N, hare, cache);
			mu++;
		}
		long lam = 1;
		hare = rpn(f, N, tortoise, cache);
		while (tortoise != hare) {
			hare = rpn(f, N, hare, cache);
			lam++;
		}
		
		System.out.println(lam);
	}
	
	static long rpn(String[] sArr, long N, long x, Map<Long, Long> cache) {
		Stack<Long> stack = new Stack<Long>();
		
		if (cache.containsKey(x)) {
			return cache.get(x);
		}
		
		for (int i = 0; i < sArr.length; i ++) {
			if (isNumeric(sArr[i])) {
				stack.push(Long.parseLong(sArr[i]));
			} else if (sArr[i].equals("x")) {
				stack.push(x);
			} else if (sArr[i].equals("N")) {
				stack.push(N);
			} else {
				long op2 = stack.pop();
				long op1 = stack.pop();
				
				if (sArr[i].equals("*")) {
					stack.push((op1 * op2) % N);
				} else if (sArr[i].equals("+")) {
					stack.push((op1 + op2) % N);
				} else {
					stack.push(op1 % op2);
				}
			}
		}
		
		long res = stack.pop();
		
		cache.put(x, res);
		
		return res;
	}
	
	static boolean isNumeric(String str)
	{
	  return str.matches("\\d+");
	}
}

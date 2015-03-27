package p12877;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	static int solutions;
	static int longest;
	static Map<Character, Integer> map;
	static String[] words;
	static String lastWord;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while (s.hasNext()) {
			int n = s.nextInt();
			String[] words = new String[n];
			for (int i = 0; i < n; i ++) {
				words[i] = s.next();
			}
			int sol = solveFor(words);
			System.out.println(sol);
		}
		/*long a = System.currentTimeMillis();
		int p = combString("0123456789");
		long b = System.currentTimeMillis();
		System.out.println(p);
		System.out.println((b - a)/1000.0);*/
	}
	
	public static int solveFor(String[] words) {
		solutions = 0;
		map = new HashMap<>();
		Main.words = words;
		Set<Integer> zeroPositions = new HashSet<>();
		longest = 0;
		int pos = 0;
		for (String s : words) {
			for (int i = 0; i < s.length(); i ++) {
				if (!map.containsKey(s.charAt(i))) {
					map.put(s.charAt(i), pos++);
				}
			}
			if (s.length() > longest) {
				longest = s.length();
			}
		}
		
		for (String s : words) {
			zeroPositions.add(map.get(s.charAt(0)));
		}
		
		lastWord = words[words.length - 1];
		combString("0123456789", map.size(), zeroPositions);
		//solvePermute("", "0123456789", map.size(), zeroPositions);
		//Set<String> permutations = permute("0123456789", map.size(), zeroPositions);
		/*boolean isSol;
		for (String perm : permutations) {
			int carry = 0;
			int sum;
			isSol = true;
			for (int i = 0; i < longest && isSol; i ++) {
				sum = 0;
				for (int j = 0; j < words.length - 1; j ++) {
					int index = words[j].length() - i - 1;
					if (index >= 0) {
						sum += (perm.charAt(map.get((words[j].charAt(index)))) - '0');
					}
				}
				sum += carry;
				carry = sum / 10;
				sum = sum % 10;
				char c = lastWord.charAt(lastWord.length() - i - 1);
				int digit = perm.charAt(map.get(c)) - '0';
				if (digit != sum) {
					isSol = false;
				}
			}
			
			if (isSol && carry == 0) {
				solutions ++;
			}
		}*/
		
		return solutions / factorial(10 - map.size());
	}
	
	public static int combString(String s, int length, Set<Integer> zeroPositions) {
	    // Print initial string, as only the alterations will be printed later
		int per = 0;
	    //System.out.println(s);   
	    char[] a = s.toCharArray();
	    int n = a.length;
	    int[] p = new int[n];  // Weight index control array initially all zeros. Of course, same size of the char array.
	    int i = 1; //Upper bound index. i.e: if string is "abc" then index i could be at "c"
	    while (i < n) {
	        if (p[i] < i) { //if the weight index is bigger or the same it means that we have already switched between these i,j (one iteration before).
	            int j = ((i % 2) == 0) ? 0 : p[i];//Lower bound index. i.e: if string is "abc" then j index will always be 0.
	            swap(a, i, j);
	            // Print current
	            //System.out.println(join(a));
	            boolean ok = true;
	    		for (int k = 0; k < a.length && ok; k ++) {
	    			if (a[k] == '0' && zeroPositions.contains(k)) {
	    				ok = false;
	    			}
	    		}
	    		if (ok) {
		            //String perm = join(a, 0, length);
	    			solveForPerm(a, zeroPositions);
		    	}
	            per ++;
	            p[i]++; //Adding 1 to the specific weight that relates to the char array.
	            i = 1; //if i was 2 (for example), after the swap we now need to swap for i=1
	        }
	        else { 
	            p[i] = 0;//Weight index will be zero because one iteration before, it was 1 (for example) to indicate that char array a[i] swapped.
	            i++;//i index will have the option to go forward in the char array for "longer swaps"
	        }
	    }
	    return per;
	}
	
	private static String join(char[] a, int offset, int length) {
	    StringBuilder builder = new StringBuilder();
	    //builder.append(a);
	    builder.append(a, offset, length);
	    return builder.toString();
	}

	private static void swap(char[] a, int i, int j) {
	    char temp = a[i];
	    a[i] = a[j];
	    a[j] = temp;
	}
	
	private static int factorial(int n) {
		int p = 1;
		while (n > 1) {
			p *= n;
			n --;
		}
		return p;
	}
	
	public static Set<String> permute(String s, int length, Set<Integer> zeroPositions) {
		Set<String> set = new HashSet<>();
		permute("", s, length, set, zeroPositions);
		return set;
	}
	
	public static void permute(String s1, String s2, int length, Set<String> permutations,
			Set<Integer> zeroPositions) {
		for (int i = 0; i < s1.length(); i ++) {
			if (s1.charAt(i) == '0' && zeroPositions.contains(i)) {
				return;
			}
		}
		if (s1.length() == length) {
			permutations.add(s1);
		} else {
			for (int i = 0; i < s2.length(); i ++) {
				permute(s1 + s2.charAt(i), s2.substring(0, i) + s2.substring(i + 1), length,
						permutations, zeroPositions);
			}
		}
	}
	
	public static void solveForPerm(String s, Set<Integer> zeroPositions) {
		int carry = 0;
		int sum;
		boolean isSol = true;
		for (int i = 0; i < longest && isSol; i ++) {
			sum = 0;
			for (int j = 0; j < words.length - 1; j ++) {
				int index = words[j].length() - i - 1;
				if (index >= 0) {
					sum += (s.charAt(map.get((words[j].charAt(index)))) - '0');
				}
			}
			sum += carry;
			carry = sum / 10;
			sum = sum % 10;
			char c = lastWord.charAt(lastWord.length() - i - 1);
			int digit = s.charAt(map.get(c)) - '0';
			if (digit != sum) {
				isSol = false;
				return;
			}
		}
		
		if (isSol && carry == 0) {
			solutions ++;
		}
	}
	
	public static void solveForPerm(char[] a, Set<Integer> zeroPositions) {
		int carry = 0;
		int sum;
		boolean isSol = true;
		for (int i = 0; i < longest && isSol; i ++) {
			sum = 0;
			for (int j = 0; j < words.length - 1; j ++) {
				int index = words[j].length() - i - 1;
				if (index >= 0) {
					sum += (a[map.get((words[j].charAt(index)))] - '0');
				}
			}
			sum += carry;
			carry = sum / 10;
			sum = sum % 10;
			char c = lastWord.charAt(lastWord.length() - i - 1);
			int digit = a[map.get(c)] - '0';
			if (digit != sum) {
				isSol = false;
				return;
			}
		}
		
		if (isSol && carry == 0) {
			solutions ++;
		}
	}
	
	public static void solvePermute(String s1, String s2, int length, Set<Integer> zeroPositions) {
		for (int i = 0; i < s1.length(); i ++) {
			if (s1.charAt(i) == '0' && zeroPositions.contains(i)) {
				return;
			}
		}
		if (s1.length() == length) {
			int carry = 0;
			int sum;
			boolean isSol = true;
			for (int i = 0; i < longest && isSol; i ++) {
				sum = 0;
				for (int j = 0; j < words.length - 1; j ++) {
					int index = words[j].length() - i - 1;
					if (index >= 0) {
						sum += (s1.charAt(map.get((words[j].charAt(index)))) - '0');
					}
				}
				sum += carry;
				carry = sum / 10;
				sum = sum % 10;
				char c = lastWord.charAt(lastWord.length() - i - 1);
				int digit = s1.charAt(map.get(c)) - '0';
				if (digit != sum) {
					isSol = false;
				}
			}
			
			if (isSol && carry == 0) {
				solutions ++;
			}
		} else {
			for (int i = 0; i < s2.length(); i ++) {
				solvePermute(s1 + s2.charAt(i), s2.substring(0, i) + s2.substring(i + 1), length, 
						zeroPositions);
			}
		}
	}
		
	public static <T >void printSet(Set<T> set) {
		Iterator<T> it = set.iterator();
		for (int i = 0; i < 20; i ++) {
			System.out.println(it.next());
		}
		/*for (T t : set) {
			System.out.println(t);
		}*/
	}
}

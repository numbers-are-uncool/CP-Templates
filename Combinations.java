import java.util.*;
import java.io.*;

public class Combination_Practice {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int ctr = 0;
	public static void main(String[] args) throws IOException {
		int a[] = { 52, 56, 32, 67 };
		List<Integer> ans = new ArrayList<Integer>();
		combine(a, 0, ans);
		System.out.println(ctr);
	}

	static void combine(int s[], int c, List<Integer> l) {
		if (c == s.length) {
			ctr++;
			System.out.println(l);
			return;
		}
		combine(s, c + 1, l);
		l.add(s[c]);
		combine(s, c + 1, l);
		l.remove(l.size() - 1);
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong() throws IOException {
		return Long.parseLong(next());
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}

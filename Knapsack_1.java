import java.util.*;
import java.io.*;
public class Knapsack_1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		int n = readInt(), w = readInt();
		Item a[] = new Item[n+1];
		for(int i=1;i<=n;i++) {
			a[i] = new Item(readInt(), readInt());
		}
		long ans[] = new long[w+1];
		for(int i=1;i<=n;i++) {
			for(int j=w;j>=a[i].weight;j--) {
				ans[j] = Math.max(ans[j], ans[j - a[i].weight] + a[i].value);
			}
			//System.out.println(Arrays.toString(ans));
		}
		System.out.println(ans[w]);
		System.out.println(Arrays.toString(ans));
	}
	private static class Item implements Comparable<Item> {
		int weight;
		long value;
		public Item (int w, long v) {
			weight = w;
			value = v;
		}
		@Override
		public int compareTo(Item o) {
			if(value > o.value)
				return 1;
			else if(value < o.value)
				return -1;
			else
				return 0;
		}
		
	}
	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) 
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}
	
	static long readLong() throws IOException {
		return Long.parseLong(next());
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

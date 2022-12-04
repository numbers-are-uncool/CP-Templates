import java.io.*;
import java.util.*;
public class Segment_Tree_Test {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int a[];
	static node seg[];
	public static void main(String[] args) throws IOException {
		int n = readInt(), m = readInt();
		a = new int[n+1];
		seg = new node[2*n];
		for(int i=1;i<=n;i++) {
			a[i] = readInt();
		}
		build(1, n, 1);
		for(int q=0;q<m;q++) {
			char c = next().charAt(0);
			if(c == 'C') {
				int x = readInt(), v = readInt();
				update(x, v, 1);
			}
			else if(c == 'M') {
				int l = readInt(), r = readInt();
				System.out.println(minQuery(l, r, 1));
			}
			else if(c == 'G') {
				int l = readInt(), r = readInt();
				System.out.println(gcdQuery(l, r, 1));
			}
			else if(c == 'Q') {
				int l = readInt(), r = readInt(), min = minQuery(l, r, 1), gcd = gcdQuery(l, r, 1);
				if(min == gcd) {
					System.out.println(freqQuery(l, r, 1));
				}
				else {
					System.out.println(0);
				}
			}
		}
	}
	static int freqQuery(int l, int r, int i) {
		if(seg[i].left == l && seg[i].right == r) {
			return seg[i].freq;
		}
		int m = (seg[i].left+seg[i].right)/2;
		if(r <= m) {
			return freqQuery(l, r, 2*i);
		}
		else if(l > m) {
			return freqQuery(l, r, 2*i+1);
		}
		else {
			if(seg[2*i].min < seg[2*i+1].min) {
				return freqQuery(l, m, 2*i);
			}
			else if(seg[2*i].min > seg[2*i+1].min) {
				return freqQuery(m+1, r, 2*i+1);
			}
			else {
				return freqQuery(l, m, 2*i) + freqQuery(m+1, r, 2*i+1);
			}
		}
	}
	static int gcdQuery(int l, int r, int i) {
		if(seg[i].left == l && seg[i].right == r) {
			return seg[i].gcd;
		}
		int m = (seg[i].left+seg[i].right)/2;
		if(r <= m) {
			return gcdQuery(l, r, 2*i);
		}
		else if(l > m) {
			return gcdQuery(l, r, 2*i+1);
		}
		else {
			return gcf(gcdQuery(l, m, 2*i), gcdQuery(m+1, r, 2*i+1));
		}
	}
	static int minQuery(int l, int r, int i) {
		if(seg[i].left == l && seg[i].right == r) {
			return seg[i].min;
		}
		int m = (seg[i].left+seg[i].right)/2;
		if(r <= m) {
			return minQuery(l, r, 2*i);
		}
		else if(l > m) {
			return minQuery(l, r, 2*i+1);
		}
		else {
			return Math.min(minQuery(l, m, 2*i), minQuery(m+1, r, 2*i+1));
		}
	}
	static void update(int pos, int v, int i) {
		if(seg[i].left == seg[i].right) {
			seg[i].min = v;
			seg[i].gcd = v;
			seg[i].freq = 1;
			return;
		}
		int m = (seg[i].left+seg[i].right)/2;
		if(pos <= m) {
			update(pos, v, 2*i);
		}
		else {
			update(pos, v, 2*i+1);
		}
		seg[i].gcd = gcf(seg[2*i].gcd, seg[2*i+1].gcd);
		if(seg[2*i].min < seg[2*i+1].min) {
			seg[i].min = seg[2*i].min;
			seg[i].freq = seg[2*i].freq;
		}
		else if(seg[2*i].min > seg[2*i+1].min) {
			seg[i].min = seg[2*i+1].min;
			seg[i].freq = seg[2*i+1].freq;
		}
		else {
			seg[i].min = seg[2*i].min;
			seg[i].freq = seg[2*i].freq + seg[2*i+1].freq;
		}
	}
	static void build(int l, int r, int i) {
		seg[i] = new node(l, r, 0, 0, 0);
		if(l == r) {
			seg[i].min = a[l];
			seg[i].gcd = a[l];
			seg[i].freq = 1;
			return;
		}
		int m = (l+r)/2;
		build(l, m, 2*i);
		build(m+1, r, 2*i+1);
		seg[i].gcd = gcf(seg[2*i].gcd, seg[2*i+1].gcd);
		if(seg[2*i].min < seg[2*i+1].min) {
			seg[i].min = seg[2*i].min;
			seg[i].freq = seg[2*i].freq;
		}
		else if(seg[2*i].min > seg[2*i+1].min) {
			seg[i].min = seg[2*i+1].min;
			seg[i].freq = seg[2*i+1].freq;
		}
		else {
			seg[i].min = seg[2*i].min;
			seg[i].freq = seg[2*i].freq + seg[2*i+1].freq;
		}
	}
	static class node {
		int left, right, min, gcd, freq;
		public node(int l, int r, int m, int g, int f) {
			left = l;
			right = r;
			min = m;
			gcd = g;
			freq = f;
		}
	}
	static int gcf(int a, int b) {
		int r = 0;
		while (true) {
			r = a % b;
			a = b;
			b = r;
			if (r == 0)
				break;
		}
		return a;
	}
	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) 
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}
	
	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}
}

import java.util.*;
import java.io.*;

public class Shop_and_Ship {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int n = readInt(), t = readInt();
		long dis[] = new long[n + 1];
		ArrayList<edge> adj[] = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<edge>();
			dis[i] = Long.MAX_VALUE / 2;
		}
		for (int i = 0; i < t; i++) {
			int x = readInt(), y = readInt(), z = readInt();
			adj[x].add(new edge(y, z));
			adj[y].add(new edge(x, z));
		}
		int k = readInt(), z[] = new int[k], p[] = new int[k];
		for (int i = 0; i < k; i++) {
			z[i] = readInt();
			p[i] = readInt();
		}
		int d = readInt();
		PriorityQueue<edge> pq = new PriorityQueue<edge>();
		pq.add(new edge(d, 0));
		dis[d] = 0;
		while (!pq.isEmpty()) {
			edge e = pq.poll();
			int cur = e.v;
			for (edge i : adj[cur]) {
				if (dis[i.v] > e.w + i.w) {
					dis[i.v] = e.w + i.w;
					pq.add(new edge(i.v, dis[i.v]));
				}
			}
		}
		long ans = Long.MAX_VALUE / 2;
		for (int i = 0; i < k; i++) {
			ans = Math.min(ans, p[i] + dis[z[i]]);
		}
		System.out.println(ans);
	}

	static class edge implements Comparable<edge> {
		int v;
		long w;

		edge(int v0, long w0) {
			v = v0;
			w = w0;
		}

		public int compareTo(edge o) {
			return Long.compare(w, o.w);
		}
	}
	final private static int BUFFER_SIZE = 1 << 16;
	private static DataInputStream din = new DataInputStream(System.in);
	private static byte[] buffer = new byte[BUFFER_SIZE];
	private static int bufferPointer = 0, bytesRead = 0;
	static PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
 
	public static String readLine() throws IOException {
		byte[] buf = new byte[64]; // line length
		int cnt = 0, c;
		while ((c = Read()) != -1) {
			if (c == '\n')
				break;
			buf[cnt++] = (byte) c;
		}
		return new String(buf, 0, cnt);
	}
 
	public static String read() throws IOException {
		byte[] ret = new byte[1024];
		int idx = 0;
		byte c = Read();
		while (c <= ' ') {
			c = Read();
		}
		do {
			ret[idx++] = c;
			c = Read();
		} while (c != -1 && c != ' ' && c != '\n' && c != '\r');
		return new String(ret, 0, idx);
	}
 
	public static int readInt() throws IOException {
		int ret = 0;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');
 
		if (neg)
			return -ret;
		return ret;
	}
 
	public static long readLong() throws IOException {
		long ret = 0;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');
		if (neg)
			return -ret;
		return ret;
	}
 
	public static double readDouble() throws IOException {
		double ret = 0, div = 1;
		byte c = Read();
		while (c <= ' ')
			c = Read();
		boolean neg = (c == '-');
		if (neg)
			c = Read();
 
		do {
			ret = ret * 10 + c - '0';
		} while ((c = Read()) >= '0' && c <= '9');
 
		if (c == '.') {
			while ((c = Read()) >= '0' && c <= '9') {
				ret += (c - '0') / (div *= 10);
			}
		}
 
		if (neg)
			return -ret;
		return ret;
	}
 
	private static void fillBuffer() throws IOException {
		bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
		if (bytesRead == -1)
			buffer[0] = -1;
	}
 
	private static byte Read() throws IOException {
		if (bufferPointer == bytesRead)
			fillBuffer();
		return buffer[bufferPointer++];
	}
 
	public void close() throws IOException {
		if (din == null)
			return;
		din.close();
	}
 
	static void print(Object o) {
		pr.print(o);
	}
 
	static void println(Object o) {
		pr.println(o);
	}
 
	static void flush() {
		pr.flush();
	}
 
	static void println() {
		pr.println();
	}
 
	static void exit() throws IOException {
		din.close();
		pr.close();
		System.exit(0);
	}
}
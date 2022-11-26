import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class LCA {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<edge> adj[];
	static int h[], n, depth[], sparse[][], log;
	public static void main(String[] args) throws IOException {
		n = readInt();
		log = (int) Math.ceil(Math.log(n) / Math.log(2));
		adj = new ArrayList[n+1];
		h = new int[n+1];
		depth = new int[n+1];
		sparse = new int[n+1][log];
		for(int i=1;i<=n;i++) {
			adj[i] = new ArrayList<edge>();
		}
		for(int i=1;i<n;i++) {
			int a = readInt(), b = readInt(), c = readInt();
			adj[a].add(new edge(b, c));
			adj[b].add(new edge(a, c));
		}
		bfs();
		for(int j=1;j<log;j++) {
			for(int i=1;i<=n;i++) {
				sparse[i][j] = sparse[sparse[i][j-1]][j-1];
			}
		}
	}
	static class edge {
		int node, len;
		public edge(int v, int l) {
			node = v;
			len = l;
		}
	}
	static void bfs() {
		boolean vis[] = new boolean[n+1];
		Queue<Integer> q = new LinkedList<Integer>();
		vis[1] = true;
		q.add(1);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(edge i : adj[cur]) {
				if(!vis[i.node]) {
					vis[i.node] = true;
					h[i.node] = h[cur] + i.len;
					depth[i.node] = depth[cur] + 1;
					sparse[i.node][0] = cur;
					q.add(i.node);
				}
			}
		}
	}
	static int lca(int v, int u) {
		if(depth[u] < depth[v]) {
			int e = u;
			u = v;
			v = e;
		}
		int jump = depth[u] - depth[v];
		for(int j=log-1;j>=0;j--) {
			if((1<<j) <= jump) {
				u = sparse[u][j];
				jump -= (1<<j);
			}
		}
		for(int j=log-1;j>=0;j--) {
			if(sparse[u][j] != sparse[v][j]) {
				u = sparse[u][j];
				v = sparse[v][j];
			}
		}
		if(u == v)
			return u;
		return sparse[u][0];
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

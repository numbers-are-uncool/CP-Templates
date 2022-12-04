import java.util.ArrayList;
import java.util.List;

public class Combination {
	static int ctr = 0;
	public static void main(String[] args) {
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
}

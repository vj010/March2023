package EqualDiverseTeams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class EqualDiverseTeams {
    // For fast input output
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            try {
                String currentDir = System.getProperty("user.dir");
                File file = new File(currentDir + "/EqualDiverseTeams/input.txt");
                br = new BufferedReader(
                        new FileReader(file));
                PrintStream out = new PrintStream(
                        new FileOutputStream(currentDir + "/EqualDiverseTeams/output.txt"));
                System.setOut(out);
            } catch (Exception e) {
                br = new BufferedReader(new InputStreamReader(System.in));
            }
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    // end of fast i/o code
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int t = reader.nextInt();
        while (t > 0) {
            t--;
            int n = reader.nextInt();
            int k = reader.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int a = reader.nextInt();
                list.add(a);
            }
            solve(list, k);
        }

    }

    public static void solve(List<Integer> list, int k) {
        int unique = 0;
        int nonUnique = 0;
        Map<Integer, Integer> m = new HashMap<>();
        for (Integer a : list) {
            if (m.containsKey(a)) {
                m.put(a, m.get(a) + 1);
            } else {
                m.put(a, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : m.entrySet()) {
            if (entry.getValue() > 1) {
                nonUnique++;
            } else {
                unique++;
            }
        }

        if (2 * k == m.size()) {
            System.out.println("YES");
            return;
        }

        if (2 * k < m.size()) {
            System.out.println("NO");
            return;
        }

        if ((2 * k - m.size()) <= nonUnique) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }
}

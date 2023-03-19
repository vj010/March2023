import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class LargestNumber {
    // For fast input output
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            try {
                String currentDir = System.getProperty("user.dir");
                File file = new File(currentDir + "/LargestNumber/input.txt");
                br = new BufferedReader(
                        new FileReader(file));
                PrintStream out = new PrintStream(
                        new FileOutputStream(currentDir + "/LargestNumber/output.txt"));
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

    static class Pair<T1 extends Integer, T2 extends Integer> {
        T1 first;
        T2 second;

        Pair(T1 val1, T2 val2) {
            this.first = val1;
            this.second = val2;
        }
    }

    // end of fast i/o code
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        String num = reader.nextLine();
        int k = reader.nextInt();

        System.out.println(largestNumberString(num, k));
        ;

    }

    private static String largestNumberString(String numberString, int k) {
        List<Integer> digitList = Arrays.asList(numberString.split("")).stream().map((num) -> Integer.parseInt(num))
                .collect(Collectors.toList());
        List<Pair<Integer, Integer>> digitWithIndex = new ArrayList<>();
        for (int i = 0; i < digitList.size(); i++) {
            digitWithIndex.add(new Pair<Integer, Integer>(digitList.get(i), i));
        }

        Collections.sort(digitWithIndex, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                if (p1.first < p2.first || (p1.first == p2.first && p1.second < p2.second)) {
                    return -1;
                }
                if (p1.first == p2.first && p1.second == p2.second) {
                    return 0;
                }
                return 1;
            }
        });
        for (int i = 0; i < k; i++) {
            Pair<Integer, Integer> p = digitWithIndex.get(i);
            p.first = -1;
        }
        List<Integer> finalList = new ArrayList<>(digitWithIndex.size());

        for (int i = 0; i < digitWithIndex.size(); i++) {
            finalList.add(0);
        }

        for (int i = 0; i < digitWithIndex.size(); i++) {
            finalList.set(digitWithIndex.get(i).second, digitWithIndex.get(i).first);
        }

        StringBuilder sb = new StringBuilder();

        finalList.stream().filter((num) -> num != -1).forEach(sb::append);

        return sb.toString();
    }
}

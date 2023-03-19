import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Stack;
import java.util.StringTokenizer;

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
        Stack<Integer> stack = new Stack<>();
        int removed = 0;
        for (Character c : numberString.toCharArray()) {
            Integer digit = Character.getNumericValue(c);
            // System.out.println(digit);
            while (!stack.empty() && stack.peek() < digit && removed < k) {
                stack.pop();
                // System.out.print(" popping");
                removed++;
            }
            stack.push(digit);
        }
        while (removed < k) {
            stack.pop();
            removed++;

        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        return sb.toString();
    }
}

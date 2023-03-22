package HorseRace;

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
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class HorseRace {
    // For fast input output
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            try {
                String currentDir = System.getProperty("user.dir");
                File file = new File(currentDir + "/HorseRace/input.txt");
                br = new BufferedReader(
                        new FileReader(file));
                PrintStream out = new PrintStream(
                        new FileOutputStream(currentDir + "/HorseRace/output.txt"));
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
            int n = reader.nextInt();
            int m = reader.nextInt();
            int x = reader.nextInt();
            List<Integer> raceWinner = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int a = reader.nextInt();
                raceWinner.add(a);
            }
            System.out.println(solve(raceWinner, m, x));

            t--;
        }
    }

    private static int solve(List<Integer> raceWinner, int m, int x) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < raceWinner.size(); i++) {
            if (freqMap.containsKey(raceWinner.get(i))) {
                freqMap.put(raceWinner.get(i), freqMap.get(raceWinner.get(i)) + 1);
            } else {
                freqMap.put(raceWinner.get(i), 1);
            }
        }
        List<Integer> wins = new ArrayList<>();
        List<Integer> prefixSumOfWins = new ArrayList<>();
        prefixSumOfWins.add(0);

        for (int i = 0; i < m; i++) {
            if (freqMap.containsKey(i + 1)) {
                wins.add(freqMap.get(i + 1));
            } else {
                wins.add(0);
            }
        }

        Collections.sort(wins);

        for (int i = 0; i < wins.size(); i++) {
            prefixSumOfWins.add(prefixSumOfWins.get(i) + wins.get(i));
        }

        int maxMinVal = Math.floorDiv(raceWinner.size(), m);
        int start = 0;
        int ans = 0;
        int end = maxMinVal + 1;

        while (start < end) {
            int mid = (start + end) >> 1;
            if (isPossible(wins, prefixSumOfWins, mid, x)) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return ans;
    }

    private static boolean isPossible(List<Integer> wins, List<Integer> prefixSumOfWins, int minVal, int x) {
        int ind = getLowerBound(wins, minVal);
        if (ind < 0) {
            return true;
        }
        int requiredSteps = (ind + 1) * minVal - prefixSumOfWins.get(ind + 1);
        if (requiredSteps <= x)
            return true;
        return false;
    }

    private static int getLowerBound(List<Integer> arr, int val) {
        int start = 0;
        int end = arr.size();
        int ans = (start + end) >> 1;
        while (start < end) {
            int mid = (start + end) >> 1;
            if (arr.get(mid) <= val) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (arr.get(ans) > val)
            return -1;
        return ans;
    }
}

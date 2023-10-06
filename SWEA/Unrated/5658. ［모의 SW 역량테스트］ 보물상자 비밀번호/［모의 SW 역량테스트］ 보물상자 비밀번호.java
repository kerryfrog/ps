import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {

    public static int changeDecimal(char[] a) {
        int ans = 0;
        for (int i = a.length - 1, j = 0; i >= 0; i--, j++) {
            char now = a[i];
            int nowNum = 0;
            if ((int) now >= (int) 'A') {
                nowNum = 10 + (int) now - (int) 'A';
            } else {
                nowNum = Integer.parseInt(String.valueOf(now));
            }
            ans += Math.pow(16, j) * nowNum;

        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            TreeSet<Integer> ts = new TreeSet<>();
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int part = N / 4;
            int K = Integer.parseInt(st.nextToken());
            char[] numbers = new char[N];
            String line = br.readLine();
            for (int i = 0; i < N; i++) {
                numbers[i] = line.charAt(i);
            }

            int start = 0;
            for (int i = 0; i < part; i++, start--) { // 총 part 번 회전
                if(start < 0 ) start = N - 1;
                char[] num = new char[part];
                for (int j = 0; j < N; j++) { // 회전마다 part 개로 잘라서 숫자 추출
                    num[j % part] = numbers[ (start+j) % N ];
                    if (j % part == part-1) {
                        int now = changeDecimal(num);
                        ts.add(now);
                        num = new char[part];
                    }
                }
            }
            for (int i = 0; i < K-1; i++) {
                int now = ts.last();
                ts.remove(now);
            }
            sb.append("#").append(tc).append(" ").append(ts.last()).append("\n");
        }
        System.out.println(sb);
    }
}
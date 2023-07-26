import java.io.*;
import java.util.*;

// 1000000 * 2 ^15  => 10 ^ 6 x 10 ^3 x 2 ^5 => 10 ^9 x 32
// int 표현범위 2147483647 => 2 * 10 ^9
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M, N;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long [] numbers = new long[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            Arrays.sort(numbers);
            Long tmp = numbers[0] + numbers[1];
            numbers[0] = tmp;
            numbers[1] = tmp;
        }
        Long ans = 0L;
        for (Long a : numbers) {
            ans += a;
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }
}



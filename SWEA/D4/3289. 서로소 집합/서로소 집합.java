import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int[] set;
    static int n;

    private static void union(int a, int b) {
        // 더 작은 수를 부모로 두도록 하자.
        int pa = findParent(a);
        int pb = findParent(b);
        if (pa < pb) {
            set[pb] = pa;
        } else {
            set[pa] = pb;
        }
    }

    private static int findParent(int a) {
        if (set[a] == a) return a;
        else return set[a] = findParent(set[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            set = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                set[i] = i;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int state = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 합치기
                if (state == 0) {
                    union(a, b);
                } else if (state == 1) {// 확인
                    int pa = findParent(a);
                    int pb = findParent(b);

                    if (pa == pb) sb.append(1);
                    else sb.append(0);
                }

            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, answer = Integer.MAX_VALUE, nowDepth;
    static int[] city;

    static ArrayList<Integer>[] adj;
    static boolean isVisited[];

    //isVisitedTF :T isVistied 가 true인 애들의 연결의 유효성을 체크
    //isVisitedTF :F isVistied 가 false인 애들의 연결의 유효성을 체크
    // r개의
    private static boolean checkValidLink(int r, boolean isVisitedTF) {
        if(r==1) return true;

        int linkedCityCnt = 1;
        boolean[] cityVisited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int last = 1; last <= N; last++) {
            if (isVisited[last] == isVisitedTF) {
                queue.add(last);
                cityVisited[last] = true;
                break;
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (Integer a : adj[now]) {
                // 행렬에 포함되어 있어야 함
                if (isVisited[a] != isVisitedTF || cityVisited[a]) continue;
                queue.add(a);
                cityVisited[a] = true;
                if (++linkedCityCnt == r) {
                    return true;
                }
            }
        }
        return false;
    }


    // 조합 + union find 문제
    private static void comb(int depth, int now) {
        if (depth == nowDepth) {

            // 연결이 유효하지 않다면 끝
            if (!checkValidLink(nowDepth, true)) return;
            if (!checkValidLink(N - nowDepth, false)) return;

            // 여기에 왔으면 연결이 유효함
            int type1 = 0, type2 = 0;

            for (int i = 1; i <= N; i++) {
                if (isVisited[i]) type1 += city[i];
                else type2 += city[i];
            }

            int diff = Math.abs(type1 - type2);
            answer = Math.min(diff, answer);
            return;
        }

        for (int i = now + 1; i <= N; i++) {
            if (isVisited[i]) continue;
            isVisited[i] = true;
            comb(depth + 1, i);
            isVisited[i] = false;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        // 도시는 1부터 시작
        city = new int[N + 1];
        adj = new ArrayList[N + 1];
        isVisited = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            city[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            adj[i] = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                adj[i].add(tmp);
            }
        }


        // nC1 ~ nC절반 까지 하기
        for (int i = 1; i <= N / 2 + N % 2; i++) {
            nowDepth = i;
            comb(0, 0);
        }
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] relations;
    static boolean[] isVisited;
    static int N, M;
    static int answer = 0;

    private static boolean dfs(int now, int depth) {
        if (depth == 4) {
            answer = 1;
            return true;
        }

        for (Integer freind : relations[now]) {
            if (!isVisited[freind]) {
                isVisited[freind] = true;
                if (dfs(freind, depth + 1)) return true;
                isVisited[freind] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isVisited = new boolean[N];
        relations = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            relations[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relations[a].add(b);
            relations[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            if(!isVisited[i]) {
                isVisited[i] = true;
                dfs(i, 0);
                isVisited[i] = false;
            }
            if(answer == 1) break;
        }
        System.out.println(answer);
    }
}
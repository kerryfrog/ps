import java.io.*;
import java.util.*;

public class Main {
    static int N,L,R;
    static int [][] board ;
    static int [][] visited;
    static int [] dx = {1, 0, -1 ,0};
    static int [] dy = {0 , 1, 0, -1};
    static int sum = 0;
    static int cnt =0;

    public static void dfs(int x, int y, int now){

        if(visited[x][y]  != 0){
            return;
        }
        sum += board[x][y];
        cnt ++;
        visited[x][y] = now;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                int abs = Math.abs(board[x][y] - board[nextX][nextY]);
                if (visited[nextX][nextY] == 0 && abs >= L && abs <= R) {
                    dfs(nextX,  nextY, now);
                }
            }
        }
    }

    public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        visited = new int[N][N];
        int day =0;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true){
            int nNow = 1;
            List<Integer> avgList = new ArrayList<>();
            boolean isEnd = true;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(visited[i][j] == 0){
//                        System.out.printf("i=%d , j=%d \n", i, j);
                        dfs(i, j, nNow);
                        avgList.add(sum/cnt);
                        nNow +=1;
                        if(cnt>1){
                            isEnd = false;
                        }
                        sum = 0;
                        cnt = 0;

                    }
                }
            }
            if (isEnd)
                break;
            day++;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    board[i][j] = avgList.get(visited[i][j] - 1);
                }
            }
            visited = new int[N][N];
        }
        System.out.println(day);
    }
}
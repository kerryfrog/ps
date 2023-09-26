import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int[][] board = new int[10][10];
    static ArrayList<Point> emptyList = new ArrayList<>();

    public static boolean checkRow(int a) {
        int[] line = new int[10];
        for (int i = 1; i <= 9; i++) {
            if (board[a][i] == 0) continue;
            if (++line[board[a][i]] > 1) {
//                System.out.println("row false"  + a);
                return false;
            }
        }
        return true;
    }

    public static boolean checkCol(int a) {
        int[] line = new int[10];
        for (int i = 1; i <= 9; i++) {
            if (board[i][a] == 0) continue;
            if (++line[board[i][a]] > 1) {
//                System.out.println("col false"  + a);
                return false;
            }
        }
        return true;
    }

    public static boolean checkBox(int a, int b) {
        int x = ((a -1) / 3) * 3 +1;
        int y = ((b-1) / 3) * 3 +1;
        int[] line = new int[10];
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (board[i][j] == 0) continue;
                if (++line[board[i][j]] > 1) {
//                    System.out.println("box false " + a + " " + b + " " + x + " " + y);
                    return false;
                }

            }
        }
        return true;
    }

    public static boolean dfs(int depth) {
        if (depth == emptyList.size()) {
            return true;
        }

        Point now = emptyList.get(depth);
        for (int i = 1; i <= 9; i++) {
            board[now.x][now.y] = i;
            if(checkRow(now.x) && checkCol(now.y) && checkBox(now.x,now.y)){
//                System.out.println( "["+ now.x+" "+now.y+"] :" +i );
                if(dfs(depth + 1)) return true;
            }
            board[now.x][now.y] = 0;
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i+1][j+1] = line.charAt(j) - '0';
                if (board[i+1][j+1] == 0) {
                    emptyList.add(new Point(i+1, j+1));
                }
            }
        }
        dfs(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <10 ; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
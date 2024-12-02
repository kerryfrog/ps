import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static char[][] room;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Point start, end;

    public static class Stat {
        public int x, y;
        public int dir;
        public int cost;

        Stat(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }

        public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x =" + x + " y=" + y + " dir=" + dir + " cost=" + cost;
        }
    }

    public static ArrayList<Stat> go(int x, int y, int dir, int cost) {
        int nextX = x;
        int nextY = y;

        ArrayList<Stat> ans = new ArrayList<>();
        while (true) {
            nextX += dx[dir];
            nextY += dy[dir];

            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
                break;
            }
            if (room[nextX][nextY] == '*') {
                break;
            }
            if (room[nextX][nextY] == '!') {
                ans.add(new Stat(nextX, nextY, dir, cost + 1));
            }
            if (nextX == end.x && nextY == end.y) {
                ans.add(new Stat(100, 100, dir, cost));
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        room = new char[N][N];
        boolean isStart = false;
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                room[i][j] = line.charAt(j);
                if (room[i][j] == '#') {
                    if (!isStart) {
                        start = new Point(i, j);
                        isStart = true;
                    } else {
                        end = new Point(i, j);
                    }
                }
            }
        }
        Queue<Stat> queue = new LinkedList<>();
        queue.add(new Stat(start.x, start.y, 0, 0));
        queue.add(new Stat(start.x, start.y, 1, 0));

        int answer = -1;
        while (!queue.isEmpty()) {
            Stat now = queue.poll();
//            System.out.println(now);
            visited[now.x][now.y] = true;
            if (now.dir == 0 || now.dir == 2) {
                ArrayList<Stat> next = go(now.x, now.y, 1, now.cost);

                for (int i = 0; i <next.size(); i++) {
                    Stat tmp = next.get(i);
                    if (tmp.x == 100 && tmp.y == 100) {
                        answer = now.cost;
                        break;
                    } else if (!visited[tmp.x][tmp.y]) {
                        queue.add(tmp);
                    }
                }
                if (answer != -1)
                    break;

                next = go(now.x, now.y, 3, now.cost);
                for (int i = 0; i <next.size(); i++) {
                    Stat tmp = next.get(i);
                    if (tmp.x == 100 && tmp.y == 100) {
                        answer = now.cost;
                        break;
                    } else if (!visited[tmp.x][tmp.y]) {
                        queue.add(tmp);
                    }
                }
                if (answer != -1)
                    break;
            } else {
                ArrayList<Stat> next = go(now.x, now.y, 0, now.cost);
                for (int i = 0; i <next.size(); i++) {
                    Stat tmp = next.get(i);
                    if (tmp.x == 100 && tmp.y == 100) {
                        answer = now.cost;
                        break;
                    } else if (!visited[tmp.x][tmp.y]) {
                        queue.add(tmp);
                    }
                }
                if (answer != -1)
                    break;
                next = go(now.x, now.y, 2, now.cost);
                for (int i = 0; i <next.size(); i++) {
                    Stat tmp = next.get(i);
                    if (tmp.x == 100 && tmp.y == 100) {
                        answer = now.cost;
                        break;
                    } else if (!visited[tmp.x][tmp.y]) {
                        queue.add(tmp);
                    }
                }
                if (answer != -1)
                    break;
            }

        }
        System.out.println(answer);
    }
}
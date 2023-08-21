import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] board;

    // 각각의 블럭들을 통해서 움직일 수 있는 방향 구할 수 있는 함수
    // r의 좌표 얻음
    private static int[] getDr(char a) {
        int[] dr = new int[4];
        if (a == '|') {
            dr[0] = 1;
            dr[1] = -1;
        } else if (a == '-') {
            dr[0] = 0;
            dr[1] = 0;
        } else if (a == '+') {
            dr[0] = -1;
            dr[1] = 0;
            dr[2] = 1;
            dr[3] = 0;
        } else if (a == '1') {
            dr[0] = 1;
            dr[1] = 0;
        } else if (a == '2') {
            dr[0] = -1;
            dr[1] = 0;
        } else if (a == '3') {
            dr[0] = -1;
            dr[1] = 0;
        } else if (a == '4') {
            dr[0] = 1;
            dr[1] = 0;
        }
        return dr;

    }

    // 각각의 블럭들을 통해서 움직일 수 있는 방향 구할 수 있는 함수
    // c 의 좌표 얻음
    private static int[] getDc(char a) {
        int[] dc = new int[4];
        if (a == '|') {
            dc[0] = 0;
            dc[1] = 0;
        } else if (a == '-') {
            dc[0] = -1;
            dc[1] = 1;
        } else if (a == '+') {
            dc[0] = 0;
            dc[1] = 1;
            dc[2] = 0;
            dc[3] = -1;
        } else if (a == '1') {
            dc[0] = 0;
            dc[1] = 1;
        } else if (a == '2') {
            dc[0] = 0;
            dc[1] = 1;
        } else if (a == '3') {
            dc[0] = 0;
            dc[1] = -1;
        } else if (a == '4') {
            dc[0] = 0;
            dc[1] = -1;
        }
        return dc;
    }

    // 특정 위치에서 연결된 블럭들을 확인해 어떤 블럭이 올지 구해주는 함수.
    private static char findBlock(int[] now) {
        int[] tmpR = getDr('+');
        int[] tmpC = getDc('+');
        boolean[] answer = new boolean[4]; // 상 우 하 좌

        for (int i = 0; i < 4; i++) {
            int newR = now[0] + tmpR[i];
            int newC = now[1] + tmpC[i];


            if (newR > 0 && newC > 0 && newR <= R && newC <= C && board[newR][newC] != '.') {
                if (i == 0 && (board[newR][newC] == '|' || board[newR][newC] == '+' || board[newR][newC] == '1' || board[newR][newC] == '4' || board[newR][newC] == 'Z')) {
                    answer[0] = true;
                    continue;
                }
                if (i == 1 && (board[newR][newC] == '-' || board[newR][newC] == '+' || board[newR][newC] == '3' || board[newR][newC] == '4')|| board[newR][newC] == 'Z') {
                    answer[1] = true;
                    continue;
                }
                if (i == 2 && (board[newR][newC] == '|' || board[newR][newC] == '+' || board[newR][newC] == '2' || board[newR][newC] == '3'|| board[newR][newC] == 'Z')) {
                    answer[2] = true;
                    continue;
                }
                if (i == 3 && (board[newR][newC] == '-' || board[newR][newC] == '+' || board[newR][newC] == '1' || board[newR][newC] == '2'|| board[newR][newC] == 'Z')) {
                    answer[3] = true;
                }
            }

        }
//        System.out.println(Arrays.toString(answer));
        if (answer[0] && answer[1] && answer[2] && answer[3]) return '+';
        else if (answer[0] && answer[2]) return '|';
        else if (answer[1] && answer[3]) return '-';
        else if (answer[1] && answer[2]) return '1';
        else if (answer[0] && answer[1]) return '2';
        else if (answer[0] && answer[3]) return '3';
        else if (answer[3] && answer[2]) return '4';
        return '-';
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R + 1][C + 1];
        boolean[][] isVisited = new boolean[R + 1][C + 1];
        Integer[] start = new Integer[2], end = new Integer[2];
        // 입력 받기
        for (int i = 1; i <= R; i++) {
            String tmp = br.readLine();
            for (int j = 1; j <= C; j++) {
                board[i][j] = tmp.charAt(j - 1);
                if (board[i][j] == 'M') {
                    start[0] = i;
                    start[1] = j;
                } else if (board[i][j] == 'Z') {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }

        Queue<Integer[]> queue = new LinkedList<>();

        isVisited[start[0]][start[1]] = true;

        // 초기 시작 위치에서 도로인 부분 먼저 큐에 넣으면서 시작
        int[] tmpR = getDr('+');
        int[] tmpC = getDc('+');
        for (int i = 0; i < 4; i++) {
            int newR = start[0] + tmpR[i];
            int newC = start[1] + tmpC[i];
            if (newR > 0 && newC > 0 && newR <= R && newC <= C && board[newR][newC] != '.') {
                Integer[] next = {newR, newC};
                queue.add(next);
                isVisited[newR][newC] = true;
            }

        }

        while (!queue.isEmpty()) {
            Integer[] now = queue.poll();

            // 각각의 블럭이 순회할 수 있는 dr 과 dc를 각각 반환받기
            int[] r = getDr(board[now[0]][now[1]]);
            int[] c = getDc(board[now[0]][now[1]]);
//            System.out.println(now[0] + "  " + now[1]);
            for (int i = 0; i < 4; i++) {
                int newR = now[0] + r[i];
                int newC = now[1] + c[i];

                // 각각의 블럭을 순회하며 그 위치가 방문하지 않았으면 체크해봄
                if (newR > 0 && newC > 0 && newR <= R && newC <= C && !isVisited[newR][newC]) {
                    // 연결이 되어 있음에도 불구하고 도로가 깔려있지 않은 경우 -> 빈 도로
                    if (board[newR][newC] == '.') {

                        int[] tmpnow = {newR, newC};
                        // 현재 위치에서 어떤 도로를 깔아야 하는지 구하기
                        char block = findBlock(tmpnow);
                        System.out.println(newR + " " + newC + " " + block);
                        return;
                    } else {
                        // 연결된 도로가 잘 깔려잇는 경우
                        Integer[] next = {newR, newC};
                        // 큐에 넣고 방문체크하여 마저 방문해보자.
                        queue.add(next);
                        isVisited[newR][newC] = true;
                    }
                }

            }

        }

    }
}
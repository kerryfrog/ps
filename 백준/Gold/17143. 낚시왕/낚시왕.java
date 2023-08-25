import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    static class Shark {
        public int speed, direction, size;

        Shark(int speed, int direction, int size) {
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        private Point move(int x, int y) {
            int tmpX = x;
            int tmpY = y;

            // 조금더 빠르게 상어 이동하도록  최적화 가능 그러나 그냥구현
            for (int i = 0; i < speed ; i++) {
                tmpX += dx[direction];
                tmpY += dy[direction];

                // 내가 보는 방향쪽에서 부딛혀야함
                if (tmpX == 0 || tmpY == 0 || tmpX == R+1 || tmpY ==C+1) {
                    this.rotate();
                    tmpX += dx[direction] * 2;
                    tmpY += dy[direction] * 2;
                }
            }
            return new Point(tmpX, tmpY);
        }
        private void rotate(){
            switch (this.direction) {
                case 1:
                    this.direction =2;
                    break;
                case 2:
                    this.direction=1;
                    break;
                case 3:
                    this.direction = 4;
                    break;
                case 4:
                    this.direction =3;
                    break;
            }
        }

        @Override
        public String toString() {

            return "speed =" + this.speed + " size =" + this.size + " direction =" + this.direction;
        }
    }

    // M : 상어의 수
    static int R, C, M;
    // 칸에 상어의 번호 저장
    static int[][] board;
    // 상어의 정보 배열 (이거의 index로 상어 판별)
    static Shark[] sharks;

    // d는 1~4 (0번째 인덱스는 사용하지 않음
    //  상 하 우 좌
    static int[] dx = {100, -1, 1, 0, 0};
    static int[] dy = {100, 0, 0, 1, -1};
    static boolean[] isDead;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[R + 1][C + 1];
        // 상어의 번호는 1 부터 시작
        sharks = new Shark[M + 1];
        // 상어가 잡혔거나 죽었거나 .. ㅋㅋ
        isDead = new boolean[M + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            Shark s = new Shark(speed, direction, size);
            sharks[i] = s;
            board[x][y] = i;
        }

//        for (int j = 1; j <=R ; j++) {
//            for (int k =1 ; k <=C ; k++) {
//                System.out.print(board[j][k] + " ");
//            }
//            System.out.println();
//        }


//        for (int i = 1; i < M; i++) {
//            System.out.println(sharks[i]);
//        }


        // 풀이
        // 낚시왕의 이동
        for (int i = 1; i <= C; i++) {
//            System.out.println("낚시왕의 위치" + i);

            // 낚시 하기
            for (int j = 1; j <= R; j++) {
                // 상어 있으면 잡아.
                if (board[j][i] != 0) {
                    int sharkNumber = board[j][i];
                    board[j][i] =0;
                    answer += sharks[sharkNumber].size;
//                    System.out.println(j + " , " + i + "의 상어를 잡음 현재 무게" + answer);
                    break;
                }
            }
            // 2. 상어의 이동
            // 새 보드를 만드는게 나을듯 (왜냐하면 하나가 움직여서 멈춰있던 애랑 겹칠 수 있음)
            int[][] newBoard = new int[R+1][C+1];

            for (int j = 1; j <= R; j++) {
                for (int k = 1; k <= C; k++) {
                    // 상어 있을 때 상어 이동
                    if (board[j][k] != 0) {
                        int sharkNumber = board[j][k];
                        Point nextPos = sharks[sharkNumber].move(j, k);
//                        System.out.println("상어 " + sharkNumber + "번 이동 결과 " + j + " " +k + " ->  "+ nextPos.x + " "+ nextPos.y);
                        // 상어 이동 후 새 보드에 쓰기
                        if (newBoard[nextPos.x][nextPos.y] == 0) {
                            newBoard[nextPos.x][nextPos.y] = sharkNumber;
                        }else{ // 상어 이동 후 새 보드에 사람 있음
                            // 새 보드 위치에 이미 상어 있으면 잡아먹기
                            int alreadySharkNumber = newBoard[nextPos.x][nextPos.y];
                            // 원래 있던 애가 작은 경우
                            if(sharks[alreadySharkNumber].size < sharks[sharkNumber].size){
                                // 원래 애 죽이고 보드에 상어 변경
                                newBoard[nextPos.x][nextPos.y] = sharkNumber;
                            } 
                        }
                    }
                }
            }
            // 보드를 새 보드로 바꿔줌
            board = newBoard;
//            System.out.println("상어 이동 완료");
//            for (int j = 1; j <=R ; j++) {
//                for (int k =1 ; k <=C ; k++) {
//                    System.out.print(board[j][k] + " ");
//                }
//                System.out.println();
//            }
        }
        System.out.println(answer);


    }
}
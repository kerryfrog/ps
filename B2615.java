import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        //////////////////////////////////////////////////////////////
        // 테스트 후 아래 파일 입력을 표준입력으로 처리하는 문장은 주석 처리해주세요!!!! ( System.setIn처리 코드 )
        //////////////////////////////////////////////////////////////
//        System.setIn(new FileInputStream("untitled/Test5.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//		검정 바둑돌 1
//		희 바둑돌 2
//		없음 0
        int board [][] = new int[21][21];

        for(int i=1; i <20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<20 ; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }

        }

        //문제 풀이
        for(int i=1; i <20; i++) {
            for(int j=1; j<20 ; j++) {
                int target =0;
                if(board[i][j] == 0) {
                    continue;
                }else {
                    target = board[i][j];
                }

//                System.out.println("i = "+i + " j = "+ j);
                boolean isWin = true;
                if(board[i][j-1] == target  ) isWin = false;
                // 1. 동쪽 탐색
                for(int k = 1; k < 6; k++) {
                    if(j+k >= 20 ) {
                        if(k != 5){
                            isWin = false;
                        }
                        break;
                    }
                    if(k == 5 && board[i][j+k] == target) {
                        isWin = false;
                        break;
                    }
                    if(k != 5 && board[i][j +k] != target) {
                        isWin = false;
                        break;
                    }
                }
                if(isWin) {
                    System.out.println(target);
                    System.out.println(i+ " "+j);
                    return;
                }

                // 2. 동남 탐색
                isWin = true;
                if(board[i-1][j-1] == target ) isWin = false;
                for(int k =1; k< 6; k++) {
                    if(j+k >= 20 || i + k >= 20) {
                        if(k != 5){
                            isWin = false;
                        }
                        break;
                    }
                    if(k == 5 && board[i+k][j+k] == target) {
                        isWin = false;
                        break;
                    }
                    if(k != 5 && board[i+k][j +k] != target) {
                        isWin = false;
                        break;
                    }
                }
                if(isWin) {
                    System.out.println(target);
                    System.out.println(i+ " "+j);
                    return;
                }

                // 2. 남 탐색
                isWin = true;
                if(board[i-1][j] == target ) isWin = false;
                for(int k = 1; k< 6; k++) {
                    if( i+k >= 20) {
                        if(k != 5){
                            isWin = false;
                        }
                        break;
                    }
                    if(k == 5 && board[i+k][j] == target ) {
                        isWin = false;
                        break;
                    }
                    if(k != 5 && board[i+k][j] != target) {
                        isWin =false;
                        break;
                    }
                }
                if(isWin) {
                    System.out.println(target);
                    System.out.println(i+ " "+j);
                    return;
                }

                // 남서 탐색
                isWin = true;
                if(board[i-1][j+1] == target ) isWin = false;
                for(int k =1; k< 6; k++) {
                    if( i+k >= 20 || j - k < 1 ) {
                        if(k != 5){
                            isWin = false;
                        }
                        break;
                    }
                    if(k == 5 && board[i+k][j-k] == target) {
                        isWin = false;
                        break;
                    }
                    if(k != 5 && board[i+k][j-k] != target) {
                        isWin = false;
                        break;
                    }
                }
                if(isWin) {
                    System.out.println(target);
                    System.out.println((i+4) +" "+ (j-4));
                    return;
                }

            }
        }
        System.out.println("0");
    }
}

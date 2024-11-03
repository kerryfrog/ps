import java.io.*;
import java.util.*;

public class Main {
	public static void main(String [] args) throws IOException {
		int C, N;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		int [][] info = new int[N][2];
		int [] dp = new int[C + 101];

		for(int i=0; i< C + 101; i++){
			dp[i] = 999999999;
		}

		for(int i=0; i<N ; i++ ){
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
			dp[info[i][1]] = Math.min(info[i][0],dp[info[i][1]]);
		}
		dp[0] = 0;
		for(int i=1; i< C + 101; i++){
			for(int j=0; j<N; j++){
				if ( i- info[j][1] >= 0 && dp[i-info[j][1]] != 999999999){
//					if(dp[i] >  dp[i-info[j][1]] + info[j][0]) {
//						System.out.println("update dp["+ i +"] =" + (dp[i-info[j][1]] + info[j][0] ) + "  j =" + j);
//					}
					dp[i] = Math.min( dp[i], dp[i-info[j][1]] + info[j][0]);
				}
			}
		}
		int answer = 99999999;
//		for(int i = 0; i <= C+10; i++){
//			System.out.print(dp[i] + " ");
//		}
		for(int i = C; i < C + 101; i++){
			answer = Math.min(answer, dp[i]);
		}
//		System.out.println();
		System.out.println(answer);
	}
}
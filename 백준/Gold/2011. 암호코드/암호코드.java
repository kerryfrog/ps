import java.io.*;

public class Main {
  public static void main(String []args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String target = br.readLine();

    long [] dp= new long [target.length()+1];

    if(target.length() == 1){
      if(Integer.parseInt(target) == 0){
        System.out.println(0);
      }else{
        System.out.println(1);
      }
      return;
    }
    if(Integer.parseInt(target.substring(0,1)) == 0 ||
        (Integer.parseInt(target.substring(1,2)) == 0 && Integer.parseInt(target.substring(0,1)) > 2)
    ) {
      System.out.println(0);
      return;
    }

    dp[0] = 1;
    if(Integer.parseInt(target.substring(0,2)) == 10 || Integer.parseInt(target.substring(0,2)) == 20){
      dp[1] = 1;
    } else if(Integer.parseInt(target.substring(0,2)) <= 26){
      dp[1] = 2;
    }else{
      dp[1] = 1;
    }

    for (int i = 2; i <target.length(); i++) {

      if(Integer.parseInt(target.substring(i,i+1)) == 0){
        if(Integer.parseInt(target.substring(i-1,i)) == 1 || Integer.parseInt(target.substring(i-1,i)) == 2){
          dp[i] = dp[i - 2];
        }
        else{
          System.out.println(0);
          return;
        }
      }
      else if(Integer.parseInt(target.substring(i-1,i)) == 0){
        dp[i] = dp[i - 1];
      }
      else if(Integer.parseInt(target.substring(i-1,i+1)) <= 26){
        dp[i] = (dp[i - 1] + dp[i - 2])% 1000000 ;
      }else{
        dp[i] = (dp[i - 1])% 1000000;
      }
    }
    System.out.println(dp[target.length() - 1] % 1000000 );

//    for (int i = 0; i <target.length() ; i++) {
//      System.out.printf("%s ",dp[i]);
//    }
  }
}
// A B C D E F G H I J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
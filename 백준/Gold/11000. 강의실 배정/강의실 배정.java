import java.io.*;
import java.util.*;

class Time implements Comparable{
    public int start, end;
    Time(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Object o) {
        if(start == ((Time)o).start) {
            return end -  ((Time)o).end;
        } else{
            return start - ((Time)o).start ;
        }
    }
    @Override
    public String toString(){
        return "[" + start + ":" + end + "]";
    }
}


public class Main {

    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        Time [] timeList = new Time[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            timeList[i] = new Time(start,end);
        }
        Arrays.sort(timeList);

        PriorityQueue<Integer> classRoom = new PriorityQueue<>();

//        for(int i=0; i<N; i++){
//            System.out.println(timeList[i]);
//        }
        classRoom.add(timeList[0].end);

        for(int i=1; i<N; i++){
            if(classRoom.peek() <= timeList[i].start ){
                classRoom.poll();
            }
            classRoom.add(timeList[i].end);
        }
//        for(int i=0; i<classRoom.size(); i++){
//            System.out.println(classRoom.get(i));
//        }
//        System.out.println("-=-----");
        System.out.println(classRoom.size());
    }
}
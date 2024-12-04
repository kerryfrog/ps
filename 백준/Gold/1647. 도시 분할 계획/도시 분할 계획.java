import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int [] parent;

    public static class Node implements Comparable<Node>{
        int next, cost;

        Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "next=" + next + " cost="+cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] graph = new ArrayList[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, cost));
            graph[b].add(new Node(a, cost));
        }

        pq.addAll(graph[1]);
        visited[1] = true;
        int ans = 0;
        int maxCost = 0;
        for (int i = 1; i < N; i++) {
            while (!pq.isEmpty() && visited[pq.peek().next]) {
                pq.poll();
            }
            Node now = pq.poll();
//            System.out.println(now);
            visited[now.next] = true;
            ans += now.cost;
            maxCost = Math.max(maxCost, now.cost);
            for (int j = 0; j < graph[now.next].size(); j++) {
                if(!visited[graph[now.next].get(j).next]){
                    pq.add(graph[now.next].get(j));
                }
            }
        }
        System.out.println(ans -maxCost);
    }
}
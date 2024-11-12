#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);
#define INF 2147483646
using namespace std;
typedef pair<int, int>pii; // pair 값 두개를 같이 사용 하게 해줌 

int V, E, K;
int dist[20202];
vector<pii> d[20202];     //그래프의 정보 담음
int visited[20202];
priority_queue< pii, vector<pii>, greater<pii> > pq;
// 아주 기본적인 다익스트라 

void dijkstra(int st){
	dist[st] = 0;
	// { 거리 , 현재 vertex} 
	pq.push({ 0,st });

	while (!pq.empty()) {
		int curr = pq.top().second; // 방문한 vertex
		int currd = pq.top().first;
		visited[curr] = 1;
		pq.pop();
		// 이동후 정보 갱신
		for (auto i : d[curr]) {
			int next = i.first, nextd = i.second;
			if (dist[next] > currd + nextd && !visited[next]) {
				dist[next] = currd + nextd;
				pq.push({ dist[next], next });

			}
		}
	}

}

void clear() {
	while (!pq.empty())
	{
		pq.pop();
	}
	for (int i = 1; i <= V; i++) dist[i] = INF;
	for (int i = 0; i < 20202; i++) visited[i] = 0;
}




int main() {
	// V vertex의 개수 
	// Edge 의 개수 
	// K 시작 정점의 번호 
	int dis1, dis2, dis3;
	int case1, case2;
	int M, N; // 중간 , 끝
	cin >> V >> E;
	for (int i = 0; i < E; i++) {
		//인접 그래프 생성
		int a, b, c; cin >> a >> b >> c;
		d[a].push_back({ b,c });  // {vertex , 거리}
		d[b].push_back({ a,c });  //양방향 그래프
	}
	cin >> M >> N;
	// 초기값 설정
	for (int i = 1; i <= V; i++) dist[i] = INF;
	
	//case 1  M먼저 방문 

	dijkstra(1);
	dis1 = dist[M];
	clear();

	dijkstra(M);
	dis2 = dist[N];
	clear();

	dijkstra(N);
	dis3 = dist[V];
	clear();

	if (dis1 == INF || dis2 == INF || dis3 == INF)
		case1 = INF;
	else
		case1 = dis1 + dis2 + dis3;

	dijkstra(1);
	dis1 = dist[N];
	clear();

	dijkstra(N);
	dis2 = dist[M];
	clear();

	dijkstra(M);
	dis3 = dist[V];
	clear();
	
	if (dis1 == INF || dis2 == INF || dis3 == INF)
		case2 = INF;
	else
		case2 = dis1 + dis2 + dis3;
	
	if (case1 == INF && case2 == INF)
		cout << -1;
	else if (case1 <= case2)
		cout << case1;
	else
		cout << case2;
		
}
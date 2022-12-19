#연결 요소의 개수
# bfs/ dfs
import sys
from collections import deque
input = sys.stdin.readline

#N : 정점 M:간선
N,M  = map(int, input().split(" "))

#그래프 입력 받기
graph = [[] for _ in range(N+1)]
visited = [False] * (N+1)

for i in range(M):
    u, v = map(int, input().split(" "))
    graph[u].append(v)
    graph[v].append(u)

#BFS 구현
def BFS(graph, start):
    queue = deque([start])
    while queue:
        n = queue.popleft()
        if visited[n] == False:
            visited[n] = True
            if not graph[n]:
                ans+=1
            else:
                for i in graph[n]:
                    queue.append(i)

ans = 0
for i in range(1, N+1):
    if visited[i] == False:
        if not graph[i]:
            ans +=1
            visited[i] = True
        else:
            BFS(graph,i)
            ans +=1

print(ans)


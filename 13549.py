from queue import PriorityQueue



N, K = map(int, input().split(" ")) 

def BFS(start, end):
  queue = PriorityQueue()
  queue.put((0, start))
  visited = [int(0) for _ in range(100001)]
  while queue:
    cost,now = queue.get()
    if now == end:
      return cost
    visited[now] = 1
    if now * 2 <= 100000 and visited[now*2] == 0:
      queue.put(( cost, now*2 ))
    if now -1 >= 0 and visited[now-1] == 0:
      queue.put((cost +1,now-1 ))
    if now +1 <= 100000 and visited[now+1] == 0:
      queue.put((cost +1,now+1 ))

print(BFS(N,K))

import heapq

N = 0
dx = [0, 1, 0 ,-1]
dy = [1, 0, -1, 0]

def dijkstra(cave, start):
  q = []
  heapq.heappush(q, (cave[0][0], start ))
  distance[0][0] = cave[0][0]
  while q:
    dist, now = heapq.heappop(q)
    if distance[now[0]][now[1]] < dist :
      continue
    for i in range(4):
      new_x= dx[i]+ now[0]
      new_y= dy[i] + now[1]
      if  new_x <0 or new_x >= N or new_y <0 or new_y >=N:
        continue
      cost = dist + cave[new_x][new_y]
      if cost < distance[new_x][new_y]:
        distance[new_x][new_y] =  cost 
        heapq.heappush(q,(cost , [new_x, new_y] ))

problem = 1
if __name__ == '__main__':
  while True:
    ans = 2000
    N = int(input())
    if N == 0:
      break
    cave=[list(map(int, input().split())) for _ in range(N)]
    distance = [[3000]*N for _ in range(N)]
    dijkstra(cave, [0,0])
    print("Problem", problem ,end='' )
    print(':',distance[N-1][N-1])
    problem+=1
from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

n, m = map(int, input().split(" "))
board = []
visited = []

for i in range(n):
  line = list(map(int,input().split(" ")))
  if 2 in line:
    start  = [i,line.index(2)]
  board.append(line)
  visited.append([int(-1) for _ in range(m)])



def BFS(start):
  queue = deque()
  queue.append([start,0])
  visited[start[0]][start[1]] = 0

  while queue:
    now, cost = queue.popleft()

    for i in range(4):
      tmpx = now[1] + dx[i]
      tmpy = now[0] + dy[i] 
      if m> tmpx >= 0 and n> tmpy >= 0 and visited[tmpy][tmpx] ==  -1:
        if board[tmpy][tmpx] ==  0:
          visited[tmpy][tmpx] = 0
          continue
        else:
          queue.append([ [tmpy, tmpx], cost+1 ])
          visited[tmpy][tmpx] = cost+1


BFS(start)

for i in range(n):
  for j in range(m):
    if board[i][j] == 0:
      visited[i][j] =0

for row in visited:
  for i in row:
    print(i, end=" ")
  print()
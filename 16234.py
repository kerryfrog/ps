from collections import deque 


## 인구이동 
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

## input 
N, L, R= map(int, input().split(" "))
board = []

for i in range(N):
  board.append(list(map(int, input().split(" "))))


def bfs(i,j):
  union = []
  queue = deque()
  queue.append([i,j])
  union.append([i,j])
  while queue:
    loc = queue.popleft()
    for i in range(4):
      col = loc[0] + dy[i]
      row = loc[1] + dx[i] 
      if  0 <= row < N and  0 <= col < N and visited[col][row] == 0 : 
        if( L <= abs(board[loc[0]][loc[1]] - board[col][row]) <= R):
          visited[col][row] = 1
          queue.append([col ,row ])
          union.append([col,row])
  return union
          

result = 0
while 1:
  flag = 0
  visited = [ [0]*N for i in range(N) ]
  for i in range(N):
    for j in range(N):
      if visited[i][j] != 1:
        visited[i][j] =1
        union = bfs(i,j)
        if len(union) > 1: 
          flag =1 
          sum = 0
          for location in union:
            sum += board[location[0]][location[1]]
          for location in union:
             board[location[0]][location[1]] = sum//len(union) 
  if flag == 0:
    print(result)
    break
  result +=1 









# 알파벳 


# 세로: R, 가로: C
# 행 : x , 열 : y 
dx =[1, 0, -1, 0]
dy =[0, 1, 0, -1]


R, C = map(int, input().split(" "))

board = []
alpha = [0] * 26
visited =[[0]*C for _ in range(R) ]

for i in range(R):
  el = list(input())
  board.append(el)

ans =1
def dfs(now, visited, alpha ,cnt):
  global ans 
  visited[now[0]][now[1]] = 1
  alpha[ord(board[now[0]][now[1]]) - 65 ] =1
  for i in range(4):
    new_x = now[0] + dx[i]
    new_y = now[1] + dy[i]
    if 0  <= new_y < C  and 0 <= new_x < R  and visited[new_x][new_y] ==0:
      if alpha[ ord(board[new_x][new_y]) - 65] == 1:
        continue
      if cnt+1 > ans :
        ans = cnt+1
      dfs([new_x, new_y], visited, alpha ,cnt+1)
      visited[new_x][new_y] = 0
      alpha[ ord(board[new_x][new_y]) - 65] =0
  return 
dfs([0,0], visited, alpha ,1)
print(ans)



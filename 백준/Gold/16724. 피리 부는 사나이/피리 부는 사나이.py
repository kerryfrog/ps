N,M = map(int, input().split(" "))

board =[]

direction = {'U':[-1,0], 'D':[1,0], 'L':[0,-1], 'R':[0,1]}

for _ in range(N):
    board.append(list(input()))


visited = [[0 for _ in range(M)] for _ in range(N)]
finished = [[0 for _ in range(M)] for _ in range(N)]
answer =0

def dfs(x, y):
    global visited, finished, answer
    visited[x][y] = 1
    nx = x+ direction[board[x][y]][0]
    ny = y + direction[board[x][y]][1]

    if visited[nx][ny] ==0:
        dfs(nx,ny)
    else :
        if finished[nx][ny] ==0:
            answer += 1
    finished[x][y] = 1



for i in range(N):
    for j in range(M):
        if visited[i][j] == 0:
            dfs(i,j)



print(answer)
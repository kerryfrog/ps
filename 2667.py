#단지 번호 붙이기
from collections import deque
from importlib.resources import contents

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

#새로운 단지를 찾아주는 함수
def findNewStart(board, visited, N):
    for i in range(N):
        for j in range(N):
            if [i,j] not in visited:
                if board[i][j] == "1":
                    return i, j
    return -1,-1

def BFS(board, N):
    village = []
    visited = []
    cnt = 0
    x, y = 0,0 
    queue = deque()
    x , y = findNewStart(board, visited, N)           
    while x !=  -1 and y != -1:
        #print("new vilage start ", x ," ", y)
        queue.append([x,y])
        while queue:
            n = queue.popleft()
            if n not in visited:
                visited.append(n)
                cnt += 1
                for i in range(4):
                    tmpx = n[0] + dx[i]
                    tmpy = n[1] + dy[i]
                    if tmpx >= 0 and tmpy >= 0 and tmpx <N and tmpy <N and  [tmpx, tmpy] not in visited:
                        if board[tmpx][tmpy] == "1":
                            queue.appendleft([tmpx, tmpy])
                        else:
                            visited.append([tmpx,tmpy])
        village.append(cnt)
        cnt = 0
        x, y = findNewStart(board, visited, N)
    return village


#입력 
N = int(input())
board = []
test = []
for _ in range(N):
    board.append(list(input()))

ans = BFS(board, N)

print(len(ans))
ans.sort()
for _ in (ans):
    print(_)



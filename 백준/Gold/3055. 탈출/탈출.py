from collections import deque
import copy
R, C = map(int, input().split(" "))

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
forest = []

for i in range(R):
    forest.append(list(input()))

def water(board):
    n_water = copy.deepcopy(board)
    for i in range(R):
        for j in range(C):
            if board[i][j] == "*":
                for k in range(4):
                    nx = i + dx[k]
                    ny = j + dy[k]
                    if R > nx >= 0 and C > ny >= 0 and board[nx][ny] == ".":
                        n_water[nx][ny] = "*"
    return n_water

x, y = 0, 0
end_x, end_y = 0, 0
for i in range(R):
    for j in range(C):
        if forest[i][j] == "S":
            x, y = i, j
            forest[i][j] = "."
        if forest[i][j] == "D":
            end_x, end_y = i, j
now_state = [[x,y]]
answer = 0
isAnswer = False
while True:
    forest = water(forest)
    next_list = []
    visited = [[0 for _ in range(C)] for _ in range(R)]
    for i in now_state:
        now_x, now_y = i
        if now_x == end_x and now_y == end_y:
            print(answer)
            isAnswer = True
            break
        for k in range(4):
            nx = now_x + dx[k]
            ny = now_y + dy[k]
            if (
                R > nx >= 0
                and C > ny >= 0
                and (forest[nx][ny] == "." or forest[nx][ny] == "D")
                and visited[nx][ny] == 0
            ):
                visited[nx][ny] = 1
                next_list.append([nx, ny])
    if isAnswer:
        break
    if len(next_list) == 0:
        break
    now_state = next_list
    answer += 1

if not isAnswer:
    print("KAKTUS")
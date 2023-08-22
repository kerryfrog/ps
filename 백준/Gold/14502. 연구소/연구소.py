N, M = map(int, input().split(" "))
board = []
# 조합에 사용
isSelected = [[0 for _ in range(M)] for _ in range(N)]
# bfs에 사용
isVisited = [[0 for _ in range(M)] for _ in range(N)]
answer = 0

for _ in range(N):
    board.append(list(map(int, input().split(" "))))

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]


def bfs(new_board, x, y):
    global isVisited
    isVisited[x][y] = 1
    cnt = 1
    is_safe = True
    if new_board[x][y] == 2:
        is_safe = False
    queue = [[x, y]]
    while (len(queue) > 0):
        a, b = queue.pop()
        for i in range(4):
            newX = a + dx[i]
            newY = b + dy[i]
            if (newX >= 0 and newY >= 0 and newX < N and newY < M and isVisited[newX][newY] == 0 and new_board[newX][
                newY] != 1):
                isVisited[newX][newY] = 1
                if new_board[newX][newY] == 2:
                    is_safe = False
                cnt += 1
                queue.append([newX, newY])
    if (is_safe):
        return cnt
    else:
        return 0


def find_safe():
    new_board = [arr[:] for arr in board]
    cnt = 0
    for i in range(N):
        for j in range(M):
            if (isSelected[i][j] == 1):
                new_board[i][j] = 1
                cnt += 1
                if cnt == 3:
                    break
        if cnt == 3:
            break
    safe = 0
    global isVisited
    isVisited = [[0 for _ in range(M)] for _ in range(N)]

    for i in range(N):
        for j in range(M):
            if isVisited[i][j] != 1 and new_board[i][j] != 1:
                safe += bfs(new_board, i, j)
    return safe




def comb(depth, now):
    if depth == 3:
        global combi
        global answer
        tmp = find_safe()
        answer = max([tmp, answer])
        return
    for i in range(0, N):
        for j in range(0, M):
            now_order = now[0] * M + now[1]
            next_order = i * M + j
            if (now_order <= next_order and isSelected[i][j] != 1 and board[i][j] == 0):
                isSelected[i][j] = 1
                comb(depth + 1, [i, j])
                isSelected[i][j] = 0


comb(0, [0, 0])
print(answer)
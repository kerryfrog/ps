import copy

N, M, G, R = map(int, input().split(" "))

garden = []
fertilizer = []
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

for i in range(N):
    garden.append(list(map(int, input().split(" "))))

for i in range(N):
    for j in range(M):
        if garden[i][j] == 2:
            fertilizer.append([i, j])

answer = 0

def count(start):
    board = copy.deepcopy(garden)
    for g in start[0]:
        x, y = g
        board[x][y] = 3
    for r in start[1]:
        x, y = r
        board[x][y] = 4
    flower = 0
    now = 3
    green = start[0]
    red = start[1]
    while not (len(green) == 0 or len(red) == 0):
        #  초록 먼저 전파
        next_green = []
        next_red =[]
        for g in green:
            if board[g[0]][g[1]] == 0:
                continue
            for k in range(4):
                nx = g[0] + dx[k]
                ny = g[1] + dy[k]
                if 0 <= nx < N and 0 <= ny < M and (board[nx][ny] == 1 or board[nx][ny] == 2):
                    board[nx][ny] = now + 2
                    next_green.append([nx, ny])
        for r in red:
            if board[r[0]][r[1]] == 0:
                continue
            for k in range(4):
                nx = r[0] + dx[k]
                ny = r[1] + dy[k]
                if 0 <= nx < N and 0 <= ny < M:
                    if board[nx][ny] == 1 or board[nx][ny] == 2:
                        board[nx][ny] = now + 3
                        next_red.append([nx, ny])
                    elif board[nx][ny] == now + 2:
                        flower += 1
                        board[nx][ny] = 0
        now += 2
        red = next_red
        green = next_green
    return flower


# 1. 처음 위치 구하기
def solve(g, r, g_m, r_m, start):
    global answer
    if g == G and r == R:
        tmp = count(start)
        # print("start {}, count(start) {}".format(start, tmp))
        answer = max(answer, tmp)
        return
    if g < G:
        for i in range(g_m, len(fertilizer)):
            if visited[i] == 1:
                continue
            visited[i] = 1
            start[0].append(fertilizer[i])
            solve(g + 1, r, i, r_m ,start)
            visited[i] = 0
            start[0].pop()
    else:
        for i in range(r_m, len(fertilizer)):
            if visited[i] == 1:
                continue
            visited[i] = 1
            start[1].append(fertilizer[i])
            solve(g, r + 1, g_m, i, start)
            visited[i] = 0
            start[1].pop()


visited = [0] * len(fertilizer)
solve(0, 0,0,0, [[], []])
print(answer)
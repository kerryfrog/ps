# 최대 거리 9 *100 = 900
max_int = 1000
T = int(input())

# 세로: R, 가로: C
# 행 : x , 열 : y
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]


def dfs(now, visited, alpha, cnt):
    global ans
    visited[now[0]][now[1]] = 1
    alpha[ord(board[now[0]][now[1]]) - 65] = 1
    for i in range(4):
        new_x = now[0] + dx[i]
        new_y = now[1] + dy[i]
        if 0 <= new_y < C and 0 <= new_x < R and visited[new_x][new_y] == 0:
            if alpha[ord(board[new_x][new_y]) - 65] == 1:
                continue
            if cnt + 1 > ans:
                ans = cnt + 1
            dfs([new_x, new_y], visited, alpha, cnt + 1)
            visited[new_x][new_y] = 0
            alpha[ord(board[new_x][new_y]) - 65] = 0
    return


for t in range(1, T + 1):
    N = int(input())
    board = [[max_int] * (N + 1)]
    ans = [[max_int] * (N + 1)]
    for i in range(N):
        tmp = list(map(int, list(input())))
        tmp.insert(0, max_int)
        ans.append(tmp)
        board.append(tmp)

    for i in range(3, 2 * N + 1):
        for j in range(1, i):
            if j > N or i - j > N:
                # print("i=", i, "j=", j, "i-j=", i - j)
                continue
            # min value 설정에서도 못나가도록  설정해 주어야 함
            # print("i=", i, "j=", j, "i-j=", i - j)
            # print("min value is", board[j - 1][i - j], board[j][i - j - 1])
            board[j][i - j] += min(board[j - 1][i - j], board[j][i - j - 1])
    # print(board)
    print(f"#{t} {board[N][N]}")


# 알파벳


visited = [[0] * C for _ in range(R)]

for i in range(R):
    el = list(input())
    board.append(el)

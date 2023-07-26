from collections import deque
### 파핑파핑 지뢰찾기

T = int(input())
# 세로: R, 가로: C
# 행 : x , 열 : y
dx = [1, 1, 0, -1, -1, -1, 0, 1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]


## 처음에 *이 아닌 곳은 주변에 지뢰가 몇개 있는지
def make_board(board):
    init_visited = [[0 for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if board[i][j] == "*":
                init_visited[i][j] = "*"
                for k in range(8):
                    new_x = dx[k] + i
                    new_y = dy[k] + j
                    if (
                        new_x >= 0
                        and new_x < N
                        and new_y >= 0
                        and new_y < N
                        and board[new_x][new_y] != "*"
                    ):
                        init_visited[new_x][new_y] += 1
    return init_visited


def bfs(state, start):
    queue = deque()
    queue.append(start)
    while queue:
        x, y = queue.popleft()
        if state[x][y] == "*":
            continue
        state[x][y] = "*"
        for i in range(8):
            tmpx = x + dx[i]
            tmpy = y + dy[i]
            if N > tmpx >= 0 and N > tmpy >= 0 and state[tmpx][tmpy] != "*":
                if state[tmpx][tmpy] == 0:
                    for j in range(8):
                        tmpx2 = x + dx[j]
                        tmpy2 = y + dy[j]
                        if (
                            N > tmpx2 >= 0
                            and N > tmpy2 >= 0
                            and state[tmpx2][tmpy2] != "*"
                        ):
                            queue.append([tmpx2, tmpy2])
                else:
                    state[tmpx][tmpy] = "*"


for tc in range(1, T + 1):
    N = int(input())
    board = []
    for i in range(N):
        board.append(list(input()))
    ans = 0
    state = make_board(board)
    for i in range(N):
        for j in range(N):
            if state[i][j] == 0:
                bfs(state, [i, j])
                ans += 1

    for i in range(N):
        for j in range(N):
            if state[i][j] != "*":
                ans += 1
    print(f"#{tc} {ans}")

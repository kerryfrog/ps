from queue import PriorityQueue

T = int(input())

# 세로: R, 가로: C
# 행 : x , 열 : y
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]


## 우선순위 큐 를 이용한 BFS
def BFS(board):
    N = len(board)
    queue = PriorityQueue()
    queue.put((0, [0, 0]))
    visited = [[0 for _ in range(N)] for _ in range(N)]
    while queue:
        cost, now = queue.get()
        x, y = now
        if x == N - 1 and y == N - 1:
            return cost
        if visited[x][y] == 1:
            continue
        visited[x][y] = 1
        for i in range(4):
            new_x = x + dx[i]
            new_y = y + dy[i]
            if 0 <= new_y < N and 0 <= new_x < N and visited[new_x][new_y] == 0:
                queue.put((cost + board[new_x][new_y], [new_x, new_y]))


## 문제 풀이
for t in range(1, T + 1):
    N = int(input())
    board = []
    for i in range(N):
        tmp = list(map(int, list(input())))
        board.append(tmp)
    ans = BFS(board)
    print(f"#{t} {ans}")

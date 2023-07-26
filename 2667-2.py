from collections import deque

dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]


# DFS 가 stack 또는 재귀
# BFS 가 큐
def BFS(now):
    queue = deque()
    queue.append(now)
    visited[now[0]][now[1]] = 1
    cnt = 1
    while queue:
        x, y = queue.pop()
        for i in range(4):
            new_x = x + dx[i]
            new_y = y + dy[i]
            if 0 <= new_x < N and 0 <= new_y < N and visited[new_x][new_y] == 0:
                if board[new_x][new_y] == 1:
                    cnt += 1
                    queue.append([new_x, new_y])
                    board[new_x][new_y] = 0
                visited[new_x][new_y] = 1

    return cnt

N = int(input())
board = []
visited = [[0 for _ in range(N)] for _ in range(N)]
for _ in range(N):
    board.append(list(map(int, input())))

answer = []
for i in range(N):
    for j in range(N):
        if board[i][j] == 1:
            answer.append(BFS([i, j]))

print(len(answer))
answer.sort()
for i in answer:
    print(i)
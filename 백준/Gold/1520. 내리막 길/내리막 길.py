import heapq

# M: 세로
# N : 가로
M,N = map(int, input().split(" "))
board =[]

dx =[-1, 0, 1, 0]
dy =[0, 1, 0, -1]

for _ in range(M):
    board.append(list(map(int, input().split(" "))))


result = 0
dp = [[0 for _ in range(N)] for _ in range(M)]
hq = []
heapq.heappush(hq, (-1 *board[0][0], [0,0]))
dp[0][0] = 1
visited = [[0 for _ in range(N)] for _ in range(M)]

while hq:
    tmp, tmp2= heapq.heappop(hq)
    x, y = tmp2
    # print("( {}, {}) = {}".format(x,y, board[x][y]))
    if visited[x][y] == 1:
        continue
    visited[x][y] = 1
    for i in range(4):
        next_x = x + dx[i]
        next_y = y + dy[i]
        if 0<= next_x < M and 0<= next_y < N  and board[x][y] > board[next_x][next_y]:
            dp[next_x][next_y] += dp[x][y]
            heapq.heappush(hq, (-1 * board[next_x][next_y], [next_x, next_y] ))


# for i in range(M):
#     print(dp[i])
print(dp[M-1][N-1])
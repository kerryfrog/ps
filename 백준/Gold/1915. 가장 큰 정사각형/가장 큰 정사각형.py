N , M = map(int, input().split(" "))
board = []
for i in range(N):
    board.append(list(input()))

dp = [[0 for _ in range(M+1)] for _ in range(N+1)]



ans = 0
for i in range(1, N+1):
    for j in range(1, M+1):
        if board[i-1][j-1] == '1':
            tmp = [dp[i-1][j], dp[i-1][j-1], dp[i][j-1]]
            dp[i][j] = min(tmp) + 1
            ans = max(ans,dp[i][j]  )



print(ans*ans)
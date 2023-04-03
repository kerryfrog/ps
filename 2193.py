#이친수
N = int(input())

dp = [0] * (N+1)


for i in range(N+1):
  if i == 0 or i == 1 or i == 2:
    dp[i] = 1
    continue
  dp[i] = dp[i-1] + dp[i-2]

print(dp[N])


import sys
N, H = map(int, sys.stdin.readline().split(" "))

top_down = [0] * (H+1)
bottom_up = [0] * (H+1)

for i in range(N):
    now = (int(sys.stdin.readline()))

    if i %2 ==0:
        bottom_up[now] +=1
    else:
        top_down[now] +=1

dp = [0] * (H+1)
dp_top_down = [0] * (H+1)
dp_bottom_up = [0] * (H+1)

for i in range(1,H +1 ):
    dp_bottom_up[H -i] = dp_bottom_up[H-i + 1] + bottom_up[H-i]
    dp_top_down[i] = dp_top_down[i-1] +  top_down[H+1-i]
    dp[i] += dp_top_down[i]
    dp[H -i] += dp_bottom_up[H -i]

ans = min(dp[1:])
cnt = len(list(filter(lambda x: dp[x] == ans , range(len(dp[1:])))))

print(ans, cnt , sep=" ")
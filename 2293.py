import sys

input = sys.stdin.readline

n, k = map(int , input().split())
coin =[]
for i in range(n):
    coin.append(int(input()))

ans = [_*0 for _ in range(k+2)]
ans[0] = 1
for i in coin:
    for j in range(k+1):
        if j < i:
            continue
        ans[j] += ans[j-i]

print(ans[k])
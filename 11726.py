import sys

# 2xn 타일링  

input = sys.stdin.readline

N = int(input().rstrip())
#결과 배열 0으로 초기화 후 시작
ans = [_*0 for _ in range(N+2)]
ans[1] = 1
ans[2] = 2

for i in range(N+2):
    if i < 3:
        continue
    ans[i] = ans[i-2] +ans[i-1]

#print(ans)
print(ans[N] % 10007)
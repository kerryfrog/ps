#피보나치수
import sys

input = sys.stdin.readline

N = int(input().rstrip())

#호출 스택의 제한이 있기 때문에 bottom up구현이 더 좋음
fibo = [ _*0 for _ in range(N+1) ]
#print(fibo)
fibo[0] = 0 
fibo[1] = 1

for i in range(N+1):
    if i <2:
        continue
    fibo[i] = fibo[i-1] + fibo[i-2]

print(fibo[N])
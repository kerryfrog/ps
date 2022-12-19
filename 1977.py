#완전 제곱수 
import sys 
import math
input = sys.stdin.readline

M = int(input().rstrip())
N = int(input().rstrip())

start = math.floor(M ** 0.5)
end = math.ceil(N**0.5)
min = 1000000
sum = 0
for i in range(start, end+1):
    #print("i is ",i, i**2)
    if i**2 >= M and i **2 <= N:
        sum += i**2
        if min > i**2:
            min = i**2

if sum ==0:
    print(-1)
else:
    print(sum)
    print(min)

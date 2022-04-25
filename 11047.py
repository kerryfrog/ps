#코인 0 
import sys
input = sys.stdin.readline


N, K = map(int, input().split(" "))
coin =[]
for i in range(N):
    coin.append(int(input().rstrip()))


#구현
cnt = 0
collect = 0
iter = len(coin) -1
while collect < K:
    #print(collect, "now coin=", coin[iter], "cnt=",cnt)
    if collect + coin[iter] <= K:
        num = (K-collect) // coin[iter]
        collect += num * coin[iter]
        cnt += num 
    iter -= 1 
    #print("after if", collect)
    if collect == K:
        print(cnt)
        break



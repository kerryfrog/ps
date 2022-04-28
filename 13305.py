#주유소
import sys
input =sys.stdin.readline

N = int(input())
cost = []
gasCost = []

cost = input().rstrip().split(" ")
gasCost = input().rstrip().split(" ")
cost = list(map(int,cost))
gasCost = list(map(int,gasCost))

min = gasCost[0]
ans =0
for i in range(len(gasCost)):
    if i == 0:
        continue
    #print(cost[i-1], min)
    ans += cost[i-1] * min
    #print("now ans =",ans)
    if gasCost[i] < min:
        min = gasCost[i]

print(ans)

import sys
# readline은 개행 문자를 포함한다.
input = sys.stdin.readline
#input 받기

#rstrip : 오른쪽 공백을 삭제 
N = int(input().rstrip())

stair = []
for i in range(N):
    stair.append(int(input().rstrip()))

#print(stair)

#문제 풀이 by bottom- up
# size N의 코스트 배열을 생성
cost = [ _*0 for _ in range(N)]
#print(cost)

#초기값 설정
cost[0] =stair[0]
cost[1] =stair[1]

#max_cost[i] -> i 포함한 아래 세개를 더한 값. 
max_cost = [_*0 for _ in range(N)]
for i in range (N):
    if i <2:
        continue
    max_cost[i] =  stair[i-1] + stair[i]


for i in range(N):
    if i<2:
        continue
    if cost[i-1] + stair[i] == cost[i-2] + max_cost[i]:
        cost[i] = cost[i-2]+ stair[i]
    cost[i] = max(cost[i-1], cost[i-2]) +stair[i]

print(cost)
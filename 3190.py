from collections import deque
import sys
input = sys.stdin.readline

#보드의 크기
N = int(input())

#사과의 개수
K = int(input())
apple = []
for i in range(K):
    a = list(map(int, input().split(" ")))
    apple.append(a)
print(apple)

#방향변환 횟수
L = int(input())

direction = []
for i in range(L):
    a, b =(input().split(" "))
    if b == "L":
        #왼쪽 : 0
        direction.append([int(a),0 ])
    else:
        # 오른쪽: 1
        direction.append([int(a),1])
print(direction)
x, y =0,0 
length = 1
nowDirection = [1,0]
dxdy = deque([[1,0], [0,1], [-1,0], [0,-1]])
print(dxdy)
dirIndex = 0
for i in range(sys.maxint):
    #가장 처음에는 방향을 찾아준다.  = nowDirection
    if direction[dirIndex] == i:
        #좌회전
        if direction[dirIndex] == 0:
            tmp = dxdy.popleft()
            dxdy.append(tmp)
            nowDirection = dxdy.popleft()
            dxdy.appendleft(nowDirection)
        else:
            #우회전
            tmp = dxdy.pop()
            dxdy.appendleft(tmp)
            nowDirection = tmp
    #움직여 준다.
    x = x + nowDirection[0]
    y = y + nowDirection[1]
    #보드를 나갔는지 확인하기 
    if x < 0 or y <0 or x >N or y> N:
        print(i)
        break
    if [x,y] in apple:
        length = length +1 
    

    



# 풍선 터뜨리기
from collections import deque
import sys
input = sys.stdin.readline

N = int(input())
balloon = []
deq = deque()
balloon =list(map(int,sys.stdin.readline().split()))


ans = []
for i in range(len(balloon)):
    deq.append( [i+1, balloon[i]] )

while deq:
    a = deq.popleft()
    ans.append(a[0])
    if not(deq):
        break
    if a[1] > 0:
        for i in range(a[1]-1):
            tmp = deq.popleft()
            deq.append(tmp)
    else:
        for i in range(-(a[1])):
            tmp = deq.pop()
            deq.appendleft(tmp) 
    #print("ballon pop", a)

for i in ans:
    print(i,end=" ")


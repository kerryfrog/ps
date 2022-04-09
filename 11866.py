# 11866 요세푸스 문제 0
from collections import deque
import sys

input = sys.stdin.readline

N, K = map(int, input().split(" "))
q = deque()
ans = []
for i in range(N):
    q.appendleft(i+1)

while q:
    for i in range(K-1):
        a = q.pop()
        q.appendleft(a)
    ans.append(q.pop())

ansStr = "<" + str(ans[0])
for i in range(len(ans)):
    if i == 0:
        continue
    ansStr = ansStr +", " + str(ans[i])

ansStr = ansStr + ">"
print(ansStr)


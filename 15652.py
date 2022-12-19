# N과 M(4)

import sys
input =sys.stdin.readline

N,M = map(int, input().split(" "))
ans = []

def sequence(level):
    if level == M:
        print(" ".join(map(str,ans)))
        return
    for i in range(N):
        if ans and ans[-1] > i+1:
            continue
        ans.append(i+1)
        sequence(level + 1)
        ans.pop()

sequence(0)
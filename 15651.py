# N과 M(3)
import sys
input =sys.stdin.readline

N,M = map(int, input().split(" "))
ans = []

def sequence(level):
    if level == M:
        print(" ".join(map(str,ans)))
        return
    for i in range(N):
        ans.append(i+1)
        sequence(level + 1)
        ans.pop()

sequence(0)
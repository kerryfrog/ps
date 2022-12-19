# N과 M(1)
import sys
input =sys.stdin.readline

N,M = map(int, input().split(" "))
visited = [False] *N
ans = []

def sequence(level):
    if level == M:
        print(" ".join(map(str,ans)))
        return
    for i in range(len(visited)):
        if not visited[i]:
            visited[i] = True
            ans.append(i+1)
            sequence(level + 1)
            visited[i] = False
            ans.pop()

sequence(0)
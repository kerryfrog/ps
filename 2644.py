#촌수계산
from collections import deque

def BFS(graph, start, end):
    visited = []
    queue = deque()
    queue.append((start, 0))
    while queue:
        n, x = queue.popleft()
        if n not in visited:
            visited.append(n)
            if n == end:
                return x
            if n in graph:
                tmp = list(set(graph[n]) - set(visited))
                tmp.sort()
                for k in tmp:
                    queue.append((k, x+1))
    return -1

graph = {}
#전체 사람수 
numOfPeople = int(input())
target = input().split(" ")
a,b = [int(i) for i in target ]

#그래프 정보 입력받기 
n = int(input())
for i in range(n):
    tmp = input().split(" ")
    n1,n2 = [int(j)for j in tmp]
    if n1 not in graph:
        graph[n1] = [n2]
    elif n2 not in graph[n1]:
        graph[n1].append(n2)
    if n2 not in graph:
        graph[n2] = [n1]
    elif n1 not in graph[n2]:
        graph[n2].append(n1)

print(BFS(graph, a, b))
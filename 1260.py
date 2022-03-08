from collections import deque
# 깊이 우선 탐색 
# stack 을 이용한 구현 
def DFS(graph, start):
    visited =[]
    stack = [start]

    while stack:
        n = stack.pop()
        if n not in visited:
            visited.append(n)
            if n in graph:
                tmp = list(set(graph[n]) - set(visited))
                tmp.sort(reverse=True)
                stack += tmp
        
    return " ".join(str(i) for i in visited)

#너비 우선 탐색
#큐를 이용하여 구현 
def BFS(graph, start):
    visited = []
    queue = deque([start])

    while queue:
        n = queue.popleft()
        if n not in visited:
            visited.append(n)
            if n in graph:
                tmp = list(set(graph[n]) - set(visited))
                tmp.sort()
                queue += tmp

    return " ".join(str(i) for i in visited)



#인접 리스트을 이용하여 그래프 저장
graph = {}

#입력 
n = input().split(" ")
node, edge, start = [int(i) for i in n ]

for i in range(edge):
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

print(DFS(graph, start));  
print(BFS(graph, start));  
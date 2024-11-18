N, M = map(int, input().split(" "))
graph = [[] for _ in range(N + 1)]

for _ in range(M):
    st, ed, cost = map(int, input().split(" "))
    graph[st].append([ed, cost])
    graph[ed].append([st, cost])

start, end = map(int, input().split(" "))


def check(now_cost):
    stack = [start]
    visited = [0 for _ in range(N + 1)]
    while stack:
        now = stack.pop()
        if now == end:
            return True

        if visited[now] == 1:
            continue

        visited[now] = 1

        for i in range(len(graph[now])):
            if graph[now][i][1] >= now_cost and visited[graph[now][i][0]] == 0:
                stack.append(graph[now][i][0])

    return False


l, r = 0, 1000000000

while l <= r:
    mid = (l + r) // 2
    if check(mid):
        l = mid + 1
    else:
        r = mid - 1

        
print(r)
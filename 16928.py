from heapq import heappush, heappop

N, M = map(int, input().split(" "))

ladders = {}
snakes = {}

for i in range(N):
    a, b = map(int, input().split(" "))
    ladders[a] = b

for i in range(M):
    a, b = map(int, input().split(" "))
    snakes[a] = b

# 우선순위 큐 풀이
queue = [(0, 1)]
isVisited = [0 for _ in range(150)]
isVisited[1] = 1

while queue:
    cost, now = heappop(queue)
    if now == 100:
        print(cost)
        break
    if now > 100:
        continue
    for i in range(1, 7):
        new = now + i
        if isVisited[new] == 0:
            isVisited[new] = 1
            if new in ladders:
                new = ladders[new]
            elif new in snakes:
                new = snakes[new]
            heappush(queue, (cost + 1, new))
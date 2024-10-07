from copy import deepcopy
import heapq

# 상 좌 우 하
dx = [-1, 0, 0, 1 ]
dy = [0, -1 , 1, 0]

class node:
    def __init__(self, x, y, time):
        self.x = x
        self.y = y
        self.time = time
    def __lt__(self, other):
        if self.time == other.time:
            if self.x == other.x:
                return self.y < other.y
            else:
                return self.x < other.x
        else:
            return self.time < other.time


def find_short_fish(x, y):
    global shark, sea , time, N
    visited = [ [0 for _ in range(N)] for _ in range(N)]

#   조건에 맞는 최단거리 찾기 -> bfs
    heap = []
    heapq.heappush(heap, node(x, y, 0))
    while heap:
        now_node = heapq.heappop(heap)
        now_x, now_y, spent_time = now_node.x, now_node.y, now_node.time
        if visited[now_x][now_y] == 1:
             continue
        # 종료 조건 확인 후 리턴
        visited[now_x][now_y] = 1
        if sea[now_x][now_y] > 0 and sea[now_x][now_y] < shark["size"]:
            return [True, now_x, now_y, spent_time]

        for i in range(4):
            next_x = now_x + dx[i]
            next_y = now_y + dy[i]
            if next_x >= 0 and next_x < N and next_y >= 0 and next_y < N:
                if sea[now_x][now_y] <= shark["size"] :
                    heapq.heappush(heap, node(next_x, next_y, spent_time+1))
    return [False, -1, -1, -1]

sea = []
time = 0
shark = {"size" : 2, "x": 0 , "y": 0, "eat" : 0 }

N = int(input())
for i in range(N):
    line = list(map(int, input().split(" ")))
    if 9 in line:
        shark["x"] = i
        shark["y"] = line.index(9)
        line[line.index(9)] = 0
    sea.append(line)

#
while True:
    isFind, fish_x, fish_y ,spent = find_short_fish(shark['x'], shark['y'])
    if not isFind:
        break
    else:
        sea[fish_x][fish_y] = 0
        shark["x"] = fish_x
        shark["y"] = fish_y
        shark["eat"] += 1
        time += spent
        if shark["eat"]  == shark["size"]:
            shark["size"] += 1
            shark["eat"] = 0

print(time)
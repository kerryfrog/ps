## 자식 노드에서 부모까지의 거리 모두 측정
## 홀짝 판단.
import sys
from collections import defaultdict

input = sys.stdin.readline
tree = defaultdict(list)

V = int(input())
for i in range(V - 1):
    a, b = map(int, input().split(" "))
    # 연결된 노드,
    tree[a].append(b)
    tree[b].append(a)

# print(tree)
# {1: [2], 2: [1, 3, 4], 3: [2], 4: [2]})
is_leaf = [1 for _ in range(V + 1)]
height = [-1 for _ in range(V + 1)]

queue = [1]
height[1] = 0
while queue:
    now_node = queue.pop()
    connects = tree[now_node]
    child = 0
    for node in connects:
        ## 방문한 적 없음 (자식 노드)
        if height[node] == -1:
            child += 1
            height[node] = height[now_node] + 1
            queue.append(node)
    if child > 0:
        is_leaf[now_node] = 0

game = 0
for i in range(1, len(is_leaf)):
    if is_leaf[i] == 1:
        game += height[i]

if game % 2 == 1:
    print("Yes")
else:
    print("No")

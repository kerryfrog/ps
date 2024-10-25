N = int(input())

tree = [[] for _ in range(N)]
info = list(map(int, input().split(" ")))
root_node = 0
target = int(input())

for i in range(N):
    if info[i] == -1:
        root_node = i
        continue
    if i == target:
        continue
    tree[info[i]].append(i)
dp = [0 for _ in range(N)]

visited = [0 for _ in range(N)]

ans = 0
def count(now):
    global  ans
    if visited[now] == 1:
        return
    if len(tree[now]) ==0:
        # print("ans up now {}".format(now))
        ans += 1

    visited[now] = 1
    for i in tree[now]:
        count(i)
    return


count(root_node)
if root_node == target:
    print(0)
else:
    print(ans)
# print(dp[root_node] - dp[target] )
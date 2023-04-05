N = int(input())

adj = [[] for _ in range(N + 1)]

for i in range(1, N + 1):
  adj[i].append(int(input()))

print(adj)

def dfs(now, visited):
  visited.add(now)
  checked[now] =1
  for i in adj[now]:
    if i not in visited:
      dfs(i, visited.copy())
    else:
      ans.extend(list(visited))


ans = []
checked = [0] * (N+1) 
for i in range(1, N+1):
  if not checked[i]:
    dfs(i, set([]))  

ans.sort()
print(ans)
# for i in ans:
#   print(i)

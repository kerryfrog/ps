#네트워크 연결

# N : 컴퓨터의 수 M :연결할 수 있는 선의 수 
# 모든 컴퓨터를 연결하는데 드는 비용 최소

#  크루스칼 연습 
# https://bbbyung2.tistory.com/74 참고 
# 백준 제출 안함 !! 
N = int(input())
M = int(input())

edges = []

parent = [i for i in range(N +1 )]

for _ in range(M):
  a, b, c = map(int, input().split(" "))
  edges.append((c, a, b))
    
edges.sort()

def find_parent(a):
  if parent[a] != a :
    parent[a] = find_parent(parent[a])
  return parent[a]

def union(a, b):
  a_p = find_parent(a)
  b_p = find_parent(b)
  if a_p < b_p:
    parent[b_p] = a_p
  else:
    parent[a_p] = b_p
  
result = 0
for edge in edges:
  cost , a, b = edge

  if find_parent(a) != find_parent(b):
    union(a,b)
    result += cost 

print(result)



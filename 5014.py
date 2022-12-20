from collections import deque 

def BFS():
  queue = deque()
  queue.append([ S , 0])
  visited = [ 0 for _ in range(F+1)]
  visited[S] = 1
  while (queue):
    floor, cost = queue.popleft()
    if floor == G:
      return cost
    if floor + U <= F and visited[floor+U] == 0:
      visited[floor + U] = 1 
      queue.append([floor+U, cost+1])
    if floor - D >= 1 and visited[floor - D] == 0:
      visited[floor - D] = 1 
      queue.append([floor-D, cost+1])
  return "use the stairs"
      

F, S , G, U, D = map(int,input().split(" "))

print(BFS());

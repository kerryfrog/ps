from collections import deque

def BFS(board, finish):
  visited = []
  queue = deque()
  queue.append([[0,0], 1])
  while(queue):
    n , cost = queue.popleft()
    if board[n[0]][n[1]] == "0":
      visited.append(n)
      continue
    if n[0] == finish[0] and n[1] == finish[1]:
      return cost
    if n not in visited:
      visited.append(n)
      if n[1] - 1 >= 0:
        queue.append([ [n[0], n[1]-1], cost+1 ])
      if n[0] + 1 <= finish[0]:
        queue.append([[n[0]+1, n[1]], cost+1])
      if n[0] - 1 >= 0:
        queue.append([ [n[0] -1 , n[1]], cost+1 ])
      if n[1] + 1 <= finish[1]:
        queue.append([ [n[0], n[1]+1], cost+1 ])



N, M = map(int , input().split(" "))
board = []

for _ in range(N):
  board.append(list(input()))

print(BFS(board, [N-1, M-1]))

from collections import deque

def BFS(start, finish, shop):
  visited =[]
  queue = deque()
  queue.append(start)
  visited.append(start)

  while queue:
    n = queue.pop()
    if abs(n[0] - finish[0] )+ abs(n[1]-finish[1]) <= 1000 :
      print("happy");
      return
    for el in shop:
      if el in visited:
        continue
      if el not in visited and abs(el[0] - n[0]) + abs(el[1]-n[1]) <= 1000 :
        visited.append(el)
        queue.append(el)
  print("sad")
  return        

t = int(input())
for i in range(t):
  n = int(input())
  start = list(map(int,input().split(" ")))
  shop = []
  for i in range(n):
    shop.append( list(map(int,input().split(" ")) ) )
  finish = list(map(int,input().split(" ")))
  BFS(start, finish, shop)
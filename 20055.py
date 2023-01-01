from collections import deque 

# input 
N, K = map(int, input().split(" "))
belt = list(map(int, input().split(" ")))
belt_length = N*2 -1

queue = deque()
for i in belt:
  queue.append([i, False])

result = 0
while True:
  last = queue.pop()
  queue.appendleft(last)
  queue[N-1][1] = False 
  for i in range(N-2,-1,-1):
    now = queue[i]
    if now[1]:
      next_index = i + 1 
      if queue[next_index][0] > 0 and queue[next_index][1] == False:
        queue[next_index][0] -= 1
        queue[i][1] = False
        queue[next_index][1] = True
        if i == N -2:
          queue[next_index][1] = False
  if queue[0][0] > 0 and queue[0][1] == False:
    queue[0][0] -= 1
    queue[0][1] = True
  broken = 0
  for i in queue:
    if i[0] ==0:
      broken+=1 
  result +=1 
  if broken >= K:
    print(result)
    break 


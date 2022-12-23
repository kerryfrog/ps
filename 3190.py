from collections import deque

def snake(deque):
  direction = 0;
  deque.append([1,1])
  for sec in range(1,10100):
    new_location = [deque[len(deque)-1][0] + dy[direction], deque[len(deque)-1][1] +dx[direction] ]
    location = deque.popleft()
    if new_location[0] > N or new_location[1] > N or new_location[0] ==0 or new_location[1] ==0:
      return sec
    if new_location in deque or new_location ==location:
      return sec
    if new_location in apple:
      deque.appendleft(location)
      apple.remove(new_location)
    deque.append(new_location)
    
    if move[sec] =="D":
      direction +=1
      if direction ==4:
        direction =0
    
    if move[sec] =="L":
      direction -=1
      if direction ==-1:
        direction =3

### get input
N =int(input())
K = int(input())
apple  = []

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

for i in range(K):
  apple.append(list(map(int,input().split(" "))))

L = int(input())
move =[ "X" for _ in range(10100)]

for i in range(L):
  time, direction = input().split(" ")
  move[int(time)] = direction

deque = deque()
print(snake(deque))
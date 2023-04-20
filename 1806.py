# 부분 합 

N, S = map(int, input().split(" "))

num = list(map(int,input().split(" ")))
start=0
end = 0

part_sum = num[0]
ans = 100001
cnt = 1
while start < N and end < N:
  if S <= part_sum :
    if ans > cnt :
      ans = cnt
    part_sum -= num[start]
    cnt -= 1 
    start += 1
  else :
    end += 1
    cnt += 1
    if end < N:
      part_sum += num[end]

if ans == 100001:
  print(0)
else :
  print(ans)
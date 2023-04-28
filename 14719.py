H, W = map(int,input().split(" "))

block = list(map(int,input().split(" ")))

tmp_h = block[0]
tmp_st =0
ans = 0

for i in range(1, len(block)):
  if tmp_h <= block[i] or i == W -1:
    tmp_m = min(tmp_h , block[i])
    for j in range(i-1, tmp_st , -1):
      if tmp_m < block[j]:
        tmp_m = block[j]
        continue
      ans += tmp_m - block[j]
    tmp_st = i
    tmp_h = block[i]
  elif tmp_h > block[i]:
    continue
print(ans)
  



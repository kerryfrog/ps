belt = []

# N: 회전초밥 벨트에 놓인 접시 수
# d: 초밥의 가짓수 
# k: 연속해서 먹는 접시의 수 
# c: 쿠폰번호 
N, d, k ,c = map(int, input().split(" "))

for i in range(N):
  a = int(input())
  belt.append(a)

ans =[]
for i in range(N):
  tmp=[]
  if i + k > N:
    tmp = belt[i : N]
    tmp += belt[0:i+k-N]
  else:
    tmp = belt[i:i+k]
  tmp.append(c)
  tmp_set = list(set(tmp))
  if len(tmp_set) > len(ans):
    ans = tmp_set

print(len(ans))
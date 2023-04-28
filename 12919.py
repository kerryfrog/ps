# 두가지 연산 가능

# 1. 문자열 뒤에 A를 추가

# 2. 문자열 뒤에 B를 추가하고 뒤집음 
S = input()
T = input()

answer = {}
for i in range(len(S),len(T)+1):
  answer[i] = []

answer[len(S)].append(S)

for i in range(len(S),len(T)):
  words = answer[i]
  set_words = list(set(words))
  for j in set_words:
    answer[i+1].append(j+'A')
    list_j = list(j)
    list_j.reverse()
    answer[i+1].append('B'+ ''.join(list_j) )
  answer[i] = []
if T in answer[len(T)]:
  print(1)
else :
  print(0)

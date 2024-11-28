N = int(input())

works =[]

for _ in range(N):
    works.append(list(map(int, input().split(" "))))

works.sort(key =lambda x: -x[1])

times =[0 ] * (works[0][1] + 1)

empty = works[0][1]
no_answer = False
for work in works:
    now = min(empty, work[1])
    for i in range(work[0]):
        if now -i < 0:
            no_answer = True
            break
        times[now-i] +=1
        if times[now - i] == 1:
            empty = now- i -1
    if no_answer:
        break


ans = 0
if no_answer :
    ans = -1
elif times[0] >= 1:
    ans = 0
else:
    for i in range(len(times)):
        if times[i] >= 1:
            ans -=1
            break
        ans+=1
print(ans)
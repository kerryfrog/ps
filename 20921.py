#그렇고 그런 사이 ㅋㅋ 
import sys
input = sys.stdin.readline

N,K = map(int, input().split(" "))
ans = [_*0 for _ in range(N)]
relationship = 0
tmp =0
for i in range(N):
    #N-i -> 배열의 숫자를 역순으로 사용 N, N-1 ...
    if relationship < K:
        front = 0
        for j in range(len(ans)):
            if ans[j] > (N-i):
                #내 앞에 있는 나(N-i)보다 큰 수 
                front += 1
            # i- front 는 나보다 큰수인데, 내 앞에 있지는 않은 수 
            if ans[j] == 0 and (N)-j-1-(i-front)+relationship <= K:
                ans[j] = (N-i)
                relationship += (N)-j-1-(i-front)
                #print("(N-i)=",N-i," N-j-1=",N-j-1, "i-front = ", i-front, " realationship = ", relationship)
                break
        #print(ans)
    if relationship == K:
        tmp = (N-i)
        break

for i in range(tmp):
    for j in range(len(ans)):
        if ans[j] ==0:
            ans[j] =i+1
            break


str = " ".join(map(str, ans))
print(str)

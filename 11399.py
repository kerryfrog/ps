#ATM
import sys

input = sys.stdin.readline

N = int(input()) 
time =[]
time= (input().rstrip().split(" "))
time = list(map(int,time))

time.sort()
#print(time)
ans = 0
tmp =0
for i in time:
    tmp = i + tmp
    ans = tmp + ans 
    #print(ans)
print(ans)

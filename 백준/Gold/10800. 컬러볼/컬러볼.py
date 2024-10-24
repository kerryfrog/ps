from sys import stdin

N= int(input())
init_balls =[]

for i in range(N):
    ball = list(map(int, stdin.readline().split()))
    ball.append(i)
    init_balls.append(ball)

balls= sorted(init_balls,key =lambda x : x[1])

answer =[0 for i in range(N)]
color_sum = [0 for i in range(N+1)]

total_sum = 0
j =0
for i in range(N):
    a = balls[i]
    b = balls[j]

    while b[1] < a[1]:
        total_sum += b[1]
        color_sum[b[0]] += b[1]
        j+=1
        b= balls[j]
    answer[a[2]] = total_sum - color_sum[a[0]]

print("\n".join(map(str, answer)))
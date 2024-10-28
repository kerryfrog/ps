T= int(input())
n = int(input())
A = list(map(int, input().split(" ")))

m =int(input())
B = list(map(int, input().split(" ")))

a_sum =[0] * (n+1)
b_sum =[0] * (m+1)

a_dict = {}
b_dict ={}

# 1. 누적합 구하기
for i in range(1, n+1):
    a_sum[i] = A[i-1] + a_sum[i-1]
for i in range(1,m+1):
    b_sum[i] = B[i-1] + b_sum[i-1]

#2. 전체 합 경우의 수 구하기

#  길이
for i in range(1,n+1):
    #  시작점
    for j in range(0,n-i+1):
        now = a_sum[i+j] - a_sum[j]
        # print("i+j={}, j={}, now={}".format(i+j, j, now))

        if now in a_dict:
            a_dict[now] += 1
        else:
            a_dict[now] = 1

for i in range(1,m+1):
    #  시작점
    for j in range(0,m-i+1):
        now = b_sum[i+j] - b_sum[j]
        # print("i+j={}, j={}, now={}".format(i+j, j, now))

        if now in b_dict:
            b_dict[now] += 1
        else:
            b_dict[now] = 1

answer = 0
for key, val in a_dict.items():
    tmp = T - key
    if tmp in b_dict:
        answer += val * b_dict[tmp]

print(answer)
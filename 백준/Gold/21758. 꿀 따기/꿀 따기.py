N = int(input())
honey = list(map(int, input().split(" ")))


p_sum_front = [0] * N
p_sum_back = [0] * N

p_sum_front[0] = honey[0]
p_sum_back[0] = honey[N-1]

for i in range(1, N):
    p_sum_front[i] = p_sum_front[i - 1] + honey[i]
    p_sum_back[i] = p_sum_back[i-1] + honey[N-i-1]

answer = max(honey[1 : N - 1]) + p_sum_front[N - 1] - honey[0] - honey[N - 1]


tmp_answer = p_sum_front[N-1] * 2 - p_sum_front[1] * 2
for i in range(2, N):
    now_answer = p_sum_front[N-1] * 2 - p_sum_front[0] - p_sum_front[i] - honey[i]
    if  now_answer > tmp_answer:
        tmp_answer = now_answer

answer = max(tmp_answer, answer)

tmp_answer = p_sum_back[N-1] * 2 - p_sum_back[1] * 2
for i in range(2, N):
    now_answer = p_sum_back[N-1] * 2 - p_sum_back[0] - p_sum_back[i] - honey[N-i-1]
    if  now_answer > tmp_answer:
        tmp_answer = now_answer

answer = max(tmp_answer, answer)

print(answer)
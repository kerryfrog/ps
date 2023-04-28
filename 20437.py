from collections import defaultdict
# W : 소문자로 이루어진 문자열 
# K : 양의 정수 
# 어떤 문자를 정확하게 K개 포함하는 가장 짧은 연속 문자열의 길이 
# 어떤 문자를 정확하게 K개를 포함하고 마지막 글자가 해당 문자로 같은 가장 긴 연속문자열의 길이 ㄱ ㄱ

T = int(input())
for i in range(T):
    W = input()
    K = int(input())
    word_dict = defaultdict(list)   
    for i in range(len(W)):
        word_dict[W[i]].append(i)
    ans_max = 0
    ans_min =1000000
    for key in word_dict:
        idx_arr = word_dict[key]
        if len(idx_arr) >= K:
            for j in range(len(idx_arr) - K+1):
                new_len = idx_arr[j+K -1] - idx_arr[j] +1 
                if new_len < ans_min:
                    ans_min = new_len
                if new_len > ans_max:
                    ans_max = new_len
        else:
            continue 
    if ans_max == 0 and ans_min == 1000000:
        print(-1)
    else:
        print(ans_min, ans_max)

    
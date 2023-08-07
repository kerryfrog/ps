N, M = map(int, input().split(" "))
numbers = list(map(int, input().split(" ")))

answer = [_ for _ in range(M)]
numbers.sort()

def comb(cnt):
    if cnt == M:
        print(*answer)
        return

    for i in range(N):
        if cnt >0 and numbers[i] < answer[cnt-1]:
            continue
        answer[cnt] = numbers[i]
        comb(cnt+1)


comb(0)
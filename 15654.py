N, M = map(int, input().split(" "))
numbers = list(map(int, input().split(" ")))

numbers.sort()

isVisited = [0 for _ in range(N)]
answers = [0 for _ in range(M)]


# cnt 는 선택된 숫자의 개수
def comb(cnt):
    if cnt == M:
        print(*answers)
        return

    for i in range(N):
        if isVisited[i] != 1:
            answers[cnt] = numbers[i]
            isVisited[i] = 1
            comb(cnt + 1)
            isVisited[i] = 0


comb(0)

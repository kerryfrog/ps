def solve(numbers, now):
    if len(numbers[now]) == 1:
        return numbers[now][0]
    else:
        a = solve(numbers, numbers[now][1] - 1)
        b = solve(numbers, numbers[now][2] - 1)
        if numbers[now][0] == "+":
            return a + b
        elif numbers[now][0] == "-":
            return a - b
        elif numbers[now][0] == "*":
            return a * b
        elif numbers[now][0] == "/":
            return a / b


for t in range(10):
    N = int(input())
    numbers = []
    isCheck = [0] * N
    for i in range(N):
        line = input().split(" ")
        if len(line) > 2:
            numbers.append([line[1], int(line[2]), int(line[3])])
        else:
            numbers.append([int(line[1])])
    print("#", t + 1, " ", int(solve(numbers, 0)), sep="")

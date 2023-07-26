N, M = map(int, input().split(" "))

numbers = list(map(int, input().split(" ")))

# print(numbers, N, M)
for i in range(M):
    numbers.sort()
    tmp = numbers[0] + numbers[1]
    numbers[0] = tmp
    numbers[1] = tmp

print(sum(numbers))

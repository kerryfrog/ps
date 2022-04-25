#정수 삼각형
import sys

input = sys.stdin.readline

N = int(input())
triangle = []

for i in range(N):
    triangle.append(list(map(int, input().split(" "))))

for i in range(N):
    if i == 0:
        continue
    for j in range(i+1):
        if j == 0:
            triangle[i][j] = triangle[i-1][j] + triangle[i][j]
        elif j == i:
            triangle[i][j] = triangle[i-1][j-1] + triangle[i][j]
        else:
            triangle[i][j] = max(triangle[i-1][j-1], triangle[i-1][j]) + triangle[i][j]

print(max(triangle[N-1]))
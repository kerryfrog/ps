A = input()
B = input()


dp = [[0 for _ in range(len(A) + 1)] for _ in range(len(B) + 1)]

A = " " + A
B = " " + B
for b in range(1, len(B)):
    for a in range(0, len(A)):
        if A[a] == B[b]:
            dp[b][a] = dp[b - 1][a - 1] + 1
        else:
            dp[b][a] = max(dp[b - 1][a], dp[b][a - 1])

print(dp[len(B) - 1][len(A) - 1])
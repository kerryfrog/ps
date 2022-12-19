# N-Queen 
# pypy 로 해서 풀어야함.. c++이랑 똑같이 했는데도 시간초과남 ㅡ.ㅡ 팍씨 
import sys 
input = sys.stdin.readline

N = int(input().rstrip())

board = [ _*0 for _ in range(16) ]

def check(line):
    for i in range(line):
        # 현재 넣은 경우의 수가 가능한 경우의 수인가? 
        if board[line] == board[i] or abs(board[line] - board[i]) == line -i:
            return True
    return False

ans =0
def chess(line):
    global ans
    if line == N:
        ans += 1 
        return
    for i in range(N):
        board[line] = i
        if(check(line)):
            continue
        chess(line+1)

chess(0)
print(ans)
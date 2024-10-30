N,M = map(int, input().split(" "))

board =[]

direction = {'U':[-1,0], 'D':[1,0], 'L':[0,-1], 'R':[0,1]}

for _ in range(N):
    board.append(list(input()))


visited = [[0 for _ in range(M)] for _ in range(N)]
now_area = 1
ans = 0
for i in range(N):
    for j in range(M):
        if visited[i][j] != 0:
            continue
        x,y, d = i,j, board[i][j]
        # print("x:{} y:{} d:{}".format(x,y,d))
        while visited[x][y] == 0:
            visited[x][y] = now_area
            x = x + direction[d][0]
            y = y + direction[d][1]
            d = board[x][y]
        if visited[x][y] == now_area:
            ans +=1
        now_area += 1
        # print("visited",visited)
print(ans)
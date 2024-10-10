from collections import deque

N, M = map(int, input().split(" "))

#  상 우 하 좌
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
#  빈칸의 표현 -2
board = []
answer = 0
find_big_group_visited = [ [ 0 for _ in range(N)] for _ in range(N)]


def print_board():
    global board
    print("###############")
    for i in range(N):
        print(board[i])
    print("###############")

for i in range(N):
    line = list(map(int, input().split(" ")))
    board.append(line)


def find_group(target_x, target_y, find_big_group_visited):
    #  find_big_group_visited 해당 색상 방문 후 -> 그룹인 애들중 색 있은애들만 vistied check
    global board
    visited = [[0 for _ in range(N)] for _ in range(N)]
    group = []

    block_cnt, rainbow_block_cnt = 0 , 0

    queue = deque()
    queue.append([target_x,target_y])

    while queue:
        x, y = queue.popleft()
        if visited[x][y] == 1:
            continue
        visited[x][y] = 1
        if board[x][y] == 0:
            block_cnt += 1
            rainbow_block_cnt +=1
            group.append([x,y])
        elif board[x][y] == board[target_x][target_y]:
            block_cnt +=1
            find_big_group_visited[x][y] = 1
            group.append([x, y])
        for i in range(4):
            next_x = x + dx[i]
            next_y = y + dy[i]

            if 0<= next_x < N and 0 <= next_y < N :
                # print("next_x {}, next_y{} board[next_x][next_y] {} board[i,j] {}".format(next_x, next_y ,board[next_x][next_y] , board[i][j]))
                if board[next_x][next_y] == board[target_x][target_y] or board[next_x][next_y]  == 0:
                    queue.append([next_x, next_y])
    return [block_cnt, rainbow_block_cnt, group]

def find_big_group():
    find_big_group_visited = [ [ 0 for _ in range(N)] for _ in range(N)]
    #  x 좌표, y 좌표 , 블럭 색상 번호, 블록 개수 ,무지개블록 개수, 그룹 리스트
    max_info = [-1, -1, -2, 0, 0, []]

    #  행의 크기가 작은 블록, 열의 크기가 작은 블록 자동으로 됨
    for i in range(0,N):
        for j in range(0,N):
            if find_big_group_visited[i][j] == 1:
                continue
            else:
                if board[i][j] >= 1:
                    block_cnt, rainbow_block_cnt, group = find_group(i,j,find_big_group_visited)
                    if block_cnt > max_info[3]:
                        max_info = [i, j, board[i][j], block_cnt, rainbow_block_cnt, group]
                    elif block_cnt == max_info[3] and rainbow_block_cnt > max_info[4]:
                        max_info = [i, j, board[i][j], block_cnt, rainbow_block_cnt, group]
                    elif block_cnt == max_info[3] and rainbow_block_cnt == max_info[4]:
                        if max_info[0] < i :
                            max_info = [i, j, board[i][j], block_cnt, rainbow_block_cnt, group]
                        elif max_info[0] == i and max_info[1] < j:
                            max_info = [i, j, board[i][j], block_cnt, rainbow_block_cnt, group]
    #         돌면서 색상일 때 거기 기준으로 bfs
    #  주의할 점은 해당 색상 방문 후 -> 그룹인 애들중 색 있은애들만 vistied check
    #  블럭 개수 ,무지개블록 개수 , 그룹리스트
    return max_info[3:]



def gravity():
    global board
    # print("print in gravity")
    # print_board()
    #  y축 기준으로 0부터 맨 아래부터 위로 훑으면서 중력 적용
    for i in range(N):
        now = N-1
        is_bottom = False
        bottom = -1
        while now >= 0:
            if board[now][i] == -9 and not is_bottom:
                 bottom = now
                 is_bottom = True
            elif board[now][i] >= 0 and is_bottom:
                board[bottom][i] = board[now][i]
                board[now][i] = -9
                is_bottom = False
                for k in range(bottom, now-1 , -1):
                    if board[k][i] == -9:
                        bottom = k
                        is_bottom = True
                        break
            elif is_bottom and board[now][i] == -1:
                bottom = -1
                is_bottom = False
            now -= 1
    return True

def turn():
    global board
    new_board = [[0 for _ in range(N)] for _ in range(N)]

    for i in range(N):
        for j in range(N):
            new_board[i][j] = board[j][N-1-i]

    board = new_board
    return True

#  블록을 삭제하고 점수르 더함
def delete_block(max_info):
    global board, answer

    block_cnt, raionbow_block_cnt, group_list = max_info
    for group in group_list:
        board[group[0]][group[1]] = -9
    answer += block_cnt * block_cnt
    return True

# print_board()


# print(find_group(0,0))


# for i in range(N):
#     print(find_big_group_visited[i])

while True:
    # block_cnt, raionbow_block_cnt, group_list
    max_info = find_big_group()
    block_cnt, raionbow_block_cnt, group_list = max_info
    if block_cnt < 2 :
        break
    delete_block([block_cnt, raionbow_block_cnt, group_list])
    gravity()
    turn()
    gravity()
print(answer)
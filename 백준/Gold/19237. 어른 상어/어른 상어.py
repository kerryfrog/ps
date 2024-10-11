# N : 바다의 크기 M: 상어의 수 , K : 냄새가 유지되는 시간
from copy import deepcopy

N , M , K = map(int, input().split(" "))

# 0  1,  2, ,3 ,4
# X ,상, 하, 좌, 우
dx = [-1, -1, 1, 0 ,0]
dy = [-1, 0, 0, -1, 1]

sea = []
shark = [[] for _ in range(M + 1)]
shark_cnt = M
for i in range(N):
    line = list(map( int, input().split(" ")))
    sea.append(line)

# 처음 상어의 방향 받기
tmp_dir = list(map(int, input().split(" ")))

# 1 2 3 4
# 상 하 좌 우
for i in range(M):
    info = [[]]
    for j in range(4):
        info.append(list(map(int, input().split(" "))))
    shark[i+1] = info

# for i in range(M):
#     print("shark  ", i)
#     print(shark[i])
def myprint(tmp_sea, type):
    print("################")
    if type ==1 :
        for i in range(N):
            tmp = ''
            for j in range(N):
                tmp += str(tmp_sea[i][j][1])
                tmp += " "
            print(tmp)
    if type ==0 :
        print("sea info : is_shark, shark_num, shark_dir, smell, smell_time")
        for i in range(N):
            print(tmp_sea[i])
    print("################")


def init(sea):
    # is_shark, shark_num, shark_dir, smell, smell_time
    real_sea = [[[False, 0, 0, 0,0 ] for _ in range(N)] for _ in range(N)]

    for i in range(N):
        for j in range(N):
            if sea[i][j] != 0:
                real_sea[i][j] = [True, sea[i][j], tmp_dir[sea[i][j]-1], sea[i][j], K]
    return real_sea
# sea
# is_shark, shark_num, shark_dir, smell, smell_time
def make_shark_info(tmp_sea):
    # shark_num, shark_x, shark_y, shark_dir
    shark_info = []

    for i in range(N):
        for j in range(N):
            if tmp_sea[i][j][0]:
                shark_info.append([tmp_sea[i][j][1], i, j, tmp_sea[i][j][2] ] )
                tmp_sea[i][j][0] = False
                tmp_sea[i][j][1] = 0
    return shark_info

def minus_smell(tmp_sea):
    for i in range(N):
        for j in range(N):
            if tmp_sea[i][j][3] != 0:
                tmp_sea[i][j][4] -= 1
                if tmp_sea[i][j][4] == 0:
                    tmp_sea[i][j][3] =0
    return True

def find_next_dir(priority, no_smell, my_smell):
    if len(no_smell) > 0:
        for pr in priority:
            if pr in no_smell:
                return pr
    else:
        for pr in priority:
            if pr in my_smell:
                return pr
    return 1

## 상어는 동시에 이동 -> 새 보드 만들고 덮어쓰기
def move(sea):
    global shark_cnt
    new_sea = deepcopy(sea)
    minus_smell(new_sea)
    # 새바다에 상어 정보를 삭제도 함
    shark_infos = make_shark_info(new_sea)

    # sea
    # is_shark, shark_num, shark_dir, smell, smell_time
    for shark_info in shark_infos:
        # 현재 상어 방향에 해당하는 우선순위
        shark_num, shark_x, shark_y, shark_dir = shark_info
        # print("shark_num {}, shark_dir {}".format(shark_num, shark_dir))
        now_priority = shark[shark_num][shark_dir]
        no_smell = []
        my_smell = []

        for i in range(1,5):
            next_x = shark_x + dx[i]
            next_y = shark_y + dy[i]
            # print("next_x {}, next_y {}".format(next_x, next_y))
            if 0 <= next_x < N and 0 <= next_y < N :
                if sea[next_x][next_y][3] == shark_num:
                    my_smell.append(i)
                elif sea[next_x][next_y][3] == 0:
                    no_smell.append(i)

        # 각 우선순위별로 ..
        next_dir = find_next_dir(now_priority, no_smell, my_smell)
        x = shark_x + dx[next_dir]
        y = shark_y + dy[next_dir]

        new_sea[shark_x][shark_y][0] = False
        new_sea[shark_x][shark_y][1] = 0
        if new_sea[x][y][0] == True:
            shark_cnt -= 1
            if shark_num < new_sea[x][y][1]:
                new_sea[x][y] = [True, shark_num, next_dir, shark_num ,K ]
        else:
            new_sea[x][y] = [True, shark_num, next_dir, shark_num, K]
    return new_sea

time = 0
sea = init(sea)
# myprint(sea, 1)

while time <= 1000:
    next_sea = move(sea)
    sea = next_sea
    time += 1
    if shark_cnt == 1:
        break

if time > 1000:
    print(-1)
else:
    print(time)
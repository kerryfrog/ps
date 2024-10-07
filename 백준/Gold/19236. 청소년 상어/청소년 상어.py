from copy import deepcopy

dx = [-1, -1, 0, 1, 1, 1, 0 , -1]
dy = [0, -1, -1, -1, 0, 1, 1, 1]

sea = [ [ 0 for _ in range(4) ] for _ in range(4)]
#  x, y , direction
shark = []
shark_eat_sum = 0

def sea_print(sea):
    print("###########################")
    for i in range(4):
        print(sea[i])
    print("###########################")

# 1. 물고기 움직임 함수
def move(sea):
    #  바다 한번 받아서 fish 를 선언하고 시작하자
    fish_list = []
    for i in range(4):
        for j in range(4):
            if sea[i][j][0] == True:
                # key, x, y, dir
                fish_list.append( [ sea[i][j][1], i, j, sea[i][j][2] ] )
            elif sea[i][j][1] == 17:
                shark = {'x' : i, 'y': j }
    fish_list.sort(key=lambda x: x[0])

    for fish_num in range(1, 17):
        x, y ,direction= -1, -1, -1
        for i in range(4):
            for j in range(4):
                if sea[i][j][0] == True and sea[i][j][1] == fish_num:
                    x ,y= i, j
                    direction = sea[i][j][2]
        if x == -1 and y == -1 and direction == -1:
            continue
        for i in range(8):
            now_dir = (direction + i) % 8
            next_x = x + dx[now_dir]
            next_y = y + dy[now_dir]
            if 0 <= next_x < 4 and  0<= next_y < 4 and not (shark['x'] == next_x and shark['y'] == next_y ):
                isFish, swap_fish_num, swap_fish_dir = sea[next_x][next_y]
                sea[next_x][next_y] = [True, fish_num, now_dir ]
                if isFish:
                    sea[x][y] = [True, swap_fish_num, swap_fish_dir]
                else :
                    sea[x][y] = [False, -1, -1]
                break
            else:
                continue

# 상어가 물고기 먹는거에 대해서 dfs
# for 로 돌릴 수 있는 경우의 수는 상어가 먹을 수 있는 물고기들

def find_eat_fish_pos(shark_x, shark_y, shirk_dir ,sea):
    eatable_list = []
    while True:
        shark_x += dx[shirk_dir]
        shark_y += dy[shirk_dir]
        if 0 <= shark_x < 4 and 0 <= shark_y < 4:
            if sea[shark_x][shark_y][0] == True:
                eatable_list.append([sea[shark_x][shark_y][1], shark_x, shark_y, sea[shark_x][shark_y][2]])
        else:
            break
    return eatable_list

def dfs(shark_x, shark_y, shark_dir, sea, ans):
    st = [[shark_x, shark_y, shark_dir, deepcopy(sea), ans]]
    global shark_eat_sum
    while st:
        s_x, s_y, s_dir, now_sea, now_ans  = st.pop()
        # print("shark [", s_x, "," ,s_y ,"] now_dir=", s_dir , "now_ans=",now_ans )
        # print()
        if now_ans > shark_eat_sum:
            shark_eat_sum =  now_ans
            # print("갱신" , shark_eat_sum)
        eatable_list = find_eat_fish_pos(s_x, s_y, s_dir, now_sea)
        for eatable in eatable_list:
            #  먹을 수 있는것 포함해서 dfs 돌리기
            fish_num, fish_x, fish_y, fish_dir = eatable

            next_sea = deepcopy(now_sea)
            next_sea[s_x][s_y] = [False, -1, -1]
            next_sea[fish_x][fish_y] = [False, 17, fish_dir]

            #  상어 포식 완료
            move(next_sea)
            # sea_print(next_sea)
            st.append([fish_x, fish_y, fish_dir, next_sea, now_ans + fish_num])
    return True


######################   여기부터 실제 동작



for i in range(4):
    line = list(map (int, input().split(" ")))
    for j in range(4):
        fish_number = line[j * 2]
        fish_dir = line[ j*2 + 1]
        fish_dir -= 1
        sea[i][j] = [True, fish_number, fish_dir]



# 1. 첫번째 물고기 잡아먹음
is_fish, fish_num, fish_dir = sea[0][0]

sea[0][0] = [False, 17, fish_dir]
shark_eat_sum = fish_num
# 첫번째
move(sea)
dfs(0,0, fish_dir,sea ,shark_eat_sum)

print(shark_eat_sum)
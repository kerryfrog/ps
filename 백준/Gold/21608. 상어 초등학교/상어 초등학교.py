import sys

# 상 우 하 좌
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

N = int(input())
room = [[0 for _ in range(N+1)] for _ in range(N+1)]
infos = []
pos_infos = {}

for _ in range(N*N):
    infos.append(list(map(int, input().split(" "))))


def print_room():
    global N, room
    print("#############")
    for i in range(1, N+1):
        print(room[i][1:])
    print("#############")

def find(info):
    global room
    student = info[0]
    like = info[1:]

    ans_x, ans_y, ans_like, ans_empty = N+1, N+1 , 0, 0
    for i in range(1, N +1):
        for j in range(1, N +1 ):
    #         여기서 조건에 맞는애 확인하며 계속 갱신
            if room[i][j] != 0:
                continue
            now_like, now_empty = 0, 0
            for k in range(4):
                next_x = i + dx[k]
                next_y = j + dy[k]

                if 0 < next_x <= N and 0 < next_y <= N:
                    if room[next_x][next_y] == 0:
                        now_empty += 1
                    elif room[next_x][next_y] > 0 and room[next_x][next_y] in like:
                        now_like += 1
            # print("i={} j={} now_like={} now_empty={}".format(i,j,now_like, now_empty))
            if ans_x == N+1 and ans_y == N+1:
                ans_x, ans_y = i, j
                ans_like, ans_empty = now_like, now_empty
            elif ans_like < now_like:
                ans_x, ans_y = i, j
                ans_like, ans_empty = now_like, now_empty
            elif ans_like == now_like and ans_empty < now_empty:
                ans_x, ans_y = i, j
                ans_like, ans_empty = now_like, now_empty

    # print(" {} 배치완료 : [ {}, {}] ".format(student, ans_x, ans_y))
    # print("","배치완료 : [" , ans_x, ",", ans_y, "]"  )
    pos_infos[student] = [ans_x, ans_y]
    room[ans_x][ans_y] = student
    return True

def cal_answer():
    global room, pos_infos
    ans = 0
    like_infos = sorted(infos, key= lambda x:x[0])
    for key, value in pos_infos.items():
        like_cnt = 0
        for i in range(4):
            next_x = value[0] + dx[i]
            next_y = value[1] + dy[i]
            if 0 < next_x <= N and 0 < next_y <= N:
                if room[next_x][next_y] in like_infos[key-1][1:]:
                    like_cnt += 1
        if like_cnt == 1:
            ans += 1
        elif like_cnt == 2:
            ans += 10
        elif like_cnt == 3:
            ans += 100
        elif like_cnt == 4:
            ans += 1000
    return ans

for info in infos:
    find(info)
# print_room()
print(cal_answer())
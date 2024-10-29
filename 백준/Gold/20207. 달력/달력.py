import bisect
N = int(input())

schedules = []

for _ in range(N):
    tmp = list(map(int, input().split(" ")))
    tmp.append(tmp[1]-tmp[0])
    schedules.append(tmp)

calender = [[0 for _ in range(366)] for _ in range(1001) ]
schedules.sort(key=lambda x: ( x[0], -1 * x[2]))
# print(schedules)

# [[stx,ed_x, height]]
union = []
for schedule in schedules:
    st , ed, trash =schedule
    height =0
    for i in range(1, 1001):
        is_good = True
        for j in range(st, ed+1):
            if calender[i][j] == 1:
                is_good = False
                break
        if is_good:
            # 들어갈 위치를 찾음
            for j in range(st, ed + 1):
                calender[i][j] = 1
            height = i
            break
        # print("i{},j{}".format(i,j))
    a=bisect.bisect_right(union, st, key=lambda x: x[0])

    if a > 0 and union[a-1][1] >= st -1:
    #     두개를 합친다.
        union[a-1][1] = max(union[a-1][1], ed)
        union[a-1][2] = max(union[a-1][2], height)
    else:
        union.append([st, ed, height])

    union.sort(key=lambda x:x[0])

# print(union)

answer = 0
for uni in union:
    answer += (uni[1]-uni[0]+1) * uni[2]
print(answer)
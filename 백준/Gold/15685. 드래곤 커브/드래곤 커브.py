N = int(input())
dragons = []
direction = {0: [1, 0], 1: [0, -1], 2: [-1, 0], 3: [0, 1]}
# down -> left , right -> down , up -> right, left-> up
next_dir = {"01": [1, 0], "10": [0, -1], "0-1": [-1, 0], "-10": [0, 1]}
board = [[0 for _ in range(101)] for _ in range(101)]

gen_list = []
init_curve = []
for _ in range(N):
    x, y, st_d, gen = map(int, input().split(" "))
    n_x = x + direction[st_d][0]
    n_y = y + direction[st_d][1]
    curve = []
    curve.append([x, y])
    curve.append([n_x, n_y])
    init_curve.append(curve)
    gen_list.append(gen)


def make_curve(arr, gen):
    now = 0
    while now < gen:
        next_arr = []
        next_arr.append(arr[-1])
        # print("arr", arr)
        # print("next_`next_arr)
        for i in range(len(arr) - 1, 0, -1):
            dx = arr[i][0] - arr[i - 1][0]
            dy = arr[i][1] - arr[i - 1][1]
            tmp = str(dx) + str(dy)
            #   print("for test", i, "tmp", tmp)
            next_x = next_arr[-1][0] + next_dir[tmp][0]
            next_y = next_arr[-1][1] + next_dir[tmp][1]
            next_arr.append([next_x, next_y])
        #    print(next_arr)
        # print("next_arr", next_arr)
        del next_arr[0]
        arr.extend(next_arr)
        now += 1
        # print("now :{} arr:{}".format(now, arr))
    return arr


curves = []
for i in range(N):
    curves.append(make_curve(init_curve[i], gen_list[i]))


for curve in curves:
    for node in curve:
        if 0<= node[0] <=100 and 0<= node[1] <= 100:
           board[node[1]][node[0]] = 1

# print(board)

ans =0
for i in range(100):
    for j in range(100):
        if board[i][j] == 1 and board[i+1][j] == 1 and board[i+1][j+1] ==1 and board[i][j+1] ==1:
            ans +=1
print(ans)
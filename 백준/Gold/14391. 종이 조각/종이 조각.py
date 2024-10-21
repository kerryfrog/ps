answer = 0


def make_num(arr):
    num = 0
    x = 1
    for i in range(len(arr) - 1, -1, -1):
        num = num + arr[i] * x
        x = x * 10
    return num


def make_num2(arr, length):
    num = 0
    x = 1
    for i in range(length-1 ,  -1 ,-1):
        num = num + arr[i][0] * x
        x = x * 10

    return num


#  depth는 디버깅용
def func(h, w, arr, depth):
    # print("in func h={} w={}, depth={}".format(h, w, depth))
    # print(arr)
    # 탈출 조건 명시
    if h == 0 or w == 0:
        return 0

    if h == 1 and w == 1:
        return arr[0][0]

    #  해당 조건에서 max 값을 리턴하자 .
    now_max = 0
    #  가로 방향 으로 1~ w 만큼 잘라 본다.
    for i in range(1, w + 1):
        now_paper_num = make_num(arr[0][0:i])
        # 길이가 i 인 종이로 자르고나서 나머지 부분에서 두가지 분할 방법이 있음
        if h > 1:
            #  1. 가로줄 나머지 따로  그리고 그 아래
            tmp = now_paper_num + func(1, w - i, [arr[0][i:]], depth + 1) + func(h - 1, w, arr[1:], depth + 1)
            now_max = max(now_max, tmp)
            # print("###answer made 1!! : now_depth={} tmp={}".format(depth, tmp))

            #  2. 가로줄 나머지 세로로 분할
            a, b = [], []
            for j in range(h):
                if j != 0:
                    a.append(arr[j][i:])
                    b.append(arr[j][:i])
                else:
                    a.append(arr[j][i:])
            tmp = now_paper_num + func(h, w - i, a, depth + 1) + func(h - 1, i, b, depth + 1)
            # print("###answer made 2!! : now_depth={} tmp={}".format(depth, tmp))
            now_max = max(now_max, tmp)
        else:
            tmp = now_paper_num + func(1, w - i, [arr[0][i:]], depth + 1)
            # print("###answer made 3!! : now_depth={} tmp={}".format(depth, tmp) )
            now_max = max(now_max, tmp)

    # 이미 위에서 다 처리함
    # print("##################second gogo#################### depth={} h ={}".format(depth, h) )
    # 세로 방향 으로 1 ~ h 만큼 잘라 본다.
    for i in range(2, h + 1):
        # 길이가 i 인 종이로 자르고나서 나머지 부분에서 두가지 분할 방법이 있음
        now_paper_num = make_num2(arr, i)
        # print("now_paper_num: {}, i ={}".format(now_paper_num, i) )
        if w > 1:
            #   두가지 자르는 방법
            # print("1.b ", b)
            # 1. 잘린 세로 종이 옆 수평으로 쭉, 그 아래 전부
            a = []
            for t in arr[:i]:
                a.append(t[1:])
            # print("arr[i:]" , arr[i:])
            tmp = now_paper_num + func(i, w-1 , a, depth + 1) + func(h-i, w , arr[i:] ,depth + 1)
            now_max = max(now_max, tmp)
            # print("end 1 depth={} now_max:{}".format(depth,now_max))
            # 잘린 세로 종이 옆이 수직으로 쭉 , + 잘린 종이 아래
            a, b = [], []
            for j in range(h):
                if j >= i:
                    a.append([(arr[j][0])])
                b.append(arr[j][1:])
            # print("1.a ", a)
            tmp = now_paper_num + func(h-i, 1, a, depth+1) + func(h, w-1, b, depth+1)
            now_max = max(now_max, tmp)
            # print("end 2 now_max:{}".format(now_max))
        else:
            a = []
            for j in range(i, h):
                a.append([arr[j][0]])
            tmp = now_paper_num + func(h-i, 1, a, depth+1)
            now_max = max(now_max, tmp)

    return now_max


N, M = map(int, input().split(" "))
paper = []

for _ in range(N):
    paper.append(list(map(int, input())))

# print(paper)
# print(type(paper[0][0]))
# print(make_num([3, 1, 3]))
print(func(N, M, paper, 0))
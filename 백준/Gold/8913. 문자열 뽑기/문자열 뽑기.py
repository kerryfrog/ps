import heapq
T = int(input())


def cut(line):
    info = []
    st = 0
    before = line[0]
    for i in range(1, len(line)):
        if line[i] != before:
            if i - st >= 2:
                info.append([st, i])
            st = i
            before = line[i]
    if len(line) - st >= 2:
        info.append([st, len(line)])
    return info


def dfs(line):
    stack = []
    heapq.heappush(stack, (len(line), line))
    while stack:
        a, sentence = heapq.heappop(stack)
        # print("pop sentence {} len {} ".format(sentence, a))
        if len(sentence) < 2:
            continue
        small = cut(sentence)
        for i in range(len(small)):
            st, ed = small[i]
            # print("st:{} ed{}".format(st, ed))
            next_line = sentence[:st] + sentence[ed:]
            # print("new_append {}".format(next_line))
            if st ==0 and ed == len(sentence):
                return True
            heapq.heappush(stack, (len(next_line),next_line))
    return False

for t in range(T):
    now = input()
    # print(cut(now))
    # for i in range(len(small)):
    #     st, ed = small[i]
    #     print("st:{} ed{}".format(st, ed))
    #     # print(now[:st])
    #     # print(now[ed:])
    #     print("new_append {}".format(now[:st] + now[ed:]))
    if dfs(now):
        print(1)
    else:
        print(0)
T = int(input())


def union(parents, parent_node, child_node):
    new_parent = parents[parent_node]
    old_child = parents[child_node]
    for i in range(len(parents)):
        if parents[i] == old_child:
            parents[i] = new_parent
    # print("union", parents)


for tc in range(1, T + 1):
    N = int(input())
    x = list(map(int, input().split(" ")))
    y = list(map(int, input().split(" ")))

    E = float(input())

    parents = [i for i in range(N)]

    costs = []

    for i in range(N):
        for j in range(N):
            if i >= j:
                continue
            tmp = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j])
            costs.append((tmp, [i, j]))

    costs.sort()
    # print(costs)

    answer = 0
    for i in costs:
        cost, islands = i
        island_a, island_b = islands
        isDone = True
        for i in parents:
            if i != 0:
                isDone = False
        if isDone:
            break

        if parents[island_a] != parents[island_b]:
            # print("connect", island_a, island_b)
            if parents[island_a] < parents[island_b]:
                union(parents, island_a, island_b)
            else:
                union(parents, island_b, island_a)
            answer += cost
        else:
            continue
    # print(parents)
    print(f"#{tc} {round(answer * E)}")

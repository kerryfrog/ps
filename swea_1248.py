
for tc in range(1, T + 1):
    V, E, a, b = map(int, input().split(" "))
    numbers = list(map(int, input().split(" ")))
    tree = defaultdict(list)
    tree2 = [0 for _ in range(V + 1)]

    for i in range(E):
        parent = numbers[0]
        child = numbers[1]
        tree2[child] = parent
        tree[parent].append(child)
        numbers = numbers[2:]

    ## a의 부모 리스트 추출
    a_parents = []
    temp = a
    while temp != 0:
        temp_parent = tree2[temp]
        a_parents.append(temp_parent)
        temp = temp_parent
    print("paorent list", a_parents)

    ## 정답 찾기
    temp = b
    answer = 1
    while temp != 0:
        temp_parent = tree2[temp]
        if temp_parent in a_parents:
            answer = temp_parent
            break
        temp = temp_parent
    print(answer)

    count = 1
    queue = [answer]
    while queue:
        node = queue.pop()
        count += len(tree[node])
        queue.extend(tree[node])

    print(f"#{tc} {answer} {count}")

from collections import defaultdict

T = int(input())
V, E, a, b = map(int, input().split(" "))

num_list = list(map(int, input().split(" ")))

tree = defaultdict(list)
for i in range(E):
    parent = num_list[0]
    child = num_list[1]
    tree[parent].append(child)
    num_list = num_list[2:]

print(tree)



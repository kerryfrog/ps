import sys
G =int(sys.stdin.readline())
P =int(sys.stdin.readline())

tree = [i for i in range(G+1)]
answer =P

def find(a):
    if tree[a] == a:
        return a
    else:
        ap = find(tree[a])
        tree[a] = ap
        return ap

def union(a, b):
    ap = find(a)
    bp = find(b)

    if ap> bp:
        tree[ap] = bp
    else:
        tree[bp] = ap

plains = []
for _ in range(P):
    plains.append(int(sys.stdin.readline()))

for i in range(P):
    now = plains[i]
    now_p = find(now)

    if now_p == 0:
        answer = i
        break
    union(now_p, now_p-1)

print(answer)
#python은 리스트를 이용하여 스택을 흉내낸다.
# 4949 균형잡힌 세상

import sys
input = sys.stdin.readline
ans = []
bal = 1

while True:
    str = input().rstrip()
    if str == ".":
        break
    stack =[]
    bal = "yes"
    for i in str:
        if i == "(" or i == "[":
            stack.append(i)
        elif i == ")":
            if len(stack) < 1:
                bal = "no"
                break
            elif stack[-1] == "(":
                stack.pop()
            else:
                bal = "no"
                break
        elif i == "]":
            if len(stack) < 1:
                bal = "no"
                break
            elif stack[-1] == "[":
                stack.pop()
            else:
                bal = "no"
                break
    if len(stack) != 0:
        bal = "no"
    ans.append(bal) 

for i in ans:
    print(i)
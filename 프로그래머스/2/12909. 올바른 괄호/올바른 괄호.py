def solution(s):
    answer = True
    a = []
    for i in s:
        if i == '(':
            a.append(i)
        elif i == ')':
            if len(a) <=0 :
                return False
            top = a.pop()
            if top != '(':
                return False
        
    if len(a) != 0:
        return False
    
    return True
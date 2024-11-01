import bisect 

def check(stones, k,target):
    
    cnt = 0
    for stone in stones:
        if stone - target < 0:
            cnt +=1
        else:
            cnt = 0
        if cnt >= k:
            return False
    return True
            
def solution(stones, k):
    st, ed =  1,  max(stones)
    answer = max(stones)
    while st <= ed:
        mid = (st+ed) //2
        # print(st, ed , mid)
        if check(stones, k, mid):
            # print("성공")
            st = mid + 1
            answer = mid
        else :
            # print("실패")
            ed = mid -1
    return answer
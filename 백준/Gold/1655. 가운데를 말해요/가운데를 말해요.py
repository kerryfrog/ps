import heapq
import sys
N = int(input())

num =[]

min_heap =[]
max_heap =[]
mid = int(input())
print(mid)

#  max_heap mid min_heap
for i in range(2,N +1):
    now = int(sys.stdin.readline())
    #  max_heap에 넣기
    if now <= mid:
        if i % 2 == 0:
            heapq.heappush(max_heap, (-1*now, now))
            tmp , val = heapq.heappop(max_heap)
            heapq.heappush(min_heap, mid)
            mid = val
        # 홀수개 일 때는 그냥 max heap에 넣으면 됨
        elif i % 2 == 1:
            heapq.heappush(max_heap, (-now, now))
    # min_heap에 넣기
    elif now > mid:
        #  전체 수의 개수가 홀수개 일 때 오른쪽이면 조정 필요
        if i % 2 == 1:
            heapq.heappush(min_heap, now)
            val = heapq.heappop(min_heap)
            heapq.heappush(max_heap, (-1*mid, mid))
            mid = val
        elif i % 2 == 0:
            heapq.heappush(min_heap,now)
    print(mid)
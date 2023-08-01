from heapq import heappush, heappop

T = int(input())

for tc in range(T):
    N, A = map(int, input().split(" "))
    tmp = list(map(int, input().split(" ")))
    tmp.append(A)
    tmp.sort()

    middle = tmp[1]
    minheap = [(tmp[2], tmp[2])]
    maxheap = [(-tmp[0], tmp[0])]
    ans = tmp[1] % 20171109
    for i in range(N - 1):
        new_numbers = list(map(int, input().split(" ")))
        for num in new_numbers:
            if num < middle:
                heappush(maxheap, (-num, num))
            else:
                heappush(minheap, (num, num))

        if len(minheap) > len(maxheap):
            trash, new_middle = heappop(minheap)
            heappush(maxheap, (-middle, middle))
            middle = new_middle
        elif len(minheap) < len(maxheap):
            trash, new_middle = heappop(maxheap)
            heappush(minheap, (middle, middle))
            middle = new_middle
        ans = (ans + (middle % 20171109)) % 20171109
    print(f"#{tc + 1} {ans}")

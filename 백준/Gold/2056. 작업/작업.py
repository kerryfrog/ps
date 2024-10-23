N = int(input())

work_time =[0]
work_degree = [0]
link = [[]]
graph = [ [] for _ in range(N+1)]

for i in range(N):
    tmp = list(map(int, input().split(" ")))
    work_time.append(tmp[0])
    work_degree.append(tmp[1])
    if tmp[1] > 0:
        link.append(tmp[2:])
        for j in range(tmp[1]):
            graph[tmp[2+j]].append(i+1)

    else :
        link.append([])
st = []
dp = [0 for _ in range(N+1)]
for i in range(1, N+1):
    if work_degree[i] == 0:
        st.append([i, 0])
        dp[i] = work_time[i]


visited = [0 for _ in range(N+1)]
while st:
    idx ,time =st.pop()
    if visited[idx] == 1:
        continue
    visited[idx] = 1

    if len(link[idx]) >0:
        st_time = 0
        for i in link[idx]:
            st_time = max( dp[i], st_time)
        dp[idx] = st_time + work_time[idx]

    for i in graph[idx]:
        work_degree[i] -= 1
        if work_degree[i] == 0:
            st.append([i , time+work_time[idx]])


print(max(dp))
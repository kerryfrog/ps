n = int(input())
paper=[]
for i in range(n):
    a,b =map(int,input().split(" "))
    paper.append([a,b])


paper.sort(key=lambda x:(x[0], x[1]))
print(paper)

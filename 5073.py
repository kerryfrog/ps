

while 1:
  a, b , c = map(int, input().split(" "))
  
  if a==0 and b ==0 and c==0 :
    break
  # print(max([a,b,c]))
  if max([a,b,c] ) *2 >= a+b+c :
    print("Invalid")
    continue 
  if(a==b and b==c and a==c):
    print("Equilateral")
  elif(a !=b and a!=c and c!=b):
    print("Scalene")
  else:
    print("Isosceles")

  
  
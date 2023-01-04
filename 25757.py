game_dict = {'Y':2 ,'F':3, 'O':4 }
people = []

N , game = input().split(" ")

for i in range(int(N)):
  people.append(input())

people = list(set(people))

print(  len(people) // (game_dict[game] -1 ) )

gather = ['a', 'e', 'i', 'o', 'u']

while True:
  isGather  = False
  flag = True
  target_txt = input()
  if target_txt == "end":
    break
  for i in range(len(target_txt)):
    if target_txt[i]  in  gather:
      isGather = True
    if i>= 2:
      if target_txt[i] in gather and target_txt[i-1] in gather and target_txt[i-2] in gather:
        flag = False
        break
      elif target_txt[i] not in gather and target_txt[i-1] not in gather and target_txt[i-2] not in gather:
        flag = False
        break
    if i >= 1 and target_txt[i] == target_txt[i-1] :
      if target_txt[i] == 'e' or target_txt[i] =='o':
        continue
      else :
        flag = False
        break
  if isGather and flag == True:
    print("<", target_txt, "> is acceptable.")
  else:
    print("<"+ target_txt+ "> is not acceptable." )
    



  

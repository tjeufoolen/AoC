import functools

print(functools.reduce(lambda prev, curr: prev+1 if curr == "(" else prev-1, [x for x in open("../input.txt").readline()], 0))
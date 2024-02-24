floor = 0

with open("../input.txt", "r") as file:
    for idx, c in enumerate(file.readline()):
        floor = floor + 1 if c == "(" else floor - 1
        if floor == -1:
            print(idx + 1)
            break
            
file = open("../input.txt", "r")
lines = file.read().splitlines()

h_pos, depth, aim = 0, 0, 0

for line in lines:
    cmd, value = line.split()
    value = int(value)

    if cmd == "forward":
        h_pos += value
        depth += aim * value
    elif cmd == "down":
        aim += value
    elif cmd == "up":
        aim -= value

answer = h_pos * depth

print(answer)

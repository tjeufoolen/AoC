file = open("../input.txt", "r")
lines = file.read().splitlines()

h_pos, depth = 0, 0

for line in lines:
    cmd, value = line.split()
    value = int(value)

    if cmd == "forward":
        h_pos += value
    elif cmd == "down":
        depth += value
    elif cmd == "up":
        depth -= value

answer = h_pos * depth

print(answer)

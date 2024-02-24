using GiantSquid_Part1;

BingoSubsystem subsystem = new("../../../../../input.txt");
Bingo bingo = new(subsystem.DrawableNumbers, subsystem.BingoBoards);

while (!bingo.IsEnded)
{
    bingo.MarkBoards(bingo.Draw());
    bingo.UpdateWinners();
}

var board = bingo.Winners.Last().Value;
int lastMarkedNumber = board.LastMarkedNumber;
int unmarkedSum = board.Sum(false);

Console.WriteLine($"The answer is: {unmarkedSum * lastMarkedNumber}");
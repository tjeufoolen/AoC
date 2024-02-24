using GiantSquid_Part1;

BingoSubsystem subsystem = new BingoSubsystem("../../../../../input.txt");
Bingo bingo = new Bingo(subsystem.DrawableNumbers, subsystem.BingoBoards);

int drawnNumber = -1;

while (!bingo.HasWinner())
{
    drawnNumber = bingo.Draw();
    Console.WriteLine($"Drawn number: {drawnNumber}.");

    bingo.MarkBoards(drawnNumber);
}

Console.WriteLine("We have a winner!");

int unmarkedSum = 0;

bingo.Boards.ForEach(board =>
{
    if (board.HasBingo)
    {
        board.Items.ForEach(row =>
        {
            unmarkedSum += row.Where(item => !item.Marked).Sum(item => item.Value);
        });
        return;
    }
});

Console.WriteLine($"The answer is: {unmarkedSum * drawnNumber}");

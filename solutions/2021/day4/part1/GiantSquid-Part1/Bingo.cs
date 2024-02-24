using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GiantSquid_Part1
{
    public class Bingo
    {
        public List<int> DrawableNumbers { get; private set; }
        public int NextDrawableNumberIndex { get; private set; } = 0;

        public List<BingoBoard> Boards { get; private set; }

        public Bingo(List<int> drawableNumbers, List<BingoBoard> boards)
        {
            DrawableNumbers = drawableNumbers;
            Boards = boards;
        }

        public bool HasWinner()
        {
            return Boards.Any(board => board.HasBingo);
        }

        public void PrintAllBoards()
        {
            Boards.ForEach(board =>
            {
                Console.WriteLine();
                board.PrintItems();
            });
        }

        public int Draw()
        {
            return DrawableNumbers[NextDrawableNumberIndex++];
        }

        public void MarkBoards(int drawnNumber)
        {
            Boards.ForEach(board => board.MarkItems(drawnNumber));
        }
    }
}

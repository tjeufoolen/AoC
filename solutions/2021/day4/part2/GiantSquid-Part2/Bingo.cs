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
        private int DrawableNumberIndex { get; set; } = 0;
        public List<BingoBoard> Boards { get; private set; }
        public SortedList<int, BingoBoard> Winners { get; private set; } = new();
        public bool IsEnded => DrawableNumberIndex >= DrawableNumbers.Count;

        public Bingo(List<int> drawableNumbers, List<BingoBoard> boards)
        {
            DrawableNumbers = drawableNumbers;
            Boards = boards;
        }

        public int Draw() => DrawableNumbers[DrawableNumberIndex++];

        public void MarkBoards(int drawnNumber) => Boards.ForEach(board => board.MarkItems(drawnNumber));

        public void UpdateWinners()
        {
            List<BingoBoard> boards = Boards.Where(board => board.HasBingo).ToList();
          
            boards.ForEach(board =>
            {
                Winners.Add(Winners.Count + 1, board);
                Boards.Remove(board);
            });
        }

        public void PrintAllBoards()
        {
            Boards.ForEach(board =>
            {
                Console.WriteLine();
                board.PrintItems();
            });
        }
    }
}

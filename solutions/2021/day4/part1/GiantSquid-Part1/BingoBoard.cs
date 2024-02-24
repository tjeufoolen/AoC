using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GiantSquid_Part1
{
    public class BingoBoard
    {
        public List<List<BingoBoardItem>> Items { get; private set; }
        public bool HasBingo { get; private set; }

        public BingoBoard(string[] input)
        {
            Items = ParseInput(input);
        }

        private List<List<BingoBoardItem>> ParseInput(string[] input)
        {
            List<List<BingoBoardItem>> result = new List<List<BingoBoardItem>>();

            for (int i = 0; i < input.Length; i++)
            {
                result.Add(new List<BingoBoardItem>());
                input[i]
                    .Split(" ")
                    .Where(x => !string.IsNullOrEmpty(x))
                    .ToList()
                    .ForEach(item => result[i].Add(new BingoBoardItem(int.Parse(item))));
            }

            return result;
        }

        public void MarkItems(int drawnNumber)
        {
            Items.ForEach((List<BingoBoardItem> column) =>
            {
                column.ForEach((BingoBoardItem item) =>
                {
                    if (item.Value == drawnNumber) item.Mark();
                });
            });

            CheckForBingo();
        }

        private void CheckForBingo()
        {
            CheckForBingoHorizontal();
            CheckForBingoVertical();
        }

        private void CheckForBingoHorizontal()
        {
            Items.ForEach((List<BingoBoardItem> row) =>
            {
                bool allAreMarked = true;

                row.ForEach((BingoBoardItem item) =>
                {
                    if (!item.Marked) allAreMarked = false;
                });
                
                if (allAreMarked)
                {
                    HasBingo = true;
                    return;
                }
            });
        }

        private void CheckForBingoVertical()
        {
            Items.ForEach((List<BingoBoardItem> row) =>
            {
                bool allAreMarked = true;
                int columnCount = row.Count;
                
                for (int i=0; i<columnCount; i++)
                {
                    if (!row[i].Marked) allAreMarked = false;
                }

                if (allAreMarked)
                {
                    HasBingo = true;
                    return;
                }
                
            });
        }

        public void PrintItems()
        {
            Items.ForEach((List<BingoBoardItem> row) =>
            {
                row.ForEach((BingoBoardItem item) =>
                {
                    Console.Write(" ");
                    item.PrintItem();
                });
                Console.WriteLine();
            });
        }
    }
}

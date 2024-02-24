using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GiantSquid_Part1
{
    public class BingoSubsystem
    {
        public List<int> DrawableNumbers { get; private set; } = new List<int>();
        public List<BingoBoard> BingoBoards { get; private set; } = new List<BingoBoard>();

        public BingoSubsystem(string inputFilePath)
        {
            string[] input = GetInput(inputFilePath);
            ParseInput(input);
        }

        private string[] GetInput(string filePath)
        {
            return File.ReadAllLines(filePath);
        }

        private void ParseInput(string[] input)
        {
            // Drawable numbers
            string[] drawableStr = input[0].Split(",");
            foreach (string drawable in drawableStr)
            {
                int number = int.Parse(drawable);
                DrawableNumbers.Add(number);
            }
                
            // Boards
            int bingoBoardRowCount = 5;
            int skip = 2;

            while(skip + bingoBoardRowCount <= input.Length)
            {
                BingoBoards.Add(new BingoBoard(input.Skip(skip).Take(bingoBoardRowCount).ToArray()));
                skip += bingoBoardRowCount + 1; // + empty line
            }
        }
    }
}

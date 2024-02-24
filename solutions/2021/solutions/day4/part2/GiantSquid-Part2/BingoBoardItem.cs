using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GiantSquid_Part1
{
    public class BingoBoardItem
    {
        private int _value;
        public int Value { 
            get => _value; 
            private set {
                _value = (value > 0) ? value : -1;
            }
        }
        public bool Marked { get; private set; } = false;

        public BingoBoardItem(int value)
        {
            Value = value;
        }

        public void Mark() => Marked = true;

        public void PrintItem()
        {
            if (Value < 10) Console.Write(" ");
            Console.Write(Value.ToString());
        }
    }
}

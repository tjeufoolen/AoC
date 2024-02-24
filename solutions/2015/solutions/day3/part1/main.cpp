#include <iostream>
#include <fstream>
#include <vector>
#include <memory>

std::vector<std::string> getInput(std::string fileName) {
    // Initialize variables
    std::vector<std::string> lines;

    // Read input from file
    std::ifstream file (fileName);
    if (file.is_open()) {
        // Read line by line
        std::string line;
        while (getline(file, line)) {
            lines.push_back(line);
        }
        // Close file
        file.close();
    } else {
        std::cout << "Unable to open file" << "\n";
    }

    return lines;
}

struct House
{
    int presents = 0;

    bool visited = false;

    std::unique_ptr<House> north;
    std::unique_ptr<House> east;
    std::unique_ptr<House> south;
    std::unique_ptr<House> west;
};

int calculateAmountOfHouses(std::unique_ptr<House>& house, int houses=0) {
    if (!house->visited) {
        house->visited = true;
        
        // Visit north 
        if (house->north != nullptr) {
            houses += calculateAmountOfHouses(house->north, houses);
        }
        // Visit east
        if (house->east != nullptr) {
            houses += calculateAmountOfHouses(house->east, houses);
        }
        // Visit south
        if (house->south != nullptr) {
            houses += calculateAmountOfHouses(house->south, houses);
        }
        // Visit west
        if (house->west != nullptr) {
            houses += calculateAmountOfHouses(house->west, houses);
        }
    }
    return houses + 1;
}

int main() {
    // Initialize variables
    std::vector<std::string> lines = getInput("../input.txt");
    std::unique_ptr<House> startingHouse = std::make_unique<House>();
    House* currentHouse = startingHouse.get(); 

    // Read first line
    for (char& c : lines[0]) {
        switch (c)
        {
            case '^':
                {
                    if (currentHouse->north == nullptr) {
                        currentHouse->north = std::make_unique<House>();
                    }
                    currentHouse = currentHouse->north.get();
                }
                break;
            case '>':
                {
                    if (currentHouse->east == nullptr) {
                        currentHouse->east = std::make_unique<House>();
                    }
                    currentHouse = currentHouse->east.get();
                }
                break;
            case 'v':
                {
                    if (currentHouse->south == nullptr) {
                        currentHouse->south = std::make_unique<House>();
                    }
                    currentHouse = currentHouse->south.get();
                }
                break;
            case '<':
                {
                    if (currentHouse->west == nullptr) {
                        currentHouse->west = std::make_unique<House>();
                    }
                    currentHouse = currentHouse->west.get();
                }
                break;   
        }
    }

    // Show amount of houses visited
    std::cout << "Amount of houses visited: " << calculateAmountOfHouses(startingHouse) << "\n";

    return 0;
}
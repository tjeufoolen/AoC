package main

import (
    "bufio"
    "fmt"
    "log"
    "os"
	"strings"
	"strconv"
)

func main() {
	// Initialize variables
	totalFeetPaper := 0

	// Read file
    file, err := os.Open("../input.txt")
    if err != nil {
        log.Fatal(err)
    }
    defer file.Close()
    scanner := bufio.NewScanner(file)
    
	// Loop through file line by line
	for scanner.Scan() {
		// Convert line to l, w, h variables
		s := strings.Split(scanner.Text(), "x")
		
		l, lErr := strconv.Atoi(s[0])
		w, wErr := strconv.Atoi(s[1])
		h, hErr := strconv.Atoi(s[2])

		if lErr != nil || wErr != nil || hErr != nil {
			log.Fatal("%s not parseable to int", s[2])
		}

		// Save surface areas
		surfaces := [3]int {l*w, w*h, h*l}
		
		// Get smallest surface
		smallest := 0
		for _, s := range surfaces {
			if smallest == 0 || s < smallest {
				smallest = s
			}
		}

		// Calculate paper needed
		paper := 2*surfaces[0] + 2*surfaces[1] + 2*surfaces[2] + smallest

		// Add to total needed
        // fmt.Printf("%dx%dx%d = %df paper needed\n", l, w, h, paper)
		totalFeetPaper += paper
    }

	fmt.Printf("totalFeetPaper: %d", totalFeetPaper)

    if err := scanner.Err(); err != nil {
        log.Fatal(err)
    }
}
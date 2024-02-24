package main

import (
    "bufio"
    "fmt"
    "log"
    "os"
	"strings"
	"strconv"
	"sort"
)

func main() {
	// Initialize variables
	totalFeetOfRibbon := 0

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

		// Calculate ribbon for wrapping present using shortest distance around
		arr := [3]int{l, w, h}
		sort.Ints(arr[:])
		present := arr[0]+arr[0]+arr[1]+arr[1]

		// Calculate ribbon for creating bow
		bow := l*w*h

		// Add to total needed
		total := present + bow
        fmt.Printf("%dx%dx%d = %df ribbon needed\n", l, w, h, total)
		totalFeetOfRibbon += total
    }

	fmt.Printf("totalFeetOfRibbon: %d\n", totalFeetOfRibbon)

    if err := scanner.Err(); err != nil {
        log.Fatal(err)
    }
}
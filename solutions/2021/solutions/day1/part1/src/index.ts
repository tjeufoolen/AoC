import { readFile } from "fs";

const getLinesFromFile = (path: string): Promise<string[]> => {
    return new Promise((resolve, reject) => {
        readFile(path, "utf8", function(err, data) {
            if(err) reject(err);
            const lines: string[] = data.toString().split("\n");
            resolve(lines);
        });
    })
}

const measurements = async (): Promise<number[]> => {
    const lines: string[] = await getLinesFromFile("../input.txt");
    return lines.map((line: string) => parseInt(line)).filter((x: any) => !isNaN(x));
}

const increases = (measurements: number[]): number => {
    let increases: number = 0;
    let prev: number | null = null;

    measurements.forEach((value: number) => {
        if (prev !== null && value > prev) increases++;
        prev = value;
    });

    return increases;
}

const main = async (): Promise<number> => increases(await measurements());

main().then((increases: number) => {
    console.log(`The measurement increased ${increases} times.`)
});
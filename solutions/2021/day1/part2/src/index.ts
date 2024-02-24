import { readFile } from 'fs';

const measurements = (): Promise<number[]> => new Promise((resolve, reject) => {
    readFile("../input.txt", "utf8", (err, data) => {
        if(err) reject(err);
        const lines: string[] = data.toString().split("\n");
        const numbers: number[] = lines.map((line: string) => parseInt(line)).filter((x: any) => !isNaN(x));
        resolve(numbers);
    });
})

const window = (arr: number[], size: number, idx: number): number[] => {
    let window: number[] = [];
    for (let i: number = 0; i<size; i++) window.push(arr[idx + i]);
    return window;
};

const sum = (arr: number[]): number => arr.reduce((sum: number, x: number) => sum + x);

const increases = (measurements: number[]): number => {
    const WINDOW_SIZE: number = 3;
    let increases: number = 0;

    for (let i: number = 0; i<measurements.length; i++) {
        if (i+WINDOW_SIZE === measurements.length) break;

        const a: number = sum(window(measurements, WINDOW_SIZE, i));
        const b: number = sum(window(measurements, WINDOW_SIZE, i+1));

        if (b > a) increases++;
    }

    return increases;
}

const main = async (): Promise<number> => increases(await measurements());

main().then((increases: number) => {
    console.log(`The measurement increased ${increases} times.`)
});
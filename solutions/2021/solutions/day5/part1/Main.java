import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> lines = new FileReader().getInput("test-input.txt");

        VentGrid vg = new VentGrid(lines);
        System.out.println(vg.toString());
        vg.print();

        int answer = 0;
        for (int y=0; y<vg.grid().size(); y++) {
            for (int x=0; x<vg.grid().get(y).size(); x++) {
                int value = vg.grid().get(y).get(x);
                if (value >= 2) answer += 1;
            }
        }

        System.out.println("Answer: " + answer);
    }
}
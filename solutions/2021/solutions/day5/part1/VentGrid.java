import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VentGrid {
    private List<VentLine> lines;

    public VentGrid(List<String> lines) {
        this.lines = new ArrayList<>();
        this.lines = lines
                .stream()
                .map(VentLine::new)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "VentGrid{" +
                "lines=[" +
                    lines.stream().map(VentLine::toString).toList().toString() +
                " ]" +
                '}';
    }

    public void print() {
        var grid = this.grid();
        for (int y=0; y<grid.size(); y++) {
            System.out.println();
            for (int x=0; x<grid.get(y).size(); x++) {
                System.out.print(grid.get(y).get(x));
            }
        }
    }

    public ArrayList<ArrayList<Integer>> grid() {
        ArrayList<ArrayList<Integer>> grid = new ArrayList<>();

        this.lines.forEach(vl -> {
            // Create arraylist if not enough for y1, y2
            for (int y=vl.y1(); y<vl.y2(); y++) {
                for (int x=vl.x1(); x<vl.x2(); x++) {
                    grid.get(y).set(x, grid.get(y).get(x));
                }
            }
        });

        return grid;
    }
}

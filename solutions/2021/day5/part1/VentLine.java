import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentLine {
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public VentLine(String line) {
        Matcher matcher = Pattern
                .compile("(\\d{1,3}),(\\d{1,3}) -> (\\d{1,3}),(\\d{1,3})")
                .matcher(line);

        if (matcher.find()) {
            this.x1 = Integer.parseInt(matcher.group(1));
            this.y1 = Integer.parseInt(matcher.group(2));
            this.x2 = Integer.parseInt(matcher.group(3));
            this.y2 = Integer.parseInt(matcher.group(4));
        }
    }

    public int x1() {
        return this.x1;
    }

    public int y1() {
        return this.y1;
    }

    public int x2() {
        return this.x2;
    }

    public int y2() {
        return this.y2;
    }

    @Override
    public String toString() {
        return "VentLine{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                '}';
    }
}

package mapgame.domain;

import java.io.Serializable;

public class Saved implements Serializable {
    public String path;
    public int step;
    public int[][] data;
    public int x;
    public int y;

    public Saved(String path, int step, int[][] data, int x, int y) {
        this.path = path;
        this.step = step;
        this.data = data;
        this.x = x;
        this.y = y;
    }
}

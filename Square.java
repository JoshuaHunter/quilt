import java.awt.*;
import javax.swing.*;

public class Square implements Cloneable {

	private double scale;
	private final int BASE_SIZE = 100;
	private int red;
	private int green;
	private int blue;
	private Color colour;
	private int depth;
    private int pos[] = new int[2];
    private boolean pos_set = false;

	public Square(double s, int r, int g, int b, int d) {
		this.scale = s;
		this.red = r;
		this.green = g;
		this.blue = b;
		this.colour = new Color(this.red, this.green, this.blue);
		this.depth = d;
	}

    public Square(Square s) {
        this.scale = s.getScale();
        this.colour = s.getColour();
        this.depth = s.getDepth();
    }

    public void refactorScale(double d) {
        this.scale = this.scale/d;
    }

    public void setPos(int x, int y) {
       pos[0] = x;
       pos[1] = y;
       pos_set = true;
    }

    public int[] getPos() {
        return pos;
    }

    public int getDepth() {
        return depth;
    }

	public int getBaseSize() {
		return BASE_SIZE;
	}

	public int getSize() {
		int result = (int)(BASE_SIZE*scale);
		return result;
	}

	public double getScale() {
		return this.scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public Color getColour() {
		return this.colour;
	}

	public void paintComponent(Graphics g, int x, int y) {
		int dmsn = this.getSize();
		g.setColor(this.colour);
		g.fillRect(x, y, dmsn, dmsn);
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Scale: " + this.scale + "\tDepth: " +
            this.depth + "\tR: " + this.red + "\tG: " + this.green +
            "\tB: " + this.blue);
        if (pos_set) {
            result.append("\tx: " + this.pos[0] + "\ty: " + this.pos[1]);
        }
		return result.toString();
	}

}

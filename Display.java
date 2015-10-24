import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Display extends JPanel {

	private ArrayList<Square> s;
    private ArrayList<Square> drawable_squares = new ArrayList<Square>();
	private int depth = 0;

	public Display(ArrayList<Square> s) {
		this.s = s;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int[] center = {this.getWidth()/2, this.getHeight()/2};
		addSquares(g, 0, center[0], center[1]);
        drawSquares(g);
	}
              
	public void addSquares(Graphics g, int depth, int x, int y) {
        if (depth >= s.size()) {
            return;
        }
        int i;
        int[] newCenter = {x, y};
        int[] offsets = {1, 1, -1, 1, 1, -1, -1, -1};
        Square square = new Square(s.get(depth));
        int size = square.getSize();
        square.setPos(x, y);
        drawable_squares.add(square);
        for (i=0; i<4; i++) {
            newCenter[0] = x;
            newCenter[1] = y;
            newCenter[0] += offsets[i*2] * size/2;
            newCenter[1] += offsets[i*2+1] * size/2;
            addSquares(g, depth+1, newCenter[0], newCenter[1]);
        }       
	}

    public void drawSquares(Graphics g) {
        int i;
        Square curr;
        int size;
        Collections.sort(drawable_squares, new CompareDepth());
        for (i=0; i<drawable_squares.size(); i++) {
            curr = drawable_squares.get(i);
            size = curr.getSize();
            curr.paintComponent(g, curr.getPos()[0]-(size/2),
                    curr.getPos()[1]-(size/2));
        }

    }
    
    public class CompareDepth implements Comparator<Square> {
        @Override
        public int compare(Square o1, Square o2) {
            return (o1.getDepth()<o2.getDepth() ? -1 :
                    (o1.getDepth()==o2.getDepth() ? 0 : 1));
        }
    }
}
import java.util.*;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;

public class Quilt {

	private static ArrayList<Square> squares = new ArrayList<Square>();
	private static int wndw_size;
	private final static int BASE_SIZE = 100;
	private static int screenSize;
	private static double initial_scale;
    private static double max_scale = 0;
    private static boolean refactor = false;

	public static void main(String[] args) {
        int i;
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {
			squares.add(buildSquare(input.nextLine()));
		}
        if (refactor) {
            for (i=0; i<squares.size(); i++) {
                System.err.println("max_scale = " + max_scale);
                squares.get(i).refactorScale(max_scale);
            }
        }
		wndw_size = calcWndw();
		runGUI();
		input.close();
	}

	private static int calcWndw() {
		int result = 0;
		int i;
		for (i=0; i<squares.size(); i++) {
			result += squares.get(i).getSize();
		}
		//result += 100;
        System.err.println("wndw_size = " + result);
	    return result/result*250;
	}

	public static Square buildSquare(String s) {
		int i, j=0, red, green, blue;
		double scale;
		double size;
		int nf_temp;
		String curr;
		Square result;
		int prevIndex = 0;
		String[] squareAttrs = new String[4];
		for (i=0; i<s.length(); i++) {
			if (s.charAt(i) == ' ') {
				curr = s.substring(prevIndex, i);
				squareAttrs[j++] = curr;
				prevIndex = i+1;
			}
		}
		squareAttrs[j] = s.substring(prevIndex, s.length());
		scale = Double.parseDouble(squareAttrs[0]);
        if (scale > max_scale) {
            max_scale = scale;
            refactor = true;
        }
		red = Integer.parseInt(squareAttrs[1]);
		green = Integer.parseInt(squareAttrs[2]);
		blue = Integer.parseInt(squareAttrs[3]);
		size = scale*BASE_SIZE;
		result = new Square(scale, red, green, blue, squares.size());
		return result;
	}
	
	public static void listSquares() {
		int i;
		for (i=0; i<squares.size(); i++) {
			System.err.println(squares.get(i).toString());
		}
	}

	public static void runGUI() {
		JFrame f = new JFrame("A Patchwork Quilt");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Display d = new Display(squares);
		f.add(d);
		f.setSize(wndw_size, wndw_size);
		f.setVisible(true);
	}

}
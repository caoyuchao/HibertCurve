import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Point2D;

public class Hibert {
	static Map<String, Pair[][]> hilbertMap = new HashMap<>();
	private final int order;

	public Hibert(int order) {
		this.order = order;
		hilbertMap.put("a",
				new Pair[][] { { new Pair(0, "d"), new Pair(1, "a") }, { new Pair(3, "b"), new Pair(2, "a") } });
		hilbertMap.put("b",
				new Pair[][] { { new Pair(2, "b"), new Pair(1, "b") }, { new Pair(3, "a"), new Pair(0, "c") } });
		hilbertMap.put("c",
				new Pair[][] { { new Pair(2, "c"), new Pair(3, "d") }, { new Pair(1, "c"), new Pair(0, "b") } });
		hilbertMap.put("d",
				new Pair[][] { { new Pair(0, "a"), new Pair(3, "c") }, { new Pair(1, "d"), new Pair(2, "d") } });
	}

	private int xy2d(int x, int y, int order) {
		// 精妙的代码
		String currentSquare = "a";
		int position = 0;
		int quad_x = 0;
		int quad_y = 0;
		int quad_position = 0;
		for (int i = order - 1; i >= 0; i--) {
			position <<= 2;
			quad_x = (x & (1 << i)) > 0 ? 1 : 0;
			quad_y = (y & (1 << i)) > 0 ? 1 : 0;

			Pair pair = hilbertMap.get(currentSquare)[quad_x][quad_y];
			quad_position = pair.num;
			currentSquare = pair.square;
			position |= quad_position;
		}
		return position;
	}

	public ArrayList<HashPoint> getSequeue(double width, double height) {
		ArrayList<HashPoint> arrayList = new ArrayList<>();
		int rows = (int) Math.pow(2, order);

		double xStep = width / rows;
		double yStep = height / rows;

		xStep *= 1.5;
		yStep *= 1.5;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				arrayList.add(new HashPoint(i, j, new Point2D((int) (xStep * i), (int) (yStep * j))));
			}
		}
		Collections.sort(arrayList);
		return arrayList;
	}

	public class HashPoint implements Comparable<HashPoint> {
		private final Point2D point;
		private final int hilbertNum;

		public HashPoint(int i, int j, Point2D point) {
			this.point = point;
			hilbertNum = xy2d(i, j, order);
		}

		public Point2D getPoint() {
			return point;
		}

		@Override
		public int compareTo(HashPoint o) {
			// TODO Auto-generated method stub
			if (hilbertNum > o.hilbertNum) {
				return 1;
			} else if (hilbertNum < o.hilbertNum) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	class Pair {
		int num = 0;
		String square;

		public Pair(int num, String square) {
			// TODO Auto-generated constructor stub
			this.num = num;
			this.square = square;
		}
	}

}

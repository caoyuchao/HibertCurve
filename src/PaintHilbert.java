import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class PaintHilbert extends Application {
	private final double width = 512;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		Hibert hilbert = new Hibert(8);
		BorderPane borderPane = new BorderPane();
		ArrayList<Hibert.HashPoint> arrayList = hilbert.getSequeue(width, width);

		Polyline polyline = new Polyline();
		ObservableList<Double> points = polyline.getPoints();
		for (int i = 0; i < arrayList.size(); i++) {
			points.add(arrayList.get(i).getPoint().getX());
			points.add(arrayList.get(i).getPoint().getY());
		}
		borderPane.setCenter(polyline);
		Scene scene = new Scene(borderPane, width * 1.5, width * 1.5);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}

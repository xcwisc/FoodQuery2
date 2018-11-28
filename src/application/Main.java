package application;
	
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Label label = new Label("Meal plan");
			ToolBar toolbar = new ToolBar(label);
			VBox foodItemsVbox = new VBox();
			HBox foodDetailHbox = new HBox();
			
			
			
			foodDetailHbox.getChildren().addAll(
					new Label("Item name           "),
					new Label("Brand               "),
					new Label("Calories   "),
					new Label("Fat(g)   "),
					new Label("Carbs(g)   "),
					new Label("Fiber(g)   "),
					new Label("Protein(g)"));
			
			HBox foodHbox = new HBox();
			foodHbox.getChildren().addAll(
					new Label("Balsamic Vinaigrette"),
					new Label("Meijer"),
					new Label("xx"),
					new Label("xx"),
					new Label("xx"),
					new Label("xx"),
					new Label("xx"));
			
			HBox foodHbox2 = new HBox();
			foodHbox2.getChildren().addAll(
					new Label("Strawberry Lowfat Yogurt"),
					new Label("Essential Everyday"),
					new Label("xx"),
					new Label("xx"),
					new Label("xx"),
					new Label("xx"),
					new Label("xx"));
			
			
			
//			ListView<String> foodDetail = new ListView<String>();
//			foodDetail.setOrientation(Orientation.HORIZONTAL);
//			foodDetail.
			ScrollPane foodItemSP = new ScrollPane();
			
//			toolbar.getItems().push();
			foodItemsVbox.getChildren().addAll(
					new Label("Available Food Items"),
					foodDetailHbox,
					foodItemSP);
		
			foodItemSP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			
			foodItemSP.setContent(new VBox(foodHbox, foodHbox2));

			root.setTop(toolbar);
			root.setCenter(foodItemsVbox);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

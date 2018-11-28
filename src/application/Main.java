package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
			/*ObservableList<String> names = FXCollections.observableArrayList("Item name",
					"Brand", "Calories", "Fat(g)", "Carbs(g)", "Fiber(g)","Protein(g)");
			ListView listname = new ListView(names);
			listname.setOrientation(Orientation.HORIZONTAL);
			*/

			
			
			GridPane foodGridPane = new GridPane();
			int counter = 0;
			gridPane.add(new Label("Red Wine Vinegar"), 0, 0, 1, 1);
			gridPane.add(new Label("Great Value"), 1, 0, 1, 1);
			gridPane.add(new Label(" xx        "), 2, 0, 1, 1);
			gridPane.add(new Label("xx    "), 3, 0, 1, 1);
			gridPane.add(new Label(" xx    "), 4, 0, 1, 1);
			gridPane.add(new Label("   xx    "), 5, 0, 1, 1);
			gridPane.add(new Label("     xx    "), 6, 0, 1, 1);
			
			gridPane.add(new Label("Shredded Mozzarella Cheese"), 0, 1, 1, 1);
			gridPane.add(new Label("Meijer  "), 1, 1, 1, 1);
			gridPane.add(new Label(" xx        "), 2, 1, 1, 1);
			gridPane.add(new Label("xx    "), 3, 1, 1, 1);
			gridPane.add(new Label(" xx    "), 4, 1, 1, 1);
			gridPane.add(new Label("   xx    "), 5, 1, 1, 1);
			gridPane.add(new Label("     xx    "), 6, 1, 1, 1);
			
			gridPane.add(new Label("Balsamic Vinaigrette"), 0, 2, 1, 1);
			gridPane.add(new Label("Meijer  "), 1, 2, 1, 1);
			gridPane.add(new Label(" xx        "), 2, 2, 1, 1);
			gridPane.add(new Label("xx    "), 3, 2, 1, 1);
			gridPane.add(new Label(" xx    "), 4, 2, 1, 1);
			gridPane.add(new Label("   xx    "), 5, 2, 1, 1);
			gridPane.add(new Label("     xx    "), 6, 2, 1, 1);
			
			gridPane.add(new Label("Strawberry LowFat Yogurt"), 0, 3, 1, 1);
			gridPane.add(new Label("Essential Everyday"), 1, 3, 1, 1);
			gridPane.add(new Label(" xx        "), 2, 3, 1, 1);
			gridPane.add(new Label("xx    "), 3, 3, 1, 1);
			gridPane.add(new Label(" xx    "), 4, 3, 1, 1);
			gridPane.add(new Label("   xx    "), 5, 3, 1, 1);
			gridPane.add(new Label("     xx    "), 6, 3, 1, 1);
			
			gridPane.add(new Label("Burrito Bean Cheese"), 0, 4, 1, 1);
			gridPane.add(new Label("Don Miguel"), 1, 4, 1, 1);
			gridPane.add(new Label(" xx        "), 2, 4, 1, 1);
			gridPane.add(new Label("xx    "), 3, 4, 1, 1);
			gridPane.add(new Label(" xx    "), 4, 4, 1, 1);
			gridPane.add(new Label("   xx    "), 5, 4, 1, 1);
			gridPane.add(new Label("     xx    "), 6, 4, 1, 1);
			
			gridPane.add(new Label("Fat Free Skim Milk"), 0, 5, 1, 1);
			gridPane.add(new Label("Kemps"), 1, 5, 1, 1);
			gridPane.add(new Label(" xx        "), 2, 5, 1, 1);
			gridPane.add(new Label("xx    "), 3, 5, 1, 1);
			gridPane.add(new Label(" xx    "), 4, 5, 1, 1);
			gridPane.add(new Label("   xx    "), 5, 5, 1, 1);
			gridPane.add(new Label("     xx    "), 6, 5, 1, 1);
			
			gridPane.add(new Label("Greek Yogurt Lemon"), 0, 6, 1, 1);
			gridPane.add(new Label("Yoplait"), 1, 6, 1, 1);
			gridPane.add(new Label(" xx        "), 2, 6, 1, 1);
			gridPane.add(new Label("xx    "), 3, 6, 1, 1);
			gridPane.add(new Label(" xx    "), 4, 6, 1, 1);
			gridPane.add(new Label("   xx    "), 5, 6, 1, 1);
			gridPane.add(new Label("     xx    "), 6, 6, 1, 1);
			
			gridPane.setHgap(10);
			
			
			foodDetailHbox.getChildren().addAll(
					new Label("Item name                              "),
					new Label(" Brand                        "),
					new Label("Calories  "),
					new Label("Fat(g) "),
					new Label("Carbs(g) "),
					new Label("Fiber(g) "),
					new Label("Protein(g) ");
			
	ObservableList<String> labelz = FXCollections.observableArrayList("Calories", "Fat(g)", "Carbs(g)", "Fiber(g)","Protein(g)");
			ObservableList<String> comparors = FXCollections.observableArrayList(">","<","=");
			ComboBox compar = new ComboBox(comparors);
			Label selLabel = new Label("Find items with ");
			TextField numSel = new TextField();
			numSel.setPromptText("type amount");
			ComboBox j = new ComboBox(labelz);
			HBox filterHbox = new HBox();
			filterHbox.getChildren().addAll(selLabel, j, compar,numSel);
				
			
//			ListView<String> foodDetail = new ListView<String>();
//			foodDetail.setOrientation(Orientation.HORIZONTAL);
//			foodDetail.
			ScrollPane foodItemSP = new ScrollPane();
			
//			toolbar.getItems().push();
			foodItemsVbox.getChildren().addAll(
					new Label("Available Food Items"),
					foodDetailHbox,
					foodItemSP, filterHbox);
		
			foodItemSP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			
			foodItemSP.setContent(foodGridPane);

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

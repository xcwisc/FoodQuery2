package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;



public class Main extends Application {
	
	private ObservableList<FoodItem> data =
	        FXCollections.observableArrayList(
        		new FoodItem("Ethan", "Williams", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Emma", "Jones", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Michael", "Brown", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Michael", "Brown", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Michael", "Brown", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Michael", "Brown", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Michael", "Brown", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Michael", "Brown", "xx", "xx", "xx", "xx", "xx")
	        );
	private ObservableList<String> labelz = FXCollections.observableArrayList("Calories", "Fat(g)", "Carbs(g)", "Fiber(g)","Protein(g)");
	
	private ObservableList<String> comparors = FXCollections.observableArrayList(">","<","=");
	
	private ObservableList<String> items = FXCollections.observableArrayList(
			"A", "B", "C", "D");
		
		
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			// initialize all the wrappers
			ToolBarWrapper toolBarWrapper = new ToolBarWrapper("Meal List", "NTR", 28);
			CenterVboxWrapper centerVboxWrapper = new CenterVboxWrapper(data, labelz, comparors);
			MealInfoWrapper mealInfoWrapper = new MealInfoWrapper(items);


			root.setTop(toolBarWrapper.getComponent());
			root.setRight(mealInfoWrapper.getComponent());
			root.setCenter(centerVboxWrapper.getComponent());

			Scene scene = new Scene(root, 950, 580);
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

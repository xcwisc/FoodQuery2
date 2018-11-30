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
                new FoodItem("Formula Soy for Diarrhea Ready to Feed", "Similac", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Premium Dark Chocolate with Mint Cookie Crunch", "Stewarts", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Greek Yogurt Lemon", "Yoplait", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Blended Strawberry Lowfat Yogurt", "Essential Everyday", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Soft Cremes Butterscotch Caramel", "Lancaster", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Porridge Heirloom Rye Rosemary Walnut Raisin", "From the Fields", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Shredded Mozzarella Cheese", "Wooden Shoe Cheese Co", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Burrito Bean Cheese", "Don Miguel", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Red Wine Vinegar", "GreatValue", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Sweet Strawberry Smoothie", "Profile", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Fat Free Skim Milk", "Kemps", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Balsamic Vinaigrette", "Meijer", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Benevento Croccantino Allo Strega Chocolatey Covered Hazelnuts", "Giuseppe Alberti Grocery", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Brown Rice 14Oz Pasta", "Tinkyada", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("Chicken Sausage Burgers Hot Italian Style", "Al Fresco", "xx", "xx", "xx", "xx", "xx"),
                new FoodItem("European Baguette", "Ecce Panis", "xx", "xx", "xx", "xx", "xx")
            );

	private ObservableList<String> labelz = FXCollections.observableArrayList("Calories", "Fat(g)", "Carbs(g)", "Fiber(g)","Protein(g)");
	
	private ObservableList<String> comparors = FXCollections.observableArrayList(">","<","=", ">=", "=<");
	
	private ObservableList<String> items = FXCollections.observableArrayList(
			"A", "B", "C", "D");
		
		
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			// initialize all the wrappers
			ToolBarWrapper toolBarWrapper = new ToolBarWrapper("MealPlan", primaryStage);
			CenterVboxWrapper centerVboxWrapper = new CenterVboxWrapper(data, labelz, comparors, primaryStage);
			MealInfoWrapper mealInfoWrapper = new MealInfoWrapper(items);


			root.setTop(toolBarWrapper.getComponent());
			root.setRight(mealInfoWrapper.getComponent());
			root.setCenter(centerVboxWrapper.getComponent());

			Scene scene = new Scene(root, 1000, 500);
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

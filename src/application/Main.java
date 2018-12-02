/*
 * Group Name: A-Team 75
 * Project: FoodQuery
 * 
 * Members: Bennett Majerowski, Micah Jona, Arjun Sachar, Alex Fanner, Chang Xu
 */

package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;


/**
 * Main Class is the driver of our MealPlan program.
 * @author A-Team 75
 *
 */
public class Main extends Application {
	
	private ObservableList<FoodItem> data =
            FXCollections.observableArrayList(
                new FoodItem("556540ff5d613c9d5f5935a9", "Formula Soy for Diarrhea Ready to Feed", "Similac", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Premium Dark Chocolate with Mint Cookie Crunch", "Stewarts", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Greek Yogurt Lemon", "Yoplait", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Blended Strawberry Lowfat Yogurt", "Essential Everyday", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Soft Cremes Butterscotch Caramel", "Lancaster", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Porridge Heirloom Rye Rosemary Walnut Raisin", "From the Fields", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Shredded Mozzarella Cheese", "Wooden Shoe Cheese Co", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Burrito Bean Cheese", "Don Miguel", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Red Wine Vinegar", "GreatValue", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Sweet Strawberry Smoothie", "Profile", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Fat Free Skim Milk", "Kemps", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Balsamic Vinaigrette", "Meijer", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Benevento Croccantino Allo Strega Chocolatey Covered Hazelnuts", "Giuseppe Alberti Grocery", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Brown Rice 14Oz Pasta", "Tinkyada", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "Chicken Sausage Burgers Hot Italian Style", "Al Fresco", "1", "1", "1", "1", "1"),
                new FoodItem("556540ff5d613c9d5f5935a9", "European Baguette", "Ecce Panis", "1", "1", "1", "1", "1")
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

			// puts elements to the border panes
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

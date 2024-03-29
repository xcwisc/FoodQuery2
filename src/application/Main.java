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
	
	private ObservableList<String> labelz = FXCollections.observableArrayList("Calories", "Fat(g)", "Carbs(g)", "Fiber(g)","Protein(g)");
	private ObservableList<String> comparors = FXCollections.observableArrayList("==", ">=", "<=");
	
	/**
	 * Initializes the GUI and displays it on the monitor.
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			FoodDataADT<FoodItem> foodData = new FoodData();
			
			// Pane that covers entire GUI window
			BorderPane root = new BorderPane();
			
			// initialize all the wrappers
			MealInfoWrapper mealInfoWrapper = new MealInfoWrapper();
			CenterVboxWrapper centerVboxWrapper = new CenterVboxWrapper(foodData, labelz, comparors, primaryStage, mealInfoWrapper);
			ToolBarWrapper toolBarWrapper = new ToolBarWrapper(centerVboxWrapper, foodData, "MealPlan", primaryStage);			

			// puts elements to the border panes
			root.setTop(toolBarWrapper.getComponent());
			root.setRight(mealInfoWrapper.getComponent());
			root.setCenter(centerVboxWrapper.getComponent());

			// Initializes the scene in our GUI
			Scene scene = new Scene(root, 1150, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launches the program
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

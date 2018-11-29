package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;


public class Main extends Application {
	
	private ObservableList<FoodItem> data =
	        FXCollections.observableArrayList(
	            new FoodItem("Red Wine Vinegar", "Great Value"),
	            new FoodItem("Shredded Mozzarella Cheese", "Meijer"),
	            new FoodItem("Ethan", "Williams"),
	            new FoodItem("Emma", "Jones"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown"),
	            new FoodItem("Michael", "Brown")
	        );
	private ObservableList<String> labelz = FXCollections.observableArrayList("Calories", "Fat(g)", "Carbs(g)", "Fiber(g)","Protein(g)");
	
	private ObservableList<String> comparors = FXCollections.observableArrayList(">","<","=");
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			// top toolbar wrapper
			ToolBarWrapper toolBarWrapper = new ToolBarWrapper("Meal List");
			// 
			TableViewWrapper tabelViewWrapper = new TableViewWrapper(data);
			QueryBarWrapper queryBarWrapper = new QueryBarWrapper(comparors, labelz);
			VBox foodItemsVbox = new VBox();
//			ScrollPane foodItemSP = new ScrollPane();
			
			// The following stuff goes along the bottom of the window
			// It does things like altering the data in the foot item library
			Button addFoodItem = new Button("Add Food Item to List");
			Button loadFoodList = new Button("Load New Food List");
			Button saveFoodList = new Button("Save Food List to File");
			
			// These buttons do things ... need to add action later
			HBox buttons = new HBox();
			buttons.getChildren().addAll(addFoodItem, loadFoodList, saveFoodList);
			foodItemsVbox.getChildren().addAll(
					new Label("Available Food Items"),
					tabelViewWrapper.getComponent(),
					queryBarWrapper.getComponent(),
					new HBox(addFoodItem, loadFoodList, saveFoodList)
					);
		
//			foodItemSP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			
//			foodItemSP.setContent(tabelViewWrapper.getComponent());
			
			// The following is for a side bar that shows what items the user has selected
			// as part of their meal and tracks the nutrition numbers (Total number of 
			// calories in meal, etc.)
			Label yourMealLabel = new Label("Your Meal"); 
			Label clickToRemoveLabel = new Label("Double click item to remove");
			VBox mealInfo = new VBox();
			
			// Vertical list of the names of food items that have been added to the meal.
			ListView<String> list = new ListView<String>();
			ObservableList<String> items = FXCollections.observableArrayList(
				"A", "B", "C", "D");
			list.setItems(items);
			
			// Horizontal line
			Line line = new Line();
			line.setStartX(0.0f);
			line.setStartY(0.0f);
			line.setEndX(300.0f);
			line.setEndY(0.0f);
			line.setStrokeWidth(2);
			
			// Allows meal list to be scroll-able
			ScrollPane s1 = new ScrollPane();
			s1.setHbarPolicy(ScrollBarPolicy.NEVER);
			s1.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			s1.setContent(list);
			
			// Trackers of nutrition counts in meal
			Label totalCaloriesLabel = new Label("Total Calories: ");
			Label totalFatLabel = new Label("Total Fat(g): ");
			Label totalCarbsLabel = new Label("Total Carbs(g): ");
			Label totalFiberLabel = new Label("Total Fiber(g): ");
			Label totalProteinLabel = new Label("Total Protein(g): ");
			
			Label totalCalories = new Label("xx");
			Label totalFat = new Label("xx");
			Label totalCarbs = new Label("xx");
			Label totalFiber = new Label("xx");
			Label totalProtein = new Label("xx");
			
			HBox calorieCounter = new HBox();
			HBox fatCounter = new HBox();
			HBox carbCounter = new HBox();
			HBox fiberCounter = new HBox();
			HBox proteinCounter = new HBox();
			
			calorieCounter.getChildren().add(totalCaloriesLabel);
			calorieCounter.getChildren().add(totalCalories);
			fatCounter.getChildren().add(totalFatLabel);
			fatCounter.getChildren().add(totalFat);
			carbCounter.getChildren().add(totalCarbsLabel);
			carbCounter.getChildren().add(totalCarbs);
			fiberCounter.getChildren().add(totalFiberLabel);
			fiberCounter.getChildren().add(totalFiber);
			proteinCounter.getChildren().add(totalProteinLabel);
			proteinCounter.getChildren().add(totalProtein);
			
			Button saveAndExit = new Button("Save Meal and Exit");
		
			mealInfo.getChildren().addAll(
					yourMealLabel, clickToRemoveLabel, line,
					s1, calorieCounter, fatCounter, carbCounter, 
					fiberCounter, proteinCounter, saveAndExit);


			
			
			// Input fields to add new food items to library. User must give a value
			// for all attributes (name, brand, calories, carbs, etc.) for item to be added.
			// Can this be simplified?
			TextField addItemName = new TextField();
			addItemName.setPromptText("item name");
			TextField addBrand = new TextField();
			addBrand.setPromptText("brand");
			TextField addCalories = new TextField();
			addCalories.setPromptText("calories");
			TextField addFat = new TextField();
			addFat.setPromptText("fat(g)");
			TextField addCarbs = new TextField();
			addCarbs.setPromptText("carbs(g)");
			TextField addFiber = new TextField();
			addFiber.setPromptText("fiber(g)");
			TextField addProtein = new TextField();
			addProtein.setPromptText("protein(g)");
			
			// Box to hold all text fields
			HBox textFields = new HBox();
			textFields.getChildren().addAll(
					addItemName, addBrand, addCalories, 
					addFat, addCarbs, addFiber, addProtein);

			
			
			HBox filterHbox = new HBox();
			
			// Box to hold all of the above buttons and fields and stuff
			VBox alterFoodList = new VBox();
			alterFoodList.getChildren().add(textFields);
			alterFoodList.getChildren().add(buttons);
			alterFoodList.getChildren().add(filterHbox);

			root.setTop(toolBarWrapper.getComponent());
			root.setRight(mealInfo);
			root.setCenter(foodItemsVbox);
//			root.setBottom(alterFoodList);
			Scene scene = new Scene(root, 1024, 640);
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

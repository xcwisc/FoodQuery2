package application;

import java.io.File;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CenterVboxWrapper {
	
	private VBox foodItemsVbox;
	
	public CenterVboxWrapper(ObservableList<FoodItem> data, ObservableList<String> labelz, ObservableList<String> comparors, Stage primaryStage) {
		foodItemsVbox = new VBox();
		TableViewWrapper tabelViewWrapper = new TableViewWrapper(data);
		QueryBarWrapper queryBarWrapper = new QueryBarWrapper(comparors, labelz);
		
		// The following stuff goes along the bottom of the window
		// It does things like altering the data in the foot item library
		Button addFoodItem = new Button("Add Food Item to List");
		Button loadFoodList = new Button("Load New Food List");
		Button saveFoodList = new Button("Save Food List to File");
		
		saveFoodList.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                File file = new File("foodList.txt");
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(primaryStage);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("Food List has been saved to a file."));
                Scene dialogScene = new Scene(dialogVbox, 300, 100);
                dialog.setScene(dialogScene);
                dialog.show();
            }
        });
		
		// These buttons do things ... need to add action later
		HBox buttons = new HBox();
		buttons.getChildren().addAll(addFoodItem, loadFoodList, saveFoodList);
		
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
		
		foodItemsVbox.getChildren().addAll(
				new Label("Available Food Items"),
				tabelViewWrapper.getComponent(),
				queryBarWrapper.getComponent(),
				textFields,
				new HBox(addFoodItem, loadFoodList, saveFoodList)	
				);	
	}
	
	public VBox getComponent() {
		return this.foodItemsVbox;
	}

}

package application;

import java.io.File;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Wrapper class for the data contained in center border pane of interface
 * @author A-Team 75
 *
 */
public class CenterVboxWrapper {

	private VBox foodItemsVbox; // VBox holds all components of the center panel of the GUI
	private FoodDataADT<FoodItem> foodData; // methods and fields associated with a food item
	TableViewWrapper tabelViewWrapper; // The table that holds the food item list

	/**
	 * Public constructor for the center panel data
	 * @param foodData
	 * @param labelz
	 * @param comparors
	 * @param primaryStage
	 * @param mealInfoWrapper
	 */
	public CenterVboxWrapper(FoodDataADT<FoodItem> foodData, ObservableList<String> labelz, 
			ObservableList<String> comparors, Stage primaryStage, MealInfoWrapper mealInfoWrapper) {
		this.foodData = foodData;
		foodItemsVbox = new VBox();
		foodItemsVbox.setId("center-vbox");
		this.tabelViewWrapper = new TableViewWrapper(foodData, mealInfoWrapper);
		QueryBarWrapper queryBarWrapper = new QueryBarWrapper(comparors, labelz, foodData, tabelViewWrapper);

		Label newLabel = new Label("Available Food Items");
		newLabel.setId("title");
		Label newerLabel = new Label("  (double click item to add to your meal)");
		newerLabel.setId("note-label");

		HBox newHbox = new HBox(newLabel, newerLabel);

		foodItemsVbox.getChildren().addAll(
				newHbox,
				tabelViewWrapper.getComponent(),
				queryBarWrapper.getComponent()
				//				new HBox(addFoodItem, loadFoodList, saveFoodList)	
				);	
	}

	/**
	 * Accessor method to components in the center panel
	 * @return
	 */
	public VBox getComponent() {
		return this.foodItemsVbox;
	}

	/**
	 * Accessor of the food item table
	 * @return
	 */
	public TableViewWrapper getTabelViewWrapper() {
		return this.tabelViewWrapper;
	}

}

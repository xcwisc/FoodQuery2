package application;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;

/**
 * Wrapper class for the data contained in the view rules pop up window
 * @author A-team 75
 *
 */
public class RulesPopUp {

	private ObservableList<String> rules; // list of all rules
	private TableViewWrapper tableViewWrapper; // table to hold components of the rules pop up
	private FoodDataADT<FoodItem> foodData; // methods and fields of food item

	/**
	 * Public constructor.
	 * @param rules
	 * @param tableViewWrapper
	 * @param foodData
	 */
	public RulesPopUp(ObservableList<String> rules, TableViewWrapper tableViewWrapper, 
			FoodDataADT<FoodItem> foodData) {
		this.rules = rules;
		this.tableViewWrapper = tableViewWrapper;
		this.foodData = foodData;
	}

	/**
	 * Method to display the food rule data in the pop up window of the GUI
	 */
	public void display() {
		
		ListView<String> rulez= new ListView<String>();
		
		// creates stage for our different rules
		Stage popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Rules List (Double click to delete rule)");

		rulez.setItems(rules);
		
		// if rule is double clicked then remove that specific rule
		rulez.setOnMouseClicked(event -> {
			if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 && !rules.isEmpty()) {
				// get the index of the removing target
				int index = rulez.getSelectionModel().getSelectedIndex();
				if (index != -1) {
					
					rules.remove(index);
					rulez.getSelectionModel().select(-1);
					tableViewWrapper.applyRules(foodData.filterByNutrients(rules));
					List<FoodItem> list = foodData.filterByNutrients(rules);

				}				
			} 
		});

		Scene ruleScene= new Scene(rulez, 400, 400);
		popup.setScene(ruleScene);

		popup.showAndWait();
	}
}

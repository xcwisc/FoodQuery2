package application;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Wrapper class for the food rules input field in the main window of
 * the interface.
 * @author A-Team 75
 *
 */
public class QueryBarWrapper {

	private ObservableList<String> comparors; // list of comparison symbols
	private ObservableList<String> labelz; // list of item attribute labels
	private ObservableList<String> rules; // list of added food rules
	private FoodDataADT<FoodItem> foodData; // methods and fields associated with food item type
    private String rule; // a single rule
	HBox filterHbox; // HBox to hold all the components of the query bar
	
	/**
	 * Public constructor of the rules input section of the main GUI window
	 * @param comparors 
	 * @param labelz 
	 * @param foodData 
	 * @param tableViewWrapper
	 */
	public QueryBarWrapper(ObservableList<String> comparors, ObservableList<String> labelz, FoodDataADT<FoodItem> foodData, TableViewWrapper tableViewWrapper) {
		this.comparors = comparors;
		this.labelz = labelz;
		rules = FXCollections.observableArrayList();
		this.foodData = foodData;
		
		// tracks values of rule specified by user
		RulesPopUp rulesPopUp = new RulesPopUp(rules, tableViewWrapper, foodData);
		ComboBox compar = new ComboBox(comparors);
		TextField numSel = new TextField();
		ComboBox j = new ComboBox(labelz);
		
		// labels for rule specifier method
		Label selLabel = new Label("Display food item with: ");
		selLabel.setId("filter-label");
		numSel.setPromptText("type amount");
		
		filterHbox = new HBox();
		Button addRule = new Button("Add Rule");
		Button viewRules = new Button("View Rules");
		
		// adds rule when clicked on
		addRule.setOnAction(e -> {
			if (!foodData.getAllFoodItems().isEmpty()) {
				// get the value of nutrients, comparator and compare value and parse it
				String nutrients = (String) j.getValue();
				if ( nutrients == "Calories") {
					nutrients = "calories";
				} else if( nutrients == "Fat(g)") {
					nutrients = "fat";
				} else if( nutrients == "Carbs(g)") {
					nutrients = "carbs";
				} else if( nutrients == "Fiber(g)") {
					nutrients = "fiber";
				} else if( nutrients == "Protein(g)") {
					nutrients = "protein";
				}
				String comparator = (String) compar.getValue();
				String compareValue = numSel.getText();
				boolean isNumber = true;
				try {
				  Double.parseDouble(compareValue);
				} catch(NumberFormatException err)
				{
					isNumber = false;
				}
				
				if (nutrients != null && comparator != null && compareValue != null && isNumber) {
					// formulate the rule string
					String newRule = nutrients + " " + comparator + " " + compareValue;
					// add new rule to the rules ObservableList
					rules.add(newRule);
					
					// clear out the query bars
					j.getSelectionModel().clearSelection();
					compar.getSelectionModel().clearSelection();
					numSel.clear();
					List<FoodItem> list = foodData.filterByNutrients(rules);
					
					tableViewWrapper.applyRules(list);
				}
			}
		});
		
		// displays rule when clicked on
		viewRules.setOnAction(e -> rulesPopUp.display());
		
		// creates filter section on GUI
		filterHbox.getChildren().addAll(selLabel, j, compar, numSel, addRule,viewRules);
	}
	
	/**
	 * Accessor method for the components in the query bar
	 * @return
	 */
	public HBox getComponent() {
		return this.filterHbox;
	}
	
	public void resetRules() {
		rules.clear();
	}
}



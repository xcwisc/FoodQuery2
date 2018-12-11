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
		RulesPopUp rulesPopUp = new RulesPopUp(rules, tableViewWrapper, foodData);
		ComboBox compar = new ComboBox(comparors);
		Label selLabel = new Label("Display food item with: ");
		selLabel.setId("filter-label");
		TextField numSel = new TextField();
		numSel.setPromptText("type amount");
		ComboBox j = new ComboBox(labelz);
		filterHbox = new HBox();
		Button addRule = new Button("Add Rule");
		Button viewRules = new Button("View Rules");
		
		addRule.setOnAction(e -> {
			// get the value of nutirents and parse it
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
			
			if (nutrients != null && comparator != null && compareValue != null) {
				// formulate the rule string
				String newRule = nutrients + " " + comparator + " " + compareValue;
				// add new rule to the rules ObservableList
				rules.add(newRule);
				
				// clear out the query bars
				j.getSelectionModel().clearSelection();
				compar.getSelectionModel().clearSelection();
				numSel.clear();
				List<FoodItem> list = foodData.filterByNutrients(rules);
	//			for (FoodItem foodItem : list) {
	//				System.out.println(foodItem);
	//			}
	//			System.out.println("//////////////////////////");
				tableViewWrapper.applyRules(list);
				
	//			// test
	//			for (String rule : rules) {
	//				System.out.println(rule);
	//			}
	//			List<FoodItem> temp = foodData.filterByNutrients(rules);
	//			for (FoodItem foodItem: temp) {
	//				System.out.println(foodItem.getFullName());
	//			}
	//			System.out.println("rule added");
			}
		});
		viewRules.setOnAction(e -> rulesPopUp.display());
//		deleteRule.setOnAction(e -> RulesPopUp.deleteRule());
		filterHbox.getChildren().addAll(selLabel, j, compar, numSel, addRule,viewRules);
	}
	
	/**
	 * Accessor method for the components in the query bar
	 * @return
	 */
	public HBox getComponent() {
		return this.filterHbox;
	}
}

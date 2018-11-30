package application;

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
	
	private ObservableList<String> comparors;
	private ObservableList<String> labelz;
	HBox filterHbox;
	
	
	public QueryBarWrapper(ObservableList<String> comparors, ObservableList<String> labelz) {
		this.comparors = comparors;
		this.labelz = labelz;
		ComboBox compar = new ComboBox(comparors);
		Label selLabel = new Label("Display food item with: ");
		selLabel.setId("filter-label");
		TextField numSel = new TextField();
		numSel.setPromptText("type amount");
		ComboBox j = new ComboBox(labelz);
		filterHbox = new HBox();
		Button selSubbutton = new Button("Add Rule");
		Button viewRules = new Button("View Rules");
		viewRules.setOnAction(e -> RulesPopUp.display());
		filterHbox.getChildren().addAll(selLabel, j, compar, numSel, selSubbutton,viewRules);
	}
	
	public HBox getComponent() {
		return this.filterHbox;
	}
}

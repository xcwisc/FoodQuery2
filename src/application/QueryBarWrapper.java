package application;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class QueryBarWrapper {
	
	private ObservableList<String> comparors;
	private ObservableList<String> labelz;
	HBox filterHbox;
	
	
	public QueryBarWrapper(ObservableList<String> comparors, ObservableList<String> labelz) {
		this.comparors = comparors;
		this.labelz = labelz;
		ComboBox compar = new ComboBox(comparors);
		Label selLabel = new Label("  Display food item with: ");
		TextField numSel = new TextField();
		numSel.setPromptText("type amount");
		ComboBox j = new ComboBox(labelz);
		filterHbox = new HBox();
		Button selSubbutton = new Button("Submit");
		filterHbox.getChildren().addAll(selLabel, j, compar, numSel, selSubbutton);
	}
	
	public HBox getComponent() {
		return this.filterHbox;
	}
}

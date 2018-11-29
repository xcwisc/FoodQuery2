package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;

public class RulesPopUp {
	public static void display() {
		Stage popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Rules List");
		ObservableList<String> rules = FXCollections.observableArrayList(
				"less than 1g Fat", "more than 200 Calories", 
				"less than or equal 5g Protein", 
				"more than or equal 2g Fiber");
		ListView<String> rulez = new ListView<String>();
		rulez.setItems(rules);
		Scene ruleScene= new Scene(rulez, 400, 400);
		popup.setScene(ruleScene);
	      
		popup.showAndWait();
	}
}

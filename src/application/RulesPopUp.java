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

public class RulesPopUp {
    


//    private static ObservableList<String> rules = FXCollections.observableArrayList();
	private ObservableList<String> rules;
	
    public RulesPopUp(ObservableList<String> rules) {
    	this.rules = rules;
    }
    


    public void display() {
        
        ListView<String> rulez= new ListView<String>();
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Rules List");

        rulez.setItems(rules);
        
        rulez.setOnMouseClicked(event -> {
			if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 && !rules.isEmpty()) {
				// get the index of the removing target
				int index = rulez.getSelectionModel().getSelectedIndex();
				if (index != -1) {
					System.out.println(index);
					rules.remove(index);
					rulez.getSelectionModel().select(-1);
				}				
			} 
		});
        
        
        
        
        Scene ruleScene= new Scene(rulez, 400, 400);
        popup.setScene(ruleScene);
          
        popup.showAndWait();
    }
}


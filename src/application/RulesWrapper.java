package application;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


/**
 * Wrapper class for a rule object
 * @author A-Team 75
 *
 */
public class RulesWrapper {
	private HBox rule;
	
	public RulesWrapper(String attribute, String comparison, Double number) {
		Text attributeText = new Text(attribute);
		Text comparisonText = new Text(comparison);
		Text numberText = new Text(number.toString());
		Button deleteButton = new Button("Delete Rule");
		rule.getChildren().addAll(attributeText, comparisonText, numberText, deleteButton);
	}
	
	public HBox getComponent() {
		return rule;
	}
}

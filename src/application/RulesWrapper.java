
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
	private HBox rule; // a single rule

	/**
	 * Public constructor for RulesWrapper. Initializes the data in a 
	 * single rule.
	 * @param attribute ("Calories", "Carbs, etc.)
	 * @param comparison ("<=", ">=", "==")
	 * @param number (some number to complete food rule)
	 */
	public RulesWrapper(String attribute, String comparison, Double number) {
		Text attributeText = new Text(attribute);
		Text comparisonText = new Text(comparison);
		Text numberText = new Text(number.toString());
		Button deleteButton = new Button("Delete Rule");
		rule.getChildren().addAll(attributeText, comparisonText, numberText, deleteButton);
	}

	/**
	 * Accessor of a single rule
	 * @return
	 */
	public HBox getComponent() {
		return rule;
	}
}
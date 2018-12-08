package application;

import com.sun.javafx.scene.control.skin.LabeledText;
import com.sun.javafx.scene.control.skin.ListViewSkin;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Wrapper class for the sidebar that contains information concerning
 * the meal that the user has chosen
 * @author A-Team 75
 *
 */
public class MealInfoWrapper {
	private VBox mealInfo;
	ObservableList<String> data;
	ListView<String> list;
	
	public MealInfoWrapper() {
		mealInfo = new VBox();
		mealInfo.setId("meal-info");
		this.data = FXCollections.observableArrayList();
		Label yourMealLabel = new Label("Your Meal");
		yourMealLabel.setId("title");
		
		Label clickToRemoveLabel = new Label("  (Double click item to remove)");
		clickToRemoveLabel.setId("note-label");
		list = new ListView<String>();
		
		list.setItems(data);
		list.setOnMouseClicked(event -> {
			if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 && !data.isEmpty()) {
				int index = list.getSelectionModel().getSelectedIndex();
//				System.out.println(index);
				data.remove(index);
			} 
		});
		
		HBox calorieCounter = new HBox();
		HBox fatCounter = new HBox();
		HBox carbCounter = new HBox();
		HBox fiberCounter = new HBox();
		HBox proteinCounter = new HBox();
		calorieCounter.getChildren().add(new Label("Total Calories: "));
		calorieCounter.getChildren().add(new Label("xx"));
		fatCounter.getChildren().add(new Label("Total Fat(g): "));
		fatCounter.getChildren().add(new Label("xx"));
		carbCounter.getChildren().add(new Label("Total Carbs(g): "));
		carbCounter.getChildren().add(new Label("xx"));
		fiberCounter.getChildren().add(new Label("Total Fiber(g): "));
		fiberCounter.getChildren().add(new Label("xx"));
		proteinCounter.getChildren().add(new Label("Total Protein(g): "));
		proteinCounter.getChildren().add(new Label("xx"));
		
		Button saveAndExit = new Button("Save Meal and Exit");
		saveAndExit.setOnAction(e -> Platform.exit());

		
		// add all elements into the vBox
		mealInfo.getChildren().addAll(
			new HBox(yourMealLabel, clickToRemoveLabel), 
			list, calorieCounter, fatCounter, carbCounter, 
			fiberCounter, proteinCounter, saveAndExit);
	}
	
	public VBox getComponent() {
		return this.mealInfo;
	}
	
	public void add(FoodItem foodItem) {
		this.data.add(foodItem.getItemName());
	}
	
	
}

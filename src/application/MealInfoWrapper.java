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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

/**
 * Wrapper class for the sidebar that contains information concerning
 * the meal that the user has chosen
 * @author A-Team 75
 *
 */
public class MealInfoWrapper {
	private VBox mealInfo;
	private ObservableList<FoodItem> data;
	private ListView<FoodItem> list;
	private double calories;
	private double fat;
	private double carbs;
	private double fiber;
	private double protein;
	private Label caloriesLabel;
	private Label fatLabel;
	private Label carbsLabel;
	private Label fiberLabel;
	private Label proteinLabel;
	
	
	public MealInfoWrapper() {
		mealInfo = new VBox();
		mealInfo.setId("meal-info");
		// initialize the data ObservableList
		this.data = FXCollections.observableArrayList();
		this.calories = 0;
		this.fat = 0;
		this.carbs = 0;
		this.fiber = 0;
		this.protein = 0;
		
		// initialize the list
		list = new ListView<>(this.data);
		list.setCellFactory(param -> new ListCell<FoodItem>() {
            @Override
            protected void updateItem(FoodItem item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getItemName() == null) {
                    setText(null);
                } else {
                    setText(item.getItemName());
                }
            }
        });
		
		list.setOnMouseClicked(event -> {
			if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 && !data.isEmpty()) {
				int index = list.getSelectionModel().getSelectedIndex();
//				System.out.println(index);
				if (index != -1) {
					FoodItem foodItem = data.get(index);
					this.calories -= foodItem.getNutrientValue("calories");
					this.fat -= foodItem.getNutrientValue("fat");
					this.carbs -= foodItem.getNutrientValue("carbs");
					this.fiber -= foodItem.getNutrientValue("fiber");
					this.protein -= foodItem.getNutrientValue("protein");
					updateAnalysis();
					data.remove(index);
					list.getSelectionModel().select(-1);
				}				
			} 
		});
		
		// initialize the labels
		Label yourMealLabel = new Label("Your Meal");
		yourMealLabel.setId("title");
		Label clickToRemoveLabel = new Label("  (Double click item to remove)");
		clickToRemoveLabel.setId("note-label");
		
		HBox calorieCounter = new HBox();
		HBox fatCounter = new HBox();
		HBox carbCounter = new HBox();
		HBox fiberCounter = new HBox();
		HBox proteinCounter = new HBox();
		this.caloriesLabel = new Label(Double.toString(this.calories));
		this.fatLabel = new Label(Double.toString(this.fat));
		this.carbsLabel = new Label(Double.toString(this.carbs));
		this.fiberLabel = new Label(Double.toString(this.fiber));
		this.proteinLabel = new Label(Double.toString(this.protein));
		
		
		calorieCounter.getChildren().add(new Label("Total Calories: "));
		calorieCounter.getChildren().add(caloriesLabel);
		fatCounter.getChildren().add(new Label("Total Fat(g): "));
		fatCounter.getChildren().add(fatLabel);
		carbCounter.getChildren().add(new Label("Total Carbs(g): "));
		carbCounter.getChildren().add(carbsLabel);
		fiberCounter.getChildren().add(new Label("Total Fiber(g): "));
		fiberCounter.getChildren().add(fiberLabel);
		proteinCounter.getChildren().add(new Label("Total Protein(g): "));
		proteinCounter.getChildren().add(proteinLabel);
		
		Button saveAndExit = new Button("Exit");
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
		this.data.add(foodItem);
		this.calories += foodItem.getNutrientValue("calories");
		this.fat += foodItem.getNutrientValue("fat");
		this.carbs += foodItem.getNutrientValue("carbs");
		this.fiber += foodItem.getNutrientValue("fiber");
		this.protein += foodItem.getNutrientValue("protein");
		updateAnalysis();
	}
	
	private void updateAnalysis() {
		this.caloriesLabel.setText(Double.toString(this.calories));
		this.fatLabel.setText(Double.toString(this.fat));
		this.carbsLabel.setText(Double.toString(this.carbs));
		this.fiberLabel.setText(Double.toString(this.fiber));
		this.proteinLabel.setText(Double.toString(this.protein));
	}
	
	
}

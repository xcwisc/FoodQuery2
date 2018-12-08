package application;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Wrapper class for the toolbar to go across the top of the program. Includes
 * the logo of our program as well as buttons to perform actions.
 * 
 * @author A-Team 75
 *
 */
public class ToolBarWrapper {
	private String labelName;
	private Label label;
	private ToolBar toolBar;

	public ToolBarWrapper(CenterVboxWrapper centerVboxWrapper, FoodDataADT<FoodItem> foodData, String labelName,
			Stage primaryStage) {
		this.labelName = labelName;
		label = new Label(this.labelName);
		label.setId("nav-bar-brand");
		// label.setFont(new Font(fontFamily, fontSize));
		Button addFoodItem = new Button("Add Food Item");
		addFoodItem.setId("nav-bar-button");
		Button loadFoodList = new Button("Load New Food List");
		loadFoodList.setId("nav-bar-button");
		Button saveFoodList = new Button("Save Food List");
		saveFoodList.setId("nav-bar-button");

		AddItemPopUp addItemPopUp = new AddItemPopUp(centerVboxWrapper, foodData);
		addFoodItem.setOnAction(e -> addItemPopUp.display());

		loadFoodList.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			configureFileChooser(fileChooser);
			File file = fileChooser.showOpenDialog(primaryStage);
			if (file != null) {
				openFile(foodData, file.toString(), centerVboxWrapper);
			}
		});
		
		saveFoodList.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Food List");
			File file = fileChooser.showSaveDialog(primaryStage);
			String fileName = file.toString();
			if (file != null) {
				foodData.saveFoodItems(fileName);
			}
		});

		toolBar = new ToolBar(label, addFoodItem, loadFoodList, saveFoodList);
		toolBar.setId("nav-bar");

	}

	// extension filter
	private static void configureFileChooser(final FileChooser fileChooser) {
		fileChooser.setTitle("Open a .csv File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
	}

	

	private void openFile(FoodDataADT<FoodItem> foodData, String path, CenterVboxWrapper centerVboxWrapper) {
		foodData.loadFoodItems(path);
		TableViewWrapper tableViewWrapper = centerVboxWrapper.getTabelViewWrapper();
		tableViewWrapper.update();
	}


	public ToolBar getComponent() {
		return this.toolBar;
	}
}

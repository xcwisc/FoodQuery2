package application;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;

/**
 * Pop up item to input a new food item to the food item table. Within there
 * are fields for the user to input the food attributes.
 * @author A-Team 75
 *
 */
public class AddItemPopUp {
   
    
	public static void display() {
		Stage popupwindow=new Stage();
		      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Add item");
		      
		      
		Label label1= new Label("Pop up window now displayed");
		      
		     
		Button button1= new Button("Close this pop up window");
		     
		     
		button1.setOnAction(e -> popupwindow.close());
		     
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		     
		      

		TextField addItemName = new TextField();
		addItemName.setPromptText("item name");

		TextField addBrand = new TextField();
		addBrand.setPromptText("brand");
		TextField addCalories = new TextField();
		addCalories.setPromptText("calories");
		TextField addFat = new TextField();
		addFat.setPromptText("fat(g)");
		TextField addCarbs = new TextField();
		addCarbs.setPromptText("carbs(g)");
		TextField addFiber = new TextField();
		addFiber.setPromptText("fiber(g)");
		TextField addProtein = new TextField();
		addProtein.setPromptText("protein(g)");
		
		Text scenetitle = new Text("Enter Details:");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
		
		Label userName = new Label("Item Name");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Brand");
		grid.add(pw, 0, 2);

		TextField pwBox = new TextField();
		grid.add(pwBox, 1, 2);
		
		Label test = new Label("Calories");
		grid.add(test, 0, 3);

		TextField test1 = new TextField();
		grid.add(test1, 1, 3);
		
		Label test2 = new Label("Fat(g)");
		grid.add(test2, 0, 4);

		TextField test3 = new TextField();
		grid.add(test3, 1, 4);

		Label test4 = new Label("Carbs(g)");
		grid.add(test4, 0, 5);

		TextField test5 = new TextField();
		grid.add(test5, 1, 5);
		
		Label test6 = new Label("Fiber(g)");
		grid.add(test6, 0, 6);

		TextField test7 = new TextField();
		grid.add(test7, 1, 6);
		
		Label test8 = new Label("Protein(g)");
		grid.add(test8, 0, 7);

		TextField test9 = new TextField();
		grid.add(test9, 1, 7);
		
		// add elements to the grid
//		grid.add(addItemName, 1, 1);
//		grid.add(addCalories, 2, 2);
//		grid.add(addFat, 3, 3);
//		grid.add(addCarbs, 4, 4);
//		grid.add(addFiber, 5, 5);
//		grid.add(addProtein, 6, 6);
//		
//		grid.add(new Label("item name"), 0, 1);
//		grid.add(new Label("brand"), 0, 2);
//		grid.add(new Label("calories"), 0, 3);
//		grid.add(new Label("fat(g)"), 0, 4);
//		grid.add(new Label("fiber(g)"), 0, 5);
//		grid.add(new Label("protein(g)"), 0, 6);
		
		Button btn = new Button("Submit Item");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 9);
		

		// Box to hold all text fields
		HBox textFields = new HBox();
		textFields.getChildren().addAll(
				addItemName,
				addBrand,
				addCalories, 
				addFat,
				addCarbs,
				addFiber,
				addProtein);
		      
		Scene scene1= new Scene(grid, 400, 400);
		      
		popupwindow.setScene(scene1);
		      
		popupwindow.showAndWait();
		       
	}

}

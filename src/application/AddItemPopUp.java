package application;


import java.util.UUID;

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
	
	private CenterVboxWrapper centerVboxWrapper; // GUI center panel components
	private FoodDataADT<FoodItem> foodData; // methods and fields associated with a food item
	
	/**
	 * Public constructor
	 * @param centerVboxWrapper
	 * @param foodData
	 */
	public AddItemPopUp(CenterVboxWrapper centerVboxWrapper, FoodDataADT<FoodItem> foodData) {
		this.centerVboxWrapper = centerVboxWrapper;
		this.foodData = foodData;
	}
	
	/**
	 * Opens a pop up window that asks user for input in order to add a new 
	 * food item to the food list.
	 */
	public void display() {
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
		
		
		Button btn = new Button("Submit Item");
		btn.setOnAction(e -> {
			//get the info
			String itemName = userTextField.getText();
			String brand = pwBox.getText();
			String calories = test1.getText();
			String fat = test3.getText();
			String carbs = test5.getText();
			String fiber = test7.getText();
			String protein = test9.getText();
			
			//update
			update(itemName, brand, calories, fat, carbs, fiber, protein);
			popupwindow.close();
		});
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 9);
		   
		Scene scene1= new Scene(grid, 400, 400);		      
		popupwindow.setScene(scene1);		      
		popupwindow.showAndWait();
		       
	}
	
	/**
	 * Updates the food list in the main GUI with the new food item from the
	 * add food item window
	 * @param itemName
	 * @param brand
	 * @param calories
	 * @param fat
	 * @param carbs
	 * @param fiber
	 * @param protein
	 */
	public void update(String itemName, String brand, String calories, String fat, String carbs, String fiber,
			String protein) {
//		System.out.println(itemName);
//		System.out.println(brand);
//		System.out.println(calories);
//		System.out.println(fat);
//		System.out.println(carbs);
//		System.out.println(fiber);
//		System.out.println(protein);
		
		// generate a id for the food
		String uniqueID = UUID.randomUUID().toString();
		uniqueID = uniqueID.replace("-", "").substring(8);
		
		// create the foodItem and insert it into the backend
		FoodItem foodItem = new FoodItem(uniqueID, itemName, brand, calories, fat, carbs, fiber,
				protein);
		foodData.addFoodItem(foodItem);
		TableViewWrapper tableViewWrapper = this.centerVboxWrapper.getTabelViewWrapper();
    	tableViewWrapper.update();
	}

}

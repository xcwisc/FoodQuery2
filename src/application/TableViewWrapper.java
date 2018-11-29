package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewWrapper {
	
	private TableView table;
	
	private ObservableList<FoodItem> data;
	
	public TableViewWrapper(ObservableList<FoodItem> data) {
		this.data = data;
		table = new TableView();
		                
        TableColumn itemNameCol = new TableColumn("Item Name");
        itemNameCol.setMinWidth(100);
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<FoodItem, String>("itemName"));
 
        TableColumn brandCol = new TableColumn("Brand");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(
                new PropertyValueFactory<FoodItem, String>("brand"));
        
        TableColumn calCol = new TableColumn("Calories");
	    calCol.setMinWidth(100);
	    calCol.setCellValueFactory(
	            new PropertyValueFactory<FoodItem, String>("calories"));
	     
	    TableColumn fatCol = new TableColumn("Fat(g)");
	    fatCol.setMinWidth(100);
	    fatCol.setCellValueFactory(
	            new PropertyValueFactory<FoodItem, String>("fat"));
	     
	    TableColumn carbsCol = new TableColumn("Carbs(g)");
	    carbsCol.setMinWidth(100);
	    carbsCol.setCellValueFactory(
	            new PropertyValueFactory<FoodItem, String>("carbs"));
	      
	    TableColumn fiberCol = new TableColumn("Fiber(g)");
	    fiberCol.setMinWidth(100);
	    fiberCol.setCellValueFactory(
	            new PropertyValueFactory<FoodItem, String>("fiber"));
	      
	    TableColumn proteinCol = new TableColumn("Protein(g)");
	    proteinCol.setMinWidth(100);
	    proteinCol.setCellValueFactory(
	            new PropertyValueFactory<FoodItem, String>("protein"));
      
      
        
        table.setItems(data);
        table.getColumns().addAll(itemNameCol, brandCol, calCol, fatCol, carbsCol, fiberCol, proteinCol);
        
 
	}
	
	public TableView getComponent() {
		return this.table;
	}
}

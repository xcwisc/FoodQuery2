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
                new PropertyValueFactory<FoodItem, String>("name"));
 
        TableColumn brandCol = new TableColumn("Brand");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(
                new PropertyValueFactory<FoodItem, String>("id"));
        
        table.setItems(data);
        table.getColumns().addAll(itemNameCol, brandCol);
 
	}
	
	public TableView getComponent() {
		return this.table;
	}
}

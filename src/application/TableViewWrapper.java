package application;

import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * Wrapper class for food item table
 * @author A-Team 75
 *
 */
public class TableViewWrapper {
	
	private TableView<FoodItem> table;
	
	private ObservableList<FoodItem> data;
	private FoodDataADT<FoodItem> foodData;
	
	@SuppressWarnings("unchecked")
	public TableViewWrapper(FoodDataADT<FoodItem> foodData, MealInfoWrapper mealInfoWrapper) {
		this.foodData = foodData;
		this.data = FXCollections.observableArrayList(foodData.getAllFoodItems());
		table = new TableView<FoodItem>();
		                
        TableColumn<FoodItem, String> itemNameCol = new TableColumn<FoodItem, String>("Item Name");
        itemNameCol.setMinWidth(250);
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<FoodItem, String>("itemName"));
 
        TableColumn<FoodItem, String> brandCol = new TableColumn<FoodItem, String>("Brand");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(
                new PropertyValueFactory<FoodItem, String>("brand"));
        
        TableColumn<FoodItem, Double> calCol = new TableColumn<FoodItem, Double>("Calories");
	    calCol.setMinWidth(100);	    
	    calCol.setCellValueFactory(new Callback<CellDataFeatures<FoodItem, Double>, ObservableValue<Double>>() {
	        public ObservableValue<Double> call(CellDataFeatures<FoodItem, Double> p) {
	        	Double raw = p.getValue().getNutrientValue("calories");
	        	ObservableValue<Double> result =  new SimpleDoubleProperty(raw).asObject();
	        	return result;
	        }
	     });
	     
	    TableColumn<FoodItem, Double> fatCol = new TableColumn<FoodItem, Double>("Fat(g)");
	    fatCol.setMinWidth(100);	    
	    fatCol.setCellValueFactory(new Callback<CellDataFeatures<FoodItem, Double>, ObservableValue<Double>>() {
	        public ObservableValue<Double> call(CellDataFeatures<FoodItem, Double> p) {
	        	Double raw = p.getValue().getNutrientValue("fat");
	        	ObservableValue<Double> result =  new SimpleDoubleProperty(raw).asObject();
	        	return result;
	        }
	     });
	     
	    TableColumn<FoodItem, Double> carbsCol = new TableColumn<FoodItem, Double>("Carbs(g)");
	    carbsCol.setMinWidth(100);	    
	    carbsCol.setCellValueFactory(new Callback<CellDataFeatures<FoodItem, Double>, ObservableValue<Double>>() {
	        public ObservableValue<Double> call(CellDataFeatures<FoodItem, Double> p) {
	        	Double raw = p.getValue().getNutrientValue("carbs");
	        	ObservableValue<Double> result =  new SimpleDoubleProperty(raw).asObject();
	        	return result;
	        }
	     });
	      
	    TableColumn<FoodItem, Double> fiberCol = new TableColumn<FoodItem, Double>("Fiber(g)");
	    fiberCol.setMinWidth(100);	    
	    fiberCol.setCellValueFactory(new Callback<CellDataFeatures<FoodItem, Double>, ObservableValue<Double>>() {
	        public ObservableValue<Double> call(CellDataFeatures<FoodItem, Double> p) {
	        	Double raw = p.getValue().getNutrientValue("fiber");
	        	ObservableValue<Double> result =  new SimpleDoubleProperty(raw).asObject();
	        	return result;
	        }
	     });
	      
	    TableColumn<FoodItem, Double> proteinCol = new TableColumn<FoodItem, Double>("Protein(g)");
	    proteinCol.setMinWidth(100);	    
	    proteinCol.setCellValueFactory(new Callback<CellDataFeatures<FoodItem, Double>, ObservableValue<Double>>() {
	        public ObservableValue<Double> call(CellDataFeatures<FoodItem, Double> p) {
	        	Double raw = p.getValue().getNutrientValue("protein");
	        	ObservableValue<Double> result =  new SimpleDoubleProperty(raw).asObject();
	        	return result;
	        }
	     });
      
        
        table.setItems(this.data);
        table.getColumns().addAll(itemNameCol, brandCol, calCol, fatCol, carbsCol, fiberCol, proteinCol);
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Node node = ((Node) event.getTarget()).getParent();
                TableRow row;
                if (node instanceof TableRow) {
                    row = (TableRow) node;
                } else {
                    // clicking on text part
                    row = (TableRow) node.getParent();
                }
                FoodItem food = (FoodItem) row.getItem();
//                System.out.println(food.getItemName());
                mealInfoWrapper.add(food);
                
            }
        });
	}
	
	public void applyRules(List<FoodItem> list) {
		ObservableList<FoodItem> obList = FXCollections.observableArrayList(list);
		table.setItems(obList);
	}
	
	public void update() {
		this.data = FXCollections.observableArrayList(foodData.getAllFoodItems());
		table.setItems(this.data);
	}
	
	public TableView<FoodItem> getComponent() {
		return this.table;
	}
}

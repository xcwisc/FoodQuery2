package application;

import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
	
	private TableView<FoodItem> table; // table of food items
	private ObservableList<FoodItem> data; // list of data for a single food item
	private FoodDataADT<FoodItem> foodData; // methods and fields associated with a food item
	
	/**
	 * Public constructor of TableViewWrapper class. Initializes the center panel of
	 * the GUI.
	 * @param foodData
	 * @param mealInfoWrapper
	 */
	@SuppressWarnings("unchecked")
	public TableViewWrapper(FoodDataADT<FoodItem> foodData, MealInfoWrapper mealInfoWrapper) {
		this.foodData = foodData;
		this.data = FXCollections.observableArrayList(foodData.getAllFoodItems());
		table = new TableView<FoodItem>();
		                
        TableColumn<FoodItem, String> itemNameCol = new TableColumn<FoodItem, String>("Item Name");
        itemNameCol.setMinWidth(250);
        
        //Splits the item name by capital letters and puts it back with spaces between the words. 
        itemNameCol.setCellValueFactory(new Callback<CellDataFeatures<FoodItem, String>, ObservableValue<String>>() {
	        public ObservableValue<String> call(CellDataFeatures<FoodItem, String> p) {
	        	String raw = p.getValue().getItemName();
	        	String[] formatted = raw.split("(?=\\p{Upper})");
	        	String ans = "";
	        	for (String part : formatted) {
	        		ans += part;
	        		ans += " ";
	        	}
	        	ObservableValue<String> result =  new SimpleStringProperty(ans);
	        	return result;
	        }
	     });
 
        //Sets the Brand column. 
        TableColumn<FoodItem, String> brandCol = new TableColumn<FoodItem, String>("Brand");
        brandCol.setMinWidth(100);
        //Splits the brand by capital letters and puts it back with spaces between the words. 
        brandCol.setCellValueFactory(new Callback<CellDataFeatures<FoodItem, String>, ObservableValue<String>>() {
	        public ObservableValue<String> call(CellDataFeatures<FoodItem, String> p) {
	        	String raw = p.getValue().getBrand();
	        	String[] formatted = raw.split("(?=\\p{Upper})");
	        	String ans = "";
	        	for (String part : formatted) {
	        		ans += part;
	        		ans += " ";
	        	}
	        	ObservableValue<String> result =  new SimpleStringProperty(ans);
	        	return result;
	        }
	     });
        
        //Sets the Calories column. 
        TableColumn<FoodItem, Double> calCol = new TableColumn<FoodItem, Double>("Calories");
	    calCol.setMinWidth(100);	    
	    calCol.setCellValueFactory(new Callback<CellDataFeatures<FoodItem, Double>, ObservableValue<Double>>() {
	        public ObservableValue<Double> call(CellDataFeatures<FoodItem, Double> p) {
	        	Double raw = p.getValue().getNutrientValue("calories");
	        	ObservableValue<Double> result =  new SimpleDoubleProperty(raw).asObject();
	        	return result;
	        }
	     });
	     
	    //Sets the Fat column. 
	    TableColumn<FoodItem, Double> fatCol = new TableColumn<FoodItem, Double>("Fat(g)");
	    fatCol.setMinWidth(100);	    
	    fatCol.setCellValueFactory(new Callback<CellDataFeatures<FoodItem, Double>, ObservableValue<Double>>() {
	        public ObservableValue<Double> call(CellDataFeatures<FoodItem, Double> p) {
	        	Double raw = p.getValue().getNutrientValue("fat");
	        	ObservableValue<Double> result =  new SimpleDoubleProperty(raw).asObject();
	        	return result;
	        }
	     });
	    
	    //Sets the Carbs column.  
	    TableColumn<FoodItem, Double> carbsCol = new TableColumn<FoodItem, Double>("Carbs(g)");
	    carbsCol.setMinWidth(100);	    
	    carbsCol.setCellValueFactory(new Callback<CellDataFeatures<FoodItem, Double>, ObservableValue<Double>>() {
	        public ObservableValue<Double> call(CellDataFeatures<FoodItem, Double> p) {
	        	Double raw = p.getValue().getNutrientValue("carbs");
	        	ObservableValue<Double> result =  new SimpleDoubleProperty(raw).asObject();
	        	return result;
	        }
	     });
	      
	    //Sets the Fiber column.
	    TableColumn<FoodItem, Double> fiberCol = new TableColumn<FoodItem, Double>("Fiber(g)");
	    fiberCol.setMinWidth(100);	    
	    fiberCol.setCellValueFactory(new Callback<CellDataFeatures<FoodItem, Double>, ObservableValue<Double>>() {
	        public ObservableValue<Double> call(CellDataFeatures<FoodItem, Double> p) {
	        	Double raw = p.getValue().getNutrientValue("fiber");
	        	ObservableValue<Double> result =  new SimpleDoubleProperty(raw).asObject();
	        	return result;
	        }
	     });
	      
	    //Sets the Protein column.
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
        
        //When an item is double clicked the item is added to meal list. 
        table.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                Node node = ((Node) event.getTarget()).getParent();
                TableRow row;
                if (node instanceof TableRow) {
                    row = (TableRow) node;
                } else {
                    // clicking on text part
                	
//                    row = (TableRow) node.getParent();
                    row = null;
                }
                if (row != null) {
	                FoodItem food = (FoodItem) row.getItem();
	                if (food != null) {
	                mealInfoWrapper.add(food);
	                }
	            }                
            }
        });
	}
	
	/**
	 * Displays only food items who conform to all user defined rules
	 * @param list of food items
	 */
	public void applyRules(List<FoodItem> list) {
		ObservableList<FoodItem> obList = FXCollections.observableArrayList(list);
		table.setItems(obList);
	}
	
	/**
	 * Updates the table of food items when new items are added to the list
	 */
	public void update() {
		this.data = FXCollections.observableArrayList(foodData.getAllFoodItems());
		table.setItems(this.data);
	}
	
	/**
	 * Accessor method of components that make up center panel of GUI
	 * @return TableView<FoodItem>
	 */
	public TableView<FoodItem> getComponent() {
		return this.table;
	}
}

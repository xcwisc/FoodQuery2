package application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents a food item with all its properties.
 * 
 * @author aka
 */
public class FoodItem {
    // The name of the food item.
    private String name;

    // The id of the food item.
    private String id;

    // The name of the food item.
    private String itemName;

    // The id of the food item.
    private String brand;
    
    private String calories;
    
    private String fat;
    
    private String carbs;
    
    private String fiber;
    
    private String protein;

    
    //String calories, String fat, String carbs, String fiber, String protein
    
    /**
     * Constructor
     * @param name name of the food item
     * @param id unique id of the food item
     */



    // Map of nutrients and value.
    private HashMap<String, Double> nutrients;
    
    /**
     * Constructor
     * @param name name of the food item
     * @param id unique id of the food item 
     */
    public FoodItem(String id, String itemName, String brand, String calories, String fat, String carbs, String fiber, String protein) {
        // TODO : Complete
    	this.id = id;
        this.itemName = new String(itemName);
        this.brand = brand;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.fiber = fiber;
        this.protein = protein;
        
    }
    
   
    
    
    /**
     * Gets the name of the food item
     * 
     * @return name of the food item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Gets the unique id of the food item
     * 
     * @return id of the food item
     */
    public String getBrand() {
        return brand;
    }
    
    public String getCalories() {
    	return calories;
    }
    
    public String getFat() {
    	return fat;
    }
    
    public String getCarbs() {
    	return carbs;
    }
    
    public String getFiber() {
    	return fiber;
    }
    
    public String getProtein() {
    	return protein;
    }
    
    /**
     * Gets the nutrients of the food item
     * 
     * @return nutrients of the food item
     */
    public HashMap<String, Double> getNutrients() {
        // TODO : Complete
        return null;
    }

    /**
     * Adds a nutrient and its value to this food. 
     * If nutrient already exists, updates its value.
     */
    public void addNutrient(String name, double value) {
        // TODO : Complete
    }

    /**
     * Returns the value of the given nutrient for this food item. 
     * If not present, then returns 0.
     */
    public double getNutrientValue(String name) {
        // TODO : Complete
        return 0;
    }
    
}

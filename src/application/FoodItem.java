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

	// The id of the food item.
	private String id;
	// The name of the food item.
	private String itemName;
	private String brand;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            name of the food item
	 * @param id
	 *            unique id of the food item
	 */

	// Map of nutrients and value.
	private HashMap<String, Double> nutrients;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            name of the food item
	 * @param id
	 *            unique id of the food item
	 */
	public FoodItem(String id, String itemName, String brand, String calories, String fat, String carbs, String fiber,
			String protein) {
		this.id = id;
		this.itemName = new String(itemName);
		this.brand = brand;
		this.nutrients = new HashMap<String, Double>();
		this.nutrients.put("calories", Double.parseDouble(calories));
		this.nutrients.put("fat", Double.parseDouble(fat));
		this.nutrients.put("carbs", Double.parseDouble(carbs));
		this.nutrients.put("fiber", Double.parseDouble(fiber));
		this.nutrients.put("protein", Double.parseDouble(protein));
	}

	/**
	 * Gets the name of the food item
	 * 
	 * @return name of the food item
	 */
	public String getItemName() {
		return this.itemName;
	}

	/**
	 * Gets the unique id of the food item
	 * 
	 * @return id of the food item
	 */
	public String getBrand() {
		return this.brand;
	}


	/**
	 * Gets the nutrients of the food item
	 * 
	 * @return nutrients of the food item
	 */
	public HashMap<String, Double> getNutrients() {
		return this.nutrients;
	}

	/**
	 * Adds a nutrient and its value to this food. If nutrient already exists,
	 * updates its value.
	 */
	public void addNutrient(String name, double value) {
		this.nutrients.put(name, value);
	}

	/**
	 * Returns the value of the given nutrient for this food item. If not present,
	 * then returns 0.
	 */
	public double getNutrientValue(String name) {
		if (this.nutrients.containsKey(name))
			return this.nutrients.get(name).doubleValue();
		return 0;
	}

	/**
	 * get the string to print the foodItem in the format of
	 * <id>,<food_name>,<calories>,<calorie_count>,<fat>,<fat_grams>,<carbohydrate>,<carbohydrate_grams>,<fiber>,<fiber_grams>,<protein>,<protein_grams>
	 * 556540ff5d613c9d5f5935a9,Stewarts_PremiumDarkChocolatewithMintCookieCrunch,calories,280,fat,18,carbohydrate,34,fiber,3,protein,3
	 */
	public String getString() {
		String result = this.id + "," + this.brand + "_" + this.itemName + ",calories," + this.getNutrientValue("calories") + ",fat,"
				+ this.getNutrientValue("fat") + ",carbohydrate," + this.getNutrientValue("carbs") + ",fiber," + this.getNutrientValue("fiber")
				+ ",protein," + this.getNutrientValue("protein");
		return result;
	}

	/**
	 * get fullName of the item for name filtering
	 * 
	 * @return the fullName containing both the brand and the name
	 */
	public String getFullName() {
		String result = this.brand + "_" + this.itemName;
		return result;
	}

}

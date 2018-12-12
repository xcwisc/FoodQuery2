package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents the backend for managing all the operations associated
 * with FoodItems
 * 
 * @author sapan (sapan@cs.wisc.edu)
 */
public class FoodData implements FoodDataADT<FoodItem> {

	// List of all the food items.
	private List<FoodItem> foodItemList;
	
	// Map of nutrients and their corresponding index
	private HashMap<String, BPTree<Double, FoodItem>> indexes;

	/**
	 * Public constructor
	 */
	public FoodData() {
		this.foodItemList = new ArrayList<FoodItem>();
		this.indexes = new HashMap<String, BPTree<Double, FoodItem>>();
		BPTree<Double, FoodItem> BPTreeCalories = new BPTree<Double, FoodItem>(3);
		this.indexes.put("calories", BPTreeCalories);
		BPTree<Double, FoodItem> BPTreeFat = new BPTree<Double, FoodItem>(3);
		this.indexes.put("fat", BPTreeFat);
		BPTree<Double, FoodItem> BPTreeCarbs = new BPTree<Double, FoodItem>(3);
		this.indexes.put("carbs", BPTreeCarbs);
		BPTree<Double, FoodItem> BPTreeFiber = new BPTree<Double, FoodItem>(3);
		this.indexes.put("fiber", BPTreeFiber);
		BPTree<Double, FoodItem> BPTreeProtein = new BPTree<Double, FoodItem>(3);
		this.indexes.put("protein", BPTreeProtein);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see skeleton.FoodDataADT#loadFoodItems(java.lang.String)
	 */
	@Override
	public void loadFoodItems(String filePath) {
		try {
			File inputF = new File(filePath);

			InputStream inputFS = new FileInputStream(inputF);

			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
			// create a input pipeline that parse the csv file and add the parsed foodItem to the list
			br.lines().filter(data -> data.length() > 11).map(rule -> parser(rule))
					.forEach(food -> this.foodItemList.add(food));
			
			// Sorts the list of food alphabetically before inserting
			// into the B Plus Tree
			Collections.sort(foodItemList, new Comparator<FoodItem>() {
				public int compare(FoodItem f1, FoodItem f2) {
					Character character = f1.itemName.toLowerCase().charAt(0);
					Character oCharacter = f2.itemName.toLowerCase().charAt(0);
					return character.compareTo(oCharacter);
				}
			});
			
			// Construct 5 BPtrees. Each tree corresponds to sorting 
			// the items by one of the nutrients
			BPTree<Double, FoodItem> BPTreeCalories = this.indexes.get("calories");
			for (FoodItem item : this.foodItemList) {
				BPTreeCalories.insert(item.getNutrientValue("calories"), item);
			}
			
			BPTree<Double, FoodItem> BPTreeFat = this.indexes.get("fat");
			for (FoodItem item : this.foodItemList) {
				BPTreeFat.insert(item.getNutrientValue("fat"), item);
			}
			
			BPTree<Double, FoodItem> BPTreeCarbs = this.indexes.get("carbs");
			for (FoodItem item : this.foodItemList) {
				BPTreeCarbs.insert(item.getNutrientValue("carbs"), item);
			}
			
			BPTree<Double, FoodItem> BPTreeFiber = this.indexes.get("fiber");
			for (FoodItem item : this.foodItemList) {
				BPTreeFiber.insert(item.getNutrientValue("fiber"), item);
			}
			
			BPTree<Double, FoodItem> BPTreeProtein = this.indexes.get("protein");
			for (FoodItem item : this.foodItemList) {
				BPTreeProtein.insert(item.getNutrientValue("protein"), item);
			}	
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	// helper method that parse that FoodItem string and construct a foodItem instance
	private FoodItem parser(String data) {
		String[] parts = data.split(",");
		String id = parts[0];
		String[] wholeName = parts[1].split("_");
		String itemName = wholeName[1];
		String brand = wholeName[0];
		String calories = parts[3];
		String fat = parts[5];
		String carbs = parts[7];
		String fiber = parts[9];
		String protein = parts[11];
		// create the FoodItem instance
		FoodItem ans = new FoodItem(id, itemName, brand, calories, fat, carbs, fiber, protein);
		return ans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see skeleton.FoodDataADT#filterByName(java.lang.String)
	 */
	@Override
	public List<FoodItem> filterByName(String substring) {
		List<FoodItem> result = new ArrayList<FoodItem>();
		// loop through the list to filter the food item
		this.foodItemList.forEach(item -> {
			if (item.getFullName().toLowerCase().contains(substring.toLowerCase())) {
				result.add(item);
			}
		});
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see skeleton.FoodDataADT#filterByNutrients(java.util.List)
	 */
	@Override
	public List<FoodItem> filterByNutrients(List<String> rules) {
		// "<nutrient> <comparator> <value>"
		// calories > 200
		
		// answer list to return
		List<FoodItem> ans = new ArrayList<FoodItem>();
		ans = this.foodItemList;
		
		// making a copy of the indexes hashmap
		HashMap<String, BPTree<Double, FoodItem>> indexesCopy = new HashMap<String, BPTree<Double, FoodItem>>();
		for (Entry<String, BPTree<Double, FoodItem>> entry : this.indexes.entrySet()) {
			indexesCopy.put(entry.getKey(), entry.getValue());
	    }
		

		for (int i = 0; i < rules.size(); i++) {
			String parts[] = rules.get(i).split(" ");
			String nutrient = parts[0];
			String comparator = parts[1];
			double value = Double.parseDouble(parts[2]);
			
			// update the list
			ans = indexesCopy.get(nutrient).rangeSearch(value, comparator);
			// update the hashMap
			BPTree<Double, FoodItem> BPTreeCalories = new BPTree<Double, FoodItem>(3);
			for (FoodItem item : ans) {
				BPTreeCalories.insert(item.getNutrientValue("calories"), item);
			}
			indexesCopy.put("calories", BPTreeCalories);
			
			BPTree<Double, FoodItem> BPTreeFat = new BPTree<Double, FoodItem>(3);
			for (FoodItem item : ans) {
				BPTreeFat.insert(item.getNutrientValue("fat"), item);
			}
			indexesCopy.put("fat", BPTreeFat);
			
			BPTree<Double, FoodItem> BPTreeCarbs = new BPTree<Double, FoodItem>(3);
			for (FoodItem item : ans) {
				BPTreeCarbs.insert(item.getNutrientValue("carbs"), item);
			}
			indexesCopy.put("carbs", BPTreeCarbs);
			
			BPTree<Double, FoodItem> BPTreeFiber = new BPTree<Double, FoodItem>(3);
			for (FoodItem item : ans) {
				BPTreeFiber.insert(item.getNutrientValue("fiber"), item);
			}
			indexesCopy.put("fiber", BPTreeFiber);
			
			BPTree<Double, FoodItem> BPTreeProtein = new BPTree<Double, FoodItem>(3);
			for (FoodItem item : ans) {
				BPTreeProtein.insert(item.getNutrientValue("protein"), item);
			}
			indexesCopy.put("protein", BPTreeProtein);
		}
		return ans;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see skeleton.FoodDataADT#addFoodItem(skeleton.FoodItem)
	 */
	@Override
	public void addFoodItem(FoodItem foodItem) {
		// add the foodItem to the foodItemList
		this.foodItemList.add(foodItem);
		// add the foodItem to 5 BPtrees
		this.indexes.get("calories").insert(foodItem.getNutrientValue("calories"), foodItem);
		this.indexes.get("fat").insert(foodItem.getNutrientValue("fat"), foodItem);
		this.indexes.get("carbs").insert(foodItem.getNutrientValue("carbs"), foodItem);
		this.indexes.get("fiber").insert(foodItem.getNutrientValue("fiber"), foodItem);
		this.indexes.get("protein").insert(foodItem.getNutrientValue("protein"), foodItem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see skeleton.FoodDataADT#getAllFoodItems()
	 */
	@Override
	public List<FoodItem> getAllFoodItems() {
		return this.foodItemList;
	}

	@Override
	public void saveFoodItems(String filename) {
		// sort the list of food items in ascending order by name
		this.foodItemList.sort((food1, food2) -> {
			return food1.getBrand().compareTo(food2.getBrand());
		});
		// save to the file
		try {
			FileWriter writer = new FileWriter(filename);
			List<String> foodDataString = new ArrayList<String>();
			foodItemList.forEach(item -> {
				foodDataString.add(item.getString());
			});
			for (String str : foodDataString) {
				writer.write(str);
				writer.write(System.getProperty("line.separator"));
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}

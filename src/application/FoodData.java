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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class represents the backend for managing all 
 * the operations associated with FoodItems
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
        // TODO : Complete
    	this.foodItemList = new ArrayList<FoodItem>();
    	this.indexes = new HashMap<String, BPTree<Double, FoodItem>>();
    }
    
    
    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#loadFoodItems(java.lang.String)
     */
    @Override
    public void loadFoodItems(String filePath) {
        // TODO : Complete
    	try {
    	      File inputF = new File(filePath);

    	      InputStream inputFS = new FileInputStream(inputF);

    	      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
    	      // the filter method may need improvement
    	      this.foodItemList = br.lines().filter(data -> data.length() > 11).map(rule -> parser(rule)).collect(Collectors.toList());
    	      
    	      //TODO: construct the HashMap
    	      br.close();
    	} catch(Exception e) {
    		e.printStackTrace();
            System.out.println(e);
    	}
    }
    
    //556540ff5d613c9d5f5935a9,Stewarts_PremiumDarkChocolatewithMintCookieCrunch,calories,280,fat,18,carbohydrate,34,fiber,3,protein,3
    private FoodItem parser(String data) {
//    	System.out.println(data);
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
    	FoodItem ans = new FoodItem(id, itemName, brand, calories, fat, carbs, fiber, protein);
    	return ans;
//    	return null;
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#filterByName(java.lang.String)
     */
    @Override
    public List<FoodItem> filterByName(String substring) {
    	List<FoodItem> result = new ArrayList<FoodItem>();
    	this.foodItemList.forEach(item -> {
    		if (item.getFullName().toLowerCase().contains(substring.toLowerCase())) {
    			result.add(item);
    		}
    	});
        return result;
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#filterByNutrients(java.util.List)
     */
    @Override
    public List<FoodItem> filterByNutrients(List<String> rules) {
        // TODO : Complete
        return null;
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#addFoodItem(skeleton.FoodItem)
     */
    @Override
    public void addFoodItem(FoodItem foodItem) {
    	this.foodItemList.add(foodItem);
    }

    /*
     * (non-Javadoc)
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
			for(String str: foodDataString) {
				writer.write(str);
			}
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
            System.out.println(e);
		}
	}
	
//	// Test
//	public static void main(String[] args) {
//		FoodData foodData = new FoodData();
//		foodData.loadFoodItems("/Users/cxu249/eclipse-workspace/FoodQuery/foodItems.csv");
//		foodData.saveFoodItems("/Users/cxu249/Desktop/hi.txt");
//		List<FoodItem> filtered = foodData.filterByName("Sour");
//		for (FoodItem item: filtered) {
//			System.out.println(item.getString());
//		}
//	}
}

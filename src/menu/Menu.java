package menu;

import java.util.HashMap;
import java.util.Map;

public class Menu {

    private static final Map<String, Double> burgers = new HashMap<>();
    private static final Map<String, Double> toppings = new HashMap<>();
    private static final Map<String, Double> drinks = new HashMap<>();

    private static Menu menu = new Menu();
    private Menu() {

        burgers.put("BEEF", 4.0);
        burgers.put("CHICKEN", 3.5);

        toppings.put("AVOCADO", 1.0);
        toppings.put("CHEESE", 1.0);
        toppings.put("PICKLES", 1.0);
        toppings.put("TOMATO", 1.0);
        toppings.put("BACON", 1.5);
        toppings.put("SALAMI", 1.25);
        toppings.put("HAM", 1.3);

        drinks.put("COKE", 1.5);
        drinks.put("FANTA", 1.5);
        drinks.put("SPRITE", 1.5);
        drinks.put("WATER", 1.0);
    }

    public static Map<String, Double> getBurgers() {
        return new HashMap<>(burgers);
    }

    public static Map<String, Double> getToppings() {
        return new HashMap<>(toppings);
    }

    public static Map<String, Double> getDrinks() {
        return new HashMap<>(drinks);
    }
}

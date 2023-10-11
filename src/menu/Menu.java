package menu;

import java.util.HashMap;
import java.util.Map;


public class Menu {

    private static final Map<String, Double> burgers = new HashMap<>();
    private static final Map<String, Double> regularToppings = new HashMap<>();
    private static final Map<String, Double> veganToppings = new HashMap<>();
    private static final Map<String, Double> drinks = new HashMap<>();

    private static Menu menu = new Menu();

    private Menu() {

        burgers.put("BEEF", 4.0);
        burgers.put("CHICKEN", 3.5);
        burgers.put("VEGAN", 3.5);

        veganToppings.put("AVOCADO", 1.0);
        veganToppings.put("CHEESE", 1.0);
        veganToppings.put("PICKLES", 1.0);
        veganToppings.put("TOMATO", 1.0);

        regularToppings.put("BACON", 1.5);
        regularToppings.put("SALAMI", 1.25);
        regularToppings.put("HAM", 1.3);

        drinks.put("COKE", 1.5);
        drinks.put("FANTA", 1.5);
        drinks.put("SPRITE", 1.5);
        drinks.put("WATER", 1.0);
    }

    public static Map<String, Double> getBurgers() {
        return new HashMap<>(burgers);
    }

    public static Map<String, Double> getDrinks() {
        return new HashMap<>(drinks);
    }

    public static Map<String, Double> getToppings(Burger burgerType) {

        Map<String, Double> toppings = new HashMap<>();

        if (burgerType.getName().contains("VEGAN")) {
            toppings.putAll(veganToppings);
            return toppings;
        } else {
            toppings.putAll(veganToppings);
            toppings.putAll(regularToppings);
            return toppings;
        }
    }

}

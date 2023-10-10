package restaurant;

import menu.Burger;
import menu.Drink;
import menu.Menu;
import menu.MenuItem;

import java.util.*;

public class MenuOrder {

    private final List<MenuItem> order = new ArrayList<>();

    private void placeOrder() {

        System.out.println("Welcome! Press any key to start.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        boolean orderInProgress = true;
        printMenu();
        String input = scanner.nextLine();

        while (orderInProgress) {
            switch (input) {

                case "1" -> {
                    printBurgerMenu();
                    input = scanner.nextLine();
                    Burger burger = createBurger(input);

                    if (burger == null) {
                        System.out.println("Please start over and select only from the available options");
                        printMenu();
                        input = scanner.nextLine();
                        break;
                    }

                    System.out.println("Do you want to add toppings to your burger? YES/NO");
                    if (scanner.nextLine().toUpperCase().contains("Y")) {
                        printToppingsMenu(burger);
                        input = scanner.nextLine();
                        String[] toppingsList = createAndValidateToppings(input, burger);

                        if (toppingsList == null) {
                            printMenu();
                            input = scanner.nextLine();
                            break;
                        } else if (toppingsList.length > 3) {
                            System.out.println("You can add only 3 toppings to a " + burger.getName() + " .");
                            System.out.println("Please start over!");
                            printMenu();
                            input = scanner.nextLine();
                            break;
                        } else {
                            for (String topping : toppingsList) {
                                burger.addTopping(topping, burger);
                            }
                        }
                    }
                    order.add(burger);
                    printMenu();
                    input = scanner.nextLine();
                }

                case "2" -> {
                    System.out.println("We're sorry. We currently don't have any side dishes available");
                    printMenu();
                    input = scanner.nextLine();
                }

                case "3" -> {
                    printDrinksMenu();
                    input = scanner.nextLine();
                    Drink drink = createDrink(input);
                    if (drink == null) {
                        System.out.println("Please start over and select only from the available options");
                        printMenu();
                        input = scanner.nextLine();
                        break;
                    }

                    System.out.println("Please select your drink size. SMALL/REGULAR/LARGE");
                    input = scanner.nextLine();
                    drink.setSize(input);
                    order.add(drink);
                    printMenu();
                    input = scanner.nextLine();
                }

                case "4" -> {
                    printOrderBreakdown();
                    printMenu();
                    input = scanner.nextLine();
                }

                case "5" -> {
                    printTotalOrderPrice();
                    printMenu();
                    input = scanner.nextLine();
                }

                case "6" -> {
                    order.clear();
                    printMenu();
                    input = scanner.nextLine();
                }

                case "7" -> {
                    System.out.println("Enjoy your vegan meal then!");
                    orderInProgress = false;
                }

                default -> {
                    System.out.println("Invalid option. Please select from 1 - 7");
                    input = scanner.nextLine();
                }
            }
        }
    }

    public void runRestaurant() {
        placeOrder();
    }

    private static void printMenu() {
        System.out.println("""
                MENU
                1. Add Burger to order
                2. Add Side Dish to order
                3. Add Drink to order
                4. Print order breakdown
                5. See total value of order
                6. Restart Order
                7. My girlfriend's cooking for me
                """);
    }

    private void printBurgerMenu() {
        Map<String, Double> burgers = Menu.getBurgers();
        System.out.println("Please choose from the following burgers :");
        for (Map.Entry<String, Double> burger : burgers.entrySet()) {
            System.out.println(burger.getKey() + " -> " + burger.getValue() + " $");
        }
    }

    private static void printToppingsMenu(Burger burgerType) {
        Map<String, Double> toppings = Menu.getToppings(burgerType);
        System.out.println("""
                        Please choose from the following toppings:
                        (a maximum of 3 toppings are allowed on each burger)
                """);
        for (Map.Entry<String, Double> topping : toppings.entrySet()) {
            System.out.println(topping.getKey() + " -> " + topping.getValue() + " $");
        }
    }

    private static void printDrinksMenu() {
        Map<String, Double> drinks = Menu.getDrinks();
        System.out.println("Please choose from the following drinks: ");
        for (Map.Entry<String, Double> drink : drinks.entrySet()) {
            System.out.println(drink.getKey() + " -> " + drink.getValue() + " $");
        }
    }

    private void printOrderBreakdown() {
        for (MenuItem item : order) {
            item.printMenuItem();
        }
        System.out.println();
    }

    private void printTotalOrderPrice() {
        double totalPrice = 0.0;
        for (MenuItem item : order) {
            double price = item.getModifiedPrice();
            totalPrice += price;
        }
        System.out.println("TOTAL: " + totalPrice);
        System.out.println();
    }

    private Burger createBurger(String input) {
        input = input.toUpperCase();
        if (Menu.getBurgers().containsKey(input)) {
            return new Burger(input, Menu.getBurgers().get(input));
        }
        return null;
    }

    private Drink createDrink(String input) {
        input = input.toUpperCase();
        if (Menu.getDrinks().containsKey(input)) {
            return new Drink(input, Menu.getDrinks().get(input));
        }
        return null;
    }

    private String[] createAndValidateToppings(String input, Burger burgerType) {
        input = input.toUpperCase();
        String[] toppings = input.split(" ");
        for (String topping : toppings) {
            if (!Menu.getToppings(burgerType).containsKey(topping)) {
                System.out.println("We do not have a " + topping + " topping. Please start over");
                return null;
            }
        }
        return input.split(" ");
    }
}

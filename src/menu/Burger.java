package menu;

import java.util.ArrayList;
import java.util.List;

public class Burger extends MenuItem {

    private final List<Topping> burgerToppings = new ArrayList<>(3);

    public Burger(String name, double price) {
        super("BURGER", name, price);
    }

    @Override
    public String getName() {
        return super.getName() + " BURGER";
    }

    @Override
    public double getModifiedPrice() {
        double price = super.getModifiedPrice();
        for (Topping burgerTopping : burgerToppings) {
            price += burgerTopping.getRegularPrice();
        }

        return price;
    }


    public void addTopping(String input) {
        if (this.burgerToppings.size() < 3 && !this.burgerToppings.contains(new Topping(input, Menu.getToppings().get(input)))) {
            this.burgerToppings.add(new Topping(input, Menu.getToppings().get(input)));
        } else if (this.burgerToppings.contains(new Topping(input, Menu.getToppings().get(input)))) {
            System.out.println("You have already included a " + input.toUpperCase() + " topping");
        } else {
            System.out.println("You can add only " + burgerToppings.size() + " toppings to the " + getName());
        }
    }

    public void removeTopping(String input) {
        this.burgerToppings.remove(new Topping(input, Menu.getToppings().get(input)));
    }

    public void printBurgerItems() {
        printMenuItem(this.getName(), super.getModifiedPrice());
        for (Topping burgerTopping : burgerToppings) {
            burgerTopping.printMenuItem();
        }
    }

    @Override
    public void printMenuItem() {
        printMenuItem("BURGER BREAKDOWN", getModifiedPrice());
        System.out.println("-".repeat(25));
        printBurgerItems();
        System.out.println();
    }
}

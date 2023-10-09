package menu;

public class Drink extends MenuItem {

    public Drink(String name) {
        super("DRINK", name, switch (name.toUpperCase()) {
            case "COKE", "FANTA", "SPRITE" -> 1.5;
            case "WATER" -> 1.0;
            default -> 1.0;
        });
    }
}

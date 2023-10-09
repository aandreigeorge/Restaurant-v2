package menu;

public abstract class MenuItem {

    private final String type;
    private final String name;
    private final double price;
    private String size = "REGULAR";

    public MenuItem(String type, String name, double price) {
        this.type = type.toUpperCase();
        this.name = name.toUpperCase();
        this.price = price;
    }

    public String getName() {
        if (type.equals("SIDE") || type.equals("DRINK")) {
            return size + " " + name;
        }
        return name;
    }

    public double getRegularPrice() {
        return price;
    }

    public double getModifiedPrice() {
        return switch (size) {
            case "SMALL" -> getRegularPrice() - 0.4;
            case "LARGE" -> getRegularPrice() + 1;
            default -> getRegularPrice();
        };
    }

    public void setSize(String size) {
        if ((size.equalsIgnoreCase("SMALL")) || (size.equalsIgnoreCase("REGULAR")) || (size.equalsIgnoreCase("LARGE"))) {
            this.size = size.toUpperCase();
        } else {
            System.out.println("This size option is not valid. We added by default a " + getName());
        }
    }

    public static void printMenuItem(String name, double price) {
        System.out.printf("%15s:%6.2f%n", name, price);
    }

    public void printMenuItem() {
        printMenuItem(getName(), getModifiedPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem menuItem = (MenuItem) o;

        if (!type.equals(menuItem.type)) return false;
        return name.equals(menuItem.name);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

}

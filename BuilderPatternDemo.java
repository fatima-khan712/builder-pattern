/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.builderpatterndemo;

/**
 *
 * @author Rising Star
 */


import java.util.*;

// Interfaces
interface Item {
    String name();
    Packing packing();
    float price();
}

interface Packing {
    String pack();
}

// Packing implementations
class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}

class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}

// Abstract categories
abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}

abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}

abstract class HotDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle(); // Could be replaced with a 'Cup' class
    }

    @Override
    public abstract float price();
}

// Burger types
class VegBurger extends Burger {
    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}

class ChickenBurger extends Burger {
    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}

// Cold drinks
class Coke extends ColdDrink {
    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}

class Pepsi extends ColdDrink {
    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}

class DietCoke extends ColdDrink {
    @Override
    public float price() {
        return 28.0f;
    }

    @Override
    public String name() {
        return "Diet Coke";
    }
}

class DietPepsi extends ColdDrink {
    @Override
    public float price() {
        return 32.0f;
    }

    @Override
    public String name() {
        return "Diet Pepsi";
    }
}

// Tea and decorators
class Tea extends HotDrink {
    @Override
    public float price() {
        return 20.0f;
    }

    @Override
    public String name() {
        return "Plain Tea";
    }
}

abstract class TeaDecorator extends HotDrink {
    protected Item tea;

    public TeaDecorator(Item tea) {
        this.tea = tea;
    }

    @Override
    public Packing packing() {
        return tea.packing();
    }
}

class Sweetener extends TeaDecorator {
    private String sweetenerType;
    private int spoons;

    public Sweetener(Item tea, String sweetenerType, int spoons) {
        super(tea);
        this.sweetenerType = sweetenerType;
        this.spoons = spoons;
    }

    @Override
    public String name() {
        return tea.name() + " + " + sweetenerType + " (" + spoons + " spoons)";
    }

    @Override
    public float price() {
        return tea.price() + (0.5f * spoons);
    }
}

class Cream extends TeaDecorator {
    public Cream(Item tea) {
        super(tea);
    }

    @Override
    public String name() {
        return tea.name() + " + Cream";
    }

    @Override
    public float price() {
        return tea.price() + 5.0f;
    }
}

// Meal class
class Meal {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        float cost = 0.0f;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems() {
        for (Item item : items) {
            System.out.print("Item: " + item.name());
            System.out.print(", Packing: " + item.packing().pack());
            System.out.println(", Price: " + item.price());
        }
    }
}

// MealBuilder
class MealBuilder {
    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        return meal;
    }

    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        return meal;
    }
}

// Main class
public class BuilderPatternDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Meal meal;
        MealBuilder builder = new MealBuilder();

        System.out.println("Welcome to Meal Builder!");
        System.out.print("Enter your meal type ('veg' or 'nonveg'): ");
        String baseChoice = scanner.next().toLowerCase();

        if (baseChoice.equals("veg")) {
            meal = builder.prepareVegMeal();
        } else if (baseChoice.equals("nonveg")) {
            meal = builder.prepareNonVegMeal();
        } else {
            System.out.println("Invalid input! Defaulting to Veg Meal.");
            meal = builder.prepareVegMeal();
        }

        System.out.println("\nWould you like to add a beverage?");
        System.out.println("1. None\n2. Coke\n3. Diet Coke\n4. Pepsi\n5. Diet Pepsi\n6. Tea");
        int drinkChoice = scanner.nextInt();

        switch (drinkChoice) {
            case 2: meal.addItem(new Coke()); break;
            case 3: meal.addItem(new DietCoke()); break;
            case 4: meal.addItem(new Pepsi()); break;
            case 5: meal.addItem(new DietPepsi()); break;
            case 6:
                Item tea = new Tea();
                System.out.print("Add sweetener? (yes/no): ");
                if (scanner.next().equalsIgnoreCase("yes")) {
                    System.out.print("Sweetener type (Sugar/Stevia): ");
                    String type = scanner.next();
                    System.out.print("Number of spoons: ");
                    int spoons = scanner.nextInt();
                    tea = new Sweetener(tea, type, spoons);
                }

                System.out.print("Add cream? (yes/no): ");
                if (scanner.next().equalsIgnoreCase("yes")) {
                    tea = new Cream(tea);
                }

                meal.addItem(tea);
                break;
        }

        System.out.println("\nYour Meal:");
        meal.showItems();
        System.out.println("Total Cost: " + meal.getCost());
        scanner.close();
    }
}

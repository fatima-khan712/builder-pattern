# builder-pattern
Overview
This project is a Java-based console application that demonstrates the use of the Builder and Decorator design patterns. It allows users to build a customizable meal by choosing between vegetarian and non-vegetarian burgers and selecting a variety of drink options, including cold and hot beverages with add-ons.

Key Features
The application allows users to:
Choose between a Veg Burger or Chicken Burger.
Add a Cold Drink (Coke, Diet Coke, Pepsi, or Diet Pepsi) or a Hot Drink (Tea).
Customize tea with optional sweeteners (like sugar or stevia) and cream.
View detailed meal information including item names, packing type, and individual prices.
Get the total cost of the entire meal.

Design Patterns Used
This application incorporates two fundamental design patterns:
Builder Pattern
Used to construct complex Meal objects step by step. The MealBuilder class encapsulates the construction logic, allowing users to create different types of meals without changing the code structure.

Decorator Pattern
Used to dynamically add additional features to the Tea item, such as sweeteners and cream. This promotes flexibility and adherence to the Open/Closed Principle.

To run the program, first make sure you have Java installed on your system. Open a terminal or command prompt, navigate to the directory containing the source file BuilderPatternDemo.java, and compile the code using the command javac BuilderPatternDemo.java. This will generate the necessary .class files. After successful compilation, run the program by typing java BuilderPatternDemo. The application will start in the console, prompting you to enter your meal type (either "veg" or "nonveg"), then offering you beverage options including cold drinks (Coke, Diet Coke, Pepsi, Diet Pepsi) or tea. If you choose tea, you will be asked if you want to add a sweetener (like sugar or stevia) and cream. After making your selections, the program will display the details of your meal including each item’s name, packing type, individual price, and the total cost.









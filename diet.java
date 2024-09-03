import java.util.Scanner;

public class Knapsack {

    public static void Meal(int[] weights, int[] values, int maxCalories, String req) {

        int n = weights.length;
        int[][] dp = new int[n + 1][maxCalories + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= maxCalories; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(values[i - 1] + dp[i - 1][j - weights[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        int maxValue = dp[n][maxCalories];
        System.out.println("Total protein in " + req + " is: " + maxValue);
        System.out.println("Selected food items:");

        int remainingCalories = maxCalories;

        for (int i = n; i > 0 && maxValue > 0; i--) {
            String name = "";

            switch (i) {
                case 1:
                    name = req.equals("Breakfast") ? "1 Cup Milk" : (req.equals("Lunch") ? "Arhar" : "Chicken Curry");
                    break;
                case 2:
                    name = req.equals("Breakfast") ? "3 Cashews" : (req.equals("Lunch") ? "Roti" : "Roti");
                    break;
                case 3:
                    name = req.equals("Breakfast") ? "4 Almonds" : (req.equals("Lunch") ? "Low Fat Curd" : "Salad");
                    break;
                case 4:
                    name = req.equals("Breakfast") ? "2 Egg Brown Bread" : (req.equals("Lunch") ? "Potato Curry" : "Rice");
                    break;
                case 5:
                    name = req.equals("Breakfast") ? "Fruits" : (req.equals("Lunch") ? "Rice" : "Egg");
                    break;
                case 6:
                    name = req.equals("Breakfast") ? "2 Walnuts" : (req.equals("Lunch") ? "Salad" : "Paneer");
                    break;
            }

            if (maxValue != dp[i - 1][remainingCalories]) {
                System.out.println(name + " (Calories: " + weights[i - 1] + ", Protein: " + values[i - 1] + ")");
                maxValue -= values[i - 1];
                remainingCalories -= weights[i - 1];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Breakfast
        int[] breakfastWeights = {163, 25, 30, 400, 230, 30};
        int[] breakfastValues = {8, 1, 1, 20, 6, 1};

        // Lunch
        int[] lunchWeights = {660, 256, 67, 187, 100, 36};
        int[] lunchValues = {44, 9, 5, 3, 3, 1};

        // Dinner
        int[] dinnerWeights = {133, 256, 36, 120, 100, 30};
        int[] dinnerValues = {13, 10, 1, 1, 2, 1};

        System.out.println("Hello, please enter your name:");
        String name = sc.nextLine();

        System.out.println("Hello " + name + ", please enter your age:");
        int age = sc.nextInt();

        System.out.println("Hello " + name + ", please enter your weight:");
        double weight = sc.nextDouble();

        System.out.println("Hello " + name + ", please enter your height in meters:");
        double height = sc.nextDouble();

        double BMI = weight / (height * height);

        if (BMI < 18.5) {
            System.out.println("Hello " + name + ", your BMI is " + BMI + ". \nAccording to your BMI, you are underweight.");
            System.out.println("Dear " + name + ", your diet for weight gain is:");
            System.out.println();

            System.out.println("Breakfast: ");
            Meal(breakfastWeights, breakfastValues, 800, "Breakfast");
            System.out.println();

            System.out.println("Lunch: ");
            Meal(lunchWeights, lunchValues, 1200, "Lunch");
            System.out.println();

            System.out.println("Dinner: ");
            Meal(dinnerWeights, dinnerValues, 425, "Dinner");

        } else if (BMI >= 18.5 && BMI < 25) {
            System.out.println("Hello " + name + ", your BMI is " + BMI + ". \nAccording to your BMI, you are healthy.");
            System.out.println("Dear " + name + ", you are healthy, so your diet is good. Please continue your regular diet.");
        } else if (BMI >= 25.0) {
            System.out.println("Hello " + name + ", your BMI is " + BMI + ". According to your BMI, you are overweight.");
            System.out.println("Dear " + name + ", your diet for weight loss is:");
            System.out.println();

            System.out.println("Breakfast: ");
            Meal(breakfastWeights, breakfastValues, 700, "Breakfast");
            System.out.println();

            System.out.println("Lunch: ");
            Meal(lunchWeights, lunchValues, 500, "Lunch");
            System.out.println();

            System.out.println("Dinner: ");
            Meal(dinnerWeights, dinnerValues, 400, "Dinner");
        }

        sc.close();
    }
}
import java.util.Scanner;

public class CustomSortIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int size = scanner.nextInt();
        int[] numbers = new int[size];

        System.out.println("Enter the elements:");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println("\nInitial array:");
        printArray(numbers);

        // Iteration: Sorting pass by pass (selection sort)
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = numbers[minIndex];
            numbers[minIndex] = numbers[i];
            numbers[i] = temp;

            System.out.println("After pass " + (i + 1) + ":");
            printArray(numbers);
        }

        System.out.println("\nFinal sorted array:");
        printArray(numbers);

        scanner.close();
    }

    // Utility method to print array
    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

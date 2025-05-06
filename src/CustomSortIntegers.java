import java.util.*;

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

        radixSort(numbers);

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

    // Get maximum value in array
    public static int getMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // Radix sort
    public static void radixSort(int[] array) {
        int max = getMax(array);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp);
            int pass = (int)(Math.log10(exp)) + 1;
            System.out.println("After Pass " + pass + ":");

            printArray(array);
        }
    }

    // Counting sort for a specific digit
    public static void countingSortByDigit(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Count occurrences of digits
        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        // Change count[i] so that count[i] contains the actual
        // position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        // Copy the output array to array[], so that array[] now
        // contains sorted numbers according to the current digit
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }
}

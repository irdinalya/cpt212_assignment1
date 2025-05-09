import java.util.*;

public class RadixSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get number of elements from user
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        // Read the numbers into integer array
        int[] inputArray = new int[n];
        System.out.println("Enter the numbers:");
        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextInt();
        }

        //Determine the max num of digits
        int maxDigits = getMaxDigits(inputArray);

        // Pad numbers with zeros and convert to string array
        String[] workingArray = new String[n];
        for (int i = 0; i < n; i++) {
            workingArray[i] = String.format("%0" + maxDigits + "d", inputArray[i]);
        }

        // Bucket arrays for alternating sorting passes
        String[][] bucketA = new String[10][n];
        String[][] bucketB = new String[10][n];
        boolean useA = true; //Track bucket to use for current pass

        //Radix sort from LSD
        for (int digitIndex = maxDigits - 1; digitIndex >= 0; digitIndex--) {
            // Clear buckets
            for (int i = 0; i < 10; i++) {
                Arrays.fill(bucketA[i], null);
                Arrays.fill(bucketB[i], null);
            }

            // Put into current bucket
            for (String s : workingArray) {
                int digit = Character.getNumericValue(s.charAt(digitIndex));
                String[][] currentBucket = useA ? bucketA : bucketB;

                // Find first empty spot and insert
                for (int i = 0; i < n; i++) {
                    if (currentBucket[digit][i] == null) {
                        currentBucket[digit][i] = s;
                        break;
                    }
                }
            }

            // Collect from current bucket
            int index = 0;
            String[][] currentBucket = useA ? bucketA : bucketB;

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < n; j++) {
                    if (currentBucket[i][j] != null) {
                        workingArray[index++] = currentBucket[i][j];
                    }
                }
            }

            useA = !useA; // Swap buckets for next round

            // Show result after each pass
            System.out.println("After pass " + (maxDigits - digitIndex) + ": " + Arrays.toString(workingArray));
        }

        // Convert result back to int
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = Integer.parseInt(workingArray[i]);
        }

        System.out.println("Sorted result: " + Arrays.toString(sorted));
    }

    // Get number of digits of the largest number
    private static int getMaxDigits(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) max = num;
        }
        return Integer.toString(max).length();
    }
}

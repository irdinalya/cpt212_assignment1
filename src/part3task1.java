import java.util.*;

public class RadixSort {
    static int primitiveOps=0;
  
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get number of elements from user
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();
        primitiveOps++; //input operation

        // Read the numbers into integer array
        int[] inputArray = new int[n];
        System.out.println("Enter the numbers:");
        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextInt();
            primitiveOps+=3; // loop, input and assignment insert operation
        }

        //Determine the max num of digits
        int maxDigits = getMaxDigits(inputArray);
        primitiveOps++; // funtion call

        // Pad numbers with zeros and convert to string array
        String[] workingArray = new String[n];
        for (int i = 0; i < n; i++) {
            workingArray[i] = String.format("%0" + maxDigits + "d", inputArray[i]);
            primitveOps+=4; // loop, format, assign and access array operation
        }

        // Bucket arrays for alternating sorting passes
        String[][] bucketA = new String[10][n];
        String[][] bucketB = new String[10][n];
        boolean useA = true; //Track bucket to use for current pass
        primitiveOps+=3; //assignment operation

        //Radix sort from LSD
        for (int digitIndex = maxDigits - 1; digitIndex >= 0; digitIndex--) {
            // Clear buckets
            for (int i = 0; i < 10; i++) {
                Arrays.fill(bucketA[i], null);
                Arrays.fill(bucketB[i], null);
                primitive+=2; // loop and fill operation
            }

            // Put into current bucket
            for (String s : workingArray) {
                int digit = Character.getNumericValue(s.charAt(digitIndex));
                String[][] currentBucket = useA ? bucketA : bucketB;
                primitiveOps+=4; //loop, charAt, digit, ternary operation

                // Find first empty spot and insert
                for (int i = 0; i < n; i++) {
                    if (currentBucket[digit][i] == null) {
                        currentBucket[digit][i] = s;
                        primitiveOps+=4; // loop, condition, acess and assign operation
                        break;
                    }
                    primitiveOps++; // loop operation
                }
            }

            // Collect from current bucket
            int index = 0;
            String[][] currentBucket = useA ? bucketA : bucketB;
            primitiveOps+=2; // assign operation

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < n; j++) {
                    if (currentBucket[i][j] != null) {
                        workingArray[index++] = currentBucket[i][j];
                        primitiveOps+=5; // loop, condition, access, assign, and add index operation
                    }
                }
                primitiveOps++; // outer loop
            }

            useA = !useA; // Swap buckets for next round
            primitiveOps++; // toggle operation

            // Show result after each pass
            System.out.println("After pass " + (maxDigits - digitIndex) + ": " + Arrays.toString(workingArray));
            primitiveOps+=3; // arithmetic, print and access array operation
        }

        // Convert result back to int
        int[] sorted = new int[n];
        primitiveOps++; // array operation
        for (int i = 0; i < n; i++) {
            sorted[i] = Integer.parseInt(workingArray[i]);
            primitiveOps+=4;  // loop, parse, access aarray and assign operation
        }

        System.out.println("Sorted result: " + Arrays.toString(sorted));
        primitiveOps++; // print operation
    }

    // Get number of digits of the largest number
    private static int getMaxDigits(int[] array) {
        int max = array[0];
        int primitiveOpsLocal=1; // array operation
        for (int num : array) {
            if (num > max) max = num;
            primitiveOpsLocal+=2; // loop and compare operation
        }

        primitiveOpsLocal+=2; // conversion
        primitiveOps+=primitiveOpsLocal // update total

        System.out.println("Primitve Operations:" + primitiveOps);
        return Integer.toString(max).length();
    }
}

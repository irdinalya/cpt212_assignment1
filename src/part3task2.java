import java.util.*;

class RadixSortWordsLSD {

    static int primitiveOps = 0; // Counter for primitive operations

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of words from user
        System.out.print("Enter number of words: ");
        int n = scanner.nextInt();
        primitiveOps++; // Reading int input
        scanner.nextLine(); // Consume the leftover newline character
        primitiveOps++; // Method call

        String[] inputArray = new String[n]; // Stores original input words
        primitiveOps++; // Array creation
        int maxLen = 0; // Will store the length of the longest word
        primitiveOps++; // Variable init

        // Input words and determine the maximum length
        System.out.println("Enter the words:");
        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextLine().toLowerCase(); // Convert to lowercase
            primitiveOps += 4; // Loop, assignment, method call, lowercase
            if (inputArray[i].length() > maxLen) {
                maxLen = inputArray[i].length(); // Update max length
                primitiveOps += 3; // Comparison + assignment + length()
            }
        }

        // Pad shorter words with spaces to match with length of the longest word
        String[] workingArray = new String[n];
        primitiveOps++; // Array creation
        for (int i = 0; i < n; i++) {
            workingArray[i] = String.format("%-" + maxLen + "s", inputArray[i]);
            primitiveOps += 4; // Loop, format, assignment, string op
        }

        // Initialize two 2D arrays (buckets) for characters: a-z (1-26), space (0)
        String[][] bucketA = new String[27][n];
        String[][] bucketB = new String[27][n];
        boolean useA = true;
        primitiveOps += 3; // 2 array creations + boolean init

        for (int digitIndex = maxLen - 1; digitIndex >= 0; digitIndex--) {
            primitiveOps += 2; // loop control & arithmetic

            // Clear buckets
            for (int i = 0; i < 27; i++) {
                Arrays.fill(bucketA[i], null);
                Arrays.fill(bucketB[i], null);
                primitiveOps += 3; // loop + fill + array access
            }

            // Distribute to buckets
            for (String s : workingArray) {
                char c = s.charAt(digitIndex);
                primitiveOps += 2; // char access + assignment
                int bucketIndex = (c == ' ') ? 0 : (c - 'a' + 1);
                primitiveOps += 3; // comparison, arithmetic, assignment
                String[][] currentBucket = useA ? bucketA : bucketB;
                primitiveOps += 2; // ternary + assignment

                for (int i = 0; i < n; i++) {
                    if (currentBucket[bucketIndex][i] == null) {
                        currentBucket[bucketIndex][i] = s;
                        primitiveOps += 4; // condition, array access, assignment, break
                        break;
                    }
                    primitiveOps += 2; // loop check + condition
                }
            }

            // Collect from buckets
            int index = 0;
            primitiveOps++; // assignment
            String[][] currentBucket = useA ? bucketA : bucketB;
            primitiveOps++; // ternary

            for (int i = 0; i < 27; i++) {
                for (int j = 0; j < n; j++) {
                    if (currentBucket[i][j] != null) {
                        workingArray[index++] = currentBucket[i][j];
                        primitiveOps += 5; // condition, access, assignment, increment, loop
                    }
                    primitiveOps += 2; // loop + condition
                }
                primitiveOps++; // outer loop increment
            }

            useA = !useA; // Alternate between bucketA and bucketB
            primitiveOps++; // toggle

            System.out.println("After pass " + (maxLen - digitIndex) + ": " + Arrays.toString(workingArray));
            primitiveOps += 3; // arithmetic, array access, print
        }

        // Trim trailing spaces before printing final result
        for (int i = 0; i < n; i++) {
            workingArray[i] = workingArray[i].trim();
            primitiveOps += 3; // loop, access, method
        }

        System.out.println("Sorted result: " + Arrays.toString(workingArray));
        primitiveOps += 2; // array access + print

        // Final primitive op count
        System.out.println("Total Primitive Operations: " + primitiveOps);
    }
}

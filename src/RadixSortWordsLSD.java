import java.util.*;

class RadixSortWordsLSD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of words from user
        System.out.print("Enter number of words: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline character

        String[] inputArray = new String[n]; // Stores original input words
        int maxLen = 0; // Will store the length of the longest word

        // Input words and determine the maximum length
        System.out.println("Enter the words:");
        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextLine().toLowerCase(); // // Convert to lowercase
            if (inputArray[i].length() > maxLen) {
                maxLen = inputArray[i].length(); // Update max length if needed
            }
        }

        // Pad shorter words with spaces to match with length of the longest word
        String[] workingArray = new String[n];
        for (int i = 0; i < n; i++) {
            workingArray[i] = String.format("%-" + maxLen + "s", inputArray[i]); // padding with space
        }

        // Initialize two 2D arrays (buckets) for characters: a-z (1-26), space (0)
        String[][] bucketA = new String[27][n];
        String[][] bucketB = new String[27][n];
        boolean useA = true;

        for (int digitIndex = maxLen - 1; digitIndex >= 0; digitIndex--) {
            // Clear buckets
            for (int i = 0; i < 27; i++) {
                Arrays.fill(bucketA[i], null);
                Arrays.fill(bucketB[i], null);
            }

            // Distribute to buckets
            for (String s : workingArray) {
                char c = s.charAt(digitIndex);
                int bucketIndex = (c == ' ') ? 0 : (c - 'a' + 1); // space = 0, a = 1, ..., z = 26
                String[][] currentBucket = useA ? bucketA : bucketB;

                for (int i = 0; i < n; i++) {
                    if (currentBucket[bucketIndex][i] == null) {
                        currentBucket[bucketIndex][i] = s;
                        break;
                    }
                }
            }

            // Collect from buckets
            int index = 0;
            String[][] currentBucket = useA ? bucketA : bucketB;
            for (int i = 0; i < 27; i++) {
                for (int j = 0; j < n; j++) {
                    if (currentBucket[i][j] != null) {
                        workingArray[index++] = currentBucket[i][j];
                    }
                }
            }

            useA = !useA; // Alternate between bucketA and bucketB
            System.out.println("After pass " + (maxLen - digitIndex) + ": " + Arrays.toString(workingArray));
        }

        // Trim trailing spaces before printing final result
        for (int i = 0; i < n; i++) {
            workingArray[i] = workingArray[i].trim();
        }

        System.out.println("Sorted result: " + Arrays.toString(workingArray));
    }
}

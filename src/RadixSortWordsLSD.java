import java.util.*;

class RadixSortWordsLSD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input words
        System.out.print("Enter number of words: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String[] inputArray = new String[n];
        System.out.println("Enter the words:");
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextLine().toLowerCase();
            if (inputArray[i].length() > maxLen) {
                maxLen = inputArray[i].length();
            }
        }

        // Pad with '{' to make all same length
        String[] workingArray = new String[n];
        for (int i = 0; i < n; i++) {
            workingArray[i] = String.format("%-" + maxLen + "s", inputArray[i]).replace(' ', '{');
        }

        // Buckets: 0-25 for a-z, 26 for '{'
        String[][] bucketA = new String[27][n];
        String[][] bucketB = new String[27][n];
        boolean useA = true;

        for (int digitIndex = maxLen - 1; digitIndex >= 0; digitIndex--) {
            // Clear buckets
            for (int i = 0; i < 27; i++) {
                Arrays.fill(bucketA[i], null);
                Arrays.fill(bucketB[i], null);
            }

            // Distribute to bucket
            for (String s : workingArray) {
                char c = s.charAt(digitIndex);
                int bucketIndex = (c == '{') ? 26 : (c - 'a');
                String[][] currentBucket = useA ? bucketA : bucketB;

                for (int i = 0; i < n; i++) {
                    if (currentBucket[bucketIndex][i] == null) {
                        currentBucket[bucketIndex][i] = s;
                        break;
                    }
                }
            }

            // Collect from bucket
            int index = 0;
            String[][] currentBucket = useA ? bucketA : bucketB;
            for (int i = 0; i < 27; i++) {
                for (int j = 0; j < n; j++) {
                    if (currentBucket[i][j] != null) {
                        workingArray[index++] = currentBucket[i][j];
                    }
                }
            }

            useA = !useA; // Swap bucket
            System.out.println("After pass " + (maxLen - digitIndex) + ": " + Arrays.toString(workingArray));
        }

        // Remove padding before printing result
        for (int i = 0; i < n; i++) {
            workingArray[i] = workingArray[i].replaceAll("\\{+$", ""); // remove trailing '{'
        }

        System.out.println("Sorted result: " + Arrays.toString(workingArray));
    }
}

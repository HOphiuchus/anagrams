package io.beyonnex.anagrams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static io.beyonnex.anagrams.AnagramUtil.*;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            logger.info("Enter command ([1]: input anagrams or [2]: output anagrams) or [q] to quit:");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("q")) {
                break;
            }

            switch (command) {
                case "1":
                    logger.info("Enter 1st string"); // string could contain spaces， so we could not use “space” as delimiter
                    String str1 = scanner.nextLine().trim();
                    logger.info("Enter 2nd string");
                    String str2 = scanner.nextLine().trim();
                    checkAndStoreAnagram(str1, str2);
                    break;
                case "2":
                    logger.info("Enter a string to find its anagrams:");
                    String inputString = scanner.nextLine().trim();
                    findAndOutputAnagrams(inputString);
                    break;

                default:
                    logger.info("Invalid command. Please enter '1' or '2'.");
            }
        }
        scanner.close();
    }


}
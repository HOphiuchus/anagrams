package io.beyonnex.anagrams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class AnagramUtil {

    private static final String NON_ALPHA_NUMBER_PATTERN = "[^a-zA-Z0-9]";
    private static final Logger logger = LoggerFactory.getLogger(AnagramUtil.class);
    // can be replaced with ConcurrentHashMap if thread safety is required
    private static final Map<String, List<String>> anagramMap = new HashMap<>();

    private AnagramUtil() {
    }

    public static Map<String, List<String>> getAnagramMap() {
        return anagramMap;
    }

    public static void checkAndStoreAnagram(String str1, String str2) {
        if (str1.isEmpty() || str2.isEmpty()) {
            logger.info("Please enter valid strings.");
            return;
        }
        if (areAnagrams(str1, str2)) {
            addToAnagramMap(str1);
            addToAnagramMap(str2);
            logger.info("** [{}] and [{}] ARE anagrams.", str1, str2);
        } else {
            logger.info("** [{}] and [{}] are NOT anagrams.", str1, str2);
        }
    }

    static boolean areAnagrams(String str1, String str2) {
        str1 = str1.replaceAll(NON_ALPHA_NUMBER_PATTERN, "");
        str2 = str2.replaceAll(NON_ALPHA_NUMBER_PATTERN, "");
        if (str1.isEmpty() || str2.isEmpty()) {
            return false;
        }
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    private static void addToAnagramMap(String str) {
        String anagramKey = sortAndFormatString(str);
        anagramMap.putIfAbsent(anagramKey, new ArrayList<>());
        anagramMap.get(anagramKey).add(str);

    }

    public static void findAndOutputAnagrams(String str) {
        String sortedKey = sortAndFormatString(str);
        if (anagramMap.containsKey(sortedKey)) {
            if (anagramMap.get(sortedKey).contains(str)) {
                logger.info("** [{}] FOUND in anagrams", str);
                if (logger.isInfoEnabled()) {
                    logger.info("** Anagrams for [{}]:{}", str, anagramMap.get(sortedKey).stream().map(x -> "[" + x + "]").collect(Collectors.joining()));
                }
            } else {
                logger.info("** [{}] NOT found in anagrams", str);
                if (logger.isInfoEnabled()) {
                    logger.info("** Anagrams for [{}]:{}", str, anagramMap.get(sortedKey).stream().filter(x -> !x.equals(str)).map(x -> "[" + x + "]").collect(Collectors.joining()));
                }
            }
        } else {
            logger.info("No anagrams found for [{}].", str);
        }
    }

    // Sort the characters in a string alphabetically
    private static String sortAndFormatString(String str) {
        str = str.replaceAll(NON_ALPHA_NUMBER_PATTERN, "");
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}

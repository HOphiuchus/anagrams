package io.beyonnex.anagrams;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AnagramUtilTest {

    @Test
    void testAreAnagram() {
        assertTrue(AnagramUtil.areAnagrams("listen", "silent"));
        assertTrue(AnagramUtil.areAnagrams("triangle", "integral"));
        assertFalse(AnagramUtil.areAnagrams("hello", "world"));
        assertFalse(AnagramUtil.areAnagrams("hello", ""));
        assertFalse(AnagramUtil.areAnagrams("", "world"));
        assertFalse(AnagramUtil.areAnagrams("", ""));
    }

    @Test
    void testOutput(){
        AnagramUtil.checkAndStoreAnagram("abc", "cba");
        AnagramUtil.checkAndStoreAnagram("abc", "bca");
        assertThat(AnagramUtil.getAnagramMap().get("abc")).hasSameElementsAs(List.of("abc", "cba", "bca"));
    }
}
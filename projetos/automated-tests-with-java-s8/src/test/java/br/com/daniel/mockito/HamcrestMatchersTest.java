package br.com.daniel.mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class HamcrestMatchersTest {

    @Test
    void testHamcrestMatchers() {

        // Given
        List<Integer> scores = Arrays.asList(99, 100, 101, 105);

        // When & Then
        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(100, 105));
        assertThat(scores, everyItem(greaterThan(98)));
        assertThat(scores, everyItem(lessThan(106)));

        // Ckeck Strings
        assertThat("", is(emptyString()));
        assertThat(null, is(emptyOrNullString()));

        // Arrays
        Integer[] myArray = { 1, 2, 3 };
        assertThat(myArray, arrayWithSize(3));
        assertThat(myArray, arrayContaining(1, 2, 3));
        assertThat(myArray, arrayContainingInAnyOrder(3, 1, 2));

    }

}

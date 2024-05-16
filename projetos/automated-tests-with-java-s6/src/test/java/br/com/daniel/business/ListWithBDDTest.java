package br.com.daniel.business;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ListWithBDDTest {

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Test size list with 10")
    @Test
    void testMockingWithList_When_SizeIsCalled_ShouldReturn10() {
        // Given / Arrange
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10);
        // When / Act & Then / Assert
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(10));
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Test size list with multiple values")
    @Test
    void testMockingWithList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        // Given / Arrange
        List<?> list = mock(List.class);
        given(list.size()).willReturn(10).willReturn(20).willReturn(30);
        // When / Act & Then / Assert
        assertThat(list.size(), is(10));
        assertThat(list.size(), is(20));
        assertThat(list.size(), is(30));
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Test get list with value Daniel")
    @Test
    void testMockingWithList_When_GetIsCalled_ShouldReturnDaniel() {
        // Given / Arrange
        var list = mock(List.class);
        given(list.get(0)).willReturn("Daniel");
        // When / Act & Then / Assert
        assertThat(list.get(0), is("Daniel"));
        assertNull(list.get(1));
    }

    @DisplayName("Test get list with value Daniel with argument matcher")
    @Test
    void testMockingWithList_When_GetIsCalledWithArgumentMatcher_ShouldReturnDaniel() {
        // Given / Arrange
        var list = mock(List.class);
        given(list.get(anyInt())).willReturn("Daniel");
        // When / Act & Then / Assert
        assertThat(list.get(anyInt()), is("Daniel"));
        assertThat(list.get(anyInt()), is("Daniel"));
    }

}

package br.com.daniel.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListTest {

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Test size list with 10")
    @Test
    void testMockingWithList_When_SizeIsCalled_ShouldReturn10() {
        // Given / Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10);
        // When / Act & Then / Assert
        assertEquals(10, list.size());
        assertEquals(10, list.size());
        assertEquals(10, list.size());
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Test size list with multiple values")
    @Test
    void testMockingWithList_When_SizeIsCalled_ShouldReturnMultipleValues() {
        // Given / Arrange
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20).thenReturn(30);
        // When / Act & Then / Assert
        assertEquals(10, list.size());
        assertEquals(20, list.size());
        assertEquals(30, list.size());
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Test get list with value Daniel")
    @Test
    void testMockingWithList_When_GetIsCalled_ShouldReturnDaniel() {
        // Given / Arrange
        var list = mock(List.class);
        when(list.get(0)).thenReturn("Daniel");
        // When / Act & Then / Assert
        assertEquals("Daniel", list.get(0));
        assertNull(list.get(1));
    }

    @DisplayName("Test get list with value Daniel with argument matcher")
    @Test
    void testMockingWithList_When_GetIsCalledWithArgumentMatcher_ShouldReturnDaniel() {
        // Given / Arrange
        var list = mock(List.class);
        when(list.get(anyInt())).thenReturn("Daniel");
        // When / Act & Then / Assert
        assertEquals("Daniel", list.get(anyInt()));
        assertEquals("Daniel", list.get(anyInt()));
    }

    @DisplayName("Test get list with value Daniel with argument matcher")
    @Test
    void testMockingWithList_When_ThrowAnException() {
        // Given / Arrange
        var list = mock(List.class);
        when(list.get(anyInt())).thenThrow(new RuntimeException("Foo Bar!!"));
        // When / Act & Then / Assert
        assertThrows(RuntimeException.class,
                () -> {
                    list.get(anyInt());
                }, () -> "Should have an throw RuntimeException");
    }

}

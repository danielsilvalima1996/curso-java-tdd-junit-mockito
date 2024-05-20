package br.com.daniel.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class OrderServiceTest {

    private final UUID defaultUiid = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");
    private final OrderService service = new OrderService();
    private final LocalDateTime defaultDateTime = LocalDateTime.of(2024, 5, 20, 0, 30, 40);

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Should Include Random Order Id When No Order Id Exists")
    @Test
    void testShouldIncludeRandomOrderId_When_NoOrderIdExists() {
        // Given / Arrange
        try (MockedStatic<UUID> mockedUuid = mockStatic(UUID.class)) {
            mockedUuid.when(UUID::randomUUID).thenReturn(defaultUiid);

            // When / Act

            Order result = service.createOrder("MacBook Pro", 2L, null);
            // Then / Assert
            assertEquals(defaultUiid.toString(), result.getId());
        }

    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("Should Include Current DateTime When created a new Date Time")
    @Test
    void testShouldIncludeCurrentDateTime_When_CreatedANewDateTime() {
        // Given / Arrange
        try (MockedStatic<LocalDateTime> mockedLocalDateTime = mockStatic(LocalDateTime.class)) {
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultDateTime);

            // When / Act

            Order result = service.createOrder("MacBook Pro", 2L, null);
            // Then / Assert
            assertEquals(defaultDateTime, result.getCreationDate());
        }

    }
    
}

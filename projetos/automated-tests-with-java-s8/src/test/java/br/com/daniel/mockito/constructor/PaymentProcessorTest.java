package br.com.daniel.mockito.constructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mockConstructionWithAnswer;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

class PaymentProcessorTest {

    void mockedObjectConstructor() {
        // Given / Arrange
        try (MockedConstruction<PaymentProcessor> mocked = mockConstruction(PaymentProcessor.class)) {

            PaymentProcessor paymentProcessor = new PaymentProcessor();
            when(paymentProcessor.chargeCustomer(anyString(),
                    any(BigDecimal.class))).thenReturn(BigDecimal.TEN);

            assertEquals(BigDecimal.TEN, paymentProcessor
                    .chargeCustomer("42", BigDecimal.valueOf(42)));
        }
    }

    @Test
    void mockedDifferentsObjectsConstructor() {
        // Given / Arrange
        try (MockedConstruction<PaymentProcessor> mocked = mockConstruction(PaymentProcessor.class)) {

            PaymentProcessor firstInstance = new PaymentProcessor("PayPal", BigDecimal.TEN);
            PaymentProcessor secondInstance = new PaymentProcessor(true, "PayPal", BigDecimal.TEN);
            when(firstInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);
            when(secondInstance.chargeCustomer(anyString(), any(BigDecimal.class))).thenReturn(BigDecimal.TEN);

            assertEquals(BigDecimal.TEN, firstInstance.chargeCustomer("42", BigDecimal.valueOf(42)));
            assertEquals(BigDecimal.TEN, secondInstance.chargeCustomer("42", BigDecimal.valueOf(42)));
            assertEquals(2, mocked.constructed().size());
        }
    }

    @Test
    void mockedDifferentsObjectsConstructorWithAnswer() {
        // Given / Arrange
        try (MockedConstruction<PaymentProcessor> mocked = 
            mockConstructionWithAnswer(PaymentProcessor.class,
                invocation -> new BigDecimal("500.00"),
                invocation -> new BigDecimal("42.00"))) {

            PaymentProcessor firstInstance = new PaymentProcessor();
            PaymentProcessor secondInstance = new PaymentProcessor();
            PaymentProcessor thirdInstance = new PaymentProcessor();

            assertEquals(new BigDecimal("500.00"), firstInstance.chargeCustomer("42", BigDecimal.ZERO));
            assertEquals(new BigDecimal("42.00"), secondInstance.chargeCustomer("42", BigDecimal.ZERO));
            assertEquals(new BigDecimal("42.00"), thirdInstance.chargeCustomer("42", BigDecimal.ZERO));
            
        }
    }

}

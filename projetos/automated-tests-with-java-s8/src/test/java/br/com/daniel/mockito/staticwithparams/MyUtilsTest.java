package br.com.daniel.mockito.staticwithparams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class MyUtilsTest {

    @Test
    void shouldMockStaticMethodWithParams() {

        try(MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class)) {
            mockedStatic.when(
                () -> MyUtils.getWelcomeMessage(
                    eq("Daniel"), 
                    anyBoolean()))
            .thenReturn("Howdy Daniel");

            String result = MyUtils.getWelcomeMessage("Daniel", false);

            assertEquals("Howdy Daniel", result);

        }

    }

}

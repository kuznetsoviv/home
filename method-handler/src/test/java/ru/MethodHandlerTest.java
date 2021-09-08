package ru;


import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A method handler is a typed, directly executable reference to an underlying method, constructor,
 * field or similar low-level operation, with optional transformations of arguments or return values.
 * Use cases:
 */
class MethodHandlerTest {

    /**
     * Example of getting LOGGER with method handler. It is more convenient way because you don't need to use class name.
     */
    private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    @Test
    void concatMethodHandler() throws Throwable {
        LOGGER.info(() -> "Test concat method handler");
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
        MethodType mt = MethodType.methodType(String.class, String.class);
        MethodHandle concatMH = publicLookup.findVirtual(String.class, "concat", mt);
        String output = (String) concatMH.invoke("java", "script");
        assertEquals("javascript", output);
    }

    @Test
    void curringMethodHandler() throws Throwable {
        LOGGER.info(() -> "Test curring method handler");
        MethodType mt = MethodType.methodType(String.class, String.class);
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
        MethodHandle concatMH = publicLookup.findVirtual(String.class, "concat", mt);
        MethodHandle bindedConcatMH = concatMH.bindTo("Hello ");
        assertEquals("Hello World!", bindedConcatMH.invoke("World!"));
    }

}

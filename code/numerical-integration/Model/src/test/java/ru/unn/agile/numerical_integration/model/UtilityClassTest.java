package ru.unn.agile.numerical_integration.Model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

public class UtilityClassTest {
    private UtilityClassTest() {
    }

    public static void testForFinal(final Class<?> aClass) {
        assertTrue("class must be final",
                Modifier.isFinal(aClass.getModifiers()));
    }

    public static void shouldHaveOneConstructor(final Class<?> aClass) {
        assertEquals("There must be only one constructor", 1,
                aClass.getDeclaredConstructors().length);

    }

    public static void constructorShouldBePrivate(final Class<?> aClass)
            throws NoSuchMethodException {

        final Constructor<?> constructor = aClass.getDeclaredConstructor();
        if (!Modifier.isPrivate(constructor.getModifiers())) {
            fail("constructor is not private");
        }
    }

    public static void constructWithExeption(final Class<?> aClass, final String errorMessage)
            throws NoSuchMethodException {

        final Constructor<?> constructor = aClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (Exception e) {
            String error = ((InvocationTargetException) e).getTargetException().getMessage();
            assertEquals(error, errorMessage);
        }
        constructor.setAccessible(false);
    }
}

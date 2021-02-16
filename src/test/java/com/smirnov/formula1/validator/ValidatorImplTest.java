package com.smirnov.formula1.validator;

import org.junit.jupiter.api.Test;
import static net.obvj.junit.utils.matchers.ExceptionMatcher.throwsException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ValidatorImplTest {
    private final ValidatorImpl validator = new ValidatorImpl();
    private static final String ABBREVIATIONS = "src/test/resources/abbreviations_test.txt";
    private static final String START = "src/test/resources/start_test.log";
    private static final String END = "src/test/resources/end_test.log";

    @Test
    void validateShouldThrowIllegalArgumentExceptionIfInputPathIsNull() {

        assertThat(() -> validator.validate(null, START, END),
                throwsException(IllegalArgumentException.class)
                        .withMessageContaining("abbreviations path is null"));

        assertThat(() -> validator.validate(ABBREVIATIONS, null, END),
                throwsException(IllegalArgumentException.class)
                        .withMessageContaining("start path is null"));

        assertThat(() -> validator.validate(ABBREVIATIONS, START, null),
                throwsException(IllegalArgumentException.class)
                        .withMessageContaining("end path is null"));
    }

    @Test
    void validateShouldThrowIllegalArgumentExceptionIfInputPathIsEmpty() {

        assertThat(() -> validator.validate("", START, END),
                throwsException(IllegalArgumentException.class)
                        .withMessageContaining("abbreviations path is empty"));

        assertThat(() -> validator.validate(ABBREVIATIONS, "", END),
                throwsException(IllegalArgumentException.class)
                        .withMessageContaining("start path is empty"));

        assertThat(() -> validator.validate(ABBREVIATIONS, START, ""),
                throwsException(IllegalArgumentException.class)
                        .withMessageContaining("end path is empty"));
    }

    @Test
    void validateShouldNotThrowIllegalArgumentExceptionWhenTheParametersAreCorrect() {
        assertDoesNotThrow(()-> validator.validate(ABBREVIATIONS, START, END));
    }
}

package com.smirnov.formula1.reader;

import org.junit.jupiter.api.Test;
import static net.obvj.junit.utils.matchers.ExceptionMatcher.throwsException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class FileReaderImplTest {
   private final FileReaderImpl fileReader = new FileReaderImpl();

   @Test
   void readFileShouldReadStringFromFile() {
      String expectedString = "DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER";
      String path = "src/test/resources/abbreviations_test.txt";
      String actualString = fileReader.readFile(path).get(0);

      assertThat(actualString, is(equalTo(expectedString)));
   }

   @Test
   void readFileShouldThrowIllegalArgumentExceptionIfInputPathDoesNotExist() {

      assertThat(() -> fileReader.readFile(""),
              throwsException(IllegalArgumentException.class)
                      .withMessageContaining("File does not exist"));
   }

}

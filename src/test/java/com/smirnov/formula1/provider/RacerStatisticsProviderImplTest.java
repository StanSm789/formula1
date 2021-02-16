package com.smirnov.formula1.provider;

import com.smirnov.formula1.domain.Racer;
import com.smirnov.formula1.parser.RacerParser;
import com.smirnov.formula1.reader.FileReader;
import com.smirnov.formula1.validator.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import static net.obvj.junit.utils.matchers.ExceptionMatcher.throwsException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class RacerStatisticsProviderImplTest {

    @Mock
    private Validator validator;

    @Mock
    private FileReader fileReader;

    @Mock
    private RacerParser racerParser;

    @Mock
    private ViewProvider viewProvider;

    @InjectMocks
    private RacerStatisticsProviderImpl statisticsProvider;

    @Test
    void provideSymbolStatisticsShouldThrowIllegalArgumentExceptionIfInputStringIsEmpty() {
        doThrow(new IllegalArgumentException("Input path is empty")).when(validator).validate("", "", "");

        assertThat(() -> statisticsProvider.provideRaceStatistics("", "", ""),
                throwsException(IllegalArgumentException.class));

        verify(validator).validate("", "", "");
        verifyNoMoreInteractions(viewProvider, fileReader, racerParser);
    }

    @Test
    void provideSymbolStatisticsShouldReturnCharStatisticsAndPutTheResultIntoCache() {
        List<String> abbreviations = fileReader.readFile("src/test/resources/abbreviations_test.txt");
        List<String> start = fileReader.readFile("src/test/resources/start_test.log");
        List<String> end = fileReader.readFile("src/test/resources/end_test.log");

        List<Racer> expectedRacers = Arrays.asList(
                new Racer("Sebastian Vettel", "FERRARI", LocalTime.parse("00:01:04.415")),
                new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", LocalTime.parse("00:01:12.013")),
                new Racer("Lewis Hamilton", "MERCEDES", LocalTime.parse("00:01:12.460")));
        String expectedView = "1. Sebastian Vettel | FERRARI | 01:04.415\n" +
                "2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013\n" +
                "3. Lewis Hamilton | MERCEDES | 01:12.460\n";

        when(racerParser.parseToRacers(abbreviations, start, end)).thenReturn(expectedRacers);
        when(viewProvider.provideView(expectedRacers)).thenReturn(expectedView);

        String raceStatistics = statisticsProvider.provideRaceStatistics("src/test/resources/abbreviations_test.txt",
                "src/test/resources/start_test.log",
                "src/test/resources/end_test.log");

        assertThat(expectedView, is(raceStatistics));

        verify(validator).validate("src/test/resources/abbreviations_test.txt",
                "src/test/resources/start_test.log",
                "src/test/resources/end_test.log");
        verify(racerParser).parseToRacers(abbreviations, start, end);
        verify(viewProvider).provideView(expectedRacers);
    }

}

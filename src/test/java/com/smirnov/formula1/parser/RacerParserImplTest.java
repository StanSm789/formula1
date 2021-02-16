package com.smirnov.formula1.parser;

import com.smirnov.formula1.domain.Racer;
import com.smirnov.formula1.reader.FileReaderImpl;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class RacerParserImplTest {
    private final RacerParserImpl racerParser = new RacerParserImpl();

    @Test
    void parseToRacersShouldReturnListOfRacers() {
        List<Racer> expectedRacers = Arrays.asList(
                new Racer("Sebastian Vettel", "FERRARI", LocalTime.parse("00:01:04.415")),
                new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", LocalTime.parse("00:01:12.013")),
                new Racer("Lewis Hamilton", "MERCEDES", LocalTime.parse("00:01:12.460")));

        List<String> abbreviations = Arrays.asList("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
                "SVF_Sebastian Vettel_FERRARI",
                "LHM_Lewis Hamilton_MERCEDES");
        List<String> start = Arrays.asList("SVF2018-05-24_12:02:58.917",
                "DRR2018-05-24_12:14:12.054",
                "LHM2018-05-24_12:18:20.125");
        List<String> end = Arrays.asList("DRR2018-05-24_12:15:24.067",
                "SVF2018-05-24_12:04:03.332",
                "LHM2018-05-24_12:19:32.585");

        List<Racer> actualRacers = racerParser.parseToRacers(abbreviations, start, end);

        assertThat(actualRacers, is(equalTo(expectedRacers)));
    }
}

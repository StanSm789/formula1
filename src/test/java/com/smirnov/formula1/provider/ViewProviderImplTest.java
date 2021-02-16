package com.smirnov.formula1.provider;

import com.smirnov.formula1.domain.Racer;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class ViewProviderImplTest {
    private final ViewProviderImpl viewProvider = new ViewProviderImpl();

    @Test
    void provideViewShouldProvideViewForOneRacer() {
        List<Racer> expectedRacerParser = new ArrayList<>();
        expectedRacerParser.add(new Racer("Sebastian Vettel", "FERRARI", LocalTime.parse("00:01:04.415")));
        String expectedView = "1. Sebastian Vettel | FERRARI | 01:04.415\n";
        String actualView = viewProvider.provideView(expectedRacerParser);

        assertThat(actualView, is(equalTo(expectedView)));
    }

    @Test
    void provideViewShouldProvideViewForRacers() {
        List<Racer> expectedRacerParser = new ArrayList<>();
        expectedRacerParser.add(new Racer("Sebastian Vettel", "FERRARI", LocalTime.parse("00:01:04.415")));
        expectedRacerParser.add(new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", LocalTime.parse("00:01:12.013")));
        expectedRacerParser.add(new Racer("Valtteri Bottas", "MERCEDES", LocalTime.parse("00:01:12.434")));
        expectedRacerParser.add(new Racer("Lewis Hamilton", "MERCEDES", LocalTime.parse("00:01:12.460")));
        expectedRacerParser.add(new Racer("Stoffel Vandoorne", "MCLAREN RENAULT", LocalTime.parse("00:01:12.463")));
        expectedRacerParser.add(new Racer("Kimi Raikkonen", "FERRARI", LocalTime.parse("00:01:12.639")));
        expectedRacerParser.add(new Racer("Fernando Alonso", "MCLAREN RENAULT", LocalTime.parse("00:01:12.657")));
        expectedRacerParser.add(new Racer("Sergey Sirotkin", "WILLIAMS MERCEDES", LocalTime.parse("00:01:12.706")));
        expectedRacerParser.add(new Racer("Charles Leclerc", "SAUBER FERRARI", LocalTime.parse("00:01:12.829")));
        expectedRacerParser.add(new Racer("Sergio Perez", "FORCE INDIA MERCEDES", LocalTime.parse("00:01:12.848")));
        expectedRacerParser.add(new Racer("Romain Grosjean", "HAAS FERRARI", LocalTime.parse("00:01:12.930")));
        expectedRacerParser.add(new Racer("Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", LocalTime.parse("00:01:12.941")));
        expectedRacerParser.add(new Racer("Carlos Sainz", "RENAULT", LocalTime.parse("00:01:12.950")));
        expectedRacerParser.add(new Racer("Esteban Ocon", "FORCE INDIA MERCEDES", LocalTime.parse("00:01:13.028")));
        expectedRacerParser.add(new Racer("Nico Hulkenberg", "RENAULT", LocalTime.parse("00:01:13.065")));
        expectedRacerParser.add(new Racer("Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", LocalTime.parse("00:01:13.179")));
        String expectedView =
                "1. Sebastian Vettel | FERRARI | 01:04.415\n" +
                "2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013\n" +
                "3. Valtteri Bottas | MERCEDES | 01:12.434\n" +
                "4. Lewis Hamilton | MERCEDES | 01:12.460\n" +
                "5. Stoffel Vandoorne | MCLAREN RENAULT | 01:12.463\n" +
                "6. Kimi Raikkonen | FERRARI | 01:12.639\n" +
                "7. Fernando Alonso | MCLAREN RENAULT | 01:12.657\n" +
                "8. Sergey Sirotkin | WILLIAMS MERCEDES | 01:12.706\n" +
                "9. Charles Leclerc | SAUBER FERRARI | 01:12.829\n" +
                "10. Sergio Perez | FORCE INDIA MERCEDES | 01:12.848\n" +
                "11. Romain Grosjean | HAAS FERRARI | 01:12.930\n" +
                "12. Pierre Gasly | SCUDERIA TORO ROSSO HONDA | 01:12.941\n" +
                "13. Carlos Sainz | RENAULT | 01:12.950\n" +
                "14. Esteban Ocon | FORCE INDIA MERCEDES | 01:13.028\n" +
                "15. Nico Hulkenberg | RENAULT | 01:13.065\n" +
                "--------------------------------------------------------------------\n" +
                "16. Brendon Hartley | SCUDERIA TORO ROSSO HONDA | 01:13.179\n";
        String actualView = viewProvider.provideView(expectedRacerParser);

        assertThat(actualView, is(equalTo(expectedView)));
    }
}

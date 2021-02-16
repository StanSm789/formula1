package com.smirnov.formula1.provider;

import com.smirnov.formula1.domain.Racer;
import com.smirnov.formula1.parser.RacerParser;
import com.smirnov.formula1.reader.FileReader;
import com.smirnov.formula1.validator.Validator;
import java.util.List;

public class RacerStatisticsProviderImpl implements RacerStatisticsProvider {
    private final Validator validator;
    private final FileReader fileReader;
    private final RacerParser racerParser;
    private final ViewProvider viewProvider;

    public RacerStatisticsProviderImpl(Validator validator,
                                       FileReader fileReader,
                                       RacerParser racerParser,
                                       ViewProvider viewProvider) {
        this.validator = validator;
        this.fileReader = fileReader;
        this.racerParser = racerParser;
        this.viewProvider = viewProvider;
    }

    @Override
    public String provideRaceStatistics(String abbreviationsInput, String startInput, String endInput) {

        validator.validate(abbreviationsInput, startInput, endInput);

        List<String> abbreviations = fileReader.readFile(abbreviationsInput);
        List<String> starts = fileReader.readFile(startInput);
        List<String> ends = fileReader.readFile(endInput);

        List<Racer> racers = racerParser.parseToRacers(abbreviations, starts, ends);

        return viewProvider.provideView(racers);
    }
}

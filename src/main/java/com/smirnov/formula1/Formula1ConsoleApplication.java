package com.smirnov.formula1;

import com.smirnov.formula1.parser.RacerParser;
import com.smirnov.formula1.parser.RacerParserImpl;
import com.smirnov.formula1.provider.ViewProvider;
import com.smirnov.formula1.provider.ViewProviderImpl;
import com.smirnov.formula1.provider.RacerStatisticsProvider;
import com.smirnov.formula1.provider.RacerStatisticsProviderImpl;
import com.smirnov.formula1.reader.FileReader;
import com.smirnov.formula1.reader.FileReaderImpl;
import com.smirnov.formula1.validator.Validator;
import com.smirnov.formula1.validator.ValidatorImpl;

public class Formula1ConsoleApplication {
    public static void main(String[] args) {
        String abbreviations = "src/main/resources/abbreviations.txt";
        String startTime = "src/main/resources/start.log";
        String endTime = "src/main/resources/end.log";

        Validator validator = new ValidatorImpl();
        FileReader fileReader = new FileReaderImpl();
        RacerParser racerParser = new RacerParserImpl();
        ViewProvider viewProvider = new ViewProviderImpl();

        RacerStatisticsProvider statisticsProvider = new RacerStatisticsProviderImpl(validator, fileReader, racerParser, viewProvider);
        String result = statisticsProvider.provideRaceStatistics(abbreviations, startTime, endTime);

        System.out.println(result);
    }

}

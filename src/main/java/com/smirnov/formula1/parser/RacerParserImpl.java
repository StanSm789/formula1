package com.smirnov.formula1.parser;

import com.smirnov.formula1.domain.Racer;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;

public class RacerParserImpl implements RacerParser {
    private static final String STRING_SEPARATOR = "_";
    private static final int RACER_NAME = 0;
    private static final int RACER_TEAM = 1;

    @Override
    public List<Racer> parseToRacers(List<String> abbreviations, List<String> start, List<String> end) {

        Map<String, String> mapAbbreviations = putAbbreviations(abbreviations);
        Map<String, String> mapStart = putTiming(start);
        Map<String, String> mapEnd = putTiming(end);
        List<String> racerAbbreviations = new LinkedList<>(mapAbbreviations.keySet());

        Map<String, String> consolidatedMap = new HashMap<>(mapAbbreviations);
        mapStart.forEach(consolidatedMap::put);
        mapEnd.forEach(consolidatedMap::put);

        List<Racer> racers = new ArrayList<>();

        for(int i = 0; i < abbreviations.size(); i++) {
            String abbreviation = racerAbbreviations.get(i);
            String racerName = mapAbbreviations.get(abbreviation).split(STRING_SEPARATOR)[RACER_NAME];
            String teamName = mapAbbreviations.get(abbreviation).split(STRING_SEPARATOR)[RACER_TEAM];
            LocalTime startTime = LocalTime.parse(mapStart.get(abbreviation));
            LocalTime endTime = LocalTime.parse(mapEnd.get(abbreviation));
            LocalTime bestLapTime = subtractTime(startTime, endTime);

            racers.add(new Racer(racerName, teamName, bestLapTime));
        }

        return racers.stream()
                .sorted(Comparator.comparing(Racer::getBestLap))
                .collect(Collectors.toList());

    }

    private Map<String, String> putAbbreviations(List<String> abbreviations) {

        return abbreviations.stream()
                .map(s -> s.split(STRING_SEPARATOR))
                .collect(toMap(a -> a[0], a1 -> a1[1].concat(STRING_SEPARATOR).concat(a1[2])));
    }


    private Map<String, String> putTiming(List<String> time) {

        return time.stream()
                .map(s -> s.split(STRING_SEPARATOR))
                .collect(toMap(a -> a[0].substring(0, 3), a1 -> a1[1]));

    }

    private LocalTime subtractTime(LocalTime startTime, LocalTime endTime) {
        return endTime.minusNanos(startTime.toNanoOfDay());
    }

}

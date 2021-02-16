package com.smirnov.formula1.parser;

import com.smirnov.formula1.domain.Racer;
import java.util.List;

public interface RacerParser {
    List<Racer> parseToRacers(List<String> abbreviations, List<String> start, List<String> end);

}

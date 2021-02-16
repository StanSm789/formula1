package com.smirnov.formula1.provider;

public interface RacerStatisticsProvider {
    String provideRaceStatistics(String abbreviations, String start, String end);
}

package com.smirnov.formula1.provider;

import com.smirnov.formula1.domain.Racer;
import java.util.List;

public class ViewProviderImpl implements ViewProvider {
    private static final String DASH = "-";
    private static final int ELIMINATION_NUMBER = 15;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int LINE_EXTENSION = 16;

    @Override
    public String provideView(List<Racer> racers) {
        StringBuilder view = new StringBuilder();

        racers.forEach(racer -> {
            if ((racers.indexOf(racer) == ELIMINATION_NUMBER)) {
                view.append(printLine(racer.getName(), racer.getTeam(), String.valueOf(racer.getBestLap())));
            }
            view.append(String.format("%d. ", racers.indexOf(racer) + 1))
                    .append(String.format("%s | ", racer.getName()))
                    .append(String.format("%s | ", racer.getTeam()))
                    .append(String.format("%s", racer.getBestLap().toString().replaceFirst("0*:*", "")))
                    .append(String.format("%s", LINE_SEPARATOR));
        });

        return view.toString();
    }

    private String printLine(String name, String team, String time) {
        StringBuilder lineMaker = new StringBuilder();
        int length = (name.length() + team.length() + time.length()) + LINE_EXTENSION;

        for(int i = 0; i < length; i++) {
            lineMaker.append(DASH);
        }

        return lineMaker.toString() + LINE_SEPARATOR;
    }

}

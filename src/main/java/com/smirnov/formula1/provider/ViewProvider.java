package com.smirnov.formula1.provider;

import com.smirnov.formula1.domain.Racer;
import java.util.List;

public interface ViewProvider {
    String provideView(List<Racer> racers);
}

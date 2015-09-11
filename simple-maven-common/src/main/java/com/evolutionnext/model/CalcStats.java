package com.evolutionnext.model;

import java.util.Collections;
import java.util.List;

public class CalcStats {

    private List<Integer> list;

    public CalcStats(List<Integer> integers) {
        this.list = integers;
    }

    public Integer getMinimum() {
        if (list == null || list.size() == 0) return null;
        return Collections.min(list);
    }
}

package com.peter.moon.d2r.flows;

public class LinkedFlow implements Flow {
    public LinkedFlow next;
    public LinkedFlow prev;

    @Override
    public RowData processData(RowData rowData) {
        return null;
    }
}

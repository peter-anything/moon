package com.peter.moon.d2r.flows;

import java.util.stream.Stream;

public class FlowManager {
    public void processData() throws Exception {
        DBProcessor processor = new DBProcessor();
        LinkedFlow flow = new ThreeTupleFlow();

        for (RowData rowData: processor.process()) {
            while (flow != null) {
                rowData = flow.processData(rowData);
                flow = flow.next;
            }
        }
    }
}

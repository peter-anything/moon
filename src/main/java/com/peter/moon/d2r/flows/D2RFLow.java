package com.peter.moon.d2r.flows;

public class D2RFLow extends LinkedFlow {
    public D2RFLow() {}

    public D2RFLow(LinkedFlow next, LinkedFlow prev) {
        this.next = next;
        this.prev = prev;
    }

    public Row process() throws Exception {
        return null;
    }
}

package com.Udm1.OnlineShopBackEnd;

public class DefaultSupportTicket implements SupportTicket {
    private Priority priority;
    private RequestType requestType;
    private int sequentialNumber = 1;
    public static int sequenceCounter = 1;

    DefaultSupportTicket() {
        this.sequentialNumber = sequenceCounter;
        sequenceCounter++;
    }

    public DefaultSupportTicket(RequestType requestType) {
        this.requestType = requestType;
        this.sequentialNumber = sequenceCounter;
        sequenceCounter++;
    }

    DefaultSupportTicket(Priority priority, RequestType requestType) {
        this.priority = priority;
        this.requestType = requestType;
        this.sequentialNumber = sequenceCounter;
        sequenceCounter++;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public int getSequentialNumber() {
        return sequentialNumber;
    }

    @Override
    public RequestType getRequestType() {
        return requestType;
    }
}

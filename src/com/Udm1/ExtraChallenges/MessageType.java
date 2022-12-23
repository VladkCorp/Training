package com.Udm1.ExtraChallenges;

public enum MessageType {
    A(Priority.HIGH), B(Priority.MEDIUM), C(Priority.LOW), D(Priority.LOW);

    private Priority priority;

    MessageType(Priority priority){
        this.priority = priority;
    }

    public Priority getPriority() {
        return this.priority;
    }
}
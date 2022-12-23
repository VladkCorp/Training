package com.Udm1.OnlineShopBackEnd;

import java.util.Comparator;

public class CustomSupportTicketsComparator implements Comparator<SupportTicket> {

    @Override
    public int compare(SupportTicket o1, SupportTicket o2) {
        assignPriority(o1);
        assignPriority(o2);
        int priorityComparisonResult = compareByPriority(o1,o2);

        if (priorityComparisonResult == 0) {
            return compareBySeuqenceNum(o1,o2);
        } else {
            return priorityComparisonResult;
        }
    }

    private void assignPriority(SupportTicket supportTicket) {
        if (supportTicket.getPriority() != null) {
            return;
        }
        if (supportTicket.getRequestType() == null) {
            supportTicket.setPriority(Priority.LOW);
            return;
        }
        switch (supportTicket.getRequestType()) {
            case OTHER:
                supportTicket.setPriority(Priority.LOW);
                break;
            case CHANGE_ACCOUNT_DETAILS:
                supportTicket.setPriority(Priority.LOW);
                break;
            case CAN_NOT_LOGIN:
                supportTicket.setPriority(Priority.MEDIUM);
                break;
            case ACCOUNT_IS_BLOCKED:
                supportTicket.setPriority(Priority.MEDIUM);
                break;
            case COOPERATION:
                supportTicket.setPriority(Priority.MEDIUM);
                break;
            case ACCOUNT_IS_HACKED:
                supportTicket.setPriority(Priority.HIGH);
                break;
            case CAN_NOT_COMPLETE_PURCHASE:
                supportTicket.setPriority(Priority.HIGH);
                break;
            case ORDER_IS_NOT_RECEIVED:
                supportTicket.setPriority(Priority.HIGH);
                break;
            default:
                supportTicket.setPriority(Priority.LOW);
        }
    }

    public int convertPriorityToInt(SupportTicket supportTicket) {
        int priorityNum = 0;

        //higher priority = higher the num
        switch (supportTicket.getPriority()) {
            case HIGH:
                priorityNum = 3;
                break;
            case MEDIUM:
                priorityNum = 2;
                break;
            case LOW:
                priorityNum = 1;
                break;
        }
        return priorityNum;
    }

    private int compareBySeuqenceNum(SupportTicket o1, SupportTicket o2) {
        if (o1.getSequentialNumber() < o2.getSequentialNumber()) {
            return -1;
        } else if (o1.getSequentialNumber() > o2.getSequentialNumber()) {
            return 1;
        }
        return 0;
    }

    //Higher priority goes first
    private int compareByPriority(SupportTicket o1, SupportTicket o2) {
        if (convertPriorityToInt(o1) < convertPriorityToInt(o2)) {
            return 1;
        } else if (convertPriorityToInt(o1) > convertPriorityToInt(o2)) {
            return -1;
        }
        return 0;
    }

}

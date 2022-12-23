package com.Udm1.OnlineShopBackEnd;

import java.util.PriorityQueue;

public class DefaultHelpDeskFacade implements HelpDeskFacade {
    PriorityQueue<SupportTicket> priorityQueue = new PriorityQueue<>(new CustomSupportTicketsComparator());

    @Override
    public void addNewSupportTicket(SupportTicket supportTicket) {
        priorityQueue.add(supportTicket);
    }

    @Override
    public SupportTicket getNextSupportTicket() {
        if (priorityQueue.isEmpty()) {
            return null;
        }
        SupportTicket nextTicket = priorityQueue.poll();
        return nextTicket;
    }

    @Override
    public int getNumberOfTickets() {
        return priorityQueue.size();
    }

}

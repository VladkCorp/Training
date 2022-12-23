package com.Udm1.OnlineShopBackEnd;

public class helpDeskTest {

    public static void main(String[] args) {
        HelpDeskFacade deskFacade = new DefaultHelpDeskFacade();
        deskFacade.addNewSupportTicket(new DefaultSupportTicket());
        deskFacade.addNewSupportTicket(new DefaultSupportTicket());
//        SupportTicket supportTicket3 = new DefaultSupportTicket(RequestType.CAN_NOT_LOGIN);
//        deskFacade.addNewSupportTicket(supportTicket3);

//        SupportTicket test1 = deskFacade.getNextSupportTicket();
//        SupportTicket test2 = deskFacade.getNextSupportTicket();
//
//        System.out.println(test1.getRequestType());
//        System.out.println(test2.getRequestType());

        System.out.println(deskFacade.getNumberOfTickets());
//      System.out.println(deskFacade.getNextSupportTicket().getPriority());
//      System.out.println(deskFacade.getNextSupportTicket().getPriority());
    }
}

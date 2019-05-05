package Student_Tech_Services_Tracking_Log;

import java.util.LinkedList;

public class ResolvedTicketStore {

    LinkedList<Ticket> resolvedTickets;

    ResolvedTicketStore() {
        resolvedTickets = new LinkedList<>();
    }

    public void addTicket(Ticket t) {
        resolvedTickets.add(t);
    }


    public LinkedList<Ticket> getAllTickets() {
        return resolvedTickets;
    }

}

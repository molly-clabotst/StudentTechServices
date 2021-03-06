package Student_Tech_Services_Tracking_Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;


/**
 * A data structure to store Tickets in memory as the program runs
 * Stores tickets in priority order, so tickets with priority 1 are at the start
 * If more than one ticket with same priority, oldest tickets are before newer tickets
 * Supports add, delete, search operations on the list of Tickets
 */


public class TicketStore {

    private static LinkedList<Ticket> ticketQueue;

    private Comparator<Ticket> ticketComparator;

    TicketStore() {

        ticketQueue = new LinkedList<>();

    }


    public void addAll(ArrayList<Ticket> tickets) {
        ticketQueue.addAll(tickets);
    }


    /**
     * Add ticket, and then sort list to keep the highest-priority at the top of the queue
     */
    public void add(Ticket newTicket) {
        ticketQueue.add(newTicket);
    }


    /**
     * Returns all tickets in the queue.
     *
     * @return All the tickets.
     */
    public LinkedList<Ticket> getAllTickets() {
        return ticketQueue;
    }


    /**
     * Returns, but does not remove, the tickets from the top of the TicketQueue
     *
     * @return the ticket at the top of the queue
     */
    public Ticket peekNextTicket() {
        return ticketQueue.peek();
    }


    /**
     * How many currently open tickets in the list?
     *
     * @return the number of open tickets
     */
    public int ticketsInQueue() {
        return ticketQueue.size();
    }


    /**
     * Searches store for ticket with given ID.
     *
     * @param id The ticket ID
     * @return The ticket with this ID, if found; null otherwise
     */
    public Ticket getTicketById(int id) {
        for (Ticket t : ticketQueue) {
            if (t.getID() == id) {
                return t;
            }
        }

        return null; // If ticket with this ID is not found
    }


    /**
     * Deletes by ticket ID.
     *
     * @return true if a ticket was found and deleted, false if a ticket with this ID is not in the queue
     */
    public boolean deleteTicketById(int deleteID) {

        //Loop over all tickets. Delete the one with this ticket ID

        for (Ticket ticket : ticketQueue) {
            if (ticket.getID() == deleteID) {
                ticketQueue.remove(ticket);
                return true;
            }
        }

        // Not found? Return false
        return false;

    }

    /**
     * Returns a list of tickets, with a description containing
     * the given String. The search is not case sensitive.
     *
     * @param description Text to search for in Ticket descriptions
     * @return a list of matching Tickets. If no matches, return an empty list.
     */
    public LinkedList<Ticket> searchByDescription(String description) {
        //TODO problem 3, implement this method
        LinkedList<Ticket> tickets = new LinkedList<>();

        if (description != null && !description.equals("") && !description.equals(" ")) {
            String[] tikDesc;
            String[] srchDesc = description.split("");
            int matches = 0;
            int tikDescCharInd = 0;
            int srchDescCharInd = 99999;


            for (Ticket ticket :
                    ticketQueue) {
                tikDesc = ticket.getProbDesc().split("");
                matches = 0;
                tikDescCharInd = 0;
                srchDescCharInd = 99999;
                tikDescloop:
                for (int i = 0; i < tikDesc.length; i++) {
                    for (int j = 0; j < srchDesc.length; j++) {
                        if (srchDescCharInd != 99999 && !(tikDescCharInd == tikDesc.length) && !(srchDescCharInd == srchDesc.length)) {
                            if (tikDesc[tikDescCharInd].equalsIgnoreCase(srchDesc[srchDescCharInd])) {
                                tikDescCharInd++;
                                srchDescCharInd++;
                                matches++;
                                if (matches == srchDesc.length) {
                                    tickets.add(ticket);
                                    break tikDescloop;
                                }
                            } else {
                                tikDescCharInd = 0;
                                srchDescCharInd = 99999;
                                matches = 0;
                            }
                        } else if (tikDesc[i].equalsIgnoreCase(srchDesc[0])) {
                            matches++;
                            tikDescCharInd = i + 1;
                            srchDescCharInd = 1;
                        } else {
                            break;
                        }
                    }

                }
            }

        }
        return tickets;
    }

}


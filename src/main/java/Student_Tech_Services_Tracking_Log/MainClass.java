package Student_Tech_Services_Tracking_Log;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/** Launches the GUI. Connects GUI to data store, and to TicketFileStorage. */

public class MainClass {

    TicketDB ticketDB;
    TrackingLog trackingLog;
//    TicketFileIO ticketFileIO;
    TicketStore ticketStore;
//    TicketGUI ticketGUI;
    ResolvedTicketStore resolvedTicketStore;

//    static String openTicketsFile = "open_tickets.txt";
    static String resolvedTicketsFilePrefix = "Resolved_Tickets_"; // for making a filename like  "Resolved_Tickets_September_28_2017.txt"
//    static String ticketCounterFile = "ticket_counter.txt";


    // Write and read to files in this directory. The tests will use a different directory, but same filenames.
//    static String ticketDataDirectory = "TicketData";


    public static void main(String[] args) {
        MainClass ticketProgram = new MainClass();
        ticketProgram.start();
    }


    // default start of program
    void start() {
        setup();
        startGUI();
    }


    protected void setup() {
        createTicketStore();
        configureResolvedTickets();
        // Start normally
        loadTicketsFromTicketStore();   // No open tickets
//        configureTicketIdGenerator(null);    // First ticket created will have ID = 1
    }



    // Do any TicketStore setup here
    protected void createTicketStore() {
        ticketStore = new TicketStore();
    }


    protected void loadTicketsFromTicketStore() {

        ticketDB=new TicketDB();


        ArrayList<Ticket> openTickets = ticketDB.loadTickets();
        ticketStore.addAll(openTickets);

    }


    // Do any setup for your resolved tickets store here.
    protected void configureResolvedTickets() {
        resolvedTicketStore = new ResolvedTicketStore();
    }

//    protected void configureTicketIdGenerator(Integer defaultNextTicketId) {
//
//        System.out.println("defaultNextTicketId = " + defaultNextTicketId);
//        if (defaultNextTicketId == null) {
//            // TODO 7 If you need to do anything to assist generating unique ticket IDs, do it here.
//            int tixCount = ticketFileIO.loadTicketIDCounter();
//            Ticket.setNextId(tixCount);
//
//        } else {
//            Ticket.setNextId(defaultNextTicketId);
//        }
//
//        System.out.println("The next ticket will have the id = " + Ticket.getNextId());
//    }



    protected void startGUI() {
        trackingLog= new TrackingLog(this);
    }


    protected void addTicket(Ticket newTicket) {

        // Add the Ticket to the ticketStore
        ticketStore.add(newTicket);
    }


    // Search the ticket store for this ticket. Returns null if ticket not found.
    protected Ticket searchById(int ticketId) {
        Ticket ticket = ticketStore.getTicketById(ticketId);
        return ticket;
    }


    protected void resolveTicket(Ticket ticket) {
        resolvedTicketStore.addTicket(ticket);
        ticketStore.deleteTicketById(ticket.getID());
    }


    // Find and return a list of matching tickets. If nothing matches, the list will be empty.
    protected LinkedList<Ticket> searchByDescription(String searchTerm) {

        LinkedList<Ticket> matchingTickets = ticketStore.searchByDescription(searchTerm);
        return matchingTickets;

    }


    protected LinkedList<Ticket> getAllTickets() {
        LinkedList<Ticket> allTickets = ticketStore.getAllTickets();
        return allTickets;
    }


    protected void quitProgram() {

        // TODO task 8
//        record date last edited
        SimpleDateFormat df = new SimpleDateFormat("MMMM_dd_yyyy");
        String today = df.format(new Date());
        ticketDB.updateLastEdit(today);

//        save active tickets
        for (Ticket ticket: ticketStore.getAllTickets()) {
//            resave ticket
            if(ticketDB.findTicketByID(ticket)!=null){
                ticketDB.updateInfo(ticket);
//                make new ticket in database
            }else {
                ticketDB.addNewTicket(ticket);
            }
        }

//        save resolved tickets



    }
}

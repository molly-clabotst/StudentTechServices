package Student_Tech_Services_Tracking_Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.*;

public class Ticket {

//    Ticket Info
    private int ID;

//    Student client info
    private String clientName;
    private String clientID;
    private String clientMail;
    private int clientPhone;
    private boolean signedWaiver = false;
    private Date dateRecorded;

//    Student member info
    private String memName;
    private String memID;
    private ArrayList<String> chainCust;

//    Descriptions
    private String probDesc;
    private String resol;

//    Disk stuff
    private boolean disk = false;
    private boolean backUp = false;
    private String method;

//    Consent
    private boolean consent;


    //STATIC Counter - one variable, shared by all Ticket objects.
    //If any Ticket object modifies this counter, all Ticket objects will have the modified value
    //Make it private - only Ticket objects should have access
//    private static int ticketIdCounter = 1;

    //The ID for each ticket - an instance variable. Each Ticket will have it's own ticketID variable
//    private int ticketID;

    // You should have already done these tasks in the previous lab
    // TODO: tickets need to store the resolution date and a string describing the resolution
    // TODO implement your mechanism to ensure new tickets have a unique ID
    // TODO add any other methods you wrote in the previous lab and will need here

//    Constructor new for ticket
    public Ticket(){}

//    public Ticket(int ID, String cName, String cID, String cMail, int cPhone, boolean sign,
//                  Date date, String mName, String mID, String prDes, boolean disk,
//                  boolean backUp, String how, boolean consent) {
//        this.ID = ID;
//        this.clientName = cName;
//        this.clientID = cID;
//        this.clientMail = cMail;
//        this.clientPhone = cPhone;
//        this.signedWaiver = sign;
//        this.dateRecorded = date;
//        this.memName = mName;
//        this.memID = mID;
//        this.probDesc = prDes;
//        this.disk = disk;
//        this.backUp = backUp;
//        this.method = how;
//        this.consent = consent;
//    }


    // TODO use this constructor to create a Ticket from existing Ticket data read from a file
    // Notice that it does not modify the static ticketIDCounter
    // Use the setNextID and getNextId method if you need to change the next ticketID that will
    // be generated, for example, if you are re-starting the program

//    Constructor for saved ticket with chain of custody
    public Ticket(int ID, String cName, String cID, String cMail, int cPhone, boolean sign,
                  Date date, String mName, String mID, String prDes,
                  ArrayList<String> chain, boolean disk, boolean backUp, String how,
                  boolean consent) {
        this.ID = ID;
        this.clientName = cName;
        this.clientID = cID;
        this.clientMail = cMail;
        this.clientPhone = cPhone;
        this.signedWaiver = sign;
        this.dateRecorded = date;
        this.memName = mName;
        this.memID = mID;
        this.probDesc = prDes;
        this.chainCust = chain;
        this.disk = disk;
        this.backUp = backUp;
        this.method = how;
        this.consent = consent;
    }

//    Setters
    public void setClientName(String name){ this.clientName = name; }
    public void setClientID(String id){
        String pattern = "([a-z&A-z][a-z&A-z][1-9][1-9][1-9][1-9][a-z&A-z][a-z&A-z])";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(id);
        if (m.find( )) {
            this.clientID = id;

        }else {
            this.clientID = "0";
        }
    }
    public void setClientMail(String mail){this.clientMail = mail;}
    public void setClientPhone(String phone){
        int fone = 0;

        try{
            fone = Integer.parseInt(phone);
        }catch (NumberFormatException nfe){
            System.out.println(nfe);
        }
        this.clientPhone = fone;
    }
    public void setSignedWaiver(boolean meh){this.signedWaiver = meh;}
    public void setMemName(String name){this.memName= name;}
    public void setMemID(String id){
        String pattern = "([a-z&A-z][a-z&A-z][1-9][1-9][1-9][1-9][a-z&A-z][a-z&A-z])";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(id);
        if (m.find( )) {
            this.memID = id;

        }else {
            this.memID = "0";
        }
    }

    public void setID(String ID){
        try {
            int id = Integer.parseInt(ID);
            this.ID = id;
        }catch (NumberFormatException nfe){
            this.ID = -1;
            System.out.println(nfe);
        }
    }
    public void setChainCust(ArrayList<String> chain){this.chainCust = chain;}
    public void setProbDesc(String desc){this.probDesc=desc;}
    public void setResol(String resolv){this.resol = resolv;}
    public void setDisk(boolean eh){this.disk=eh;}
    public void setBackUp(boolean maybe){this.backUp=maybe;}
    public void setMethod(String how){this.method = how;}
    public void setConsent(boolean yes){this.consent=yes;}

//    Getters
    public int getID(){return this.ID;}
    public String getClientName(){return this.clientName;}
    public String getClientID(){return this.clientID;}
    public String getClientMail(){return this.clientMail;}
    public int getClientPhone(){return this.clientPhone;}
    public boolean getSignedWaive(){return this.signedWaiver;}
    public String getMemName(){return this.memName;}
    public String getMemID(){return this.memID;}
    public ArrayList<String> getChainCust(){return this.chainCust;}
    public String getProbDesc(){return this.probDesc;}
    public String getResol(){return this.resol;}
    public boolean getDisk(){return this.disk;}
    public boolean getBackUp(){return this.backUp;}
    public String getMethod(){return this.method;}
    public boolean getConsent(){return this.consent;}

//    New ticket toString method
    @Override
    public String toString(){
        SimpleDateFormat sdfr = new SimpleDateFormat("MMMM/dd/yyyy");
        String day = sdfr.format(this.dateRecorded);
        return("\nStudent\nName: "+ this.clientName+ "\tStar ID: "+this.clientID+
                "\tEmail: "+this.clientMail+"\nPhone: " + String.valueOf(this.clientPhone) +
                "\tDate Reported: " + day + "\tSigned Waiver: " +
                "\nClub Member\nName: " + this.memName + "\tStar ID: " + this.memID +
                "\nDescription of Problem: " + "\n" + this.probDesc +
                "\n\nConsent to perfom services: " + "\n");
    }

    // TODO Problem 8 you may want to add a method to generate a String representing this Ticket, suitable
    //  for writing to a file. Whatever you write out, should be able to be read and turned back into a Ticket object.
    // Recommendation: write dates as timestamps, not a String. It will be much simpler to read them back.

//    string for ticket with chain of custody
    public String toString2() {
        SimpleDateFormat sdfr = new SimpleDateFormat("MMMM/dd/yyyy");
        String day = sdfr.format(this.dateRecorded);
        return ("\nStudent\nName: " + this.clientName + "\tStar ID: " + this.clientID +
                "\tEmail: " + this.clientMail + "\nPhone: " + String.valueOf(this.clientPhone) +
                "\tDate Reported: " + day + "\tSigned Waiver: " +
                "\nClub Member\nName: " + this.memName + "\tStar ID: " + this.memID +
                "\nDescription of Problem: " + "\n" + this.probDesc + "\nChain of Custody: " +
                "\n\nConsent to perfom services: " + "\n");
    }
}


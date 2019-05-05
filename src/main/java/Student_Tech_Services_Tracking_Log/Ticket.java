package Student_Tech_Services_Tracking_Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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
    private LinkedList<String> chainCust;

//    Descriptions
    private String probDesc;
    private String resol;

//    Disk stuff
    private boolean backUp = false;
    private String method;

//    Consent
    private boolean consent;


//    Constructor for new Ticket
    public Ticket(String cName, boolean sign, String prDes, boolean consent) {
        this.clientName = cName;
        this.signedWaiver = sign;
        this.dateRecorded = new Date();
        this.probDesc = prDes;
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
    //TODO: set up regex for this method
    public void setClientMail(String mail){this.clientMail = mail;}
    public int setClientPhone(String phone){
        int fone = 0;

        try{
            fone = Integer.parseInt(phone);
        }catch (NumberFormatException nfe){
            fone = 0;
            return fone;
        }
        return this.clientPhone = fone;
    }
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
    //TODO: set up getting ID from Database
    public void setID(String ID){
        try {
            int id = Integer.parseInt(ID);
            this.ID = id;
        }catch (NumberFormatException nfe){
            this.ID = -1;
            System.out.println(nfe);
        }
    }
    public void setChainCust(LinkedList<String> chain){this.chainCust = chain;}
    public void setProbDesc(String desc){this.probDesc=desc;}
    public void setResol(String resolv){this.resol = resolv;}
    public void setBackUp(boolean maybe){this.backUp=maybe;}
    public void setMethod(String how){this.method = how;}

//    Getters
    public int getID(){return this.ID;}
    public String getClientName(){return this.clientName;}
    public String getClientID(){return this.clientID;}
    public String getClientMail(){return this.clientMail;}
    public int getClientPhone(){return this.clientPhone;}
    public boolean getSignedWaive(){return this.signedWaiver;}
    public String getMemName(){return this.memName;}
    public String getMemID(){return this.memID;}
    public LinkedList<String> getChainCust(){return this.chainCust;}
    public String getProbDesc(){return this.probDesc;}
    public String getResol(){return this.resol;}
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
                "\tDate Reported: " + day + "\tSigned Waiver: " + String.valueOf(this.signedWaiver) +
                "\nClub Member\nName: " + this.memName + "\tStar ID: " + this.memID +
                "\nDescription of Problem: " + "\n" + this.probDesc +
                "\n\nConsent to perform services: " + String.valueOf(this.consent) + "\n");
    }

    public String chainToString(){
        //TODO: Make formatting less ugly
//        make a header for chain of custody mini table
        String chainString="\nName\tStar Id";
//        Add all members to chain of custody list
        for (String memb :
                this.chainCust) {
//        Breaking apart entries from the list
            String array[]= memb.split("/");
//        Add names and star ids to string
            chainString +="\n"+ array[0]+"\t"+ array[1];
        }
        return chainString;
    }

    // Recommendation: write dates as timestamps, not a String. It will be much simpler to read them back.

//    string for ticket with chain of custody
    public String toString2() {
        SimpleDateFormat sdfr = new SimpleDateFormat("MMMM/dd/yyyy");
        String day = sdfr.format(this.dateRecorded);
        return ("\nStudent\nName: " + this.clientName + "\tStar ID: " + this.clientID +
                "\tEmail: " + this.clientMail + "\nPhone: " + String.valueOf(this.clientPhone) +
                "\tDate Reported: " + day + "\tSigned Waiver: " + String.valueOf(this.signedWaiver) +
                "\nClub Members\nMain Club Member\nName: " + this.memName + "\tStar ID: " + this.memID +
                "\nChain of Custody: " + chainToString() +"\nDescription of Problem: " + "\n" + this.probDesc +
                "\n\nConsent to perform services: " + String.valueOf(this.consent) + "\n");
    }
}


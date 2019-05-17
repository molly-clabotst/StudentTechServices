package Student_Tech_Services_Tracking_Log;

import java.sql.*;
import java.util.ArrayList;

public class TicketDB {

    private static final String url = "jdbc:sqlite:products.db";

    protected ArrayList<Ticket> loadTickets(){

        final String srchSql = "select * from tickets";

        try(Connection conect = DriverManager.getConnection(url);
            PreparedStatement prepState = conect.prepareStatement(srchSql)){

            ResultSet tikRs = srchSql.executeQuery();

            if(!tikRs.isBeforeFirst()) {
                System.out.println("Sorry, no entries are in the table yet");
            }else {
                while (tikRs.next()){
                    String clientID = tikRs.getString("clientID");
                    boolean waviver = tikRs.getBoolean("waiver_signed");
                    String desc = tikRs.getString("description_of_problem");
                    int group = tikRs.getInt("group_worked");
                    String prob = tikRs.getString("problems_to_escalate");
                    String sol = tikRs.getString("solutions");
                    boolean disk = tikRs.getBoolean("disk_clean");
                    boolean cons = tikRs.getBoolean("consent_understanding");

                    if()
                    Ticket tick = (String clientID, boolean waiver, String desc,);

                }
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return new ArrayList<>();
    }

    protected Ticket findTicketByID(Ticket ticket){
        return null;
    }

    protected void updateLastEdit(String today){

    }

    protected String updateInfo(Ticket ticket){
        return "";

    }

    protected void addNewTicket(Ticket ticket){

    }
}

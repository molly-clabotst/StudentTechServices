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

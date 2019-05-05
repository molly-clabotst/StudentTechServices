package Student_Tech_Services_Tracking_Log;

import com.sun.xml.internal.bind.v2.TODO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class TrackingLog extends JFrame{

//    Pannels and panes
    protected JPanel mainPanel;
    protected JTabbedPane tabbedPane;
    //    List
    protected JList<String> chanOfCustody;
    private JList tikDispList;
    //    Radio Buttons and groups
    //    Backed up?
    protected JRadioButton noRadioButton;
    protected JRadioButton yesRadioButton;
    protected ButtonGroup yesNoGroup;
    //    How?
    protected JRadioButton externalHardDriveRadioButton;
    protected JRadioButton cloudBasedStorageRadioButton;
    protected ButtonGroup backUpGroup;
    //    Checkboxes
    protected JCheckBox signedWaiverCheckBox;
    protected JCheckBox consent;
    //    Text Fields
    protected JTextField textMemStar;
    protected JTextField textStar;
    protected JTextField textCliPhone;
    protected JTextField textCliName;
    protected JTextField textClieEmail;
    protected JTextField textSrchID;
    protected JTextField textSrchDesc;
    protected JTextField textMemName;
    //    Buttons
    protected JButton submitInformationButton;
    protected JButton searchIDButton;
    protected JButton searchDescButton;
    protected JButton loadButton;
    protected JButton addClubMemberToButton;
    private JButton saveAndQuitButton;
    //    Text Area
    protected JTextArea taProbDesc;
    protected JTextArea taResolution;

    //    Create the list model for the chain of custody and the displayed tickets
    //    instantiate the main class
    protected DefaultListModel<String>chainListMo;
    protected DefaultListModel<String>tikListMo;
    MainClass manager;

//    Starting the app
    TrackingLog(MainClass manager) {
//        Instantiate the app
        this.manager = manager;
//        Set up Gui
        setContentPane(mainPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        rootPane.setDefaultButton(submitInformationButton);
//        Set up list models for JLists
        chainListMo= new DefaultListModel<>();
        tikListMo = new DefaultListModel<>();
        chanOfCustody.setModel(chainListMo);
        tikDispList.setModel(tikListMo);
        chanOfCustody.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tikDispList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        Set up listeners for components
        addListeners();

    }

    private void clearBoxesView(){
//        Clear Ticket viewer text boxes
        textSrchDesc.setText("");
        textSrchID.setText("");
//        Clear ticket display list
        tikDispList.clearSelection();
    }


    private void clearBoxesTrak(){
//        Clear Radios
        yesNoGroup.clearSelection();
        backUpGroup.clearSelection();
//        Uncheck Checkboxes
        signedWaiverCheckBox.setSelected(false);
        consent.setSelected(false);
//        Clear Text Areas
        taProbDesc.setText("");
        taResolution.setText("");
//        Clear Text Boxes
        textMemStar.setText("");
        textStar.setText("");
        textCliPhone.setText("");
        textClieEmail.setText("");
        textCliName.setText("");
        textMemName.setText("");
    }

    private void addListeners(){

        addClubMemberToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chainListMo.addElement(textMemName.getText()+"/"+textMemStar.getText());
            }
        });

        submitInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                Checking that there is data for; Client student name, email, star Id; club members info;
//                problem description
                if(textClieEmail.getText().trim().isEmpty() || textCliName.getText().trim().isEmpty()||
                chainListMo.isEmpty()|| textStar.getText().trim().isEmpty()||taProbDesc.getText().trim().isEmpty()){
                    showMessageDialog("Please enter data for all fields up to the chain of custody" +
                            " to add ticket");
//                Checking that there is data for waiver is signed and consent to services are present
                }else if(!consent.isSelected()||!signedWaiverCheckBox.isSelected()){
                    showMessageDialog("Student client must sign a waiver and consent to services before services" +
                            "can be rendered.");
                }
//                If there is the required data for the ticket add all the data
                else {
                    try{
//                        Add simple data to ticket via constructor
                        Ticket newTik = new Ticket(textCliName.getText(), signedWaiverCheckBox.isSelected(),
                                taProbDesc.getText(), consent.isSelected());
//                        Setting the rest of the values through setters
                        newTik.setClientID(textStar.getText());
                        newTik.setClientMail(textClieEmail.getText());
//                      Only add phone number if the text is present
                        if(!textCliPhone.getText().trim().isEmpty()){
                            int er = newTik.setClientPhone(textCliPhone.getText());
//                            Exception handling for non numeric input
                            if (er==0){
                                String phone= showInputDialog("Please enter a numbers " +
                                        "only, for the phone number.");
                                newTik.setClientPhone(phone);
                            }
                        }
//                        Setting boolean of signed waiver to true in ticket
//                        Taking first name and id out of listmodel to add to primary Club member,
//                        also entering following club members to the chain of custody array in ticket
                        LinkedList<String> chain = new LinkedList<>();
                        int counter = 0;
                        for (Object namNID: chainListMo.toArray()) {
                            String array[] = String.valueOf(namNID).split("/");

                            if (counter==0) {
                                newTik.setMemName(array[0]);
                                newTik.setMemID(array[1]);
                            }
                            chain.add(String.valueOf(namNID));
                        }
                        newTik.setChainCust(chain);
//                        Set if disk is backed up and how
                        if (yesRadioButton.isSelected()) {
                            newTik.setBackUp(true);
                            newTik.setMethod(externalHardDriveRadioButton.isSelected() ? "External" : "Cloud");
                        }
//                    If there is a resolution entered automatically add it to the resolved
//                    ticket store. Otherwise add it to the ticket store
                        if(taResolution.getText()!= null || taResolution.getText().isEmpty()){
                            newTik.setResol(taResolution.getText());
                            manager.resolveTicket(newTik);
                        }else {
                            manager.ticketStore.add(newTik);
                        }
//                        Clear values from tracking log inputs after ticket is added
                        clearBoxesTrak();

//                    Exceptions
                    }catch (NumberFormatException nfe){
                        showMessageDialog("Quit being a jerk. Try again.");
                        System.out.println(nfe);
                    }catch (NullPointerException npe){
                        System.out.println(npe);
                    }catch (Exception t){
                        System.out.println(t);
                    }
                }
            }
        });


        searchDescButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check to see if the textbox is empty
                if (textSrchDesc.getText().trim().isEmpty()){
                    showMessageDialog("Please enter search terms into the " +
                                    "text box");
                }else {
                    // if there are tickets
                    if(manager.searchByDescription(textSrchDesc.getText()).size()!=0){
                        LinkedList<Ticket> results = manager.searchByDescription(textSrchDesc.getText());
                        for (Ticket result :
                                results) {
                            if(chainListMo.getSize()>1){
                                tikListMo.addElement(result.toString2());
                            }
                            tikListMo.addElement(result.toString());
                        }
                        clearBoxesView();

                        //if there aren't tickets
                    }else {
                        clearBoxesView();
                        showMessageDialog("Sorry there were no tickets that " +
                                "matched your description");
                    }
                }
            }
        });

        searchIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // try just in case the user enters a non-numeric into box
                // check to see if the textbox is empty
                if (textSrchID.getText().trim().isEmpty()){
                    showMessageDialog("Please enter valid number into the " +
                            "text box");
                }else {
                    // if there are tickets
                    try {
                        if (manager.searchById(Integer.parseInt(textSrchID.getText())) != null) {
                            Ticket result = manager.searchById(Integer.parseInt(textSrchID.getText()));
                            if (chainListMo.getSize() > 1) {
                                tikListMo.addElement(result.toString2());
                            }
                            tikListMo.addElement(result.toString());

                            clearBoxesView();

                            //if there aren't tickets
                        } else {
                            showMessageDialog("Sorry there were no tickets that " +
                                    "matched your description.");
                        }
                    } catch (NumberFormatException nfe) {
                        clearBoxesView();
                        showMessageDialog("Please enter a valid number");
                    }
                }
            }
        });

//TODO: Add delete club member button and ticket from ticket viewer

//        deleteSelectedButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(ticketList.isSelectionEmpty()){
//                    showMessageDialog("Please select the ticket you wish to " +
//                            "delete from the list");
//                }else {
//                    String resolution = showInputDialog("How was the " +
//                            "issue resolved?");
//                    if(resolution!=null) {
//                        Ticket toDelete = ticketList.getSelectedValue();
//                        toDelete.setResDesc(resolution);
//                        toDelete.setDateRes();
//                        showMessageDialog("Your ticket was successfully deleted");
//                        manager.resolveTicket(toDelete);
//                        listModel.removeElement(toDelete);
//                        ticketListStatusDescription.setText(ALL_TICKETS);
//                    }
//                }
//            }
//        });
        saveAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitProgram();
            }
        });
    }

    // Call this method to quit the program. The tests expect you to use it.
    protected void quitProgram() {
        manager.quitProgram();
        this.dispose();
    }


    // Use this method to show message dialogs displaying the message given.
    // Otherwise tests for code that shows alert dialogs will time out and fail.
    protected void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Use this method to show input dialogs asking the given question.
    // Otherwise tests for code that shows input dialogs will time out and fail.
    // If user presses the cancel button, this method will return null.
    protected String showInputDialog(String question) {
        return JOptionPane.showInputDialog(this, question);
    }


}
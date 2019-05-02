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
    protected JList<LinkedList> chanOfCustody;
//    Radio Buttons and groups
//    Backed up?
    protected JRadioButton noRadioButton;
    protected JRadioButton yesRadioButton;
    protected ButtonGroup yesNoGroup;
//    How?
    protected JRadioButton externalHardDriveRadioButton;
    protected JRadioButton cloudBasedStorageRadioButton;
    protected ButtonGroup backUpGroup;
//    Spinner
    protected JSpinner dateSpinner;
//    Checkboxes
    protected JCheckBox diskCleanUpCheckBox;
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
//    Text Area
    protected JTextArea taProbDesc;
    protected JTextArea taTikDisplay;
    protected JTextArea taResolution;

    //    Create the list model and instantiate the main class
    protected DefaultListModel<LinkedList>listModel;
    Date date= new Date();
    MainClass manager;

    TrackingLog(MainClass manager) {

        this.manager = manager;

        setContentPane(mainPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        rootPane.setDefaultButton(submitInformationButton);

        configDateSpinner();
        listModel = new DefaultListModel<>();
        chanOfCustody.setModel(listModel);
        chanOfCustody.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // displaying the ticket queue at the beginning of the program you jerk
        displayList(manager.ticketStore.getAllTickets());

        addListeners();

    }
    //TODO: find the garden program and copy the JSpinner
    private void configDateSpinner() {
//        priorityComboBox.setModel(new DefaultComboBoxModel(priority));
    }

    private void displayList(LinkedList<> list){
        listModel.removeAllElements();
        LinkedList<LinkedList<String>> name = new LinkedList<>();
        for (Object item:
                list) {
            listModel.addElement(item);
        }
    }

    private void displayElement(Ticket newElement) {
        listModel.addElement(newElement);
    }

    /**
     * @param,
     * my attempt to avoid concurrent modification exception TY stack overflow
     */
//    private void deleteFrmList() {
//
//        for (Ticket t :
//                forDisplayList) {
//            forDisplayList.remove(t);
//        }
//
////        Iterator<Ticket> iter = forDisplayList.iterator();
////        while (iter.hasNext()){
////            Ticket t = iter.next();
////            iter.remove();
////    }
//    }
    private void clearBoxes(){
//        Clear Radios
        yesNoGroup.clearSelection();
        backUpGroup.clearSelection();
//        Reset Date Spinner
        dateSpinner.setValue(date);
//        Uncheck Checkboxes
        diskCleanUpCheckBox.setSelected(false);
        signedWaiverCheckBox.setSelected(false);
        consent.setSelected(false);
//        Clear Text Areas
        taProbDesc.setText("");
        taTikDisplay.setText("");
        taResolution.setText("");
//        Clear Text Boxes
        textMemStar.setText("");
        textStar.setText("");
        textCliPhone.setText("");
        textClieEmail.setText("");
        textCliName.setText("");
        textMemName.setText("");
        textSrchDesc.setText("");
        textSrchID.setText("");
    }

    private void addListeners(){
        submitInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                Checking that there is data for; Client student name, email, star Id; Club member name,
//                star ID; problem description, waiver is signed and consent to services are present
                if(textClieEmail.getText().trim().isEmpty() || textCliName.getText().trim().isEmpty()||
                listModel.isEmpty()|| textStar.getText().trim().isEmpty()||taProbDesc.getText().trim().isEmpty()||
                        !consent.isSelected()|| !signedWaiverCheckBox.isSelected()){
                    showMessageDialog("Please enter data for all fields to add " +
                            "ticket");
                }else {
                    try {
                        Ticket newTik = new Ticket();

                        newTik.setClientName(textCliName.getText());
                        newTik.setClientID(textStar.getText());
                        newTik.setClientMail(textClieEmail.getText());
//                        Only add phone number if the text is present
                        if(!textCliPhone.getText().trim().isEmpty()){
                            newTik.setClientPhone(textCliPhone.getText());
                        }
                        newTik.setSignedWaiver(true);
                        for (:
                             ) {

                        }
                        //                    showMessageDialog("Your ticket was successfully added\n" +
                        //                            "                        ^____^\n"+newTik);
                        //                    forDisplayList = manager.getAllTickets().stream().collect(Collectors.toCollection(LinkedList::new));
                        displayList(manager.ticketStore.getAllTickets());
                        //                    displayList(forDisplayList);
                        clearBoxes();
                    }
//                    catch (NumberFormatException nfe){
//                        priorityComboBox.setSelectedIndex(0);
//                    }
                    catch (NullPointerException npe){
                        priorityComboBox.setSelectedIndex(0);
                        showMessageDialog("Please enter a number for the priority of the ticket, 1-5.");
                    }catch (Exception t){
                        System.out.println(t);
                    }
                }
            }
        });

        searchDescriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check to see if the textbox is empty
                if (descriptionSearchTextBox.getText().trim().isEmpty()){
                    listModel.removeAllElements();
                    ticketListStatusDescription.setText(NO_TICKETS_FOUND);
                    clearBoxes();
                }else {
                    //clear Jlist for use
                    listModel.removeAllElements();
                    //clear reusable list for use
//                    if(!forDisplayList.isEmpty()){
////                        forDisplayList.clear();
//                    }
                    // add list of matching tickets to reusable list
//                    forDisplayList = manager.searchByDescription(descriptionSearchTextBox.getText());
                    // if there are tickets
                    if(manager.searchByDescription(descriptionSearchTextBox.getText()).size()!=0){
                        ticketListStatusDescription.setText(TICKETS_MATCHING_DESCRIPTION);
                        displayList(manager.searchByDescription(descriptionSearchTextBox.getText()));
                        clearBoxes();

                        //if there aren't tickets
                    }else {
                        ticketListStatusDescription.setText(NO_TICKETS_FOUND);
                        clearBoxes();
                    }
                }
            }
        });

        searchIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // try just in case the user enters a non-numeric into box
                try {
                    //if to check if the box is empty or has a bs index
                    if (idSearchTextBox.getText().trim().isEmpty() ||
                            Integer.parseInt(idSearchTextBox.getText()) < 0){
                        listModel.removeAllElements();
                        ticketListStatusDescription.setText(INVALID_TICKET_ID);
                        clearBoxes();
                    }else {
                        // clear the Jlist for the future list
                        listModel.removeAllElements();
                        //clear the reusable list in order for new use
//                        if(!forDisplayList.isEmpty()){
//                            forDisplayList.clear();
//                        }
                        // find ticket
                        Ticket idTik = manager.searchById(Integer.parseInt(idSearchTextBox.getText()));
                        //if for no ticket
                        if(idTik==null){
                            ticketListStatusDescription.setText(NO_TICKETS_FOUND);
                            clearBoxes();
                        }else {
                            ticketListStatusDescription.setText(TICKET_MATCHING_ID);
//                            forDisplayList.add(idTik);
//                            displayList(forDisplayList);
                            displayElement(manager.searchById(Integer.parseInt(idSearchTextBox.getText())));
                            clearBoxes();
                        }
                    }
                }catch (NumberFormatException nfe){
                    listModel.removeAllElements();
                    ticketListStatusDescription.setText(INVALID_TICKET_ID);
                    clearBoxes();
                }
            }
        });

        showAllTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!forDisplayList.isEmpty()){
                    forDisplayList.clear();}
                forDisplayList = manager.getAllTickets().stream().collect(Collectors.toCollection(LinkedList::new));
                displayList(forDisplayList);
                ticketListStatusDescription.setText(ALL_TICKETS);
            }
        });

        deleteSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ticketList.isSelectionEmpty()){
                    showMessageDialog("Please select the ticket you wish to " +
                            "delete from the list");
                }else {
                    String resolution = showInputDialog("How was the " +
                            "issue resolved?");
                    if(resolution!=null) {
                        Ticket toDelete = ticketList.getSelectedValue();
                        toDelete.setResDesc(resolution);
                        toDelete.setDateRes();
                        showMessageDialog("Your ticket was successfully deleted");
                        manager.resolveTicket(toDelete);
                        listModel.removeElement(toDelete);
                        ticketListStatusDescription.setText(ALL_TICKETS);
                    }
                }
            }
        });
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
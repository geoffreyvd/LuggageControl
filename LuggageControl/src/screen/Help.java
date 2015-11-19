/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen;

import baseClasses.ErrorJDialog;
import baseClasses.SwitchingJPanel;
import constants.ScreenNames;
import javax.swing.JList;
import main.LuggageControl;

/**
 *
 * @author root
 */
public class Help extends SwitchingJPanel{

    // <editor-fold defaultstate="collapsed" desc="helpListDoubleClick">
    private int helpListDoubleClick = 0;
    // default value is -1 because -1 can never be a index
    private int helpListPreviousValue = -1;
    // </editor-fold>
    
    /**
     * Creates new form Help
     */
    public Help(LuggageControl luggageControl) {
        super(luggageControl);
        initComponents();
    }
    
    /**
     * Open the next help screen based on which helpList item was pressed.
     * @param panelName name of the screen which can be found in ScreenConstants
     */
    public void openHelpScreen(String listItemName) {
        if(listItemName.equals(helpList.getModel().getElementAt(0))) {
            luggageControl.switchJPanel(ScreenNames.Help.ADDING);
            luggageControl.switchTab(screen.help.Adding.ADD_CUSTOMER, ScreenNames.Help.ADDING);
        }
        else if(listItemName.equals(helpList.getModel().getElementAt(1))) {
            luggageControl.switchJPanel(ScreenNames.Help.ADDING);
            luggageControl.switchTab(screen.help.Adding.ADD_FLIGHTS, ScreenNames.Help.ADDING);
        }
        else if(listItemName.equals(helpList.getModel().getElementAt(2))) {
            luggageControl.switchJPanel(ScreenNames.Help.ADDING);
            luggageControl.switchTab(screen.help.Adding.ADD_LUGGAGE, ScreenNames.Help.ADDING);
        }
        else if(listItemName.equals(helpList.getModel().getElementAt(3))) {
            luggageControl.switchJPanel(ScreenNames.Help.FINDING);
            luggageControl.switchTab(screen.help.Finding.FIND_CUSTOMER, ScreenNames.Help.FINDING);
        }
        else if(listItemName.equals(helpList.getModel().getElementAt(4))) {
            luggageControl.switchJPanel(ScreenNames.Help.FINDING);
            luggageControl.switchTab(screen.help.Finding.FIND_FLIGHTS, ScreenNames.Help.FINDING);
        }
        else if(listItemName.equals(helpList.getModel().getElementAt(5))) {
            luggageControl.switchJPanel(ScreenNames.Help.FINDING);
            luggageControl.switchTab(screen.help.Finding.FIND_LUGGAGE, ScreenNames.Help.FINDING);
        }
        else if(listItemName.equals(helpList.getModel().getElementAt(6))) {
            luggageControl.switchJPanel(ScreenNames.Help.FINDING);
            luggageControl.switchTab(screen.help.Finding.FIND_LINKS, ScreenNames.Help.FINDING);
        }
        else if(listItemName.equals(helpList.getModel().getElementAt(7))) {
            luggageControl.switchJPanel(ScreenNames.Help.LINKING);
            luggageControl.switchTab(screen.help.Linking.CUSTOMER_TO_FLIGHTS, ScreenNames.Help.LINKING);
        }
        else if(listItemName.equals(helpList.getModel().getElementAt(8))) {
            luggageControl.switchJPanel(ScreenNames.Help.LINKING);
            luggageControl.switchTab(screen.help.Linking.FLIGHTS_TO_LUGGAGE, ScreenNames.Help.LINKING);
        }
        else if(listItemName.equals(helpList.getModel().getElementAt(9))) {
            luggageControl.switchJPanel(ScreenNames.Help.LINKING);
            luggageControl.switchTab(screen.help.Linking.LUGGAGE_TO_CUSTOMER, ScreenNames.Help.LINKING);
        }
        else if(listItemName.equals(helpList.getModel().getElementAt(10))) {
            luggageControl.switchJPanel(ScreenNames.Help.LINKING);
            luggageControl.switchTab(screen.help.Linking.LUGGAGE_TO_LUGGAGE, ScreenNames.Help.LINKING);
        }
        else {
            new ErrorJDialog(listItemName, (new Throwable()).getStackTrace());
        }
//            case helpList.getComponent(0).getName():
//                luggageControl.switchJPanel(pane);
//            default:
//                new ErrorJDialog("Error: selected item does not reference screen", (new Throwable()).getStackTrace());
//                break;
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelScreenHelp = new javax.swing.JLabel();
        buttonBack = new javax.swing.JButton();
        scrollPaneHelpList = new javax.swing.JScrollPane();
        helpList = new javax.swing.JList();

        labelScreenHelp.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        labelScreenHelp.setText("Help");

        buttonBack.setText("Back");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackbackToPreviousScreen(evt);
            }
        });

        scrollPaneHelpList.setPreferredSize(new java.awt.Dimension(1920, 1080));
        scrollPaneHelpList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                helpListKeyPressed(evt);
            }
        });

        helpList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Add new customer [employee]", "Add new flight [employee]", "Add new luggage [employee]", "Find customer [employee - administrator]", "Find flights [employee - administrator]", "Find luggage [employee - administrator]", "Find linkes between customers, luggage and flights [employee - administrator]", "Linking customers to flights [employee]", "Linking flights to luggage [employee]", "Linking luggage to customers [employee]", "Linking lost luggage to found luggage [employee]" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        helpList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        helpList.setToolTipText("");
        helpList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpListMouseClicked(evt);
            }
        });
        helpList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                helpListKeyPressed(evt);
            }
        });
        scrollPaneHelpList.setViewportView(helpList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneHelpList, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelScreenHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelScreenHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneHelpList, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBackbackToPreviousScreen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackbackToPreviousScreen
        luggageControl.switchPreviousPanel();
    }//GEN-LAST:event_buttonBackbackToPreviousScreen

    private void helpListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpListMouseClicked
        JList tempItem = null;
        try {
            tempItem = (javax.swing.JList<String>)evt.getComponent();
        }
        catch(Exception e) {
        }
        if(helpListPreviousValue == tempItem.getSelectedIndex()) {
            helpListDoubleClick++;
        }
        else {
            helpListPreviousValue = tempItem.getSelectedIndex();
            helpListDoubleClick = 0;
        }
        
        if(helpListDoubleClick >= 1) {
            openHelpScreen(tempItem.getSelectedValue().toString());
            // new ErrorJDialog("Error: tab does not exist", (new Throwable()).getStackTrace());
        }
    }//GEN-LAST:event_helpListMouseClicked

    private void helpListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_helpListKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER) {
            JList tempItem = null;
            try {
                tempItem = (javax.swing.JList<String>)evt.getComponent();
                openHelpScreen(tempItem.getSelectedValue().toString());
            }
            catch(Exception e) {
            }
        }
    }//GEN-LAST:event_helpListKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBack;
    private javax.swing.JList helpList;
    private javax.swing.JLabel labelScreenHelp;
    private javax.swing.JScrollPane scrollPaneHelpList;
    // End of variables declaration//GEN-END:variables
}

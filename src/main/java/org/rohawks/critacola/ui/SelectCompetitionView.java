package org.rohawks.critacola.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.rohawks.critacola.CritController;
import org.rohawks.critacola.beans.*;
import org.rohawks.critacola.util.Helper;

/**
 * The first thing the user sees. Controls competition selection.
 */
public class SelectCompetitionView extends CritView {
    private JList list;
    private DefaultListModel listModel;
    private JButton selectButton;

    private JPanel formPanel;
    private JTextField competitionNameField;
    private JTextField competitionLocationField;
    private final String BUTTON_CARD = "button";
    private final String PANEL_CARD = "panel";

    public SelectCompetitionView( CritController controller ) {
        super( controller );
    }

    @Override
    public String getName() {
        return "Select Competition";
    }

    @Override
    public void refresh() {
        CompetitionList competitionList =
            controller.getModel().getCompetitionList();
        listModel.clear();
        for( Competition c : competitionList.getCompetitions() ) {
            listModel.addElement( c.getName() );
        }
        selectButton.setEnabled( !competitionList.isEmpty() &&
                                 list.getSelectedIndex() >= 0 );
    }

    @Override
    public boolean providesBackButton() {
        return false;
    }

    @Override
    public void buildPanel() {
        buildForm();

        panel = new JPanel( new BorderLayout() );
        panel.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );

        listModel = new DefaultListModel();
        list = new JList( listModel );
        list.addMouseListener( controller.getSelectCompetitionMouseAdapter() );
        panel.add( new JScrollPane( list ) );

        JPanel buttonPanel = new JPanel( new GridLayout( 1, 2, 10, 0 ) );
        buttonPanel.setBorder( BorderFactory.createEmptyBorder( 10, 0, 0, 0 ) );
        JButton addButton = new JButton( "Add Competition" );
        Helper.setComponentFontSize( addButton, 18 );
        addButton
            .addActionListener( controller.getActionListener( addButton ) );
        buttonPanel.add( addButton );

        selectButton = new JButton( "Select Competition" );
        Helper.setComponentFontSize( selectButton, 18 );
        selectButton
            .addActionListener( controller.getActionListener( selectButton ) );
        buttonPanel.add( selectButton );
        panel.add( buttonPanel, BorderLayout.SOUTH );
    }

    private void buildForm() {

        // Initialize form and layout
        formPanel = new JPanel();
        GroupLayout layout = new GroupLayout( formPanel );
        formPanel.setLayout( layout );
        layout.setAutoCreateGaps( true );
        layout.setAutoCreateContainerGaps( true );

        // Create components
        JLabel nameLabel = new JLabel( "Name" );
        JLabel locationLabel = new JLabel( "Location" );
        competitionNameField = new JTextField( 20 );
        competitionNameField
            .addAncestorListener( Helper.getRequestFocusListener() );
        Helper.setComponentFontSize( competitionNameField, 18 );
        competitionLocationField = new JTextField( 20 );
        Helper.setComponentFontSize( competitionLocationField, 18 );

        // Create layout
        GroupLayout.Alignment TRAILING = GroupLayout.Alignment.TRAILING,
            LEADING = GroupLayout.Alignment.LEADING,
            BASELINE = GroupLayout.Alignment.BASELINE;
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup( layout
                          .createParallelGroup( TRAILING )
                           .addComponent( nameLabel )
                           .addComponent( locationLabel ) )
                .addGroup( layout
                           .createParallelGroup( LEADING )
                           .addComponent( competitionNameField )
                           .addComponent( competitionLocationField ) )
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup( layout
                           .createParallelGroup( BASELINE )
                           .addComponent( nameLabel )
                           .addComponent( competitionNameField ) )
                .addGroup( layout
                           .createParallelGroup( BASELINE )
                           .addComponent( locationLabel )
                           .addComponent( competitionLocationField ) )
        );
    }

    /**
     * Shows a dialog and gets a new Competition.
     */
    public Competition getCompetitionFromUser() {

        // Clear the form before we show it
        competitionNameField.setText( "" );
        competitionLocationField.setText( "" );

        // Loop until we get a legit answer
        do {
            JOptionPane.showMessageDialog( controller.getViewFrame(),
                                           formPanel,
                                           "Add Competition",
                                           JOptionPane.QUESTION_MESSAGE );
        } while( competitionNameField.getText().trim().length() == 0 );

        // Construct a new competition from the form data
        return new Competition( competitionNameField.getText(),
                                competitionLocationField.getText() );
    }

    public Competition getSelectedCompetition() {
        int index = list.getSelectedIndex();
        if( index >= 0 ) {
            return controller
                .getModel()
                .getCompetitionList()
                .getCompetitions()
                .get( index );
        } else {
            return null;
        }
    }
}

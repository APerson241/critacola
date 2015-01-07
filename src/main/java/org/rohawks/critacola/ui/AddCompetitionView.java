package org.rohawks.critacola.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import org.rohawks.critacola.*;
import org.rohawks.critacola.beans.*;
import org.rohawks.critacola.util.*;

/**
 * The form for adding a competition.
 */
public class AddCompetitionView extends CritView {
    private JTextField name;
    private JTextField location;
    private JButton submitButton;
    private DefaultListModel listModel;

    public AddCompetitionView( CritController controller ) {
        super( controller );
    }

    @Override
    public String getName() {
        return "Add Competition";
    }

    @Override
    public void refresh() {
        boolean nameNonEmpty = name.getText().length() > 0;
        submitButton.setEnabled( nameNonEmpty );
    }

    @Override
    public boolean providesBackButton() {
        return true;
    }

    @Override
    protected void buildPanel() {
        panel = new JPanel( new BorderLayout() );

        // Top pair of panels (metadata, team list)
        JPanel topPanel = new JPanel( new GridLayout( 2, 1, 10, 10 ) );
        JPanel metadataPanel = new JPanel( new GridLayout( 2, 2, 10, 10 ) );
        metadataPanel.setBorder(
                BorderFactory.createTitledBorder( "Competition Information" ) );
        metadataPanel.add( new JLabel( "Name (required)" ) );
        name = new JTextField( 20 );
        name.addKeyListener( controller.getRefreshOnKeystrokeListener() );
        Helper.setComponentFontSize( name, 18 );
        metadataPanel.add( name );
        metadataPanel.add( new JLabel( "Location" ) );
        location = new JTextField( 20 );
        location.addKeyListener( controller.getRefreshOnKeystrokeListener() );
        Helper.setComponentFontSize( location, 18 );
        metadataPanel.add( location );
        topPanel.add( metadataPanel );

        JPanel teamListPanel = new JPanel( new BorderLayout() );
        teamListPanel.setBorder(
                BorderFactory.createTitledBorder(
                "Team List (can be empty)" ) );
        listModel = new DefaultListModel();
        listModel.addElement( "3419" );
        JList teamList = new JList( listModel );
        teamListPanel.add( new JScrollPane( teamList ), BorderLayout.CENTER );
        JPanel teamButtonsPanel = new JPanel( new GridLayout( 2, 2, 5, 5 ) );
        teamButtonsPanel.setBorder(
                BorderFactory.createEmptyBorder( 0, 5, 0, 0 ) );
        JButton addTeamButton = new JButton( "Add Team" );
        addTeamButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent event ) {
                String team = JOptionPane.showInputDialog(
                        controller.getViewFrame(),
                        "Enter a team number.",
                        "Add Team",
                        JOptionPane.QUESTION_MESSAGE );
                listModel.addElement( team );
            }
        } );
        teamButtonsPanel.add( addTeamButton );
        JButton removeTeamButton = new JButton( "Remove Team" );
        removeTeamButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent event ) {
                int index = teamList.getSelectedIndex();
                if( index < 0 ) {
                    return;
                }
                listModel.remove( index );
                if( index == listModel.getSize() ) {
                    --index;
                }
                teamList.setSelectedIndex( index );
                teamList.ensureIndexIsVisible( index );
            }
        } );
        teamButtonsPanel.add( removeTeamButton );
        teamListPanel.add( teamButtonsPanel, BorderLayout.EAST );
        topPanel.add( teamListPanel );
        panel.add( topPanel, BorderLayout.CENTER );

        submitButton = new JButton( "Create Competition" );
        submitButton.addActionListener(
                controller.getActionListener( submitButton ) );
        submitButton.setEnabled( false );
        panel.add( submitButton, BorderLayout.SOUTH );
    }

    /**
     * Build a Competition from the information in the form fields.
     */
    public Competition getCompetition() {
        return new Competition( name.getText(), location.getText() );
    }

    public void clearForm() {
        name.setText( "" );
        location.setText( "" );
        listModel.clear();
    }
}


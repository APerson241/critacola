package org.rohawks.critacola.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

import org.rohawks.critacola.*;
import org.rohawks.critacola.beans.*;
import org.rohawks.critacola.util.*;

/**
 * A panel containing a form to enter a match.
 */
class EnterMatchRecordPanel {
    private JPanel panel;
    private JLabel matchLabel;
    private JTextField teamField;
    private JCheckBox noShowBox;
    private JCheckBox nonFunctionalBox;
    private JComboBox autoDropdown;
    private JTextArea stackArea;
    private JTextArea coopArea;
    private JTextField litterAttemptedField;
    private JTextField litterMadeField;
    private JSlider humanPlayerSlider;
    private JSlider driveTrainSlider;
    private JSlider buildQualitySlider;
    private JTextArea restrictionsArea;
    private JTextArea commentsArea;
    private JButton submitButton;

    private CritController controller;

    public EnterMatchRecordPanel( CritController controller ) {
        this.controller = controller;
        buildPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    public Team getTeam() {
        try {
            int teamNumber = Integer.parseInt( teamField.getText() );
            return Team.get( teamNumber );
        } catch( NumberFormatException ex ) {
            return null;
        }
    }

    public MatchRecord getMatchRecord() {
        return new MatchRecord( getTeam(),
                                noShowBox.isSelected(),
                                nonFunctionalBox.isSelected(),
                                (String)autoDropdown.getSelectedItem(),
                                stackArea.getText(),
                                coopArea.getText(),
                                Integer.parseInt( litterAttemptedField
                                                  .getText() ),
                                Integer.parseInt( litterMadeField.getText() ),
                                humanPlayerSlider.getValue(),
                                driveTrainSlider.getValue(),
                                buildQualitySlider.getValue(),
                                restrictionsArea.getText(),
                                commentsArea.getText() );
    }

    private Border titled( String title ) {
        return BorderFactory.createTitledBorder( title );
    }

    public void addSubmitListener( ActionListener listener ) {
        submitButton.addActionListener( listener );
    }

    public void refresh() {
        submitButton.setEnabled( getTeam() != null );
    }

    private void buildPanel() {
        panel = new JPanel( new GridLayout( 1, 8 ) );

        JPanel metadataPanel = new JPanel( new GridLayout( 2, 1 ) );
        JPanel metadataTopPanel = new JPanel( new GridLayout( 2, 2 ) );
        metadataTopPanel.setBorder( titled( "Metadata" ) );
        metadataTopPanel.add( new JLabel( "Match" ) );
        matchLabel = new JLabel();
        metadataTopPanel.add( matchLabel );
        metadataTopPanel.add( new JLabel( "Team #" ) );
        teamField = new JTextField( 5 );
        teamField.addKeyListener( controller.getRefreshOnKeystrokeListener() );
        Helper.setComponentFontSize( teamField, 22 );
        metadataTopPanel.add( teamField );
        metadataPanel.add( metadataTopPanel );
        JPanel metadataBottomPanel = new JPanel( new GridLayout( 2, 2 ) );
        noShowBox = new JCheckBox( "No-show?" );
        metadataBottomPanel.add( noShowBox );
        nonFunctionalBox = new JCheckBox( "Non-functional?" );
        metadataBottomPanel.add( nonFunctionalBox );
        metadataPanel.add( metadataBottomPanel );
        panel.add( metadataPanel );

        JPanel autoPanel = new JPanel( new BorderLayout() );
        autoPanel.setBorder( titled( "Autonomous" ) );
        JPanel autoInnerPanel = new JPanel();
        autoDropdown = new JComboBox( new String[] { "Robot set",
                                                     "Tote set",
                                                     "Tote stack",
                                                     "Bin set" } );
        autoInnerPanel.add( autoDropdown );
        autoPanel.add( autoInnerPanel );
        panel.add( autoPanel );

        JPanel stackPanel = new JPanel( new BorderLayout() );
        stackPanel.setBorder( titled( "Stacks" ) );
        stackArea = new JTextArea();
        stackPanel.add( stackArea );
        panel.add( stackPanel );

        JPanel coopPanel = new JPanel( new BorderLayout() );
        coopPanel.setBorder( titled( "Coopertition" ) );
        coopArea = new JTextArea();
        coopPanel.add( coopArea );
        panel.add( coopPanel );

        JPanel litterPanel = new JPanel( new GridLayout( 2, 1 ) );
        litterPanel.setBorder( titled( "Litter (human player)" ) );
        litterPanel.add( new JLabel( "Attempted / made" ) );
        JPanel litterBottomPanel = new JPanel();
        litterAttemptedField = new JTextField( 2 );
        litterAttemptedField.setText( "0" );
        litterBottomPanel.add( litterAttemptedField );
        litterBottomPanel.add( new JLabel( " / " ) );
        litterMadeField = new JTextField( 2 );
        litterMadeField.setText( "0" );
        litterBottomPanel.add( litterMadeField );
        litterPanel.add( litterBottomPanel );
        panel.add( litterPanel );

        JPanel ratingsPanel = new JPanel( new GridLayout( 3, 2 ) );
        ratingsPanel.setBorder( titled( "Ratings" ) );
        ratingsPanel.add( new JLabel( "Human player" ) );
        humanPlayerSlider = new JSlider( 1, 5, 3 );
        humanPlayerSlider.setMajorTickSpacing( 1 );
        humanPlayerSlider.setPaintTicks( true );
        ratingsPanel.add( humanPlayerSlider );
        ratingsPanel.add( new JLabel( "Drivetrain" ) );
        driveTrainSlider = new JSlider( 1, 5, 3 );
        driveTrainSlider.setMajorTickSpacing( 1 );
        driveTrainSlider.setPaintTicks( true );
        ratingsPanel.add( driveTrainSlider );
        ratingsPanel.add( new JLabel( "Build quality" ) );
        buildQualitySlider = new JSlider( 1, 5, 3 );
        buildQualitySlider.setMajorTickSpacing( 1 );
        buildQualitySlider.setPaintTicks( true );
        ratingsPanel.add( buildQualitySlider );
        panel.add( ratingsPanel );

        JPanel finalPanel = new JPanel( new BorderLayout() );
        JPanel commentsPanel = new JPanel( new GridLayout( 2, 1 ) );
        JPanel commentsRestrPanel = new JPanel( new BorderLayout() );
        commentsRestrPanel.setBorder( titled( "Restrictions" ) );
        restrictionsArea = new JTextArea();
        commentsRestrPanel.add( restrictionsArea );
        commentsPanel.add( commentsRestrPanel );
        JPanel commentsCommntsPanel = new JPanel( new BorderLayout() );
        commentsCommntsPanel.setBorder( titled( "Comments" ) );
        commentsArea = new JTextArea();
        commentsCommntsPanel.add( commentsArea );
        commentsPanel.add( commentsCommntsPanel );
        finalPanel.add( commentsPanel );
        submitButton = new JButton( "Enter Match" );
        Helper.setComponentFontSize( submitButton, 14 );
        finalPanel.add( submitButton, BorderLayout.SOUTH );
        panel.add( finalPanel );
    }

    public void setMatch( String match ) {
        matchLabel.setText( match );
    }

    public void setEnabled( boolean enabled ) {
        setContainerEnabled( panel, enabled );
    }

    private void setContainerEnabled( Container parent, boolean enabled ) {
        for( Component c : parent.getComponents() ) {
            c.setEnabled( enabled );
            if( c instanceof Container ) {
                setContainerEnabled( (Container)c, enabled );
            }
        }
    }

    public void clearForm() {
        clearContainer( panel );
        litterAttemptedField.setText( "0" );
        litterMadeField.setText( "0" );
    }

    private void clearContainer( Container parent ) {
        for( Component c : parent.getComponents() ) {
            if( c instanceof JTextComponent ) {
                ( (JTextComponent)c ).setText( "" );
            } else if( c instanceof JComboBox ) {
                ( (JComboBox)c ).setSelectedIndex( -1 );
            } else if( c instanceof JSlider ) {
                ( (JSlider)c ).setValue( 3 );
            } else if( c instanceof Container ) {
                clearContainer( (Container)c );
            }
        }
    }
}

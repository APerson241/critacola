package org.rohawks.critacola.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import org.rohawks.critacola.*;
import org.rohawks.critacola.beans.*;
import org.rohawks.critacola.util.*;

/**
 * The form for entering a match.
 */
public class EnterMatchView extends CritView {
    private enum FormStatus { READY, R1, R2, R3, B1, B2, B3 };
    private FormStatus formStatus = FormStatus.READY;

    private JButton submitButton;
    private JTextField competitionName;
    private ButtonGroup whoWonGroup;
    private JCheckBox coopertitionAchieved;

    private TeamPanel r1Panel;
    private TeamPanel r2Panel;
    private TeamPanel r3Panel;
    private TeamPanel b1Panel;
    private TeamPanel b2Panel;
    private TeamPanel b3Panel;

    private MatchRecord r1;
    private MatchRecord r2;
    private MatchRecord r3;
    private MatchRecord b1;
    private MatchRecord b2;
    private MatchRecord b3;

    private JTextField teamField;
    private JSlider pickupSlider;
    private JSlider driveTrainSlider;
    private JSlider driverSlider;
    private JTextArea commentsArea;
    private JButton submitMatchRecordButton;

    private static final Color RED_ENTERED = new Color( 255, 75, 75 );
    private static final Color RED_NONENTERED = Color.RED;
    private static final Color BLUE_ENTERED = new Color( 75, 75, 255 );
    private static final Color BLUE_NONENTERED = Color.BLUE;

    public EnterMatchView( CritController controller ) {
        super( controller );
    }

    @Override
    public String getName() {
        return "Enter Match";
    }

    @Override
    public void refresh() {
        if( formStatus == FormStatus.READY ) {
            submitButton.setEnabled( competitionName.getText().length() > 0 );
        } else {
            submitButton.setEnabled( false );
        }
    }

    @Override
    public boolean providesBackButton() {
        return true;
    }

    @Override
    protected void buildPanel() {
        panel = new JPanel( new BorderLayout() );

        JPanel competitionPanel = new JPanel( new GridLayout( 1, 3, 5, 5 ) );
        JPanel competitionNamePanel = new JPanel(
                new GridLayout( 2, 1, 5, 5 ) );
        competitionNamePanel.add( new JLabel( "Competition Name" ) );
        competitionName = new JTextField( 20 );
        competitionName.addKeyListener(
                controller.getRefreshOnKeystrokeListener() );
        competitionNamePanel.add( competitionName );
        competitionPanel.add( competitionNamePanel );
        JPanel whoWonPanel = new JPanel( new GridLayout( 3, 1, 5, 5 ) );
        whoWonPanel.add( new JLabel( "Who won?" ) );
        whoWonGroup = new ButtonGroup();
        JRadioButton redAllianceWon = new JRadioButton( "Red alliance" );
        redAllianceWon.setActionCommand( "Red alliance" );
        whoWonGroup.add( redAllianceWon );
        whoWonPanel.add( redAllianceWon );
        JRadioButton blueAllianceWon = new JRadioButton( "Blue alliance" );
        blueAllianceWon.setActionCommand( "Blue alliance" );
        whoWonGroup.add( blueAllianceWon );
        whoWonPanel.add( blueAllianceWon );
        competitionPanel.add( whoWonPanel );
        coopertitionAchieved = new JCheckBox( "Coopertition bonus achieved?" );
        competitionPanel.add( coopertitionAchieved );
        panel.add( competitionPanel, BorderLayout.NORTH );

        JPanel middlePanel = new JPanel( new GridLayout( 2, 1 ) );

        JPanel teamsPanel = new JPanel( new GridLayout( 2, 3 ) );
        r1Panel = new TeamPanel( AllianceColor.RED );
        teamsPanel.add( r1Panel.getPanel() );
        r2Panel = new TeamPanel( AllianceColor.RED );
        teamsPanel.add( r2Panel.getPanel() );
        r3Panel = new TeamPanel( AllianceColor.RED );
        teamsPanel.add( r3Panel.getPanel() );
        b1Panel = new TeamPanel( AllianceColor.BLUE );
        teamsPanel.add( b1Panel.getPanel() );
        b2Panel = new TeamPanel( AllianceColor.BLUE );
        teamsPanel.add( b2Panel.getPanel() );
        b3Panel = new TeamPanel( AllianceColor.BLUE );
        teamsPanel.add( b3Panel.getPanel() );
        middlePanel.add( teamsPanel );

        JPanel matchRecordPanel = new JPanel( new GridLayout( 1, 3 ) );
        JPanel teamInfoPanel = new JPanel();
        teamInfoPanel.setLayout( new BoxLayout(
                teamInfoPanel, BoxLayout.Y_AXIS ) );
        teamInfoPanel.add( new JLabel( "Team #" ) );
        teamField = new JTextField( 5 );
        Helper.setComponentFontSize( teamField, 22 );
        teamInfoPanel.add( teamField );
        matchRecordPanel.add( teamInfoPanel );
        JPanel ratingsPanel = new JPanel( new GridLayout( 3, 2 ) );
        ratingsPanel.setBorder( BorderFactory.createTitledBorder( "Ratings" ) );
        ratingsPanel.add( new JLabel( "Pickup" ) );
        pickupSlider = new JSlider( 1, 5, 3 );
        pickupSlider.setMajorTickSpacing( 1 );
        pickupSlider.setPaintTicks( true );
        ratingsPanel.add( pickupSlider );
        ratingsPanel.add( new JLabel( "Drivetrain" ) );
        driveTrainSlider = new JSlider( 1, 5, 3 );
        driveTrainSlider.setMajorTickSpacing( 1 );
        driveTrainSlider.setPaintTicks( true );
        ratingsPanel.add( driveTrainSlider );
        ratingsPanel.add( new JLabel( "Driver" ) );
        driverSlider = new JSlider( 1, 5, 3 );
        driverSlider.setMajorTickSpacing( 1 );
        driverSlider.setPaintTicks( true );
        ratingsPanel.add( driverSlider );
        matchRecordPanel.add( ratingsPanel );
        JPanel farRightPanel = new JPanel( new BorderLayout() );
        JPanel commentsPanel = new JPanel( new BorderLayout() );
        commentsPanel.setBorder(
                BorderFactory.createTitledBorder( "Comments" ) );
        commentsArea = new JTextArea();
        commentsPanel.add( commentsArea );
        farRightPanel.add( commentsPanel );
        submitMatchRecordButton = new JButton( "Done with Team" );
        farRightPanel.add( submitMatchRecordButton, BorderLayout.SOUTH );
        matchRecordPanel.add( farRightPanel );
        middlePanel.add( matchRecordPanel );

        panel.add( middlePanel, BorderLayout.CENTER );

        submitButton = new JButton( "Save Match" );
        submitButton.addActionListener(
                controller.getActionListener( submitButton ) );
        panel.add( submitButton, BorderLayout.SOUTH );
    }

    /**
     * Build a match from the information in the form fields.
     */
    public Match getMatch() {
        AllianceColor alliance =
                whoWonGroup.getSelection().getActionCommand().equals(
                "Red alliance" ) ? AllianceColor.RED : AllianceColor.BLUE;
        MatchRecord[] recs = new MatchRecord[] { r1, r2, r3, b1, b2, b3 };
        return new Match( alliance, coopertitionAchieved.isSelected(), recs );
    }

    public MatchRecord getMatchRecord() {
        return new MatchRecord( pickupSlider.getValue(),
                                driveTrainSlider.getValue(),
                                driverSlider.getValue(),
                                commentsArea.getText() );
    }

    public void clearForm() {
        //name.setText( "" );
        //location.setText( "" );
        //listModel.clear();
    }

    private class TeamPanel {
        private AllianceColor color;
        private boolean teamEntered;
        private JPanel panel;
        private JLabel label;
        private JButton button;

        public TeamPanel( AllianceColor color ) {
            panel = new JPanel();
            boolean red = color == AllianceColor.RED;
            panel.setBackground( red ? RED_NONENTERED : BLUE_NONENTERED );
            panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
            label = new JLabel( "Team ????" );
            Helper.setComponentFontSize( label, 18 );
            panel.add( label );
            button = new JButton( "Enter" );
            panel.add( button );
        }

        public JPanel getPanel() {
            return panel;
        }
    }
}


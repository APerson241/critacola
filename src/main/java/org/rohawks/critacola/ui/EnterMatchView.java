package org.rohawks.critacola.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import org.rohawks.critacola.*;
import org.rohawks.critacola.beans.*;
import org.rohawks.critacola.util.*;

/**
 * The form for entering a match.
 */
public class EnterMatchView extends CritView {
    private enum TeamPosition { NONE, R1, R2, R3, B1, B2, B3 }
    private TeamPosition selectedPosition = TeamPosition.NONE;

    private static final EnumSet<TeamPosition> redPositions =
            EnumSet.of( TeamPosition.R1, TeamPosition.R2, TeamPosition.R3 );
    private static final EnumSet<TeamPosition> bluePositions =
            EnumSet.complementOf( redPositions );

    private boolean enteringMatchRecord = false;

    private JTextField matchNameField;
    private ButtonGroup whoWonGroup;
    private JCheckBox coopertitionAchievedBox;
    private JButton submitButton;

    private TeamPanel r1Panel;
    private TeamPanel r2Panel;
    private TeamPanel r3Panel;
    private TeamPanel b1Panel;
    private TeamPanel b2Panel;
    private TeamPanel b3Panel;

    private List<TeamPanel> teamPanels;

    private Map<TeamPosition, MatchRecord> matchRecords;

    private EnterMatchRecordPanel emrPanel;

    public EnterMatchView( CritController controller ) {
        super( controller );
        matchRecords = new HashMap<TeamPosition, MatchRecord>();
    }

    @Override
    public String getName() {
        return "Enter Match";
    }

    @Override
    public void refresh() {
        boolean winnerSelected = whoWonGroup.getSelection() != null;
        submitButton.setEnabled( !enteringMatchRecord &&
                                 winnerSelected &&
                                 matchRecords.size() == 6 );
        for( TeamPanel p : teamPanels ) {
            p.refresh();
        }
        submitButton.setText( "Save Match" +
                              ( ( matchRecords.size() != 6 ) ?
                                ( " (" + matchRecords.size() +
                                  " out of 6 teams entered)" ) :
                                "" ) );
        emrPanel.setMatch( matchNameField.getText() );
        emrPanel.refresh();
    }

    @Override
    public boolean providesBackButton() {
        return true;
    }

    @Override
    protected void buildPanel() {
        panel = new JPanel( new BorderLayout() );

        JPanel competitionPanel = new JPanel( new GridLayout( 1, 3, 5, 5 ) );
        competitionPanel.setBorder(
                BorderFactory.createTitledBorder( "Metadata" ) );
        JPanel namePanel = new JPanel( new GridLayout( 2, 1, 5, 5 ) );
        namePanel.add( new JLabel( "Match name" ) );
        matchNameField = new JTextField( 20 );
        matchNameField
            .addKeyListener( controller.getRefreshOnKeystrokeListener() );
        namePanel.add( matchNameField );
        competitionPanel.add( namePanel );
        JPanel whoWonPanel = new JPanel( new GridLayout( 3, 1, 5, 5 ) );
        whoWonPanel.add( new JLabel( "Who won?" ) );
        whoWonGroup = new ButtonGroup();
        JRadioButton redAllianceWon = new JRadioButton( "Red alliance" );
        redAllianceWon.setActionCommand( "Red alliance" );
        redAllianceWon.addActionListener(
                controller.getRefreshActionListener() );
        whoWonGroup.add( redAllianceWon );
        whoWonPanel.add( redAllianceWon );
        JRadioButton blueAllianceWon = new JRadioButton( "Blue alliance" );
        blueAllianceWon.setActionCommand( "Blue alliance" );
        blueAllianceWon.addActionListener(
                controller.getRefreshActionListener() );
        whoWonGroup.add( blueAllianceWon );
        whoWonPanel.add( blueAllianceWon );
        competitionPanel.add( whoWonPanel );
        coopertitionAchievedBox = new JCheckBox(
                "Coopertition bonus achieved?" );
        competitionPanel.add( coopertitionAchievedBox );
        panel.add( competitionPanel, BorderLayout.NORTH );

        JPanel middlePanel = new JPanel( new GridLayout( 2, 1 ) );

        JPanel teamsPanel = new JPanel( new GridLayout( 2, 3 ) );
        teamPanels = new ArrayList<TeamPanel>();
        selectedPosition = TeamPosition.NONE;
        for( TeamPosition pos : TeamPosition.values() ) {
            if( pos == TeamPosition.NONE ) {
                continue;
            }

            TeamPanel tPanel = new TeamPanel( pos );
            teamPanels.add( tPanel );
            teamsPanel.add( tPanel.getPanel() );
        }
        middlePanel.add( teamsPanel );

        emrPanel = new EnterMatchRecordPanel( controller );
        emrPanel.setEnabled( false );
        emrPanel.addSubmitListener( new EMRPanelSubmitListener() );
        middlePanel.add( emrPanel.getPanel() );
        panel.add( middlePanel, BorderLayout.CENTER );

        submitButton = new JButton( "Save Match" );
        Helper.setComponentFontSize( submitButton, 14 );
        submitButton.addActionListener(
                controller.getActionListener( submitButton ) );
        panel.add( submitButton, BorderLayout.SOUTH );
    }

    /**
     * Build a match from the information in the form fields.
     */
    public Match getMatch() {
        String name = matchNameField.getText();
        AllianceColor allianceWhoWon =
                whoWonGroup.getSelection().getActionCommand().equals(
                "Red alliance" ) ? AllianceColor.RED : AllianceColor.BLUE;
        MatchRecord[] recs = matchRecords.values()
            .toArray( new MatchRecord[0] );

        return new Match( name,
                          allianceWhoWon,
                          coopertitionAchievedBox.isSelected(),
                          recs );
    }

    public MatchRecord getMatchRecord() {
        return emrPanel.getMatchRecord();
    }

    public void clearForm() {
        matchNameField.setText( "" );
        whoWonGroup.clearSelection();
        coopertitionAchievedBox.setSelected( false );
        emrPanel.clearForm();
        emrPanel.refresh();
        emrPanel.setEnabled( false );
        for( TeamPanel teamPanel : teamPanels ) {
            teamPanel.clear();
        }
        refresh();
    }

    private enum TeamEntryStatus { READY, ENTERING, ENTERED }

    private static final Map<AllianceColor,
        Map<TeamEntryStatus, Color>> PANEL_COLOR;

    static {
        PANEL_COLOR = new HashMap<AllianceColor,
            Map<TeamEntryStatus, Color>>();
        Map<TeamEntryStatus, Color> red =
            new HashMap<TeamEntryStatus, Color>();
        red.put( TeamEntryStatus.READY, Color.RED );
        red.put( TeamEntryStatus.ENTERING, new Color( 255, 75, 75 ) );
        red.put( TeamEntryStatus.ENTERED, new Color( 255, 180, 180 ) );
        PANEL_COLOR.put( AllianceColor.RED, red );
        Map<TeamEntryStatus, Color> blue =
            new HashMap<TeamEntryStatus, Color>();
        blue.put( TeamEntryStatus.READY, Color.BLUE );
        blue.put( TeamEntryStatus.ENTERING, new Color( 75, 75, 255 ) );
        blue.put( TeamEntryStatus.ENTERED, new Color( 180, 180, 255 ) );
        PANEL_COLOR.put( AllianceColor.BLUE, blue );
    }

    private class TeamPanel {
        private TeamPosition position;
        private AllianceColor color;
        private JPanel panel;
        private JLabel label;
        private JButton button;

        private TeamEntryStatus entryStatus = TeamEntryStatus.READY;

        public TeamPanel( TeamPosition position ) {
            this.position = position;
            boolean red = redPositions.contains( position );
            this.color = ( red ? AllianceColor.RED : AllianceColor.BLUE );

            panel = new JPanel();
            panel.setLayout( new BoxLayout( panel, BoxLayout.Y_AXIS ) );
            label = new JLabel( "Team ?????" );
            label.setAlignmentX( Component.CENTER_ALIGNMENT );
            Helper.setComponentFontSize( label, 22 );
            panel.add( label );
            button = new JButton( "Enter" );
            button.setAlignmentX( Component.CENTER_ALIGNMENT );
            Helper.setComponentFontSize( button, 16 );
            button.addActionListener( new EMRPanelLoadListener( this ) );
            panel.add( button );

            refresh();
        }

        public void clear() {
            setEntryStatus( TeamEntryStatus.READY );
            refresh();
        }

        public void refresh() {
            panel.setBackground( PANEL_COLOR.get( color ).get( entryStatus ) );

            switch( entryStatus ) {
            case READY:
                button.setText( "Enter" );
                button.setEnabled( selectedPosition == TeamPosition.NONE );
                label.setText( "Team ?????" );
                break;
            case ENTERING:
                button.setText( "Entering..." );
                button.setEnabled( false );
                Team team = emrPanel.getTeam();
                label.setText( "Team " +
                               ( ( team == null ) ?
                                 "?????" :
                                 "#" + team.getNumber() ) );
                break;
            case ENTERED:
                button.setText( "Entered" );
                button.setEnabled( false );
                break;
            }
        }

        public TeamEntryStatus getEntryStatus() {
            return entryStatus;
        }

        public void setEntryStatus( TeamEntryStatus status ) {
            entryStatus = status;
        }

        public boolean isEntered() {
            return getEntryStatus() == TeamEntryStatus.ENTERED;
        }

        public JPanel getPanel() {
            return panel;
        }

        public TeamPosition getPosition() {
            return position;
        }
    }

    /**
     * The listener when the "Enter" button is clicked on a TeamPanel.
     */
    private class EMRPanelLoadListener implements ActionListener {
        private TeamPanel teamPanel;

        public EMRPanelLoadListener( TeamPanel panel ) {
            teamPanel = panel;
        }

        public void actionPerformed( ActionEvent event ) {
            teamPanel.setEntryStatus( TeamEntryStatus.ENTERING );
            selectedPosition = teamPanel.getPosition();
            refresh();
            emrPanel.clearForm();
            emrPanel.setEnabled( true );
        }
    }

    private class EMRPanelSubmitListener implements ActionListener {
        public void actionPerformed( ActionEvent event ) {
            for( TeamPanel p : teamPanels ) {
                if( p.getPosition() == selectedPosition ) {
                    p.setEntryStatus( TeamEntryStatus.ENTERED );
                    MatchRecord record = emrPanel.getMatchRecord();
                    matchRecords.put( p.getPosition(), record );
                }
                p.refresh();
            }
            emrPanel.clearForm();
            emrPanel.setEnabled( false );
            selectedPosition = TeamPosition.NONE;
            refresh();
        }
    }
}


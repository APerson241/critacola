package org.rohawks.critacola.browser;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import org.rohawks.critacola.CritController;
import org.rohawks.critacola.beans.*;
import org.rohawks.critacola.ui.CritView;
import org.rohawks.critacola.util.*;

/**
 * Handles the behavior of the EntityBrowser, including the search functionality
 * and switching to the editing view.
 */
public class EntityBrowser extends CritView {
    private DefaultListModel matchRecordListModel;
    private JPanel detailsPanel;
    private List<MatchRecord> matchRecords;

    public EntityBrowser( CritController controller ) {
        super( controller );
        matchRecords = new ArrayList<MatchRecord>();
    }

    @Override
    public String getName() { return "Entity Browser"; }

    @Override
    public void refresh() {
        if( controller.getModel() == null ) return;
        matchRecordListModel.clear();
        matchRecords.clear();

        // Flatten match records into a uber-list
        CompetitionList compList = controller.getModel().getCompetitionList();
        for( Competition c : compList.getCompetitions() ) {
            for( Match m : c.getMatches() ) {
                for( MatchRecord mr : m.getMatchRecords() ) {
                    String s = String
                        .format( "Team %d, match %s, competition %s",
                                 mr.getTeam().getNumber(),
                                 m.getName(),
                                 c.getName() );
                    matchRecordListModel.addElement( s );
                    matchRecords.add( mr );
                }
            }
        }
    }

    @Override
    public boolean providesBackButton() {
        return true;
    }

    @Override
    protected void buildPanel() {
        panel = new JPanel( new GridLayout( 2, 1, 5, 5 ) );

        JPanel browsePanel = new JPanel( new BorderLayout() );
        browsePanel.setBorder( BorderFactory.createTitledBorder(
                "Browse Match Records" ) );
        matchRecordListModel = new DefaultListModel();
        JList matchRecordList = new JList( matchRecordListModel );
        matchRecordList.addMouseListener( new MatchRecordListListener() );
        browsePanel.add( new JScrollPane( matchRecordList ) );
        panel.add( browsePanel );

        detailsPanel = new JPanel( new GridLayout( 12, 2, 5, 5 ) );
        detailsPanel.setBorder( BorderFactory.createTitledBorder( "Details" ) );
        panel.add( detailsPanel );
    }

    private class MatchRecordListListener extends MouseAdapter {
        public void mouseClicked( MouseEvent evt ) {

            // Get the match record in question
            JList list = (JList)evt.getSource();
            int index = list.getSelectedIndex();
            MatchRecord matchRecord = matchRecords.get( index );

            // Empty the details pane
            detailsPanel.removeAll();
            detailsPanel.validate();
            detailsPanel.repaint();

            // Add all the details
            detailsPanel.add( new JLabel( "No-show?" ) );
            detailsPanel.add( new JLabel( matchRecord.isNoShow() ?
                                          "Yes" :
                                          "No" ) );
            detailsPanel.add( new JLabel( "Functional?" ) );
            detailsPanel.add( new JLabel( matchRecord.isNonFunctional() ?
                                          "Yes" :
                                          "No" ) );
            detailsPanel.add( new JLabel( "Auto" ) );
            detailsPanel.add( new JLabel( matchRecord.getAuto() ) );
            detailsPanel.add( new JLabel( "Stack" ) );
            detailsPanel.add( new JLabel( matchRecord.getStack() ) );
            detailsPanel.add( new JLabel( "Coop" ) );
            detailsPanel.add( new JLabel( matchRecord.getCoop() ) );
            detailsPanel.add( new JLabel( "Litter" ) );
            detailsPanel.add( new JLabel( matchRecord.getLitterAttempted() +
                                          " / " +
                                          matchRecord.getLitterMade() ) );
            detailsPanel.add( new JLabel( "Human player rating" ) );
            detailsPanel.add( new JLabel( matchRecord
                                          .getHumanPlayerRating() + "" ) );
            detailsPanel.add( new JLabel( "Drive train rating" ) );
            detailsPanel.add( new JLabel( matchRecord
                                          .getDriveTrainRating() + "" ) );
            detailsPanel.add( new JLabel( "Build quality rating" ) );
            detailsPanel.add( new JLabel( matchRecord
                                          .getBuildQualityRating() + "" ) );
            detailsPanel.add( new JLabel( "Restrictions" ) );
            detailsPanel.add( new JLabel( matchRecord.getRestrictions() ) );
            detailsPanel.add( new JLabel( "Comments" ) );
            detailsPanel.add( new JLabel( matchRecord.getComments() ) );

            // Repaint
            detailsPanel.validate();
            detailsPanel.repaint();
        }
    }
}

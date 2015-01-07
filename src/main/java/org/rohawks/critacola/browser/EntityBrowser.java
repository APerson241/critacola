package org.rohawks.critacola.browser;

import java.awt.*;

import javax.swing.*;

import org.rohawks.critacola.CritController;
import org.rohawks.critacola.beans.*;
import org.rohawks.critacola.ui.CritView;
import org.rohawks.critacola.util.Helper;

/**
 * Handles the behavior of the EntityBrowser, including the search functionality
 * and switching to the editing view.
 */
public class EntityBrowser extends CritView {
    private DefaultListModel competitionListModel;
    private JTextField searchTextField;

    public EntityBrowser( CritController controller ) {
        super( controller );
    }

    @Override
    public String getName() { return "Entity Browser"; }

    @Override
    public void refresh() {
        if( controller.getModel() == null ) return;
        CompetitionList compList = controller.getModel().getCompetitionList();
        competitionListModel.clear();
        for( Competition c : compList.getCompetitions() ) {
            competitionListModel.addElement( c.getName() );
        }
    }

    @Override
    public boolean providesBackButton() {
        return true;
    }

    @Override
    protected void buildPanel() {
        panel = new JPanel( new GridLayout( 2, 1, 5, 5 ) );
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder( BorderFactory.createTitledBorder( "Search" ) );
        searchPanel.setLayout( new BoxLayout( searchPanel, BoxLayout.Y_AXIS ) );
        JLabel searchLabel = new JLabel( "Search" );
        Helper.setComponentFontSize( searchLabel, 24 );
        searchPanel.add( searchLabel );
        searchPanel.add( Box.createHorizontalStrut( 5 ) );
        searchPanel.add( new JLabel( "(competitions, teams, matches, etc.)" ) );
        searchTextField = new JTextField( 20 );
        Helper.setComponentFontSize( searchTextField, 18 );
        searchPanel.add( searchTextField );
        JButton searchButton = new JButton( "Search" );
        searchButton.setEnabled( false );
        searchPanel.add( searchButton );
        panel.add( searchPanel );

        JPanel browsePanel = new JPanel( new BorderLayout() );
        browsePanel.setBorder( BorderFactory.createTitledBorder(
                "Browse Competitions" ) );
        competitionListModel = new DefaultListModel();
        browsePanel.add( new JScrollPane( new JList( competitionListModel ) ) );
        panel.add( browsePanel );
    }
}

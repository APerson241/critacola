package org.rohawks.critacola.ui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.rohawks.critacola.CritController;
import org.rohawks.critacola.beans.*;
import org.rohawks.critacola.db.*;

/**
 * A View that's the first thing the user sees. Offers options to switch to
 * other Views.
 */
public class HomeView extends CritView {
    private JButton enterMatchButton;
    private JButton viewStatsButton;
    private JButton viewDataButton;

    public HomeView( CritController controller ) {
        super( controller );
    }

    @Override
    public String getName() {
        return "Home";
    }

    @Override
    public void refresh() {
        boolean competitionsAvailable = false;
        try {
            CritModel model = controller.getModel();
            CompetitionList list = model.getCompetitionList();
            competitionsAvailable = !list.isEmpty();
        } catch( NullPointerException ex ) {
            /*
             * Since HomeView is the initial view, it's possible that when we
             * get here, something's null on the model side (because the
             * constructor for the model hasn't been called yet). Thus, we
             * just assume that everything's fine.
             */
        }
        enterMatchButton.setEnabled( competitionsAvailable );
        viewStatsButton.setEnabled( false );
        viewDataButton.setEnabled( competitionsAvailable );
    }

    @Override
    public boolean providesBackButton() {
        /*
         * Hard to imagine how one might go back from home.
         * At the moment, this is the only view that returns false.
         */
        return false;
    }

    @Override
    protected void buildPanel() {
        panel = new JPanel( new GridLayout( 4, 1, 10, 10 ) );
        panel.setBorder( BorderFactory.createEmptyBorder( 20, 20, 20, 20 ) );
        enterMatchButton = new JButton( "Enter Match" );
        enterMatchButton.addActionListener(
                controller.getSwitchCardListener(
                ViewType.ENTER_MATCH ) );
        panel.add( enterMatchButton );
        JButton addCompetitionButton = new JButton( "Add Competition" );
        addCompetitionButton.addActionListener(
                controller.getSwitchCardListener(
                ViewType.ADD_COMPETITION ) );
        panel.add( addCompetitionButton );
        viewStatsButton = new JButton( "View Statistics" );
        viewStatsButton.addActionListener(
                controller.getActionListener(
                viewStatsButton ) );
        panel.add( viewStatsButton );
        viewDataButton = new JButton( "View Data" );
        viewDataButton.addActionListener(
                controller.getSwitchCardListener(
                ViewType.ENTITY_BROWSER_SEARCH ) );
        panel.add( viewDataButton );
    }
}

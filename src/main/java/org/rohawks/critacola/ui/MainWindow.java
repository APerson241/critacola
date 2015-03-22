package org.rohawks.critacola.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.*;

import javax.swing.*;

import org.rohawks.critacola.*;
import org.rohawks.critacola.browser.*;
import org.rohawks.critacola.util.*;

/**
 * Controls the main window of the class and switching between cards.
 */
public class MainWindow {
    private JFrame frame;
    private JPanel cardPanel;
    private JButton backButton;
    private JLabel locationLabel;

    private CritController controller;
    private Map<ViewType, CritView> views;
    private CritView currentView;

    public MainWindow( CritController controller ) {
        this.controller = controller;
        constructViews();
        buildGUI();
    }

    /**
     * Construct all the views.
     */
    private void constructViews() {
        views = new HashMap<ViewType, CritView>();
        views.put( ViewType.SELECT_COMPETITION, new SelectCompetitionView(
                controller ) );
        views.put( ViewType.HOME, new HomeView( controller ) );
        views.put( ViewType.ENTER_MATCH, new EnterMatchView( controller ) );
        views.put( ViewType.ENTITY_BROWSER_SEARCH, new EntityBrowser(
                controller ) );
    }

    private void buildGUI() {
        frame = new JFrame( getWindowTitle() );

        JPanel topPanel = new JPanel( new BorderLayout() );
        topPanel.setBorder( BorderFactory.createEmptyBorder( 5, 5, 0, 5 ) );
        backButton = new JButton( "<< Back" );
        backButton.setEnabled( false );
        backButton.addActionListener(
                controller.getSwitchCardListener( ViewType.HOME ) );
        topPanel.add( backButton, BorderLayout.WEST );
        locationLabel = new JLabel( "?" );
        Helper.setComponentFontSize( locationLabel, 18 );

        // Add some glue on either side of the label by putting it in a JPanel
        JPanel topMiddlePanel = new JPanel();
        topMiddlePanel.add( Box.createHorizontalGlue() );
        topMiddlePanel.add( locationLabel );
        topMiddlePanel.add( Box.createHorizontalGlue() );
        topPanel.add( topMiddlePanel, BorderLayout.CENTER );

        // Create a strut with the same size as the button to center the label
        int strutWidth = (int)backButton.getPreferredSize().getWidth();
        topPanel.add(
                Box.createHorizontalStrut( strutWidth ),
                BorderLayout.EAST );
        frame.add( topPanel, BorderLayout.NORTH );

        cardPanel = new JPanel( new CardLayout() );
        cardPanel.setBorder( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ) );
        for( ViewType viewType : ViewType.values() ) {
            JPanel specificCardPanel = views.get( viewType ).getPanel();
            String cardName = viewType.toString();
            cardPanel.add( specificCardPanel, cardName );
        }
        frame.add( cardPanel, BorderLayout.CENTER );

        frame.setBounds( Helper
                         .getCenteredBounds( Configurables.WINDOW_SIZE ) );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    private String getWindowTitle() {
        PropertiesManager propertiesManager = PropertiesManager.getInstance();
        return propertiesManager.getProperty( "application.name" ) + " - " +
                propertiesManager.getProperty( "application.version" );
    }

    public void setVisible( boolean visible ) {
        frame.setVisible( visible );
    }

    public void switchCard( ViewType viewType ) {
        CardLayout cardLayout = ( CardLayout ) cardPanel.getLayout();
        currentView = views.get( viewType );
        refreshChrome();
        refreshCurrentView();
        cardLayout.show( cardPanel, viewType.toString() );
    }

    public void showDialog( String text, String title ) {
        JOptionPane.showConfirmDialog( frame, text, title,
                JOptionPane.OK_CANCEL_OPTION );
    }

    public void showDialog( String text ) {
        showDialog( text, "Message" );
    }

    public void refreshCurrentView() {
        if( currentView != null ) {
            currentView.refresh();
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public CritView getCurrentView() {
        return currentView;
    }

    /**
     * Refresh the "navigation bar".
     */
    public void refreshChrome() {
        locationLabel.setText( currentView.getName() );
        backButton.setEnabled( currentView.providesBackButton() );
    }
}

package org.rohawks.critacola;

import java.awt.event.*;

import javax.swing.*;

import org.rohawks.critacola.beans.*;
import org.rohawks.critacola.db.CritModel;
import org.rohawks.critacola.ui.*;

public class CritController {
    private MainWindow window;
    private CritModel model;

    public CritController() {
        window = new MainWindow( this );
        model = new CritModel();
        window.switchCard( ViewType.SELECT_COMPETITION );
    }

    public CritModel getModel() {
        return model;
    }

    public JFrame getViewFrame() {
        return window.getFrame();
    }

    public void setWindowVisible( boolean visible ) {
        window.setVisible( visible );
    }

    /**
     * Called when a competition is selected from the SelectCompetitionView.
     * Necessary because there are two different events that result in this.
     */
    private void onSelectCompetition( Competition selected ) {
        model.getCompetitionList().setSelectedCompetition( selected );
        window.refreshCurrentView();
        window.switchCard( ViewType.HOME );
    }

    public ActionListener getActionListener( JComponent component ) {
        if( component instanceof JButton ) {
            JButton button = (JButton) component;
            String text = button.getText();
            if( text.equals( "Save Match" ) ) {
                return new ActionListener() {
                    public void actionPerformed( ActionEvent event ) {
                        CritView view = window.getCurrentView();
                        if( view instanceof EnterMatchView ) {
                            EnterMatchView emv = (EnterMatchView)view;
                            Match m = emv.getMatch();
                            model
                                .getCompetitionList()
                                .getSelectedCompetition()
                                .addMatch( m );
                            window.switchCard( ViewType.HOME );
                            emv.clearForm();
                        }
                    }
                };
            } else if( text.equals( "Add Competition" ) ) {
                return new ActionListener() {
                    public void actionPerformed( ActionEvent event ) {
                        CritView view = window.getCurrentView();
                        if( view instanceof SelectCompetitionView ) {
                            Competition c = ( (SelectCompetitionView)view )
                                .getCompetitionFromUser();
                            model.getCompetitionList().addCompetition( c );
                            window.refreshCurrentView();
                        }
                    }
                };
            } else if( text.equals( "Select Competition" ) ) {
                return new ActionListener() {
                    public void actionPerformed( ActionEvent event ) {
                        CritView view = window.getCurrentView();
                        if( view instanceof SelectCompetitionView ) {
                            SelectCompetitionView scv =
                                ( SelectCompetitionView ) view;
                            Competition comp = scv.getSelectedCompetition();
                            onSelectCompetition( comp );
                        }
                    }
                };
            }
        }
        return new ActionListener() {
            public void actionPerformed( ActionEvent event ) {}
        };
    }

    public MouseAdapter getSelectCompetitionMouseAdapter() {
        return new MouseAdapter() {
            public void mouseClicked( MouseEvent event ) {
                JList list = (JList)event.getSource();
                if( event.getClickCount() == 2 ) {
                    CritView view = window.getCurrentView();
                    onSelectCompetition( ( (SelectCompetitionView)view )
                                         .getSelectedCompetition() );
                }
                window.refreshCurrentView();
            }
        };
    }

    /**
     * Return a new ActionListener that just switches to a card.
     */
    public ActionListener getSwitchCardListener( ViewType card ) {
        return new ActionListener() {
            public void actionPerformed( ActionEvent event ) {
                window.switchCard( card );
            }
        };
    }

    /**
     * Returns a new ActionListener that refreshes the current view.
     */
    public ActionListener getRefreshActionListener() {
        return new ActionListener() {
            public void actionPerformed( ActionEvent event ) {
                window.refreshCurrentView();
            }
        };
    }

    public KeyListener getRefreshOnKeystrokeListener() {
        return new KeyListener() {
            @Override
            public void keyTyped( KeyEvent event ) {
                window.refreshCurrentView();
            }

            @Override
            public void keyPressed( KeyEvent event ) {
                window.refreshCurrentView();
            }

            @Override
            public void keyReleased( KeyEvent event ) {
                window.refreshCurrentView();
            }
        };
    }
}

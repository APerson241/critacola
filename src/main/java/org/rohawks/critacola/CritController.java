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

    public ActionListener getActionListener( JComponent component ) {
        if( component instanceof JButton ) {
            JButton button = (JButton) component;
            String text = button.getText();
            if( text.equals( "Create Competition" ) ) {
                return new ActionListener() {
                    public void actionPerformed( ActionEvent event ) {
                        AddCompetitionView view = (AddCompetitionView)
                                window.getCurrentView();
                        Competition c = view.getCompetition();
                        model.getCompetitionList().addCompetition( c );
                        view.clearForm();
                        window.switchCard( ViewType.HOME );
                    }
                };
            } else if( text.equals( "Save Match" ) ) {
                return new ActionListener() {
                    public void actionPerformed( ActionEvent event ) {
                        window.switchCard( ViewType.HOME );
                    }
                };
            }
        }
        return new ActionListener() {
            public void actionPerformed( ActionEvent event ) {}
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

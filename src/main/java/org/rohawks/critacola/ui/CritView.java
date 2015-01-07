package org.rohawks.critacola.ui;

import javax.swing.JPanel;
import org.rohawks.critacola.CritController;

/**
 * An abstract View in the MVC Pattern.
 */
public abstract class CritView {
    protected CritController controller;
    protected JPanel panel;

    public CritView( CritController controller ) {
        this.controller = controller;
        buildPanel();
        refresh();
    }

    /**
     * Returns the JPanel representing this View.
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Returns the name of this View.
     */
    public abstract String getName();

    /**
     * Refreshes this View.
     */
    public abstract void refresh();

    /**
     * Returns whether this View provides a "back" button.
     */
    public abstract boolean providesBackButton();

    /**
     * Builds this view's panel. Hook method; called in constructor.
     */
    protected abstract void buildPanel();
}

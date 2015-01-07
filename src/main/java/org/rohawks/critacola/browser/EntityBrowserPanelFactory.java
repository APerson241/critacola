package org.rohawks.critacola.browser;

import java.awt.*;

import javax.swing.*;

import org.rohawks.critacola.beans.Competition;

/**
 * A factory for generating JPanels that provide a view of an entity. For
 * instance, calling <code>getCompetitionPanel(Competition)</code> will return
 * a JPanel that displays a summary of the Competition passed to
 * the method.
 *
 * Panels created by this factory don't actually include the name of the
 * entity (that's the EntityBrowser's job) or the ability to edit the entity's
 * properties.
 *
 * Used by the EntityBrowser.
 */
public abstract class EntityBrowserPanelFactory {
    /**
     * A private constructor, to prevent this utility class from being
     * instantiated.
     */
    private EntityBrowserPanelFactory() {}

     /**
      * Returns, if possible, a JPanel summarizing the provided object.
      *
      * @param object The object to summarize, if possible.
      * @return A summarizing JPanel or null if it couldn't be summarized.
      */
    public static JPanel getPanel( Object object ) {
        if( object instanceof Competition ) {
            return getCompetitionPanel( (Competition) object );
        }
        return null;
    }

    /**
     * Returns a JPanel that summarizes a Competition.
     *
     * @param competition The competition to summarize.
     * @return A JPanel summarizing the provided competition.
     */
    public static JPanel getCompetitionPanel( Competition competition ) {
        JPanel resultPanel = new JPanel( new GridLayout( 1, 2 ) );
        resultPanel.add( new JLabel( "Name" ) );
        resultPanel.add( new JLabel( competition.getName() ) );
        return resultPanel;
    }
}

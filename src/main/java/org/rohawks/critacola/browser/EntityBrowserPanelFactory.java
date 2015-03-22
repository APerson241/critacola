package org.rohawks.critacola.browser;

import java.awt.*;

import javax.swing.*;

import org.rohawks.critacola.beans.*;

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
        System.out.println( "[getPanel] ENTERED" );
        if( object instanceof Competition ) {
            return getCompetitionPanel( (Competition) object );
        } else if( object instanceof MatchRecord ) {
            System.out.println( "[getPanel] YO" );
            return getMatchRecordPanel( (MatchRecord) object );
        }
        return null;
    }

    /**
     * Returns a JPanel that summarizes a Competition.
     *
     * @param competition The competition to summarize.
     * @return A JPanel summarizing the provided competition.
     */
    private static JPanel getCompetitionPanel( Competition competition ) {
        JPanel resultPanel = new JPanel( new GridLayout( 1, 2 ) );
        resultPanel.add( new JLabel( "Name" ) );
        resultPanel.add( new JLabel( competition.getName() ) );
        return resultPanel;
    }

    private static JPanel getMatchRecordPanel( MatchRecord record ) {
        JPanel resultPanel = new JPanel();
        resultPanel.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
        System.out.println( "[getMatchRecordPanel] YO" +
                            record.getTeam().getNumber() );
        resultPanel.add( new JLabel( record.getTeam().getNumber() + "" ) );
        return resultPanel;
    }
}

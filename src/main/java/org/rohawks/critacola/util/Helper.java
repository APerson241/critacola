package org.rohawks.critacola.util;

import java.awt.*;

import javax.swing.JComponent;
import javax.swing.event.*;

/**
 * Quite a bit of helper functions.
 */
public abstract class Helper {
    public static void setComponentFontSize(
            JComponent component,
            int newSize ) {
        Font currentFont = component.getFont();
        Font newFont = new Font( currentFont.getFontName(),
                currentFont.getStyle(),
                newSize );
        component.setFont( newFont );
    }

    public static Rectangle getCenteredBounds( Dimension size ) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Point location = new Point( ( screen.width - size.width ) / 2,
                                    ( screen.height - size.height ) / 2 );
        return new Rectangle( location, size );
    }

    public static Rectangle getCenteredBounds( int width, int height ) {
        return getCenteredBounds( new Dimension( width, height ) );
    }

    /**
     * Checks if a string is an integer. Hey, it's faster than EAFP.
     */
    public static boolean isInteger( String str ) {
        if ( str == null ) {
            return false;
        }
        int length = str.length();
        if ( length == 0 ) {
            return false;
        }
        int i = 0;
        if ( str.charAt( 0 ) == '-' ) {
            if ( length == 1 ) {
                return false;
            }
            i = 1;
        }
        for ( ; i < length; i++ ) {
            char c = str.charAt( i );
            if ( c <= '/' || c >= ':' ) {
                return false;
            }
        }
        return true;
    }

    /**
     * From http://stackoverflow.com/a/10773412/1757964.
     */
    public static AncestorListener getRequestFocusListener() {
        return new AncestorListener() {

            @Override
            public void ancestorAdded( AncestorEvent e ) {
                JComponent component = e.getComponent();
                component.requestFocusInWindow();
                component.removeAncestorListener( this );
            }

            @Override
            public void ancestorMoved( AncestorEvent e ) {}

            @Override
            public void ancestorRemoved( AncestorEvent e ) {}
        };
    }
}

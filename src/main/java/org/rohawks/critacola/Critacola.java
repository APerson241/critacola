package org.rohawks.critacola;

/**
 * Here be the main method.
 */
public class Critacola {
    public static void main( String[] args ) {
        ( new Critacola() ).go();
    }

    public void go() {
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                CritController controller = new CritController();
                controller.setWindowVisible( true );
            }
        } );
    }
}


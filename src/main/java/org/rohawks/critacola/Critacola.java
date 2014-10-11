package org.rohawks.critacola;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Hello world!
 *
 */
public class Critacola {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        JFrame frame = new JFrame( "Hello World!" );
        frame.add( new JLabel( "Hello World!" ) );
        frame.setBounds( 50, 50, 500, 500 );
        frame.setVisible( true );
    }
    
    public int add( int arg0, int arg1 ) {
    	return arg0 + arg1;
    }
}

package org.rohawks.critacola.ui;

public enum ViewType {
    SELECT_COMPETITION( "Select Competition" ),
    HOME( "Home" ),
    ENTER_MATCH( "Enter Match" ),
    ENTITY_BROWSER_SEARCH( "Entity Browser Search" );

    private String name;

    private ViewType( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

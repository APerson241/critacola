package org.rohawks.critacola.ui;

public enum ViewType {
    HOME( "Home" ),
    ADD_COMPETITION( "Add Competition" ),
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

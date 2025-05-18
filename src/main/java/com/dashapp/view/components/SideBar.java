package com.dashapp.view.components;

public class SideBar {
    private boolean isAuthenticated;


    public SideBar(){
        this.isAuthenticated=false;
    }

    public SideBar(boolean isAuthenticated){
        this.isAuthenticated=isAuthenticated;
    }

    private void initialize(){
        if(isAuthenticated){
            creaSidebar();
        }
    }

    private void creaSidebar() {

    }
}

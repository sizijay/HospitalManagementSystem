/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author SiziJayawardena
 */

//implement the main Controller Class
public interface Controller {
    //declare the abstract methods
    void initializeView();

    public View getView();

    public void updateView();
    
    public void clearView();
    
}

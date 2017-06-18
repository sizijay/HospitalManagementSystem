/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

//import needed methods
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.Controller;
import util.UIFactory;
import util.View;

/**
 *
 * @author SiziJayawardena
 */

//simple UI to show successful message
//implement the Successful Controller method
public class SuccessfulController implements Controller{
    
    //create a new Sucessful object
    private final Successful view = new Successful();

    @Override
    public void initializeView() {
        
        //action for button OK
        view.getBtnOK().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.SUCCESSFUL);
            }
            
        });
    }

    //other abstract methods
    @Override
    public View getView() {
        return view;
    }

    @Override
    public void updateView() {
    }

    @Override
    public void clearView() {
    }
    
}

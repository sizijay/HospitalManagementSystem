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

//simple UI to show unsuccessful message
//implement the class UnsuccessfulController
public class UnsuccessfulController implements Controller{
    
    //create a new Unsuccessful object
    private final Unsuccessful view = new Unsuccessful();

    @Override
    public void initializeView() {
        
        //action for button OK
        view.getBtnOK().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.UNSUCCESSFUL);
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

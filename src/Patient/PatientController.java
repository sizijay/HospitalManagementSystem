/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patient;

//import all the needed methods
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.Controller;
import util.UIFactory;
import util.View;

/**
 *
 * @author SiziJayawardena
 */

//implement the class PatientController
public class PatientController implements Controller {
    
    
    //creat a new Patient object
    private final Patient view = new Patient();

    
    //abstract methods
    @Override
    public void initializeView() {
        
        //action for back button
        view.getBtnBack().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.PATIENT);
                UIFactory.showUI(UIFactory.UIName.LOGIN);
            }
            
        });
        
        //get into the interface for new Tests and Treatments
        view.getBtnNewTT().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.PATIENT);
                UIFactory.showUI(UIFactory.UIName.TESTTREAT);
            }
            
        });
        
        //get into the interface for Patient Details
        view.getBtnDetails().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.PATIENT);
                UIFactory.showUI(UIFactory.UIName.PATIENTDETAILS);
            }
            
        });
        
        //get into the interface for adding new Patient Admission
        view.getBtnNewAd().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.PATIENT);
                UIFactory.showUI(UIFactory.UIName.ADMISSION);
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

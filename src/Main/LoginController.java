/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
//import all methods
import EmployeeDetails.AddNewEmployee;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Controller;
import util.UIFactory;
import static util.UIFactory.UIName.ADDNEWEMPLOYEE;
import util.View;

/**
 *
 * @author SiziJayawardena
 */

//main interface for different interfaces

//implement login controller class
public class LoginController implements Controller{
    
    //create a new login object
    private final Login view = new Login();
    
    //abstract methods
    @Override
    public void initializeView() {
        
        //getting the staff details interface
        view.getBtnStaff().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.LOGIN);
                UIFactory.showUI(UIFactory.UIName.EMPLOYEEDETAILS);
            }
            
        });
        
        //get into the interface for adding new employee
        view.getBtnAdd().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.LOGIN);
                UIFactory.showUI(UIFactory.UIName.ADDNEWEMPLOYEE);
                
            }
            
        });
        
        //get into the interface for patient functions
        view.getBtnPatient().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.LOGIN);
                UIFactory.showUI(UIFactory.UIName.PATIENT);
            }
            
        });
        
        
        //get into the interface for making payment
        view.getBtnPayment().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.LOGIN);
                UIFactory.showUI(UIFactory.UIName.PAYMENT);
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

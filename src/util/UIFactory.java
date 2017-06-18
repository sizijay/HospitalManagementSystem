/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

//import all the needed methods and classes
import EmployeeDetails.AddNewEmployee;
import EmployeeDetails.AddNewEmployeeController;
import EmployeeDetails.EmployeeDetailsController;
import Main.LoginController;
import Main.MainController;
import Main.SuccessfulController;
import Main.UnsuccessfulController;
import Patient.AdmissionController;
import Patient.PatientController;
import Patient.PatientDetailsController;
import Patient.PaymentController;
import Patient.TestTreatController;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLayeredPane;


/**
 *
 * @author SiziJayawardena
 */

//implement the class UIFactory
public class UIFactory {
    
    //implement the eum UIName
    public enum UIName{
        
        //declare UINames
        MAIN,
        LOGIN,
        EMPLOYEEDETAILS, 
        ADDNEWEMPLOYEE, 
        PATIENT, 
        PATIENTDETAILS, 
        TESTTREAT, 
        ADMISSION, 
        PAYMENT, 
        SUCCESSFUL, 
        UNSUCCESSFUL,
        
    }
    
    //get a new hashMap for UIName and Controller
    private static final Map<UIName,Controller>factory = new HashMap<>();
    
    
    //hashing the relevant UINames for the relevant Controllers
    public static final Controller getUI(UIName name){
        Controller control = factory.get(name);
        
        if(control==null){
            switch(name){
                case LOGIN:
                    control = new LoginController();
                    break;
                
                case EMPLOYEEDETAILS:
                    control = new EmployeeDetailsController();
                    break;
                    
                case MAIN:
                    control = new MainController();
                    break;
                    
                case ADDNEWEMPLOYEE:
                    control = new AddNewEmployeeController();
                    break;
                    
                case PATIENT:
                    control = new PatientController();
                    break;
                    
                case PATIENTDETAILS:
                    control = new PatientDetailsController();
                    break;
                    
                case TESTTREAT:
                    control = new TestTreatController();
                    break;
                    
                case ADMISSION:
                    control = new AdmissionController();
                    break;
                    
                case PAYMENT:
                    control = new PaymentController();
                    break;
                    
                case SUCCESSFUL:
                    control = new SuccessfulController();
                    break;
                    
                case UNSUCCESSFUL:
                    control = new UnsuccessfulController();
                    break;
            }
            factory.put(name, control);
            control.initializeView();
        }
        return control;
    }
    
    //implement the showUI method
    public static final void showUI(UIName name){
        Controller control = getUI(name);
        View vi = control.getView();
        control.updateView();
        vi.setVisible(true);
    }
    
    //implement the hideUI method
    public static final void hideUI(UIName name){
        Controller control = getUI(name);
        View vi = control.getView();
        control.updateView();
        vi.dispose();
    }
    
    //implement the showUI method for showing JLayered Panes
    public static final void showUI(UIName name, JLayeredPane parent){
        Controller control = getUI(name);
        control.updateView();
        View vi = control.getView();
        parent.add((Component) vi);
        vi.setVisible(true);
    }
    
    //implement the hideUI method for hiding JLayered Panes
    public static final void hideUI(UIName name, JLayeredPane parent){
        Controller control = getUI(name);
        View vi = control.getView();
        parent.remove((Component) vi);
        vi.dispose();
    }
}


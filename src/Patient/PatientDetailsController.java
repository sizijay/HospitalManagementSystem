/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patient;


//import all the needed methods
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.Statement;
import util.Controller;
import util.UIFactory;
import util.View;

/**
 *
 * @author SiziJayawardena
 */


//implement the class PatientDetailsContoller
public class PatientDetailsController implements Controller{
    
    //create a new PatientDetails object
    private final PatientDetails view = new PatientDetails();

    @Override
    public void initializeView() {
        view.getBtnBack().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.PATIENTDETAILS);
                UIFactory.showUI(UIFactory.UIName.PATIENT);
            }
            
        });
        
        //disapearing the text included in Date text field when clicked TEST 
        view.txtDateTest.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                view.txtDateTest.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
            
        });
        

        //disapearing the text included in Date text field when clicked TEST 
        view.txtDateTreat.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                view.txtDateTreat.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
            
        });
        
        
        //clear all the fields when the Patient ID text field is selected
        view.txtPatientID.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                view.txtPatientID.setText(null);
                view.txtAddressG.setText(null);
                view.txtAddressP.setText(null);
                view.txtAgeP.setText(null);
                view.txtComTest.setText(null);
                view.txtComTreat.setText(null);
                view.txtConsID.setText(null);
                view.txtDateTest.setText(null);
                view.txtDesTest.setText(null);
                view.txtDateTreat.setText(null);
                view.txtDesTreat.setText(null);
                view.txtFNameG.setText(null);
                view.txtLNameP.setText(null);
                view.txtNICG.setText(null);
                view.txtFNameP.setText(null);
                view.txtLNameG.setText(null);
                view.txtGuardianID.setText(null);
                view.txtNICP.setText(null);
                view.txtTelG.setText(null);
                view.txtTelP.setText(null);
                view.txtWardNo.setText(null);
                view.buttonGroup1.clearSelection();
                view.buttonGroup2.clearSelection();
                view.comboTest.removeAllItems();
                view.comboTreat.removeAllItems();
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
            
        });
        
        
        //search for the patient from the PatientID
        view.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
                
                try{
                    Statement s = util.Database.getStatement();
                    
                    ResultSet p = s.executeQuery("SELECT * FROM Patient WHERE Patient.PatientID = '"+view.txtPatientID.getText()+"'");
                    
                    while(p.next()){
                        view.txtFNameP.setText(p.getString("FName"));
                        view.txtAddressP.setText(p.getString("Address"));
                        view.txtAgeP.setText(p.getString("Age"));
                        view.txtLNameP.setText(p.getString("LName"));
                        view.txtWardNo.setText(p.getString("Ward_WardNo"));
                        view.txtNICP.setText(p.getString("NIC"));
                        view.txtTelP.setText(p.getString("Tel_No"));
                        view.txtConsID.setText(p.getString("Consultantx_ConsID"));
                        
                    
                        
                        if (p.getString("Gender").equals("Male")){
                            view.radioMaleP.setSelected(true);
                        }
                        else if (p.getString("Gender").equals("Female")){
                            view.radioFemaleP.setSelected(true);
                        }
                    }
                    
                    ResultSet g = s.executeQuery("SELECT Guardian.*,Patient.PatientID FROM Patient INNER JOIN Guardian ON Patient.Guardian_GuardianID = Guardian.GuardianID WHERE Patient.PatientID = '"+view.txtPatientID.getText()+"'");
                    while(g.next()){
                        view.txtFNameG.setText(g.getString("FName"));
                        view.txtAddressG.setText(g.getString("Address"));
                        view.txtLNameG.setText(g.getString("LName"));
                        view.txtNICG.setText(g.getString("NIC"));
                        view.txtTelG.setText(g.getString("Tel_No"));
                        view.txtGuardianID.setText(g.getString("GuardianID"));
                        
                    
                        
                        if (g.getString("Gender").equals("Male")){
                            view.radioMaleG.setSelected(true);
                        }
                        else if (g.getString("Gender").equals("Female")){
                            view.radioFemaleG.setSelected(true);
                        }
                    }
                    
                    ResultSet rs = s.executeQuery("SELECT Test.TestID FROM Patient INNER JOIN Test ON Patient.PatientID = Test.Patient_PatientID WHERE Patient.PatientID = '"+view.txtPatientID.getText()+"'");
                    view.comboTest.removeAllItems();
                    view.comboTest.setSelectedItem("Select");
                    while(rs.next()){
                        view.comboTest.addItem(rs.getString("TestID"));
                    }
                    ResultSet rs1 = s.executeQuery("SELECT Treatment.TreatmentID FROM Patient INNER JOIN Treatment ON Patient.PatientID = Treatment.Patient_PatientID WHERE Patient.PatientID = '"+view.txtPatientID.getText()+"'");
                    view.comboTreat.removeAllItems();
                    view.comboTreat.setSelectedItem("Select");
                    while(rs1.next()){
                        view.comboTreat.addItem(rs1.getString("TreatmentID"));
                    }
                    
                     
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        
        //search for test details
        view.getBtnSearchTest().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Statement s = util.Database.getStatement();
                    
                    ResultSet rs = s.executeQuery("SELECT Test.*,Patient.PatientID FROM Patient INNER JOIN Test ON Patient.PatientId = Test.Patient_PatientID WHERE Patient.PatientID = '"+view.txtPatientID.getText()+"' AND Test.TestID = '"+view.comboTest.getSelectedItem().toString()+"'");
                    while(rs.next()){
                        view.txtDesTest.setText(rs.getString("Description"));
                        view.txtComTest.setText(rs.getString("Comments"));
                        view.txtDateTest.setText(rs.getString("Date"));
                        
                    }
                    
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        
        //search for the treatment details
        view.getBtnSearchTreat().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Statement s = util.Database.getStatement();
                    
                    ResultSet rs = s.executeQuery("SELECT Treatment.*,Patient.PatientID FROM Patient INNER JOIN Treatment ON Patient.PatientId = Treatment.Patient_PatientID WHERE Patient.PatientID = '"+view.txtPatientID.getText()+"' AND Treatment.TreatmentID = '"+view.comboTreat.getSelectedItem().toString()+"'");
                    while(rs.next()){
                        view.txtDesTreat.setText(rs.getString("Description"));
                        view.txtComTreat.setText(rs.getString("Comments"));
                        view.txtDateTreat.setText(rs.getString("Date"));
                        
                    }
                    
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        
        //update the current test details
        view.getBtnUpdateTest().addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Statement s = util.Database.getStatement();
                    
                    
                    
                    s.executeUpdate("UPDATE Test SET Description = '"+view.txtDesTest.getText()+"' ,Comments = '"+view.txtComTest.getText()+"' ,Date = DATE '"+view.txtDateTest.getText()+"' WHERE Test.TestID = '"+view.comboTest.getSelectedItem().toString()+"' AND Test.Patient_PatientID = '"+view.txtPatientID.getText()+"'");
                    
                    
                    
                    
                    UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        
        //update the current treatment details
        view.getBtnUpdateTreat().addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Statement s = util.Database.getStatement();
                    
                    
                    
                    s.executeUpdate("UPDATE Treat SET Description = '"+view.txtDesTreat.getText()+"' ,Comments = '"+view.txtComTreat.getText()+"' ,Date = DATE '"+view.txtDateTreat.getText()+"' WHERE Treatment.TreatmentID = '"+view.comboTreat.getSelectedItem().toString()+"' AND Treatment.Patient_PatientID = '"+view.txtPatientID.getText()+"'");
                    
                    
                    
                    
                    UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
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

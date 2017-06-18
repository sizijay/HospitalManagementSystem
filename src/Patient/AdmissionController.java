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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Controller;
import util.UIFactory;
import util.View;

/**
 *
 * @author SiziJayawardena
 */

//implement the class Admission Controller
public class AdmissionController implements Controller{
    
    //create a new Admission object
    private final Admission view = new Admission();
    
    //declare needed variables
    private int ad, pat, gua;
    private String genderP, genderG,PID, GID;

    @Override
    public void initializeView() {
        //action for back button
        view.getBtnBack().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.ADMISSION);
                UIFactory.showUI(UIFactory.UIName.PATIENT);
            }
            
        });
        
        //get the current consultant IDs into the combo box
        view.getComboConsID().addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                Statement s = util.Database.getStatement();
                try {
                    ResultSet rs = s.executeQuery("SELECT ConsID FROM Consultantx ORDER BY ConsID ASC");
                    view.comboConsID.removeAllItems();
                    view.comboConsID.setSelectedItem("Select");
                    while(rs.next()){
                        view.comboConsID.addItem(rs.getString("ConsID"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
            
        });
        
        //get the current doctor IDs nto the combo box
        view.getComboDocID().addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                Statement s = util.Database.getStatement();
                try {
                    ResultSet rs = s.executeQuery("SELECT DoctorID FROM Doctor");
                    view.comboDocID.removeAllItems();
                    while(rs.next()){
                        view.comboDocID.addItem(rs.getString("DoctorID"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
            
        });
        
        
        //get the current ward numbers into the combo box
        view.getComboWardNo().addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                Statement s = util.Database.getStatement();
                try {
                    ResultSet rs = s.executeQuery("SELECT WardNo,WardName FROM Ward ORDER BY WardNo ASC");
                    view.comboWardNo.removeAllItems();
                    while(rs.next()){
                        view.comboWardNo.addItem(rs.getString("WardNo")+"-"+rs.getString("WardName"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
            
        });
        //t the current guardian IDs into the combo box
        view.getComboOldIDG().addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                Statement s = util.Database.getStatement();
                try {
                    ResultSet rs = s.executeQuery("SELECT GuardianID FROM Guardian");
                    view.comboOldIDG.removeAllItems();
                    while(rs.next()){
                        view.comboOldIDG.addItem(rs.getString("GuardianID"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            
            //and set the correponding attribute values for the selected guardian ID into the textfields when Focusi is lost from that combo box
            @Override
            public void focusLost(FocusEvent e) {
                Statement s1 = util.Database.getStatement();
                try {
                    ResultSet rs1 = s1.executeQuery("SELECT * FROM Guardian WHERE GuardianID = '"+view.comboOldIDG.getSelectedItem().toString()+"'");
                    
                    while(rs1.next()){
                        view.txtAddressG.setText(rs1.getString("Address"));
                        view.txtFNameG.setText(rs1.getString("FName"));
                        view.txtLNameG.setText(rs1.getString("LName"));
                        view.txtTelG.setText(rs1.getString("Tel_No"));
                        view.txtNICG.setText(rs1.getString("NIC"));
                        view.txtGuardianID.setText(rs1.getString("GuardianID"));

                        if (rs1.getString("Gender").equals("Male")){
                            view.radioMaleG.setSelected(true);
                        }
                        else if (rs1.getString("Gender").equals("Female")){
                            view.radioFemaleG.setSelected(true);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        });
        
        
        //generate next patientID, GuardianID and AdmissionID for the next record
        view.getBtnGenerate().addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                ad=0;
                pat=0;
                gua=0;
                try{
                    Statement s = util.Database.getStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM Patient,Admission,Guardian");
                    while(rs.next()){
                        ad = Integer.parseInt(rs.getString("AdmissionID")) ;
                        pat = Integer.parseInt(rs.getString("PatientID"));
                        gua = Integer.parseInt(rs.getString("GuardianID"));
                    }
                    
                    
                    view.txtAdmissionID.setText(Integer.toString(ad+1));
                    view.txtPatientID.setText(Integer.toString(pat+1));
                    view.txtGuardianID.setText(Integer.toString(gua+1));
                    
                }catch(Exception ae){
                    ae.printStackTrace();
                }
                
            }
        });
        
        
        //action for the Submit button 
        //Insert the new records into the database
        view.getBtnSubmit().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Statement s = util.Database.getStatement();
                    if(view.radioMaleP.isSelected()){
                        genderP = "Male";
                    }
                    else if(view.radioFemaleP.isSelected()){
                        genderP = "Female";
                    }
                    
                    if(view.radioMaleG.isSelected()){
                        genderG = "Male";
                    }
                    else if(view.radioFemaleG.isSelected()){
                        genderG = "Female";
                    }
                    
                    if(! view.checkG.isSelected()){
                        
                        s.executeUpdate("INSERT INTO Guardian(GuardianID,FName,LName,Address,NIC,Tel_No,Gender) VALUES ('"+view.txtGuardianID.getText()+"','"+view.txtFNameG.getText()+"','"+view.txtLNameG.getText()+"','"+view.txtAddressG.getText()+"','"+view.txtNICG.getText()+"','"+view.txtTelG.getText()+"','"+genderG+"')");
                        GID = view.txtGuardianID.getText();
                    }
                    else{
                        GID = view.comboOldIDG.getSelectedItem().toString();
                    }        
                    
                    s.executeUpdate("INSERT INTO Patient (FName,LName,Address,NIC,Tel_No,Age,Gender,Guardian_GuardianID,Ward_WardNo,Consultantx_ConsID,Doctor_DoctorID) VALUES ('"+view.txtFNameP.getText()+"','"+view.txtLNameP.getText()+"','"+view.txtAddressP.getText()+"','"+view.txtNICP.getText()+"','"+view.txtTelP.getText()+"','"+view.txtAgeP.getText()+"','"+genderP+"','"+GID+"','"+view.comboWardNo.getSelectedItem().toString()+"','"+view.comboConsID.getSelectedItem().toString()+"','"+view.comboDocID.getSelectedItem().toString()+"')");
                    //PID = view.txtOldIDP.getText();
                    
                    s.executeUpdate("INSERT INTO Admission (InDate,Patient_PatientID) VALUES (DATE '"+view.txtDate.getText()+"' , '"+view.txtPatientID.getText()+"')");
                    
                    UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                    
                }catch(Exception ae){
                    ae.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
                
 
            }
            
        });
        
        //search for current PatientIDs and get the relevant details
        view.getBtnSearchOldID().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Statement s = util.Database.getStatement();
                    
                    ResultSet rs = s.executeQuery("SELECT * FROM Patient WHERE Patient.NIC = '"+view.txtNICP.getText()+"'");                    
                    while(rs.next()){
                        view.txtFNameP.setText(rs.getString("FName"));
                        view.txtAddressP.setText(rs.getString("Address"));
                        view.txtLNameP.setText(rs.getString("LName"));
                        view.txtNICP.setText(rs.getString("NIC"));
                        view.txtTelP.setText(rs.getString("Tel_No"));
                        view.txtAgeP.setText(rs.getString("Age"));
                        
                        
                    
                        
                        if (rs.getString("Gender").equals("Male")){
                            view.radioMaleP.setSelected(true);
                        }
                        else if (rs.getString("Gender").equals("Female")){
                            view.radioFemaleP.setSelected(true);
                        }
                    }
                    
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        
        //clear all the fields 
        view.getBtnClear().addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
             
                try{
                    view.txtPatientID.setText(null);
                    view.txtAdmissionID.setText(null);
                    view.txtGuardianID.setText(null);
                    view.txtAddressP.setText(null);
                    view.txtAddressG.setText(null);
                    view.comboOldIDG.removeAllItems();
                    view.comboOldIDG.addItem("Select");
                    view.comboOldIDG.setSelectedItem("Select");
                    view.comboConsID.removeAllItems();
                    view.comboConsID.addItem("Select");
                    view.comboConsID.setSelectedItem("Select");
                    view.txtFNameP.setText(null);
                    view.txtFNameG.setText(null);
                    view.txtLNameP.setText(null);
                    view.txtLNameG.setText(null);
                    view.comboWardNo.removeAllItems();
                    view.comboWardNo.addItem("Select");
                    view.comboWardNo.setSelectedItem("Select");
                    view.comboDocID.removeAllItems();
                    view.comboDocID.addItem("Select");
                    view.comboDocID.setSelectedItem("Select");
                    view.checkP.setSelected(false);
                    view.checkG.setSelected(false);
                    view.txtNICP.setText(null);
                    view.txtNICG.setText(null);
                    view.txtAgeP.setText(null);
                    view.txtTelP.setText(null);
                    view.txtTelG.setText(null);
                    view.txtDate.setText("YYYY-MM-DD");
                    view.buttonGroup1.clearSelection();
                    view.buttonGroup2.clearSelection();
                    
                   
                    
                }catch(Exception ae){
                    ae.printStackTrace();
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

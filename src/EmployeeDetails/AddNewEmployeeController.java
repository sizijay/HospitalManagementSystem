/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeDetails;

//import all methods
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import util.Controller;
import util.UIFactory;
import util.View;

/**
 *
 * @author SiziJayawardena
 */
//create the Employee Controller
public class AddNewEmployeeController implements Controller {
    
    //create a new AddNewEmployee object
    private final AddNewEmployee view = new AddNewEmployee();
    
    //declaration of needed variables for different methods
    private int emp=0;
    private int doc=0;
    private String gender;
    @Override
    public void initializeView() {
        
        //action for back button
        view.getBtnBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.ADDNEWEMPLOYEE);
                UIFactory.showUI(UIFactory.UIName.LOGIN);
            }
        });
        



        //**********************************************************
        //each buttons's actions have been implemented from here on
        //generate the next IDs for Doctor
        view.getBtnGenerate().addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                emp=0;
                doc=0;
                try{
                    Statement s = util.Database.getStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM Employee,Doctor");
                    while(rs.next()){
                        emp = Integer.parseInt(rs.getString("EmployeeID")) ;
                        doc = Integer.parseInt(rs.getString("DoctorID"));
                    }
                    
                    //while(rs.next()){
                        view.txtEmpID.setText(Integer.toString(emp+1));
                        view.txtDocID.setText(Integer.toString(doc+1));
                    //}
                }catch(Exception ae){
                    ae.printStackTrace();
                }
                
            }
        });
        
        //adding new doctor
        view.getBtnNewDoc().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Statement s = util.Database.getStatement();
                    if(view.radioMale.isSelected()){
                        gender = "Male";
                    }
                    else if(view.radioFemale.isSelected()){
                        gender = "Female";
                    }   
                    s.executeUpdate("INSERT INTO Employee(EmployeeID,FName,LName,Address,NIC,Tel_No,email,Gender) VALUES ('"+view.txtEmpID.getText()+"','"+view.txtFName.getText()+"','"+view.txtLName.getText()+"','"+view.txtAddress.getText()+"','"+view.txtNIC.getText()+"','"+view.txtTel.getText()+"','"+view.txtMail.getText()+"','"+gender+"')");
                    s.executeUpdate("INSERT INTO Doctor(SLMC_No,Employee_EmployeeID) VALUES ('"+view.txtSLMC.getText()+"','"+view.txtEmpID.getText()+"')");
                    UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //adding new nurse
        view.getBtnNewNurse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Statement s = util.Database.getStatement();
                    if(view.radioMaleN.isSelected()){
                        gender = "Male";
                    }
                    else if(view.radioFemaleN.isSelected()){
                        gender = "Female";
                    }   
                    s.executeUpdate("INSERT INTO Employee(EmployeeID,FName,LName,Address,NIC,Tel_No,email,Gender) VALUES ('"+view.txtEmpIDN.getText()+"','"+view.txtFNameN.getText()+"','"+view.txtLNameN.getText()+"','"+view.txtAddressN.getText()+"','"+view.txtNICN.getText()+"','"+view.txtTelN.getText()+"','"+view.txtMailN.getText()+"','"+gender+"')");
                    s.executeUpdate("INSERT INTO Nurse(Employee_EmployeeID,Ward_WardNo) VALUES ('"+view.txtEmpIDN.getText()+"','"+view.txtWardNo.getText()+"')");
                    UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        
        //generate new IDs for Nurse
        view.getBtnGenerateN().addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                emp=0;
                doc=0;
                try{
                    Statement s = util.Database.getStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM Employee,Nurse");
                    while(rs.next()){
                        emp = Integer.parseInt(rs.getString("EmployeeID")) ;
                        doc = Integer.parseInt(rs.getString("NurseID"));
                    }
                    
                    //while(rs.next()){
                        view.txtEmpIDN.setText(Integer.toString(emp+1));
                        view.txtNurseID.setText(Integer.toString(doc+1));
                    //}
                }catch(Exception ae){
                    ae.printStackTrace();
                }
                
            }
        });
        
        //add new staff member
        view.getBtnNewStaff().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Statement s = util.Database.getStatement();
                    if(view.radioMaleS.isSelected()){
                        gender = "Male";
                    }
                    else if(view.radioFemaleS.isSelected()){
                        gender = "Female";
                    }   
                    s.executeUpdate("INSERT INTO Employee(EmployeeID,FName,LName,Address,NIC,Tel_No,email,Gender) VALUES ('"+view.txtEmpIDS.getText()+"','"+view.txtFNameS.getText()+"','"+view.txtLNameS.getText()+"','"+view.txtAddressS.getText()+"','"+view.txtNICS.getText()+"','"+view.txtTelS.getText()+"','"+view.txtMailS.getText()+"','"+gender+"')");
                    s.executeUpdate("INSERT INTO MainStaff(Employee_EmployeeID,Profession) VALUES ('"+view.txtEmpIDS.getText()+"','"+view.txtProfession.getText()+"')");
                    UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //generate new IDs for staff member
        view.getBtnGenerateS().addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                emp=0;
                doc=0;
                try{
                    Statement s = util.Database.getStatement();
                    ResultSet rs = s.executeQuery("SELECT * FROM Employee,MainStaff");
                    while(rs.next()){
                        emp = Integer.parseInt(rs.getString("EmployeeID")) ;
                        doc = Integer.parseInt(rs.getString("StaffID"));
                    }
                    
                    //while(rs.next()){
                        view.txtEmpIDS.setText(Integer.toString(emp+1));
                        view.txtStaffID.setText(Integer.toString(doc+1));
                    //}
                    
                }catch(Exception ae){
                    ae.printStackTrace();
                }
                
            }
        });
        
        //this button clears all the text fields
        view.getBtnClear().addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
             
                try{
                    view.txtDocID.setText(null);
                    view.txtAddress.setText(null);
                    view.txtAddressN.setText(null);
                    view.txtAddressS.setText(null);
                    view.txtEmpID.setText(null);
                    view.txtEmpIDN.setText(null);
                    view.txtEmpIDS.setText(null);
                    view.txtFName.setText(null);
                    view.txtFNameN.setText(null);
                    view.txtFNameS.setText(null);
                    view.txtLName.setText(null);
                    view.txtLNameN.setText(null);
                    view.txtLNameS.setText(null);
                    view.txtMail.setText(null);
                    view.txtMailN.setText(null);
                    view.txtMailS.setText(null);
                    view.txtNIC.setText(null);
                    view.txtNICN.setText(null);
                    view.txtNICS.setText(null);
                    view.txtNurseID.setText(null);
                    view.txtProfession.setText(null);
                    view.txtSLMC.setText(null);
                    view.txtStaffID.setText(null);
                    view.txtTel.setText(null);
                    view.txtTelN.setText(null);
                    view.txtTelS.setText(null);
                    view.txtWardNo.setText(null);
                    view.buttonGroup1.clearSelection();
                    view.buttonGroup2.clearSelection();
                    view.buttonGroup3.clearSelection();
                    gender = null;
                   
                    
                }catch(Exception ae){
                    ae.printStackTrace();
                }
                
            }
        });
    }
    
    //other methods of the main controller
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

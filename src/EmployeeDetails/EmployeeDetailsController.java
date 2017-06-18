/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmployeeDetails;

//import all abstract methods
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
public class EmployeeDetailsController implements Controller {
    
    //create main object named view and other variables
    private final EmployeeDetails view = new EmployeeDetails();
    private String gender;
    private int AttenID;
    private int SalaryID;
    private String DNS;
    private int sal;
    
    //initializing method
    @Override
    public void initializeView() {
        
        //action for back button
        view.getBtnBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.EMPLOYEEDETAILS);
                UIFactory.showUI(UIFactory.UIName.LOGIN);
            }
        });
        
        
        //**********************************************************************
        //buttons' actions have implemented from here on
        //serch for doctors' details
        view.getBtnSearchD().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Statement s = util.Database.getStatement();
                    
                    ResultSet rs = s.executeQuery("SELECT * FROM Doctor INNER JOIN Employee ON Doctor.Employee_EmployeeID = Employee.EmployeeID WHERE Doctor.DoctorID = "+view.txtDocID.getText()+"");
                    
                    while(rs.next()){
                        view.txtFNameD.setText(rs.getString("FName"));
                        view.txtAddressD.setText(rs.getString("Address"));
                        view.txtEmpIDD.setText(rs.getString("EmployeeID"));
                        view.txtLNameD.setText(rs.getString("LName"));
                        view.txtMailD.setText(rs.getString("email"));
                        view.txtNICD.setText(rs.getString("NIC"));
                        view.txtSLMCD.setText(rs.getString("SLMC_No"));
                        view.txtTelD.setText(rs.getString("Tel_No"));
                        
                    
                        
                        if (rs.getString("Gender").equals("Male")){
                            view.radioMaleD.setSelected(true);
                        }
                        else if (rs.getString("Gender").equals("Female")){
                            view.radioFemaleD.setSelected(true);
                        }
                    }
                    
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //serach for nurse
        view.getBtnSearchN().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Statement s = util.Database.getStatement();
                    
                    ResultSet rs = s.executeQuery("SELECT * FROM Nurse INNER JOIN Employee ON Nurse.Employee_EmployeeID = Employee.EmployeeID WHERE Nurse.NurseID = "+view.txtNurseID.getText()+"");
                    
                    while(rs.next()){
                        view.txtFNameN.setText(rs.getString("FName"));
                        view.txtAddressN.setText(rs.getString("Address"));
                        view.txtEmpIDN.setText(rs.getString("EmployeeID"));
                        view.txtLNameN.setText(rs.getString("LName"));
                        view.txtWardNo.setText(rs.getString("Ward_WardNo"));
                        view.txtNICN.setText(rs.getString("NIC"));
                        view.txtTelN.setText(rs.getString("Tel_No"));
                        view.txtMailN.setText(rs.getString("email"));
                        
                    
                        
                        if (rs.getString("Gender").equals("Male")){
                            view.radioMaleN.setSelected(true);
                        }
                        else if (rs.getString("Gender").equals("Female")){
                            view.radioFemaleN.setSelected(true);
                        }
                    }
                    
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //search for staff member
        view.getBtnSearchS().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Statement s = util.Database.getStatement();
                    
                    ResultSet rs = s.executeQuery("SELECT * FROM MainStaff INNER JOIN Employee ON MainStaff.Employee_EmployeeID = Employee.EmployeeID WHERE MainStaff.StaffID = "+view.txtStaffID.getText()+"");
                    
                    while(rs.next()){
                        view.txtFNameS.setText(rs.getString("FName"));
                        view.txtAddressS.setText(rs.getString("Address"));
                        view.txtEmpIDS.setText(rs.getString("EmployeeID"));
                        view.txtLNameS.setText(rs.getString("LName"));
                        view.txtProfession.setText(rs.getString("Profession"));
                        view.txtNICS.setText(rs.getString("NIC"));
                        view.txtTelS.setText(rs.getString("Tel_No"));
                        view.txtMailS.setText(rs.getString("email"));
                        
                    
                        
                        if (rs.getString("Gender").equals("Male")){
                            view.radioMaleS.setSelected(true);
                        }
                        else if (rs.getString("Gender").equals("Female")){
                            view.radioFemaleS.setSelected(true);
                        }
                    }
                    
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //update the details of the doctor
        view.getBtnUpdateD().addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Statement s = util.Database.getStatement();
                    if(view.radioMaleD.isSelected()){
                        gender = "Male";
                    }
                    else if(view.radioFemaleD.isSelected()){
                        gender = "Female";
                    }   
                    s.executeUpdate("UPDATE Employee SET FName='"+view.txtFNameD.getText()+"',LName='"+view.txtLNameD.getText()+"',Address='"+view.txtAddressD.getText()+"',NIC='"+view.txtNICD.getText()+"',Tel_No='"+view.txtTelD.getText()+"',email='"+view.txtMailD.getText()+"',Gender='"+gender+"' WHERE Employee.EmployeeID='"+view.txtEmpIDD.getText()+"'");
                    s.executeUpdate("UPDATE Doctor SET SLMC_No='"+view.txtSLMCD.getText()+"',Employee_EmployeeID='"+view.txtEmpIDD.getText()+"' WHERE Doctor.DoctorID='"+view.txtDocID.getText()+"'");
                    //UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //update details of the nurse
        view.getBtnUpdateN().addActionListener(new ActionListener() {
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
                    s.executeUpdate("UPDATE Employee SET FName='"+view.txtFNameN.getText()+"',LName='"+view.txtLNameN.getText()+"',Address='"+view.txtAddressN.getText()+"',NIC='"+view.txtNICN.getText()+"',Tel_No='"+view.txtTelN.getText()+"',email='"+view.txtMailN.getText()+"',Gender='"+gender+"' WHERE Employee.EmployeeID='"+view.txtEmpIDN.getText()+"'");
                    s.executeUpdate("UPDATE Nurse SET Ward_WardNo='"+view.txtWardNo.getText()+"',Employee_EmployeeID='"+view.txtEmpIDN.getText()+"' WHERE Nurse.NurseID='"+view.txtNurseID.getText()+"'");
                    //UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //update details of the staff member
        view.getBtnUpdateS().addActionListener(new ActionListener() {
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
                    s.executeUpdate("UPDATE Employee SET FName='"+view.txtFNameS.getText()+"',LName='"+view.txtLNameS.getText()+"',Address='"+view.txtAddressS.getText()+"',NIC='"+view.txtNICS.getText()+"',Tel_No='"+view.txtTelS.getText()+"',email='"+view.txtMailS.getText()+"',Gender='"+gender+"' WHERE Employee.EmployeeID='"+view.txtEmpIDS.getText()+"'");
                    s.executeUpdate("UPDATE MainStaff SET Profession='"+view.txtProfession.getText()+"',Employee_EmployeeID='"+view.txtEmpIDS.getText()+"' WHERE MainStaff.StaffID='"+view.txtStaffID.getText()+"'");
                    //UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //search for the attendance details
        view.getBtnAttenSearch().addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Statement s = util.Database.getStatement();
                    
                    ResultSet rs = s.executeQuery("SELECT FName,LName FROM Employee WHERE Employee.EmployeeID = "+view.txtAttenEmpID.getText());
                    
                    while(rs.next()){
                        view.txtAttenFName.setText(rs.getString("FName"));
                        view.txtAttenLName.setText(rs.getString("LName"));
                    }
                    
                    ResultSet rn = s.executeQuery("SELECT COUNT(*) AS countN FROM Nurse WHERE Nurse.Employee_EmployeeID = '"+view.txtAttenEmpID.getText()+"'");
                    while(rn.next()){
                        if(rn.getInt("countN")==1){
                        DNS="Nurse";
                        }
                    }
                            
                    
                    ResultSet rst = s.executeQuery("SELECT COUNT(*) AS countS FROM MainStaff WHERE MainStaff.Employee_EmployeeID = '"+view.txtAttenEmpID.getText()+"'");
                    while(rst.next()){    
                        if(rst.getInt("countS")==1){
                            DNS = "Maintenance Staff";
                        }
                    }
                    
                    ResultSet rd = s.executeQuery("SELECT COUNT(*) AS countD FROM Doctor WHERE Doctor.Employee_EmployeeID = '"+view.txtAttenEmpID.getText()+"'");
                    while(rd.next()){    
                        if(rd.getInt("countD")==1){
                            DNS = "Doctor";
                        }
                    }
                    
                    
                    view.lblDNS.setText(DNS);
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //add new attendance details
        view.getBtnAttenAdd().addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Statement s = util.Database.getStatement();
                    
                    if(view.lblDNS.getText().equals("Doctor")){
                        sal = 4300;
                    }
                    else if(view.lblDNS.getText().equals("Nurse")){
                        sal = 2150;
                    }
                    else if(view.lblDNS.getText().equals("Maintenance Staff")){
                        sal = 1200;
                    }
                    
                    s.executeUpdate("INSERT INTO Attendance (Month,Days,Employee_EmployeeID) VALUES ('"+view.txtAttenMonth.getText()+"','"+view.txtAttenDays.getText()+"','"+view.txtAttenEmpID.getText()+"')");
                    s.executeUpdate("INSERT INTO Salary (Month,Attendance_Employee_EmployeeID,Amount) VALUES ('"+view.txtAttenMonth.getText()+"','"+view.txtAttenEmpID.getText()+"','"+sal * Integer.parseInt(view.txtAttenDays.getText())+"')");
                    
                    
                    
                    
                    UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //update the attendance details
        view.getBtnAttenUpdate().addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Statement s = util.Database.getStatement();
                    
                    if(view.lblDNS.getText().equals("Doctor")){
                        sal = 4300;
                    }
                    else if(view.lblDNS.getText().equals("Nurse")){
                        sal = 2150;
                    }
                    else if(view.lblDNS.getText().equals("Maintenance Staff")){
                        sal = 1200;
                    }
                    
                    s.executeUpdate("UPDATE Attendance SET Days = '"+view.txtAttenDays.getText()+"' WHERE Attendance.Month = '"+view.txtAttenMonth.getText()+"' AND Attendance.Employee_EmployeeID = '"+view.txtAttenEmpID.getText()+"'");
                    s.executeUpdate("UPDATE Salary SET Amount = '"+sal * Integer.parseInt(view.txtAttenDays.getText())+"' WHERE Salary.Month = '"+view.txtAttenMonth.getText()+"' AND Salary.Attendance_Employee_EmployeeID = '"+view.txtAttenEmpID.getText()+"'");
                    
                    
                    
                    
                    UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //generate new attendance ID for adding new attendance record
        view.getBtnAttenGenerate().addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
                AttenID=0;
                try{
                    Statement s = util.Database.getStatement();
                    ResultSet rs = s.executeQuery("SELECT AttenID FROM Attendance ORDER BY AttenID ASC");
                    while(rs.next()){
                        AttenID = Integer.parseInt(rs.getString("AttenID")) ;
                        
                    }
                    
                    
                    view.txtAttenID.setText(Integer.toString(AttenID+1));
                          
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //search for attendance records
        view.getBtnAttenSearchV().addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Statement s = util.Database.getStatement();
                    
                    ResultSet rs = s.executeQuery("SELECT * FROM Employee INNER JOIN Attendance ON Employee.EmployeeID = Attendance.Employee_EmployeeID WHERE Employee.EmployeeID = '"+view.txtAttenEmpIDV.getText()+"' AND Attendance.Month = '"+view.txtAttenMonthV.getText()+"'");
                    
                    while(rs.next()){
                        view.txtAttenFNameV.setText(rs.getString("FName"));
                        view.txtAttenLNameV.setText(rs.getString("LName"));
                        view.txtAttenDaysV.setText(rs.getString("Days"));
                        view.txtAttenIDV.setText(rs.getString("AttenID"));
                    }
                    
                    ResultSet rn = s.executeQuery("SELECT COUNT(*) AS countN FROM Nurse WHERE Nurse.Employee_EmployeeID = '"+view.txtAttenEmpIDV.getText()+"'");
                    while(rn.next()){
                        if(rn.getInt("countN")==1){
                        DNS="Nurse";
                        }
                    }
                            
                    
                    ResultSet rst = s.executeQuery("SELECT COUNT(*) AS countS FROM MainStaff WHERE MainStaff.Employee_EmployeeID = '"+view.txtAttenEmpIDV.getText()+"'");
                    while(rst.next()){    
                        if(rst.getInt("countS")==1){
                            DNS = "Maintenance Staff";
                        }
                    }
                    
                    ResultSet rd = s.executeQuery("SELECT COUNT(*) AS countD FROM Doctor WHERE Doctor.Employee_EmployeeID = '"+view.txtAttenEmpIDV.getText()+"'");
                    while(rd.next()){    
                        if(rd.getInt("countD")==1){
                            DNS = "Doctor";
                        }
                    }
                    
                    
                    view.lblDNSV.setText(DNS);
                    
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        //search for the salary records
        view.getBtnSalarySearch().addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
                try{
                    Statement s = util.Database.getStatement();
                    
                    ResultSet rs = s.executeQuery("SELECT * FROM Employee INNER JOIN Salary ON Employee.EmployeeID = Salary.Attendance_Employee_EmployeeID WHERE Employee.EmployeeID = '"+view.txtSalaryEmpID.getText()+"' AND Salary.Month = '"+view.txtSalaryMonth.getText()+"'");
                    
                    while(rs.next()){
                        view.txtSalaryFName.setText(rs.getString("FName"));
                        view.txtSalaryLName.setText(rs.getString("LName"));
                        view.txtSalaryID.setText(rs.getString("SalaryID"));
                        view.txtSalaryTotal.setText("LKR "+rs.getString("Amount")+" /=");
                    }
                    
                }catch(Exception ea){
                    ea.printStackTrace();
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
        });
        
        
        //clears all the text fields
        view.getBtnClear().addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
             
                try{
                    view.txtDocID.setText(null);
                    view.txtAddressD.setText(null);
                    view.txtAddressN.setText(null);
                    view.txtAddressS.setText(null);
                    view.txtEmpIDD.setText(null);
                    view.txtEmpIDN.setText(null);
                    view.txtEmpIDS.setText(null);
                    view.txtFNameD.setText(null);
                    view.txtFNameN.setText(null);
                    view.txtFNameS.setText(null);
                    view.txtLNameD.setText(null);
                    view.txtLNameN.setText(null);
                    view.txtLNameS.setText(null);
                    view.txtMailD.setText(null);
                    view.txtMailN.setText(null);
                    view.txtMailS.setText(null);
                    view.txtNICD.setText(null);
                    view.txtNICN.setText(null);
                    view.txtNICS.setText(null);
                    view.txtNurseID.setText(null);
                    view.txtProfession.setText(null);
                    view.txtSLMCD.setText(null);
                    view.txtStaffID.setText(null);
                    view.txtTelD.setText(null);
                    view.txtTelN.setText(null);
                    view.txtTelS.setText(null);
                    view.txtWardNo.setText(null);
                    view.buttonGroup1.clearSelection();
                    view.buttonGroup2.clearSelection();
                    view.buttonGroup3.clearSelection();
                    view.txtAttenDays.setText(null);
                    view.txtAttenDaysV.setText(null);
                    view.txtAttenEmpID.setText(null);
                    view.txtAttenEmpIDV.setText(null);
                    view.txtAttenFName.setText(null);
                    view.txtAttenFNameV.setText(null);
                    view.txtAttenID.setText(null);
                    view.txtAttenIDV.setText(null);
                    view.txtAttenLName.setText(null);
                    view.txtAttenLNameV.setText(null);
                    view.txtAttenMonth.setText(null);
                    view.txtAttenMonthV.setText(null);
                    view.txtSalaryEmpID.setText(null);
                    view.txtSalaryFName.setText(null);
                    view.txtSalaryID.setText(null);
                    view.txtSalaryLName.setText(null);
                    view.txtSalaryMonth.setText(null);
                    view.txtSalaryTotal.setText(null);
                    view.lblDNS.setText("******");
                    gender = null;
                   
                    
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

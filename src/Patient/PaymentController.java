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
import java.sql.SQLException;
import java.sql.Statement;
import util.Controller;
import util.UIFactory;
import util.View;

/**
 *
 * @author SiziJayawardena
 */

//implement the class PaymentController
public class PaymentController implements Controller{
    
    
    //create a new Payment object
    private final Payment view = new Payment();
    private int pay;

    @Override
    public void initializeView() {
        
        //action for the back button
        view.getBtnBack().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.PAYMENT);
                UIFactory.showUI(UIFactory.UIName.LOGIN);
            }
            
        });
        
        //clear the date text field when selected
        view.txtDate.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                view.txtDate.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
            
        });
        
        //clear the amount field when selected
        view.txtAmount.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                view.txtAmount.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
            
        });
        
        //clear all fields when AdmissionId field is selected
        view.txtAdmissionID.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                
                view.txtAdmissionID.setText(null);
                view.txtAmount.setText("LKR");
                view.txtDate.setText("YYYY-MM-DD");
                view.txtPaymentID.setText(null);
                view.lblName.setText(null);
                view.lblName1.setText(null);
                view.lblIDExist.setText(null);
                
            }

            
            //get the relevant records for the text fields for the Admission ID
            @Override
            public void focusLost(FocusEvent e) {
                Statement s = util.Database.getStatement();
                
                if(!view.txtAdmissionID.getText().equals("")){  
                try{
                    ResultSet rs2 = s.executeQuery("SELECT COUNT(*) AS count FROM Payment WHERE Payment.Admission_AdmissionID = '"+view.txtAdmissionID.getText()+"'");
                    while(rs2.next()){    
                        if(rs2.getInt("count")!=0){
                            view.lblIDExist.setText("Has already Paid for this ID");
                        }
                        else{
                            ResultSet rs = s.executeQuery("SELECT Patient.FName,Patient.LName,Patient.PatientID FROM Patient INNER JOIN Admission ON Patient.PatientID = Admission.Patient_PatientID WHERE Admission.AdmissionID = '"+view.txtAdmissionID.getText()+"'");

                            if(rs.next()){
                                view.lblName.setText(rs.getString("FName")+" "+rs.getString("LName"));
                                view.lblName1.setText("Patient ID = "+rs.getString("PatientID"));

                                ResultSet rs1 = s.executeQuery("SELECT Payment.PaymentID FROM Payment ORDER BY PaymentID ASC");
                                
                                while(rs1.next()){

                                pay = Integer.parseInt(rs1.getString("PaymentID")) ;
                                
                                }
                                    

                                view.txtPaymentID.setText(Integer.toString(pay+1));
                                break;
                            }
                            else{
                                view.lblName.setText(null);
                                view.lblName1.setText(null);
                            }
                        }
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                
            }}
            
        });
        
        
        //action for the pay button
        //update the payment details into the database
        view.getBtnPay().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement s = util.Database.getStatement();
                try {
                    s.executeUpdate("INSERT INTO Payment (Date,Amount,Admission_AdmissionID) VALUES (DATE '"+view.txtDate.getText()+"','"+view.txtAmount.getText()+"','"+view.txtAdmissionID.getText()+"')");
                    s.executeUpdate("UPDATE Admission SET OutDate = DATE'"+view.txtDate.getText()+"' WHERE AdmissionID = '"+view.txtAdmissionID.getText()+"'");
                    
                    
                    view.txtAdmissionID.setText(null);
                    view.txtAmount.setText("LKR");
                    view.txtDate.setText("YYYY-MM-DD");
                    view.txtPaymentID.setText(null);
                    view.lblName.setText(null);
                    view.lblName1.setText(null);
                    
                    
                    UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    
                    
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

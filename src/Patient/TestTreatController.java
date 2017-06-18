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
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Controller;
import util.Database;
import util.UIFactory;
import util.View;

/**
 *
 * @author SiziJayawardena
 */

//implement the class TestTreatController
public class TestTreatController implements Controller {
    
    //create a new TestTreat object
    private final TestTreat view = new TestTreat();
    
    private int ID;
    
    @Override
    public void initializeView() {
        //action for the back button
        view.getBtnBack().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.TESTTREAT);
                UIFactory.showUI(UIFactory.UIName.PATIENT);
            }
            
        });
        
        
        //set the relevant Patient details for the given PatientID
        view.txtPatientID.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                view.lblName.setText(null);
                view.txtPatientID.setText(null);
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                Statement s = util.Database.getStatement();
                try {
                    ResultSet rs = s.executeQuery("SELECT FName,LName FROM Patient WHERE Patient.PatientID = '"+view.txtPatientID.getText()+"'");
                    
                    if(rs.next()){
                        
                        view.lblName.setText(rs.getString("FName")+" "+rs.getString("LName"));
                    }
                    else{
                        view.lblName.setText(null);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        });
        
        //generating the next Test ID when selected
        view.radioTest.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                Statement s = util.Database.getStatement();
                try {
                    ResultSet rs = s.executeQuery("SELECT TestID FROM Test");
                    
                    while(rs.next()){
                        
                        ID = Integer.parseInt(rs.getString("TestID")) ;
                    }
                    
                    view.lblID.setText("Test ID = "+Integer.toString(ID+1));
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
            
        });
        
        //generating the next Treatment ID when Selected
        view.radioTreat.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                Statement s = util.Database.getStatement();
                try {
                    ResultSet rs = s.executeQuery("SELECT TreatmentID FROM Treatment");
                    
                    while(rs.next()){
                        
                        ID = Integer.parseInt(rs.getString("TreatmentID")) ;
                    }
                    
                    view.lblID.setText("Treat ID = "+Integer.toString(ID+1));
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
            
        });
        
        //clear the Date text field when selected
        view.txtDate.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                view.txtDate.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
            
        });
        
        //submit button action
        //update the test data into the database
        view.getBtnSubmit().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Statement s = util.Database.getStatement();
                if(view.radioTest.isSelected()){
                    try {
                        s.executeUpdate("INSERT INTO Test (Description,Comments,Date,Patient_PatientID) VALUES ('"+view.txtDescription.getText()+"','"+view.txtComment.getText()+"',DATE '"+view.txtDate.getText()+"','"+view.txtPatientID.getText()+"')");
                        
                        view.txtComment.setText(null);
                        view.txtDescription.setText(null);
                        view.txtDate.setText("YYYY-MM-DD");
                        view.txtPatientID.setText(null);
                        view.buttonGroup1.clearSelection();
                        view.lblID.setText(null);
                        view.lblName.setText(null);
                        
                        UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                        
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                    }
                }
                else if(view.radioTreat.isSelected()){
                    try {
                        s.executeUpdate("INSERT INTO Treatment (Description,Comments,Date,Patient_PatientID) VALUES ('"+view.txtDescription.getText()+"','"+view.txtComment.getText()+"',DATE '"+view.txtDate.getText()+"','"+view.txtPatientID.getText()+"')");
                        
                        view.txtComment.setText(null);
                        view.txtDescription.setText(null);
                        view.txtDate.setText("YYYY-MM-DD");
                        view.txtPatientID.setText(null);
                        view.buttonGroup1.clearSelection();
                        view.lblID.setText(null);
                        view.lblName.setText(null);
                        
                        UIFactory.showUI(UIFactory.UIName.SUCCESSFUL);
                        
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                    }
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

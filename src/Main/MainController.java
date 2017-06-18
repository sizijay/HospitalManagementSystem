/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

//import methods
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import util.Controller;
import util.UIFactory;
import util.View;

/**
 *
 * @author SiziJayawardena
 */

//implement the class MainController
public class MainController implements Controller {
    
    //create a new Main object
    private final Main view = new Main();
    private static boolean flag = false;
    
    //abstract methods
    @Override
    public void initializeView() {
        //action for exit button
        view.getBtnExit().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                UIFactory.hideUI(UIFactory.UIName.MAIN);
            }
            
        });
        
        //print the copyright details on the interface when going to insert the username
        view.txtUsername.addFocusListener(new FocusListener(){
            @Override
            
            public void focusGained(FocusEvent e) {
                if(!flag){
                    new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String x="";   
                        String txt="*****Copyright© Sisira Jayawardena® 2017*****";
                        flag=true;
                        for(int i=0;i<txt.length();i++){
                            try {

                                    view.sizi.setText(x=x+txt.charAt(i));

                                    Thread.sleep(40);
                                } catch(InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                        }
                    
                    }).start();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
            
        });
        //handling the login
        view.getBtnEnter().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(view.txtPassword.getPassword());
                if(view.txtUsername.getText().toLowerCase().equals("admin") && password.equals("admin")){
                    UIFactory.hideUI(UIFactory.UIName.MAIN);
                    UIFactory.showUI(UIFactory.UIName.LOGIN);
                }
                else{
                    view.txtPassword.setText("");
                    view.txtUsername.setText("");
                    UIFactory.showUI(UIFactory.UIName.UNSUCCESSFUL);
                }
            }
            
        });
    }
    
    
    //other abstract methods
    @Override
    public View getView() {
        return  view;
        
    }

    @Override
    public void updateView() {
        
    }

    @Override
    public void clearView() {
    }
    
}
    


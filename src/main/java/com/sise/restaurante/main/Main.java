/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sise.restaurante.main;

import com.sise.restaurante.ui.LoginUI;

/**
 *
 * @author User02
 */
public class Main {

    public static void main(String[] args) {
        
        try{
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        new LoginUI().setVisible(true);
         }catch (Exception e){
             System.out.println(e);
         }
    }
}

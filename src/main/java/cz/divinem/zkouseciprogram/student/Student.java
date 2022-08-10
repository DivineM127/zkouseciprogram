/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.divinem.zkouseciprogram.student;

/**
 *
 * @author novos
 */
public class Student {
    private String name = "";
    private double procenta;
            
    public void setProcenta(double procenta) {
        this.procenta = procenta;
    }
    public double getProcenta() {
        return procenta;
    }  
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    

}

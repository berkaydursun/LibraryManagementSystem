/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author BERKAY
 */
public class Captcha {
    private String captcha="";
    private char characters[]={'A','B','C','D','E','F','G','H','I','J','K','L'
            ,'M','N','O','P','R','S','T','U','V','Y','Z','X','0','1','2','3','4'
            ,'5','6','7','8','9'};
   
    public Captcha(){
    Random rand=new Random();
    for(int i=0;i<4;i++){
    captcha+=characters[rand.nextInt(33)];
    }
    
    }
    
    public String getCaptcha(){
    return captcha;
    }
}

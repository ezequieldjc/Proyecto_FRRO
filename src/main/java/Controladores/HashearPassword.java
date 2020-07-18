package Controladores;

import java.util.Random;

public class HashearPassword {

    private String hide = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@-_";
    private String unhide = "abCDENOPQR48cdefijklmFGnoVWZ1pLMUXghYS390!@-Tqrstu567vwxyzABHIJK2_";
    private int a=5;
    private int b=3;
    private int c=2;
    private int d=5;

    public String hidePwd (String pass){
        String hidePass = "";
        for (int x=0 ;x<a ; x++){
            hidePass += hide.charAt(new Random().nextInt(hide.length()));
        }
        for (int x = 0 ; x < 3;x++){
            hidePass += getHideChar(pass.charAt(x));
        }
        for (int x=0 ;x<b ; x++){
            hidePass += hide.charAt(new Random().nextInt(hide.length()));
        }
        for (int x = 3 ; x < 6;x++){
            hidePass += getHideChar(pass.charAt(x));
        }
        for (int x=0 ;x<c ; x++){
            hidePass += hide.charAt(new Random().nextInt(hide.length()));
        }
        for (int x = 6 ; x < pass.length();x++){
            hidePass += getHideChar(pass.charAt(x));
        }
        for (int x=0 ;x<d ; x++){
            hidePass += hide.charAt(new Random().nextInt(hide.length()));
        }
        return hidePass;
    }

    public String unHidePwd (String pass){
        String unhidePass = "";
        pass = pass.substring(a,a+3) + pass.substring(a+3+b, a+3+b+3)+pass.substring(a+b+3+3+2,pass.length()-5);
        for (int x = 0 ; x < pass.length();x++){
            unhidePass += getUnhideChar(pass.charAt(x));
        }
        return unhidePass;
    }

    private char getHideChar(char k){
        if (unhide.contains(""+k)){
            return unhide.charAt(hide.indexOf(k));
        } else {
            return k;
        }
    }

    private char getUnhideChar(char k){
       if (hide.contains(""+k))
           return hide.charAt(unhide.indexOf(k));
       else
           return k;
    }

}

package dev.obniavka;
import javax.swing.*;

public class InfoBox{
    public void incorrectCityMessage(){ JOptionPane.showMessageDialog(null,"Неправильно введене місто"); }

    public void incorrectAdmin(){
        JOptionPane.showMessageDialog(null,"Неправильно введений логін або пароль");
    }
}
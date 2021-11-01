package dev.obniavka;
import javax.swing.*;

public class InfoBox{
    public void incorrectCityMessage(){ JOptionPane.showMessageDialog(null,"Неправильно введене місто"); }

    public void incorrectAdmin(){
        JOptionPane.showMessageDialog(null,"Неправильно введений логін або пароль");
    }

    public void weatherAdd(){ JOptionPane.showMessageDialog(null,"Не вся інформація про погоду була додана"); }

    public void succsesfulAdding(){ JOptionPane.showMessageDialog(null,"Інформація про погоду була успішно додана"); }

}
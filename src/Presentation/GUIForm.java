package Presentation;

import Recursos.Cube;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIForm extends JFrame{
    private JPanel panel1;
    private MyCanvas canvas=new MyCanvas();

    public GUIForm(){
        super("Rubik");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1030,850);
        getContentPane().add(panel1);

    }


}

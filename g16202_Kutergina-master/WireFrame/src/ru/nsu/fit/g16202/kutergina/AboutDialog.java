package ru.nsu.fit.g16202.kutergina;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {

    public AboutDialog() {
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(800, 600));
        setTitle("About FIT_16202_Kutergina_Init");
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        Label what = new Label("    Init Version 1.0");
        what.setFont(new Font("Serif", Font.PLAIN, 14));
        Label who = new Label("FIT Kutergina 16202\n\n");
        Label who1 = new Label("New - создать новую фигуру\n");
        Label who2 = new Label("Delete - удалить выделенную фигуру(Зелёный цвет)\n");
        Label who3 = new Label("Open - открыть файл\n");
        Label who4 = new Label("Init - сбросить углы\n");
        Label who5 = new Label("Color - выбрать цвет фигуры\n");
        Label who6 = new Label("Перемещение тела по сцене осуществляется стрелками на клавиатуре\n");
        Label who7 = new Label("Переключение активной фигуры клавиша ENTER\n");
        Label who8 = new Label("Вращение фигуры осуществляется перемещением курсора с зажатой левой кнопкой мыши\n");
        Label who9 = new Label("(вращение сцены +CTRL)\n");
        Label who10 = new Label("создать/переместить/удалить точку сплайна левая/левая зажатая/правая кнопки мыши соответственно");
        who.setFont(new Font("Serif", Font.PLAIN, 14));
        JButton ok = new JButton("Ok");
        ok.addActionListener(e -> dispose());
        listPane.add(what);
        listPane.add(who);
        listPane.add(who1);
        listPane.add(who2);
        listPane.add(who3);
        listPane.add(who4);
        listPane.add(who5);
        listPane.add(who6);
        listPane.add(who7);
        listPane.add(who8);
        listPane.add(who9);
        listPane.add(who10);
        listPane.add(ok);
        add(listPane);
        pack();
        setVisible(true);
    }

}
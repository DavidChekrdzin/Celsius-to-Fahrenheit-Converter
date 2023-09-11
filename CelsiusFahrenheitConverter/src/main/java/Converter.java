import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

public class Converter extends JFrame{
    private JComboBox select1ComboBox;
    private JButton convertButton;
    private JComboBox select2ComboBox;
    private JTextField inputField;
    private JTextField outputField;
    private JPanel mainPanel;
    private JButton clearButton;
    private JButton exitButton;
    private String measurementUnits[] = {"Celsius","Fahrenheit"};

    public Converter()
    {
        //adding all the measurement units to the combo boxes
        Arrays.stream(measurementUnits).forEach(s-> select1ComboBox.addItem(s));
        Arrays.stream(measurementUnits).forEach(s-> select2ComboBox.addItem(s));

        //setting default values to combo boxes
        select1ComboBox.setSelectedItem(measurementUnits[0]);
        select2ComboBox.setSelectedItem(measurementUnits[1]);

        //setting JFrame specifications and parameters
        setContentPane(mainPanel);
        setTitle("Temperature Converter");
        setSize(600,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //if item 1 from combo box 1 is selected then set item 2 to combo box 2 and vice versa
        select1ComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(select1ComboBox.getSelectedItem()== measurementUnits[0])
                {
                    select2ComboBox.setSelectedItem(measurementUnits[1]);
                }
                if(select1ComboBox.getSelectedItem()== measurementUnits[1])
                {
                    select2ComboBox.setSelectedItem(measurementUnits[0]);
                }
            }
        });

        //if convert button is clicked then convert the input value to the designated measurement unit
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (select1ComboBox.getSelectedItem() == measurementUnits[0]) {
                        double celsius = Double.parseDouble(inputField.getText());
                        double fahrenheit = celsius * 1.8 + 32;

                        outputField.setText(Double.toString(fahrenheit));
                    }
                    if (select1ComboBox.getSelectedItem() == measurementUnits[1]) {
                        double fahrenheit = Double.parseDouble(inputField.getText());
                        double celsius = (fahrenheit - 32) * 0.5556;

                        outputField.setText(Double.toString(celsius));
                    }
                    //exception if user types in things other than numbers or numbers with , instead of .
                }catch(NumberFormatException a)
                {
                    outputField.setText("Numbers only please (or use . instead of ,)");
                }
            }
        });

        //if clear button is clicked clear both the text boxes
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText(null);
                outputField.setText(null);
            }
        });

        //if exit button is clicked close the program
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    public static void main(String[]args)
    {
        new Converter();
    }

}

package core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    // helper class


    // function to set messages
    public static void showMsg(String str) {

        String msg;
        String title = switch (str) {
            case "fill" -> {
                msg = "Please fill in all fields.";
                yield "Error!";
            }
            case "done" -> {
                msg = "Successful";
                yield "Done";
            }
            case "notFound" -> {
                msg = str + " Not found!";
                yield "Not found.";
            }
            case "error" -> {
                msg = "You Made a Wrong Transaction!";
                yield "Error!";
            }
            default -> {
                msg = str;
                yield "Message";
            }
        };

        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);


    }


    // function to create a confirmation window to check if user is sure or not
    public static boolean confirm(String str){

        String msg;
        if(str.equals("Sure")){
            msg = "Are you sure?";
        }else{
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Are you sure ?",JOptionPane.YES_NO_OPTION) == 0 ;
    }


    // with trim method, all empty strings are being excluded and is fieldempty function checks if jtextfield is empty.
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    // checks if jtextfield array is empty
    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        // with for loop, array objects are being controlled
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }

    // arrange the location of window
    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

}
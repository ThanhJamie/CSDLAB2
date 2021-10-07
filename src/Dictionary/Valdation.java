/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author GMT
 */
public class Valdation extends Vector<String>{
    private final static Scanner in = new Scanner(System.in);
    int maxChoices, minChoices;
    public Valdation(int min, int max){
        super();
        maxChoices = max;
        minChoices = min;
    }

    public static String getString(String input, String error, boolean nothing) {
        System.out.print(input);
        String output = new Scanner(System.in).nextLine().trim();
        if (!output.isEmpty()) {
            return output;
        }
        if (nothing) {
            return null;
        }
        System.err.println(error);
        return getString(input, error, nothing);
    }

    public static int getInteger(String input, String error) {
        try {
            return Integer.parseInt(getString(input, error, false));
        } catch (Exception e) {
            System.err.println(error);
            return getInteger(input, error);
        }
    }
    
    public static int getInterger(String input, String error, int min){
        int output  = getInteger(input, error);
        if(output>=min){
            return output;
        }System.err.println("Just input a number from" + min);
        return getInterger(input, error, min);
    }
    
    public static int getInterger(String input, String error, int min,int max){
        int output  = getInteger(input, error);
        if(output>=min  && output<=max){
            return output;
        }System.err.println("Just input a number from " + min + "to " +max);
        return getInterger(input, error, min,max);
    }
    public static double getDouble(String input, String error) {
        try {
            return Double.parseDouble(getString(input, error, false));
        } catch (Exception e) {
            System.err.println(error);
            return getDouble(input, error);
        }
    }

    public static double getDouble(String input, String error, double min) {
        double output = getDouble(input, error);
        if (output >= min) {
            return output;
        }
        System.out.println("Just input a number from " + min);
        return getDouble(input, error, min);
    }

    public static double getDouble(String input, String error, double min, double max) {
        double output = getDouble(input, error, min);
        if (output <= max) {
            return output;
        }
        System.out.println("Just input a number from " + min + " to " + max);
        return getDouble(input, error, min, max);
    }
    
    public static String checkInputString() {

        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static String getInputYN(String input, String error, boolean nothing){
        boolean t = true;
        String result = null;
        do {            
            String a = getString(input, error, false);
            if(a.equalsIgnoreCase("Y")||a.equalsIgnoreCase("N")){
                result = a;
                t = false;
            }
        } while (t);
        return result;
    }
    public static boolean checkValidDate(String input) {
        String formatString = "dd/MM/yyyy";

        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            format.setLenient(true);
            format.parse(input);
        } catch(Exception e){
            return false;
        }

        return true;
    }
    
    public static Date getDate(String msg) {
            String str;
            Scanner sc = new Scanner(System.in);
            Date date = null;
            boolean isValid;
		do {
                    try {
                        isValid = true;
			str = Valdation.getString(msg, "Invalid", false);
                        if (str.isEmpty()) {
                            throw new Error();
                        }
                        date = Date.valueOf(str);
                    } catch (Exception e) {
                        System.out.println("Invalid value");
                        isValid = false;
                    }
		} while (date == null); 
		
            return date;
        }
    
     public int inputInteger(String message, int min, int max){
        while(true){
            try{
                int value;
                Scanner scanner = new Scanner(System.in);
                System.out.print(message);
                value = Integer.parseInt(scanner.nextLine());
                if(value >= min && value <= max)
                    return value;
                System.out.println("Wrong value! Input again, please!");
            }catch(NumberFormatException error){
                System.err.println(error);
            }
        }
    }
    
    public int getUserChoice(){
        System.out.println();
        for(String choice:this)
            System.out.println(choice);
        return inputInteger("Input your choice: ", minChoices, maxChoices);
    }
}

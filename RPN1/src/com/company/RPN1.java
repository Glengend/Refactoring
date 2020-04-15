package com.company;
import java.util.Scanner;

class StackNode {
    public StackNode(double data, StackNode underneath) {
        this.data = data;
        this.underneath = underneath;
    }

    public StackNode underneath;
    public double data;
}


class RPN {
    private String theCommand;                                                    //reorganized for readability and both logic eg current command
    private StackNode topStackNode;                                              // refactored for clarity
   static Scanner in = new Scanner(System.in);                                  //optimizing scanner object
    public void into(double new_data) {
        StackNode newNode = new StackNode(new_data, topStackNode);              //refactored variable name for consistency
        topStackNode = newNode;
    }

    public double outof() {
        double topData = topStackNode.data;                                         //refactored (renamed) variable for consistency
        topStackNode = topStackNode.underneath;
        return topData;
    }

    public RPN(String command) {
        this.topStackNode = null;                                                         //refactored this for clarity of scope added this.
        this.theCommand = command;
    }

    public double get() {
        double a, b;
        int j;

        for (int i = 0; i < theCommand.length(); i++) {
// if it's a digit
            if (Character.isDigit(theCommand.charAt(i))) {
                double number;

// get a string of the number
                String temp = "";
                for (j = 0; (j < 100) && (Character.isDigit(theCommand.charAt(i)) || (theCommand.charAt(i) == '.')); j++, i++) {
                    temp = temp + String.valueOf(theCommand.charAt(i));
                }

// convert to double and add to the stack
                number = Double.parseDouble(temp);
                into(number);
            } else if (theCommand.charAt(i) == '+') {
                b = outof();
                a = outof();
                into(a + b);
            } else if (theCommand.charAt(i) == '-') {
                b = outof();
                a = outof();
                into(a - b);
            } else if (theCommand.charAt(i) == '*') {
                b = outof();
                a = outof();
                into(a * b);
            } else if (theCommand.charAt(i) == '/') {
                b = outof();
                a = outof();
                into(a / b);
            } else if (theCommand.charAt(i) == '^') {
                b = outof();
                a = outof();
                into(Math.pow(a, b));
            } else if (theCommand.charAt(i) != ' ') {
                IllegalArgumentException up =new IllegalArgumentException("Uses numbers and arithmetic only bitches!");                 //refactored  error codes including letters
                throw  up;
            }
        }

        double val = outof();

        if (topStackNode != null) {
            throw new IllegalArgumentException();
        }

        return val;
    }



    /* main method */
    public static void main(String args[]) {
        while (true) {
            try {                                                                               // change in code using try catch defencing programming
                System.out.println("Enter RPN expression or \"quit\".");
                String rpnInput = in.nextLine();
                if (rpnInput.equals("quit")) {
                    break;
                } else {
                    RPN calc = new RPN(rpnInput);                                             //refactoring using the IDE for readability
                    System.out.printf("Answer is %f\n", calc.get());
                }
            } catch (Exception bitch) {
                System.out.println("This is fucken wrongggg!!" + bitch.getMessage());
            }
        }
    }
}
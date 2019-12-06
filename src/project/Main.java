package project;

import java.io.IOException;
import java.util.Scanner;

// Final Version 2.4 Edited in 22/10

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner cin = new Scanner(System.in);
        String input;
        Boolean check;     // To Check if the parser return True
        int counter = 0;   // To Check The Number of arguments

        Parser p = new Parser();
        Terminal t = new Terminal();

        while(true) {

            input = "";
            input = cin.nextLine();
            String[] commands = input.split(" \\| ");  // Split The input by pipe operator

            // Loop the Command lines
            for (int i = 0; i < commands.length; i++) {

                check = p.parse(commands[i]);
                // Parse each command
                if (check) {                      // If Parser Return True

                    String[] arguments = p.getArguments();     // Get the Arguments of this command line

                    // Check the short and full path
                    for(int l=0 ; l<arguments.length ; l++)
                    {
                        if(arguments[l] == null) break;
                        if(arguments[l].charAt(1) != ':')           // the case if it is a short path
                        {
                            arguments[l] = t.getDefaultPath() + "\\" + arguments[l];
                        }
                    }
                    // Get the number of arguments
                    for (int j = 0; j < arguments.length; j++) {
                        if (arguments[j] != null) counter += 1;

                    }
                    // Check the cmd and send the cmd and the arguments to the terminal
                    switch (p.getCmd()) {
                        case "cp":
                            if (counter < 2) System.out.println("Few number of Arguments!");
                            else if (counter > 2) System.out.println("too many number of Arguments!");
                            else
                                t.cp(arguments[0], arguments[1]);
                            break;
                        case "mv":
                            if (counter < 2) System.out.println("Few number of Arguments!");
                            else if (counter > 2) System.out.println("too many number of Arguments!");
                            else
                                t.mv(arguments[0], arguments[1]);
                            break;
                        case "cat":
                            if (counter < 1) System.out.println("Few number of Arguments!");
                            else
                                for (int k = 0; k < counter; k++) {
                                    t.cat(arguments[k]);
                                }
                            break;
                        case "mkdir":
                            if (counter < 1) System.out.println("Few number of Arguments!");
                            else if (counter > 1) System.out.println("too many number of Arguments!");
                            else
                                t.mkdir(arguments[0]);
                            break;
                        case "rmdir":
                            if (counter < 1) System.out.println("Few number of Arguments!");
                            else if (counter > 1) System.out.println("too many number of Arguments!");
                            else
                                t.rmdir(arguments[0]);
                            break;
                        case "rm":
                            if (counter < 1) System.out.println("Few number of Arguments!");
                            else if (counter > 1) System.out.println("too many number of Arguments!");
                            else
                                t.rm(arguments[0]);
                            break;
                        case "cd":
                            if (counter < 1) System.out.println("Few number of Arguments!");
                            else if (counter > 1) System.out.println("too many number of Arguments!");
                            else
                                t.cd(arguments[0]);
                            break;
                        case "ls":
                            if (counter > 1) System.out.println("too many number of Arguments!");
                            else {
                                if (arguments[0] != null) t.ls(arguments[0]);
                                else t.ls();
                            }
                            break;
                        case "more":
                            if (counter < 1) System.out.println("Few number of Arguments!");
                            else if (counter > 1) System.out.println("too many number of Arguments!");
                            else
                                t.more(arguments[0]);
                            break;
                        case "pwd":
                            if (counter > 0) System.out.println("too many numbers of Arguments!");
                            else
                                t.pwd();
                            break;
                        case "clear":
                            if (counter > 0) System.out.println("too many numbers of Arguments!");
                            else
                                t.clear();
                            break;
                        case "date":
                            if (counter > 0) System.out.println("too many numbers of Arguments!");
                            else
                                t.date();
                            break;
                        case "help":
                            if (counter > 0) System.out.println("too many numbers of Arguments!");
                            else
                                t.help();
                            break;
                        case "args":
                            if (counter > 0) System.out.println("too many numbers of Arguments!");
                            else
                                t.args();
                            break;
                        case "exit":
                            t.exit();
                            break;
                    }
                    counter = 0;
                }
            }
        }
    }
}


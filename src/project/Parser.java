package project;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    String[] args;
    String cmd , path;
    ArrayList<String> Commands = new ArrayList<String>(List.of("cp", "cd", "clear", "ls", "mv", "rm", "mkdir", "rmdir", "cat", "more", "pwd", "args", "date", "exit" , "help"));
    Boolean check = false , space = true , quote = false;
    int index = 0;

    public boolean parse(String input) {
        index = 0;
        cmd = "";
        path = "";
        args = new String[20];

        // Loop to the end of the Input
        for (int i = 0; i < input.length(); i++) {
            // Take the cmd
            while (true) {
                cmd += input.charAt(i);
                i += 1;
                if(i == input.length() ||input.charAt(i) == ' ' ) break;
            }
            // Check if the cmd is valid
            if (!Commands.contains(cmd)) {
                System.out.println(cmd + " is not recognized as an internal or external command");
                return false;
            }
            if(i == input.length()) return true;
            // Ignore the space
            if(input.charAt(i) == ' ') i += 1;

            // Get the Arguments
            while(true)
            {
                // Handling if the path contain a space
                if(input.charAt(i) == '\"')
                {
                    i += 1;
                    while(input.charAt(i) != '\"' && i != input.length())
                    {
                        path += input.charAt(i);
                        i += 1;
                    }
                    args[index] = path;
                    path = "";
                    index += 1;
                }

                if(input.charAt(i) == '\"') i+=1;
                if(i == input.length()) return true;
                if(input.charAt(i) == ' ') i+=1;

                path += input.charAt(i);
                i += 1;

                if(i == input.length())
                {
                    args[index] = path;
                    return true;
                }
                if(input.charAt(i) == ' ')
                {
                    args[index] = path;
                    path = "";
                    i += 1;
                    index += 1;
                }
            }
        }
    return true;
    }
    public String getCmd()
    {
        return cmd;
    }
    public String[] getArguments()
    {
        return args;
    }
}
package project;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Scanner;

public class Terminal  {
    String defaultPath = "C:\\Users\\user\\Desktop";

    //Copy File
    public void cp(String sourcePath, String destinationPath) throws IOException
    {
        File source = new File(sourcePath);
        File destination = new File(destinationPath);

        if(!source.isFile() || !destination.isFile())
        {
            System.out.println("Failed : The two arguments should be files!");
            return;
        }
        FileUtils.copyFile(source , destination);
    }

    //Move File
    public void mv(String sourcePath, String destinationPath) throws IOException {

        File source =new File(sourcePath);
        File destination =new File(destinationPath);

        if(!source.isFile())
        {
            System.out.println("Failed! : Source path should be file");
            return;
        }
        if(!destination.isFile() && destination.isDirectory())
        {
            if(new File(destination+ "\\" + source.getName()).exists())
            {
                System.out.println("File Already Exist");
                return;
            }
            FileUtils.moveFileToDirectory(source , destination , false);
            return;
        }
        /*if(source.renameTo(new File(new File(destinationPath) + source.getName()))){
            System.out.println("File is moved successful!");
        }
        else
            System.out.println("File is failed to move!");

         */
    }
    // Concatenate file and display It
    public void cat(String sourcePath) throws IOException {
        String line = null;
        File source = new File(sourcePath);

        if(!source.isFile()) System.out.println("Failed : The argument should be file!");

        FileReader fileReader = new FileReader(sourcePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }

    // Make Directory
    public void mkdir(String path) {
            File file = new File(path);
            if (!file.exists()) file.mkdir();
    }
    // Remove Empty Directory
    public void rmdir(String sourcePth) {

        File directory = new File(sourcePth);
        //make sure directory exists
        if (!directory.exists()) {
            System.out.println("Directory does not exist.");
            return;
        }
        else directory.delete();
    }
    // Remove File and its Directory
    public void rm(String path) throws IOException {
        File file = new File(path);
        if(!file.isDirectory())
        {
            System.out.println("Path Should be a directory!");
            return;
        }
        FileUtils.forceDelete(file);
    }
    // Show all the Content of this File (Default Path)
    public void ls()
    {
        Path dir = Paths.get(defaultPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Show all the Content of this File (Input Path)
    public void ls(String path)
    {
        if(new File(path).isDirectory()) {
            Path dir = Paths.get(path);
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                for (Path file : stream) {
                    System.out.println(file.getFileName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else System.out.println(path + " is Not a directory");
    }
    // Date
    public void date(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(format.format(date));
    }
    // Show the Current directory
    public void pwd()
    {
        System.out.println(defaultPath);
    }
    // Clear The Screen
    public  void clear(){
        for(int i=0 ; i<50 ; i++)
        {
            System.out.println();
        }
    }
    // Change The Directory
    public void cd(String sourcePath)
    {
        File file = new File(sourcePath);
        if(file.isDirectory())
            defaultPath = sourcePath;
        else System.out.println(sourcePath + " Is not a Directory !");
    }
    // display and scroll down the output in one direction
    public void more(String sourcePath) throws IOException {
        File file = new File(sourcePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int i = 0;
        String input ;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
            i++;
            if (i % 10==0) {
                Scanner scan = new Scanner(System.in);
                input = scan.nextLine();
            }
        }

        br.close();
    }
    public void help()
    {
        System.out.println("cp : copy File To another Directory");
        System.out.println("mv : move File To another Directory");
        System.out.println("ls : show all the content of this Path");
        System.out.println("cd : Change the default directory");
        System.out.println("pwd : Show the current Directory");
        System.out.println("cat : concatenate files and print the content");
        System.out.println("mkdir : create a directory with each given name");
        System.out.println("rmdir : removes each given empty directory");
        System.out.println("rm : removes each specified file can be used to remove directory");
        System.out.println("more : display and scroll down the output in one direction");
        System.out.println("clear : Clear the Screen");
        System.out.println("date : Show th Date");
    }
    public void args()
    {
        System.out.println("cp : arg1: sourcePath arg2: destinationPath");
        System.out.println("mv : arg1: sourcePath arg2: destinationPath");
        System.out.println("ls : (none) or arg1: Path");
        System.out.println("cd : arg1: Path");
        System.out.println("pwd : (none)");
        System.out.println("cat : arg1: sourcePath (can take many args)");
        System.out.println("mkdir : arg1: sourcePath");
        System.out.println("rmdir : arg1: sourcePath");
        System.out.println("rm : arg1: sourcePath");
        System.out.println("more : arg1: sourcePath");
        System.out.println("clear : (none)");
        System.out.println("date : (none)");
    }
    public void exit()
    {
        System.exit(0);
    }

    public String getDefaultPath() {
        return defaultPath;
    }
}

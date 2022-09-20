import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/*


Part6 not finished little bot




*/


public class Launcher {

    static Console mainConsole = new Console();

    public static void main(String[] args) {
        System.out.println("This is a welcome message from dev.");
        var m = new Scanner(System.in);
        String nextInput = "";
        while (!Objects.equals(nextInput, "quit")) {

            System.out.print("Enter command: ");
            nextInput = m.nextLine();

            if (Objects.equals(nextInput, "fibo")) {
                mainConsole.preFibo();
            }
            else if (Objects.equals(nextInput, "comPasOuf"))
            {
                mainConsole.comPasOuf();
            }
            else if (Objects.equals(nextInput, "quit"))
            {
                System.out.println("Exiting!!!!");
                break;
            }
            else
            {
                System.out.println("Unknown Command!");
            }

        }
        System.exit(1);


    }

}


interface ConsoleInterface {

    public void comPasOuf();
    public int fibo(int n);
    public void preFibo();
    public String name();

    public boolean run(Scanner scanner);
}


class Console implements ConsoleInterface {

    private String me = "The Best Dev in the world";


    public boolean run(Scanner scanner){
        return scanner.hasNext();
    }
    public String name(){
        return me;
    }
    public void preFibo(){
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter Fibo number: ");
        int nFibo = myObj.nextInt();
        // Output input by user
        System.out.println("Chosen Number: " + nFibo);
        System.out.println(fibo(nFibo));
    }


    public void comPasOuf() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("ComPasOuf");
        System.out.println("Enter File Path: ");
        String path = (myObj.nextLine());
        File f = new File(path);
        if (f.exists()){
            try {
                String bigText = java.nio.file.Files.readString(Path.of(path));
                String[] splitArray = (bigText.toLowerCase().replace("\n","").split(" "));
                Dictionary<String, Integer> dict = new Hashtable<>();


                String greatWord = "";
                int greatWordNum = 0;

                List<String> keyList = new ArrayList<>();
                for (int i = 0; i < splitArray.length; i = i+1){
                    if (!keyList.contains(splitArray[i])) {
                        keyList.add(splitArray[i]);
                        dict.put(splitArray[i], 1);
                    }
                    else
                    {
                        dict.put(splitArray[i], dict.get(splitArray[i])+1);
                        if (dict.get(splitArray[i]) > greatWordNum){
                            greatWord = splitArray[i];
                            greatWordNum = dict.get(splitArray[i]);
                        }
                    }
                }
                System.out.println("GreatWord: " + greatWord + "\nGreatWordNum: " + greatWordNum);
/*
                        for (int i = 0; i < splitArray.length; i = i + 1) {
                                dict.put(splitArray[i], dict.get(splitArray[i])+1);
                        }*/
                System.out.println(dict);


                // /home/saturne/data1.txt
                // comPasOuf
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public int fibo(int n) {
        if(n < 0) {
            System.out.println("Incorrect input");
        }
        else if (n == 0) {
            return 0;
        }
        else if( n == 1 || n == 2) {
            return 1;
        }
        return fibo(n - 1) + fibo(n - 2);
    }
}
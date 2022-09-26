import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/*


Part6 not finished little bot




*/


public class Launcher {

    static Quit quitInstance = new Quit();
    static Freq freqInstance = new Freq();
    static Fibo fiboInstance = new Fibo();
    static Predict predictInstance = new Predict();

    static List<Command> ListCommand = new ArrayList<>();


    public static void main(String[] args) {
        boolean found = false;
        ListCommand.add(quitInstance);
        ListCommand.add(fiboInstance);
        ListCommand.add(freqInstance);
        ListCommand.add(predictInstance);
        System.out.println("This is a welcome message from dev.");
        String nextInput = "";
        while (!Objects.equals(nextInput, "quit")) {
            var m = new Scanner(System.in);
            System.out.print("Enter command: ");
            nextInput = m.nextLine();
            for (Command command : ListCommand) {
                if (Objects.equals(nextInput, command.name())) {
                    found = true;
                    if (!command.run(m)){
                        break;
                    };
                }
            }
            if (!found)
                System.out.println("Unknown Command!");
        }
        System.exit(1);
    }

}


interface Command{

    public String name();

    public boolean run(Scanner scanner);
}


class Fibo implements Command {

    private String me = "fibo";


    public boolean run(Scanner scanner){

        preFibo(scanner);
        return true;
    }
    public String name(){
        return me;
    }

    public void preFibo(Scanner myObj){

        System.out.println("Enter Fibo number: ");
        int nFibo = myObj.nextInt();
        // Output input by user
        System.out.println("Chosen Number: " + nFibo);
        System.out.println(fibo(nFibo));
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


class Freq implements Command {

    private static String me = "freq";

    public String name(){
        return me;
    }

    public boolean run(Scanner myObj){
        System.out.println("ComPasOuf");
        System.out.println("Enter File Path: ");
        String path = (myObj.nextLine());
        comPasOuf(path);
        return true;
    }

    public void comPasOuf(String path) {
        File f = new File(path);
        if (f.exists()){
            String bigText = "";
            try {
                bigText = java.nio.file.Files.readString(Path.of(path));
            }
            catch (IOException e) {
                System.out.println("File Not Found !");
                System.exit(-1);
            }
            if (bigText.length() != 0){

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
                System.out.println(dict);
                // /home/saturne/data1.txt
                // comPasOuf
            }
            else
            {
                System.out.println("Empty File. Exiting !");
                System.exit(-1);
            }

        }
    }
}

class Predict implements Command{

    private static String me= "predict";

    public String name() {
        return me;
    }

    public boolean run(Scanner scanner) {
        System.out.println("Predict");
        System.out.println("Enter File Path: ");
        String path = (scanner.nextLine());
        predict(path);
        return false;
    }


    public static void predict(String path){
        File f = new File(path);
        if (f.exists()){
            String bigText = "";
            try {
                bigText = java.nio.file.Files.readString(Path.of(path));
            }
            catch (IOException e) {
                System.out.println("Unreadable File:" + e.toString());
                System.exit(-1);
            }
            if (bigText.length() != 0){

                String[] splitArray = (bigText.toLowerCase().replace("\n","").split(" "));
                Dictionary<String, List<Integer>> dict = new Hashtable<>();
                Hashtable<Integer, String> dict2 = new Hashtable<>();
                String greatWord = "";
                int greatWordNum = 0;

                List<String> keyList = new ArrayList<>();
                for (int i = 0; i < splitArray.length; i = i+1){
                    if (!keyList.contains(splitArray[i])) {
                        List<Integer> tmpList = new ArrayList<>();
                        tmpList.add(i);
                        keyList.add(splitArray[i]);
                        dict2.put(i, splitArray[i]);
                        dict.put(splitArray[i], tmpList);
                    }
                    else
                    {
                        List<Integer> getList = dict.get(splitArray[i]);
                        getList.add(i);
                        dict.put(splitArray[i], getList);
                    }
                }
                int prevKey = 0;
                List<Integer> setCopy = new ArrayList<>(dict2.keySet());
                for (Integer val: setCopy)
                {
                    if (val != 0){
                        if (val != prevKey+1)
                        {
                            dict2.put(val-1, splitArray[val-1]);
                        }
                        prevKey = val;
                    }
                }
                TreeMap<Integer, String> sortedMap = new TreeMap<Integer, String>(dict2);
                System.out.println(dict);
                System.out.println(sortedMap);

            }
            else
            {
                System.out.println("Empty File. Exiting !");
                System.exit(-1);
            }

        }
    }


}

class Quit implements Command{

    private static String me = "quit";

    public String name() {
        return me;
    }

    public boolean run(Scanner scanner) {
        System.exit(0);
        return false;
    }
}

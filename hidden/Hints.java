import java.io.*;
import java.util.*;
import java.util.regex.*;
class Hints{
    public static void main(String[] args){
        int Stage=0;
        Stage=getStage();
        ArrayList<String> hints= new ArrayList<String>();
        hints=loadHints(Stage);
        Scanner userInput = new Scanner(System.in);
        Iterator<String> give = hints.iterator();
        System.out.println("you are on stage "+Stage);
        System.out.println("Write Y for next hint, N to close a program and R to check on what stage you are.");
        while(userInput.hasNext()){
        switch(userInput.next().toLowerCase()){
            case "y":
                if(give.hasNext()){
                System.out.println(give.next());
                }
                else{
                give = hints.iterator();
                System.out.println(give.next());
                }
                break;
            case "n":
                return;
            case "r":
                if(Stage!=getStage()){
                    Stage=getStage();
                    hints=loadHints(Stage);
                    give = hints.iterator();
                }
                System.out.println("you are on stage "+Stage);
                break;
            case "e":
                System.out.println(getStage());
                break;
        }
        }
    }
    
    
    private static int getStage(){
        try{
            BufferedReader stageReader = new BufferedReader(new FileReader("./hidden/config.discouragingextension")); 
            String line;
            while((line=stageReader.readLine())!=null){
                Scanner Stagelooker =new Scanner(line);
                String template= "Stage";
                while(Stagelooker.hasNext()){
                    if(template.equals(Stagelooker.next())){
                        Stagelooker.hasNext();
                        return Integer.parseInt(Stagelooker.next());
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer. error code filenotFoundException");
            return -1;
        }
        catch(Exception e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer."); 
            return -1;
        }
    return -2;
    }
    
    
    private static ArrayList<String> loadHints(int Stage){
        ArrayList<String> hintsForStage =new ArrayList<String>();
        boolean writeToArray=false;
        try{
            BufferedReader stageReader = new BufferedReader(new FileReader("./hidden/hints.dat")); 
            String line;
            while((line=stageReader.readLine())!=null){
                Scanner Stagelooker =new Scanner(line);
                String template= "Stage";
                int toCompare=0;
                while(Stagelooker.hasNext()){
                    if(template.equals(Stagelooker.next())){
                        Stagelooker.hasNext();
                        toCompare=Integer.parseInt(Stagelooker.next());
                        if(toCompare==Stage){
                            writeToArray=true;
                            break;
                        }
                        if(toCompare==(Stage+1)){
                            writeToArray=false;;
                            break;
                        }
                    }
                }
                if(writeToArray){
                    hintsForStage.add(line);
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer. error code filenotFoundException");
            return null;
        }
        catch(Exception e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer."); 
            return null;
        }
    hintsForStage.remove(0);
    return hintsForStage;
    }
}
import java.io.*;
import java.util.*;
class Human{
int weight=60;
    static String see(){
        int Stage =0;
        String toSay=null;
        try(BufferedReader stageReader = new BufferedReader(new FileReader("./hidden/config.discouragingextension")) ;  BufferedReader sayReader = new BufferedReader(new FileReader("./hidden/say.dat")) ){
            String line;
            while((line=stageReader.readLine())!=null){
                Scanner Stagelooker =new Scanner(line);
                String template= "Stage";
                while(Stagelooker.hasNext()){
                    if(template.equals(Stagelooker.next())){
                        Stagelooker.hasNext();
                        Stage=Integer.parseInt(Stagelooker.next());
                    }
                }
            }
            for(int i=0;i<=Stage;i++){
                toSay=sayReader.readLine();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer. error code filenotFoundException");
        }
        catch(Exception e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer."); 
        }
        return toSay;
    }
}
import java.io.*;
import java.util.*;
class World{
    int Stage=0;
    
    World(){
        try{
            BufferedReader stageReader = new BufferedReader(new FileReader("./hidden/config.discouragingextension")); 
            String line;
            StringBuffer inputBuffer = new StringBuffer();
            while((line=stageReader.readLine())!=null){
                inputBuffer.append(line);
                inputBuffer.append('\n');
                Scanner Stagelooker =new Scanner(line);
                String template= "Stage";
                while(Stagelooker.hasNext()){
                    if(template.equals(Stagelooker.next())){
                        Stagelooker.hasNext();
                        Stage=Integer.parseInt(Stagelooker.next());
                    }
                }
            }
        stageReader.close();
        System.out.println(Stage);
        if(Stage==0){
            try(FileOutputStream fileOut = new FileOutputStream("./hidden/config.discouragingextension")){
                String inputStr = inputBuffer.toString();
                System.out.println(inputStr);
                inputStr=inputStr.replace("Stage 0","Stage 1");
                System.out.println(inputStr);
                fileOut.write(inputStr.getBytes());
            }
            catch(Exception e){
                System.out.println("Error inside the writein to config. You shuld not be able to see this. Contact the gamme developer."); 
            }
        }
        System.out.println("you did it!");
        }
    catch(FileNotFoundException e){
        System.out.println("You shuld not be able to see this. Contact the gamme developer. error code filenotFoundException");
    }
    catch(Exception e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer."); 
    }
    }
}
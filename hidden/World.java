import java.io.*;
import java.util.*;
import java.util.regex.*;
class World{
    int Stage=0;
    
    World(){
        Stage=getStage();
        if (Stage==0){
            changeStageTo(1);
            pushThePlot(1);
        }
    }
    World(String key){
        this();
        if(key.equals("calm down")){
            if(Stage<2){
                changeStageTo(2);
                pushThePlot(2);
            }
        }
    }
    private static void pushThePlot(int Stage){
        ArrayList<String> lines=null;
        try(    BufferedReader story = new BufferedReader(new FileReader("./hidden/Story.dat")); BufferedReader player = new BufferedReader(new FileReader("Player.java"))  ){
            // try to get a lines from story 
            lines =new ArrayList<String>();
            String line=null;
            boolean start=false;
            String temp="Stage "+Integer.toString(Stage);
            Pattern whatToAdd =Pattern.compile(temp);
            temp="Stage "+Integer.toString(Stage+1);
            Pattern endStage =Pattern.compile(temp);
            Matcher matcher=null; 
            while((line=story.readLine())!=null){
                if(start){
                    matcher=endStage.matcher(line);
                    if(matcher.find()){
                        break;
                    }
                    lines.add(line);
                }
                else{
                    matcher=whatToAdd.matcher(line);
                    if(matcher.find()){
                        start=true;
                    }
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer. error code filenotFoundException");
        }
        catch(IOException e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer. error code IOException");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e); 
        }
        // lines from the storry are in ArrayList lines 
        // put a code into a string with changes
        try{
            BufferedReader stageReader = new BufferedReader(new FileReader("Player.java"));
            String line;
            boolean isStoryWritten=false;
            StringBuffer inputBuffer = new StringBuffer();
            String temp="Narrator:";
            if (Stage==1)    temp="Story:";
            Pattern storyPattern =Pattern.compile("Story:");
            Pattern narratorPattern =Pattern.compile("Narrator:");
            Matcher matcher=null; 
            Pattern mainfun = Pattern.compile("public static void main");
            Matcher storyMatcher=null;
            while((line=stageReader.readLine())!=null){
                matcher = narratorPattern.matcher(line);
                storyMatcher = mainfun.matcher(line);
                if(matcher.find()){
                    continue;
                }
                matcher = storyPattern.matcher(line);
                if(matcher.find()){
                    continue;
                }
                if(storyMatcher.find()){
                inputBuffer.append(line);
                inputBuffer.append('\n');
                Iterator<String> poStory = lines.iterator();
                while(poStory.hasNext()){
                    inputBuffer.append("        ");
                    inputBuffer.append(poStory.next());
                    inputBuffer.append('\n');
                }
                continue;
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
                }
                stageReader.close();
                // nadpisanie nowego pliku 
                try(FileOutputStream fileOut = new FileOutputStream("Player.java")){
                    String inputStr = inputBuffer.toString();
                    fileOut.write(inputStr.getBytes());
                }
                catch(Exception e){
                    System.out.println("Error inside the writein to config. You shuld not be able to see this. Contact the gamme developer."); 
                }      
        }
        catch(FileNotFoundException e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer. error code filenotFoundException");
        }
        catch(IOException e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer. error code IOException");
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e); 
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
    
    private static void changeStageTo(int target){
        int stagelocal=0;
        try{
             //read the config file 
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
                        stagelocal=Integer.parseInt(Stagelooker.next());
                    }
                }
            }
        stageReader.close();
        //write the config file 
            try(FileOutputStream fileOut = new FileOutputStream("./hidden/config.discouragingextension")){
                String inputStr = inputBuffer.toString();
                inputStr=inputStr.replace("Stage "+ Integer.toString(stagelocal),"Stage "+ Integer.toString(target));
                fileOut.write(inputStr.getBytes());
            }
            catch(Exception e){
                System.out.println("Error inside the writein to config. You shuld not be able to see this. Contact the gamme developer."); 
            }
        }
        catch(FileNotFoundException e){
            System.out.println("You shuld not be able to see this. Contact the gamme developer. error code filenotFoundException");
        }
        catch(Exception e){
                System.out.println("You shuld not be able to see this. Contact the gamme developer."); 
        }
    }
    
    
}
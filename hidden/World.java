import java.io.*;
import java.util.*;
import java.util.regex.*;
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
        System.out.println("stage is "+Stage);
        if(Stage==0){
            try(FileOutputStream fileOut = new FileOutputStream("./hidden/config.discouragingextension")){
                String inputStr = inputBuffer.toString();
                inputStr=inputStr.replace("Stage 0","Stage 1");
                Stage=1;
                this.pushThePlot(Stage);
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
    
    private static void pushThePlot(int Stage){
        try(BufferedReader story = new BufferedReader(new FileReader("./hidden/Story.dat"))/*; BufferedReader player = new BufferedReader(new FileReader("Player.java"))*/){
            // try to get a lines from story 
            ArrayList<String> lines =new ArrayList<String>();
            String line=null;
            boolean start=false;
            System.out.println("Stage "+Integer.toString(Stage+1)+"*");
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
            System.out.println(Arrays.toString(lines.toArray()));
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
}
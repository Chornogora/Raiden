package ua.nure.bulhakov.summary.controller.launch;

import ua.nure.bulhakov.summary.tags.LocalizationTagHandler;

import java.io.*;
import java.util.*;

public class ResourceLauncher implements Launcher{

    private static ResourceLauncher instance;

    private ResourceLauncher(){

    }

    public static ResourceLauncher getInstance(){
        if(instance == null){
            instance = new ResourceLauncher();
        }
        return instance;
    }

    @Override
    public void config(String rootPath) throws LaunchException {
        Map<String, ResourceBundle> map = new HashMap<>();

        try{
            prepareFile(rootPath + "WEB-INF/Localization/resources_ru.properties", rootPath + "WEB-INF/Localization/resources_ru_ok.properties");
            FileInputStream stream = new FileInputStream(rootPath + "/WEB-INF/Localization/resources_ru_ok.properties");
            ResourceBundle bundle = new PropertyResourceBundle(stream);
            map.put("ru", bundle);

            prepareFile(rootPath + "WEB-INF/Localization/resources_en.properties", rootPath + "WEB-INF/Localization/resources_en_ok.properties");
            stream = new FileInputStream(rootPath + "/WEB-INF/Localization/resources_en_ok.properties");
            ResourceBundle bundle2 = new PropertyResourceBundle(stream);
            map.put("en", bundle2);
        }catch(FileNotFoundException e){
            throw new LaunchException("Can't find resources", e);
        }catch(IOException e){
            throw new LaunchException("Can't load resources", e);
        }

        LocalizationTagHandler.setBundles(map);
    }

    private void prepareFile(String filename, String resultName)throws IOException{
        PrintWriter pw = null;
        try (Scanner sc = new Scanner(new FileReader(filename))){
            pw = new PrintWriter(resultName);
            while(sc.hasNext()){
                String str = sc.nextLine().replace(" ", "\\u0020");
                pw.println(str);
            }
        }catch(IOException e){
            //Never will be thrown
        }finally {
            pw.close();
        }
    }
}

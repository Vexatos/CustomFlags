package mods.custom_flags.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.Minecraft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * User: nerd-boy
 * Date: 2/08/13
 * Time: 3:38 PM
 * TODO: Add discription
 */
public class LanguageHelper {

    /**
     * Loads all the languages from the lang folder
     */
    public static void loadAllLanguages(String displayName, String prefix, String langFolder){

        loadDefault(displayName, prefix);

        File folder = getOrGenerateLangFolder(displayName, langFolder);

        readAllLanguages(folder, displayName, prefix);
    }

    /**
     * Loads the default language file
     */
    private static void loadDefault(String displayName, String prefix) {
        Minecraft.getMinecraft().getLogAgent().logInfo(displayName+": Loading Default Language");
        String line = null;
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(LanguageHelper.class.getResourceAsStream("en_US.lang")));
            while ((line = reader.readLine()) != null) {
                String[] split = line.split("=");
                if(split.length == 2){
                    if(split[0].startsWith("item.")){
                        split[0] = split[0].replace("item.", "item."+prefix+":");
                    }else if (split[0].startsWith("tile.")){
                        split[0] = split[0].replace("tile.", "tile."+prefix+":");
                    }
                    LanguageRegistry.instance().addStringLocalization(split[0], split[1]);
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Reads all the .lang files from the given folder
     * @param folder the folder to read the lang files from
     */
    private static void readAllLanguages(File folder, String displayName, String prefix) {
        BufferedReader reader = null;

        if(folder.exists()){
            for (File langFile : folder.listFiles()) {
                if(langFile.getName().endsWith(".lang")){
                    try{
                        reader = new BufferedReader(new FileReader(langFile));
                        String l = langFile.getName().substring(0, langFile.getName().length()-5);

                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            String[] split = line.split("=");
                            if(split.length == 2){
                                if(split[0].startsWith("item.")){
                                    split[0] = split[0].replace("item.", "item."+prefix+":");
                                }else if (split[0].startsWith("tile.")){
                                    split[0] = split[0].replace("tile.", "tile."+prefix+":");
                                }
                                LanguageRegistry.instance().addStringLocalization(split[0], l, split[1]);
                            }
                        }

                        Minecraft.getMinecraft().getLogAgent().logInfo(displayName+": Loaded Language "+l);

                    }catch(Exception e){}
                    finally{
                        if(reader!=null){
                            try{
                                reader.close();
                            }catch(Exception e){}
                        }
                    }
                }
            }
        }

    }

    /**
     * Retrieves the folder containing the .lang files or generates it if it does not exist
     * @return
     */
    private static File getOrGenerateLangFolder(String displayName, String langFolder) {
        File dir = FMLClientHandler.instance().getClient().mcDataDir;
        File MBLang = new File(dir.getAbsolutePath()+File.separator+"lang"+File.separator+langFolder);
        if(!MBLang.exists()){
            Minecraft.getMinecraft().getLogAgent().logInfo(displayName+": Generating Language Folder");
            MBLang.mkdirs();
        }
        return MBLang;
    }

}
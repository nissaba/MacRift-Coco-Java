/* SkillFileReader */

import com.apple.cocoa.foundation.*;
import com.apple.cocoa.application.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class SkillFileReader extends FileReder {

    Hashtable skillList;
    Hashtable categorieList;

    public Hashtable makeSkillList() {
        NSOpenPanel panel = new NSOpenPanel();
        panel = panel.openPanel();
        panel.runModalInDirectory(null, null, null, null);
        NSArray an = panel.filenames();
        if(!OpenFile((String)an.objectAtIndex(0))){
            return null;
        }
        
        categorieList = new Hashtable();
        SkillClass skill = getNextSkill();
        
        while(skill != null){
            //if new cat make cat if existe get cat.
            if(categorieList.containsKey(skill.getCategorie())){
               skillList = (Hashtable) categorieList.get(skill.getCategorie());
            }else{
                skillList = new Hashtable();
                categorieList.put(skill.getCategorie(), skillList);
            }
            skillList.put(skill.getName(), skill);
            skill = getNextSkill();
        }

        return categorieList;
    }
    
    public Hashtable getSkillList(String key){
        if(categorieList.containsKey(key)){
          skillList = (Hashtable) categorieList.get(key);
          return skillList;
        } 
        return null;
    }
    
    public SkillFileReader() {
    }
    
    private SkillClass getNextSkill(){
        SkillClass Skill;
        StringBuffer tempStr;
        char tempChar;
        
        tempStr = new StringBuffer();
        Skill = new SkillClass();
        
        //while not at '@' read, check for eof
        while((tempChar = (char)Read()) != '@'){
            if(tempChar == (char)-1){
                try{
                    inFile.close();
                }catch(IOException e){}
                return null;
            }
        }
        //get name of skill
        while((tempChar = (char)Read()) != '{')
        {
            tempStr.append(tempChar);
        }
        Skill.setName(tempStr.toString());
        tempStr.delete(0,tempStr.length());
        //get the filds of the skill and interpret them
        while((tempChar = (char)Read()) != '}')
        {
            if(tempChar == '#'){
                while((tempChar = (char)Read()) != '=')
                {
                    if(tempChar != ' ')
                        tempStr.append(tempChar);
                }
                String compStr = tempStr.substring(0,tempStr.length());
                if(compStr.compareTo("categorie") == 0)
                {
                    while((tempChar = (char)Read()) != '"'){}
                    tempStr.delete(0,tempStr.length());
                    while((tempChar = (char)Read()) != '"')
                    {
                        tempStr.append(tempChar);
                    }
                    Skill.setCategorie(tempStr.substring(0,tempStr.length()));
                    tempStr.delete(0,tempStr.length());
                }else if(compStr.compareTo("description") == 0)
                {
                    while((tempChar = (char)Read()) != '"'){}
                    tempStr.delete(0,tempStr.length());
                    while((tempChar = (char)Read()) != '"')
                    {
                        tempStr.append(tempChar);
                    }
                    Skill.setDescription(tempStr.substring(0,tempStr.length()));
                    tempStr.delete(0,tempStr.length());
                }else if(compStr.compareTo("requirement") == 0)
                {
                    while((tempChar = (char)Read()) != '"'){}
                    tempStr.delete(0,tempStr.length());
                    while((tempChar = (char)Read()) != '"')
                    {
                        tempStr.append(tempChar);
                    }
                    Skill.setRequirement(tempStr.substring(0,tempStr.length()));
                    tempStr.delete(0,tempStr.length());
                }else if(compStr.compareTo("rule") == 0)
                {
                    while((tempChar = (char)Read()) != '"'){}
                    tempStr.delete(0,tempStr.length());
                    while((tempChar = (char)Read()) != '"')
                    {
                        tempStr.append(tempChar);
                    }
                    Skill.setRules(tempStr.substring(0,tempStr.length()));
                    tempStr.delete(0,tempStr.length());
                }else if(compStr.compareTo("levelrule") == 0)
                {
                    while((tempChar = (char)Read()) != '"'){}
                    tempStr.delete(0,tempStr.length());
                    while((tempChar = (char)Read()) != '"')
                    {
                        tempStr.append(tempChar);
                    }
                    Skill.setLevelRule(tempStr.substring(0,tempStr.length())); 
                    tempStr.delete(0,tempStr.length());
                }else
                {
                    return null;
                }
            }
        }
        return Skill;
    }

}

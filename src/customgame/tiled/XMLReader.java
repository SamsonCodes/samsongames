/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.tiled;

import java.util.ArrayList;

public class XMLReader 
{
    //Get list of elements within tags from list of strings source
    public static ArrayList<String> getElements(String tag, ArrayList<String> source)
    {
        ArrayList<String> elements = new ArrayList<>();
        String element = "";
        String currentTag = "";
        boolean tagStart = false;
        boolean startFound = false;
        for(int elemId = 0; elemId < source.size(); elemId++)
        {
            for(int charId = 0; charId < source.get(elemId).length(); charId ++)
            {
                if(!startFound)
                {
                    if(!tagStart)
                    {
                        if(source.get(elemId).charAt(charId) == '<')
                        {
                            tagStart = true;
                        }
                    }
                    else //if tagStart
                    {
                        if(source.get(elemId).charAt(charId) == '>')
                        {
                            tagStart = false;
                            if(currentTag.equals(tag))
                            {
                                startFound = true;
                            }
                            currentTag = "";
                        }
                        else
                        {
                            currentTag += source.get(elemId).charAt(charId);
                        }
                    }
                }
                else //if startFound
                {
                    element+=source.get(elemId).charAt(charId);
                    if(!tagStart)
                    {
                        if(source.get(elemId).charAt(charId) == '<')
                        {
                            tagStart = true;
                        }
                    }
                    else //if tagSTart
                    {
                        if(source.get(elemId).charAt(charId) == '>')
                        {
                            tagStart = false;
                            if(currentTag.equals("/" + tag))
                            {
                                element = element.replace("</" + tag + ">", ""); //remove endtag from element
                                elements.add(element);
                                element = "";
                                startFound = false;
                            }
                            currentTag = "";
                        }
                        else //if char != '>'
                        {
                            currentTag += source.get(elemId).charAt(charId);
                        }
                    }
                }
            }
        }
        return elements;
    }
    
     //Get element within tag from string source
    public static String getElement(String tag, String source)
    {
        ArrayList<String> sourceList = new ArrayList();
        sourceList.add(source);
        return getElement(tag, sourceList);
    }
    
    //Get element within tag from list of strings source
    public static String getElement(String tag, ArrayList<String> source)
    {
        ArrayList<String> elements = getElements(tag, source);
        return elements.get(0);
    }
    
    //Get list of elements within tags from string source
    public static ArrayList<String> getElements(String tag, String source)
    {
        ArrayList<String> sourceList = new ArrayList();
        sourceList.add(source);
        return getElements(tag, sourceList);
    }
    
    //Get list of elements with tag included from list of strings source
    public static ArrayList<String> getElementsPlus(String tag, ArrayList<String> source)
    {
        ArrayList<String> elements = new ArrayList<>();
        String element = "";
        String currentTag = "";
        boolean tagStart = false;
        boolean startFound = false;
        boolean noEndTag = false;
        for(int elemId = 0; elemId < source.size(); elemId++)
        {
            int tagStartId = 0;
            for(int charId = 0; charId < source.get(elemId).length(); charId ++)
            {
                if(!startFound)
                {
                    if(!tagStart)
                    {
                        if(source.get(elemId).charAt(charId) == '<')
                        {
                            tagStart = true;
                            tagStartId = charId;
                            element+="<";
                        }
                    }
                    else //if tagStart
                    {
                        element+=source.get(elemId).charAt(charId);
                        if(source.get(elemId).charAt(charId) == '>')
                        {
                            tagStart = false;
                            if(currentTag.equals(tag))
                            {
                                startFound = true;
                            }
                            else
                            {
                                element = "";
                            }
                            currentTag = "";
                        }
                        else if(source.get(elemId).charAt(charId) == ' ')
                        {
                            tagStart = false;
                            if(currentTag.equals(tag))
                            {
                                //System.out.println("currentTag = \"" + currentTag +"\"");
                                startFound = true;
                                noEndTag = true;
                            }
                            else
                            {
                                element = "";
                                currentTag += source.get(elemId).charAt(charId);
                            }
                            currentTag = "";
                        }
                        else
                        {
                            currentTag += source.get(elemId).charAt(charId);
                        }                        
                    }
                }
                else //if startFound
                {
                    element+=source.get(elemId).charAt(charId);
                    if(noEndTag)
                    {
                        if(source.get(elemId).charAt(charId) == '>')
                        {
                            tagStart = false;
                            noEndTag = false;
                            //System.out.println("element = \"" + element + "\"");
                            elements.add(element);
                            element = "";
                            startFound = false;
                            currentTag = "";
                        }
                    }
                    else //if !noEndTag
                    {
                        if(!tagStart)
                        {
                            if(source.get(elemId).charAt(charId) == '<')
                            {
                                tagStart = true;
                            }
                        }
                        else //if tagStart
                        {
                            if(source.get(elemId).charAt(charId) == '>')
                            {
                                tagStart = false;
                                if(currentTag.equals("/" + tag))
                                {
                                    elements.add(element);
                                    element = "";
                                    startFound = false;
                                }
                                currentTag = "";
                            }
                            else //if char != '>'
                            {
                                currentTag += source.get(elemId).charAt(charId);
                            }
                        }
                    }
                }
            }
        }
        return elements;
    }
    
     //Get element with tag included from string source
    public static String getElementPlus(String tag, String source)
    {
        ArrayList<String> sourceList = new ArrayList();
        sourceList.add(source);
        return getElementPlus(tag, sourceList);
    }
    
    //Get element with tag included from list of strings source
    public static String getElementPlus(String tag, ArrayList<String> source)
    {
        ArrayList<String> elements = getElementsPlus(tag, source);
        if(!elements.isEmpty())
            return elements.get(0);
        else
            return "";
    }
    
    //Get list of elements with tag included from string source
    public static ArrayList<String> getElementsPlus(String tag, String source)
    {
        ArrayList<String> sourceList = new ArrayList();
        sourceList.add(source);
        return getElementsPlus(tag, sourceList);
    }
}

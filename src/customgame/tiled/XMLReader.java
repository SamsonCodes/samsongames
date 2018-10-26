/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package customgame.tiled;

import java.util.ArrayList;

public class XMLReader 
{
    //Get list of elements with tag included from list of strings source
    public static ArrayList<String> getElementsPlus(String tag, ArrayList<String> source)
    {
        boolean noEndTag = true;
        for(String sourceLine: source)
        {
            if(sourceLine.contains("</" + tag + ">"))
                noEndTag = false;
        }
        ArrayList<String> elements = new ArrayList<>();
        String element = "";
        String currentTag = "";
        boolean tagStart = false;
        boolean startFound = false;
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
                            }
                            else
                            {
                                element = "";
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
    
    //Get attribute from a single element
    public static String getAttribute(String attriName, String element)
    {
        String attribute = "";
        
        boolean complete = false;
        if(element.contains(attriName + "="))
        {
            int index1 = element.indexOf(attriName + "=") + attriName.length()+2;
            int index2 = element.indexOf("\"", index1);
            attribute = element.substring(index1, index2);
            complete = true;
        }
        
        return attribute;
    }
    
    public static String stripTags(String tag, String element)
    {
        if(element.contains("<" + tag + ">"))
        {
            element = (element.replace("<" + tag + ">", "")).replace("</" + tag + ">", "");
        }
        else if(element.contains("<" + tag + " "))
        {
            element = (element.replace("<" + tag + " ", "")).replace(">", "");
        }
        return element;
    }
}

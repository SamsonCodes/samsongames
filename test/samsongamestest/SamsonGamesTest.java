/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package samsongamestest;

import customgame.Game;
import customgame.tiled.XMLReader;
import java.util.ArrayList;
import samsongamestest.states.UIState;
import samsongamestest.states.WorldState;


public class SamsonGamesTest 
{
    public final static String PACKAGE_PATH = "C:\\Users\\Samson\\Documents\\NetBeansProjects\\SamsonGamesTest\\src\\samsongamestest\\";
    public static final int FRAME_WIDTH = 1200, FRAME_HEIGHT = 900, TILE_SIZE = 50;
    
    public SamsonGamesTest()
    {        
//        xmlTest();
//        worldRenderTest();
        uiTest();
    }
    
    private void worldRenderTest()
    {
        System.out.println("WorldRenderTest");
        Game game = new Game("GAMETEST", FRAME_WIDTH, FRAME_HEIGHT);
        game.getStateMachine().add("world", new WorldState(game.getGui()));
        game.getStateMachine().change("world");
        game.run();
        System.out.println("");
    }
    
    private void uiTest()
    {
        System.out.println("UI Test");
        Game game = new Game("GAMETEST", FRAME_WIDTH, FRAME_HEIGHT);
        game.getStateMachine().add("ui", new UIState(game.getGui()));
        game.getStateMachine().change("ui");
        game.run();
        System.out.println("");
    }
    
    //This method tests whether the XMLReader class is doing what it is supposed to be doing. Naughty XMLReader class...
    private boolean xmlTest()
    {
        boolean passed = true;
        ArrayList<String> data = new ArrayList();
        data.add("<info company=\"Toshiba\" date=\"25-10-19\">");
        data.add("<customers random=\"momma\">");
        data.add("<customer>");
        data.add("<name>Samson</name> <age> 25 </age>");
        data.add("</customer>");
        data.add("<customer>");
        data.add("<name>Joshua</name> <age> 26 </age>");
        data.add("</customer>");
        data.add("</customers>");
        
        String info = XMLReader.getElementPlus("info", data);
        if(!info.equals("<info company=\"Toshiba\" date=\"25-10-19\">"))
        {
            passed = false;
            System.out.println("XMLReader.getElementPlus(\"info\", data) = " + info);
        }
        String infoX = XMLReader.stripTags("info", info);
        if(!infoX.equals("company=\"Toshiba\" date=\"25-10-19\""))
        {
            passed = false;
            System.out.println("XMLReader.stripTags(\"info\", info) = " + infoX);
        }
        String company = XMLReader.getAttribute("company", info);
        if(!company.equals("Toshiba"))
        {
            passed = false;
            System.out.println("The company is " + company);
        }
        String date = XMLReader.getAttribute("date", info);
        if(!date.equals("25-10-19"))
        {
            passed = false;
            System.out.println("The date is " + date);
        }
        String customerElement = XMLReader.getElementPlus("customers", data);
        if(!customerElement.equals("<customers random=\"momma\"><customer><name>Samson</name> <age> 25 </age></customer>"
                + "<customer><name>Joshua</name> <age> 26 </age></customer></customers>"))
        {
            passed = false;
            System.out.println("customerElement = " + customerElement);
        }
        String stripCustomers = XMLReader.stripTags("customers", customerElement);
        if(!stripCustomers.equals("<customer><name>Samson</name> <age> 25 </age></customer>"
                + "<customer><name>Joshua</name> <age> 26 </age></customer>"))
        {
            passed = false;
            System.out.println("stripCustomers = " + stripCustomers);
        }
        ArrayList<String> customers = XMLReader.getElementsPlus("customer", data);
        String[] customerAnswers = new String[]{"<customer><name>Samson</name> <age> 25 </age></customer>",
            "<customer><name>Joshua</name> <age> 26 </age></customer>"};
        for(int i = 0; i < customers.size(); i++)
        {
            if(!customers.get(i).equals(customerAnswers[i]))
            {
                passed = false;
                System.out.println("customers.get("+i+")=" +customers.get(i));
            }
        }
        //System.out.println("Customer elements:");
        ArrayList<String> names = new ArrayList();
        String[] stripAnswers = new String[]{"<name>Samson</name> <age> 25 </age>",
            "<name>Joshua</name> <age> 26 </age>"};
        for(int i = 0; i < customers.size(); i++)
        {
            String customerX = XMLReader.stripTags("customer", customers.get(i));
            if(!customerX.equals(stripAnswers[i]))
            {
                passed = false;
                System.out.println("customers.get("+i+")(wot) = " + customerX);
            }
            
            String name = XMLReader.stripTags("name",XMLReader.getElementPlus("name", customers.get(i)));
            names.add(name);
        }
        String[] nameAnswers = new String[]{"Samson","Joshua"};
        for(int i = 0; i < names.size(); i++)
        {
            if(!names.get(i).equals(nameAnswers[i]))
            {
                passed = false;
                System.out.println("names.get("+i+")=" + names.get(i));
            }
        }
        if(passed) 
            System.out.println("Passed xmlTest");
        else
            System.out.println("Failed xmlTest!!!");
        return passed;
    }

    public static void main(String[] args) 
    {
        SamsonGamesTest samsonGamesTest = new SamsonGamesTest();
    }

}

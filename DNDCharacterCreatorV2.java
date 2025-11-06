/*
Cameron Stone
9/10/2025
P4
*/
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class DNDCharacterCreatorV2
{
    static Scanner scan = new Scanner(System.in);
    //holds all the races and classes available to the player.
    static String[] races = {"Hill Dwarf", "Mountain Dwarf", "High Elf", "Wood Elf", "Drow Elf", "LightFoot Halfling", "Stout Halfling", "Human", "Dragonborn", "Forest Gnome", "Rock Gnome", "Half-Elf", "Half-Orc", "Tiefling"};
    static String[] classes = {"Artificer", "Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer", "WarLock", "Wizard"};
    //All the variables each character has.
    static String characterName;
    static String characterRace;
    static String characterClass;
    static int STR;
    static int DEX;
    static int CON;
    static int INT;
    static int WIS;
    static int CHA;
    static int racePos;
    //Variables used to confirm player input.
    static boolean createNewCharacter;
    static boolean writeToFile;
    static boolean reroll;
    static boolean confirm;
    static String options;
    public static void main(String[] args)
    {
        //Main function that handles rerunning main parts of code.
        createNewCharacter = true;
        System.out.println("Welcome to DND Character Creator V2.");
        while (createNewCharacter)
        {
            if (reroll)
            {
                reroll = false;
                createStats();
                fileOptions();
            }
            else
            {
                getName();
                getRace();
                getClasss();
                createStats();
                fileOptions();
            }

        }
    }
    static void getName()
    {
        //Ask the user for their name and confirms your choice saving it to a variable.
        confirm = false;
        while(!confirm)
        {
            System.out.println("Please enter your character's name:");
            characterName = scan.nextLine();
            System.out.println("Is " + characterName + " correct? y/n");
            options = scan.nextLine();
            if(options.equalsIgnoreCase("y"))
            {
                confirm = true;
                break;
            }
        }
    }
    static void getRace()
    {
        //Ask the user for their race and confirms your choice saving it to a variable.
        confirm = false;
        while (!confirm)
        {
            boolean validRace = false;
            while (!validRace) {
                System.out.println("Please pick a race from the following list:");
                for (int i = 0; i < races.length; i++) {
                    System.out.print(races[i] + ", ");
                }
                System.out.println();
                characterRace = scan.nextLine();
                for (int i = 0; i < races.length; i++) {
                    if (characterRace.equalsIgnoreCase(races[i])) {
                        racePos = i;
                        characterRace = races[i];
                        validRace = true;
                        break;
                    }
                }
                if(!validRace) {
                    System.out.println("Try again");
                }
            }
            System.out.println("Is " + characterRace + " correct? y/n");
            options = scan.nextLine();
            if(options.equalsIgnoreCase("y"))
            {
                confirm = true;
                break;
            }
        }
    }
    static void getClasss()
    {
        //Ask the user for their class and confirms your choice saving it to a variable.
        confirm = false;
        while (!confirm)
        {
            boolean validClass = false;
            while (!validClass)
            {
                System.out.println("Please pick a class from the following list:");
                for (int i = 0; i < classes.length; i++)
                {
                    System.out.print(classes[i] + ", ");
                }
                System.out.println();
                characterClass = scan.nextLine();
                for(int i = 0; i < classes.length; i++)
                {
                    if(characterClass.equalsIgnoreCase(classes[i]))
                    {
                        characterClass = classes[i];
                        validClass = true;
                        break;
                    }
                }
                if(!validClass)
                {
                    System.out.println("Try again");
                }
            }
            System.out.println("Is " + characterClass + " correct? y/n");
            options = scan.nextLine();
            if(options.equalsIgnoreCase("y"))
            {
                confirm = true;
                break;
            }
        }
    }
    static void createStats()
    {
        //Grid that holds all the stat modifiers for each race.
        int[][] statModifiers = {
                {0,0,2,0,1,0}, //Hill Dwarf
                {2,0,2,0,0,0}, //Mountain Dwarf
                {0,2,0,1,0,0}, //High Elf
                {0,2,0,0,1,0}, //Wood Elf
                {0,2,0,0,0,1}, //Drow Elf
                {0,2,0,0,0,1}, //Lightfoot Halfling
                {0,2,1,0,0,0}, //Stout Halfling
                {1,1,1,1,1,1}, //Human
                {2,0,0,0,0,1}, //Dragonborn
                {0,1,0,2,0,0}, //Forest Gnome
                {0,0,1,2,0,0}, //Rock Gnome
                {0,1,0,1,0,2}, //Half Elf
                {2,0,1,0,0,0}, //Half Orc
                {0,0,0,1,0,2}, //Tiefling
        };
        //generates the stats with a random function from 3-18 + race stat modifieres.
        STR = random(3,18) + statModifiers[racePos][0];
        DEX = random(3,18) + statModifiers[racePos][1];
        CON = random(3,18) + statModifiers[racePos][2];
        INT = random(3,18) + statModifiers[racePos][3];
        WIS = random(3,18) + statModifiers[racePos][4];
        CHA = random(3,18) + statModifiers[racePos][5];
        System.out.println("Generating character stat block:");
        System.out.println(" Name: " + characterName);
        System.out.println(" Race: " + characterRace);
        System.out.println(" Class: " + characterClass);
        System.out.println(" Strength:     " + STR);
        System.out.println(" Dexterity:    " + DEX);
        System.out.println(" Constitution: " + CON);
        System.out.println(" Intelligence: " + INT);
        System.out.println(" Wisdom:       " + WIS);
        System.out.println(" Charisma:     " + CHA);

    }
    static void fileOptions()
    {
        //Asks the user if they want to save delete or reroll their character then confirms their choice.
        confirm = false;
        while(!confirm)
        {
            System.out.println("Would you like to Save/Delete/Reroll");
            options = scan.nextLine();
            switch (options.toLowerCase())
            {
                //Starts the create file function.
                case "save":
                    createFile();
                    confirm = true;
                    break;
                case "delete":
                    //Asks you if you want to create a new character or close the program.
                    System.out.println("Are you sure? y/n");
                    options = scan.nextLine();
                    if(options.equalsIgnoreCase("y"))
                    {
                        System.out.println("Would you like to create a new character or close program? 1/2");
                        options = scan.nextLine();
                        if(options.equals("1"))
                        {
                            createNewCharacter = true;
                            confirm = true;
                            break;
                        }
                        if(options.equals("2"))
                        {
                            System.out.println("Thank you for using the DND character creator V2");
                            System.exit(0);
                        }
                    }
                    break;
                case "reroll":
                    //Reruns the createstats function then returns to this function.
                    System.out.println("Are you sure? y/n");
                    options = scan.nextLine();
                    if(options.equalsIgnoreCase("y"))
                    {
                        reroll = true;
                        confirm = true;
                    }
                    break;
                default:
                    break;
            }
        }
    }
    static void createFile()
    {
        //attepts to create a file called CharacterStats unless the file already exists then asks the user if they want to overite the file or add to it then confirms their choice.
        boolean newFile = false;
        try
        {
            File characters = new File("CharacterStats.txt");
            confirm = false;
            while(!confirm) {
                if (!characters.createNewFile() && !writeToFile) {
                    System.out.println("A characterStats file already exists.\nWould you like to overwrite it that file or add to it. 1/2");
                    options = scan.nextLine();
                    if (options.equalsIgnoreCase("1")) {
                        System.out.println("Are you sure? y/n");
                        options = scan.nextLine();
                        if (options.equalsIgnoreCase("y")) {
                            if(writeToFile)
                            {
                                newFile = false;
                            }
                            else
                            {
                                newFile = true;
                            }
                            confirm = true;
                            writeToFile = true;

                            new FileOutputStream("CharacterStats.txt").close();
                        }
                    }
                    if (options.equalsIgnoreCase("2")) {
                        writeToFile = true;
                        confirm = true;
                    }
                }
                else
                {
                    newFile = true;
                    writeToFile = false;
                    confirm = true;
                }
                if(writeToFile)
                {
                    break;
                }
            }
            FileWriter fw = new FileWriter(characters, true);
            if (newFile)
            {
                fw.write("Character Stats:\n\n");

            }
            else
            {
                fw.write("\n");
            }
            fw.write(" Name: " + characterName + "\n");
            fw.write(" Race: " + characterRace + "\n");
            fw.write(" Class: " + characterClass + "\n");
            fw.write(" Strength:     " + STR + "\n");
            fw.write(" Dexterity:    " + DEX + "\n");
            fw.write(" Constitution: " + CON + "\n");
            fw.write(" Intelligence: " + INT + "\n");
            fw.write(" Wisdom:       " + WIS + "\n");
            fw.write(" Charisma:     " + CHA + "\n");
            fw.close();
            System.out.println("Character Data Saved");
            System.out.println("Would you like to create another character or close program 1/2");
            options = scan.nextLine();
            if (options.equalsIgnoreCase("1"))
            {
                createNewCharacter = true;
            }
            if (options.equalsIgnoreCase("2"))
            {
                System.out.println("Thank you for using the DND character creator V2");
                System.exit(0);
            }
        }
        catch (IOException e)
        {
            System.out.println("There was an error when trying to write to file");
            System.exit(0);
        }
    }
    static int random(int min, int max)
    {
        //generates a random number between a minimum value and a maximum value.
        max = max - min + 1;
        return (int)(Math.random() * max + min);
    }
}
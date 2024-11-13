package se.david.robbergame.tools;

import se.david.robbergame.model.Burglar;
import se.david.robbergame.model.Entity;
import se.david.robbergame.model.Resident;

import java.util.Scanner;

public class Utils {

    // ANSI escape-koder för färgsättning
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String ORANGE = "\u001B[38;5;214m";
    public static final String CYAN = "\u001B[36m";

    public static final String BG_GOLD = "\u001B[43m"; // Gul bakgrund
    public static final String BG_BLUE = "\u001B[44m";
    public static final String BG_WHITE = "\u001B[47m";
    public static final String RESET = "\u001B[0m";

    private static final Scanner sc = new Scanner(System.in);


    public static void printTypeWriterEffect(String text, int baseDelay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);

            // Variera fördröjningen baserat på tecknet
            int delay = c == ' ' ? baseDelay * 2 : baseDelay;

            try {
                Thread.sleep(delay); // Paus mellan varje tecken
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    //PRINTERS

    public static void printMenu() {
        printTypeWriterEffect("\n\n~~~~~~THE ROBBER GAME~~~~~~\n\nEnter your name: ", 100);
    }

    public static void printStory() {
        printTypeWriterEffect(CYAN + "\n\nYou wake up in the dark...\nWhere are you???\nOh, you're in your bed...\nYou had a bad nightmare..\nLike someone was trying to rob you in the middle of the night...\n" +
                "You feel thirsty and decide to get up and grab something to drink from the kitchen...\n\n" + RESET, 25);
    }

    public static void printDirections() {
        System.out.println("Where do you want to go now?\n\n1) Go to office\n2) Go to hallway\n3) Go to kitchen\n4) Go to bedroom\n5) Quit ");
    }


    //SCANNER
    public static String inputScanner() {

        return sc.nextLine();
    }

    public static void closeScanner() {
        sc.close();
    }

    //FIGHTING

    // Metod för att välja weapon och starta en fight

    public static void fightOneRound(Resident resident, Burglar burglar) {

        System.out.println("Choose your weapon (Enter weapon name):\n");

        resident.getInventory().forEach((weapon, damage) ->
                System.out.println("- " + weapon + " (Damage: " + damage + ")")
        );

        System.out.print("--> ");
        String weaponChoice = Utils.inputScanner().toLowerCase();

        Integer weaponDamage = resident.getInventory().get(weaponChoice); // Hämta damage från valt weapon direkt från hashmap med KEY-value (weaponChoice)

        if (weaponDamage == null) {
            System.out.println(RED + "Error:\n- You don't have that weapon\nOR\n- Invalid input\n" + RESET);
            return; // Avbryt om vapnet inte finns
        }

        boolean fightRunning = true;
        while (fightRunning && resident.isConscious() && burglar.isConscious()) {

            if (resident.hasWeapon("frying pan") && resident.hasWeapon("pillow")){

                System.out.println("1) Continue\n2) Exit\n");
                System.out.print("-->");
            }
            else {

                System.out.println("1) Continue\n2) Exit (Search for a new weapon)\n");
                System.out.print("-->");
            }

            String actionChoice = Utils.inputScanner();

            switch (actionChoice) {

                case "1": // Fortsätt slåss
                    executeResidentAttack(resident, burglar, weaponDamage); // Använd det valda vapnet

                    if (burglar.isConscious()) {
                        executeBurglarAttack(burglar, resident); // Rånaren attackerar om den är i medvetande
                    } else if (!burglar.isConscious() || !resident.isConscious()) {

                        fightRunning = false;
                    }
                    break;

                case "2": // Pausa och leta efter nytt vapen
                    Utils.printTypeWriterEffect(CYAN + "You leave the fight to search for a weapon.\n\n" + RESET,25);
                    return; //Avbryter hela metoden

                default:
                    System.out.println(RED + "Invalid choice. Please enter 1 to continue or 2 to run.\n\n" + RESET);
            }
        }
    }

    public static void executeResidentAttack(Entity attacker, Entity defender, int attackDamage) {

        defender.takeHit(attackDamage);

        System.out.println(YELLOW + attacker.getRole() + RESET + " attacked " + RED + defender.getRole() + RESET + " (Damage: " + RED + attackDamage + RESET + ")\n");

        if (defender.isConscious()) {
            System.out.println(RED + defender.getRole() + "'s" + RESET + " health is: " + GREEN + defender.getHealth() + "\n" + RESET);
        } else {
            System.out.println(RED + defender.getRole() + " is unconscious\n" + RESET);
        }
    }

    public static void executeBurglarAttack(Entity attacker, Entity defender) {

        attacker.punch(defender);

        System.out.println(RED + attacker.getRole() + RESET + " attacked " + YELLOW + defender.getRole() + RESET + " (Damage: " + RED + attacker.getDamage() + RESET + ")\n");

        if (defender.isConscious()) {
            System.out.println(YELLOW + defender.getRole() + "'s" + RESET + " health is: " + GREEN + defender.getHealth() + "\n" + RESET);
        } else {
            System.out.println(RED + defender.getRole() + " is unconscious\n" + RESET);
        }
    }
}




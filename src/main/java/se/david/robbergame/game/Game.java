package se.david.robbergame.game;


import se.david.robbergame.model.Burglar;
import se.david.robbergame.model.Resident;
import se.david.robbergame.rooms.RoomManager;
import se.david.robbergame.tools.Utils;

import static se.david.robbergame.tools.Utils.*;

public class Game {

    public static boolean gameRunning = true;
    private final RoomManager roomManager = new RoomManager(); // Skapar upp instanser av alla rum med namn och beskrivning


    public void startGame() {


        Utils.printMenu(); //Skriver ut en meny och ber om spelarnamn.

        String userName = Utils.inputScanner(); //Returnerar sc.nextLine()
        Resident resident = new Resident(100, 5, userName); //Ärver av Entity-klassen (Health, Damage, Role)
        Burglar burglar = new Burglar(100, 20, "Burglar");

        Utils.printStory();


        while (gameRunning) {

            if (!resident.isConscious()) {

                Utils.printTypeWriterEffect(RED + "The burglar stole all your stuff!\n\nGAME OVER!\n\n" + RESET, 25);
                Utils.closeScanner();
                gameRunning = false;
                break;

            } else if (!burglar.isConscious()) {
                Utils.printTypeWriterEffect(BG_WHITE + BLACK + "You beat the " + RED + "BURGLAR" + BLACK + "!" + RESET +
                        CYAN + "\n\nHurry and call the cops from the office before he wakes up!\n\n" + RESET, 25);
            }


            roomManager.getRoom("Living Room").enterRoom(resident, burglar);

            System.out.print("--> ");
            String userInput = inputScanner();


            switch (userInput) {

                case "1" -> roomManager.getRoom("Office").enterRoom(resident, burglar);

                case "2" -> roomManager.getRoom("Hallway").enterRoom(resident, burglar);

                case "3" -> roomManager.getRoom("Kitchen").enterRoom(resident, burglar);

                case "4" -> roomManager.getRoom("Bedroom").enterRoom(resident, burglar);

                case "5" -> {
                    System.out.println("Quitting game...");
                    gameRunning = false;
                    Utils.closeScanner();
                }

                default -> System.out.println("Please enter a valid number between 1 and 5.");
            }
        }
    }
}




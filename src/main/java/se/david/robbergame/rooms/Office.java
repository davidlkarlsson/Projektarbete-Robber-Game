package se.david.robbergame.rooms;

import se.david.robbergame.model.Burglar;
import se.david.robbergame.model.Resident;
import se.david.robbergame.tools.Utils;

import static se.david.robbergame.game.Game.gameRunning;
import static se.david.robbergame.tools.Utils.*;

public class Office extends RoomAbstract {

    public Office(String roomName, String roomDescription) {
        super(roomName, roomDescription);
    }

    @Override
    public void enterRoom(Resident resident, Burglar burglar) {

        Utils.printTypeWriterEffect("Going to: " + YELLOW + roomName + "\n\n" + RESET, 100);

        // Kollar om roomdescription redan har skrivits ut.
        isRoomDescriptionDisplayed();

        boolean roomRunning = true;

        while (roomRunning && resident.isConscious()) {

            if (burglarInHouse && burglar.isConscious()) {

                System.out.println("What would you like to do?\n\n1) Call the cops \n2) Exit (Search for a weapon)\n");
                System.out.print("--> ");
                String choice = Utils.inputScanner();

                switch (choice) {

                    case "1" ->
                            Utils.printTypeWriterEffect(CYAN + "You cannot do that while the " + RED + "BURGLAR" + CYAN + " is still conscious!\n\n" + RESET,25);

                    case "2" -> {
                        Utils.printTypeWriterEffect(CYAN + "You run back to the livingroom.\nMaybe find something to defend yourself with?\n\n", 25);
                        roomRunning = false;
                    }

                    default -> System.out.println(RED + "Please enter a valid number (1 or 2)." + RESET);
                }
            } else if (!burglar.isConscious()) {

                System.out.println("What would you like to do?\n\n1) Call the cops \n2) Exit\n");
                System.out.print("--> ");
                String choice = Utils.inputScanner();

                switch (choice) {

                    case "1" -> {
                        Utils.printTypeWriterEffect(CYAN + "You walk up to the office desk and picks up the phone.\nThe cops should be here any minute now.\n\n" +
                                BG_GOLD + BLACK + "CONGRATULATONS, YOU FINISHED THE GAME!" + RESET, 25);
                        Utils.closeScanner();
                        roomRunning = false;
                        gameRunning = false;
                    }

                    case "2" -> {
                        Utils.printTypeWriterEffect(CYAN + "You You leave the office...\n\n", 25);
                        roomRunning = false;
                    }

                    default -> System.out.println(RED + "Please enter a valid number (1 or 2)." + RESET);
                }


            } else {
                Utils.printTypeWriterEffect(CYAN + "Everything seems fine in here.\nYou step out of the office.\n\n" + RESET, 25);
                roomRunning = false;
            }
        }
    }
}

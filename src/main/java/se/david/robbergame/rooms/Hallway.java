package se.david.robbergame.rooms;

import se.david.robbergame.model.Burglar;
import se.david.robbergame.model.Resident;
import se.david.robbergame.tools.Utils;

import static se.david.robbergame.tools.Utils.*;
import static se.david.robbergame.tools.Utils.RESET;

public class Hallway extends RoomAbstract {


    public Hallway(String roomName, String roomDescription) {
        super(roomName, roomDescription);
    }

    @Override
    public void enterRoom(Resident resident, Burglar burglar) {

        Utils.printTypeWriterEffect("Going to: " + BLUE + roomName + "\n\n" + RESET, 100);

        // Kollar om roomdescription redan har skrivits ut
        isRoomDescriptionDisplayed();

        boolean roomRunning = true;

        while (roomRunning && resident.isConscious() && burglar.isConscious()) {

            if (isBurglarInHouse()) {

                System.out.println("What would you like to do?\n\n1) Fight \n2) Run\n");
                System.out.print("--> ");
                String choice = Utils.inputScanner();

                switch (choice) {

                    case "1" -> Utils.fightOneRound(resident,burglar);

                    case "2" -> {

                        Utils.printTypeWriterEffect(CYAN + "You run back to the livingroom.\nMaybe find something to defend yourself with?\n\n" + RESET,25);
                        roomRunning = false;
                    }

                    default -> System.out.println(RED + "Please enter a valid number between (1 or 2)." + RESET);
                }

            } else {
                Utils.printTypeWriterEffect(CYAN + "You walk up to the door and unlocks it.\nThe door swings open and hits you in the head." +
                        "\nYou fall to the ground.\nA " + RED + "BURGLAR" + CYAN + " is in the house!\n\n" + RESET, 25);
                setBurglarInHouse(true);
            }
        }
    }
}

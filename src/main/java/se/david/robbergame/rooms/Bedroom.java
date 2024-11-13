package se.david.robbergame.rooms;

import se.david.robbergame.model.Burglar;
import se.david.robbergame.model.Resident;
import se.david.robbergame.tools.Utils;

import static se.david.robbergame.tools.Utils.*;

public class Bedroom extends RoomAbstract {

    public Bedroom(String roomName, String roomDescription) {
        super(roomName, roomDescription);
    }

    @Override
    public void enterRoom(Resident resident, Burglar burglar) {

        Utils.printTypeWriterEffect("Going to: " + RED + roomName + "\n\n" + RESET, 100);

        // Kollar om roomdescription redan har skrivits ut.
        isRoomDescriptionDisplayed();

        boolean roomRunning = true;

        while (roomRunning && resident.isConscious()) {

            if (isBurglarInHouse() && !resident.hasWeapon("pillow")) {

                Utils.printTypeWriterEffect(CYAN + "You run into the bedroom. Anything useful in here?\n\n" + RESET, 25);
                System.out.println("What would you like to do?\n\n1) Pick up pillow\n2) Exit bedroom\n");
                System.out.print("--> ");
                String choice = Utils.inputScanner();

                switch (choice) {

                    case "1" -> {
                        Utils.printTypeWriterEffect("You pick up a " + BG_WHITE + BLACK + "Pillow" + RESET + " and put it in your " + ORANGE + "INVENTORY" + RESET + ".\n\n", 25);
                        resident.addWeapon("pillow", 2);
                    }

                    case "2" -> {
                        Utils.printTypeWriterEffect(CYAN + "You run back to the livingroom.\nMaybe find something to defend yourself with?\n\n", 25);
                        roomRunning = false;
                    }

                    default -> System.out.println(RED + "Please enter a valid number (1 or 2)." + RESET);
                }
            }
            else if (isBurglarInHouse() && resident.hasWeapon("pillow")) {

                System.out.println("What would you like to do?\n\n1) Exit bedroom\n");
                System.out.print("--> ");
                String choice = Utils.inputScanner();

                switch (choice) {

                    case "1" -> {
                        Utils.printTypeWriterEffect(CYAN + "You rush out of the bedroom!\n\n" + RESET, 25);
                        roomRunning = false;
                    }

                    default -> System.out.println(RED + "Please enter a valid choice. (Exit bedroom)\n" + RESET);
                }
            }
            else {
                Utils.printTypeWriterEffect(CYAN + "Nothing special in here.\nYou decide to leave the bedroom.\n\n" + RESET, 25);
                roomRunning = false;
            }
        }
    }
}


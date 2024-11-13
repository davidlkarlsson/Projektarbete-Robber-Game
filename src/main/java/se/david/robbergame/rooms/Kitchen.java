package se.david.robbergame.rooms;

import se.david.robbergame.model.Burglar;
import se.david.robbergame.model.Resident;
import se.david.robbergame.tools.Utils;

import static se.david.robbergame.tools.Utils.*;
import static se.david.robbergame.tools.Utils.RESET;

public class Kitchen extends RoomAbstract {


    public Kitchen(String roomName, String roomDescription) {
        super(roomName, roomDescription);
    }

    @Override
    public void enterRoom(Resident resident, Burglar burglar) {

        Utils.printTypeWriterEffect("Going to: " + GREEN + roomName + "\n\n" + RESET, 100);

        // Kollar om roomdescription redan har skrivits ut.
        isRoomDescriptionDisplayed();

        boolean roomRunning = true;

        while (roomRunning && resident.isConscious()) {

            if (isBurglarInHouse() && !resident.hasWeapon("frying pan")) {
                Utils.printTypeWriterEffect(CYAN + "You rush into the kitchen to find something to defend yourself with!\n\n" + RESET, 25);

                System.out.println("What would you like to do?\n\n1) Pick up a frying pan\n2) Exit kitchen\n");
                System.out.print("--> ");
                String choice = Utils.inputScanner();

                switch (choice) {

                    case "1" -> {
                        Utils.printTypeWriterEffect("You pick up a " + BG_GOLD + BLACK + "Frying Pan" + RESET + " and put it in your " + ORANGE + "INVENTORY" + RESET + ".\n\n", 25);
                        resident.addWeapon("frying pan", 25);

                    }
                    case "2" -> roomRunning = false;

                    default -> System.out.println(RED + "Please enter a valid choice between 1 and 2." + RESET);
                }

            } else if (isBurglarInHouse() && resident.hasWeapon("frying pan")) {

                System.out.println("What would you like to do?\n\n1) Exit kitchen\n");
                System.out.print("--> ");
                String choice = Utils.inputScanner();

                switch (choice) {

                    case "1" -> {
                        Utils.printTypeWriterEffect(CYAN + "You rush out of the kitchen!\n\n" + RESET, 25);
                        roomRunning = false;
                    }

                    default -> System.out.println(RED + "Please enter a valid choice. (Exit kitchen)\n" + RESET);
                }


            } else if (!resident.hasWeapon("bottle")) {

                Utils.printTypeWriterEffect("What would you like to do?\n\n1) Grab a bottle of water\n2) Exit kitchen\n", 25);
                System.out.print("--> ");
                String choice = Utils.inputScanner();

                switch (choice) {

                    case "1" -> {
                        Utils.printTypeWriterEffect("You grab a " + BG_BLUE + BLACK + "BOTTLE OF WATER" + RESET + " and have a sip.\nYou put it in your " + ORANGE + "INVENTORY" + RESET + ".\n\n", 25);
                        resident.addWeapon("bottle", 10);
                    }
                    case "2" -> roomRunning = false;


                    default -> System.out.println(RED + "Please enter a valid choice between 1-3.\n" + RESET);
                }

            } else if (resident.hasWeapon("bottle")) {

                System.out.println("What would you like to do?\n\n1) Exit kitchen\n");
                System.out.print("--> ");
                String choice = Utils.inputScanner();

                switch (choice) {

                    case "1" -> roomRunning = false;

                    default -> System.out.println(RED + "Please enter a valid choice. (Exit kitchen)\n" + RESET);
                }
            }


        }

    }
}



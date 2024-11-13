package se.david.robbergame.rooms;

import se.david.robbergame.model.Burglar;
import se.david.robbergame.model.Resident;
import se.david.robbergame.tools.Utils;

import static se.david.robbergame.tools.Utils.*;

public class Livingroom extends RoomAbstract {

    public Livingroom(String roomName, String roomDescription) {

        super(roomName, roomDescription);
    }

    @Override
    public void enterRoom(Resident resident, Burglar burglar) {

        if (!resident.isConscious()) {
            return;
        }

        Utils.printTypeWriterEffect("Going to: " + PURPLE + roomName + "\n\n" + RESET, 100);

        // Kollar om roomdescription redan har skrivits ut
        isRoomDescriptionDisplayed();


        if (hearNoise) {
            Utils.printTypeWriterEffect(CYAN + "Suddenly you hear a weird noise from the front door...\nYou better check it out?\n\n" + RESET, 25);
            hearNoise = false;
        }

        Utils.printDirections();

    }
}





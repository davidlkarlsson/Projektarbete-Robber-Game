package se.david.robbergame.rooms;

import se.david.robbergame.model.Burglar;
import se.david.robbergame.model.Resident;
import se.david.robbergame.tools.Utils;

import static se.david.robbergame.tools.Utils.CYAN;
import static se.david.robbergame.tools.Utils.RESET;

public abstract class RoomAbstract {

    protected String roomName;
    protected String roomDescription;
    protected boolean descriptionDisplayed = false; // Flagga för att hålla reda på om beskrivningen har visats
    protected boolean hearNoise = true;
    protected static boolean burglarInHouse = false;


    public RoomAbstract(String roomName, String roomDescription) {
        this.roomName = roomName;
        this.roomDescription = roomDescription;
    }

    public void isRoomDescriptionDisplayed(){

        if (!descriptionDisplayed) {
            Utils.printTypeWriterEffect(CYAN + roomDescription + RESET, 25);
            descriptionDisplayed = true; // Sätter flaggan till true så att beskrivningen inte visas igen nästa gång man går in i rummet
        }
    }

    public abstract void enterRoom(Resident resident, Burglar burglar);


    public static void setBurglarInHouse(boolean isInHouse) {
        burglarInHouse = isInHouse;
    }

    // Kontrollera om burglar är i huset
    public static boolean isBurglarInHouse() {
        return burglarInHouse;
    }


}
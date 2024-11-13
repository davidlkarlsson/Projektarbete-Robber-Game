package se.david.robbergame.rooms;

import java.util.HashMap;
import java.util.Map;

public class RoomManager {

    private Map<String, RoomAbstract> rooms;

    public RoomManager() {

        rooms = new HashMap<>();

        rooms.put("Living Room", new Livingroom("Living Room", "You step into your cozy Livingroom, everything seems normal here.\n\n"));
        rooms.put("Office", new Office("Office", "A nice working place for a programmer.\n\n"));
        rooms.put("Hallway", new Hallway("Hallway", "A stair is leading down to the hallway and the front door.\n\n"));
        rooms.put("Kitchen", new Kitchen("Kitchen", "A nice and top modern kitchen.\n\n"));
        rooms.put("Bedroom", new Bedroom("Bedroom", "The best place in the house, your bed.\n\n"));
    }

    public RoomAbstract getRoom(String direction) {

        return rooms.get(direction);
    }

}

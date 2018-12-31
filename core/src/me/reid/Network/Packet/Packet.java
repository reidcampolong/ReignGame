package me.reid.Network.Packet;

import java.io.Serializable;
import java.util.HashMap;

public class Packet implements Serializable {

    protected PacketID id;
    protected byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public enum PacketID {

        LOGINREQUEST(0),
        LOGINSUCCESS(1),
        PLAYERLOGIN(2),
        PLAYERMOVE(3);

        private int id;

        PacketID(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static PacketID getPacket(int id) {
            return packetIDs.get(id);
        }

        private static HashMap<Integer, PacketID> packetIDs = new HashMap();
        static {
            for(PacketID id : PacketID.values()) {
                packetIDs.put(id.getId(), id);
            }
        }
    }
}
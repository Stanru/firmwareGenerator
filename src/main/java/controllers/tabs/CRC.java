package controllers.tabs;

public class CRC {
    static public byte getCRC8(byte[] data){
        byte crc = 0;
        for (byte datum : data) {
            crc += datum;
        }
        return (byte)-crc;
    }
}

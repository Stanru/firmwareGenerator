package controllers.tabs;

import java.io.File;

public enum Devises{
    KAMERTON_5(Kamerton5_1.class.getName(), "Камертон 5", 8, 1,4, "firmware/kamerton_5_1.hex"),

    KAMERTON_5_BUK(Kamerton5_2.class.getName(), "БУК", 4, 1,0, "firmware/kamerton_5_2/kamerton_5_2_buc.hex"),
    KAMERTON_5_BG205(Kamerton5_2.class.getName(), "БГ(205)", 5, 1,4, "firmware/kamerton_5_2/kamerton_5_2_bg_205.hex"),
    KAMERTON_5_BG405(Kamerton5_2.class.getName(), "БГ(405)", 5, 1,4, "firmware/kamerton_5_2/kamerton_5_2_bg_405.hex"),

    ZASLON_GNSL(Zaslon.class.getName(),"ГНСЛ", 7, 1,4, "firmware/zaslon/svaz.hex"),
    ZASLON_RSIL(Zaslon.class.getName(),"РСиЛ", 21, 1,0, "firmware/zaslon/rs.hex"),
    ZASLON_RSGL(Zaslon.class.getName(), "РСгЛ", 22, 1, 0, "firmware/zaslon/rs.hex"),
    ZASLON_VOLS(Zaslon.class.getName(), "УЗ ВОЛС", 6, 1, 4, "firmware/zaslon/svaz.hex"),
    ZASLON_SVAZ(Zaslon.class.getName(), "Г СВАЗ",3,1, 4, "firmware/zaslon/svaz.hex"),
    ZASLON_UKVZ(Zaslon.class.getName(), "МС УКВЗ",10, 1,4, "firmware/zaslon/mc.hex"),
    ZASLON_UKAZ(Zaslon.class.getName(), "МС УКАЗ",11,1,4, "firmware/zaslon/mc.hex"),
    ZASLON_SAZ(Zaslon.class.getName(), "Г ЗЭС",15,2,2, "firmware/zaslon/saz.hex"),
    ZASLON_EMP(Zaslon.class.getName(), "Г ШЭП",16,2,2, "firmware/zaslon/saz.hex"),
    ZASLON_PDU(Zaslon.class.getName(), "ПДУ",2,0,0, "firmwarezaslon/brat.hex"),
    ZASLON_IT(Zaslon.class.getName(), "ИТ",-1,0,0, "firmware/zaslon/it_zaslon_v2.hex"),
    ZASLON_WDT(Zaslon.class.getName(), "СторожТаймер",0,-1,0, "firmware/WatchDogsSTM8.hex"),

    ROSYNKA_EMP(Rosyanka.class.getName(), "Сфера-РП", 2,5, 2, "firmware/rosyanka/rosyanka.hex"),
    ROSYNKA_DEMP(Rosyanka.class.getName(), "Сенсор-РП", 2,6, 2, "firmware/rosyanka/rosyanka.hex"),
    ROSYNKA_SVAZ(Rosyanka.class.getName(), "Мелодия-РВ", 1,7, 4, "firmware/rosyanka/rosyanka.hex"),
    ROSYNKA_UKVZ(Rosyanka.class.getName(), "Сенсор-РВ", 1,8, 4, "firmware/rosyanka/rosyanka.hex"),
    ROSYNKA_UKAZ(Rosyanka.class.getName(), "Сенсор-РА",1,10,4, "firmware/rosyanka/rosyanka.hex"),
    ROSYNKA_SAZ(Rosyanka.class.getName(), "Сфера-Р",2,11,2, "firmware/rosyanka/rosyanka.hex"),
    ROSYNKA_DSAZ(Rosyanka.class.getName(), "Сенсор-Р",2,12,2, "firmware/rosyanka/rosyanka.hex"),
    ROSYNKA_BSS(Rosyanka.class.getName(), "Сигнал-Р",0,15,20, "firmware/rosyanka/rosyanka.hex"),
    ROSYNKA_DSS(Rosyanka.class.getName(), "Сенсор-РБ",0,16,20, "firmware/rosyanka/rosyanka.hex"),
    ROSYNKA_MRT(Rosyanka.class.getName(), "Милодия-РТ",1,17,1, "firmware/rosyanka/rosyanka.hex"),
    ROSYNKA_IT(Rosyanka.class.getName(), "Инф-Терминал",0,-1,0, "firmware/rosyanka/it_rosyanka.hex"),
    ROSYNKA_WDT(Rosyanka.class.getName(), "СторожТаймер",0,-1,0, "firmware/WatchDogsSTM8.hex");

    private final String nameInterface;
    private final String name;
    private final int typeDev;
    private final int typeGroup;
    private final int countChannels;
    private final File firmwareFile;

    Devises(String nameInterface, String name, int typeDev, int typeGrup, int countChannels, String pathToTheFirmwareFile) {
        this.nameInterface = nameInterface;
        this.name = name;
        this.typeDev = typeDev;
        this.typeGroup = typeGrup;
        this.countChannels = countChannels;
        this.firmwareFile = new File(pathToTheFirmwareFile);
    }

    public String getNameInterface() {
        return nameInterface;
    }

    public int getTypeDev() {
        return typeDev;
    }

    public int getTypeGroup() {
        return typeGroup;
    }

    public String getName() {
        return name;
    }

    public int getCountChannels() {
        return countChannels;
    }

    public File getFirmwareFile() { return firmwareFile; }
}

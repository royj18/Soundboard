package space.itsjosh.soundboard;

/**
 * All data that relates to the sounds info
 */
class SoundObject {

    private String itemName;
    private Integer itemID;

    SoundObject(String itemName, Integer itemID){

        this.itemName = itemName;
        this.itemID = itemID;
    }

    String getItemName(){

        return itemName;
    }

    Integer getItemID(){

        return itemID;
    }
}
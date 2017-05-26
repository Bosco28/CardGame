package Model.Card;

/**
 * Created by boscp on 2017-05-26.
 */
public enum EquipmentType {
    HORSE("HORSE"), WEAPON("WEAPON"), ARMOR("ARMOR");

    private String type;

    EquipmentType(String type) {this.type = type;}

    public String getType() {return type;}
}

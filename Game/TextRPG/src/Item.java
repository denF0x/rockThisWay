/**
 * Created by Денис on 29.01.2017.
 */
public class Item {
    private String name;
    public static enum ItemType {Consumables, InfinityConsumables, Quests, DefaultConsumables, Armor, Weapon, ForShop};
    private ItemType type;

    public ItemType getType(){
        return type;
    }

    public String getName()
    {
        return name;
    }

    public Item(String _name, ItemType _type)
    {
        name = _name;
        type = _type;
    }
}

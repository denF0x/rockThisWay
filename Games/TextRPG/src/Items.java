package Games.TextRPG.src;

/**
 * Created by Денис on 29.01.2017.
 */
public class Items {
    private String name;
    public static enum ItemType {Consumables, InfinityConsumables, Quests, DefaultConsumables, Armor, Weapon, ForShop};
    private ItemType type;
    private int price;

    public ItemType getType(){
        return type;
    }

    public String getName()
    {
        return name;
    }
    public int getPrice(){
        return price;
    }

    public Items(String name, ItemType type, int price)
    {
        this.name = name;
        this.type = type;
        this.price = price;
    }
    //здесь пока будут все игровые предметы. Не забывай неймить их в соответствующих методах
    //тут Consumables
    public final static Items smallElicsirOfHealth = new Items("Бутылка здоровья", ItemType.Consumables, 100);
    public final static Items bigElicsirOfHealth = new Items("Большой эликсир здоровья", ItemType.Consumables,200);
    public final static Items rawMeatOfDear = new Items("Сырое мясо убитого оленя",ItemType.Consumables, 50);
    public final static Items friedMeatOfDear = new Items("Жареное мясо убитого оленя",ItemType.Consumables,100);

    //InfinityConsumables
    public final static Items stoneForBlade = new Items("Точильный камень", ItemType.InfinityConsumables,50);
    public final static Items defaultItemForKnight = new Items("Камень рыцаря", ItemType.InfinityConsumables,200);
    public final static Items defaultItemForBarbarian = new Items("Камень варвара", ItemType.InfinityConsumables,200);
    public final static Items defaultItemForDwarf = new Items("Камень гнома", ItemType.InfinityConsumables,200);

    //Quests
    public final static Items mapForAmazonianIsland = new Items("Карта, ведущая к острову амазонок", ItemType.Quests,5);

    //DefaultConsumables
    public final static Items giantBallsOfRabbit = new Items("Гигантские шары кролика (дарует удачу)", ItemType.DefaultConsumables,500);

    //Armor
    public final static Items fireBra = new Items("Огнеупорный лифчик", ItemType.Armor,200);

    //Weapon
    public final static Items bigAndBlack = new Items("Черный большой елдак", ItemType.Weapon,300);

    //тут ForShop
    public final static Items skinOfDear = new Items("Шкура убитого оленя", ItemType.ForShop,150);


}

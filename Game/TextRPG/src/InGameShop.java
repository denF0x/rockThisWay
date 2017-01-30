/**
 * Created by Денис on 30.01.2017.
 */
public class InGameShop {

    public final int ITEMS_COUNT = 6;
    private Item[] itm = new Item[ITEMS_COUNT];
    private int[] itmCost = new int[ITEMS_COUNT];
    private int goldAmount;

    public InGameShop(){
        goldAmount = 2000;
        itm[0] = new Item("Бутылка здоровья", Item.ItemType.Consumables); //метод useItem
        itmCost[0] = 100;
        itm[1] = new Item("Огнеупорный лифчик", Item.ItemType.Armor);//нужно создать возможность надевать и снимать. метод
        itmCost[1] = 150;
        itm[2] = new Item("Черный большой елдак", Item.ItemType.Weapon); //нужно создать возможность надевать и снимать. метод
        itmCost[2] = 300;
        itm[3] = new Item("Гигантские шары кролика (дарует удачу)", Item.ItemType.DefaultConsumables); //прописать постоянное применение
        itmCost[3] = 500;
        itm[4] = new Item("Карта, ведущая к острову амазонок", Item.ItemType.Quests); //нужен для старта квеста
        itmCost[4] = 5;
        itm[5] = new Item("Точильный камень", Item.ItemType.InfinityConsumables);//метод useItem
        itmCost[5] = 50;
    }

    public void showItems(){
        System.out.println("Ассортимент:");
        for(int i = 0; i < ITEMS_COUNT; i++){
            System.out.println((i + 1) + ". " + itm[i].getName() + " " + itmCost[i]);
        }
    }

    public void buyByHero(int index, Hero h){
        if (h.myInventory.isCoinsEnough(itmCost[index]))
        {
            goldAmount += itmCost[index];
            h.myInventory.spendSomeCoins(itmCost[index]);
            h.myInventory.addToInventory(itm[index]);
            System.out.println("Герой приобрел " + itm[index].getName());
        } else {
            System.out.println("Не хватает золота :(");
        }
    }
}

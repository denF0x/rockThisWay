package Games.TextRPG.src;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Денис on 30.01.2017.
 */
public class InGameShop {

    public static HashMap<Integer, Items> itm = new HashMap<>();
    private static int goldAmount;

    public InGameShop() {


        itm.put(1, Items.smallElicsirOfHealth);
        itm.put(2, Items.bigElicsirOfHealth);
        itm.put(3, Items.fireBra);
        itm.put(4, Items.giantBallsOfRabbit);
        itm.put(5, Items.stoneForBlade);
        itm.put(6, Items.bigAndBlack);
        itm.put(7, Items.mapForAmazonianIsland);
        goldAmount = 2000;
    }

    //работает!
    public static void shopActionsByHero(Hero hero) {
        while (true) {
            System.out.println("Ассортимент:");
            for (Map.Entry<Integer, Items> pair : itm.entrySet()) {
                Integer num = pair.getKey();
                Items item = pair.getValue();
                System.out.println(num + ". " + item.getName() + " Цена: " + item.getPrice());
            }
            int numOfBuyingItem = Utils.getAction(0, itm.size(), "\n\nВыберите какой товар вы желаете приобрести: \n0.Выход из магазина");
            Items itemByKey = itm.get(numOfBuyingItem);
            if (numOfBuyingItem == 0) {
                return;
            } else if (hero.myInventory.isCoinsEnough(itemByKey.getPrice())) {
                goldAmount += itemByKey.getPrice();
                hero.myInventory.spendSomeCoins(itemByKey.getPrice());
                hero.myInventory.addToInventory(itemByKey);
                System.out.println("Герой приобрел " + itemByKey.getName());
            } else if (hero.myInventory.isCoinsEnough(itemByKey.getPrice()) == false) {
                System.out.println("Не хватает золота :(");
            }
        }
    }
}



/*
    public final int ITEMS_COUNT = 7;
    private Items[] itm = new Items[ITEMS_COUNT];
    private int[] itmCost = new int[ITEMS_COUNT];
    private int goldAmount;

    public InGameShop(){
        goldAmount = 2000;
        itm[0] = Items.smallElicsirOfHealth; //метод useItem
        itmCost[0] = 100;
        itm[1] = Items.fireBra;//нужно создать возможность надевать и снимать. метод
        itmCost[1] = 150;
        itm[2] = Items.bigAndBlack; //нужно создать возможность надевать и снимать. метод
        itmCost[2] = 300;
        itm[3] = Items.giantBallsOfRabbit; //прописать постоянное применение
        itmCost[3] = 500;
        itm[4] = Items.mapForAmazonianIsland; //нужен для старта квеста
        itmCost[4] = 5;
        itm[5] = Items.stoneForBlade;//метод useItem
        itmCost[5] = 50;
        itm[6] = Items.bigElicsirOfHealth;
        itmCost[6] = 200;
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
    }*/
import java.util.ArrayList;

/**
 * Created by Денис on 29.01.2017.
 */
public class Inventory {
    private int gold;
    ArrayList<Item> inv; //содержимое инвенторя

    public Inventory(){
        gold = 0;
        inv = new ArrayList<>();

    }
    public void spendSomeCoins(int amount){
        gold -= amount;
    }

    public boolean isCoinsEnough(int amount){
        if (gold >= amount) return true;
        return false;
    }

    public void addSomeCoins (int amount){
        gold += amount;
    }

    public void addSomeCoinsAfterWin (int amount){
        gold += amount;
        System.out.println("Персонаж получил " + amount + " золота. Всего " + gold + " золота");
    }

    public void addToInventory(Item _newItem){
        inv.add(_newItem);
    }


    public void addToInventoryAfterWin(Item _newItem){
        inv.add(_newItem);
        System.out.println("Персонаж получил " + _newItem.getName());
    }
    public void deleteFromInventory(Item deletableItem){
        inv.remove(deletableItem);
    }

    public void showAllItems()
    {
        System.out.println("Инвентарь: ");
        System.out.println("0. Закончить осмотр");
        if (inv.size() > 0)
        {
            for (int i = 0; i < inv.size(); i++) {
                System.out.println((i+1) + ". " + inv.get(i).getName());
            }
        } else {
            System.out.println("Инвентарь пуст");
        }
        System.out.println("Золото: " + gold);
    }

    public String useItem(int _itemID)
    {
        if (_itemID == 0)
            return "";
        String a = inv.get(_itemID - 1).getName();
        if (inv.get(_itemID - 1).getType() == Item.ItemType.Consumables)
        inv.remove(_itemID - 1);
        return a;
    }

    public int getSize(){
        return inv.size();
    }

    public void transferAllItemsToAnotherInventory(Inventory invOfPersonWhoGetThisItem){
        for (int i = 0; i < inv.size(); i++){
            invOfPersonWhoGetThisItem.addToInventoryAfterWin(inv.get(i));
            invOfPersonWhoGetThisItem.addSomeCoinsAfterWin(gold);
        }
    }
    public void transferAllItemsToAnotherInventoryWithTwentyPercentChance(Inventory invOfPersonWhoGetThisItem){
        //for (int i = 0; i < inv.size(); i ++){
            invOfPersonWhoGetThisItem.addSomeCoinsAfterWin(gold);
            if(Utils.rand.nextInt(100) < 20){
                invOfPersonWhoGetThisItem.addToInventoryAfterWin(inv.get(Utils.rand.nextInt(inv.size())));
               // invOfPersonWhoGetThisItem.addToInventoryAfterWin(inv.get(i));
            }
        }

}

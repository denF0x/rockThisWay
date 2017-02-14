package Game.TextRPG.src;

import Game.TextRPG.src.Enemies.*;

/**
 * Created by Денис on 14.02.2017.
 */
public class BattleClass {

    private int currentRound;
    private int inpInt;

    public BattleClass(Hero hero, Monster monster){
        currentRound = 1;
        System.out.println("Бой между игроком " + hero.getName() + " и монстром " + monster.getName() + " начался!");
        do {
            //начало раунда
            System.out.println("Текущий раунд: " + currentRound);
            hero.showInfo();
            monster.showInfo();
            //ход героя
            System.out.println("Ход игрока");
            hero.makeNewRound();
            inpInt = Utils.getAction(0, 3, "\n\n1. Атака\n2. Защита\n3. Открыть инвентарь\n0. Выйти из игры");
            //пропуск двух строк
            System.out.println("\n\n");
            //1.Атака
            if (inpInt == 1) {
                monster.getDamage(hero.makeAttack()); //монстр получает урон
                if (!monster.isAlive()) //жив ли монстр проверяем, ибо бесит меня, что он атакует после смерти
                {
                    System.out.println(monster.getName() + " погиб");
                    hero.expGain(monster.getHpMax() * 2); //экспы за монстра дают в размере двух его макс хп
                    hero.addKillCounter();
                    monster.myInventory.transferAllItemsToAnotherInventoryWithTwentyPercentChance(hero.myInventory);
                    //currentMonster = (Monster)monsterPattern[Utils.rand.nextInt(3)].clone(); //сложносоставленная херня, которая бесконечно копирует монстров из патерна рандомным образом
                    break;
                    //System.out.println("На поле боя выходит " + currentMonster.getName()); //сообщение о выходе нового монстра
                }
            }
            //2. Защита
            if (inpInt == 2) {
                hero.setBlockStance();
            }
            if (inpInt == 3) {
                hero.myInventory.showAllItems();
                int invInput = Utils.getAction(0, hero.myInventory.getSize(), "\n\nВыберите предмет для использования");
                String usedItem = hero.myInventory.useItem(invInput);
                if (usedItem != "") {
                    System.out.println(hero.getName() + " использовал " + usedItem);
                    hero.useItem(usedItem);
                } else {
                    System.out.println(hero.getName() + " просто закрыл сумку");
                }
            }
            //0.Выйти из игры
            if (inpInt == 0) break;
            //ход монстра
            monster.makeNewRound();
            if (Utils.rand.nextInt(100) < 80) {
                hero.getDamage(monster.makeAttack());
            } else {
                monster.setBlockStance();
            }
            //конец текущего раунда
            currentRound++;

        } while (true);
        //сообщение при победе или поражении
        if (monster.isAlive() && hero.isAlive())
            System.out.println(hero.getName() + " сбежал с поля боя");
        if (!hero.isAlive()) System.out.println("Победил " + monster.getName());
        if (!monster.isAlive()) System.out.println("Победил " + hero.getName());
    }

    public BattleClass(Hero hero, Enemy monster){
        currentRound = 1;
        System.out.println("Бой между игроком " + hero.getName() + " и монстром " + monster.getName() + " начался!");
        do {
            //начало раунда
            System.out.println("Текущий раунд: " + currentRound);
            hero.showInfo();
            monster.showInfo();
            //ход героя
            System.out.println("Ход игрока");
            hero.makeNewRound();
            inpInt = Utils.getAction(0, 3, "\n\n1. Атака\n2. Защита\n3. Открыть инвентарь\n0. Выйти из игры");
            //пропуск двух строк
            System.out.println("\n\n");
            //1.Атака
            if (inpInt == 1) {
                monster.getDamage(hero.makeAttack()); //монстр получает урон
                if (!monster.isAlive()) //жив ли монстр проверяем, ибо бесит меня, что он атакует после смерти
                {
                    System.out.println(monster.getName() + " погиб");
                    hero.expGain(monster.getHpMax() * 2); //экспы за монстра дают в размере двух его макс хп
                    hero.addKillCounter();
                    monster.myInventory.transferAllItemsToAnotherInventory(hero.myInventory);
                    //currentMonster = (Monster)monsterPattern[Utils.rand.nextInt(3)].clone(); //сложносоставленная херня, которая бесконечно копирует монстров из патерна рандомным образом
                    break;
                    //System.out.println("На поле боя выходит " + currentMonster.getName()); //сообщение о выходе нового монстра
                }
            }
            //2. Защита
            if (inpInt == 2) {
                hero.setBlockStance();
            }
            if (inpInt == 3) {
                hero.myInventory.showAllItems();
                int invInput = Utils.getAction(0, hero.myInventory.getSize(), "\n\nВыберите предмет для использования");
                String usedItem = hero.myInventory.useItem(invInput);
                if (usedItem != "") {
                    System.out.println(hero.getName() + " использовал " + usedItem);
                    hero.useItem(usedItem);
                } else {
                    System.out.println(hero.getName() + " просто закрыл сумку");
                }
            }
            //0.Выйти из игры
            if (inpInt == 0) break;
            //ход монстра
            monster.makeNewRound();
            if (Utils.rand.nextInt(100) < 80) {
                hero.getDamage(monster.makeAttack());
            } else {
                monster.setBlockStance();
            }
            //конец текущего раунда
            currentRound++;

        } while (true);
        //сообщение при победе или поражении
        if (monster.isAlive() && hero.isAlive())
            System.out.println(hero.getName() + " сбежал с поля боя");
        if (!hero.isAlive()) System.out.println("Победил " + monster.getName());
        if (!monster.isAlive()) System.out.println("Победил " + hero.getName());
    }

}



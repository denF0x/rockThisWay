package Game.TextRPG.src;

import Game.TextRPG.src.Enemies.*;

/**
 * Created by Денис on 14.02.2017.
 */
public class BattleClass {

    private Hero hero;
    private Enemy enemy;
    private int currentRound;
    private int currentAction;
    private boolean heroInBattle = true;
    private int numOfOptions = 4;

    private int atackOption = 1;
    private int defenseOption = 2;
    private int inventoryOption = 3;
    private int escapeOption = 4;
    private int exitFromGame = 0;

    public BattleClass(Hero hero, Enemy enemy) {
        this.hero = hero;
        this.enemy = enemy;
        doBattle();
    }
       void doBattle(){
        currentRound = 1;
        System.out.println("Бой между игроком " + hero.getName() + " и монстром " + enemy.getName() + " начался!");
        do {
            //начало раунда
            System.out.println("Текущий раунд: " + currentRound);
            hero.showInfo();
            enemy.showInfo();
            //ход героя
            System.out.println("Ход игрока");
            hero.makeNewRound();
            if (numOfOptions == 4){
            currentAction = Utils.getAction(0, numOfOptions, "\n\n1. Атака\n2. Защита\n3. Открыть инвентарь\n4. Попытаться сбежать\n0. Выйти из игры");
            } else if (numOfOptions == 3){
                currentAction = Utils.getAction(0, numOfOptions, "\n\n1. Атака\n2. Защита\n3. Открыть инвентарь\n0. Выйти из игры");
            }
            //пропуск двух строк
            System.out.println("\n\n");
            //1.Атака
            if (currentAction == atackOption) {
                enemy.getDamage(hero.makeAttack()); //монстр получает урон
                if (!enemy.isAlive()) //жив ли монстр проверяем, ибо бесит меня, что он атакует после смерти
                {
                    System.out.println(enemy.getName() + " погиб");
                    hero.expGain(enemy.getHpMax() * 2); //экспы за монстра дают в размере двух его макс хп
                    hero.addKillCounter();
                    enemy.myInventory.transferAllItemsToAnotherInventory(hero.myInventory);
                    //currentMonster = (Monster)monsterPattern[Utils.rand.nextInt(3)].clone(); //сложносоставленная херня, которая бесконечно копирует монстров из патерна рандомным образом
                    break;
                    //System.out.println("На поле боя выходит " + currentMonster.getName()); //сообщение о выходе нового монстра
                }
            }
            //2. Защита
            if (currentAction == defenseOption) {
                hero.setBlockStance();
            }
            if (currentAction == inventoryOption) {
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
            if (currentAction == escapeOption){
                System.out.println(hero.getName() + " решает сбежать с поля боя...");
                numOfOptions = 3;
                if (Utils.rand.nextInt(100) < 50){
                    heroInBattle = false;
                    System.out.println("... и успешно смывается");
                    break;
                } else {
                    System.out.println("... но не успешно и бой продолжается");
                }
            }
            //0.Выйти из игры
            if (currentAction == exitFromGame){ break;}
            //ход монстра
            enemy.makeNewRound();
            if (Utils.rand.nextInt(100) < 80) {
                hero.getDamage(enemy.makeAttack());
            } else {
                enemy.setBlockStance();
            }
            //конец текущего раунда
            currentRound++;

        } while (true);
        //сообщение при победе или поражении
        if (enemy.isAlive() && hero.isAlive())
            System.out.println(hero.getName() + " сбежал с поля боя");
        if (!hero.isAlive()) System.out.println("Победил " + enemy.getName());
        if (!enemy.isAlive()) System.out.println("Победил " + hero.getName());
        if (heroInBattle == false){
            System.out.println(enemy.getName() + " пытается догнать героя, но ему это не удается");
        }
    }

}



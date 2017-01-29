import java.util.Random;
import java.util.Scanner;

/**
 * Created by Денис on 26.01.2017.
 */
public class GameClass {
    public static Random rand = new Random();
    //массивы героев и врагов
    private Hero[] heroPattern = new Hero[3];
    private Monster[] monsterPattern = new Monster[3];
    //параметры основного цикла
    private Hero mainHero;
    private Monster currentMonster;
    private int currentRound;
    private Scanner sc = new Scanner(System.in);
    //инициализация игры
    public GameClass(){
        initGame();
    }

    //основной игровой цикл
    public void mainGameLoop(){
        int inpInt = 0;
        System.out.println("Игра началась!");
        //выбор героя
        String s = "Выберите героя:\n";
        for (int i = 0; i < 3; i++){
          s +=  (i+1) + ". " + heroPattern[i].getFullName() + "\n";
        }
        inpInt = getAction(1,3, s);
        mainHero = (Hero)heroPattern[inpInt-1].clone();
        System.out.println("Вы выбрали " + mainHero.getFullName());
        //сюжетная вставка
        System.out.println(mainHero.getName() + " отправляется в свой первый эпический квест и натыкается на орду тварей,\nкоторые берут его в плен и заставляют сражаться на арене ради увеселения публики");
        //выход первого врага
        currentMonster = (Monster)monsterPattern[0].clone();
        System.out.println("Первым на арену выходит " + currentMonster.getFullName());

        //вся магия здесь
        do{
            //начало раунда
            System.out.println("Текущий раунд: " + currentRound);
            mainHero.ShowInfo();
            currentMonster.ShowInfo();
            //ход героя
            System.out.println("Ход игрока");
            mainHero.makeNewRound();
            inpInt = getAction(0,3, "1. Атака\n2. Защита\n3. Открыть инвентарь\n0. Выйти из игры");
            //пропуск двух строк
            System.out.println("\n\n");
            //1.Атака
            if(inpInt == 1){
                currentMonster.getDamage(mainHero.makeAttack()); //монстр получает урон
                if(!currentMonster.isAlive()) //жив ли монстр проверяем, ибо бесит меня, что он атакует после смерти
                {
                    System.out.println(currentMonster.getName() + " погиб");
                    mainHero.expGain(currentMonster.getHpMax() * 2); //экспы за монстра дают в размере двух его макс хп
                    mainHero.addKillCounter();
                    currentMonster = (Monster)monsterPattern[rand.nextInt(3)].clone(); //сложносоставленная херня, которая бесконечно копирует монстров из патерна рандомным образом
                    System.out.println("На поле боя выходит " + currentMonster.getName()); //сообщение о выходе нового монстра
                }
            }
            //2. Защита
            if (inpInt == 2){
                mainHero.setBlockStance();
            }
            if (inpInt == 3){
                mainHero.myInventory.showAllItems();
                int invInput = getAction(0, mainHero.myInventory.getSie(), "Выберите предмет для использования");
                String usedItem = mainHero.myInventory.useItem(invInput);
                if (usedItem != ""){
                    System.out.println(mainHero.getName() + " использовал " + usedItem);
                    mainHero.useItem(usedItem);
                } else {
                    System.out.println(mainHero.getName() + " просто закрыл сумку");
                }
            }
            //0.Выйти из игры
            if (inpInt == 0 ) break;
            //ход монстра
            currentMonster.makeNewRound();
            if (rand.nextInt(100) < 80) {
                mainHero.getDamage(currentMonster.makeAttack());
            }else {
                currentMonster.setBlockStance();
            }
            //конец текущего раунда
            currentRound ++;

        } while(true);
        //сообщение при победе или поражении
        if (currentMonster.isAlive() && mainHero.isAlive()) System.out.println(mainHero.getName() + " сбежал с поля боя");
        if(!mainHero.isAlive()) System.out.println("Победил " + currentMonster.getName());
        if(!currentMonster.isAlive()) System.out.println("Победил " + mainHero.getName());
        //финал)
        System.out.println("Игра завершена");
    }
        //вся инфа по персонажам пока здесь
    private void initGame() {
        heroPattern[0] = new Hero("Knight", "Lancelot", 16, 10, 20);
        heroPattern[1] = new Hero("Barbarian", "Konan", 20, 5, 20);
        heroPattern[2] = new Hero("Dwarf", "Gimli", 18, 7, 20);

        monsterPattern[0] = new Monster("Humanoid", "Goblin", 4, 4, 6);
        monsterPattern[1] = new Monster("Humanoid", "Orc", 12, 6, 10);
        monsterPattern[2] = new Monster("Humanoid", "Troll", 16, 12, 12);

        currentRound = 1;
    }

    //метод для адекватной работы с выбором вариантов
    public int getAction(int _min, int _max, String _str)
    {
        int x = 0;
        do
        {
          if(_str != "")  System.out.println(_str);
            x = sc.nextInt();
        } while ( x < _min || x > _max);

        return x;
    }
}

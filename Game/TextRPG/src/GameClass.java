import javax.xml.bind.SchemaOutputResolver;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Денис on 26.01.2017.
 */
public class GameClass {
    public static Random rand = new Random();

    private Hero[] heroPattern = new Hero[3];
    private Monster[] monsterPattern = new Monster[3];

    private Hero mainHero;
    private Monster currentMonster;
    private int currentRound;
    private int monsterID = 0;

    public GameClass(){
        initGame();
    }

    public void mainGameLoop(){
        Scanner sc = new Scanner(System.in);
        int inpInt = 0;
        System.out.println("Игра началась!");

        System.out.println("Выберите героя:");
        for (int i = 0; i < 3; i++){
            System.out.println((i+1) + ". " + heroPattern[i].getFullName());
        }
        inpInt = sc.nextInt();
        mainHero = (Hero)heroPattern[inpInt-1].clone();
        System.out.println("Вы выбрали " + mainHero.getFullName());
        currentMonster = (Monster)monsterPattern[monsterID].clone();

        do{
            System.out.println("Текущий раунд: " + currentRound);
            mainHero.ShowInfo();
            currentMonster.ShowInfo();
            System.out.println("Ход игрока");
            mainHero.makeNewRound();
            System.out.println("1. Атака\n2. Защита\n3. Пропустить ход\n9. Выйти из игры");
            inpInt = sc.nextInt();
            System.out.println("\n\n");
            if(inpInt == 1){
                currentMonster.getDamage(mainHero.makeAttack());
            }
            if (inpInt == 2){
                mainHero.setBlockStance();
            }
            if (inpInt == 9 ) break;
            currentMonster.makeNewRound();
            mainHero.getDamage(currentMonster.makeAttack());
            currentRound ++;
            if(!currentMonster.isAlive){
                System.out.println(currentMonster.getName() + " погиб");
                mainHero.expGain(currentMonster.getHpMax() * 2);
                monsterID++;
                if(monsterID < monsterPattern.length) {
                    currentMonster = (Monster) monsterPattern[monsterID].clone();
                    System.out.println("На поле боя выходит: " + currentMonster.getName());
                } else {
                    break;
                }
            }

            if(!mainHero.isAlive){
                break;
            }

        } while(true);
        if(!mainHero.isAlive) System.out.println("Победил " + currentMonster.getName());
        if(!currentMonster.isAlive) System.out.println("Победил " + mainHero.getName());

        System.out.println("Игра завершена");
    }

    private void initGame() {
        heroPattern[0] = new Hero("Knight", "Lancelot", 500, 40, 10);
        heroPattern[1] = new Hero("Barbarian", "Konan", 1000, 60, 0);
        heroPattern[2] = new Hero("Dwarf", "Gimli", 500, 50, 20);

        monsterPattern[0] = new Monster("Humanoid", "Goblin", 250, 30, 5);
        monsterPattern[1] = new Monster("Humanoid", "Orc", 380, 40, 10);
        monsterPattern[2] = new Monster("Humanoid", "Troll", 490, 50, 20);

        currentRound = 1;
    }

}

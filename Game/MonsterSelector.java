package Game;
import java.util.Random;

public class MonsterSelector {
   private static int randomID1, randomID2, randomID3;
    public static Monster[] easyGetEnemies(int stageID) {
        /*
          정해진 대로 적을 생성해주는 클래스.
          스테이지 id별로 적 배열을 생성해 리턴한다.
          쉬운 적 (전투 3회 이후는 안나오게)
         */
        Monster[] easyEnemyList = new Monster[5];
        switch (stageID) {
            case 1:
               easyEnemyList[0] = new LouseRed();
               easyEnemyList[1] = new LouseGreen();
               break;
            case 2:
               easyEnemyList[0] = new Cultist();
                break;
            case 3:
               easyEnemyList[0] = new JawWorm();
               break;
            case 4:
               easyEnemyList[0] = new SpikeSlimeM();
               easyEnemyList[1] = new SpikeSlimeS();
               break;
            case 5:
               easyEnemyList[0] = new AcidSlimeM();
               easyEnemyList[1] = new AcidSlimeS();
               break;
        }
        return easyEnemyList;
    }
    public static Monster[] getEnemies(int stageID) {
       Random random = new Random();
       randomID1 = random.nextInt(16) + 1;
       randomID2 = random.nextInt(8) + 1;
        /*
          정해진 대로 적을 생성해주는 클래스.
          스테이지 id별로 적 배열을 생성해 리턴한다.
          일반전투
         */
        Monster[] enemyList = new Monster[5];
        switch (stageID) {
            case 1:
               enemyList[0] = new BlueSlaver();
               break;
            case 2:
               enemyList[0] = new RedSlaver();
                break;
            case 3:
               enemyList[0] = new Looter();
               break;
            case 4:
               enemyList[0] = new SpikeSlimeS();
               enemyList[1] = new SpikeSlimeS();
               enemyList[2] = new SpikeSlimeS();
               enemyList[3] = new SpikeSlimeS();
               enemyList[4] = new SpikeSlimeS();
               break;
            case 5:
               enemyList[0] = new AcidSlimeS();
               enemyList[1] = new AcidSlimeS();
               enemyList[2] = new AcidSlimeS();
               enemyList[3] = new AcidSlimeS();
               enemyList[4] = new AcidSlimeS();
               break;
            case 6:
               enemyList[0] = new ShieldGremlin();
               enemyList[1] = new GremlinWizard();
               enemyList[2] = new MadGremlin();
               enemyList[3] = new FatGremlin();
               enemyList[4] = new SneakyGremlin();
               break;
            case 7:
               enemyList[0] = new LouseRed();
               enemyList[1] = new LouseGreen();
               enemyList[2] = new LouseRed();
               break;
            case 8:
               enemyList[0] = new FungiBeast();
               enemyList[1] = new FungiBeast();
               break;
               
            case 9:
               switch (randomID1) {
               case 1:
                   enemyList[0] = new LouseRed();
                   enemyList[1] = new Cultist();
                   break;
                case 2:
                   enemyList[0] = new LouseRed();
                   enemyList[1] = new BlueSlaver();
                    break;
                case 3:
                   enemyList[0] = new LouseRed();
                   enemyList[1] = new RedSlaver();
                   break;
                case 4:
                   enemyList[0] = new LouseRed();
                   enemyList[1] = new Looter();
                   break;
                case 5:
                   enemyList[0] = new LouseGreen();
                   enemyList[1] = new Cultist();
                   break;
                case 6:
                   enemyList[0] = new LouseGreen();
                   enemyList[1] = new BlueSlaver();
                   break;
                case 7:
                   enemyList[0] = new LouseGreen();
                   enemyList[1] = new RedSlaver();
                   break;
                case 8:
                   enemyList[0] = new LouseGreen();
                   enemyList[1] = new Looter();
                   break;
                case 9:
                   enemyList[0] = new SpikeSlimeM();
                   enemyList[1] = new Cultist();
                   break;
                case 10:
                   enemyList[0] = new SpikeSlimeM();
                   enemyList[1] = new BlueSlaver();
                   break;
                case 11:
                   enemyList[0] = new SpikeSlimeM();
                   enemyList[1] = new RedSlaver();
                   break;
                case 12   :
                   enemyList[0] = new SpikeSlimeM();
                   enemyList[1] = new Looter();
                   break;
                case 13:
                   enemyList[0] = new AcidSlimeM();
                   enemyList[1] = new Cultist();
                   break;
                case 14:
                   enemyList[0] = new AcidSlimeM();
                   enemyList[1] = new BlueSlaver();
                   break;
                case 15:
                   enemyList[0] = new AcidSlimeM();
                   enemyList[1] = new RedSlaver();
                   break;
                case 16:
                   enemyList[0] = new AcidSlimeM();
                   enemyList[1] = new Looter();
                   break;
               }
               break;
               
            case 10:
               switch (randomID2) {
               case 1:
                   enemyList[0] = new LouseRed();
                   enemyList[1] = new FungiBeast();
                   break;
                case 2:
                   enemyList[0] = new LouseRed();
                   enemyList[1] = new JawWorm();
                    break;
                case 3:
                   enemyList[0] = new LouseGreen();
                   enemyList[1] = new FungiBeast();
                   break;
                case 4:
                   enemyList[0] = new LouseGreen();
                   enemyList[1] = new JawWorm();
                   break;
                case 5:
                   enemyList[0] = new SpikeSlimeM();
                   enemyList[1] = new FungiBeast();
                   break;
                case 6:
                   enemyList[0] = new SpikeSlimeM();
                   enemyList[1] = new JawWorm();
                   break;
                case 7:
                   enemyList[0] = new AcidSlimeM();
                   enemyList[1] = new FungiBeast();
                   break;
                case 8:
                   enemyList[0] = new AcidSlimeM();
                   enemyList[1] = new JawWorm();
                   break;
               }
               break;
        }
        return enemyList;
    }
    
    //앨리트 몬스터 리스트
    public static Monster[] eliteGetEnemies(int stageID) { 
        Monster[] eliteEnemyList = new Monster[5];
        switch (stageID) {
        case 1:
           eliteEnemyList[0] = new SpikeSlimeL();
           break;
        case 2:
           eliteEnemyList[1] = new AcidSlimeL();
           break;
        }
        return eliteEnemyList;
    }
    
    //보스 몬스터 리스트
    public static Monster[] bossGetEnemies(int stageID) { 
        Monster[] bossEnemyList = new Monster[5];
        switch (stageID) {
        case 1:
           bossEnemyList[0] = new GremlinNob();
           break;
        case 2:
           bossEnemyList[1] = new Lagavulin();
           break;
        }
        return bossEnemyList;
    }
    
    //분열 시 사용되는 슬라임
    public static Monster[] getSplit(int stageID) {
      Monster[] split = new Monster[5];
      switch (stageID) {
          case 1:
             split[0] = new SpikeSlimeM();
             split[1] = new SpikeSlimeM();
             break;
          case 2:
             split[0] = new AcidSlimeM();
             split[1] = new AcidSlimeM();
              break;
      }
      return split;
  }
}
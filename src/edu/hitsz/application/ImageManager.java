package edu.hitsz.application;


import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.PropBlood;
import edu.hitsz.prop.PropBomb;
import edu.hitsz.prop.PropBullet;
import edu.hitsz.frame.DifficultyFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageManager {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, BufferedImage> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static BufferedImage BACKGROUND_IMAGE;
    public static BufferedImage HERO_IMAGE;
    public static BufferedImage HERO_BULLET_IMAGE;
    public static BufferedImage ENEMY_BULLET_IMAGE;
    public static BufferedImage MOB_ENEMY_IMAGE;
    public static BufferedImage ELITE_ENEMY_IMAGE;
    public static BufferedImage BOSS_ENEMY_IMAGE;
    public static BufferedImage Blood_Prop_IMAGE;
    public static BufferedImage Bomb_Prop_IMAGE;
    public static BufferedImage Bullet_Prop_IMAGE;
    public static BufferedImage Over_IMAGE;

    static {
        try {

            HERO_IMAGE = ImageIO.read(new FileInputStream("src/images/hero.png"));
            MOB_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/mob.png"));
            ELITE_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/elite.png"));
            BOSS_ENEMY_IMAGE = ImageIO.read(new FileInputStream("src/images/boss.png"));
            HERO_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_hero.png"));
            ENEMY_BULLET_IMAGE = ImageIO.read(new FileInputStream("src/images/bullet_enemy.png"));
            Blood_Prop_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_blood.png"));
            Bomb_Prop_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bomb.png"));
            Bullet_Prop_IMAGE = ImageIO.read(new FileInputStream("src/images/prop_bullet.png"));
            Over_IMAGE=ImageIO.read(new FileInputStream("src/images/game_over.png"));

            CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE);
            CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(),ELITE_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(),BOSS_ENEMY_IMAGE);
            CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);
            CLASSNAME_IMAGE_MAP.put(PropBlood.class.getName(),Blood_Prop_IMAGE);
            CLASSNAME_IMAGE_MAP.put(PropBomb.class.getName(),Bomb_Prop_IMAGE);
            CLASSNAME_IMAGE_MAP.put(PropBullet.class.getName(),Bullet_Prop_IMAGE);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static BufferedImage get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static BufferedImage get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }
    public static void setBackGround()
    {
        int k= DifficultyFrame.difficultySelection;
        try {
            if (k == 0) {
                BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg.jpg"));
            } else if (k == 1) {
                BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
            } else if (k == 2) {
                BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg4.jpg"));
            }
        }catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}

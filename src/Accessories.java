import java.util.Random;

public interface Accessories {
    String getName();
    String getDescription();
}

interface Boots extends Accessories{
    double moveSpeedIncrease();
}

interface Ring extends Accessories{

    double manaIncrease();
}

interface noOnehit_Boots extends Boots{
    boolean noOnehit_kill();
}
interface DecreaseDamage_Boots extends Boots{
    double DecreaseDamagePercent();
}
interface DamageIncrease_Ring extends Ring{
    double DamageIncreasePercent();
}
interface multipleStat_Ring extends Ring{
    double multipleOneStat();
    int RandomNumber();
}

class Accessory implements Accessories{
    protected String name;
    protected String description;

    public Accessory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

}

class Ringclass extends Accessory implements Ring{
    public Ringclass(String name, String description) {
        super(name,description);
    }
    @Override
    public double manaIncrease() {
        System.out.println("Mana increased by wearing " + name);
        return 50;
    }
}

class Bootsclass extends Accessory implements Boots{
    public Bootsclass(String name,String description) {
        super(name,description);
    }

    @Override
    public double moveSpeedIncrease() {
        System.out.println("Move speed increased by wearing " + name);
        return 50;
    }
}

class noFirsthitBoots extends Bootsclass implements noOnehit_Boots{
    private boolean isProtected;

    public noFirsthitBoots(String name, String description) {
        super(name,description);
        this.isProtected = false;
    }

    @Override
    public boolean noOnehit_kill() {
        if (!isProtected) {
            System.out.println(super.getName() + " prevents a first-hit!");
            isProtected = true;
            return true;
        } else {
            System.out.println(super.getName() + " effect is already active.");
            return false;
        }
    }
}

class armorBoots extends Bootsclass implements DecreaseDamage_Boots {
    public armorBoots(String name,String description) {
        super(name,description);
    }
    @Override
    public double DecreaseDamagePercent() {
        System.out.println(name + "Decrease damage 15%!!");
        return 15.0;
    }
}

class warRing extends Ringclass implements DamageIncrease_Ring {
    public warRing(String name,String description) {
        super(name,description);
    }
    @Override
    public double DamageIncreasePercent() {
        System.out.println(name + "Increase damage 10%!!");
        return 10.0;
    }
}

class godRing extends Ringclass implements multipleStat_Ring {

    public godRing(String name,String description) {
        super(name,description);
    }
    @Override
    public double multipleOneStat() {
        return 2;
    }

    @Override
    public int RandomNumber() {
        Random rand = new Random();
        int n1 = rand.nextInt(3) + 1;
        return n1;
    }
}
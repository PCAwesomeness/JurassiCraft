package to.uk.ilexiconn.jurassicraft.api;

import net.minecraft.entity.monster.IMob;

public interface IAggressiveDino extends IMob
{
    boolean isHungry();

    boolean isAngry();
}

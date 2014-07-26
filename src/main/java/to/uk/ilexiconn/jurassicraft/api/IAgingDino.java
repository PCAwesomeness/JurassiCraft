package to.uk.ilexiconn.jurassicraft.api;

import to.uk.ilexiconn.jurassicraft.api.enums.AgeState;

/**
 * Use this stuff for the ai, Not coded yet
 */
public interface IAgingDino
{
    int getAge();

    int getMaxAge();

    AgeState getAgeState();

    float getAgeScale();
}

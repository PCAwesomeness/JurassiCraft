package to.uk.ilexiconn.jurassicraft.data.client.block.model;

import to.uk.ilexiconn.jurassicraft.data.client.modelbase.MowzieModelBase;
import to.uk.ilexiconn.jurassicraft.data.client.modelbase.MowzieModelRenderer;

public class ModelEgg extends MowzieModelBase
{
    public MowzieModelRenderer[] shapes = new MowzieModelRenderer[4];

    public ModelEgg()
    {
        textureWidth = 64;
        textureHeight = 64;

        shapes[0] = new MowzieModelRenderer(this, 0, 21);
        shapes[0].addBox(-3f, 0f, -3f, 6, 5, 6);
        shapes[0].setRotationPoint(0f, 18f, 0f);
        shapes[1] = new MowzieModelRenderer(this, 0, 14);
        shapes[1].addBox(-2.5f, 0f, -2.5f, 5, 2, 5);
        shapes[1].setRotationPoint(0f, 22f, 0f);
        shapes[2] = new MowzieModelRenderer(this, 24, 24);
        shapes[2].addBox(-2.5f, 0f, -2.5f, 5, 3, 5);
        shapes[2].setRotationPoint(0f, 16f, 0f);
        shapes[3] = new MowzieModelRenderer(this, 24, 18);
        shapes[3].addBox(-1.5f, 0f, -1.5f, 3, 3, 3);
        shapes[3].setRotationPoint(0f, 15f, 0f);

        for (MowzieModelRenderer shape : shapes) shape.setTextureSize(64, 64);
    }

    public void render()
    {
        for (MowzieModelRenderer shape : shapes) shape.render(0.0625f);
    }
}

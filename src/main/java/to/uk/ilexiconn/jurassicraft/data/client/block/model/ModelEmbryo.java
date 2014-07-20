package to.uk.ilexiconn.jurassicraft.data.client.block.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelEmbryo extends ModelBase
{
    public ModelRenderer[] shapes = new ModelRenderer[13];

    public ModelEmbryo()
    {
        textureWidth = 64;
        textureHeight = 64;

        shapes[0] = new ModelRenderer(this, 0, 0);
        shapes[0].addBox(0F, 0F, 0F, 3, 5, 2);
        shapes[0].setRotationPoint(-2.5F, 2F, -1F);
        shapes[1] = new ModelRenderer(this, 10, 0);
        shapes[1].addBox(0F, 0F, 0F, 2, 2, 3);
        shapes[1].setRotationPoint(-2F, 1F, -3F);
        shapes[2] = new ModelRenderer(this, 20, 0);
        shapes[2].addBox(0F, 0F, 0F, 2, 1, 1);
        shapes[2].setRotationPoint(-2F, 2F, -0.4F);
        shapes[3] = new ModelRenderer(this, 26, 0);
        shapes[3].addBox(0F, 0F, 0F, 2, 1, 1);
        shapes[3].setRotationPoint(-2F, 2F, -3F);
        shapes[4] = new ModelRenderer(this, 20, 2);
        shapes[4].addBox(0F, 0F, 0F, 1, 1, 2);
        shapes[4].setRotationPoint(0F, 4F, -2F);
        shapes[5] = new ModelRenderer(this, 26, 2);
        shapes[5].addBox(0F, 0F, 0F, 1, 1, 2);
        shapes[5].setRotationPoint(-3F, 4F, -2F);
        shapes[6] = new ModelRenderer(this, 32, 0);
        shapes[6].addBox(0F, 0F, 0F, 1, 2, 1);
        shapes[6].setRotationPoint(-2.5F, 6.5F, -1F);
        shapes[7] = new ModelRenderer(this, 36, 0);
        shapes[7].addBox(0F, 0F, 0F, 1, 2, 1);
        shapes[7].setRotationPoint(-0.5F, 6.5F, -1F);
        shapes[8] = new ModelRenderer(this, 40, 0);
        shapes[8].addBox(0F, 0F, 0F, 1, 3, 1);
        shapes[8].setRotationPoint(-1.5F, 6F, 0.5F);
        shapes[9] = new ModelRenderer(this, 32, 3);
        shapes[9].addBox(0F, 0F, 0F, 1, 1, 1);
        shapes[9].setRotationPoint(-1F, 9F, 0F);
        shapes[10] = new ModelRenderer(this, 36, 3);
        shapes[10].addBox(0F, 0F, 0F, 1, 1, 1);
        shapes[10].setRotationPoint(-1.5F, 10F, 0F);
        shapes[11] = new ModelRenderer(this, 10, 5);
        shapes[11].addBox(0F, 0F, 0F, 1, 1, 1);
        shapes[11].setRotationPoint(-1F, 11F, 0.5F);
        shapes[12] = new ModelRenderer(this, 0, 7);
        shapes[12].addBox(0F, 0F, 0F, 1, 7, 1);
        shapes[12].setRotationPoint(-1.5F, 12F, 0F);

        for (ModelRenderer shape : shapes) shape.setTextureSize(64, 64);
    }

    public void render()
    {
        for (ModelRenderer shape : shapes) shape.render(0.0625f);
    }
}

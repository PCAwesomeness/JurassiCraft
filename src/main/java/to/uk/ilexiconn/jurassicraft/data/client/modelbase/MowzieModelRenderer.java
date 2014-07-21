package to.uk.ilexiconn.jurassicraft.data.client.modelbase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class MowzieModelRenderer extends ModelRenderer
{
    public float initRotateAngleX;
    public float initRotateAngleY;
    public float initRotateAngleZ;

    public float initRotationPointX;
    public float initRotationPointY;
    public float initRotationPointZ;

    public MowzieModelRenderer(ModelBase par1ModelBase, String par2Str)
    {
        super(par1ModelBase, par2Str);
    }

    public MowzieModelRenderer(ModelBase par1ModelBase, int par2, int par3)
    {
        super(par1ModelBase, par2, par3);
    }

    public MowzieModelRenderer(ModelBase par1ModelBase)
    {
        super(par1ModelBase);
    }

    public void setRotationAngle(float x, float y, float z)
    {
        rotateAngleX = x;
        rotateAngleY = y;
        rotateAngleZ = z;
    }

    public void setInitValuesToCurrentPose()
    {
        initRotateAngleX = rotateAngleX;
        initRotateAngleY = rotateAngleY;
        initRotateAngleZ = rotateAngleZ;

        initRotationPointX = rotationPointX;
        initRotationPointY = rotationPointY;
        initRotationPointZ = rotationPointZ;
    }

    public void setCurrentPoseToInitValues()
    {
        rotateAngleX = initRotateAngleX;
        rotateAngleY = initRotateAngleY;
        rotateAngleZ = initRotateAngleZ;

        rotationPointX = initRotationPointX;
        rotationPointY = initRotationPointY;
        rotationPointZ = initRotationPointZ;
    }
}

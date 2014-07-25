package to.uk.ilexiconn.jurassicraft.data.client.animationbase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;

public class MowzieModelBase extends ModelBase
{
    public void addChildTo(ModelRenderer child, ModelRenderer parent)
    {
        float distance = (float) Math.sqrt(Math.pow((child.rotationPointZ - parent.rotationPointZ), 2) + Math.pow((child.rotationPointY - parent.rotationPointY), 2));
        float oldRotateAngleX = parent.rotateAngleX;
        float parentToChildAngle = (float) Math.atan((child.rotationPointZ - parent.rotationPointZ) / (child.rotationPointY - parent.rotationPointY));
        float childRelativeRotation = parentToChildAngle - parent.rotateAngleX;
        float newRotationPointY = (float) (distance * (Math.cos(childRelativeRotation)));
        float newRotationPointZ = (float) (distance * (Math.sin(childRelativeRotation)));
        parent.rotateAngleX = 0F;
        child.setRotationPoint(child.rotationPointX - parent.rotationPointX, newRotationPointY, newRotationPointZ);
        parent.addChild(child);
        parent.rotateAngleX = oldRotateAngleX;
        child.rotateAngleX -= parent.rotateAngleX;
        child.rotateAngleY -= parent.rotateAngleY;
        child.rotateAngleZ -= parent.rotateAngleZ;
    }

    public void faceTarget(MowzieModelRenderer box, int divider, float f3, float f4)
    {
        box.rotateAngleY = (f3 / (180F / (float) Math.PI)) / divider + box.initRotateAngleY;
        box.rotateAngleX = (f4 / (180F / (float) Math.PI)) / divider + box.initRotateAngleX;
    }

    public void walk(MowzieModelRenderer box, float speed, float degree, boolean invert, float offset, float f, float f1)
    {
        int intinvert = 1;
        if (invert) intinvert = -1;
        box.rotateAngleX = MathHelper.cos(f * speed + offset) * degree * intinvert * f1 + box.initRotateAngleX;
    }

    public void flap(MowzieModelRenderer box, float speed, float degree, boolean invert, float offset, float f, float f1)
    {
        int intinvert = 1;
        if (invert) intinvert = -1;
        box.rotateAngleZ = MathHelper.cos(f * speed + offset) * degree * intinvert * f1 + box.initRotateAngleZ;
    }

    public void tailSwing(MowzieModelRenderer[] boxes, float speed, float degree, double rootOffset, float frame)
    {
        int numberOfSegments = boxes.length;
        float offset = (float) ((rootOffset * Math.PI) / (2 * numberOfSegments));
        for (int i = 0; i < numberOfSegments; i++)
            boxes[i].rotateAngleY = MathHelper.cos(frame * speed + offset * i) * degree + boxes[i].initRotateAngleY;
    }

    public void transitionBox(MowzieModelRenderer box, float desiredrotateAngleX, float desiredrotateAngleY, float desiredrotateAngleZ, float animationSpeed, int animationCounter)
    {
        if (animationCounter == 0)
        {
            box.initRotateAngleY = box.rotateAngleY;
            box.initRotateAngleX = box.rotateAngleX;
        }
        box.rotateAngleY = box.initRotateAngleY - ((box.initRotateAngleY - desiredrotateAngleY) / 2) + ((box.initRotateAngleY - desiredrotateAngleY) / 2) * (MathHelper.cos((float) ((animationCounter * Math.PI) / animationSpeed)));
        box.rotateAngleY = box.initRotateAngleX - ((box.initRotateAngleX - desiredrotateAngleX) / 2) + ((box.initRotateAngleX - desiredrotateAngleX) / 2) * (MathHelper.cos((float) ((animationCounter * Math.PI) / animationSpeed)));
    }

    public void simpleParent(ModelRenderer child, ModelRenderer parent)
    {
        float offsetX = parent.rotationPointX - child.rotationPointX;
        float offsetY = parent.rotationPointY - child.rotationPointY;
        float offsetZ = parent.rotationPointZ - child.rotationPointZ;

        child.setRotationPoint(parent.rotationPointX, parent.rotationPointY, parent.rotationPointZ);

        child.offsetX -= offsetX;
        child.offsetY -= offsetY;
        child.offsetZ -= offsetZ;
    }

    public void newfaceTarget(MowzieModelRenderer box, int divider, float f3, float f4)
    {
        box.rotateAngleY += (f3 / (180F / (float) Math.PI)) / divider;
        box.rotateAngleX += (f4 / (180F / (float) Math.PI)) / divider;
    }

    public void newwalk(MowzieModelRenderer box, float speed, float degree, boolean invert, float offset, float weight, float f, float f1)
    {
        int intinvert = 1;
        if (invert) intinvert = -1;
        box.rotateAngleX += MathHelper.cos(f * speed + offset) * degree * intinvert * f1 + weight * f1;
    }

    public void newflap(MowzieModelRenderer box, float speed, float degree, boolean invert, float offset, float f, float f1)
    {
        int intinvert = 1;
        if (invert) intinvert = -1;
        box.rotateAngleZ += MathHelper.cos(f * speed + offset) * degree * intinvert * f1;
    }

    public void newbob(MowzieModelRenderer box, float speed, float degree, boolean bounce, float f, float f1)
    {
        float bob = (float) (Math.sin(f * speed) * f1 * degree - f1 * degree);
        box.rotationPointY += bob;
    }

    public void newtailSwing(MowzieModelRenderer[] boxes, float speed, float degree, double rootOffset, float frame)
    {
        int numberOfSegments = boxes.length;
        float offset = (float) ((rootOffset * Math.PI) / (2 * numberOfSegments));
        for (int i = 0; i < numberOfSegments; i++)
            boxes[i].rotateAngleY += MathHelper.cos(frame * speed + offset * i) * degree;
    }

    public void newchainWave(MowzieModelRenderer[] boxes, float speed, float degree, double rootOffset, float f, float f1)
    {
        int numberOfSegments = boxes.length;
        float offset = (float) ((rootOffset * Math.PI) / (2 * numberOfSegments));
        for (int i = 0; i < numberOfSegments; i++)
            boxes[i].rotateAngleX += MathHelper.cos(f * speed + offset * i) * f1 * degree;
    }
}

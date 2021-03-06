package com.levicore.libgdxcore.tween;

import com.levicore.libgdxcore.core.Entity;

import aurelienribon.tweenengine.TweenAccessor;

public class EntityAccessor implements TweenAccessor<Entity> {

    public static final int EXP = 7;

    public static final int HP = 6;
    public static final int MP = 5;
    public static final int TP = 4;

    public static final int SCALE = 3;
    public static final int ROTATION = 2;
    public static final int POSXY = 1;
	public static final int ALPHA = 0;

	@Override
	public int getValues(Entity target, int tweenType, float[] returnValues) {
		switch(tweenType) {
            case SCALE :
                returnValues[0] = target.getScaleX() * target.getScaleY();
                return 1;
            case ROTATION :
                returnValues[0] = target.getRotation();
                return 1;
            case POSXY:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                return 2;
            case ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;
            default:
                assert false;
                return -1;
		}
	}

	@Override
	public void setValues(Entity target, int tweenType, float[] newValues) {
		switch(tweenType) {
            case SCALE :
                target.setScale(newValues[0]);
                break;
            case ROTATION :
                target.setRotation(newValues[0]);
                break;
            case POSXY :
                target.setPosition(newValues[0], newValues[1]);
                break;
            case ALPHA :
                target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
                break;
            default :
                assert false;
		}
	}



}
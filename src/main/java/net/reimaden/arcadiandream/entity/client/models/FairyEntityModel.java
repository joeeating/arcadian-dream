/*
 * Copyright (c) 2023 Maxmani and contributors.
 * Licensed under the EUPL-1.2 or later.
 */

package net.reimaden.arcadiandream.entity.client.models;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.reimaden.arcadiandream.ArcadianDream;
import net.reimaden.arcadiandream.entity.client.renderers.FairyEntityRenderer;
import net.reimaden.arcadiandream.entity.custom.FairyEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class FairyEntityModel extends GeoModel<FairyEntity> {

    @Override
    public Identifier getModelResource(FairyEntity animatable) {
        return new Identifier(ArcadianDream.MOD_ID, "geo/entity/fairy.geo.json");
    }

    @Override
    public Identifier getTextureResource(FairyEntity animatable) {
        return FairyEntityRenderer.LOCATION_BY_VARIANT.get(animatable.getVariant());
    }

    @Override
    public Identifier getAnimationResource(FairyEntity animatable) {
        return new Identifier(ArcadianDream.MOD_ID, "animations/entity/fairy.animation.json");
    }

    @Override
    public void setCustomAnimations(FairyEntity animatable, long instanceId, AnimationState<FairyEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}

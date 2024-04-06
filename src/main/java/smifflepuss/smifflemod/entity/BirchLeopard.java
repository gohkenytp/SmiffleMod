package smifflepuss.smifflemod.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import smifflepuss.smifflemod.entity.ai.BirchLeopardAIMelee;
import smifflepuss.smifflemod.registry.SmiffleModEntities;
import smifflepuss.smifflemod.registry.SmiffleModSoundEvents;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BirchLeopard extends Animal implements GeoEntity {
    protected static final RawAnimation BIRCH_LEOPARD_IDLE_ANIM = RawAnimation.begin().thenLoop("animation.birchleopard.idle");
    protected static final RawAnimation BIRCH_LEOPARD_WALK_ANIM = RawAnimation.begin().thenLoop("animation.birchleopard.walk");
    protected static final RawAnimation BIRCH_LEOPARD_ATTACK_ANIM = RawAnimation.begin().thenLoop("animation.birchleopard.attack");
    protected static final RawAnimation BIRCH_LEOPARD_SNEAK_ANIM = RawAnimation.begin().thenLoop("animation.birchleopard.sneak");

    private static final EntityDataAccessor<Boolean> BL_SNEAKING = SynchedEntityData.defineId(BirchLeopard.class, EntityDataSerializers.BOOLEAN);

    private final AnimatableInstanceCache instanceCache = GeckoLibUtil.createInstanceCache(this);

    public float prevSneakProgress;
    public float sneakProgress;
    private boolean hasSlowedDown = false;

    public BirchLeopard(EntityType<? extends BirchLeopard> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
        this.moveControl = new BirchLeopardMoveControl(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 32.0D).add(Attributes.ATTACK_DAMAGE, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.35D).add(Attributes.FOLLOW_RANGE, 64F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new BirchLeopardAIMelee(this));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this,  1.0D, 70));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 15.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Chicken.class, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Cow.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Sheep.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Pig.class, false));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Horse.class, false));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Rabbit.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, false, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return SmiffleModEntities.BIRCH_LEOPARD.get().create(p_146743_);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SmiffleModSoundEvents.ENTITY_BIRCH_LEOPARD_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SmiffleModSoundEvents.ENTITY_BIRCH_LEOPARD_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SmiffleModSoundEvents.ENTITY_BIRCH_LEOPARD_DEATH.get();
    }

    @Override
    public float getScale() {
        return this.isBaby() ? 0.3F : 1.0F;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return (stack.isEdible() && stack.is(Items.SWEET_BERRIES));
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (super.doHurtTarget(entity)) {
            this.playSound(SoundEvents.FOX_BITE, this.getSoundVolume(), this.getVoicePitch());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("BLSneaking", this.isBLSneaking());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setBLSneaking(compound.getBoolean("BLSneaking"));
    }

    public boolean isBLSneaking() {
        return this.entityData.get(BL_SNEAKING);
    }

    public void setBLSneaking(boolean sneaking) {
        this.entityData.set(BL_SNEAKING, sneaking);
    }

    static class BirchLeopardMoveControl extends MoveControl {
        private final BirchLeopard birchLeopard;

        public BirchLeopardMoveControl(BirchLeopard birchLeopardEntity) {
            super(birchLeopardEntity);
            this.birchLeopard = birchLeopardEntity;
        }

        @Override
        public void tick() {
            if (!this.birchLeopard.isBLSneaking()) {
                super.tick();
            }
        }
    }

    public void tick() {
        super.tick();
        this.prevSneakProgress = sneakProgress;

        final boolean blSneaking = isBLSneaking();

        if (blSneaking) {
            if (sneakProgress < 5F) {
                sneakProgress += 0.5F;
            }
        } else {
            if (sneakProgress > 0F) {
                sneakProgress -= 0.5F;
            }
        }

        if(blSneaking && !hasSlowedDown){
            hasSlowedDown = true;
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.25F);
        }
        if(!blSneaking && hasSlowedDown){
            hasSlowedDown = false;
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35F);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "BirchLeopardAnimations", 3, this::setAnimation));
    }

    protected <E extends BirchLeopard> PlayState setAnimation(final AnimationState<E> event) {
        if (event.isMoving()) {
            return event.setAndContinue(BIRCH_LEOPARD_WALK_ANIM);
        } else if (this.isBLSneaking()) {
            return event.setAndContinue(BIRCH_LEOPARD_SNEAK_ANIM);
        } else {
            event.setAndContinue(BIRCH_LEOPARD_IDLE_ANIM);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.instanceCache;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BL_SNEAKING, false);
    }
}

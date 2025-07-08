package com.github.stoyank7.helldivers;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class StratagemEntity extends SnowballEntity {

    public StratagemEntity(EntityType<? extends SnowballEntity> entityType, World world) {
        super(entityType, world);
    }

    public StratagemEntity(World world, LivingEntity owner, ItemStack stack) {
        super(world, owner, stack);
    }

    public StratagemEntity(World world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.STRATAGEM;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        World world = this.getWorld();
        BlockPos landPos = this.getBlockPos().down(2);

        // TODO: MAke a firework fall
//        	public FireworkRocketEntity(World world, ItemStack stack, double x, double y, double z, boolean shotAtAngle) {

        ItemStack stack = this.getStack();
        FireworkRocketEntity firework = new FireworkRocketEntity(
                world, stack,
                landPos.getX() + 0.5,
                landPos.getY() + 0.5,
                landPos.getZ() + 0.5,
                true
        );
        ProjectileEntity.spawn(firework, (ServerWorld) world, stack);

//        world.spawnEntity(firework);


//        for (int x = -1; x <= 1; x++) {
//            for (int z = -1; z <= 1; z++) {
//                BlockPos basePos = landPos.down().add(x, 0, z);
//                world.setBlockState(basePos, Blocks.IRON_BLOCK.getDefaultState());
//            }
//        }
//
//        world.setBlockState(landPos, Blocks.BEACON.getDefaultState());
//        world.setBlockState(landPos.up(), Blocks.RED_STAINED_GLASS.getDefaultState());
//
//        // TODO: Add delayed explosion
//        // Working on: adding delay
////        net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
////        https://wiki.fabricmc.net/tutorial:event_index?s[]=event
////        https://wiki.fabricmc.net/tutorial:callbacks?s[]=event
//        // TODO: Create explosion
//        StratagemScheduler.scheduleStratagem(100, () -> {
//            world.createExplosion(null, landPos.getX() + 1, landPos.getY() + 1, landPos.getZ(), 25.0F, World.ExplosionSourceType.TNT);
//        });
    }
}

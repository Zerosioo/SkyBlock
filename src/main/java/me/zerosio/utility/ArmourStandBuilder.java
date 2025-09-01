package me.zerosio.utility;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.util.EulerAngle;

public class ArmourStandBuilder {

    private final Location location;
    private final ItemStack heldItem;
    private ItemStack helmet = null;
    private ItemStack chestplate = null;
    private ItemStack leggings = null;
    private ItemStack boots = null;

    private boolean visible = true; // Fixed: default to visible
    private boolean small = false;
    private boolean marker = false;
    private boolean arms = true;
    private boolean gravity = false;

    // Hand positions
    private EulerAngle rightArmPose = EulerAngle.ZERO;
    private EulerAngle leftArmPose = EulerAngle.ZERO;
    
    // Body positions
    private EulerAngle bodyPose = EulerAngle.ZERO;
    private EulerAngle headPose = EulerAngle.ZERO;
    private EulerAngle rightLegPose = EulerAngle.ZERO;
    private EulerAngle leftLegPose = EulerAngle.ZERO;

    public ArmourStandBuilder(Location location, Material material, byte data) {
        this.location = location;
        this.heldItem = new MaterialData(material, data).toItemStack(1);
    }

    public ArmourStandBuilder setHelmet(ItemStack helmet) {
        this.helmet = helmet;
        return this;
    }

    public ArmourStandBuilder setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
        return this;
    }

    public ArmourStandBuilder setLeggings(ItemStack leggings) {
        this.leggings = leggings;
        return this;
    }

    public ArmourStandBuilder setBoots(ItemStack boots) {
        this.boots = boots;
        return this;
    }

    public ArmourStandBuilder setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public ArmourStandBuilder setSmall(boolean small) {
        this.small = small;
        return this;
    }

    public ArmourStandBuilder setMarker(boolean marker) {
        this.marker = marker;
        return this;
    }

    public ArmourStandBuilder setArms(boolean arms) {
        this.arms = arms;
        return this;
    }

    public ArmourStandBuilder setGravity(boolean gravity) {
        this.gravity = gravity;
        return this;
    }

    // Hand pose methods
    public ArmourStandBuilder setRightArmPose(EulerAngle pose) {
        this.rightArmPose = pose;
        return this;
    }

    public ArmourStandBuilder setLeftArmPose(EulerAngle pose) {
        this.leftArmPose = pose;
        return this;
    }

    // Body pose methods
    public ArmourStandBuilder setBodyPose(EulerAngle pose) {
        this.bodyPose = pose;
        return this;
    }

    public ArmourStandBuilder setHeadPose(EulerAngle pose) {
        this.headPose = pose;
        return this;
    }

    public ArmourStandBuilder setRightLegPose(EulerAngle pose) {
        this.rightLegPose = pose;
        return this;
    }

    public ArmourStandBuilder setLeftLegPose(EulerAngle pose) {
        this.leftLegPose = pose;
        return this;
    }

    // Convenience method for head rotation (yaw/pitch)
    public ArmourStandBuilder setHeadRotation(float yaw, float pitch) {
        this.headPose = new EulerAngle(Math.toRadians(pitch), Math.toRadians(yaw), 0);
        return this;
    }

    // Method to put item on head instead of hand
    public ArmourStandBuilder putItemOnHead() {
        if (this.helmet == null) {
            this.helmet = this.heldItem;
        }
        return this;
    }

    public ArmorStand spawn() {
        ArmorStand stand = location.getWorld().spawn(location, ArmorStand.class);
        stand.setVisible(visible);
        stand.setSmall(small);
        stand.setMarker(marker);
        stand.setArms(arms);
        stand.setGravity(gravity);

        stand.setItemInHand(heldItem);
        if (helmet != null) stand.setHelmet(helmet);
        if (chestplate != null) stand.setChestplate(chestplate);
        if (leggings != null) stand.setLeggings(leggings);
        if (boots != null) stand.setBoots(boots);

        // Set poses
        stand.setRightArmPose(rightArmPose);
        stand.setLeftArmPose(leftArmPose);
        stand.setBodyPose(bodyPose);
        stand.setHeadPose(headPose);
        stand.setRightLegPose(rightLegPose);
        stand.setLeftLegPose(leftLegPose);

        return stand;
    }
}
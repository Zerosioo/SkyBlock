package me.zerosio.utility;

import net.minecraft.server.v1_8_R3.BlockPosition;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

import java.util.Objects;

public class XYZ {
    private double x;
    private double y;
    private double z;

    public XYZ(double x, double y, double z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    public XYZ(Location location) {
        this.setX(location.getBlockX());
        this.setY(location.getBlockY());
        this.setZ(location.getBlockZ());
    }

    public XYZ(Vector location) {
        this.setX(location.getX());
        this.setY(location.getY());
        this.setZ(location.getZ());
    }


	public double length() {
		return Math.sqrt(NumberConversions.square(this.getX()) + NumberConversions.square(this.getY()) + NumberConversions.square(this.getZ()));
	}

    public XYZ normalise(){
		double length = this.length();
		this.setX(this.getX() / length);
		this.setY(this.getY() / length);
		this.setZ(this.getZ() / length);
		return this;
	}


    public static XYZ fromLocation(Location location) {
        return new XYZ(location.getX(), location.getY(), location.getZ());
    }

    public Direction toDirection(){
        Direction dir = new Direction(0,0);
        if (getX() == 0.0D && getZ() == 0.0D) {
            dir.setPitch(getY() > 0.0D ? -90 : 90);
            return dir;
        } else {
            double theta = Math.atan2(-getX(), getZ());
            dir.setYaw((float) Math.toDegrees((theta + 6.283185307179586D) % 6.283185307179586D));
            double x2 = NumberConversions.square(getX());
            double z2 = NumberConversions.square(getZ());
            double xz = Math.sqrt(x2 + z2);
            dir.setPitch((float)Math.toDegrees(Math.atan(-getY() / xz)));
            return dir;
        }
    }

    public static XYZ fromDirection(Direction dir){
            XYZ vector = new XYZ(0,0,0);
            double rotX = (double)dir.getYaw();
            double rotY = (double)dir.getPitch();
            vector.setY(-Math.sin(Math.toRadians(rotY)));
            double xz = Math.cos(Math.toRadians(rotY));
            vector.setX(-xz * Math.sin(Math.toRadians(rotX)));
            vector.setZ(xz * Math.cos(Math.toRadians(rotX)));
            return vector;
    }

    public XYZ clone() {
        return new XYZ(getX(), getY(), getZ());
    }

    public double distance(XYZ xyz) {
        double xDist = getX() - xyz.getX();
        double yDist = getY() - xyz.getY();
        double zDist = getZ() - xyz.getZ();
        return Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof XYZ)) return false;
        XYZ xyz = (XYZ) o;
        return (xyz.getX() == getX() && xyz.getY() == getY() && xyz.getZ() == getZ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld("world"), getX(), getY(), getZ());
    }

    public Location toLocation(World world) {
        return new Location(world, getX(), getY(), getZ());
    }

    public Vector toVector() {
        return new Vector(getX(), getY(), getZ());
    }

    public BlockPosition toBlockPosition() {
        return new BlockPosition(getX(), getY(), getZ());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}

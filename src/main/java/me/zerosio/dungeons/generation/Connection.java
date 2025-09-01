package me.zerosio.dungeons.generation;

import java.awt.Point;

public class Connection {
    private final Point point1;
    private final Point point2;
    private DoorType connectionType;

    public Connection(Point point1, Point point2, DoorType connectionType) {
        this.point1 = point1;
        this.point2 = point2;
        this.connectionType = connectionType;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setConnectionType(DoorType connectionType) {
        this.connectionType = connectionType;
    }

    public DoorType getConnectionType() {
        return connectionType;
    }
}

package com.bernardo.dbi.client.widget;

public class SmallArrowBtn extends DBIBtnManager {
    public enum Direction {
        LEFT(30), RIGHT(40);
        final int u;
        Direction(int u) { this.u = u; }
    }
    public SmallArrowBtn(int x, int y, Direction dir, Runnable onClick) {
        super(x, y, 10, 10, dir.u, 14, 24, onClick);
    }
}

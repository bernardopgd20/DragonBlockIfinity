package com.bernardo.dbi.client.widget;

public enum BtnIcon {

    // Grandes 15x15 - Y normal: 14, Y pressed: 29
    ARROW_LEFT_LG      (0,   14, 15, 15, false),
    ARROW_RIGHT_LG     (15,  14, 15, 15, false),
    X_LG               (90,  14, 15, 15, false),
    CIRCLE_LG          (105, 14, 15, 15, false),
    ARROW_UP_LG        (120, 14, 15, 15, false),
    ARROW_DOWN_LG      (135, 14, 15, 15, false),

    // Pequenos 10x10 - Y normal: 14, Y pressed: 24
    ARROW_LEFT_SM      (30,  14, 10, 10, true),
    ARROW_RIGHT_SM     (40,  14, 10, 10, true),
    ARROW_UP_SM        (50,  14, 10, 10, true),
    ARROW_DOWN_SM      (60,  14, 10, 10, true),
    SKIP_START_SM      (70,  14, 10, 10, true),
    SKIP_END_SM        (80,  14, 10, 10, true),
    K_IN_SM            (90,  14, 10, 10, true),
    K_OUT_SM           (100, 14, 10, 10, true),
    X_SM               (110, 14, 10, 10, true),
    CIRCLE_SM          (120, 14, 10, 10, true),
    START_SM           (130, 14, 10, 10, true),
    PAUSE_SM           (140, 14, 10, 10, true);

    public final int u, v, width, height;
    public final boolean small;
    public final int vPressed;

    BtnIcon(int u, int v, int width, int height, boolean small) {
        this.u = u;
        this.v = v;
        this.width = width;
        this.height = height;
        this.small = small;
        this.vPressed = small ? 24 : 29;
    }
}

package com.example.draggridview;

/**
 * 快捷方式元素信息
 */
public class ShortCutItem {

    private boolean isAddedToShortCut;

    private int type;

    public ShortCutItem(boolean isAddedToShortCut, int type) {
        this.isAddedToShortCut = isAddedToShortCut;
        this.type = type;
    }

    public boolean isAddedToShortCut() {
        return isAddedToShortCut;
    }

    public void setIsAddedToShortCut(boolean isAddedToShortCut) {
        this.isAddedToShortCut = isAddedToShortCut;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

package com.ntu.ynn_shop.ynn_shop.Interface;

public interface ItemTouchListenner {
    void onMove(int oldPosition, int newPosition);

    void swipe(int position, int direction);
}
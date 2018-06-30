package com.ntu.ynn_shop.ynn_shop.cac_cong_cu;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class tao_khoang_trong_doc extends RecyclerView.ItemDecoration {
    private int space;
    public tao_khoang_trong_doc(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = space;
        outRect.top = space;
    }
}

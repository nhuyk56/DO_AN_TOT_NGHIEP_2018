package com.ntu.ynn_shop.ynn_shop.cac_cong_cu;

import android.util.Log;

import com.github.marlonlom.utilities.timeago.TimeAgo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class lay_thoi_gian {
    public String lay(String st)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            Date d = sdf.parse(st);
            long millis = d.getTime();

            return TimeAgo.using(millis)
                    .replace("just now", "vài giây trước")
                    .replace("one minute ago", "1 phút trước")
                    .replace("minutes ago", "phút trước")
                    .replace("about an hour ago", "khoảng 1 giờ trước")
                    .replace("hours ago", "giờ trước")
                    .replace("yesterday","hôm qua")
                    .replace("days ago", "ngày trước")
                    .replace("about a month ago", "khoảng 1 tháng trước")
                    .replace("months ago","tháng trước")
                    .replace("about a year ago", "khoảng 1 năm trước")
                    .replace("years ago","năm trước");
        } catch (ParseException ex) {
           return "vài năm trước";
        }

    }

}

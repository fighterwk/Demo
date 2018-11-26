package com;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/6/27
 */
public class Client {
    public static void main(String[] args) {

        float i = 8.25f;
        float a = 3.23f;

        System.out.println(Integer.toBinaryString(Float.floatToIntBits(i)));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(a)));

//        Calendar calendar = Calendar.getInstance();
////        calendar.add(Calendar.HOUR_OF_DAY, -5);
//        SimpleDateFormat sdf = new SimpleDateFormat("H");
//        String format = sdf.format(calendar.getTime());
//
//        System.out.println(format);

//        System.out.println(System.currentTimeMillis());
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println(System.nanoTime());
//        {
//            float a = 3;
//            float b = 4;
//
//            float c = (float) Math.hypot(a, b);
//
//            float angleA = (float) Math.asin(a / c);
//
//            System.out.println("角a:" + angleA);
//            System.out.println("弧长:" + Math.toDegrees(angleA));
//            System.out.println("弧长2:" + angleA * 180f / Math.PI);
//        }
//
//        float y = 0;        // y边长
//        float r = 5;        // 半径
//        float x = 0;        // x边长
//
//        int menuCount = 6;
//        float singleAngle = 360f / menuCount;
//        float mAngle = 0;
//
//
//            x = (float) (r * Math.cos(Math.toRadians(mAngle)));
//            for (int i = 0; i < menuCount; i++) {
//                y = (float) (r * Math.sin(Math.toRadians(mAngle)));
//
//            System.out.println(String.format("p%d(%.2f, %.2f)", i, x, y));
//            mAngle += singleAngle;
//        }


//        int size = 8;  // 声明空间大小
//        System.out.println(4 & size - 1); // 2
//        System.out.println(1 >> 16);


        // 声明ArrayList初始化空间大小为14
//        ArrayList<String> list = new ArrayList<>(14);
//
//        for (int i = 0; i < 15; i++) {
//            list.add(String.valueOf(i));
//        }
//
//        System.out.println(list.size());
    }
}

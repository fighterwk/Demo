package com.example;


import java.math.BigDecimal;

public class MyClass {

    public static final void main(String[] args) {

//        List<String> list = new ArrayList<>();
//        list = Arrays.asList(new String[]{"0", "1"});
//        list = new ArrayList<>(list);
//        list.remove(0);
//        System.out.println(list instanceof ArrayList);

        BigDecimal aBigDecimal = new BigDecimal("1.1");
        BigDecimal bBigDecimal = new BigDecimal("2.0");

//        System.out.println(aBigDecimal);
//        System.out.println(bBigDecimal);


        BigDecimal subtract = bBigDecimal.subtract(aBigDecimal);
        System.out.println("使用BigDecimal进行相减计算:" + subtract.doubleValue());

        System.out.println("直接相减: " + (2.0 - 1.1));


//
//        System.out.println(2.0 - 1.1);
//        BigDecimal bigDecimal = new BigDecimal(2.0d);
//        BigDecimal subtract = bigDecimal.subtract(new BigDecimal(1.1d));
//        /**
//         * BigDecimal.setScale()方法用于格式化小数点
//         setScale(1)表示保留一位小数，默认用四舍五入方式
//         setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
//         setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
//         setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
//         setScale(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
//         */
//        System.out.println(subtract.setScale(10, BigDecimal.ROUND_HALF_UP).doubleValue());
//        System.out.println(subtract.scale());

//        float a = 7879781.3031193f;
//        float b = 78797811.39010f;
//
//        BigDecimal aDecimal = new BigDecimal(a);
//        BigDecimal bDecimal = new BigDecimal(b);
//        System.out.println(a * b);
//
//        BigDecimal multiply = aDecimal.multiply(bDecimal);
//        System.out.println(multiply.doubleValue());


//        for (int i = 0; i < 5; i++) {
//            System.out.println(String.format("%d %s 4 = %d", (i - 1), "%",((i - 1) % 4)));
//        }

//
//        try {
//            Process exec = Runtime.getRuntime().exec("ping 192.168.0.1");
//            InputStream inputStream = exec.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
//            String s;
//            while ((s = reader.readLine()) != null){
//                System.out.println(s);
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//

//        double degrees = Math.toDegrees(Math.PI / 180 * 90);
//        double degrees = Math.toDegrees(Math.toRadians(90));
//        System.out.println("degrees: " + degrees);
//        double degrees = 36;
//        double c = 5;
//        double a = 4;
//        double b = 3;
//
//        degrees = Math.asin(a / c);
//
//        b = Math.sin(degrees) * c;
//        a = Math.cos(degrees) * c;
//        System.out.println("b:" + b + " a:" + a);
//        double cycle = 2 * Math.PI * 360;
//        for (int i = 0; i < 360; i++) {
//            System.out.println(cycle * i);
//        }
//        for (float i = 0; i <= 90; i++) {
//            System.out.println(String.format("%.6f", Math.sin(Math.toRadians(i)) * 360));
//        }

//        System.out.println(Math.toDegrees(2 * Math.PI));
//        System.out.println(Math.sin(Math.PI / 2));
        // 计算弧度
        //
//        double radians = Math.atan2(-300 + 200, - 300 + 200);
//        System.out.println(Math.toDegrees(radians));

        // 已知原点 p1(200, 200), 计算p2(x, y), r = 200
//        for (int i = 0; i <= 360; i++) {
//            double degrees = Math.toDegrees(i);
//            double x = 200 + 200 * Math.sin(degrees);
//            double y = 200 + 200 * Math.cos(degrees);
//            System.out.printf("x:%.4f, y:%.4f", x, y);
//        }


        // Math.tan Math


    }
}

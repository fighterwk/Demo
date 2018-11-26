package com;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/7/4
 */
public class JsonParseClient {
    public static void main(String[] args) {
        String s = "{\n" +
                "                        \"CouponId\": 1,//优惠券ID\n" +
                "                        \"CouponNo\": \"1823524649152\",//优惠券编号\n" +
                "                        \"CouponName\":\"瑜伽体验券\",//优惠卷名称\n" +
                "                        \"CouponType\": \"体验券\" ,//优惠卷类型名称\n" +
                "                        \"CouponTypeName\":\"xxxx\",//退款原因\n" +
                "                        \"UsableRange\":1,//使用范围ID\n" +
                "                        \"UsableRangeName\": \"全场通用\",//使用范围名称\n" +
                "                        \"ExpiredTime\": \"2018-04-12T17:17:29.413\",//过期时间\n" +
                "                        \"ReceiveTime\": \"2018-04-12T17:17:29.413\",//使用时间\n" +
                "                        \"MemberId\": 10000,//会员ID\n" +
                "                        \"MemberName\": \"何维维\"//会员名称\n" +
                "                }";

        //去掉行级注释 与带斜杠的协议如http://冲突
        String regex = "\\/\\/[^\\n]*";
        //优化版的去掉行级注释 防止去掉协议中的双斜杠
        String regex2 = "(?<!:)\\/\\/.*";
        //去掉块级别注释
        String regex3 = "\\/\\*(\\s|.)*?\\*\\/";
        //即去掉行也去掉块级的注释
        String regex4 = "(?<!:)\\/\\/.*|\\/\\*(\\s|.)*?\\*\\/";
        s = s.replaceAll(regex4, "");
        System.out.println(s);

//        URI uri = URI.create("http://www.testkmjkzx.kmwlyy.com/web/zszg/index.html#/page?accessToken=c6a703da4a4a44188439e60f1e436f88&action=HOME");
//        System.out.println(String.format("%s://%s", uri.getScheme(), uri.getHost()));
    }


}

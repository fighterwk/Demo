package com.example.gson;

import com.example.gson.bean.ResponseBean;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2017/10/20
 */
public class GsonTest {
    public static final void main(String[] args) throws ClassNotFoundException {

        String json = "{\"ResultCode\":1,\"ResultMessage\":\"设置默认收货人成功!\",\"Data\":false}";
//                "{\"Data\":\"false\",\"ResultCode\":0,\"ResultMessage\":\"设置默认收货人成功！\"}";
        ResponseBean<String> responseBean =
//                new ResponseBean<>();
//        responseBean.setData(false);
//        responseBean.setResultMessage("设置默认收货人成功!");
                GsonUtil.toBean(json, new TypeToken<ResponseBean<String>>(){}.getType());

//        System.out.println(new TypeToken<Boolean>(){}.getType().getTypeName());
//        System.out.println(new Gson().fromJson("true", Class.forName("java.lang.Boolean")));

//        System.out.println(GsonUtil.toJson(responseBean));
        System.out.println(responseBean);
//        System.out.println(GsonUtil.toBean(json, ResponseBeanB.class));
    }


    public class ResponseBeanB {
        @SerializedName("ResultCode")
        int resultCode = 1;
        @SerializedName("ResultMessage")
        String resultMessage;
        @SerializedName("Data")
        Boolean data;


        public Boolean getData() {
            return data;
        }

        public int getResultCode() {
            return resultCode;
        }

        public String getResultMessage() {
            return resultMessage;
        }

        @Override
        public String toString() {
            return "ResponseBean{" +
                    "resultCode=" + resultCode +
                    ", resultMessage='" + resultMessage + '\'' +
                    ", data=" + data +
                    '}';
        }
    }
}

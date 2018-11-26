package com.gson;

import com.example.gson.bean.ResponseBean;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/6/21
 */
public class GsonClient {
    public static void main(String[] args) {

        // 将null 对象，转换为具体的空对象
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES)
//                .setFieldNamingStrategy(new FieldNamingStrategy() {
//                    @Override
//                    public String translateName(Field f) {
//                        System.out.println(f.getName());
//                        System.out.println("--------------");
//                        return "KM：" + f.getName();
//                    }
//                })
//                .setPrettyPrinting()
                .serializeNulls()
//                .registerTypeAdapter(String.class, new JsonDeserializer<String>() {
//                    @Override
//                    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                        System.out.println("T类型:" + typeOfT.getTypeName());
//                        System.out.println("字段是否为Null: " + json.isJsonNull());
//                        System.out.println(json.getAsString());
//                        return json.getAsString();
//                    }
//                })
//                .serializeNulls()
//                .registerTypeAdapterFactory(new TypeAdapterFactory() {
//                    @Override
//                    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
//                        System.out.println("rawType:" + type.getRawType());
//                        System.out.println("type:" + type.getType());
//                        System.out.println("parameterized" + ParameterizedType.class.isInstance(type.getType()));
//                        return null;
//                    }
//                })
                .create();

//        String json = "{\"Data\":null,\"ResultCode\":1,\"ResultMessage\":\"修改成功\",\"IsEncrypt\":false}";
        String json = "{\"data\":null,\"resultcode\":1,\"resultmessage\":\"修改成功\",\"IsEncrypt\":false}";

        Object o = gson.fromJson(json, new TypeToken<ResponseBean<String>>() {
        }.getType());

        System.out.println(o);


        String s = gson.toJson(o);
        System.out.println(s);


//        Gson gson = new GsonBuilder().serializeNulls().create();
//
//        String json = "{\"Data\":1,\"ResultCode\":1,\"ResultMessage\":\"修改成功\",\"IsEncrypt\":false}";
//
//        ObjBean objBean = gson.fromJson(json, ObjBean.class);


//        BaseResponse<Boolean> response = gson.fromJson(json, new TypeToken<BaseResponse<Boolean>>() {
//        }.getType());
//
//        System.out.println(response.getData());

//        JsonObject object = gson.fromJson(json, JsonObject.class);
//
//
//        JsonParser parser = new JsonParser();
//        String data = object.get("Data").getAsString();
//        System.out.println(data + "   -----------:" + parser.parse(data).isJsonNull());
//
//        TypeAdapter<BaseResponse<Boolean>> adapter = gson.getAdapter(new TypeToken<BaseResponse<Boolean>>() {
//        });
//
//        BaseResponse<Boolean> booleanBaseResponse = adapter.fromJsonTree(object);
//        System.out.println(booleanBaseResponse.getResultMessage());
//
//
//
//        // null, [], {}, String
//
//        JsonParser parser = new JsonParser();
//        System.out.println("array: " + parser.parse("[]").isJsonArray());
//        System.out.println("JsonPrimitive: " + parser.parse("true").isJsonPrimitive());
//        System.out.println("JsonNull: " + parser.parse(null + "").isJsonNull());
//        System.out.println("JsonObject: " + parser.parse("{}").isJsonObject());
//
//        try {
//            return;
//        }finally {
//            System.out.println("结束语");
//        }


//        JsonElement element = object.get("Data");
//        System.out.println("array: " + element.isJsonArray());
//        System.out.println("JsonPrimitive: " + element.isJsonPrimitive());
//        System.out.println("JsonNull: " + element.isJsonNull());
//        System.out.println("JsonObject: " + element.isJsonObject());


//        System.out.println(object.get("Data").getAsString());
//        System.out.println(object.get("ResultCode").getAsString());
//        System.out.println(object.get("ResultMessage").getAsString());


    }
}

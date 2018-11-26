package com.example.reterct;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkArgument;

/**
 * @Description描述:
 * @Author作者: Kyle
 * @Date日期: 2018/6/19
 */
public class FxClient {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 声明List<T>, 判断T泛型是否是List类型的数据
//        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>() {
//        };

        Test1<List<String>> list = new Test1<List<String>>() {
        };

        ParameterizedType genericSuperclass = (ParameterizedType) list.getClass().getGenericSuperclass();
        Type type = genericSuperclass.getActualTypeArguments()[0];
        if (type instanceof ParameterizedType){
            ParameterizedType pt = (ParameterizedType) type;
            Type rawType = pt.getRawType();
            if (rawType instanceof Class){
                System.out.println(rawType == List.class);
            }
        }
//        Class<?> rawType = getRawType(genericSuperclass.getActualTypeArguments()[0]);
//        System.out.println(rawType == List.class);
//        if (rawType.isInterface()) {
//            list.t = (List<String>) Proxy.newProxyInstance(rawType.getClassLoader(),
//                    new Class[]{rawType}, new InvocationHandler() {
//                        @Override
//                        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
////                            method.invoke(o, objects);
//                            return null;
//                        }
//                    });
//        } else {
//            list.t = (List<String>) rawType.newInstance();
//        }

        System.out.println(list.t);

    }


    public static Class<?> getRawType(Type type) {
        if (type instanceof Class<?>) {
            // type is a normal class.
            return (Class<?>) type;

        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;

            // I'm not exactly sure why getRawType() returns Type instead of Class.
            // Neal isn't either but suspects some pathological case related
            // to nested classes exists.
            Type rawType = parameterizedType.getRawType();
            checkArgument(rawType instanceof Class);
            return (Class<?>) rawType;

        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            return Array.newInstance(getRawType(componentType), 0).getClass();

        } else if (type instanceof TypeVariable) {
            // we could use the variable's bounds, but that won't work if there are multiple.
            // having a raw type that's more general than necessary is okay
            return Object.class;

        } else if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);

        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or "
                    + "GenericArrayType, but <" + type + "> is of type " + className);
        }
    }

}


interface Test<T> {
}

class Test1<T> implements Test<T> {
    // t 属性可能为空
    public T t;
}
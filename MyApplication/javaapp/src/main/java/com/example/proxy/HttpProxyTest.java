package com.example.proxy;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description描述: Java 动态代理  模拟Retrofit原理
 * <pre>
 * 知识点:
 * 1. Java 动态代理 Proxy
 * 代理类是在程序运行过程中创建的。然而，一旦被创建，就变成了常规类，与虚拟机中的任何其他类没
 * 有什么区别。
 * 所有的代理类都扩展于Proxy类。一个代理类只有一个实例域 — 调用处理器，它定义在
 * Proxy的超类中。为了履行代理对象的职责，所需要的任何附加数据都必须存储在调用处理器
 * 中
 * 关键类:
 * java.lang.reflect.InvocationHandler
 * • Obj ect i nvoke(Obj ect proxy, Met hod met hod, Obj ect [ ] args)
 * 定义了代理对象调用方法时希望执行的动作。
 * java.lang.reflect.Proxy
 * • st at i c Cl ass get ProxyCl ass(Cl assLoader l oader, Cl ass[ ] i nt erf aces)
 * 返回实现指定接口的代理类。
 * • st at i c Obj ect newPr oxyI nst ance( Cl assLoader l oader , Cl ass[ ] i nt er f aces,
 * I nvocat i onHandl er handl er)
 * 构造一个实现指定接口的代理类的实例。所有方法都将调用给定处理器对象的invoke方法。
 * • st at i c bool ean i sProxyCl ass(Cl ass c)
 * 如果c是一个代理类返回true
 * 2. java 注解 Annotation
 * 3. Java 反射
 * 4. Java 类加载器 ClassLoader
 * http://blog.csdn.net/gjanyanlig/article/details/6818655
 * </pre>
 * @Author作者: Kyle
 * @Date日期: 2017/11/20
 */
public class HttpProxyTest {
    public static void main(String[] args) {
        Login login = (Login) Proxy.newProxyInstance(Login.class.getClassLoader(), new Class[]{Login.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

                        // 获得api功能路径
                        String action = o.getClass().getInterfaces()[0].getSimpleName() + "/" +
                                method.getName();

                        // 获取返回的参数
                        Type genericParameterType = method.getGenericReturnType();
                        if (genericParameterType != null) {
                            if (genericParameterType instanceof ParameterizedType) {
                                ParameterizedType pt = (ParameterizedType) genericParameterType;
                                Type[] actualTypeArguments = pt.getActualTypeArguments();
                                if (actualTypeArguments != null) {
                                    String typeName = actualTypeArguments[0].getTypeName();
                                    System.out.println("actual: " + typeName);
                                }
                            } else {
                                System.out.println(genericParameterType);
                            }
                        }

                        Params.Builder builder = new Params.Builder();
                        builder.baseUrl("http://api.kyle.com")
                                .action(action);

                        int argCount = objects != null ? objects.length : 0;
                        List<String> methodArr = new ArrayList<>();
                        // 获取参数注解
                        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                        if (parameterAnnotations != null) {
                            for (Annotation[] parameterAnnotation : parameterAnnotations) {
                                if (parameterAnnotation == null || parameterAnnotation.length == 0) {
                                    continue;
                                }
                                for (Annotation annotation : parameterAnnotation) {
                                    if (annotation instanceof Field) {
                                        String key = ((Field) annotation).value();
                                        methodArr.add(key);
                                    }
                                }

                            }
                        }

                        if (argCount != methodArr.size()) {
                            throw new IllegalArgumentException();
                        }

                        for (int i = 0; i < methodArr.size(); i++) {
                            builder.put(methodArr.get(i), String.valueOf(objects[i]));
                        }

                        Params params = builder
                                .build();

                        HttpRequest request = new HttpRequest();
                        request.request(params);
                        // 返回的结果
                        return new Result<String>() {
                            @Override
                            public String body() {
                                // TODO: 2017/11/21 请求结果
                                return "{\"msg\":\"请求成功\",\"data\":{}}";
                            }
                        };
                    }
                });

//        login.test("myKye", "0202002");
        Result<String> result = login.login("kyle", "123456");
        System.out.println(result.body());
        login.reg("test", "123456", "1441");
//        login.noParams();

    }
}

/**
 * 定义：返回请求得到的结果。
 *
 * @param <T>
 */
interface Result<T> {

    String body();
}

class LoginApi{

    public void login(String name, String pass){
        Params.Builder builder = new Params.Builder();
        builder.baseUrl("http://www.kyle.com")
                .action("login")
        .put("name", name)
        .put("pass", pass);

        new HttpRequest().request(builder.build());
    }
}

/**
 * 定义:API请求(接口是一个对事物进行描述)
 */
interface Login {
    /**
     * 登录接口
     *
     * @param account
     * @param pass
     * @return 得到结果
     */
    Result<String> login(@Field("account") String account, // @Field声明参数名称
                         @Field("pass") String pass);

    void reg(@Field("acc") String acc,
             @Field("pass") String pass, @Field("code") String code);

    // 无参请求
    void noParams();

    // 抛出异常(参数必须是有@Field声明的，不然就是无用参数)
    void test(@Field("key") String key, String no);
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@interface Field {

    String value();
}

enum HttpType {
    GET, POST, PUT, DELETE, HEAD, CONNECT, OPTIONS, TRACE
}

/**
 * 请求参数，
 */
class Params {
    private String baseUrl;
    private String action;
    private Map<String, Object> params;
    private HttpType httpType;


    private Params(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.action = builder.action;
        this.params = builder.params;
        this.httpType = builder.httpType;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAction() {
        return action;
    }

    public HttpType getHttpType() {
        return httpType;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    static class Builder {
        private String baseUrl;
        private String action;
        private HttpType httpType;
        private Map<String, Object> params = new HashMap<>();

        public Builder() {
            httpType = HttpType.POST;
        }

        public Builder baseUrl(String baseUrl) {
            check(baseUrl, "baseUrl 不能为空");
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder action(String action) {
            check(action, "action 不能为空");
            this.action = action;
            return this;
        }

        public Builder put(String key, String value) {
            check(key, "key 不能为空");
            this.params.put(key, value);
            return this;
        }

        public Builder type(HttpType type) {
            this.httpType = type;
            return this;
        }

        public Params build() {
            check(this.baseUrl, "baseUrl 不能为空");
            check(this.action, "action 不能为空");
            return new Params(this);
        }


        private void check(String params, String eDesc) {
            if (null == params || "".equals(params)) {
                throw new IllegalArgumentException(eDesc);
            }
        }
    }

}

class HttpRequest {

    public void request(Params params) {
        System.out.println("开始请求:" + params.getBaseUrl() + "/" + params.getAction());
        // HTTP_TYPE 有多种方式
        System.out.println("请求方式:" + params.getHttpType().name());
        System.out.println("Content-Type:application/x-www-form-urlencoded");
        System.out.println("请求参数:");
        Set<String> keys = params.getParams().keySet();
        for (String key : keys) {
            System.out.println(key + " => " + params.getParams().get(key));
        }
    }
}
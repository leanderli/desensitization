# desensitization（数据脱敏）

### 一、简介

此项目作用于一般 Java 后端项目在返回前端数据时对某些关键字段做技术加工处理，从而实现关键信息脱敏，保障数据安全。（未实现数据库存储加密功能）

### 二、使用范围

Springboot 构建的 Java 后端项目。

### 三、基本原理

1、针对以 Springboot 构建的后端项目，由于 SpringMVC 默认采用了 Jackson 作为其序列化框架，且 Jackson 默认提供了优秀的拓展接口以方便开发者自定义序列化类，所以本项目借助于这两个特性实现了对象的自定义序列化处理，再配合使用注解，更加方便实现了数据返回时的脱敏处理。

2、同时由于 SpringMVC 可替换的序列化框架特性，也实现了使用 FastJson 作为默认序列化框架时的脱敏序列化功能。

### 四、使用方式

1、git clone 本项目，执行 mvn clean install

2、在自己的项目中以 pom 方式引入依赖

```xml
    <dependency>
        <groupId>net.futureorigin</groupId>
        <artifactId>data-desensitization-core</artifactId>
        <version>${version}</version>
    </dependency>
```

3、当是使用默认的 Jackson 为序列化框架时，仅需要在要脱敏的对象属性上加上对应的注解即可，如下：

```java
@Data
public class UserCO extends BaseCO {

    private String userName;

    @SensitiveField(SensitiveFieldType.CN_NAME)
    private String userCnName;
    private String userEnName;

    @SensitiveField(value = SensitiveFieldType.ID_CARD_NO)
    private Long idCardNo;

    @SensitiveField(value = SensitiveFieldType.TELEPHONE)
    private String telephone;

    @SensitiveField(value = SensitiveFieldType.MOBILE)
    private Long mobile;

    @SensitiveField(value = SensitiveFieldType.ADDRESS)
    private String address;

    @SensitiveField(value = SensitiveFieldType.EMAIL)
    private String email;

    @SensitiveField(value = SensitiveFieldType.BANK_CARD_NO)
    private Long bankCardNo;

    @SensitiveField(value = SensitiveFieldType.BANK_COOP_NO)
    private Long bankCoopNo;

    @SensitiveField(value = SensitiveFieldType.COMMON_NO)
    private Long studentNo;

    @SensitiveField(value = CustomSensitiveFieldType.BIRTHDAY)
    private Date birthday;
}
```

4、当使用了 FastJson 为序列化框架时，除了要配置第三步的注解外，还需要在 FastJson 的配置中配置自定义的序列化过滤器，如下：

```java
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1.定义一个converters转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 2.添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        // 【注意】需配置自定义的序列化过滤器
        fastJsonConfig.setSerializeFilters(new FastJsonSensitiveFieldSerializer());
        // 3.在converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4.将converter赋值给HttpMessageConverter
        // 5.返回HttpMessageConverters对象
        return new HttpMessageConverters(fastConverter);
    }
```

5、自定义拓展：

1）可以通过继承对应默认提供的处理器重写父类的 `handle` 方法来覆盖默认的处理方式，如：

```java
public class CustomAddressSensitiveFieldHandler extends AddressSensitiveFieldHandler {

    @Override
    public String handle(Object src) {
        if (Objects.isNull(src)) {
            return null;
        }
        return "CustomAddressSensitiveFieldHandler";
    }
}
```

2）也可以通过直接继承抽象父类 `AbstractSensitiveFieldHandler` 来实现完全自定义的处理器，如：

```java
public class BirthdaySensitiveFieldHandler extends AbstractSensitiveFieldHandler {
    @Override
    public String getSensitiveType() {
        return CustomSensitiveFieldType.BIRTHDAY;
    }

    @Override
    public String handle(Object src) {
        if (Objects.isNull(src)) {
            return null;
        }
        String value = src.toString();
        return value.replaceAll(value, "********");
    }
}
```

使用此种方式时需要注意方法 `getSensitiveType` ，这意味着将注册一种新的处理类型到处理器注册表 `SensitiveFieldHandlerRegistry` 中，与此对应的在对象属性上使用注解 `@SensitiveField` 的值应当与之对应。

上述两种方式最终都需要手动注册到处理器注册表中，如：

```java
        SensitiveFieldHandlerRegistry.getRegistry().registerSensitiveFieldHandler(
                new BirthdaySensitiveFieldHandler(),
                new CommonNoSensitiveFieldHandler()
        );
```


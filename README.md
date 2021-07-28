[TOC]

# desensitization（数据脱敏）

### 一、简介

此项目作用于一般 Java 后端项目在返回前端数据时对某些关键字段做技术加工处理，从而实现关键信息脱敏，保障数据安全。（未实现数据库存储加密功能）

### 二、使用范围

Springboot 构建的 Java 后端项目。

### 三、基本原理

1、针对以 Springboot 构建的后端项目，由于 SpringMVC 默认采用了 Jackson 作为其序列化框架，且 Jackson 默认提供了优秀的拓展接口以方便开发者自定义序列化类，所以本项目借助于这两个特性实现了对象的自定义序列化处理，再配合使用注解，更加方便实现了数据返回时的脱敏处理。

2、同时由于 SpringMVC 可替换的序列化框架特性，也实现了使用 FastJson 作为默认序列化框架时的脱敏序列化功能。

### 四、使用方式

#### 一、框架自动处理

1、git clone 本项目，执行 mvn clean install

2、在自己的项目中以 pom 方式引入依赖

```xml
<dependency>
	<groupId>net.futureorigin</groupId>
	<artifactId>desensitization-core</artifactId>
	<version>${version}</version>
</dependency>
```

3、当是使用默认的 Jackson 为序列化框架时，仅需要在要脱敏的对象属性上加上对应的注解即可，如下：

```java
/**
 * GroupCO
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/26.
 */
@Data
public class GroupCO extends AbstractCO {

    @SensitiveField(value = SensitiveFieldType.CN_NAME, autoProcessing = true)
    private String groupName;

    @SensitiveField(value = SensitiveFieldType.CN_NAME, autoProcessing = true)
    private String groupClass;

    @SensitiveField(value = SensitiveFieldType.CN_NAME, autoProcessing = true)
    private String telephone;

    @SensitiveField(value = SensitiveFieldType.COMMON_NO, autoProcessing = true)
    private Long index;

}

```

> @SensitiveField：标注需要处理的属性字段
>
> value = SensitiveFieldType.CN_NAME：标明该属性字段敏感数据类型
>
> autoProcessing = true：当使用框架自动处理时，需要显式的设置该注解值为 true，默认为 false 框架不自动处理（一些业务需要同一个接口在不同条件下脱敏或者不脱敏，这种场景目前还没办法完全让框架自动处理，只能是在下面的手动处理中实现）
>
>

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

#### 二、手动处理

> 某些场景下往往需要同一个接口返回数据时可以根据某种条件来选择脱敏或者不脱敏，比如默认查询时需要脱敏，当通过身份验证后查询不需要脱敏，由于目前还没有完全实现框架完全自动化的处理方式，所以提供了这种手动处理的做法。

1、在 git clone 本项目并成功执行了 mvn clean install 之后，可以在项目引入如下依赖：

```xml
<dependency>
	<groupId>net.futureorigin</groupId>
	<artifactId>desensitization-spring-boot-starter</artifactId>
	<version>${version}</version>
</dependency>
```

为尽量简化使用方式，实现了 spring-boot-starter，通过直接注入处理类来调用对应函数。

2、注解要处理的对象属性：

```java
/**
 * GroupCO
 * <p></p>
 *
 * @author Leander Lee create on 2021/7/26.
 */
@Data
public class GroupCO extends AbstractCO {

    @SensitiveField(value = SensitiveFieldType.CN_NAME)
    private String groupName;

    @SensitiveField(value = SensitiveFieldType.CN_NAME)
    private String groupClass;

    @SensitiveField(value = SensitiveFieldType.CN_NAME)
    private String telephone;

    @SensitiveField(value = SensitiveFieldType.COMMON_NO)
    private Long index;

}
```

> 注：
>
> 1、由于是手动处理，所以 @SensitiveField 中不需要再设置 autoProcessing 属性；
>
> 2、**使用此种处理方式时，需要注意脱敏字段的类型应当为String，若 Response 对象比较单一，脱敏字段类型则可以不必强制为 String，否则在手动处理时会报 ClassCastException。**

3、Controller 中手动处理：

```java
@RestController@RequestMapping(path = "test")public class DataDesensitizationTestController extends SensitiveHandleController {    @Autowired    private SensitiveObjectHandler sensitiveObjectHandler;    @GetMapping(path = "getUser")    public ResponseEntity<Object> getUser() {        UserCO userCO = generateUser(0);        return ResponseEntity.ok(nonDesensitization()                ? userCO : sensitiveObjectHandler.desensitization(userCO));    }}
```

> 1、自动注入 SensitiveObjectHandler；
>
> 2、在返回数据时手动调用方法处理：sensitiveObjectHandler.desensitization(...)

```java
@RestControllerpublic class SensitiveHandleController {    @Autowired    private HttpServletRequest request;    protected boolean nonDesensitization() {        if (null == request.getHeader("Non-Desensitization")) {            return false;        }        return Boolean.parseBoolean(request.getHeader("Non-Desensitization"));    }}
```

> 此公共 Controller 是为了模拟部分场景脱敏和不脱敏，具体可以查看源码 test 模块的 test 包的测试代码。

4、配置自定义 Handler：

在 springboot 默认的 application.properties 中增加配置即可，例如：

```properties
net.futureorigin.desensitization.additional-handlers=\  net.futureorigin.test.common.CustomAddressSensitiveFieldHandler,\  net.futureorigin.test.common.BirthdaySensitiveFieldHandler
```


package java.lang.annotation;

/**
 * 表示带有类型的注解默认由javadoc和类似的工具来记录。此类型应用于注释类型的声明，这些类型的注释会影响其客户端对已注释元素的使用.
 * 如果一个类型声明被注解为Documented，它的注解就会成为被注解元素的公共API的一部分。
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Documented {
}

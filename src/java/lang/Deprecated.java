package java.lang;

import static java.lang.annotation.ElementType.*;

/**
 * 被注释为Deprecated的程序元素是程序员不建议使用的元素，通常是因为它是危险的，或者因为有更好的替代品存在。
 * 当一个被弃用的程序元素在非弃用的代码中被使用或覆盖时，编译器会发出警告。
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface Deprecated {
}

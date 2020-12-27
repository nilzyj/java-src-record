package java.lang.annotation;

/**
 * 所有注解类型继承的通用接口。请注意，手动继承这个接口并没有定义一个注解类型。
 * 还要注意的是，这个接口本身并没有定义一个注解类型。。
 *
 * 关于注解类型的更多信息，请参见《Java语言规范》第9.6节。
 *
 * java.lang.reflect.AnnotatedElement 接口讨论了将注解类型从不可重复进化到可重复时的兼容性问题。.
 */
public interface Annotation {
    /**
     * 如果指定对象代表的注解在逻辑上等同于这个注解，则返回true。 换句话说，如果指定的对象是一个与这个实例相同的注解类型的实例，
     * 其所有成员都等于这个注解的相应成员，则返回true。如下所述:
     * <ul>
     *    两个相应的基元类型成员，其值是如果x == y，x和y被认为是相等的，除非它们的类型是float或double。
     *
     *    <li>Two corresponding <tt>float</tt> members whose values
     *    are <tt>x</tt> and <tt>y</tt> are considered equal if
     *    <tt>Float.valueOf(x).equals(Float.valueOf(y))</tt>.
     *    (Unlike the <tt>==</tt> operator, NaN is considered equal
     *    to itself, and <tt>0.0f</tt> unequal to <tt>-0.0f</tt>.)
     *
     *    <li>Two corresponding <tt>double</tt> members whose values
     *    are <tt>x</tt> and <tt>y</tt> are considered equal if
     *    <tt>Double.valueOf(x).equals(Double.valueOf(y))</tt>.
     *    (Unlike the <tt>==</tt> operator, NaN is considered equal
     *    to itself, and <tt>0.0</tt> unequal to <tt>-0.0</tt>.)
     *
     *    <li>Two corresponding <tt>String</tt>, <tt>Class</tt>, enum, or
     *    annotation typed members whose values are <tt>x</tt> and <tt>y</tt>
     *    are considered equal if <tt>x.equals(y)</tt>.  (Note that this
     *    definition is recursive for annotation typed members.)
     *
     *    <li>Two corresponding array typed members <tt>x</tt> and <tt>y</tt>
     *    are considered equal if <tt>Arrays.equals(x, y)</tt>, for the
     *    appropriate overloading of {@link java.util.Arrays#equals}.
     * </ul>
     *
     * @return true if the specified object represents an annotation
     *     that is logically equivalent to this one, otherwise false
     */
    boolean equals(Object obj);

    /**
     * 返回此注解的哈希码，定义如下:
     *
     * 注解的哈希码是其成员（包括具有默认值的成员）的哈希码的总和，定义如下。:
     *
     * The hash code of an annotation member is (127 times the hash code
     * of the member-name as computed by {@link String#hashCode()}) XOR
     * the hash code of the member-value, as defined below:
     *
     * 一个成员值的哈希码取决于它的类型:
     * <ul>
     * <li>The hash code of a primitive value <tt><i>v</i></tt> is equal to
     *     <tt><i>WrapperType</i>.valueOf(<i>v</i>).hashCode()</tt>, where
     *     <tt><i>WrapperType</i></tt> is the wrapper type corresponding
     *     to the primitive type of <tt><i>v</i></tt> ({@link Byte},
     *     {@link Character}, {@link Double}, {@link Float}, {@link Integer},
     *     {@link Long}, {@link Short}, or {@link Boolean}).
     *
     * <li>The hash code of a string, enum, class, or annotation member-value
     I     <tt><i>v</i></tt> is computed as by calling
     *     <tt><i>v</i>.hashCode()</tt>.  (In the case of annotation
     *     member values, this is a recursive definition.)
     *
     * <li>The hash code of an array member-value is computed by calling
     *     the appropriate overloading of
     *     {@link java.util.Arrays#hashCode(long[]) Arrays.hashCode}
     *     on the value.  (There is one overloading for each primitive
     *     type, and one for object reference types.)
     * </ul>
     */
    int hashCode();

    /**
     * 返回该注解的字符串表示。表示的详情取决于具体实现，但是一般按照下面的格式表示。
     * <pre>
     *   &#064;com.acme.util.Name(first=Alfred, middle=E., last=Neuman)
     * </pre>
     */
    String toString();

    /**
     * 返回该注解的注解类型
     */
    Class<? extends Annotation> annotationType();
}

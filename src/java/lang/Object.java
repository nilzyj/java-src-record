/*
 * Copyright (c) 1994, 2012, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.lang;

/**
 * Class {@code Object} is the root of the class hierarchy（层级）.
 * Every class has {@code Object} as a superclass. All objects,
 * including arrays, implement the methods of this class.
 *
 * object类是类层级中的顶级，
 * object是所有类的父类。所有的对象，包括数组，都实现了这个类的方法。
 * @author  unascribed
 * @see     java.lang.Class
 * @since   JDK1.0
 */
public class Object {

    /**
     * native说明修饰的是一个原生态方法，方法对应的实现不是在当前文件，
     * 而是在用其他语言（如C和C++）实现的文件中。Java语言本身不能对操作系统底层
     * 进行访问和操作，但是可以通过JNI接口调用其他语言来实现对底层的访问。
     */
    private static native void registerNatives();
    static {
        registerNatives();
    }

    /**
     * Returns the runtime class of this {@code Object}. The returned
     * {@code Class} object is the object that is locked by {@code
     * static synchronized} methods of the represented class.
     * 返回该对象运行时的类。返回的类对象是被其所属类的静态同步方法锁定的对象。
     *
     * <p><b>The actual result type is {@code Class<? extends |X|>}
     * where {@code |X|} is the erasure of the static type of the
     * expression on which {@code getClass} is called.</b> For
     * example, no cast is required in this code fragment:</p>
     *
     * <p>
     * {@code Number n = 0;                             }<br>
     * {@code Class<? extends Number> c = n.getClass(); }
     * </p>
     * 泛型设计中协变/逆变，类型擦除
     * 返回类型Class<? extends |x|>可能跟多态有关，|x|可能跟list有关
     * 实际返回类型是Class<? extends |X|>，其中声明|X|是调用getClass的表达式的静态擦除类型。
     * 例如，以下代码段不需要转换：
     * Number n = 0;
     * Class<? extends Number> c = n.getClass();
     *
     * @return The {@code Class} object that represents the runtime
     *         class of this object.
     * 返回代表该对象运行时的类的类对象
     * @jls 15.8.2 Class Literals
     */
    public final native Class<?> getClass();

    /**
     * object子类大多重写了该方法
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     * 返回对象哈希值。该方法有利于涉及到java.util.HashMap、HashSet的类
     * <p>
     * The general contract of {@code hashCode} is:
     * 要注意的是：
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     *     an execution of a Java application, the {@code hashCode} method
     *     must consistently return the same integer, provided no information
     *     used in {@code equals} comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an
     *     application to another execution of the same application.
     *     在一个java应用运行期间被同一个对象调用多次，hashCode方法会返回一样的整数，
     *     前提是用于比较对象的信息没有被修改。
     *     java应用再次运行时，该整数和之前不一致。
     * <li>If two objects are equal according to the {@code equals(Object)}
     *     method, then calling the {@code hashCode} method on each of
     *     the two objects must produce the same integer result.
     *     equals()方法判断两个对象相等，则这两个对象调用hashCode获得值一定相同。
     * <li>It is <em>not</em> required that if two objects are unequal
     *     according to the {@link java.lang.Object#equals(java.lang.Object)}
     *     method, then calling the {@code hashCode} method on each of the
     *     two objects must produce distinct integer results.  However, the
     *     programmer should be aware that producing distinct integer results
     *     for unequal objects may improve the performance of hash tables.
     *     equals方式判断两个对象不相等不代表hashCode获取的值一定不同。然而，开发者必须意识到
     *     两个不同的对象调用hashCode产生不同的整数结果可能会改善哈希表的性能（表现、行为）
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java&trade; programming language.)
     * 尽可能的合理实际，在object类中定义hashCode方法，对于不相等的对象返回不同的整数。
     * （典型的实现方式是通过将对象内部地址转换为整数，但是这种实现技术并不是Java编程语言所要求的。）
     * @return  a hash code value for this object.返回对象哈希值
     * @see     java.lang.Object#equals(java.lang.Object)
     * @see     java.lang.System#identityHashCode
     */
    public native int hashCode();

    /**
     * Indicates whether some other object is "equal to" this one.
     * 表示两个对象是否相等
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * equals方法与非空对象引用实现了等价关系
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     *     自反性：任何非空值x，x.equals(x)都返回true
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     *     对称性：任何非空值x、y，x.equals(y)返回true，当且仅当y.equals(x)返回true
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     *     传递性：任何非空值x、y、z，如果x.equals(y)返回true，y.equals(z)返回true，
     *     那么x.equals(z)应该返回true
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     *     一致性：任何非空值x、y，多次调用x.equals(y)返回值一致，都为true或都为false，
     *     前提是用于比较对象的信息没有被修改
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     *     任何非空值x，x.equals(null)应该返回false
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * 类对象的equals方法实现对象上最有辨识性的可能的等价关系；对于任何非空值x和y，
     * 该方法返回true当且仅当x和y涉及到相同对象，x == y等于true。
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     * 要注意的是当equals方法被重写时，为了保持和hashCode方法的普遍约定，
     * 该约定规定相等的对象必须有相等的哈希值，因此hashCode方法通常也有必要重写，
     * @param   obj   the reference object with which to compare.
     * @return  {@code true} if this object is the same as the obj
     *          argument; {@code false} otherwise.
     * @see     #hashCode()
     * @see     java.util.HashMap
     */
    public boolean equals(Object obj) {
        return (this == obj);
    }

    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object. The general
     * intent is that, for any object {@code x}, the expression:
     * <blockquote>
     * <pre>
     * x.clone() != x</pre></blockquote>
     * will be true, and that the expression:
     * <blockquote>
     * <pre>
     * x.clone().getClass() == x.getClass()</pre></blockquote>
     * will be {@code true}, but these are not absolute requirements.
     * While it is typically the case that:
     * <blockquote>
     * <pre>
     * x.clone().equals(x)</pre></blockquote>
     * will be {@code true}, this is not an absolute requirement.
     * <p>
     * 创建并返回该对象的复制。复制准确的意思取决于类对象。通常指的是，对于任意对象x，表达式：
     * x.clone() != x是true，对于表达式：
     * x.clone().getClass() == x.getClass()会是true，但这不是绝对的。？？
     * 一个典型情况是：
     * x.clone().equals(x)是true，这不是绝对的。？？
     * By convention, the returned object should be obtained by calling
     * {@code super.clone}.  If a class and all of its superclasses (except
     * {@code Object}) obey this convention, it will be the case that
     * {@code x.clone().getClass() == x.getClass()}.
     * 按照惯例，返回的对象应该通过super.clone调用获取到。如果一个类和所有它的父类(除了Object)
		 * 都遵守这个规则，将满足以下情况，x.clone().getClass() == x.getClass()
     * <p>
     * By convention, the object returned by this method should be independent
     * of this object (which is being cloned).  To achieve this independence,
     * it may be necessary to modify one or more fields of the object returned
     * by {@code super.clone} before returning it.  Typically, this means
     * copying any mutable objects that comprise the internal "deep structure"
     * of the object being cloned and replacing the references to these
     * objects with references to the copies.  If a class contains only
     * primitive fields or references to immutable objects, then it is usually
     * the case that no fields in the object returned by {@code super.clone}
     * need to be modified.
		 * 按照惯例，通过该方法返回的对象（将被克隆的那个）是独立的。为了实现此独立性，
		 * 可能需要在返回该对象之前修改通过super.clone返回对象的一个或多个字段。
		 * 通常，这意味着复制任何组成对象内部“深层结构”的可变对象将被克隆和取代
		 * 涉及到带有复制属性的对象。如果一个类仅仅包含原始属性或者涉及到不可变对象，
		 * 那么通常这个情况下，super.clone返回的对象中的字段都不需要修改。
     * <p>
     * The method {@code clone} for class {@code Object} performs a
     * specific cloning operation. First, if the class of this object does
     * not implement the interface {@code Cloneable}, then a
     * {@code CloneNotSupportedException} is thrown. Note that all arrays
     * are considered to implement the interface {@code Cloneable} and that
     * the return type of the {@code clone} method of an array type {@code T[]}
     * is {@code T[]} where T is any reference or primitive type.
     * Otherwise, this method creates a new instance of the class of this
     * object and initializes all its fields with exactly the contents of
     * the corresponding fields of this object, as if by assignment; the
     * contents of the fields are not themselves cloned. Thus, this method
     * performs a "shallow copy" of this object, not a "deep copy" operation.
		 * Object类的clone方法表现出一种特殊的克隆操作。首先，如果类对象不实现
		 * Cloneable接口，那么会抛出CloneNoSupportedException。要注意的是所有数组都要考虑到
		 * 实现Cloneable接口并且数组类型T[]的clone方法返回类型是T[]，其中T是任何相关和原始类型。
		 * 此外，该方法创建类对象的一个新的实例，并且准确的初始化该对象所有相关字段内容，
		 * 就像是安排好的一样；这些字段内容不是它们自己被克隆的。因此，该方法表现出对象的"隐性复制"，
		 * 而不是“深度复制”（对象字段不是复制的）。
     * <p>
     * The class {@code Object} does not itself implement the interface
     * {@code Cloneable}, so calling the {@code clone} method on an object
     * whose class is {@code Object} will result in throwing an
     * exception at run time.
     *
     * @return     a clone of this instance.
     * @throws  CloneNotSupportedException  if the object's class does not
     *               support the {@code Cloneable} interface. Subclasses
     *               that override the {@code clone} method can also
     *               throw this exception to indicate that an instance cannot
     *               be cloned.
     * @see java.lang.Cloneable
     */
    protected native Object clone() throws CloneNotSupportedException;

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * 返回对象字符串形式。通常toString方法返回“文本表示”对象的字符串。
     * 结果理应是简洁但信息的表示是易读的。
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     * Object类的toString方法返回一个由对象是一个实例的类名、@符号和该对象哈希值的
     * 无符号十六进制。换句话说，该方法返回一个字符串，这个字符串等于:
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * @return  a string representation of the object.
     */
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    /**
     * Wakes up a single thread that is waiting on this object's
     * monitor. If any threads are waiting on this object, one of them
     * is chosen to be awakened. The choice is arbitrary and occurs at
     * the discretion of the implementation. A thread waits on an object's
     * monitor by calling one of the {@code wait} methods.
     * 唤醒一个正在等待该对象监听器的单一线程。如果任何线程正在等待该对象，其中之一
     * 会被选择唤醒。选择是随机的，由实现自行决定执行。一个线程通过调用wait方法之一
     * 等待一个对象的监听器。
     * <p>
     * The awakened thread will not be able to proceed until the current
     * thread relinquishes the lock on this object. The awakened thread will
     * compete in the usual manner with any other threads that might be
     * actively competing to synchronize on this object; for example, the
     * awakened thread enjoys no reliable privilege or disadvantage in being
     * the next thread to lock this object.
     * 唤醒线程不能执行直到当前线程释放对象锁。唤醒线程将以通常的方式与可能在
     * 该对象上进行同步竞争的任何其他线程进行竞争；例如，唤醒线程没有可靠的特权
     * 或在下一个线程中锁定该对象处于劣势。
     * <p>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. A thread becomes the owner of the
     * object's monitor in one of three ways:
     * <ul>
     * <li>By executing a synchronized instance method of that object.
     * <li>By executing the body of a {@code synchronized} statement
     *     that synchronizes on the object.
     * <li>For objects of type {@code Class,} by executing a
     *     synchronized static method of that class.
     * </ul>
     * 该方法只应该被对象监听器拥有者的线程调用。一个线程成为对象监听器拥有者的三种方式：
     * 执行对象同步实例方法。
     * 执行同意对象的synchronized状态体。
     * 对于Class类型对象，执行类方法的同步静态方法。
     * <p>
     * Only one thread at a time can own an object's monitor.
     * 同时只能有一个线程拥有对象监听器。
     *
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of this object's monitor.
     * @see        java.lang.Object#notifyAll()
     * @see        java.lang.Object#wait()
     */
    public final native void notify();

    /**
     * Wakes up all threads that are waiting on this object's monitor. A
     * thread waits on an object's monitor by calling one of the
     * {@code wait} methods.
     * 唤醒所有等待对象监听器的线程。通过调用wait方法使一个线程等待对象监听器。
     * <p>
     * The awakened threads will not be able to proceed until the current
     * thread relinquishes the lock on this object. The awakened threads
     * will compete in the usual manner with any other threads that might
     * be actively competing to synchronize on this object; for example,
     * the awakened threads enjoy no reliable privilege or disadvantage in
     * being the next thread to lock this object.
     * 唤醒线程不能执行直到当前线程释放对象锁。唤醒线程将以通常的方式与可能在
     * 该对象上进行同步竞争的任何其他线程进行竞争；例如，唤醒线程没有可靠的特权
     * 或在下一个线程中锁定该对象处于劣势。
     * <p>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of this object's monitor.
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#wait()
     */
    public final native void notifyAll();

    /**
     * Causes the current thread to wait until either another thread invokes the
     * {@link java.lang.Object#notify()} method or the
     * {@link java.lang.Object#notifyAll()} method for this object, or a
     * specified amount of time has elapsed.
     * 使当前线程等待直到任一其它线程为该对象调用java.lang.Object#notify()或者
     * java.lang.Object#notifyAll()，或过去特定时间。
     * <p>
     * The current thread must own this object's monitor.
     * 当前线程必须拥有该对象监听器。
     * <p>
     * This method causes the current thread (call it <var>T</var>) to
     * place itself in the wait set for this object and then to relinquish
     * any and all synchronization claims on this object. Thread <var>T</var>
     * becomes disabled for thread scheduling purposes and lies dormant
     * until one of four things happens:
     * 该方法当前线程（称作T）将自己放到此对象的等待集合，然后放弃该对象中任何和所有
     * 同步声明。为了线程调度目的，线程T被禁用，并且处于休眠状态，直到以下发生四件事之一：
     * <ul>
     * <li>Some other thread invokes the {@code notify} method for this
     * object and thread <var>T</var> happens to be arbitrarily chosen as
     * the thread to be awakened.
     * 一些其他的线程为该对象调用了notify方法，并且线程T恰好被选择作为唤醒的线程。
     * <li>Some other thread invokes the {@code notifyAll} method for this
     * object.
     * 一些其他的线程为该对象调用了notifyAll方法。
     * <li>Some other thread {@linkplain Thread#interrupt() interrupts}
     * thread <var>T</var>.
     * 一些其他的线程中断了线程T。
     * <li>The specified amount of real time has elapsed, more or less.  If
     * {@code timeout} is zero, however, then real time is not taken into
     * consideration and the thread simply waits until notified.
     * 过去特定的时间后。如果timeout是0，然而，真正的时间没被考虑进去，
     * 线程一直等待直到被通知。
     * </ul>
     * The thread <var>T</var> is then removed from the wait set for this
     * object and re-enabled for thread scheduling. It then competes in the
     * usual manner with other threads for the right to synchronize on the
     * object; once it has gained control of the object, all its
     * synchronization claims on the object are restored to the status quo
     * ante - that is, to the situation as of the time that the {@code wait}
     * method was invoked. Thread <var>T</var> then returns from the
     * invocation of the {@code wait} method. Thus, on return from the
     * {@code wait} method, the synchronization state of the object and of
     * thread {@code T} is exactly as it was when the {@code wait} method
     * was invoked.
     * 线程T从等待集合中移除，为了线程调度而重新可用。然后按照通常的方式和其他线程竞争对象的同步权；
     * 一旦它获得对象的控制权，所有它的对象同步声明被恢复到现状。
     * <p>
     * A thread can also wake up without being notified, interrupted, or
     * timing out, a so-called <i>spurious wakeup</i>.  While this will rarely
     * occur in practice, applications must guard against it by testing for
     * the condition that should have caused the thread to be awakened, and
     * continuing to wait if the condition is not satisfied.  In other words,
     * waits should always occur in loops, like this one:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait(timeout);
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * (For more information on this topic, see Section 3.2.3 in Doug Lea's
     * "Concurrent Programming in Java (Second Edition)" (Addison-Wesley,
     * 2000), or Item 50 in Joshua Bloch's "Effective Java Programming
     * Language Guide" (Addison-Wesley, 2001).
     *
     * <p>If the current thread is {@linkplain java.lang.Thread#interrupt()
     * interrupted} by any thread before or while it is waiting, then an
     * {@code InterruptedException} is thrown.  This exception is not
     * thrown until the lock status of this object has been restored as
     * described above.
     *
     * <p>
     * Note that the {@code wait} method, as it places the current thread
     * into the wait set for this object, unlocks only this object; any
     * other objects on which the current thread may be synchronized remain
     * locked while the thread waits.
     * <p>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @param      timeout   the maximum time to wait in milliseconds.
     * @throws  IllegalArgumentException      if the value of timeout is
     *               negative.
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of the object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#notifyAll()
     */
    public final native void wait(long timeout) throws InterruptedException;

    /**
     * Causes the current thread to wait until another thread invokes the
     * {@link java.lang.Object#notify()} method or the
     * {@link java.lang.Object#notifyAll()} method for this object, or
     * some other thread interrupts the current thread, or a certain
     * amount of real time has elapsed.
     * <p>
     * This method is similar to the {@code wait} method of one
     * argument, but it allows finer control over the amount of time to
     * wait for a notification before giving up. The amount of real time,
     * measured in nanoseconds, is given by:
     * <blockquote>
     * <pre>
     * 1000000*timeout+nanos</pre></blockquote>
     * <p>
     * In all other respects, this method does the same thing as the
     * method {@link #wait(long)} of one argument. In particular,
     * {@code wait(0, 0)} means the same thing as {@code wait(0)}.
     * <p>
     * The current thread must own this object's monitor. The thread
     * releases ownership of this monitor and waits until either of the
     * following two conditions has occurred:
     * <ul>
     * <li>Another thread notifies threads waiting on this object's monitor
     *     to wake up either through a call to the {@code notify} method
     *     or the {@code notifyAll} method.
     * <li>The timeout period, specified by {@code timeout}
     *     milliseconds plus {@code nanos} nanoseconds arguments, has
     *     elapsed.
     * </ul>
     * <p>
     * The thread then waits until it can re-obtain ownership of the
     * monitor and resumes execution.
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are
     * possible, and this method should always be used in a loop:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait(timeout, nanos);
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @param      timeout   the maximum time to wait in milliseconds.
     * @param      nanos      additional time, in nanoseconds range
     *                       0-999999.
     * @throws  IllegalArgumentException      if the value of timeout is
     *                      negative or the value of nanos is
     *                      not in the range 0-999999.
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of this object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     */
    public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }

        if (nanos > 0) {
            timeout++;
        }

        wait(timeout);
    }

    /**
     * Causes the current thread to wait until another thread invokes the
     * {@link java.lang.Object#notify()} method or the
     * {@link java.lang.Object#notifyAll()} method for this object.
     * In other words, this method behaves exactly as if it simply
     * performs the call {@code wait(0)}.
     * <p>
     * The current thread must own this object's monitor. The thread
     * releases ownership of this monitor and waits until another thread
     * notifies threads waiting on this object's monitor to wake up
     * either through a call to the {@code notify} method or the
     * {@code notifyAll} method. The thread then waits until it can
     * re-obtain ownership of the monitor and resumes execution.
     * <p>
     * As in the one argument version, interrupts and spurious wakeups are
     * possible, and this method should always be used in a loop:
     * <pre>
     *     synchronized (obj) {
     *         while (&lt;condition does not hold&gt;)
     *             obj.wait();
     *         ... // Perform action appropriate to condition
     *     }
     * </pre>
     * This method should only be called by a thread that is the owner
     * of this object's monitor. See the {@code notify} method for a
     * description of the ways in which a thread can become the owner of
     * a monitor.
     *
     * @throws  IllegalMonitorStateException  if the current thread is not
     *               the owner of the object's monitor.
     * @throws  InterruptedException if any thread interrupted the
     *             current thread before or while the current thread
     *             was waiting for a notification.  The <i>interrupted
     *             status</i> of the current thread is cleared when
     *             this exception is thrown.
     * @see        java.lang.Object#notify()
     * @see        java.lang.Object#notifyAll()
     */
    public final void wait() throws InterruptedException {
        wait(0);
    }

    /**
     * Called by the garbage collector on an object when garbage collection
     * determines that there are no more references to the object.
     * A subclass overrides the {@code finalize} method to dispose of
     * system resources or to perform other cleanup.
     * <p>
     * The general contract of {@code finalize} is that it is invoked
     * if and when the Java&trade; virtual
     * machine has determined that there is no longer any
     * means by which this object can be accessed by any thread that has
     * not yet died, except as a result of an action taken by the
     * finalization of some other object or class which is ready to be
     * finalized. The {@code finalize} method may take any action, including
     * making this object available again to other threads; the usual purpose
     * of {@code finalize}, however, is to perform cleanup actions before
     * the object is irrevocably discarded. For example, the finalize method
     * for an object that represents an input/output connection might perform
     * explicit I/O transactions to break the connection before the object is
     * permanently discarded.
     * <p>
     * The {@code finalize} method of class {@code Object} performs no
     * special action; it simply returns normally. Subclasses of
     * {@code Object} may override this definition.
     * <p>
     * The Java programming language does not guarantee which thread will
     * invoke the {@code finalize} method for any given object. It is
     * guaranteed, however, that the thread that invokes finalize will not
     * be holding any user-visible synchronization locks when finalize is
     * invoked. If an uncaught exception is thrown by the finalize method,
     * the exception is ignored and finalization of that object terminates.
     * <p>
     * After the {@code finalize} method has been invoked for an object, no
     * further action is taken until the Java virtual machine has again
     * determined that there is no longer any means by which this object can
     * be accessed by any thread that has not yet died, including possible
     * actions by other objects or classes which are ready to be finalized,
     * at which point the object may be discarded.
     * <p>
     * The {@code finalize} method is never invoked more than once by a Java
     * virtual machine for any given object.
     * <p>
     * Any exception thrown by the {@code finalize} method causes
     * the finalization of this object to be halted, but is otherwise
     * ignored.
     *
     * @throws Throwable the {@code Exception} raised by this method
     * @see java.lang.ref.WeakReference
     * @see java.lang.ref.PhantomReference
     * @jls 12.6 Finalization of Class Instances
     */
    protected void finalize() throws Throwable { }
}

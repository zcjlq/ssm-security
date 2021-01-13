package net.wendal.nutzbook;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

/**
 * http://nutzbook.wendal.net/more_configure/fail.html
 */
@SetupBy(value = MainSetup.class)
// 请注意星号!!不要拷贝少了
@IocBy(type = ComboIocProvider.class, args = {"*js", "ioc/",
        // 这个package下所有带@IocBean注解的类,都会登记上
        "*anno", "net.wendal.nutzbook",
        "*tx", // 事务拦截 aop
        "*async"}) // 异步执行aop
@Modules(scanPackage = true)
public class MainModule {
}

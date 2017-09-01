# Smart-framework

## 简介
- 参考黄勇先生的《架构探险》
- 融合MVC，AOP，IOC，DAO等特性
- 使用注解简化配置

## 框架实现
- 工程结构
```
smart-framework/
　　┗ src/
　　　　┗ main/
　　　　　　┗ java/
　　　　　　　　┗ org/
　　　　　　　　　　┗ mart4j/
　　　　　　　　　　　　┗ framework/
　　　　　　　　　　　　　　┗ annotation/    定义注解，包括Controller、Service、Inject、Action、Transaction、Aspect
　　　　　　　　　　　　　　┗ bean/          定义bean，包括用于封装请求参数的Param、封装响应视图View或数据Data、请求对象Request、请求处理Handler映射
　　　　　　　　　　　　　　┗ helper/        定义助手类，包括Bean助手类（保存Bean对象实例）、IOC助手类（将Bean实例注入）、Class助手类（用于类加载）、
　　　　　　　　　　　　　　┗ proxy/         定义AOP工具类，依靠代理链模式生成动态代理类，替换BeanHelper中的实例
　　　　　　　　　　　　　　┗ util/          定义各种工具类，包括类加载器、类型转换工具类、反射工具类、集合类工具等
　　　　　　┗ resources/                   包括一个Log4j的配置文件
　　┗ pom.xml
```
- IOC的实现
1. Bean的集中式管理，只有一种作用范围——单例
2. 在请求转发器启动并初始化时，扫描Web项目中的所有Bean类，并生成Bean实例集中存放在BeanHelper中
3. 为带有@Inject注解的Bean实例的属性注入相应的实例对象

- AOP的实现
1. 基于代理链模式，生成动态代理类，并实例化，最终替换BeanHelper中Bean实例

- 请求转发器


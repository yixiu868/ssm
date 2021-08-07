## 生命周期

### BeanFactory生命周期

![20210807113139](img\20210807113139.png)

* 1、InstantiationAwareBeanPostProcessor是容器级接口；
* 2、BeanPostProcessor接口也是容器级的，该接口方法对Bean进行加工操作。
  * **入参**：当前正在处理的Bean，beanName是当前Bean配置名，**返回对象**：为加工处理后的Bean。
  * **作用**：BeanPostProcessor在Spring框架中占有重要的地位，为容器提供对Bean进行后续加工处理的切入点，Spring容器所提供的各种“神奇功能”（如AOP、动态代理等）都通过BeanPostProcessor实施。

### ApplicationContext生命周期

![20210807123120](img\20210807123120.png)
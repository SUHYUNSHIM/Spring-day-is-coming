package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        ac.getBean("beanB",BeanB.class);
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                              () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(includeFilters = @Filter( classes = MyIncludeComponent.class),
                   excludeFilters = @Filter( classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}

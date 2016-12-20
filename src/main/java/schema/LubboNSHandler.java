package schema;


import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class LubboNSHandler extends NamespaceHandlerSupport{
    @Override
    public void init() {
        registerBeanDefinitionParser("lubbo", new LubboBeanDefinitionParser());
    }
}

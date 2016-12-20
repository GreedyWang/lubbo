package schema;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;


public class LubboBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {



    @Override
    protected Class<?> getBeanClass(Element element) {
        return Lubbo.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String _interface = element.getAttribute("interface");
        String _ref = element.getAttribute("ref");
        String id = element.getAttribute("id");
        if(StringUtils.hasText(id)) {
            builder.addPropertyValue("id", id);
        }
        if(StringUtils.hasText(_interface)) {
            builder.addPropertyValue("_interface", _interface);
        }
        if(StringUtils.hasText(_ref)) {
            builder.addPropertyValue("_ref", _ref);
        }

    }
}

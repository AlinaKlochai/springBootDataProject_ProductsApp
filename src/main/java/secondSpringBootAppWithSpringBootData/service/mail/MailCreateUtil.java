package secondSpringBootAppWithSpringBootData.service.mail;


import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class MailCreateUtil {

    private final Configuration freemakerConfiguration;

    public String createConfirmationMail(String name,  String link) {
        try{
            Template template = freemakerConfiguration.getTemplate("confirm_registration_mail.ftlh");
            Map<Object,Object> model = new HashMap<>();
            model.put("name", name);
            model.put("link", link);

            return FreeMarkerTemplateUtils.processTemplateIntoString(template,model);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}

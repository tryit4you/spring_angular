package pilab.com.takeleaf.Utility;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import pilab.com.takeleaf.models.AppUser;


public class EmailConstructor {
    @Autowired
    private Environment env;

    @Autowired
    private TemplateEngine templateEngine;
    
    
    public MimeMessagePreparator constructNewUserMail(AppUser user,String password){
        Context context =new Context();
        context.setVariable("user", user);
        context.setVariable("password", password);
        String text=templateEngine.process("newUserEmailTemplate", context);

        MimeMessagePreparator messagePreparator=new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception{
                MimeMessageHelper helper =new MimeMessageHelper(mimeMessage);
                helper.setPriority(1);
                helper.setTo(user.getEmail());
                helper.setSubject("Tín hiệu từ vũ trụ");
                helper.setText(text, true);
                helper.setFrom(new InternetAddress(env.getProperty("support.emal")));
            }
            
        };
        return messagePreparator;
    }
   
    public MimeMessagePreparator constructUpdateUserMail(AppUser user){
        Context context =new Context();
        context.setVariable("user", user);
        String text=templateEngine.process("newUserEmailTemplate", context);

        MimeMessagePreparator messagePreparator=new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception{
                MimeMessageHelper helper =new MimeMessageHelper(mimeMessage);
                helper.setPriority(1);
                helper.setTo(user.getEmail());
                helper.setSubject("Tín hiệu từ vũ trụ");
                helper.setText(text, true);
                helper.setFrom(new InternetAddress(env.getProperty("support.emal")));
            }
            
        };
        return messagePreparator;
    }
}

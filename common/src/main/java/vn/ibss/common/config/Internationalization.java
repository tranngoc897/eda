package vn.ibss.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class Internationalization extends WebMvcConfigurerAdapter {

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /*
    @Autowired
    MessageSource messageSource;
   Usage:  messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
    or: messageSource.getMessage(String key, @Nullable Object[] params, Locale locale)

    The first arg to the getMessage() holds the message key as specified in the properties file, e.g “good.morning”.
    The second arg (params) is an array of Objects. You could pass a null value here. However,
    this becomes useful should you want to pass objects or values from the code to the messages.properties file.
     For example, if you want to pass the person’s name from the code to the properties file.
     First, we define the key in the properties file for the different languages as such:
    good.morning.name={0} Good morning {1}
    So that when an array of Objects(say Strings) is passed to the getMessage() method,
     the first element of the array replaces {0} and the second element replaces {1}. For example:
    String[] params= new String[]{“Ikhiloya”, “today”}
    */

}

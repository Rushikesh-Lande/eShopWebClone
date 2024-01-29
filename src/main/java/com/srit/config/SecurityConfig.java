/*
 * package com.srit.config;
 * 
 * import java.util.concurrent.TimeUnit;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Qualifier; import
 * org.springframework.boot.web.servlet.FilterRegistrationBean; import
 * org.springframework.boot.web.servlet.ServletRegistrationBean; import
 * org.springframework.context.MessageSource; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
 * import org.springframework.core.env.Environment; import
 * org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
 * import
 * org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
 * import
 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 * import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
 * import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebMvcConfigurerAdapter {
 * 
 * private Long hitCounter;
 * 
 * @Autowired Environment env;
 * 
 * @Autowired private MessageSource messageSource;
 * 
 * public SecurityConfig() { this.hitCounter = 0L; }
 * 
 * 
 * public void addViewControllers(final ViewControllerRegistry registry) {
 * super.addViewControllers(registry); System.out.println(
 * "**********************************1************************************************"
 * ); registry.addViewController("/login").setViewName("public/login");
 * registry.addRedirectViewController("/", "/home"); }
 * 
 * @Bean public SpringSecurityDialect securityDialect() { return new
 * SpringSecurityDialect(); }
 * 
 * @Bean public ClassLoaderTemplateResolver emailTemplateResolver() { final
 * ClassLoaderTemplateResolver emailTemplateResolver = new
 * ClassLoaderTemplateResolver();
 * emailTemplateResolver.setPrefix("email-templates/");
 * emailTemplateResolver.setSuffix(".html");
 * emailTemplateResolver.setTemplateMode("HTML5");
 * emailTemplateResolver.setCharacterEncoding("UTF-8");
 * emailTemplateResolver.setOrder(Integer.valueOf(2)); return
 * emailTemplateResolver; }
 * 
 * @Bean public static PropertySourcesPlaceholderConfigurer
 * propertySourcesPlaceholderConfigurer() { return new
 * PropertySourcesPlaceholderConfigurer(); }
 * 
 * @Bean reactor.Environment env() { return new reactor.Environment(); }
 * 
 * @Bean(name = { "eventBus" }) EventBus crateeventBus(final reactor.Environment
 * env) { return EventBus.create(env, this.createDispatcher()); }
 * 
 * @Bean Dispatcher createDispatcher() { final WorkQueueDispatcher d = new
 * WorkQueueDispatcher("multThreadedQueueDispatcher1",
 * (int)this.env.getProperty("reactor.poolsize", (Class)Integer.class,
 * (Object)200), (int)this.env.getProperty("reactor.blocklog",
 * (Class)Integer.class, (Object)2048), (Consumer)null); return (Dispatcher)d; }
 * 
 * @Bean UpiGCMRequestApi getupiMessageApi() { final OkHttpClient okHttpClient =
 * new OkHttpClient(); okHttpClient.setReadTimeout(160L, TimeUnit.SECONDS);
 * okHttpClient.setConnectTimeout(10L, TimeUnit.SECONDS);
 * okHttpClient.setConnectionPool(new ConnectionPool(5, 5000L));
 * okHttpClient.setHostnameVerifier(SSLHelper.getHostnameVerifier());
 * okHttpClient.setSslSocketFactory(SSLHelper.getSocketFactory()); final
 * Retrofit server = new
 * Retrofit.Builder().baseUrl(this.env.getProperty("ext_host2")).
 * addConverterFactory((Converter.Factory)GsonConverterFactory.create()).client(
 * okHttpClient).build(); final UpiGCMRequestApi upiserver =
 * (UpiGCMRequestApi)server.create((Class)UpiGCMRequestApi.class); return
 * upiserver; }
 * 
 * }
 */
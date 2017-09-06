package kuber.netes.main;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;

public abstract class AbstractJavaFxApplicationSupport  extends Application{

    private static String[] savedArgs;

    protected ConfigurableApplicationContext context;

    /**
     * see http://developers-club.com/posts/265511/
     * 
     * 1.At the time of initialization of JavaFX we start initialization of Spring of a context
     * 2.Well and the next line the current object is filled with bins
     * 3.Inheriting the abstract class described above, we will specify behavior of our JavaFX of the application. At this stage we can already use DI and all other "buns" of a spring
     */
    @Override
    public void init() throws Exception {
        context = SpringApplication.run(getClass(), savedArgs);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }
    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> clazz, String[] args) {
        AbstractJavaFxApplicationSupport .savedArgs = args;
        Application.launch(clazz, args);
    }
    
}

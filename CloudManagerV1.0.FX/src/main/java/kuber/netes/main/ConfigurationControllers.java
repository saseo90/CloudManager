package kuber.netes.main;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * 1.스프링 설정파일
 * 2.view(fxml) 을 지정한다.
 * @author LEE
 * @author Ruslan Molchanov (ruslanys@gmail.com)
 * @author http://mruslan.com
 */
@Configuration
public class ConfigurationControllers {
    
    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его произвести все необходимые инъекции.
     */
    public MainController getMainController() throws IOException {
        return (MainController) getMainView().getController();
    }
    
    @Bean(name = "mainView")
    public View getMainView() throws IOException {
        return loadView("/kuber/netes/main/cont.fxml");
    }
        
    /**
     * Самый обыкновенный способ использовать FXML загрузчик.
     * Как раз-таки на этом этапе будет создан объект-контроллер,
     * произведены все FXML инъекции и вызван метод инициализации контроллера.
     */
    protected View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getResource(url).openStream();
            FXMLLoader loader = new FXMLLoader();
            System.out.println("1 : "+url);
            System.out.println(getClass().getResource(url).openStream());
            loader.load(fxmlStream);
            System.out.println("2");
            return new View(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }
    
    /**
     * Класс - оболочка: контроллер мы обязаны указать в качестве бина,
     * а view - представление, нам предстоит использовать в точке входа {@link Application}.
     */
    public class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }
    }
}

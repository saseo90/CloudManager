package cloud.manager.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @Lazy 지연로딩 : 실제 사용할 때 로딩한다.
 *
 */
@Lazy
@SpringBootApplication
public class MainApplication extends AbstractJavaFxApplicationSupport {

    @Qualifier("mainView")
    @Autowired private ConfigurationControllers.View view;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(view.getView());
        primaryStage.setTitle("쿠버네티스 실습");//제목이식
        primaryStage.setScene(scene);//화면이식
        primaryStage.centerOnScreen();//중앙배치
        primaryStage.show();//출력
    }

    public static void main(String[] args) {
        launchApp(MainApplication.class, args);
    }

}

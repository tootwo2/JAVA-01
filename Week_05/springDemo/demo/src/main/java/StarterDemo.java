import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 演示starter
 *
 * @Author tootwo2
 * @Date 2021/2/20 11:57 下午
 */
@EnableAutoConfiguration
@SpringBootConfiguration
public class StarterDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StarterDemo.class, args);

        Student student = context.getBean(Student.class);
        System.out.println(student.getName());
    }
}

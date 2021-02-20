import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过Annotation装配bean
 *
 * @Author tootwo2
 * @Date 2021/2/20 11:42 下午
 */
public class Demo1 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        Student student = context.getBean(Student.class);

        System.out.println(student.getName());

    }

    public static class AppConfig {

        @Bean
        public Student getStudent1() {
            return new Student("s1", 10);
        }

    }
}

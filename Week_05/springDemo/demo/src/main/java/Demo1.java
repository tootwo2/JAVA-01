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
        context.registerBean("student2", Student.class, () -> new Student("student2", 1));
        context.refresh();

        Student student1 = context.getBean("student1", Student.class);
        Student student2 = context.getBean("student2", Student.class);

        System.out.println(student1.getName());
        System.out.println(student2.getName());

    }

    public static class AppConfig {

        @Bean("student1")
        public Student getStudent1() {
            return new Student("student1", 10);
        }

    }
}

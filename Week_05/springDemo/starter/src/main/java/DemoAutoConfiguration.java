import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 演示starter
 *
 * @Author tootwo2
 * @Date 2021/2/20 11:56 下午
 */
@Configuration
public class DemoAutoConfiguration {

    @Bean
    public Student getStudent() {

        return new Student("starter", 99);
    }
}

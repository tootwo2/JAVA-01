import lombok.Data;

/**
 * TODO
 *
 * @Author tootwo2
 * @Date 2021/2/20 11:41 下午
 */
@Data
public class Student {

    private String name;

    private Integer age;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

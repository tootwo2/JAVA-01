package 作业2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class CustomerClassLoader extends ClassLoader {

  public static void main(String[] args) {
    try {
      Class clazz = new CustomerClassLoader().findClass("Hello.xlass");
      Object object = clazz.getDeclaredConstructor().newInstance();
      Method method = clazz.getMethod("hello");
      method.invoke(object);
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {

    byte[] bs = getClassBytes(name);

    return defineClass("Hello", bs, 0, bs.length);

  }

  private byte[] getClassBytes(String fileName) throws ClassNotFoundException {
    String path = System.getProperty("user.dir") + File.separator + "作业2" + File.separator + fileName;
    // path += className.replace('.', File.separatorChar) + ".class";
    System.out.println(path);

    try (FileInputStream fis = new FileInputStream(path)) {
      byte[] bs = new byte[fis.available()];
      fis.read(bs);

      for (int i = 0; i < bs.length; i++) {

        bs[i] = (byte) (255 - bs[i]);
      }
      return bs;
    } catch (IOException e) {
      throw new ClassNotFoundException();
    }

  }
}

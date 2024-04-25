package week_2.Serialization;

import java.io.*;
import java.util.Base64;

public class example {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // User 객체 생성
        User user = new User("sanha", 30,100);

        // 객체 직렬화
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.close();

        // 직렬화된 데이터 출력
        byte[] serializedUser = byteArrayOutputStream.toByteArray();
        String serializedUserBase64 = Base64.getEncoder().encodeToString(serializedUser);
        System.out.println("Serialized User: " + serializedUserBase64);
        //rO0ABXNyABl3ZWVrXzIuU2VyaWFsaXphdGlvbi5Vc2Vyd2LPqxPHZ98CAAJJAANhZ2VMAARuYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7eHAAAAAedAAFc2FuaGE=


        // 바이트 배열에서 객체로 역직렬화
        serializedUser = Base64.getDecoder().decode(serializedUserBase64);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedUser);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object objectUser = objectInputStream.readObject();
        User deserializedUser = (User) objectUser;
        objectInputStream.close();

        // 역직렬화된 객체 출력

        System.out.println("Deserialized User: " + deserializedUser);
        //Deserialized User: User{name='sanha', age=30, weight=0}
    }
}
// Serializable 해당 클래스의 객체가 직렬화될 수 있다는 것을 나타낸다. 없으면 에러
class User implements Serializable {
    private static final long serialVersionUID = 1L; // serialVersionUID 명시적으로 지정
    private String name;
    private int age;

    //transient -> 값을 제외하며 null이 들어간다.
    private transient int weight;

    public User(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}

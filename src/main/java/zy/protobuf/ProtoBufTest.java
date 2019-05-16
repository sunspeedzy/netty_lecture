package zy.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("张三").setAge(20).setAddress("北京")
                .build();

        byte[] student2ByteArray = student.toByteArray();  // 对象转换成 字节数组（序列化），字节数组内容可以在网络上传输

        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray); // 把字节数组再转换为 对象（反序列化）

        System.out.println(student2);
//        输出结果有字符串的转义
//        name: "\345\274\240\344\270\211"
//        age: 20
//        address: "\345\214\227\344\272\254"
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
//        输出结果
//        张三
//        20
//        北京
    }
}

import java.io.*;
/*
 * author:Tammy Pi
 * function:对象的串行化
 */
public class SerializableTest implements Serializable{

    class Test implements Serializable{

        private String username;
        //表明password不是对象串行化的一部分
        private transient String password;

        public Test(String username,String password){

            this.username = username;
            this.password = password;
        }

        public String toString() {

            return username+","+password;
        }
    }

    public void test(){

        Test t = new Test("tammypi","1988");
        System.out.println(t.toString());

        //将t持久化到硬盘
        ObjectOutputStream os = null;
        ObjectInputStream oi = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream("c:\\Test.out"));
            os.writeObject(t);
            os.flush();
            os.close();
            os = null;

            oi = new ObjectInputStream(new FileInputStream("c:\\Test.out"));
            try {
                Test t1 = (Test) oi.readObject();
                System.out.println(t1.toString());
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{

            if(os!=null){

                try {
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(oi!=null){

                try {
                    oi.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){

        SerializableTest s = new SerializableTest();
        s.test();
    }
}

package com.wenyize.thrift;

import com.wenyize.thrift.generated.Person;
import com.wenyize.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {

    public static void main(String[] args) {

        /*
        I had the same issue. Replacing "localhost" with the ip fixed it.
          The reason was: Python used TCPV6, where Java used TCP.
         */
        TTransport transport = new TFramedTransport(new TSocket("127.0.0.1",8899),600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try{

            transport.open();
            Person person = client.getPersonByUserName("张三");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            System.out.println("-------------------");

            Person person1 = new Person();
            person1.setUsername("李四");
            person1.setAge(22);
            person1.setMarried(true);

            client.savePerson(person1);

        }catch (Exception ex){
            ex.printStackTrace();

        }finally {

        }
    }
}

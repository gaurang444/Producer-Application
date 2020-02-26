package com.gaurang.myproject;

import com.google.gson.Gson;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class MysqlPostMappingController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingKey;

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
    static JdbcTemplate jdbcTemplate;

    public void dbrun(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/javalearndb");
        ds.setUsername("root");
        ds.setPassword("Pass@123");
        jdbcTemplate = new JdbcTemplate(ds);
    }


    public void db_opeartions(MainObject msg){
        dbrun();
        String orderid=msg.getOrderid();
        List<Products> products=msg.getProducts();
        Recipent rp=msg.getRp();
        Contact ct=rp.getContacts();
        String cname=ct.getName();
        String cphone=ct.getPhone();
        String cemail=ct.getEmail();
        List<Address> adaray=rp.getAdres();

        //String addressid = UUID.randomUUID().toString();
        String rpid = UUID.randomUUID().toString();
        //JdbcInsert dto=new JdbcInsert();
        for(int i=0;i<products.size();i++){
            String name=products.get(i).getName();
            String price=products.get(i).getPrice();
            //System.out.println(name + " " + price);
            int x5=jdbcTemplate.update(
                    "insert into products (orderid,rpid,name,price) values(?,?,?,?)",
                    orderid,rpid,name,price);
            if(x5!=1){
                System.out.println("error");
            }

        }




		/*(int x6=jdbcTemplate.update(
				"insert into recipents (rpid,name,email,phone,addressid,orderid) values(?,?,?,?,?,?)",
				rpid,cname,cemail,cphone,addressid,orderid);
		if(x6!=1){
			System.out.println("error");
		}

		 */



        for(int i=0;i<adaray.size();i++){
            String adid = UUID.randomUUID().toString();

            String a1=adaray.get(i).getAddressType();
            String a2=adaray.get(i).getLine1();
            String a3=adaray.get(i).getLine2();
            String a4=adaray.get(i).getCity();
            String a5=adaray.get(i).getState();
            String a6=adaray.get(i).getCountry();
            int x7=jdbcTemplate.update(
                    "insert into address (addressid,addresstype,line1,line2,city,state,country,rpid) values(?,?,?,?,?,?,?,?)",
                    adid,a1,a2,a3,a4,a5,a6,rpid);
            if(x7!=1){
                System.out.println("error");
            }
            int x6=jdbcTemplate.update(
                    "insert into recipents (rpid,name,email,phone,addressid,orderid) values(?,?,?,?,?,?)",
                    rpid,cname,cemail,cphone,adid,orderid);
            if(x6!=1){
                System.out.println("error");
            }

        }









    }
    @PostMapping(path = "/fanout")
    public MainObject FanoutsendMessage(@RequestBody MainObject msg){

        Gson gson = new Gson();
        String jsonstr = gson.toJson(msg);

        amqpTemplate.convertAndSend("up-exchange","", msg);
        System.out.println("mssagesent Successfully sent out in fanout rabbit mq");
        db_opeartions(msg);
        return msg;
    }
    //one to one where message will be redirected to the queue with specific binding
    @PostMapping(path = "/direct")
    public MainObject DirectsendMessage(@RequestBody MainObject msg){

        Gson gson = new Gson();
        String jsonstr = gson.toJson(msg);

        amqpTemplate.convertAndSend(exchange,routingKey,msg);
        System.out.println("mssage successfully  sent in direct exchange rabbit mq");

        db_opeartions(msg);
		/*MongoClient mongo = new MongoClient( "localhost" , 27017 );
		// Creating Credentials
		MongoCredential credential;
		credential = MongoCredential.createCredential("root",                "Pass@123",
				"password".toCharArray());
		System.out.println("Connected to the database successfully");
		MongoDatabase db = mongo.getDatabase("mydb");


		DBCollection collection =  db.getCollection("consumer");
		DBObject dbObject = (DBObject) JSON.parse(jsonstr);

		collection.insert(dbObject);
		System.out.println("Connected to the database  collection");

		 */


        //MongoCollection<Document> mycollection =       database.getCollection("consumer");
        //mycollection.insertOne(Document.parse(jsonstr));
        //DBObject object = (DBObject) JSON.parse(jsonstr);
        //mycollection.insertOne((Document) object);
        System.out.println("Data inserted successfully  in mysql Database");


        return msg;
    }
    @PostMapping(path = "/topic")
    public MainObject TopicsendMessage(@RequestBody MainObject msg){

        Gson gson = new Gson();
        String jsonstr = gson.toJson(msg);

        amqpTemplate.convertAndSend("topic-exchange","up.#", msg);
        System.out.println("mssage successfully  sent in topic exchange  rabbit mq");


        System.out.println(msg.getOrderid());
        db_opeartions(msg);

        return msg;
    }



}
package mongo;

import org.bson.Document;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class ConnectDemo {
	private static String host = "localhost";
	private static int port = 27017;
	private static String dbName = "userDB";
	private static String collectionName = "userCol";
	private MongoClient mongoClient = null;

	public MongoClient getMongoClient() {
		if (mongoClient == null) {
			MongoClientOptions.Builder build = new MongoClientOptions.Builder();
			build.threadsAllowedToBlockForConnectionMultiplier(50); // 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
			build.maxWaitTime(1000 * 60 * 2);
			build.connectTimeout(1000 * 60 * 1); // 与数据库建立连接的timeout设置为1分钟

			MongoClientOptions myOptions = build.build();
			try {
				// 数据库连接实例
				mongoClient = new MongoClient(host + ":" + port, myOptions);
				//System.out.println(mongoClient.getMongoClientOptions().toString());

			} catch (MongoException e) {
				e.printStackTrace();
			}

		}
		return mongoClient;
	}

	/*
	 * 注册新用户 return 0--成功 return 1--用户已存在 return 2--错误
	 */
	private ConnectDemo() {
		if (mongoClient == null) {
			MongoClientOptions.Builder build = new MongoClientOptions.Builder();
			build.threadsAllowedToBlockForConnectionMultiplier(50); // 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
			/*
			 * 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟
			 * 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception
			 * 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败
			 */
			build.maxWaitTime(1000 * 60 * 2);
			build.connectTimeout(1000 * 60 * 1); // 与数据库建立连接的timeout设置为1分钟

			MongoClientOptions myOptions = build.build();
			try {
				// 数据库连接实例
				mongoClient = new MongoClient(host + ":" + port, myOptions);
				//System.out.println(mongoClient.getMongoClientOptions().toString());

			} catch (MongoException e) {
				e.printStackTrace();
			}

		}
	}

	private static final ConnectDemo connect = new ConnectDemo();

	public static ConnectDemo getConnect() {
		return connect;
	}

	public int register(String username, String password, String email,
			String auth) {
		Document newUser = new Document("username", username)
				.append("password", password).append("email", email)
				.append("auth", auth);
		Document check = new Document("username", username);
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
			System.out.println("Connect to database successfully");
			MongoCollection<Document> collection = mongoDatabase
					.getCollection(collectionName);

			FindIterable<Document> findIterable = collection.find(check);
			MongoCursor<Document> mongoCursor = findIterable.iterator();

			if (mongoCursor.hasNext()) {
				//System.out.println("已存在--");
				return 1; // 判断是否已存在
			}

			collection.insertOne(newUser);
			//System.out.println("注册成功");
			return 0;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return 2;
		}

	}

	/*
	 * 登陆 return 0---成功 return 1---密码错误 return 2---用户不存在 return 3---错误
	 */
	public int login(String username, String password) {
		Document user = new Document("username", username).append("password",
				password);
		Document checkUsername = new Document("username", username);
		try {
			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
			System.out.println("Connect to database successfully");
			MongoCollection<Document> collection = mongoDatabase
					.getCollection(collectionName);

			FindIterable<Document> findIterable = collection.find(user);
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			if (mongoCursor.hasNext()) {
				return 0; // 成功
			}

			FindIterable<Document> findIterable2 = collection
					.find(checkUsername);
			MongoCursor<Document> mongoCursor2 = findIterable2.iterator();
			if (mongoCursor2.hasNext()) {

				return 1;
			} else {

				return 2;
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return 3;
		}

	}

	/*
	 * 删除 成功---0 错误---1
	 */
	public int delete(Document user) {
		try {
			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
			System.out.println("Connect to database successfully");
			MongoCollection<Document> collection = mongoDatabase
					.getCollection(collectionName);

			FindIterable<Document> findIterable = collection.find(user);
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			if (mongoCursor.hasNext()) {
				collection.deleteOne(user);
				return 0; // 成功
			} else {
				return 1;
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return 1;
		}

	}

	public String findAll() {
		JSONObject userInfo = new JSONObject();
		JSONArray userInfoArray = new JSONArray();
		Document user = new Document();
		try {
			MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
			System.out.println("Connect to database successfully");
			MongoCollection<Document> collection = mongoDatabase
					.getCollection(collectionName);

			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				user = mongoCursor.next();
				user.remove("_id");
				//System.out.println(user.toString());
				userInfoArray.add(JSON.parseObject(user.toJson()));
			}
			return userInfoArray.toJSONString();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return "error";
		}

	}

	public Document findByUsername(String username) {
		Document user = new Document("username", username);
		Document result = null;
		try {
			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
			System.out.println("Connect to database successfully");
			MongoCollection<Document> collection = mongoDatabase
					.getCollection(collectionName);

			FindIterable<Document> findIterable = collection.find(user);
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			if ((result = mongoCursor.next()) != null) {
				return result; // 成功s
			} else {

				return result;
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.out.println("meiyou");
			return result;
		}

	}

	public String update(Document user, String key, String value) {
		String oldValue = null;
		Document result = null;
		try {
			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
			System.out.println("Connect to database successfully");
			MongoCollection<Document> collection = mongoDatabase
					.getCollection(collectionName);

			FindIterable<Document> findIterable = collection.find(user);
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			if ((result = mongoCursor.next()) != null) {
				oldValue = result.replace(key, value).toString();
				System.out.println(result.get(key).toString());
				collection.replaceOne(user,result);
				return oldValue; // 成功
			} else {
				return oldValue;
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return oldValue;
		}

	}

	public void close() {
		if (mongoClient != null) {
			mongoClient.close();
			mongoClient = null;
			System.out.println("断开连接");
		}
	}
}
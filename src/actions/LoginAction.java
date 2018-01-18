package actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import mongo.ConnectDemo;

public class LoginAction implements Action{
	private static String dbName = "userDB";
	private static String collectionName = "userCol";
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Document user = new Document("username", username).append("password", password);
		Document checkUsername = new Document("username", username);
		MongoClient mongoClient =ConnectDemo.getConnect().getMongoClient();
		try {
			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
			System.out.println("Connect to database successfully");
			MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

			FindIterable<Document> findIterable = collection.find(user);
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			if (mongoCursor.hasNext()) {
				return "0"; // 成功
			}

			FindIterable<Document> findIterable2 = collection.find(checkUsername);
			MongoCursor<Document> mongoCursor2 = findIterable2.iterator();
			if (mongoCursor2.hasNext()) {

				return "1";
			} else {

				return "2";
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return "3";
		}
		
	}

}


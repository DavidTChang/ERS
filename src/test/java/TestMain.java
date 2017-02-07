import com.revature.dao.UserDao;
import com.revature.pojo.User;

public class TestMain {

	public static void main(String[] args) {
		UserDao dao = new UserDao();
		User user = dao.getUser("david");
		
		System.out.println(user.getFirstName());
		
	}

}

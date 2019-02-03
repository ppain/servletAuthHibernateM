package dbService;
import dbService.dataSets.UserProfile;

import java.util.HashMap;
import java.util.Map;


public class AccountService {
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    DBService dbService = new DBService();

    public void testDB() {
        try {
            dbService.printConnectInfo();

            String testUser = "testovsky";

            long userId = dbService.addUser(testUser,"pass");
            System.out.println("Added user id: " + userId);

            UserProfile dataSetTestById = dbService.getUserById(userId);
            System.out.println("User data set: " + dataSetTestById.getLogin());

            UserProfile dataSetTestByLogin = getUserByLogin(testUser);
            System.out.println("User data set: " + dataSetTestByLogin.getPassword());

        } catch (
                DBException e) {
            e.printStackTrace();
        }
    }

    public void addNewUser(String login, String password){
        try {
            dbService.addUser(login,password);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public UserProfile getUserByLogin(String login) {
        UserProfile profile = null;
        try {
            profile = dbService.getUser(login);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return profile;
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
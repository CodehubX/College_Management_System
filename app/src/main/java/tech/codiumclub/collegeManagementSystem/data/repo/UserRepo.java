package tech.codiumclub.collegeManagementSystem.data.repo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tech.codiumclub.collegeManagementSystem.app.helper.UrlRequest;
import tech.codiumclub.collegeManagementSystem.data.model.User;
import tech.codiumclub.collegeManagementSystem.model.UserList;

/**
 * Created by Jerry on 19-06-2017.
 */

public class UserRepo {
    private static final String TAG = UserRepo.class.getSimpleName();
    private User user;

    public UserRepo() {

    }

    public List<UserList> getUser(String response) {
        UserList userList;
        List<UserList> userLists = new ArrayList<UserList>();

        try {
            JSONObject userResponse = (new JSONObject(response));
            JSONArray userArray = userResponse.getJSONArray("users");
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject user = userArray.getJSONObject(i);

                userList = new UserList();
                userList.setUserId(user.getInt("userId"));
                userList.setUserName(user.getString("userName"));
                userList.setUserEmail(user.getString("userEmail"));
                userList.setRoleType(user.getString("roleType"));

                userLists.add(userList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userLists;
    }

    public String insert(User user, String url) {
        String response = null;

        String userName = user.getUserName();
        String userEmail = user.getUserEmail();
        int userRoleId = user.getUserRoleId();

        HashMap<String, String> params = new HashMap<>();
        params.put("userName", userName);
        params.put("userEmail", userEmail);
        params.put("userRoleId", String.valueOf(userRoleId));

        UrlRequest urlRequest = new UrlRequest();
        response = urlRequest.postUrlData(url, params);
        Log.d(TAG, response);
        return response;
    }

    public String update(User user, String url, int updateUserRoleStatus) {
        /**
         * 1. "0" For User Name & Email
         * 2. "1" For Role
         */

        String response = null;

        int userId = user.getUserId();
        String userName = user.getUserName();
        String userEmail = user.getUserEmail();
        int userRoleId = user.getUserRoleId();
        int userUpdateStatus = 0;

        HashMap<String, String> params = new HashMap<>();
        params.put("userName", userName);
        params.put("userEmail", userEmail);
        params.put("userRoleId", String.valueOf(userRoleId));

        if (updateUserRoleStatus == 1)
            userUpdateStatus = 1;

        params.put("userUpdateStatus", String.valueOf(userUpdateStatus));
        UrlRequest urlRequest = new UrlRequest();
        response = urlRequest.putUrlData(url, userId, params);
        Log.d(TAG, response);
        return response;
    }

    public String delete(User user, String url) {
        String response = null;

        int userId = user.getUserId();

        UrlRequest urlRequest = new UrlRequest();
        response = urlRequest.deleteUrlData(url, userId);
        Log.d(TAG, response);
        return response;
    }
}

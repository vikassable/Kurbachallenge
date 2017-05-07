package vikas.com.kurbachallenge.networkutil;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vikas.com.kurbachallenge.model.Post;
import vikas.com.kurbachallenge.model.User;

/**
 * Created by Vikas on 5/7/2017.
 */

public interface DataService {
    @GET("/users")
    Call<List<User>> getUsers();
    @GET("/posts")
    Call<List<Post>> getPosts(@Query("userId") int userId);

}

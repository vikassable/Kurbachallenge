package vikas.com.kurbachallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vikas.com.kurbachallenge.adapter.UserAdapter;
import vikas.com.kurbachallenge.model.Post;
import vikas.com.kurbachallenge.model.User;
import vikas.com.kurbachallenge.networkutil.DataService;
import vikas.com.kurbachallenge.networkutil.ServiceGenerator;
import vikas.com.kurbachallenge.util.Constants;

public class PostsActivity extends AppCompatActivity {
    private int mUserId;
    private TextView mDataTextView;
    private static final String TAG = PostsActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        this.mDataTextView = (TextView)findViewById(R.id.data_textView);
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_USER_ID)){
            this.mUserId = intent.getIntExtra(Constants.KEY_USER_ID, -1);
            Log.d(TAG, "user Id "+ this.mUserId);
            this.getPost(this.mUserId);
        }

    }
    private void getPost(int userId) {
        DataService dataService = ServiceGenerator.createService(DataService.class);
        Call<List<Post>> call = dataService.getPosts(userId);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d(TAG, response.toString() + "");
                List<Post> posts = response.body();
                Log.d(TAG, "posts: " + posts.size());
                for(Post post : posts){
                    mDataTextView.append(post.getTitle()+"\n\t"+
                    post.getBody()+"\n\n");
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "request failed");
            }
        });
    }
}

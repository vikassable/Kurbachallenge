package vikas.com.kurbachallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vikas.com.kurbachallenge.adapter.UserAdapter;
import vikas.com.kurbachallenge.model.User;
import vikas.com.kurbachallenge.networkutil.DataService;
import vikas.com.kurbachallenge.networkutil.ServiceGenerator;
import vikas.com.kurbachallenge.util.Constants;

public class MainActivity extends AppCompatActivity implements UserAdapter.ListItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private UserAdapter mUserAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mRecyclerView = (RecyclerView)findViewById(R.id.rv_users);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);


        this.getUsers();
    }

    private void getUsers() {
        DataService dataService = ServiceGenerator.createService(DataService.class);
        Call<List<User>> call = dataService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d(TAG, response.body() + "");
                List<User> users = response.body();
                Log.d(TAG, "users: " + users.size());
                mUserAdapter = new UserAdapter(users, MainActivity.this);
                mRecyclerView.setAdapter(mUserAdapter);

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG, "request failed");
            }
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Toast.makeText(this, "item clicked --"+ clickedItemIndex, Toast.LENGTH_SHORT).show();
        User user = this.mUserAdapter.getData().get(clickedItemIndex);
        Intent intent = new Intent(this, PostsActivity.class);
        intent.putExtra(Constants.KEY_USER_ID, user.getId());
        startActivity(intent);

    }
}

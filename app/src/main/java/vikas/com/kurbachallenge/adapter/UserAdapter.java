package vikas.com.kurbachallenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vikas.com.kurbachallenge.R;
import vikas.com.kurbachallenge.model.User;

/**
 * Created by Vikas on 5/7/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> mData;


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
    final private ListItemClickListener mOnClickListener;

    public UserAdapter(List<User> data, ListItemClickListener listener) {
        this.mData = data;
        this.mOnClickListener = listener;
    }

    public List<User> getData() {
        return mData;
    }

    public void setData(List<User> data) {
        this.mData = data;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = this.mData.get(position);
        holder.userNameTextView.setText(user.getName());
        holder.addrTextView.setText(user.getAddress().getStreet()
                        + "\n" + user.getAddress().getSuite()
                        + "\n" + user.getAddress().getCity()
                        + " : " + user .getAddress().getZipcode());
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView userNameTextView;
        TextView addrTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            userNameTextView = (TextView) itemView.findViewById(R.id.user_name_textView);
            addrTextView = (TextView) itemView.findViewById(R.id.addr_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}

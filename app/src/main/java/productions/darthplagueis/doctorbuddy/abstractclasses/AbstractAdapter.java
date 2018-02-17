package productions.darthplagueis.doctorbuddy.abstractclasses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractAdapter<T> extends RecyclerView.Adapter<AbstractViewHolder> {

    public List<T> list;
    public View view;

    public AbstractAdapter() {
        list = new ArrayList<>();
    }

    @Override
    @NonNull
    public AbstractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
        return getViewHolder();
    }

    protected abstract AbstractViewHolder getViewHolder();

    public abstract int getLayout();

    @Override
    public void onBindViewHolder(@NonNull AbstractViewHolder holder, int position) {
        if (!list.isEmpty()) {
            holder.onBind(list.get(position));
        }
    }

    public void setList(@NonNull List<T> list) {
        this.list = list;
    }

    public void updateList(@NonNull List<T> newList) {
        list.addAll(newList);
        notifyItemRangeInserted(getItemCount(), list.size() - 1);
    }

    @Override
    public int getItemCount() {
        if (!list.isEmpty()) {
            return list.size();
        } else {
            return 0;
        }
    }
}

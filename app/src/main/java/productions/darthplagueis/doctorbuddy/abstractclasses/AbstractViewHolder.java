package productions.darthplagueis.doctorbuddy.abstractclasses;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public abstract class AbstractViewHolder<T> extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private Context context;
    private Resources resources;

    public AbstractViewHolder(@NonNull View itemView) {
        super(itemView);
        setupFields(itemView);
        setViews();
        setOnClickListeners();
    }

    private void setupFields(@NonNull View itemView) {
        context = itemView.getContext();
        resources = context.getResources();
    }

    public abstract void setViews();

    public abstract void onBind(T t);

    @NonNull
    protected Context getContext() {
        return context;
    }

    @NonNull
    protected Resources getResources() {
        return resources;
    }

    public void setOnClickListeners () {
        itemView.setOnClickListener(this);
    }
}

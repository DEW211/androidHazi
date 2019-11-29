package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import data.Category;
import data.CategoryResult;
import hu.bme.aut.triviapp.R;
import network.NetworkManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder> {

    private List<Category> categoryList = new ArrayList<>();

    public List<Category> getCheckedCategories(){
        List<Category> list = new ArrayList<>();
        for (Category category : categoryList) {
            if(category.isChecked()){
                list.add(category);
            }
        }
        return list;
    }

    public CategoryRecyclerAdapter() {

            NetworkManager.getInstance().getCategories().enqueue(new Callback<CategoryResult>() {
                @Override
                public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                    if(response.isSuccessful()){
                        //lista feltöltése
                        for (Category triviaCategory : response.body().getTriviaCategories()) {
                            categoryList.add(triviaCategory);
                        }
                        notifyDataSetChanged();
                    }else{
                        //baj van(és lesz is, mert dolgok és Murphy)
                    }
                }

                @Override
                public void onFailure(Call<CategoryResult> call, Throwable t) {
                    t.printStackTrace();
                }
            });


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row, parent, false);
        return new ViewHolder(categoryRowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        holder.tvCategory.setText(category.getName());

        holder.chSelected.setChecked(category.isChecked());

        holder.chSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((CheckBox) v).toggle();
                category.setChecked(((CheckBox) v).isChecked());

            }
        });

    }

    @Override
    public int getItemCount() {
       return categoryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvCategory;
        public CheckBox chSelected;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            chSelected = itemView.findViewById(R.id.checkBox);

        }
    }
}

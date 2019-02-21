package br.com.paulosalvatore.pagseguroandroidturmaa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

class ProgrammingLanguageAdapter extends RecyclerView.Adapter<ProgrammingLanguageAdapter.ViewHolder> {
    private List<ProgrammingLanguage> programmingLanguages;
    private View.OnClickListener listener;

    public ProgrammingLanguageAdapter(
            List<ProgrammingLanguage> programmingLanguages,
            View.OnClickListener listener
    ) {
        this.programmingLanguages = programmingLanguages;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.programming_language_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProgrammingLanguage programmingLanguage = programmingLanguages.get(position);
        holder.bindView(programmingLanguage, listener);
    }

    @Override
    public int getItemCount() {
        return programmingLanguages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
        }

        void bindView(ProgrammingLanguage item, View.OnClickListener listener) {
            // itemView -> Layout XML
            // item -> Dado
            // Lógica de Apresentação do Dado

            ImageView ivMain = itemView.findViewById(R.id.ivMain);
            TextView tvTitle = itemView.findViewById(R.id.tvTitle);
            TextView tvLaunchYear = itemView.findViewById(R.id.tvLaunchYear);
            TextView tvDescription = itemView.findViewById(R.id.tvDescription);

//            ivMain.setImageResource(item.getImageResourceId());
            ivMain.setImageDrawable(
                    ContextCompat.getDrawable(
                            itemView.getContext(),
                            item.getImageResourceId()
                    )
            );

            tvTitle.setText(item.getTitle());
            tvLaunchYear.setText(String.valueOf(item.getYear()));
            tvDescription.setText(item.getDescription());

            itemView.setOnClickListener(listener);
        }
    }
}

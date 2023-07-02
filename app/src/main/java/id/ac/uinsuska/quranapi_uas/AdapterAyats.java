package id.ac.uinsuska.quranapi_uas;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.ac.uinsuska.quranapi_uas.Models.VerseViewModel.TranslationsItem;
import id.ac.uinsuska.quranapi_uas.Models.VerseViewModel.VersesItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAyats extends RecyclerView.Adapter<AdapterAyats.AyatViewHolder> {
    private List<VersesItem> results;
    private List<TranslationsItem> resultTranslations;

    public AdapterAyats(List<VersesItem> results, List<TranslationsItem> resultTranslations) {
        this.results = results;
        this.resultTranslations = resultTranslations;
    }

    @NonNull
    @Override
    public AdapterAyats.AyatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AyatViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.verse, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAyats.AyatViewHolder holder, int position) {
        VersesItem result = results.get(position);
        TranslationsItem resultTranslation = resultTranslations.get(position);
        holder.textViewAyat.setText(result.getTextUthmani());
        holder.textTerjemahanAyat.setText(resultTranslation.getText());
        holder.textViewNomorAyatKe.setText(String.valueOf(position + 1));
        setTranslation(result.getId(), result.getVerseKey(), holder.textTerjemahanAyat);
    }

    private void setTranslation(int id, String verseKey, TextView textTerjemahanAyat) {
        System.out.println(verseKey);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class AyatViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAyat;
        TextView textTerjemahanAyat;
        TextView textViewNomorAyatKe;
        public AyatViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAyat = itemView.findViewById(R.id.tvAyat);
            textTerjemahanAyat = itemView.findViewById(R.id.tvTerjemahanAyat);
            textViewNomorAyatKe = itemView.findViewById(R.id.tvAyatKe);
        }
    }

    public void setData(List<VersesItem> data, List<TranslationsItem> translations){
        results.clear();
        results.addAll(data);
        resultTranslations.clear();
        resultTranslations.addAll(translations);
        notifyDataSetChanged();
    }
}
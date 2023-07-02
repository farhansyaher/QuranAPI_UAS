package id.ac.uinsuska.quranapi_uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import id.ac.uinsuska.quranapi_uas.Models.VerseViewModel.Audios;
import id.ac.uinsuska.quranapi_uas.Models.VerseViewModel.AudiosItem;
import id.ac.uinsuska.quranapi_uas.Models.VerseViewModel.Translations;
import id.ac.uinsuska.quranapi_uas.Models.VerseViewModel.TranslationsItem;
import id.ac.uinsuska.quranapi_uas.Models.VerseViewModel.Verses;
import id.ac.uinsuska.quranapi_uas.Models.VerseViewModel.VersesItem;
import id.ac.uinsuska.quranapi_uas.Retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSurahActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterAyats adapterAyats;
    private List<VersesItem> results = new ArrayList<>();
    private List<TranslationsItem> resultTranslations = new ArrayList<>();
    TextView tvSurahKe, tvNamaSurah, tvJumlahAyat, tvTurunDi;
    Button btnPlay, btnPause, btnStop;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        // Rincian Surah

        // Id Surah
        int id = getIntent().getIntExtra("id", 1);
        tvSurahKe = findViewById(R.id.tvSurahKe);
        tvSurahKe.setText("Surat ke - " + id);

        // Nama Surah
        String nama = getIntent().getStringExtra("nama");
        tvNamaSurah = findViewById(R.id.tvNamaSurah);
        tvNamaSurah.setText(nama);

        // Total Ayat Surah
        int jmlh = getIntent().getIntExtra("jmlh_ayat", 1);
        tvJumlahAyat = findViewById(R.id.tvJumlahAyat);
        tvJumlahAyat.setText("Berjumlah " + jmlh + " ayat");

        // Kota Turunnya Surah
        String kota = getIntent().getStringExtra("kota");
        tvTurunDi = findViewById(R.id.tvTurunDi);
        tvTurunDi.setText("Diturunkan di " + kota);

        setUpView();
        setUpRecyclerView();
        System.out.println(id);
        getDataFromApi(id);
    }

    private void setUpRecyclerView() {
        adapterAyats = new AdapterAyats(results, resultTranslations);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterAyats);
        Button btnPlay = recyclerView.findViewById(R.id.btnPlay);
        System.out.println("Pass");
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
    }

    private void getDataFromApi(int id){
        ApiService.endpoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if(response.isSuccessful()){
                    List<VersesItem> result = response.body().getVerses();
                    Log.d("Ayat", result.toString());
//                    adapterAyats.setData(result, );
                    getTranslationFromAPI(result, id);
                }
            }

            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
                Log.d("Ayat", t.toString());
            }
        });
    }

    private void getTranslationFromAPI(List<VersesItem> resultVerses, int id) {
        ApiService.endpoint().getTranslation(id).enqueue(new Callback<Translations>() {
            @Override
            public void onResponse(Call<Translations> call, Response<Translations> response) {
                if(response.isSuccessful()){
                    List<TranslationsItem> resultTranslations = response.body().getTranslations();
                    Log.d("Translations", resultTranslations.toString());
                    adapterAyats.setData(resultVerses, resultTranslations);
                }
            }

            @Override
            public void onFailure(Call<Translations> call, Throwable t) {
                Log.d("Ayat", t.toString());
            }
        });
    }

    private void getSoundsFromApi(int id){

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);

        ApiService.endpoint().getAudio(id).enqueue(new Callback<Audios>() {
            @Override
            public void onResponse(Call<Audios> call, Response<Audios> response) {
                if(response.isSuccessful()){
                    List<AudiosItem> result = response.body().getAudios();

                    for (int i=0; i<result.size(); i++) {
                        System.out.println(result.get(i));
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try{
                            mediaPlayer.setDataSource(result.get(i).getAudioUrl());
                            mediaPlayer.prepare();
//                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d("Audio", result.toString());
                }
            }

            @Override
            public void onFailure(Call<Audios> call, Throwable t) {
                Log.d("Audio", t.toString());
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer == null) {
                    getSoundsFromApi(id);
                } else {
                    mediaPlayer.start();
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer == null) {
                    Toast.makeText(getApplicationContext(), "Media Player Null", Toast.LENGTH_SHORT).show();
                } else {
                    mediaPlayer.pause();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });
    }

    public void playAyat(View v){
        System.out.println("Playing");

        int id = getIntent().getIntExtra("id", 1);

        getSoundsFromApi(id);
    }
}
package id.ac.uinsuska.quranapi_uas.Retrofit;

import id.ac.uinsuska.quranapi_uas.Models.SurahModel.Chapters;
import id.ac.uinsuska.quranapi_uas.Models.VerseViewModel.Audios;
import id.ac.uinsuska.quranapi_uas.Models.VerseViewModel.Translations;
import id.ac.uinsuska.quranapi_uas.Models.VerseViewModel.Verses;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();

    @GET("quran/verses/uthmani")
    Call<Verses> getAyat(@Query("chapter_number") int id);

    @GET("chapter_recitations/7")
    Call<Audios> getAudio(@Query("chapter_number") int id);

    @GET("quran/translations/33")
    Call<Translations> getTranslation(@Query("chapter_number") int id);
}
package id.ac.uinsuska.quranapi_uas.Models.VerseViewModel;

import com.google.gson.annotations.SerializedName;

public class AudiosItem {

	@SerializedName("chapter_id")
	private String chapterId;

	@SerializedName("format")
	private String format;

	@SerializedName("audio_url")
	private String AudioUrl;

	public String getChapterId(){
		return chapterId;
	}

	public String getFormat(){
		return format;
	}

	public String getAudioUrl(){
		return AudioUrl;
	}

	@Override
 	public String toString(){
		return 
			"AudiosItem{" +
			"chapter_id = '" + chapterId + '\'' +
			",format = '" + format + '\'' +
			",audio_url = '" + AudioUrl + '\'' +
			"}";
		}
}
package id.ac.uinsuska.quranapi_uas.Models.VerseViewModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Audios {

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("audio_files")
	private List<AudiosItem> audios;

	public Meta getMeta(){
		return meta;
	}

	public List<AudiosItem> getAudios(){
		return audios;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"meta = '" + meta + '\'' + 
			",audios = '" + audios + '\'' +
			"}";
		}
}
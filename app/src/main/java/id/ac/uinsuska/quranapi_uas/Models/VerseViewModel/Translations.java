package id.ac.uinsuska.quranapi_uas.Models.VerseViewModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Translations {

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("translations")
	private List<TranslationsItem> translations;

	public Meta getMeta(){
		return meta;
	}

	public List<TranslationsItem> getTranslations(){
		return translations;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"meta = '" + meta + '\'' + 
			",translations = '" + translations + '\'' +
			"}";
		}
}
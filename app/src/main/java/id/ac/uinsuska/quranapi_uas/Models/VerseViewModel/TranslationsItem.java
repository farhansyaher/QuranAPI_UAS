package id.ac.uinsuska.quranapi_uas.Models.VerseViewModel;

import com.google.gson.annotations.SerializedName;

public class TranslationsItem {

	@SerializedName("resource_id")
	private String resourceId;

	@SerializedName("text")
	private String text;

	public String getResourceId(){
		return resourceId;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"TranslationsItem{" +
			"resource_id = '" + resourceId + '\'' +
			",text = '" + text + '\'' +
			"}";
		}
}
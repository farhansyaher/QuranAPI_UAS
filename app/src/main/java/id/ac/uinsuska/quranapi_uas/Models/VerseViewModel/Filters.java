package id.ac.uinsuska.quranapi_uas.Models.VerseViewModel;

import com.google.gson.annotations.SerializedName;

public class Filters{

	@SerializedName("chapter_number")
	private String chapterNumber;

	public String getChapterNumber(){
		return chapterNumber;
	}

	@Override
 	public String toString(){
		return 
			"Filters{" + 
			"chapter_number = '" + chapterNumber + '\'' + 
			"}";
		}
}
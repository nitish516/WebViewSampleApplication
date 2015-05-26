package com.example.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

public class AppState {

	SharedPreferences mConf;

	private static final String LANGUAGE = "LANGUAGE";
	private static final String LANGUAGE_ID = "LANGUAGE_ID";

	private static AppState instance;

	public synchronized static void init(Context context) {
		if (instance == null)
			instance = new AppState(context);
	}

	public AppState(Context context) {
		if (this.mConf == null) {
			this.mConf = context.getSharedPreferences(PConstants.APP_CONFIG,
					Context.MODE_PRIVATE);
		}
	}

	public static AppState getInstance() {
		if (instance == null)
			throw new RuntimeException("AppState has not been initialized");
		return instance;
	}

	public static AppState getInstance(Context context) {
		if (instance == null)
			instance = new AppState(context);
		return instance;
	}

	public Boolean setLanguage(String language) {
		SharedPreferences.Editor ed = mConf.edit();
		ed.putString(LANGUAGE, language);
		return ed.commit();
	}

	public String getLanguage() {
		return mConf.getString(LANGUAGE, "regular");
	}

	public Boolean setLanguageId(String languageId) {
		SharedPreferences.Editor ed = mConf.edit();
		ed.putString(LANGUAGE_ID, languageId);
		return ed.commit();
	}

	public String getLanguageId() {
		return mConf.getString(LANGUAGE_ID, "");
	}

}

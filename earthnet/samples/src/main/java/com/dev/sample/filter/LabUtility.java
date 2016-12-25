package com.dev.sample.filter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AjayMenon on 12/24/2016.
 */
public class LabUtility {

	private static LabUtility instance = null;

	private LabUtility () {
		instance = new LabUtility();
	}

	public static LabUtility getInstance () {
		return instance;
	}

	/**
	 * Read input keys from the given key file.
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> getFileContent (final String fileName) throws FileNotFoundException, IOException{

		if(fileName!=null) {
			BufferedReader bufferedKeyReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			List<String> keyList = null;

			if (bufferedKeyReader != null && bufferedKeyReader.ready()) {
				keyList = new ArrayList<String>(50);
				String currentine = null;

				while ((currentine = bufferedKeyReader.readLine()) != null) {
					keyList.addAll(Arrays.asList(currentine.split(" ")));
				}

				return keyList;
			}
		}

		return null;
	}
}

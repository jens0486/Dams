package de.seideman.dams.manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginManager implements LoginManagerLocal {

	public Boolean login(String user, String passHash) {
		Boolean result = false;
		HashMap<String, String> users = loadUser();
		System.out.println(users.containsKey(user)+" "+passHash.contains(users.get(user)));
		
		
		if (users.containsKey(user) && passHash.contains(users.get(user))) {
			System.out.println("Jawohl");
			result = true;
		}

		return result;
	}

	private HashMap<String, String> loadUser() {

		ArrayList<String> array = new ArrayList<String>();

		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(
							"D:\\FHB\\JEE_EnterpriseAnwendung\\Projekt\\DAMS02\\users.php"));
			String zeile = null;
			System.out.println("Ausgabe: ");

			while ((zeile = reader.readLine()) != null) {
				array.add(zeile);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return parseUserArray(array);
	}

	private HashMap<String, String> parseUserArray(ArrayList<String> array) {
		HashMap<String, String> hash = new HashMap<String, String>();
		boolean bool = true;
		int index = 0;

		// find Index, where is to find the first User-Entrie
		while (bool) {
			if (array.get(index).contains("<users")) {
				bool = false;
			} else {
				index++;
			}
		}

		// put usernames and passwords in an HashMap
		for (int z = index + 1; z < array.size(); z++) {
			if (!array.get(z).contains("</users")) {
				String[] user = array.get(z).split(";");
				hash.put(user[0], user[2]);
			}
		}
//		for (String key : hash.keySet()) {
//			System.out.println(key + " " + hash.get(key));
//		}
		return hash;
	}

}

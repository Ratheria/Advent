package version;

import controller.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Version
{
    private static final String VERSION_URL = "https://raw.githubusercontent.com/Ratheria/Advent/master/src/version/update.txt";
	private static final String RELEASES_URL = "https://github.com/Ratheria/Advent/releases";

    private Version() { }

    public static String versionCheck()
    {
        String result = "";

		if (!AdventMain.DO_VERSION_CHECK)
		{
			return result;
		}

		try
        {
            URL url = new URL(VERSION_URL);
            URLConnection connection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Version.class.getResourceAsStream("update.txt")));
            String lastUpdated = bufferedReader.readLine();
            System.out.println("Last Updated " + lastUpdated + "\n");
            bufferedReader.close();

            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String mostRecentUpdate = bufferedReader.readLine();
            if (!lastUpdated.equals(mostRecentUpdate))
            {
                result = "\n Updated Version Available: " + RELEASES_URL + "\n\n\n";
            }
            bufferedReader.close();
        }
        catch (IOException e)
        {
            result = "\n Version Check Failed\n\n\n";
            e.printStackTrace();
        }

        return result;
    }
}

package version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Version
{
    public static String versionCheck()
    {
        try
        {
            URL url  = new URL("https://raw.githubusercontent.com/Ratheria/Advent/master/src/version/update.txt");
            URLConnection  connection  = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Version.class.getResourceAsStream("update.txt")));
            String lastUpdated = bufferedReader.readLine();
            System.out.println("Last Updated " + lastUpdated + "\n");
            bufferedReader.close();

            bufferedReader   = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String mostRecentUpdate = bufferedReader.readLine();
            if(!lastUpdated.equals(mostRecentUpdate))
            { return "\n Updated Version Available: " + bufferedReader.readLine() + "\n\n\n"; }
            bufferedReader.close();
        }
        catch (IOException e)
        { e.printStackTrace(); }
        return "";
    }
}

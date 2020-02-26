package version;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Version
{
    public static String versionCheck()
    {
        try
        {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(Version.class.getResourceAsStream("update.txt")));
            String lastUpdated = fileReader.readLine();
            System.out.println(lastUpdated);
            URL            url  = new URL("https://raw.githubusercontent.com/Ratheria/Advent/master/src/version/update.txt");
            URLConnection  connection  = url.openConnection();
            BufferedReader bufferedReader   = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String mostRecentUpdate = bufferedReader.readLine();
            if(!lastUpdated.equals(mostRecentUpdate))
            { return " Updated Version Available: " + bufferedReader.readLine() + "\n\n\n"; }
        }
        catch (IOException e)
        { e.printStackTrace(); }
        return "";
    }
}

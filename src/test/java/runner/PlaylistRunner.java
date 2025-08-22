package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
		features = {"src\\test\\resources\\spotifyfeature\\Playlist.feature"},
		glue = {"steps"},
		plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
		
		
		
		)

public class PlaylistRunner extends AbstractTestNGCucumberTests{

}

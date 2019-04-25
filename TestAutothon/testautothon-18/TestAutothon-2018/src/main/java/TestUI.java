package JsonCollector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestUI {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hagarwal\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://twitter.com/stepin_forum");

        //To fetch First profile details
        String name_first = driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[1]/strong")).getText();

        driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[1]")).click();

        String handle_name_first = driver.findElement(By.xpath("//h2[contains(@class,'ProfileHeaderCard-screenname u-inlineBlock')]/a")).getText();
        String following_count_first = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[2]/a/span[3]")).getText();
        String followers_count_first = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[3]/a/span[3]")).getText();


        System.out.println("Name of the first people to follow:" + name_first);
        System.out.println("Handle Name of the first people to follow:" + handle_name_first);
        System.out.println("Following count of the first people:" + following_count_first);
        System.out.println("Followers of the first people:" + followers_count_first);

        driver.navigate().back();
        //To fetch Second profile details
        String name_second = driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[2]/strong")).getText();

        driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[2]")).click();

        String handle_name_second = driver.findElement(By.xpath("//h2[contains(@class,'ProfileHeaderCard-screenname u-inlineBlock')]/a")).getText();
        String following_count_second = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[2]/a/span[3]")).getText();
        String followers_count_second = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[3]/a/span[3]")).getText();

        System.out.println("Name of the first second to follow:" + name_second);
        System.out.println("Handle Name of the second people to follow:" + handle_name_second);
        System.out.println("Following count of the second people:" + following_count_second);
        System.out.println("Followers of the second people:" + followers_count_second);

        driver.navigate().back();
        //To fetch third profile details
        String name_third = driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[3]/strong")).getText();

        driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[3]")).click();

        String handle_name_third = driver.findElement(By.xpath("//h2[contains(@class,'ProfileHeaderCard-screenname u-inlineBlock')]/a")).getText();
        String following_count_third = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[2]/a/span[3]")).getText();
        String followers_count_third = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[3]/a/span[3]")).getText();

        System.out.println("Name of the first second to follow:" + name_third);
        System.out.println("Handle Name of the second people to follow:" + handle_name_third);
        System.out.println("Following count of the second people:" + following_count_third);
        System.out.println("Followers of the second people:" + followers_count_third);
        driver.close();

        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("");
        driver.get("http://cgi-lib.berkeley.edu/ex/fup.html");
        driver.findElement(By.xpath("//input[@value='Press']"));
    }
}
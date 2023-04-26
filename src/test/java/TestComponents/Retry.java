package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count = 0;
    int maxTry = 2;

    @Override
    //This method is for retrying selected test, which randomly fails all the time
    //If selected test fails, we're retrying him again to check if another test will be fail as well or not
    public boolean retry(ITestResult iTestResult) {

        if(count< maxTry)
        {
            count++;
            return true;
        }
        return false;
    }
}

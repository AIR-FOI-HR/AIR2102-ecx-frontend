package com.ecxfoi.wbl.wienerbergerfrontend.data;

import com.ecxfoi.wbl.wienerbergerfrontend.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource
{

    public Result<LoggedInUser> login(String username, String password)
    {

        try
        {
            // TODO: handle loggedInUser authentication

            if (!(username.equals("admin") && password.equals("Test1234"))) {
                throw new Exception();
            }

            LoggedInUser fakeUser =
                    new LoggedInUser(
                            1,
                            "admin");
            return new Result.Success<LoggedInUser>(fakeUser);
        }
        catch (Exception e)
        {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout()
    {
        // TODO: revoke authentication
    }
}
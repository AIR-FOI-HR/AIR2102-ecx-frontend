package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.myaccount;

import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.api.APIUtil;
import com.ecxfoi.wbl.wienerbergerfrontend.models.EmptyResponse;
import com.ecxfoi.wbl.wienerbergerfrontend.models.UserData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.UserRepository;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAccountViewModel extends ViewModel
{
    private UserRepository userRepository;

    public ObservableField<String> userMessage;
    public ObservableField<UserData> data;
    public ObservableField<Boolean> error;

    @Inject
    public MyAccountViewModel(UserRepository userRepository)
    {
        this.userRepository = userRepository;

        userMessage = new ObservableField<>();
        data = new ObservableField<>();
        error = new ObservableField<>(false);
    }

    Boolean validateInput(UserData userData)
    {
        if (StringUtils.isEmpty(userData.email))
        {
            userMessage.set("Email is empty!");
            return false;
        }
        else if (StringUtils.isEmpty(userData.firstName))
        {
            userMessage.set("First name is empty!");
            return false;
        }
        else if (StringUtils.isEmpty(userData.lastName))
        {
            userMessage.set("Last name is empty!");
            return false;
        }

        userMessage.set("");
        return true;
    }

    public void updateUserData()
    {
        UserData updatedUserData = data.get();

        if (updatedUserData != null && !validateInput(updatedUserData))
        {
            error.set(true);
        }

        userRepository.setCurrentUserData(updatedUserData).enqueue(new Callback<WienerbergerResponse<UserData>>()
        {
            @Override
            public void onResponse(final Call<WienerbergerResponse<UserData>> call, final Response<WienerbergerResponse<UserData>> response)
            {
                boolean isSuccessful = response.body() != null;
                String newUserMessage = isSuccessful ? "User data successfuly updated!" : APIUtil.getErrorResponseMessage(response.errorBody());

                error.set(!isSuccessful);
                userMessage.set(newUserMessage);
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<UserData>> call, final Throwable t)
            {
                userMessage.set("We were unable to connect to the server!");
                error.set(true);
                call.cancel();
            }
        });
    }

    public void setUserData(UserData userData)
    {
        this.data.set(userData);
    }

    public LiveData<UserData> getCurrentUserData()
    {
        return userRepository.getCurrentUserData();
    }

    // Unlike the rest of the model's properties that are bound two-way to the text controls, Title isn't directly bound to the spinner
    // As it seems, a property can't be bound directly to the selected item, so this little workaround is used to set it manually
    public void onDropdownItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        String selectedTitle = (String) parent.getSelectedItem();

        UserData newUserData = data.get();

        if (newUserData != null)
        {
            newUserData.title = selectedTitle;

            data.set(newUserData);
        }
    }
}

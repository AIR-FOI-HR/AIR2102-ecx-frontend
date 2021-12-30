package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.myaccount;

import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.api.APIUtil;
import com.ecxfoi.wbl.wienerbergerfrontend.models.UserData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.UserRepository;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAccountViewModel extends ViewModel
{
    private final UserRepository userRepository;

    // It was too much of a hastle to use resources :(
    private final String checkFieldMessage = "Check this field";
    private final String successfulUpdateMessage = "User data successfully updated!";
    private final String multipleErrorsMessage = "Check fields for errors!";

    public ObservableField<String> firstNameMessage;
    public ObservableField<String> lastNameMessage;
    public ObservableField<String> phoneMessage;
    public ObservableField<String> faxMessage;
    public ObservableField<String> mainErrorMessage;

    public ObservableField<UserData> data;
    public ObservableField<Boolean> error;

    @Inject
    public MyAccountViewModel(UserRepository userRepository)
    {
        this.userRepository = userRepository;
        firstNameMessage = new ObservableField<>();
        lastNameMessage = new ObservableField<>();
        phoneMessage = new ObservableField<>();
        faxMessage = new ObservableField<>();
        mainErrorMessage = new ObservableField<>();
        data = new ObservableField<>();
        error = new ObservableField<>(false);
    }

    private void clearAllMessages()
    {
        mainErrorMessage.set("");
        firstNameMessage.set("");
        lastNameMessage.set("");
        phoneMessage.set("");
        faxMessage.set("");
    }

    Boolean validateInput(UserData userData)
    {
        clearAllMessages();
        ArrayList<String> errors = new ArrayList<>();
        if (StringUtils.isEmpty(userData.getFirstName()))
        {
            errors.add("First name");
            firstNameMessage.set(checkFieldMessage);
        }
        if (StringUtils.isEmpty(userData.getLastName()))
        {
            errors.add("Last name");
            lastNameMessage.set(checkFieldMessage);
        }
        if (StringUtils.isEmpty(userData.getPhoneNum()))
        {
            errors.add("Phone number");
            phoneMessage.set(checkFieldMessage);
        }
        if (StringUtils.isEmpty(userData.getFaxNum()))
        {
            errors.add("Fax number");
            faxMessage.set(checkFieldMessage);
        }

        if (!errors.isEmpty())
        {
            String finalMessage = "";
            if (errors.size() <= 2)
            {
                for (String error : errors)
                {
                    finalMessage = StringUtils.join(new String[]{finalMessage, StringUtils.join(new String[]{error, " is empty!\n"})});
                }
            } else {
                finalMessage = multipleErrorsMessage;
            }
            mainErrorMessage.set(finalMessage);
            return false;
        }
        return true;
    }

    public void updateUserData()
    {
        UserData updatedUserData = data.get();

        if (updatedUserData != null && !validateInput(updatedUserData))
        {
            error.set(true);
            return;
        }

        userRepository.setCurrentUserData(updatedUserData).enqueue(new Callback<WienerbergerResponse<UserData>>()
        {
            @Override
            public void onResponse(final Call<WienerbergerResponse<UserData>> call, final Response<WienerbergerResponse<UserData>> response)
            {
                clearAllMessages();
                boolean isSuccessful = response.body() != null;
                String receivedUserMessage = isSuccessful ? successfulUpdateMessage : APIUtil.getErrorResponseMessage(response.errorBody());

                error.set(!isSuccessful);

                if (!isSuccessful)
                {
                    switch (receivedUserMessage.substring(0, 3))
                    {
                        case "Fax":
                        {
                            faxMessage.set(checkFieldMessage);
                            break;
                        }
                        case "Pho":
                        {
                            phoneMessage.set(checkFieldMessage);
                            break;
                        }
                        case "Las":
                        {
                            lastNameMessage.set(checkFieldMessage);
                            break;
                        }
                        case "Fir":
                        {
                            firstNameMessage.set(checkFieldMessage);
                            break;
                        }
                    }
                }

                mainErrorMessage.set(receivedUserMessage);
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<UserData>> call, final Throwable t)
            {
                mainErrorMessage.set("Connection error");
                error.set(true);
                call.cancel();
            }
        });
    }

    public void setUserData(UserData userData)
    {
        this.data.set(userData);
    }

    public LiveData<UserData>getCurrentUserData()
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
            newUserData.setTitle(selectedTitle);

            data.set(newUserData);
        }
    }
}
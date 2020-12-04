package edu.cnm.deepdive.whatwouldjesusdo.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.whatwouldjesusdo.R;
import edu.cnm.deepdive.whatwouldjesusdo.databinding.ActivityLoginBinding;
import edu.cnm.deepdive.whatwouldjesusdo.service.GoogleSignInService;
import edu.cnm.deepdive.whatwouldjesusdo.service.MainRepository;

/**
 * This class works with the google sign in class to tell the application what to do upon login
 * activies.
 */
public class LoginActivity extends AppCompatActivity {

  private static final int LOGIN_REQUEST_CODE = 1000;

  private GoogleSignInService service;
  private ActivityLoginBinding binding;
  private MainRepository mainRepository;
  private long oauth;

  /**
   * This method allows for the loggining in to connect to the application.
   * @param savedInstanceState
   */
  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    service = GoogleSignInService.getInstance();
    mainRepository = new MainRepository(this);
    service.refresh()
        .subscribe(
            this::updateAndSwitch,
            (throwable) -> {
              binding = ActivityLoginBinding.inflate(getLayoutInflater());
              binding.signIn.setOnClickListener((v) -> service.startSignIn(this, LOGIN_REQUEST_CODE));
              setContentView(binding.getRoot());
            }
        );
  }

  /**
   * This method gives a result upon failint to or succesfully logging in.
   * @param requestCode
   * @param resultCode
   * @param data
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == LOGIN_REQUEST_CODE) {
      service.completeSignIn(data)
          .addOnSuccessListener(this::updateAndSwitch)
          .addOnFailureListener((throwable) ->
              Toast.makeText(this, R.string.login_failure_message, Toast.LENGTH_LONG).show());
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  /**
   * This method updates information depending on who logs in on the device.
   * @param account
   */
  private void updateAndSwitch(GoogleSignInAccount account) {
    mainRepository.getOrCreate(account)
        .subscribe(
            (user) -> {
              Intent intent = new Intent(this, MainActivity.class)
                  .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(intent);
            },
            (throwable) -> {
              Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
              throw new RuntimeException(throwable);
            }
        );
  }

}
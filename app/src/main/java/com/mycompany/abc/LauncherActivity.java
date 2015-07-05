package com.mycompany.abc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.mycompany.abc.task.LoadingTask;


public class LauncherActivity extends Activity implements LoadingTask.LoadingTaskFinishedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the launcher screen
        setContentView(R.layout.activity_launcher);
        // Find the progress bar
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.activity_launcher_progress_bar);
        // Start your loading
        new LoadingTask(progressBar, this).execute("");
    }

    // This is the callback for when your async task has finished
    public void onTaskFinished() {
        completeLauncher();
    }

    private void completeLauncher() {
        startApp();
        finish(); // Don't forget to finish this Splash Activity so the user can't return to it!
    }

    private void startApp() {
        Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

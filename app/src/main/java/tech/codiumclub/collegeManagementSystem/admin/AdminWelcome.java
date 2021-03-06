package tech.codiumclub.collegeManagementSystem.admin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import tech.codiumclub.collegeManagementSystem.R;
import tech.codiumclub.collegeManagementSystem.app.config.ConnectivityReceiver;

/**
 * Created by Jerry on 12-06-2017.
 */

public class AdminWelcome extends Fragment implements ConnectivityReceiver.ConnectivityReceiverListener {

    public static final String TAG = AdminWelcome.class.getSimpleName();
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        checkConnection();
        View view = inflater.inflate(R.layout.admin_fragment_welcome, container, false);
        String url = "https://www.powr.io/plugins/rss-feed/view?unique_label=3b3ff56e_1488530168&external_type=iframe";
        webView = (WebView) view.findViewById(R.id.admin_welcome_web_view);
        webView.loadUrl(url);
        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Force links and redirects to open tech the WebView instead of tech a browser
        webView.setWebViewClient(new WebViewClient());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Admin Welcome");
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showToast(isConnected);
    }

    // Showing the status tech Toast
    private void showToast(boolean isConnected) {
        String message;
        int color;
        if (!isConnected) {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Callback will be triggered when there is change tech
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showToast(isConnected);
    }
}

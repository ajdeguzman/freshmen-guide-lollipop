package edu.ucuccs.ucufreshmenguide;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.cocosw.bottomsheet.BottomSheet;

import java.util.List;

public class FragmentContactUs extends Fragment {
    public static final String TAG = "handbook";

    private ArrayAdapter<String> adapter;
    private ImageView btnMenuContacts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contactus, container, false);
        btnMenuContacts = (ImageView)rootView.findViewById(R.id.btnMenuContacts);
        btnMenuContacts.bringToFront();
        btnMenuContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new BottomSheet.Builder(getActivity())
                        .title("Contact Us")
                        .sheet(R.menu.menu_contact_us)
                        .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case R.id.contact_website:
                                        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ucu.edu.ph"));
                                        startActivity(webIntent);
                                        break;
                                    case R.id.contact_map:
                                        double latitude = 15.9800295;
                                        double longitude = 120.5610351;
                                        String label = "Urdaneta City University";
                                        String uriBegin = "geo:" + latitude + "," + longitude;
                                        String query = latitude + "," + longitude + "(" + label + ")";
                                        String encodedQuery = Uri.encode(query);
                                        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                                        Uri uri = Uri.parse(uriString);
                                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                                        startActivity(intent);
                                        break;
                                    case R.id.contact_email:

                                        if (isMailClientPresent(getActivity())) {
                                            Intent email = new Intent(Intent.ACTION_SEND);
                                            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"aljohndeguzman@gmail.com"});
                                            email.setType("message/rfc822");
                                            startActivity(Intent.createChooser(email, "Choose an Email Client"));
                                        } else {
                                            showToast("No apps can perform this client");
                                        }
                                        break;
                                    case R.id.contact_phone:
                                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                                        callIntent.setData(Uri.parse("tel:568 2475"));
                                        startActivity(callIntent);
                                        break;
                                }
                            }
                        }).show();
            }
        });
        showToast("Pinch Map to Zoom");
        return rootView;
    }
    public static boolean isMailClientPresent(Context context){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, 0);
        if(list.size() == 0)
            return false;
        else
            return true;
    }
    public void showToast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}

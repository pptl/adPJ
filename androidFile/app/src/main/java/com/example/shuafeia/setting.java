package com.example.shuafeia;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class setting extends Fragment{
    private Button logout;
    FirebaseAuth auth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        auth = FirebaseAuth.getInstance();

        View view =inflater.inflate(R.layout.fragment_setting, container, false);
        logout = view.findViewById(R.id.Logout_Button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });

        return view;
    }



    private void Logout() {
        auth.signOut();
        Intent intent = new Intent(this.getContext(),login_page.class);
        startActivity(intent);
    }


}

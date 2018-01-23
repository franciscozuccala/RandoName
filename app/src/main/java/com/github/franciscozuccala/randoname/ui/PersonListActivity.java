package com.github.franciscozuccala.randoname.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.github.franciscozuccala.randoname.R;

public class PersonListActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		if (savedInstanceState == null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			PersonListFragment fragment = new PersonListFragment();
			fragmentTransaction.add(com.github.franciscozuccala.randoname.R.id.containerFragment, fragment);
			fragmentTransaction.commit();
		}
	}
}

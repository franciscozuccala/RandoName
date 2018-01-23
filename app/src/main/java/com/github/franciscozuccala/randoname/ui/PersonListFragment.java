package com.github.franciscozuccala.randoname.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.franciscozuccala.randoname.domain.Person;
import com.github.franciscozuccala.randoname.domain.PersonsList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonListFragment extends Fragment {

	private RecyclerView recyclerView;
	private List<Person> persons;
	private PersonRecyclerViewAdapter adapter;
	private FloatingActionButton floatingActionButton;
	private Button getNameButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(com.github.franciscozuccala.randoname.R.layout.fragment_list, container, false);
		if (savedInstanceState != null) {
			persons = ((PersonsList)savedInstanceState.getSerializable("PersonsList")).getPersons();
		}
		if (persons == null) {
			persons = new ArrayList<Person>();
		}
		recyclerView = (RecyclerView)view.findViewById(com.github.franciscozuccala.randoname.R.id.recyclerView);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
		adapter = new PersonRecyclerViewAdapter(persons, new PersonRecyclerViewAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(Person item) {
				adapter.remove(item);
			}
		}, getActivity());
		adapter.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(linearLayoutManager);
		RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
		itemAnimator.setAddDuration(1000);
		itemAnimator.setRemoveDuration(1000);
		recyclerView.setItemAnimator(itemAnimator);
		floatingActionButton = (FloatingActionButton)view.findViewById(com.github.franciscozuccala.randoname.R.id.addFab);
		floatingActionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog alertDialog = createAddDialog();
				alertDialog.show();
			}
		});
		getNameButton = (Button)view.findViewById(com.github.franciscozuccala.randoname.R.id.button);
		getNameButton.setClickable(true);
		getNameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Random random = new Random();
				List<Person> allPersons = adapter.getPersons();
				if (allPersons != null && !allPersons.isEmpty()) {
					Person winner = allPersons.get(random.nextInt(allPersons.size()));
					AlertDialog alertDialog = createWinnerDialog(winner.getName());
					alertDialog.show();
				} else {
					Snackbar snackbar = Snackbar.make(getView(), "Ingrese una persona", Snackbar.LENGTH_LONG);
					snackbar.show();
				}
			}
		});


		return view;
	}


	public AlertDialog createAddDialog() {
		LayoutInflater linf = LayoutInflater.from(getActivity());
		final View inflator = linf.inflate(com.github.franciscozuccala.randoname.R.layout.insert_dialog, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(inflator);
		final AlertDialog alertDialog=builder.create();
		final EditText text = (EditText)inflator.findViewById(com.github.franciscozuccala.randoname.R.id.newPerson);
		Button yesButton = (Button)inflator.findViewById(com.github.franciscozuccala.randoname.R.id.yesButton);
		yesButton.setClickable(true);
		yesButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				adapter.insert(new Person(text.getText().toString()));
				alertDialog.cancel();
			}
		});
		Button noButton = (Button)inflator.findViewById(com.github.franciscozuccala.randoname.R.id.noButton);
		noButton.setClickable(true);
		noButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});
		return alertDialog;
	}

	public AlertDialog createWinnerDialog(String winner) {
		LayoutInflater linf = LayoutInflater.from(getActivity());
		final View inflator = linf.inflate(com.github.franciscozuccala.randoname.R.layout.winner_dialog, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(inflator);
		final AlertDialog alertDialog=builder.create();
		TextView text = (TextView)inflator.findViewById(com.github.franciscozuccala.randoname.R.id.winnerName);
		text.setText(winner);
		Button okButton = (Button)inflator.findViewById(com.github.franciscozuccala.randoname.R.id.okButton);
		okButton.setClickable(true);
		okButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				alertDialog.cancel();
			}
		});
		return alertDialog;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		PersonsList list = new PersonsList();
		list.setPersons(adapter.getPersons());
		outState.putSerializable("PersonsList", list);
	}

}

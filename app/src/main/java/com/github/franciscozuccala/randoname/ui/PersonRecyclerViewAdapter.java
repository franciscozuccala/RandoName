package com.github.franciscozuccala.randoname.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.franciscozuccala.randoname.domain.Person;
import com.github.franciscozuccala.randoname.R;

import java.util.List;


public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.PersonItemViewHolder> {

	private List<Person> persons;
	private OnItemClickListener listener;
	private Context context;
	private RecyclerView.LayoutManager layoutManager;

	public interface OnItemClickListener {
		void onItemClick(Person item);
	}

	public PersonRecyclerViewAdapter(List<Person> list, OnItemClickListener listener, Context context) {
		this.listener = listener;
		this.context=context;
		persons = list;

	}
	public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
		this.layoutManager= layoutManager;
	}

	@Override
	public PersonItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
		return new PersonItemViewHolder(v);
	}

	@Override
	public void onBindViewHolder(PersonItemViewHolder holder, int position) {
		final Person person = persons.get(position);
		holder.bind(person, listener);
		animate(holder);

	}

	public void animate(RecyclerView.ViewHolder viewHolder) {
		final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
		viewHolder.itemView.setAnimation(animAnticipateOvershoot);
	}

	@Override
	public int getItemCount() {
		return persons.size();
	}

	public List<Person> getPersons(){
		return persons;
	}

	public void remove(Person person) {
		int position=persons.indexOf(person);
		if(position<persons.size() && !persons.isEmpty()&& position>=0) {
			persons.remove(position);
			notifyItemRemoved(position);
		}
	}

	public void insert(Person person) {
		persons.add(0,person);
		notifyItemInserted(0);
		layoutManager.scrollToPosition(0);
	}

	public static class PersonItemViewHolder extends RecyclerView.ViewHolder {
		TextView namePerson;
		LinearLayout itemPerson;
		ImageView imgPerson;

		public PersonItemViewHolder(View itemView) {
			super(itemView);
			itemPerson = (LinearLayout)itemView.findViewById(R.id.itemPerson);
			namePerson = (TextView)itemView.findViewById(R.id.namePerson);
			imgPerson = (ImageView)itemView.findViewById(R.id.imgPerson);
		}

		public void bind(final Person person, final OnItemClickListener listener) {
			namePerson.setText(person.getName());
			itemPerson.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onItemClick(person);
				}
			});
		}


	}


}
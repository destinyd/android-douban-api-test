package com.realityandapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.inject.Inject;
import com.realityandapp.R;
import com.realityandapp.UI.Adapters.AdapterWork;
import com.realityandapp.constants.Extras;
import com.realityandapp.core.HistoriesController;
import com.realityandapp.core.ImageLoader;
import com.realityandapp.model.v2.Celebrity;
import com.realityandapp.model.v2.Work;
import com.realityandapp.service.MovieService;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import roboguice.util.RoboAsyncTask;


public class ActivityCelebrity extends BaseActivity {

    @InjectView(R.id.iv_image)
    protected ImageView iv_image;
    @InjectView(R.id.tv_name)
    protected TextView tv_name;
    @InjectView(R.id.tv_name_en)
    protected TextView tv_name_en;
    @InjectView(R.id.tv_gender)
    protected TextView tv_gender;
    @InjectView(R.id.tv_birthday)
    protected TextView tv_birthday;
    @InjectView(R.id.tv_born_place)
    protected TextView tv_born_place;

    @InjectView(R.id.lv_works)
    protected ListView lv_works;

    @InjectExtra(Extras.CELEBRITY)
    protected Celebrity celebrity;

    @Inject
    private ImageLoader avatars;

    MovieService movieService;
    protected Celebrity full_celebrity;

    AdapterWork adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_celebrity);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        movieService = new MovieService();

        person_to_view();
        get_remote_person_to_view();

        lv_works.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                onListItemClick((ListView) parent, view, position, id);
            }
        });

        HistoriesController.getFactory().record(celebrity);
    }

    private void person_to_view() {
        avatars.bind(iv_image, celebrity.getAvatars(), "medium");
        tv_name.setText(celebrity.getName());
    }

    protected void get_remote_person_to_view() {
        new RoboAsyncTask<Boolean>(this) {
            public Boolean call() throws Exception {
                full_celebrity = movieService.getCelebrityById(celebrity.getId());
                return true;
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(Boolean relationship) {
                full_person_to_view();
            }
        }.execute();
    }

    private void full_person_to_view() {
        if (full_celebrity.getName_en() != null)
            tv_name_en.setText(full_celebrity.getName_en());
        if (full_celebrity.getGender() != null)
            tv_gender.setText(full_celebrity.getGender());
        if (full_celebrity.getBirthday() != null)
            tv_birthday.setText(full_celebrity.getBirthday());
        if (full_celebrity.getBorn_place() != null)
            tv_born_place.setText(full_celebrity.getBorn_place());
        works_to_view();
    }

    private void works_to_view() {
        adapter = new AdapterWork(
                getLayoutInflater(), full_celebrity.getWorks(),
                avatars);
        lv_works.setAdapter(adapter);
        setListViewHeightBasedOnChildren(lv_works);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        Work work = ((Work) l.getItemAtPosition(position));
        startActivity(new Intent(this, ActivitySubject.class).putExtra(Extras.SUBJECT, work.getSubject()));
    }

    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        getSupportMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

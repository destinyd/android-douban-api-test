package com.realityandapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.inject.Inject;
import com.realityandapp.R;
import com.realityandapp.UI.Adapters.AdapterCelebrity;
import com.realityandapp.constants.Extras;
import com.realityandapp.core.ImageLoader;
import com.realityandapp.model.v2.Celebrity;
import com.realityandapp.model.v2.Subject;
import com.realityandapp.service.MovieService;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import roboguice.util.RoboAsyncTask;


public class ActivitySubject extends BaseActivity {

    @InjectView(R.id.iv_image)
    protected ImageView iv_image;
    @InjectView(R.id.tv_name)
    protected TextView tv_name;
    @InjectView(R.id.tv_rating)
    protected TextView tv_rating;
    @InjectView(R.id.tv_year)
    protected TextView tv_year;
    @InjectView(R.id.tv_subtype)
    protected TextView tv_subtype;

    @InjectView(R.id.tv_countries)
    protected TextView tv_countries;

    @InjectView(R.id.lv_directors)
    protected ListView lv_directors;

    @InjectView(R.id.lv_casts)
    protected ListView lv_casts;

    @InjectExtra(Extras.SUBJECT)
    protected Subject subject;

    @Inject
    private ImageLoader avatars;

    MovieService movieService;
    protected Subject full_subject;

    AdapterCelebrity adapterDirectors = null;
    AdapterCelebrity adapterCasts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_subject);

        movieService = new MovieService();

        subject_to_view();
        get_remote_subject_to_view();

        lv_casts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                onListItemClick((ListView) parent, view, position, id);
            }
        });

        lv_directors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                onListItemClick((ListView) parent, view, position, id);
            }
        });
    }

    private void subject_to_view() {
        avatars.bind(iv_image, subject.getImages(), "medium");
        tv_name.setText(subject.getTitle());
        tv_rating.setText(subject.getRating().getAverage().toString());
        tv_year.setText(subject.getYear().toString());
        tv_subtype.setText(subject.getSubtype());
    }

    protected void get_remote_subject_to_view() {
        new RoboAsyncTask<Boolean>(this) {
            public Boolean call() throws Exception {
                full_subject = movieService.getMovieById(subject.getId());
                return true;
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(Boolean relationship) {
                full_subject_to_view();
            }
        }.execute();
    }

    private void full_subject_to_view() {
        tv_countries.setText(full_subject.getCountriesString());
        directors_to_view();
        casts_to_view();
    }

    private void directors_to_view() {
        adapterDirectors = new AdapterCelebrity(
                getLayoutInflater(), full_subject.getDirectors(),
                avatars);
        lv_directors.setAdapter(adapterDirectors);
        setListViewHeightBasedOnChildren(lv_directors);
    }

    private void casts_to_view() {
        adapterCasts = new AdapterCelebrity(
                getLayoutInflater(), full_subject.getCasts(),
                avatars);
        lv_casts.setAdapter(adapterCasts);
        setListViewHeightBasedOnChildren(lv_casts);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        Celebrity celebrity = ((Celebrity) l.getItemAtPosition(position));
        startActivity(new Intent(this, ActivityCelebrity.class).putExtra(Extras.CELEBRITY, celebrity));
    }
}

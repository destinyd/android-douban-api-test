package com.realityandapp.UI;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.inject.Inject;
import com.realityandapp.R;
import com.realityandapp.constants.Extras;
import com.realityandapp.core.ImageLoader;
import com.realityandapp.model.v2.Subject;
import com.realityandapp.service.MovieService;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import roboguice.util.RoboAsyncTask;


public class ActivitySubject extends RoboActivity {

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

    @InjectExtra(Extras.SUBJECT)
    protected Subject subject;

    @Inject
    private ImageLoader avatars;

    MovieService movieService;
    protected Subject full_subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_subject);

        movieService = new MovieService();

        subject_to_view();
        get_remote_subject_to_view();

    }

    private void subject_to_view() {
        avatars.bind(iv_image, subject, "medium");
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
    }
}

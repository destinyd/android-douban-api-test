package com.realityandapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import com.costum.android.widget.LoadMoreListView;
import com.google.inject.Inject;
import com.realityandapp.R;
import com.realityandapp.UI.Adapters.AdapterMovies;
import com.realityandapp.constants.Extras;
import com.realityandapp.core.ImageLoader;
import com.realityandapp.model.v2.Subject;
import com.realityandapp.model.v2.SubjectList;
import com.realityandapp.service.MovieService;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import roboguice.util.RoboAsyncTask;

public class MainActivity extends
        RoboActivity {

    private String str_q;
    SubjectList subjectList;
    AdapterMovies adapter = null;

    @InjectView(R.id.et_q)
    private EditText et_q;

    @InjectView(R.id.lv_movies)
    private LoadMoreListView lv_movies;

    @Inject
    private ImageLoader avatars;
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down then this Bundle contains the data it most
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    MovieService movieService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieService = new MovieService();

        lv_movies.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                onListItemClick((LoadMoreListView) parent, view, position, id);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.realityandapp.R.menu.main, menu);
        return true;
    }

    public void btn_search_click(View view) {
        str_q = et_q.getText().toString();
        getSubjects();
    }

    private void getSubjects() {
        new RoboAsyncTask<Boolean>(this) {
            public Boolean call() throws Exception {
                subjectList = movieService.search(str_q);

                return true;
            }

            @Override
            protected void onException(Exception e) throws RuntimeException {
                e.printStackTrace();
//                Toaster.showLong(ActivitySubjectImage.this, "获取信息失败");
            }

            @Override
            public void onSuccess(Boolean relationship) {
                subjectList_to_listview();
            }

            @Override
            protected void onFinally() throws RuntimeException {
                lv_movies.onLoadMoreComplete();
            }
        }.execute();
    }

    public void onListItemClick(LoadMoreListView l, View v, int position, long id) {
        Subject joke = ((Subject) l.getItemAtPosition(position));
        startActivity(new Intent(this, ActivitySubject.class).putExtra(Extras.SUBJECT, joke));

    }


    private void subjectList_to_listview() {
//        if(adapter == null)
//        {
        adapter = new AdapterMovies(
                getLayoutInflater(), subjectList.getSubjects(),
                avatars);
        lv_movies.setAdapter(adapter);
//        }
//        else
//        {
//            adapter.setItems(subjectList.getSubjects());
//            adapter.notifyDataSetChanged();
//        }
    }

}

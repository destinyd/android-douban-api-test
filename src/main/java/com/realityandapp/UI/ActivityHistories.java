package com.realityandapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.actionbarsherlock.view.Menu;
import com.google.inject.Inject;
import com.realityandapp.R;
import com.realityandapp.UI.Adapters.AdapterCelebrity;
import com.realityandapp.UI.Adapters.AdapterHistory;
import com.realityandapp.constants.Extras;
import com.realityandapp.core.HistoriesController;
import com.realityandapp.core.ImageLoader;
import com.realityandapp.model.History;
import com.realityandapp.model.v2.Celebrity;
import com.realityandapp.model.v2.Subject;
import roboguice.inject.InjectView;
import roboguice.util.RoboAsyncTask;

import java.util.List;


public class ActivityHistories extends BaseActivity {

    @InjectView(R.id.lv_histories)
    protected ListView lv_histories;

    @Inject
    private ImageLoader avatars;

    AdapterHistory adapterHistory = null;

    List<History> histories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_histories);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        histories = HistoriesController.getFactory().histories;

        histories_to_view();

        lv_histories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                onListItemClick((ListView) parent, view, position, id);
            }
        });
    }

    private void histories_to_view() {
        adapterHistory = new AdapterHistory(
                getLayoutInflater(), histories,
                avatars);
        lv_histories.setAdapter(adapterHistory);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        History history = ((History) l.getItemAtPosition(position));
        if (history.getType() == History.Type.CELEBRITY){
            Celebrity celebrity = new Celebrity(history);
            startActivity(new Intent(this, ActivityCelebrity.class).putExtra(Extras.CELEBRITY, celebrity));
        }
        else
        {
            Subject subject = new Subject(history);
            startActivity(new Intent(this, ActivitySubject.class).putExtra(Extras.SUBJECT, subject));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

package com.realityandapp.UI.Adapters;

import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.realityandapp.R;
import com.realityandapp.core.ImageLoader;
import com.realityandapp.model.v2.Subject;

import java.util.List;

public class AdapterMovies extends SingleTypeAdapter<Subject> {
    private final ImageLoader avatars;

    public AdapterMovies(LayoutInflater inflater, List<Subject> items, ImageLoader avatars) {
        super(inflater, R.layout.item_subject); // ,,items
        setItems(items);
        this.avatars = avatars;
    }

    /**
     * @param inflater
     * @param items
     */
    public AdapterMovies(LayoutInflater inflater, List<Subject> items) {
        this(inflater, items, null);
    }
    @Override
    public long getItemId(final int position) {
        final String id = getItem(position).getId().toString();
        return !TextUtils.isEmpty(id) ? id.hashCode() : super
                .getItemId(position);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[] { R.id.tv_name, R.id.tv_published_at, R.id.tv_text, R.id.iv_image };
    }

    @Override
    protected void update(int position, Subject subject) {
//        super.update(position, subject);

        setText(0, subject.getTitle());
        setText(1, subject.getYear().toString());
        setText(2, subject.getRating().getAverage().toString());
//
//        setGone(3, true);
//        setGone(4, true);
//        setGone(5, true);

        avatars.bind(imageView(3), subject.getImages());
//
//        if(joke.isVideo())
//        {
//            setGone(5, false);
//        }
//        else{
//            setGone(5, true);
//            if(joke.isGif())
//                setGone(4, false);
//        }



    }
}
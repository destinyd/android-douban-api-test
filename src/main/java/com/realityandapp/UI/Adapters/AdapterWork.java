package com.realityandapp.UI.Adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.realityandapp.R;
import com.realityandapp.core.ImageLoader;
import com.realityandapp.model.v2.Subject;
import com.realityandapp.model.v2.Work;

import java.util.List;

public class AdapterWork extends SingleTypeAdapter<Work> {
    private final ImageLoader avatars;

    public AdapterWork(LayoutInflater inflater, List<Work> items, ImageLoader avatars) {
        super(inflater, R.layout.item_subject); // ,,items
        setItems(items);
        this.avatars = avatars;
    }

    /**
     * @param inflater
     * @param items
     */
    public AdapterWork(LayoutInflater inflater, List<Work> items) {
        this(inflater, items, null);
    }
    @Override
    public long getItemId(final int position) {
        return getItem(position).hashCode();
//        final String id = getItem(position).hashCode();
//        return !TextUtils.isEmpty(id) ? id.hashCode() : super
//                .getItemId(position);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[] { R.id.tv_name, R.id.tv_published_at, R.id.tv_text, R.id.iv_image };
    }

    @Override
    protected void update(int position, Work work) {
//        super.update(position, subject);

        Subject subject = work.getSubject();
        if(subject != null)
        {
            setText(0, subject.getTitle());
            setText(1, String.valueOf(subject.getYear()));
            if(subject.getRating() != null)
                setText(2, String.valueOf(subject.getRating().getAverage()));
            else
                setText(2, "");
            avatars.bind(imageView(3), subject.getImages());
        }
        else
        {
            setText(0, "");
            setText(1, "");
            setText(2, "");
        }
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
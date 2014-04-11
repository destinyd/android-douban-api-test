package com.realityandapp.UI.Adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.realityandapp.R;
import com.realityandapp.core.ImageLoader;
import com.realityandapp.model.v2.Celebrity;

import java.util.List;

public class AdapterCelebrity extends SingleTypeAdapter<Celebrity> {
    private final ImageLoader avatars;

    public AdapterCelebrity(LayoutInflater inflater, List<Celebrity> items, ImageLoader avatars) {
        super(inflater, R.layout.item_person); // ,,items
        setItems(items);
        this.avatars = avatars;
    }

    /**
     * @param inflater
     * @param items
     */
    public AdapterCelebrity(LayoutInflater inflater, List<Celebrity> items) {
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
        return new int[] { R.id.tv_name, R.id.iv_image };
    }

    @Override
    protected void update(int position, Celebrity peroson) {
        setText(0, peroson.getName());

        avatars.bind(imageView(1), peroson.getAvatars());

    }
}
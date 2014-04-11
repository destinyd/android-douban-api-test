package com.realityandapp.UI.Adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.realityandapp.R;
import com.realityandapp.core.ImageLoader;
import com.realityandapp.core.PrettyDateFormat;
import com.realityandapp.model.History;

import java.util.List;

public class AdapterHistory extends SingleTypeAdapter<History> {
    private final ImageLoader avatars;

    public AdapterHistory(LayoutInflater inflater, List<History> items, ImageLoader avatars) {
        super(inflater, R.layout.item_subject); // ,,items
        setItems(items);
        this.avatars = avatars;
    }

    /**
     * @param inflater
     * @param items
     */
    public AdapterHistory(LayoutInflater inflater, List<History> items) {
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
        return new int[]{R.id.tv_name, R.id.tv_published_at, R.id.tv_text, R.id.iv_image};
    }

    @Override
    protected void update(int position, History history) {
        if(history.getType() == History.Type.CELEBRITY)
            setText(0, "名人");
        else
            setText(0, "影片");
        setText(1, PrettyDateFormat.defaultFormat(history.getView_at()));
        setText(2, history.getName());

        avatars.bind(imageView(3), history.getImages());
    }
}
package com.realityandapp.UI.Adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.realityandapp.R;
import com.realityandapp.core.ImageLoader;
import com.realityandapp.model.v2.Person;
import com.realityandapp.model.v2.Subject;

import java.util.List;

public class AdapterPerson extends SingleTypeAdapter<Person> {
    private final ImageLoader avatars;

    public AdapterPerson(LayoutInflater inflater, List<Person> items, ImageLoader avatars) {
        super(inflater, R.layout.item_person); // ,,items
        setItems(items);
        this.avatars = avatars;
    }

    /**
     * @param inflater
     * @param items
     */
    public AdapterPerson(LayoutInflater inflater, List<Person> items) {
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
    protected void update(int position, Person peroson) {
        setText(0, peroson.getName());

        avatars.bind(imageView(1), peroson.getAvatars());

    }
}
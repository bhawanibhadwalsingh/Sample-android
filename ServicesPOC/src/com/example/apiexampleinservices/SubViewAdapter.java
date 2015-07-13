package com.example.apiexampleinservices;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.softprodigy.librarymsspro.ApplicationBitmapManager;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


public class SubViewAdapter extends BaseAdapter {
	private SubClickListener mListener;
	private final List<SubViewChildModel> list;
	private Context context;
	DecimalFormat form = new DecimalFormat("0.00");
	int newposition;

	public interface SubClickListener {
		void onItemClicked1(SubViewChildModel s);
	}

	public SubViewAdapter(Context context, int resourceId,
			List<SubViewChildModel> model) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = new ArrayList<SubViewChildModel>(model);
		// this.list.addAll(model);
//		newposition = positon;
		mListener = (SubClickListener) context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		System.out.println(">>>>>total size>>>>" + list.size());
		return list.size();
	}

	@Override
	public SubViewChildModel getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return ((Integer) position).longValue();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		convertView = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.searchscreenitem, null);
			holder = new ViewHolder();

			holder.image_search = (ImageView) convertView
					.findViewById(R.id.imv_image_search);

			holder.spinner_search = (ProgressBar) convertView
					.findViewById(R.id.spinner_search);
			holder.title_search = (TextView) convertView
					.findViewById(R.id.tv_search_title);
			holder.desc_search = (TextView) convertView
					.findViewById(R.id.tv_search_desc);

			holder.mainframe = (LinearLayout) convertView
					.findViewById(R.id.mainframesubview);
			holder.price_search = (TextView) convertView
					.findViewById(R.id.tv_search_rate);

			final SubViewChildModel newsObj = list.get(position);
			try {

				//
				holder.title_search.setText(newsObj
						.getSubcategoriesProductname());
				holder.desc_search.setText(Html.fromHtml(newsObj
						.getSubcategoriesshortdesc()));
				// holder.price_search.setText(newsObj.getSearchproductPrice());

				double d1 = Double.parseDouble(newsObj.getSubcategoriesprice());
				holder.price_search.setText("$ " + form.format(d1));

				ApplicationBitmapManager.INSTANCE.loadBitmap(
						newsObj.getSubcategorieimage(), holder.image_search,
						holder.spinner_search);

				holder.mainframe.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						mListener.onItemClicked1(newsObj);
					}
				});

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return convertView;
	}

	class ViewHolder {

		ImageView image_search;
		TextView title_search;
		TextView desc_search;
		TextView price_search;
		ProgressBar spinner_search;
		LinearLayout mainframe;

	}
}

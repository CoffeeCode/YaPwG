package info.ap.yapwg.adapter;

import info.ap.yapwg.InfoFragment;
import info.ap.yapwg.GeneratorFragment;
import info.ap.yapwg.TestFragment;
import info.ap.yapwg.AdvGeneratorFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return new GeneratorFragment();
		case 1:
			return new AdvGeneratorFragment();			
		case 2:
			return new TestFragment();
		case 3:
			return new InfoFragment();			

		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 4;
	}

}

package pt.ipbeja.pdm2.swimapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeadlinesFragment extends ListFragment {


    public HeadlinesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, NewsData.Headlines);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_headlines, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);

        /*Toast.makeText(getActivity(), NewsData.Articles[ pos ] + " was clicked",
                Toast.LENGTH_SHORT).show();*/

        if (getActivity().findViewById(R.id.fragment_container) != null){

            /*Toast.makeText(getActivity().getBaseContext(), "Clicked Portrait." + NewsData.Headlines[pos],
                    Toast.LENGTH_SHORT).show();*/

            Toast.makeText(getActivity().getBaseContext(), NewsData.Headlines[pos],
                    Toast.LENGTH_SHORT).show();

            // Create fragment and give it an argument specifying the article it should show
            ArticleFragment newFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt("position", pos);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();

        }
        else{

            /*Toast.makeText(getActivity().getBaseContext(), "Clicked Landscape." + NewsData.Headlines[pos],
                    Toast.LENGTH_SHORT).show();*/

            Toast.makeText(getActivity().getBaseContext(), NewsData.Headlines[pos],
                    Toast.LENGTH_SHORT).show();

            //TextView article = (TextView) getActivity().findViewById(R.id.article_text);
            //article.setText(NewsData.Articles[pos]);

        }

    }

}

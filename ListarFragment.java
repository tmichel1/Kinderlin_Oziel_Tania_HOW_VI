### lvm/app/src/main/java/br/com/dlweb/lvm/unidade/ListarFragment.java
package br.com.dlweb.lvm.unidade;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import br.com.dlweb.lvm.R;
import br.com.dlweb.lvm.database.DatabaseHelper;

public class ListarFragment extends Fragment {

    public ListarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.unidade_fragment_listar, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.listViewUnidades);
        databaseHelper.getAllUnidade(getActivity(), lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvId = view.findViewById(R.id.textViewIdListUnidade);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));

                EditarFragment editarFragment = new EditarFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                editarFragment.setArguments(b);
                ft.replace(R.id.frameunidade, editarFragment).commit();
            }
        });

        return v;
    }
}
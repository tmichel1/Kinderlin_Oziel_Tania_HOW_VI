#lvms/app/src/main/java/br/com/dlweb/lvm/unidade/MainFragment.java 
package br.com.dlweb.lvm.unidade;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.dlweb.lvm.R;

public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.unidade_fragment_main, container, false);
        if (savedInstanceState == null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameUnidade, new ListarFragment()).commit();
        }
        Button btnListar = v.findViewById(R.id.buttonListar);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameUnidade, new	ListarFragment()).commit();
            }
        });
        Button btnAdicionar = v.findViewById(R.id.buttonAdicionar);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameUnidade, new AdicionarFragment()).commit();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }
}

// lvm/app/src/main/java/br/com/dlweb/lvm/unidade/AdicionarFragment.java 
package br.com.dlweb.lvm.unidade;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.dlweb.lvm.R;
import br.com.dlweb.lvm.database.DatabaseHelper;


public class AdicionarFragment extends Fragment {

    private EditText etNome;
   
    public AdicionarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.unidade_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editTextNomeUnidade);
       
    

        Button btnAdicionar = v.findViewById(R.id.buttonAdicionarUnidade);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
            }
         })

        return v;
    }

    private void adicionar () {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        }  else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            unidade m = new unidade();
            m.setNome(etNome.getText().toString());
            
            databaseHelper.createunidade(m);
            Toast.makeText(getActivity(), "Unidade Temática  salva!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameUnidade, new ListarFragment()).commit();
        }
    }
}
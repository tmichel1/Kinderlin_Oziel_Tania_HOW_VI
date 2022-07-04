package br.com.dlweb.lvm.conteudo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import br.com.dlweb.lvm.R;
import br.com.dlweb.lvm.database.DatabaseHelper;

public class EditarFragment extends Fragment {

    EditText etNome;
   
    Spinner spUnidade;
    ArrayList<Integer> listUnidadeId;
    ArrayList<String> listUnidadeName;
  
    DatabaseHelper databaseHelper;
    conteudo b;

    public EditarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.conteudo_fragment_editar, container, false);
        Bundle bundle = getArguments();
        int id_conteudo = bundle.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        b = databaseHelper.getByIdconteudo(id_conteudo);

        spUnidade = v.findViewById(R.id.spinnerUnidadeconteudo);
        
        etNome = v.findViewById(R.id.editTextNomeconteudo);
       

        listUnidadeId = new ArrayList<>();
        listUnidadeName = new ArrayList<>();
        databaseHelper.getAllNameUnidade(listUnidadeId, listUnidadeName);

        
        listobjetoName = new ArrayList<>();
        

        ArrayAdapter<String> spUnidadeArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, listUnidadeName);
        spUnidade.setAdapter(spUnidadeArrayAdapter);

        

        etNome.setText(b.getNome());
        
        spUnidade.setSelection(listUnidadeId.indexOf(b.getId_Unidade()));
        

        Button btnEditar = v.findViewById(R.id.buttonEditarconteudo);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_conteudo);
            }
        });

        Button btnExcluir = v.findViewById(R.id.buttonExcluirconteudo);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.dialog_excluir_conteudo);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir(id_conteudo);
                    }
                });
                builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Não faz nada
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return v;
    }

    private void editar (int id) {
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            conteudo b = new conteudo();
            b.setId(id);
            String nomeUnidade = spUnidade.getSelectedItem().toString();
            b.setId_Unidade(listUnidadeId.get(listUnidadeName.indexOf(nomeUnidade)));
        
            
            b.setNome(etNome.getText().toString());
            
            databaseHelper.updateconteudo(b);
            Toast.makeText(getActivity(), "Conteudo editado!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameconteudo, new ListarFragment()).commit();
        }
    }

    private void excluir (int id) {
        b = new conteudo();
        b.setId(id);
        databaseHelper.deleteconteudo(b);
        Toast.makeText(getActivity(), "Conteudo excluído!", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameconteudo, new ListarFragment()).commit();
    }
}
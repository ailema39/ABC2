package com.mycompany.abc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DangerSituationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_danger_situation, container, false);
        TextView t = (TextView)v.findViewById(R.id.a_tip);

        t.setText("En caso de presencia de humo en la cabina se debe caminar por el sendero luminoso que se encuentra ubicado en el piso. Esta medida de seguridad obedece a la presencia de oxigeno en esta zona.");
        t = (TextView)v.findViewById(R.id.b_tip);
        t.setText("Atender los consejos de evacuación,  uso de chalecos, mascarillas de oxigeno que le podría salvar la vida ante una situación de peligro.");
        t = (TextView)v.findViewById(R.id.c_tip);
        t.setText("Prohibido fumar dentro de los aviones, utilizar los cinturones de seguridad al despegue y aterrizaje y en los momentos que lo soliciten, al igual que los dispositivos electrónicos.");

        return v;
    }

}

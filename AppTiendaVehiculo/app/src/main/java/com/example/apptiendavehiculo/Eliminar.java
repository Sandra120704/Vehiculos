package com.example.apptiendavehiculo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Eliminar extends AppCompatActivity {

    TextView lblInfo;
    Button btnEliminar;
    int idVehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eliminar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> insets);

        lblInfo = findViewById(R.id.lblInfo);
        btnEliminar = findViewById(R.id.btnEliminar);

        idVehiculo = getIntent().getIntExtra("id", 0);
        String placa = getIntent().getStringExtra("placa");

        lblInfo.setText("¿Desea eliminar el vehículo con placa: " + placa + "?");

        btnEliminar.setOnClickListener(v -> eliminarVehiculo());
    }

    private void eliminarVehiculo() {
        String url = "http://192.168.1.10:3000/vehiculos/" + idVehiculo;

        StringRequest request = new StringRequest(Request.Method.DELETE, url,
                response -> {
                    Toast.makeText(getApplicationContext(), "Vehículo eliminado", Toast.LENGTH_SHORT).show();
                    finish();
                },
                error -> Toast.makeText(getApplicationContext(), "Error al eliminar", Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(this).add(request);
    }
}

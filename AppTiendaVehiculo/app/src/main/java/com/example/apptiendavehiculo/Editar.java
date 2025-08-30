package com.example.apptiendavehiculo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Editar extends AppCompatActivity {
    EditText txtMarca, txtModelo, txtColor, txtPrecio, txtPlaca;
    Button btnActualizar;
    int idVehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> insets);

        txtMarca = findViewById(R.id.txtMarca);
        txtModelo = findViewById(R.id.txtModelo);
        txtColor = findViewById(R.id.txtColor);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtPlaca = findViewById(R.id.txtPlaca);
        btnActualizar = findViewById(R.id.btnActualizar);

        idVehiculo = getIntent().getIntExtra("id", 0);
        txtMarca.setText(getIntent().getStringExtra("marca"));
        txtModelo.setText(getIntent().getStringExtra("modelo"));
        txtColor.setText(getIntent().getStringExtra("color"));
        txtPrecio.setText(getIntent().getStringExtra("precio"));
        txtPlaca.setText(getIntent().getStringExtra("placa"));

        btnActualizar.setOnClickListener(v -> actualizarVehiculo());
    }

    private void actualizarVehiculo() {
        String url = "http://10.0.2.2:3000/vehiculos/" + idVehiculo;

        StringRequest request = new StringRequest(Request.Method.PUT, url,
                response -> {
                    Toast.makeText(getApplicationContext(), "Vehículo actualizado", Toast.LENGTH_SHORT).show();
                    finish();
                },
                error -> Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("marca", txtMarca.getText().toString());
                params.put("modelo", txtModelo.getText().toString());
                params.put("color", txtColor.getText().toString());
                params.put("precio", txtPrecio.getText().toString());
                params.put("placa", txtPlaca.getText().toString());
                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}

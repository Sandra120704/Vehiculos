require('dotenv').config();
const express = require('express');
const pool = require('./db');
const app = express();
app.use(express.json()); // Middleware para recibir JSON

// Puerto del servidor
const port = process.env.PORT || 3000;

// Manejo de errores de BD
const handDbError = (res, error) => {
  console.error('Error de acceso a la base de datos', error);
  res.status(500).json({ success: false, error: 'Error interno en el servidor' });
};

/* VERBOS
POST   : Inserción
GET    : Consulta
PUT    : Actualización
DELETE : Eliminación
*/


// GET - Listar todos los vehículos
app.get('/vehiculos', async (req, res) => {
  try {
    const [rows] = await pool.query('SELECT * FROM vehiculos');
    res.status(200).json({ success: true, data: rows, message: 'Vehículos obtenidos correctamente' });
  } catch (error) {
    handDbError(res, error);
  }
});

// POST - Registrar un nuevo vehículo
app.post('/vehiculos', async (req, res) => {
  const { marca, modelo, color, precio, placa } = req.body;

  // Validación de campos
  if (!marca || !modelo || !color || !precio || !placa) {
    return res.status(400).json({ success: false, error: 'Todos los campos son obligatorios' });
  }
  if (isNaN(precio)) {
    return res.status(400).json({ success: false, error: 'El precio debe ser numérico' });
  }

  try {
    const [result] = await pool.query(
      'INSERT INTO vehiculos (marca, modelo, color, precio, placa) VALUES (?, ?, ?, ?, ?)',
      [marca, modelo, color, precio, placa]
    );

    res.status(201).json({
      success: true,
      data: { id: result.insertId, marca, modelo, color, precio, placa },
      message: 'Vehículo registrado correctamente',
    });
  } catch (error) {
    if (error.code === 'ER_DUP_ENTRY') {
      return res.status(409).json({ success: false, error: 'La placa ya existe' });
    }
    handDbError(res, error);
  }
});

// PUT - Actualizar vehículo
app.put('/vehiculos/:id', async (req, res) => {
  const { id } = req.params;
  const { marca, modelo, color, precio, placa } = req.body;

  if (!marca || !modelo || !color || !precio || !placa) {
    return res.status(400).json({ success: false, error: 'Todos los campos son obligatorios' });
  }
  if (isNaN(precio)) {
    return res.status(400).json({ success: false, error: 'El precio debe ser numérico' });
  }

  try {
    const [result] = await pool.query(
      'UPDATE vehiculos SET marca = ?, modelo = ?, color = ?, precio = ?, placa = ? WHERE id = ?',
      [marca, modelo, color, precio, placa, id]
    );

    if (result.affectedRows === 0) {
      return res.status(404).json({ success: false, error: 'Vehículo no existe' });
    }

    res.status(200).json({
      success: true,
      data: { id, marca, modelo, color, precio, placa },
      message: 'Vehículo actualizado correctamente',
    });
  } catch (error) {
    if (error.code === 'ER_DUP_ENTRY') {
      return res.status(409).json({ success: false, error: 'La placa ya existe' });
    }
    handDbError(res, error);
  }
});

// DELETE - Eliminar vehículo
app.delete('/vehiculos/:id', async (req, res) => {
  const { id } = req.params;

  try {
    const [result] = await pool.query('DELETE FROM vehiculos WHERE id = ?', [id]);

    if (result.affectedRows === 0) {
      return res.status(404).json({ success: false, error: 'Vehículo no existe' });
    }

    res.status(200).json({
      success: true,
      data: { id },
      message: 'Vehículo eliminado correctamente',
    });
  } catch (error) {
    handDbError(res, error);
  }
});


app.listen(port, () => {
  console.log(`Servidor iniciado en http://localhost:${port}`);
});

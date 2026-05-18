package cl.canchapro.ms_canchas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/canchas")
public class CanchaController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getCanchas() {
        
        return jdbcTemplate.queryForList("SELECT * FROM canchas");
    }
    @PostMapping
public String crearCancha(@RequestBody Map<String, Object> nuevaCancha) {
    String sql = "INSERT INTO canchas (nombre, tipo, precio, estado) VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(sql, 
        nuevaCancha.get("nombre"), 
        nuevaCancha.get("tipo"), 
        nuevaCancha.get("precio"), 
        nuevaCancha.get("estado")
    );
    return "Cancha creada exitosamente en la base de datos.";
}
}
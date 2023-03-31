/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.ALL.Controller;

import com.portfolio.ALL.Dto.dtoProyecto;
import com.portfolio.ALL.Entity.Proyecto;
import com.portfolio.ALL.Security.Controller.Mensaje;
import com.portfolio.ALL.Service.SProyecto;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author llopi
 */
@RestController
@CrossOrigin(origins= {"http://localhost:4200","https://frontendall-4901b.web.app"})
@RequestMapping("/proyecto")

public class CProyecto {

    @Autowired
    SProyecto sproyecto;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = sproyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
     @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id")int id){
        if(!sproyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("No Existe"),HttpStatus.NOT_FOUND);
        }
        Proyecto proyecto = sproyecto.getOne(id).get();
        return new ResponseEntity(proyecto,HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!sproyecto.existsById(id)){
            return new ResponseEntity(new Mensaje("No Existe"),HttpStatus.NOT_FOUND);
        }
        sproyecto.delete(id);
        return new ResponseEntity(new Mensaje("Proyecto Eliminado"),HttpStatus.OK);
    }
    
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoproyecto){
        if(StringUtils.isBlank(dtoproyecto.getNombre()))
            return new ResponseEntity(new Mensaje("El Nombre Obligatorio"),HttpStatus.BAD_REQUEST);
        if(sproyecto.existsByNombre(dtoproyecto.getNombre()))
            return new ResponseEntity(new Mensaje("Ese Proyecto ya existe"), HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto = new Proyecto(dtoproyecto.getNombre(),dtoproyecto.getDescripcion(),dtoproyecto.getImgp());
        sproyecto.save(proyecto);
        
        return new ResponseEntity (new Mensaje("Proyecto Cargado"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoproyecto){
        if(!sproyecto.existsById(id))
            return new ResponseEntity(new Mensaje("el id no existe"), HttpStatus.BAD_REQUEST);
        
        if(sproyecto.existsByNombre(dtoproyecto.getNombre()) && sproyecto.getByNombre(dtoproyecto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Ese Proyecto ya existe"),HttpStatus.BAD_REQUEST);
           
        if(StringUtils.isBlank(dtoproyecto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        
        Proyecto proyecto = sproyecto.getOne(id).get();
        proyecto.setNombre(dtoproyecto.getNombre());
        proyecto.setDescripcion((dtoproyecto.getDescripcion()));
        proyecto.setImgp(dtoproyecto.getImgp());
        
        sproyecto.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto modificado correctamente"),HttpStatus.OK);
    
    }
}

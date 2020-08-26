package com.examplesec.demo.rest;

import com.examplesec.demo.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestController {

    private List<Developer> developers = Stream.of(
         new Developer(1L, "in", "in"),
         new Developer(2L, "sadf", "sdf")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Developer> getAll(){
        return developers;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developer:read')")
    public Developer getById(@PathVariable Long id){
      return  developers.stream().filter(developer -> developer.getId().equals(id)).findFirst().orElseGet(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('developer:write')")
    public Developer create(@RequestBody Developer developer) {
        developers.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        developers.removeIf( developer -> developer.getId().equals(id));
    }
}

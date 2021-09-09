package com.akpp.baba_api_prototype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping(
        path = "/baba",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class BabaController {

    private BabaService babaService;

    @Autowired
    public BabaController(BabaService babaService) {
        this.babaService = babaService;
    }

    @GetMapping
    public List<Baba> findAll() {
        return babaService.findAll();
    }

    @GetMapping("/{id}")
    public Baba findOne(@PathVariable("id") UUID id) {
        return babaService.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Baba add(@RequestBody @Valid Baba baba) {
        return babaService.save(baba);
    }

    @PutMapping("/{id}")
    public Baba update(@PathVariable("id") UUID id, @RequestBody @Valid Baba newBaba) {
        return babaService.update(id, newBaba);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        babaService.delete(id);
    }

    @PostMapping("/{id}/vote")
    @ResponseStatus(HttpStatus.CREATED)
    public Baba vote(@PathVariable("id") UUID id, @RequestBody @Valid BabaVote babaVote) {
        return babaService.vote(id, babaVote);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> ((FieldError) error).getField(),
                        error -> Optional.ofNullable(error.getDefaultMessage()).orElse("UNKNOWN")));
    }
}

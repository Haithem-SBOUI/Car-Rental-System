package de.tekup.carrentalsystembackend.controller;



import de.tekup.carrentalsystembackend.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/analysis")
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class AnalysisController {
    private final AnalysisService analysisService;

    @GetMapping("/count-number-car-by-brand")
    public ResponseEntity<?> findNumberCarByBrand() {
        return ResponseEntity.ok(analysisService.findNumberCarByBrand());
    }


    @GetMapping("/count-by-pickup-month")
    public ResponseEntity<List<Object[]>> countByPickupMonth() {
        List<Object[]> chartData = analysisService.countByPickupMonth();
        return ResponseEntity.ok(chartData);
    }

    @GetMapping("/count-by-created-on")
    public ResponseEntity<List<Object[]>> countByCreatedOn() {
        List<Object[]> chartData = analysisService.countByCreatedOn();
        return ResponseEntity.ok(chartData);
    }


}
